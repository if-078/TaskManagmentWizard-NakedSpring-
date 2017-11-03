
  $(document).ready(function () {
//         $("#main, #registration, #login").hide();



    if(window.sessionStorage.getItem("token")){
        $("#main").show();
    }else{
          $("#login").show();
          }
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
        state.dateFrom = currentData.setHours(0,0,0,0);
        state.dateTo = currentData.setHours(23,59,59,0) + 1000;
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
        state.dateTo = Date.parse( $('#tmw-time-custom-to').val() ) + 86400000;

        $('#tmw-time-btn-group > button, #tmw-time-custom-btn').removeClass('active');
        $('#tmw-time-custom-btn').addClass('active');
        taskTable();
    });

    $('#tmw-time-custom-cancel-btn').click(function (e) {

    });


    //ON CLICK APPLY FILTERS --> STATUS, PRIORITY, TAG
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


    // ON CLICK RESET FILTERS --> STATUS, PRIORITY, TAG
    $('#tmw-reset-btn').click(function () {
        state.status = [];
        state.priority = [];
        state.tag = [];
        $("#statusBox, #priorityBox, #tagBox").combobox('clear');

        taskTable();
    });


    // CREATE TREEVIEW
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
                            return 'api/tasks/tree/0';

                        default:
                            return 'api/tasks/tree/' + node.id;
                    }
                }
            },

            dblclick_toggle: false
        }
    });

    var refreshTree = function () {
        $.jstree.reference('#tmw-treeview').select_node(state.parentid);
        $('#tmw-treeview').jstree(true).close_node(state.parentid);
    }

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

    // DOUBLE-CLICK ON ROOT-TASK
    $('#tmw-treeview').on('dblclick.jstree',function (event, data) {

        var node = $(event.target).closest('li');
        var id = node[0].id;
        if ((data==undefined)&&(id != '$')) {
            showFull(id);
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
            url: 'api/tasks/filter' + generatedRequestParameters(),
            type: 'GET',
            contentType: 'application/json',
            headers: createAuthToken(),
            success: function (data, textStatus, jqXHR) {
                setToken(jqXHR);

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

                        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
                            if ( aData[5] == "DONE" )
                            {
                                $('td', nRow).css('background-color', '#66ff66' );
                            }
                            else if ( aData[5] == "INPROGRESS" )
                            {
                                $('td', nRow).css('background-color', '#d2ff4d');
                            }
                            else if ( aData[5] == "NEW" )
                            {
                                $('td', nRow).css('background-color', '#b3d9ff');
                            }
                            else if ( aData[5] == "REVIEW" )
                            {
                                $('td', nRow).css('background-color', '#ffff99');
                            }
                            else
                            {
                                $('td', nRow).css('background-color', '#f2f2f2');
                            }
                        },

                        paging: false,
                        info: false,
                        searching: false
                    });
                } else {
                    $('#tmw-task-table').css('visibility', 'hidden');
                }
            },
             error: function (jqXHR, textStatus, errorThrown) {
                                    if (jqXHR.status === 401) {
                                        resetToken();
                                    } else {
                                        throw new Error("an unexpected error occured: " + errorThrown);
                                    }
                                }
        });
    }


    // SHOW FULL INFORMATION ABOUT THE TASK
    var showFull = function (id) {
        taskDTO = {};
        clearTaskModal();
        clearErrorTask();
        // AJAX return response full info one User
        $.ajax({
            url: 'api/tasks/view/' + id,
            type: 'GET',
            contentType: 'application/json',

            headers:createAuthToken(),
            success: function (data, textStatus, jqXHR) {
                 var token= jqXHR.getResponseHeader('Authentication');
                 window.sessionStorage.setItem("token",token);
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
            },
             error: function (jqXHR, textStatus, errorThrown) {
                                    if (jqXHR.status === 401) {
                                        resetToken();
                                    } else {
                                        throw new Error("an unexpected error occured: " + errorThrown);
                                    }
                                }
        });
    };


    // GET FULL INFORMATION ABOUT THE TASK
    $('#tmw-task-btn-save').on( 'click',function () {
        createOrUpdatetask(taskDTO);
    } );

    $('#tmw-task-table').on('dblclick', 'tr', function () {
        var table = $('#tmw-task-table').DataTable();
        var taskId = table.row(this).data()[0];
        showFull(taskId);
    });

    $('#tmw-task-table').on('click', 'tr:first-child', function(e) {
        e.stopPropagation();
    });

    $('#tmw-task-table').on('click', 'tr', function () {
        var table = $('#tmw-task-table').DataTable();
        if(table!= undefined) {
            taskID = table.row(this).data()[0];
        }
    });

    $('#tmw-delete-task').on("click", function () {
        deletetask(taskID);
    } );

    $('#tmw-create-task').on('click', function () {
        clearErrorTask();
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
        clearErrorTask();
        taskDTO = {};
        $.ajax({
            url: 'tasks',
            data: JSON.stringify(task),
            type: 'POST',
            contentType: 'application/json',
            success: function (data) {
                $('#tmw-modal').modal('hide');
                refreshTree();
                clearTaskModal();
                taskTable();
            },
            cache: false
        }).fail(function ($xhr) {
            if($xhr.status == 400){
                var data = $xhr.responseJSON;
                showErrorsOfForm(data)
            }
        });
    }

    function updatetask(task){
        clearErrorTask();
        $.ajax({
            url: 'tasks/update',
            data: JSON.stringify(task),
            type: 'PUT',
            contentType: 'application/json',
            success: function () {
                $('#tmw-modal').modal('hide');
                refreshTree();
                clearTaskModal();
                taskTable();
            },
            cache: false
        }).fail(function ($xhr) {
            if($xhr.status == 400){
                var data = $xhr.responseJSON;
                showErrorsOfForm(data)
            }
        });
    };

    function deletetask(taskId){
        $.ajax({
            type: 'DELETE',
            url: 'tasks/' + taskId,
            contentType: 'application/json',
            success: function () {
                refreshTree();
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
            url: 'api/users/all',
            type: 'GET',
            contentType: 'application/json',
            headers: createAuthToken(),
            success: function (data, textStatus, jqXHR) {
                var token= jqXHR.getResponseHeader('Authentication');
                window.sessionStorage.setItem("token",token);
                $.each(data, function(i, user) {
                    $('#tmw-task-assignTo').append($('<option>', {
                        value: user.id,
                        text: user.name
                    }));
                });

                if(id != null) $('#tmw-task-assignTo').val(id);

            },
             error: function (jqXHR, textStatus, errorThrown) {
                                    if (jqXHR.status === 401) {
                                        resetToken();
                                    } else {
                                        throw new Error("an unexpected error occured: " + errorThrown);
                                    }
                                }
        });
    }

    function fillSelectPriority(id){
        $('#tmw-task-priority').empty();
        $.ajax({
            url: 'api/priority',
            type: 'GET',
            contentType: 'application/json',
            headers: createAuthToken(),
            success: function (data, textStatus, jqXHR) {
                var token= jqXHR.getResponseHeader('Authentication');
                                 window.sessionStorage.setItem("token",token);
                $.each(data, function(i, priority) {
                    $('#tmw-task-priority').append($('<option>', {
                        value: priority.id,
                        text: priority.name
                    }));
                });

                if(id != null) $('#tmw-task-priority').val(id);

            },
             error: function (jqXHR, textStatus, errorThrown) {
                                    if (jqXHR.status === 401) {
                                        resetToken();
                                    } else {
                                        throw new Error("an unexpected error occured: " + errorThrown);
                                    }
                                }
        });
    }

    function fillSelectStatus(id){
        $('#tmw-task-status').empty();
        $.ajax({
            url: 'api/status',
            type: 'GET',
            contentType: 'application/json',
            headers:createAuthToken(),
            success: function (data, textStatus, jqXHR) {
                    var token= jqXHR.getResponseHeader('Authentication');
                    window.sessionStorage.setItem("token",token);
                $.each(data, function(i, status) {
                    $('#tmw-task-status').append($('<option>', {
                        value: status.id,
                        text: status.name
                    }));
                });

                if(id != null) $('#tmw-task-status').val(id);

            },
             error: function (jqXHR, textStatus, errorThrown) {
                                    if (jqXHR.status === 401) {
                                        resetToken();
                                    } else {
                                        throw new Error("an unexpected error occured: " + errorThrown);
                                    }
                                }
        });
    }


    function createAuthToken(){
    var token=window.sessionStorage.getItem("token");
    if(token){
        return {"Authentication": token}
    }else{
    return {}}
    }

        $("#loginForm").submit(function (event) {
            event.preventDefault();

            var $form = $(this);
            var formData = {
                username: $form.find('input[name="email"]').val(),
                password: $form.find('input[name="password"]').val()
            };

            doLogin(formData);
        });

        function doLogin(loginData) {
                $.ajax({
                    url: "/login",
                    type: "POST",
                    data: JSON.stringify(loginData),
                    contentType: "application/json; charset=utf-8",
                    dataType: "json",
                    success: function (data, textStatus, jqXHR) {
                        setToken(jqXHR);
                        $("#logout").show();
                        $("#login").hide();
                        $("#main").show();
                        $("#leftPanel").load("static/load-pages/taskFilter.html");
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        if (jqXHR.status === 401) {
                            resetToken();
                        } else {
                            throw new Error("an unexpected error occured: " + errorThrown);
                        }
                    }
                });
            }

                $("#logout").click(function(){
                $("#logout").hide();
                $("#main").hide();
                $("#login").show();
                  resetToken();
                });


        function resetToken() {
            window.sessionStorage.removeItem("token");
            window.location.href = "";
        }
        
        function setToken(jqXHR) {
            var token = jqXHR.getResponseHeader('Authentication');
            window.sessionStorage.setItem("token", token);
            $.ajaxSetup({
                headers: createAuthToken()
            });
        }

    function clearErrorTask(){

        $('#tmw-task-name').css({"border-color": "","border-width":"","border-style":""});
        $('#tmw-task-endDate').css({"border-color": "","border-width":"","border-style":""});
        $('#tmw-task-startDate').css({"border-color": "","border-width":"","border-style":""});
        $('#tmw-task-estimateTime').css({"border-color": "","border-width":"","border-style":""});

        $('#tmw-task-name-error').empty();
        $('#tmw-task-startDate-error').empty();
        $('#tmw-task-endDate-error').empty();
        $('#tmw-task-estimateTime-error').empty();
    }

    function showErrorsOfForm(data){
        for(var i = 0; i < data.fieldErrors.length; i++) {
            if (data.fieldErrors[i].field == 'name') {
                $('#tmw-task-name').css({"border-color": "#FF0000","border-width":"1px","border-style":"solid"});
                $('#tmw-task-name-error').text(data.fieldErrors[i].message).css('color', 'red');
            }
            if (data.fieldErrors[i].field == 'startDate') {
                $('#tmw-task-startDate').css({"border-color": "#FF0000","border-width":"1px","border-style":"solid"});
                $('#tmw-task-startDate-error').text(data.fieldErrors[i].message).css('color', 'red');
            }
            if (data.fieldErrors[i].field == 'endDate') {
                $('#tmw-task-endDate').css({"border-color": "#FF0000","border-width":"1px","border-style":"solid"});
                $('#tmw-task-endDate-error').text(data.fieldErrors[i].message).css('color', 'red');
            }
            if (data.fieldErrors[i].field == 'estimateTime') {
                $('#tmw-task-estimateTime').css({"border-color": "#FF0000","border-width":"1px","border-style":"solid"});
                $('#tmw-task-estimateTime-error').text(data.fieldErrors[i].message).css('color', 'red');
            }
        }
    }

});
