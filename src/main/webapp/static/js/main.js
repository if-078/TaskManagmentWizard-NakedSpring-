
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

    var taskID = null;
    var taskDTO = {};

    // STATE OF APPLIED FILTERS


    // ON CLICK - SELECT TIME - ALL
    $('#tmw-time-all-btn').click(function (){
        $('#tmw-info-selected-time').html('Selected Time : All');
        state.dateFrom = 0;
        state.dateTo = 0;
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

    //var taskDTO = {};

    var showFull = function (id) {
        taskDTO = {};
        clearTaskModal();
        // AJAX return response full info one User
        $.ajax({
            url: 'tasks/view/' + id,
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {
                taskDTO = data;

                $('#tmw-task-name').val(taskDTO.name);
                $('#tmw-task-createDate').val(taskDTO.createdDate);
                $('#tmw-task-startDate').val(taskDTO.startDate);
                $('#tmw-task-endDate').val(taskDTO.endDate);
                $('#tmw-task-estimateTime').val(taskDTO.estimateTime);

                fillSelectUser(taskDTO.assignTo.id);
                fillSelectPriority(taskDTO.priority.id);
                fillSelectStatus(taskDTO.status.id);

                $('#tmw-task-table tbody').on( 'click', 'tr', function () {
                    $(this).addClass('active').siblings().removeClass('active');
                    $(this).css('background-color', 'red').siblings().css('background-color', '');
                } );

                $('#tmw-modal').modal('show');
            }
        });
    };

    // GET FULL INFORMATION ABOUT THE TASK

    $('#tmw-task-btn-save').on( 'click',function () {
        console.log("-----------");
        console.log(taskDTO);
        console.log("-----------");

        createOrUpdatetask(taskDTO);
    } );

    $('#tmw-task-table').on('dblclick', 'tr', function () {
        var table = $('#tmw-task-table').DataTable();
        var taskId = table.row(this).data()[0];
        showFull(taskId);
    });

    $('#tmw-task-table').on('click', 'tr', function () {
        var table = $('#tmw-task-table').DataTable();
        taskID = table.row(this).data()[0];
    });

    $('#tmw-delete-task').on("click", function () {
        console.log(taskID);
        deletetask(taskID);
    } );

    $('#tmw-create-task').on('click', function () {
        taskDTO = {}
        clearTaskModal();
        fillSelectUser(null);
        fillSelectPriority(null);
        fillSelectStatus(null);

        $('#tmw-modal').modal('show');
    });

    function createOrUpdatetask(taskDTO){
        var task = {};

        if ($.isEmptyObject(taskDTO)) {
            task =
                {
                    "name"         : $('#tmw-task-name').val(),
                    "createdDate"  : $('#tmw-task-createDate').val(),
                    "startDate"    : $('#tmw-task-startDate').val(),
                    "endDate"      : $('#tmw-task-endDate').val(),
                    "estimateTime" : $('#tmw-task-estimateTime').val(),
                    "assignTo"     : $('#tmw-task-assignTo').find(":selected").val(),
                    "statusId"     : $('#tmw-task-status').find(":selected").val(),
                    "priorityId"   : $('#tmw-task-priority').find(":selected").val(),
                    "parentId"     : state.parentid
                }

            createtask(task);
        }else {
                task =
                    {
                        "id"           : taskDTO.id,
                         "name"        : $('#tmw-task-name').val(),
                        "createdDate"  : $('#tmw-task-createDate').val(),
                        "startDate"    : $('#tmw-task-startDate').val(),
                        "endDate"      : $('#tmw-task-endDate').val(),
                        "estimateTime" : $('#tmw-task-estimateTime').val(),
                        "assignTo"     : $('#tmw-task-assignTo').find(":selected").val(),
                        "statusId"     : $('#tmw-task-status').find(":selected").val(),
                        "priorityId"   : $('#tmw-task-priority').find(":selected").val(),
                        "parentId"     : state.parentid
                    }

            updatetask(task);
        }
    }

    function createtask(task){
        taskDTO = {};
        $.ajax({
            url: 'tasks',
            data: JSON.stringify(task),
            type: 'POST',
            contentType: 'application/json',
            success: function (data) {

                console.log("good create");
                $('#tmw-modal').modal('hide');
                clearTaskModal();
                taskTable();
            },
            cache: false
        }).fail(function ($xhr) {
            console.log("bad")

            if($xhr.status == 400){
                var data = $xhr.responseJSON;
                console.log(data);
            }
        });
    }

    function updatetask(task){
        $.ajax({
            url: 'tasks/update',
            data: JSON.stringify(task),
            type: 'PUT',
            contentType: 'application/json',
            success: function () {
                console.log("update good");
                $('#tmw-modal').modal('hide');
                clearTaskModal();
                taskTable();
            },
            cache: false
        }).fail(function ($xhr) {
            console.log("update bad");
            if($xhr.status == 400){
                var data = $xhr.responseJSON;
                console.log(data);
            }
        });
    };

    function deletetask(taskId){
        $.ajax({
            type: 'DELETE',
            url: 'tasks/' + taskId,
            contentType: 'application/json',
            success: function () {
                taskTable();
            },
            error: function(jqXHR) {
                console.log(jqXHR.status)
            }
        });
    }


    function clearTaskModal(){
        $('#tmw-task-name').val('');
        $('#tmw-task-createDate').val('');
        $('#tmw-task-startDate').val('');
        $('#tmw-task-endDate').val('');
        $('#tmw-task-estimateTime').val('');
        $('#tmw-task-assignTo').empty();
        $('#tmw-task-status').empty();
        $('#tmw-task-priority').empty();
    }

    function fillSelectUser(id){
        $('#tmw-task-assignTo').empty();
        $.ajax({
            url: 'users/all',
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {

                $.each(data, function(i, user) {
                    $('#tmw-task-assignTo').append($('<option>', {
                        value: user.id,
                        text: user.name
                    }));
                });

                if(id != null) $('#tmw-task-assignTo').val(id);

            }
        });
    }

    function fillSelectPriority(id){
        $('#tmw-task-priority').empty();
        $.ajax({
            url: 'priority',
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {

                $.each(data, function(i, priority) {
                    $('#tmw-task-priority').append($('<option>', {
                        value: priority.id,
                        text: priority.name
                    }));
                });

                if(id != null) $('#tmw-task-priority').val(id);

            }
        });
    }

    function fillSelectStatus(id){
        $('#tmw-task-status').empty();
        $.ajax({
            url: 'status',
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {

                $.each(data, function(i, status) {
                    $('#tmw-task-status').append($('<option>', {
                        value: status.id,
                        text: status.name
                    }));
                });

                if(id != null) $('#tmw-task-status').val(id);

            }
        });
    }
});
