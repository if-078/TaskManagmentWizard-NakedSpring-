
$(document).ready(function () {

    // STATE OF APPLIED FILTERS
    var state = {
        parentid : 0,
        dateFrom : 0,
        dateTo : 0,
        status: [],
        priority: [],
        tag: []
    };
    // STATE OF APPLIED FILTERS


    // ON CLICK - SELECT TIME - ALL
    $('#tmw-time-all-btn').click(function (){
        $('#tmw-info-selected-time').html('Selected Time : All');
        state.dateFrom = 0;
        state.dateTo = 10000000000000;
        taskTable();
    });


    // ON CLICK - SELECT TIME - TODAY
    $('#tmw-time-today-btn').click(function (){
        $('#tmw-info-selected-time').html('Selected Time : Today');
        var currentData = new Date();
        var numberDay = currentData.getDay();
        state.dateFrom = currentData.setHours(0,0,0,0);
        state.dateTo = currentData.setHours(23,59,59,0);
        taskTable();
    });


    // ON CLICK - SELECT TIME - WEEK
    $('#tmw-time-week-btn').click(function (){
        $('#tmw-info-selected-time').html('Selected Time : Week');
        var currentData = new Date();
        var numberDay = currentData.getDay();
        state.dateFrom = currentData.setHours(0,0,0,0) - (numberDay-1)*86400000;
        state.dateTo = currentData.setHours(23,59,59,0) + (6-numberDay)*86400000;
        taskTable();
    });


    // ON CLICK - SELECT TIME - CUSTOM
    $('.datepicker').datepicker({
        autoclose: true
    });

    $('#tmw-time-btn-group > button').click(function (e) {
        e.preventDefault();
        $('#tmw-time-btn-group > button, #tmw-time-custom-btn').removeClass('active');
        $(this).addClass('active');
    });

    $('#tmw-time-custom-apply-btn').click(function (e) {
        e.preventDefault();

        if (!$('#tmw-time-custom-from').val() || !$('#tmw-time-custom-to').val()) {
            return;
        }

        state.dateFrom = Date.parse( $('#tmw-time-custom-from').val() );
        state.dateTo = Date.parse( $('#tmw-time-custom-to').val() ) + 86399999;

        $('#tmw-info-selected-time').html('Selected Time : ' + $('#tmw-time-custom-from').val() + " - " + $('#tmw-time-custom-to').val());
        $('#tmw-time-btn-group > button, #tmw-time-custom-btn').removeClass('active');
        $('#tmw-time-custom-btn').addClass('active');
        taskTable();
    });

    $('#tmw-time-custom-cancel-btn').click(function (e) {

    });
    // ON CLICK - SELECTED TIME - CUSTOM ======================================

    //ON CLICK APPLY FILTERS --> STATUS, PRIORITY, TAG ========================



    $('#tmw-apply-btn').click(function () {
        if (!$("#statusBox").val()=="") {
            state.status = $("#statusBox").val().split(",");
        }else  state.status = [];
        if (!$("#priorityBox").val()=="") {
            state.priority = $("#priorityBox").val().split(",");
        }else  state.priority = [];
        if (!$("#tagBox").val()=="") {
            state.tag = $("#tagBox").val().split(",");
        }else  state.tag =  [];

        console.log(state);
        taskTable();
    });
    // ON CLICK APPLY FILTERS --> STATUS, PRIORITY, TAG =======================


    // ON CLICK RESET FILTERS --> STATUS, PRIORITY, TAG =======================
    $('#tmw-reset-btn').click(function () {
        state.status = [];
        state.priority = [];
        state.tag = [];
        $("#statusBox, #priorityBox, #tagBox").combobox('clear');

        taskTable();
    });
    // ON CLICK RESET FILTERS --> STATUS, PRIORITY, TAG =======================


    //======================================================================================================================
    //======================================================================================================================


    //===============================================================================
    $('#tmw-treeview').jstree({
        core: {
            data: {
                url: function (node) {
//				$("ul.jstree-container-ul a.jstree-anchor").attr("title", "node.name");
                    switch (node.id) {
                        case '#':
                            var rootNode = {
                                id: '$',
                                text: 'All Projects',
                                children: true
                            };
                            return 'data:application/json,' + encodeURIComponent(JSON.stringify(rootNode));

                        case '$':
                            return 'tasks/tree/0';

                        default:
                            return 'tasks/tree/' + node.id;
                    }
                }
            },

            dblclick_toggle: false
        }
    });

    // REMOVE NODES IN A TREE
    $('#tmw-treeview').on('after_close.jstree', function (e, data) {
        var tree = $('#tmw-treeview').jstree(true);
        tree.delete_node(data.node.children);
        tree._model.data[data.node.id].state.loaded = false;
    });

    // OUTPUT TABLE SUBTASKS FOR SELECTED ROOT-TASK
    $('#tmw-treeview').on('select_node.jstree', function (event, data) {
        var hasChildren = (data.node.children.length > 0 || !data.node.state.loaded);
        if (hasChildren){

            state.parentid = data.node.id !== '$' ? data.node.id : 0;
            console.log("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+state.parentid)
            taskTable();
        }else{
            showFull(data.node.id);
        }
    });


    var generatedRequestParameters = function(){

        var parameters = '?parentid=' + state.parentid + '&date='+ state.dateFrom + ',' + state.dateTo;

        parameters = parameters + '&status=';
        for (var i = 0; i < state.status.length; i++) {
                parameters = parameters + state.status[i] + ',';

        }
        parameters = parameters.slice(0,-1);

        parameters = parameters + '&priority=';
        for (var i = 0; i < state.priority.length; i++) {

                parameters = parameters + state.priority[i] + ',';

        }
        parameters = parameters.slice(0,-1);

        parameters = parameters + '&tag=';
        for (var i = 0; i < state.tag.length; i++) {

                parameters = parameters + state.tag[i] + ',';

        }
        parameters = parameters.slice(0,-1);

        console.log("In method parameters   " + parameters);

        return parameters;
    }


    var taskTableInit = false;
    var taskTable = function () {
        $.ajax({
            url: 'tasks/filter' + generatedRequestParameters(),
            type: 'GET',
            contentType: 'application/json',

            success: function (data) {
                var rows = []

                for (var i = 0; i < data.length; i++) {
                    rows.push(Object.values(data[i]));
                }

                if (taskTableInit) {
                    $('#tmw-task-table').DataTable().destroy();
                }

                taskTableInit = true;

                if (rows.length > 0) {
                    $('#tmw-task-table').css('visibility', 'visible');

                    $('#tmw-task-table').DataTable({
                        data: rows,

                        columns: [
                            {title: "ID", visible: false},
                            {title: "Name"},
                            {title: "Start Date"},
                            {title: "Est. Time"},
                            {title: "Assignee"},
                            {title: "Status"},
                            {title: "Priority"}
                        ],

                        paging: false,
                        info: false,
                        searching: false
                    });
                } else {
                    $('#tmw-task-table').css('visibility', 'hidden');
                }
            }
        });
    }

    // SHOW FULL INFORMATION ABOUT THE TASK
    var showFull = function (id) {

        // AJAX return response full info one User
        $.ajax({
            url: 'tasks/view/' + id,
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {

                $('#tmw-user-field1').html(data.name);
                $('#tmw-user-field2').html(data.createdDate);
                $('#tmw-user-field3').html(data.startDate);
                $('#tmw-user-field4').html(data.estimateTime);
                $('#tmw-user-field5').html(data.assignTo.name);
                $('#tmw-user-field6').html(data.status.name);
                $('#tmw-user-field7').html(data.priority.name);

                $('#tmw-modal').modal('show');
            }
        });
    };

    // GET FULL INFORMATION ABOUT THE TASK
    $('#tmw-task-table').on('click', 'tr', function () {
        var table = $('#tmw-task-table').DataTable();
        var taskId = table.row(this).data()[0];
        showFull(taskId);
    });
});
