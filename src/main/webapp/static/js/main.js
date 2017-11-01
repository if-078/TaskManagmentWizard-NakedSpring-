
$(document).ready(function () {

    // State applaed filters ==================================================
    var state = {
        idActivTask : 0,
        dateFrom : '',
        dateTo : '',
        status: [],
        priority: [],
        tag: []
    };
    // State applaed filters ==================================================


    // ON CLICK - SELECTED TIME - ALL =========================================
    $('#tmw-time-all-btn').click(function (){
        $('#tmw-info-selected-time').html('Selected Time : All');
        state.dateFrom = '';
        state.dateTo = '';
        taskTable();
    });
    // ON CLICK - SELECTED TIME - ALL =========================================


    // ON CLICK - SELECTED TIME - TODAY =======================================
    $('#tmw-time-today-btn').click(function (){
        $('#tmw-info-selected-time').html('Selected Time : Today');
        state.dateFrom = '30/10/2017';
        state.dateTo = '30/10/2017';
        taskTable();
    });
    // ON CLICK - SELECTED TIME - TODAY =======================================


    // ON CLICK - SELECTED TIME - WEEK ========================================
    $('#tmw-time-week-btn').click(function (){
        $('#tmw-info-selected-time').html('Selected Time : Week');
        state.dateFrom = '30/10/2017';
        state.dateTo = '04/11/2017';
        taskTable();
    });
    // ON CLICK - SELECTED TIME - WEEK ========================================


    // ON CLICK - SELECTED TIME - CUSTOM ======================================
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

        state.dateFrom = $('#tmw-time-custom-from').val();
        state.dateTo = $('#tmw-time-custom-to').val();

        $('#tmw-info-selected-time').html('Selected Time : ' + state.dateFrom + " - " + state.dateTo);

        $('#tmw-time-btn-group > button, #tmw-time-custom-btn').removeClass('active');
        $('#tmw-time-custom-btn').addClass('active');
    });

    $('#tmw-time-custom-cancel-btn').click(function (e) {

    });
    // ON CLICK - SELECTED TIME - CUSTOM ======================================

    //ON CLICK APPLY FILTERS --> STATUS, PRIORITY, TAG ========================
    $('#tmw-apply-btn').click(function () {

        state.status = $("#statusBox").val().split(",");
        state.priority = $("#priorityBox").val().split(",");
        state.tag = $("#tagBox").val().split(",");
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

    $('#tmw-treeview').on('after_close.jstree', function (e, data) {
        var tree = $('#tmw-treeview').jstree(true);
        tree.delete_node(data.node.children);
        tree._model.data[data.node.id].state.loaded = false;
    });

    $('#tmw-treeview').on('select_node.jstree', function (event, data) {
        var hasChildren = (data.node.children.length > 0 || !data.node.state.loaded);
        if (hasChildren){
            state.idActivTask = data.node.id !== '$' ? data.node.id : 0;
            taskTable();
        }else{
            showFull(data.node.id);
        }
//		$("ul.jstree-container-ul a.jstree-anchor").attr("title", "node.name_view");
    });

    $('i.jstree-ocl').click(function() {
//		$("ul.jstree-container-ul a.jstree-anchor").attr("title", "data.node.id");
    });

    $("ul.jstree-container-ul a.jstree-anchor").mouseenter(function() {
        $("ul.jstree-container-ul a.jstree-anchor").css({"width": "100%", "overflow": "none", "color": "red"});
    });

    var generatedRequestParameters = function(){

        var parameters = '?taskid=' + state.idActivTask + '&date='+ state.dateFrom + ',' + state.dateTo;

        console.log(state.status);

        parameters = parameters + '&status=';
        for (var i = 0; i < state.status.length; i++) {
                parameters = parameters + state.status[i].id + ',';

        }
        parameters = parameters.slice(0,-1);

        parameters = parameters + '&priority=';
        for (var i = 0; i < state.priority.length; i++) {

                parameters = parameters + state.priority[i].id + ',';

        }
        parameters = parameters.slice(0,-1);

        parameters = parameters + '&tag=';
        for (var i = 0; i < state.tag.length; i++) {
           
                parameters = parameters + state.tag[i].id + ',';

        }
        parameters = parameters.slice(0,-1);

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
                console.log(data);
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
                $('#tmw-user-field5').html(data.assignTo);
                $('#tmw-user-field6').html(data.status);
                $('#tmw-user-field7').html(data.priority);

                $('#tmw-modal').modal('show');
            }
        });
    };

    $('#tmw-task-table').on('click', 'tr', function () {
        var table = $('#tmw-task-table').DataTable();
        var taskId = table.row(this).data()[0];
        showFull(taskId);
    });
});
