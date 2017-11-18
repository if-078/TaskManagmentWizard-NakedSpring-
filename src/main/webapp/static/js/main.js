/*$.ajaxSetup({
    global:true
});

$(document).ajaxError(function(jqXHR){
    if(jqXHR.status===401){
        $("#main, #registration").hide();
        $("#login").show();
    }

});*/

$(document).ready(function () {
    var datatoken = window.sessionStorage.getItem("token");
    if (datatoken != undefined | datatoken != null) {
        $.ajaxSetup({
            headers: createAuthToken()
        });
        $("#main").show();
        $("#user-login").text("Hello, " + window.sessionStorage.getItem("name"));
        $("#logout").show();
        $("")
    } else {
        $("#main").hide();
        $("#login").show();

    }

    // STATE OF APPLIED FILTERS
    var state = {
        parentid: 0,
        dateFrom: 0,
        dateTo: 0,
        status: [],
        priority: [],
        tag: []
    };

    var tags = [];

    var taskID = null;
    var taskDTO = {};

    // ON CLICK - SELECT TIME - ALL
    $('#tmw-time-all-btn').click(function () {
        $('#tmw-info-selected-time').html('Selected Time : All');
        state.dateFrom = 0;
        state.dateTo = 0;
        taskTable();
    });

    // ON CLICK - SELECT TIME - TODAY
    $('#tmw-time-today-btn').click(function () {
        $('#tmw-info-selected-time').html('Selected Time : Today');
        var currentData = new Date();
        state.dateFrom = currentData.setHours(0, 0, 0, 0);
        state.dateTo = currentData.setHours(23, 59, 59, 999);

        taskTable();
    });

    // ON CLICK - SELECT TIME - WEEK
    $('#tmw-time-week-btn').click(function () {
        $('#tmw-info-selected-time').html('Selected Time : Week');
        var currentData = new Date();
        var numberDay = currentData.getDay();

        state.dateFrom = currentData.setHours(0, 0, 0, 0) - (numberDay - 1) * 24 * 60 * 60 * 1000;
        state.dateTo = currentData.setHours(23, 59, 59, 999) + (8 - numberDay) * 24 * 60 * 60 * 1000;

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

        state.dateFrom = Date.parse($('#tmw-time-custom-from').val());
        state.dateTo = Date.parse($('#tmw-time-custom-to').val()) + 24 * 60 * 60 * 1000 - 1;

        $('#tmw-time-btn-group > button, #tmw-time-custom-btn').removeClass('active');
        $('#tmw-time-custom-btn').addClass('active');
        taskTable();
    });

    $('#tmw-time-custom-cancel-btn').click(function (e) {

    });


    //ON CLICK APPLY FILTERS --> STATUS, PRIORITY, TAG
    $('#tmw-apply-btn').click(function () {
        if (!$("#statusBox").val() == "") {
            state.status = $("#statusBox").val().split(",");
        } else state.status = [];
        if (!$("#priorityBox").val() == "") {
            state.priority = $("#priorityBox").val().split(",");
        } else state.priority = [];
        if (!$("#tagBox").val() == "") {
            state.tag = $("#tagBox").val().split(",");
        } else state.tag = [];

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

    var refreshTree = function (method, data) {
        switch (method) {
            case 'create': {
                var idParent = "#" + data.parentId + "_anchor";
                var level = Number($(idParent).attr("aria-level")) + 1;
                ($(idParent).next()).append("<li role=\"treeitem\" aria-selected=\"false\" " +
                    "aria-level=\"" + level + "\" aria-labelledby=\"" + data.id + "_anchor\" id=\"" +
                    data.id + "\" class=\"jstree-node  jstree-leaf\">" +
                    "<i class=\"jstree-icon jstree-ocl\" role=\"presentation\"></i><a class=\"jstree-anchor\" " +
                    "href=\"#\" tabindex=\"-1\" id=\"" + data.id + "_anchor\" title=\"" + data.name + "\">" +
                    "<i class=\"jstree-icon jstree-themeicon\" role=\"presentation\"></i>" + data.name + "</a></li>");
                break;
            }
            case 'update': {
                $("#" + data.id + "_anchor").html("<i class=\"jstree-icon jstree-themeicon\" " +
                    "role=\"presentation\"></i>" + data.name);
                break;
            }
            case 'delete': {
                $("li#" + data).remove();
            }
            default:
        }
        taskTable();
    };

    // REMOVE NODES IN A TREE
    $('#tmw-treeview').on('after_close.jstree', function (e, data) {
        var tree = $('#tmw-treeview').jstree(true);
        tree.delete_node(data.node.children);
        tree._model.data[data.node.id].state.loaded = false;
    });

    // OUTPUT TABLE SUBTASKS FOR SELECTED ROOT-TASK
    $('#tmw-treeview').on('select_node.jstree', function (event, data) {
        var hasChildren = (data.node.children.length > 0 || !data.node.state.loaded);
        if (hasChildren) {

            state.parentid = data.node.id !== '$' ? data.node.id : 0;
            taskTable();
        } else {
            showFull(data.node.id);
        }
    });

    // CREATE TOOLTIPE TASK
    $('#tmw-treeview').on("mouseenter.jstree", function (event, data) {
        aTree();
    });
    var aTree = function () {
        var tasks = $("ul.jstree-container-ul a.jstree-anchor");
        for (i = 0; i < tasks.length; i++) {
            tasks[i].title = tasks[i].innerText;
        }
    };

    // DOUBLE-CLICK ON ROOT-TASK
    $('#tmw-treeview').on('dblclick.jstree', function (event, data) {

        var node = $(event.target).closest('li');
        var id = node[0].id;
        if ((data == undefined) && (id != '$')) {
            showFull(id);
        }

    });


    var generatedRequestParameters = function () {
        var parameters = '?parentid=' + state.parentid + '&date=' + state.dateFrom + ',' + state.dateTo;
        parameters = parameters + '&status=';
        for (var i = 0; i < state.status.length; i++) {
            parameters = parameters + state.status[i] + ',';
        }
        parameters = parameters.slice(0, -1);
        parameters = parameters + '&priority=';
        for (var i = 0; i < state.priority.length; i++) {
            parameters = parameters + state.priority[i] + ',';
        }
        parameters = parameters.slice(0, -1);
        parameters = parameters + '&tag=';
        for (var i = 0; i < state.tag.length; i++) {
            parameters = parameters + state.tag[i] + ',';
        }
        parameters = parameters.slice(0, -1);
        return parameters;
    };

    var taskTableInit = false;
    var taskTable = function () {
        $.ajax({
            url: 'api/tasks/filter' + generatedRequestParameters(),
            type: 'GET',
            contentType: 'application/json',
            headers: createAuthToken(),
            success: function (data, textStatus, jqXHR) {
                setToken(jqXHR);

                $.ajax({
                    url: 'api/tasks/tree/' + state.parentid,
                    type: 'GET',
                    contentType: 'application/json',
                    success: function (treeData) {

                        var subtasks = [];

                        for (var i = 0; i < treeData.length; i++) {
                            subtasks.push(Object.values(treeData[i]));
                        }

                        var rows = [];

                        for (var i = 0; i < data.length; i++) {
                            var hasChildren = false;
                            for (var j = 0; j < subtasks.length; j++) {
                                if ((data[i].id == subtasks[j][0]) && (subtasks[j][2])) {
                                    hasChildren = true;
                                    break;
                                }
                            }
                            if (!hasChildren) {
                                rows.push(Object.values(data[i]));
                            }
                        }

                        if (taskTableInit) {
                            $('#tmw-task-table').DataTable().destroy();
                            taskTableInit = false;
                        }

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

                            taskTableInit = true;

                            if (!$('#tmw-main-calendar').hasClass('hidden')) {
                                makeTableRowsDraggable();
                            }
                        } else {
                            $('#tmw-task-table').css('visibility', 'hidden');
                        }
                    }
                });
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
            headers: createAuthToken(),

            success: function (data, textStatus, jqXHR) {
                var token = jqXHR.getResponseHeader('Authentication');
                window.sessionStorage.setItem("token", token);
                taskDTO = data;

                $('#tmw-task-name').val(taskDTO.name);
                $('#tmw-task-createDate').val(taskDTO.createdDate);
                $('#tmw-task-startDate').val(taskDTO.startDate);
                $('#tmw-task-endDate').val(taskDTO.endDate);
                $('#tmw-task-estimateTime').val(taskDTO.estimateTime);
                fillSelectUser(taskDTO.assignTo.id);
                fillSelectPriority(taskDTO.priority.id);
                fillSelectStatus(taskDTO.status.id);
                fillSelectTags(taskDTO.id);

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
    $('#tmw-task-btn-save').on('click', function () {
        createOrUpdatetask(taskDTO);
    });

    $('#tmw-task-btn-delete').on('click', function () {
        deletetask(taskDTO.id);
    });

    $('#tmw-task-table').on('dblclick', 'tr:not(:first)', 'tr', function () {
        var table = $('#tmw-task-table').DataTable();
        var taskId = table.row(this).data()[0];
        $(this).addClass('active').siblings().removeClass('active');
        showFull(taskId);
    });

    $('#tmw-task-table').on('click', 'tr:not(:first)', 'tr', function () {
        var table = $('#tmw-task-table').DataTable();
        $(this).addClass('active').siblings().removeClass('active');
        taskID = table.row(this).data()[0];
    });

    $('#tmw-delete-task').on("click", function () {
        deletetask(taskID);
    });

    $('#tmw-create-task').on('click', function () {
        clearErrorTask();
        taskDTO = {}
        clearTaskModal();
        fillSelectUser(null);
        fillSelectPriority(null);
        fillSelectStatus(null);
        fillSelectTags(null);
        //$('#tmw-tag-multi-select').multiselect('deselectAll', false);

        $('#tmw-modal').modal('show');
    });

    function createOrUpdatetask(taskDTO) {
        var task = {};

        if ($.isEmptyObject(taskDTO)) {
            task =
                {
                    "name": $('#tmw-task-name').val(),
                    "createdDate": $('#tmw-task-createDate').val(),
                    "startDate": $('#tmw-task-startDate').val(),
                    "endDate": $('#tmw-task-endDate').val(),
                    "estimateTime": $('#tmw-task-estimateTime').val(),
                    "assignTo": $('#tmw-task-assignTo').find(":selected").val(),
                    "statusId": $('#tmw-task-status').find(":selected").val(),
                    "priorityId": $('#tmw-task-priority').find(":selected").val(),
                    "parentId": state.parentid,
                    "tags": getSelectedTags(),
                }

            createtask(task);
        } else {
            task =
                {
                    "id": taskDTO.id,
                    "name": $('#tmw-task-name').val(),
                    "createdDate": $('#tmw-task-createDate').val(),
                    "startDate": $('#tmw-task-startDate').val(),
                    "endDate": $('#tmw-task-endDate').val(),
                    "estimateTime": $('#tmw-task-estimateTime').val(),
                    "assignTo": $('#tmw-task-assignTo').find(":selected").val(),
                    "statusId": $('#tmw-task-status').find(":selected").val(),
                    "priorityId": $('#tmw-task-priority').find(":selected").val(),
                    "parentId": state.parentid,
                    "tags": getSelectedTags(),
                }

            updatetask(task);
        }
    }

    function getSelectedTags() {
        var selectedIdOfTags = $('#tmw-tag-multi-select').val();
        var seletedTags = [];

        for (var i = 0; i < tags.length; i++) {
            for (var j = 0; j < selectedIdOfTags.length; j++) {
                if (tags[i].id == selectedIdOfTags[j]) {
                    seletedTags.push(tags[i]);
                    break;
                }
            }
        }
        return seletedTags;

    }

    function createtask(task) {
        clearErrorTask();
        taskDTO = {};
        $.ajax({
            url: '/api/tasks',
            data: JSON.stringify(task),
            type: 'POST',
            contentType: 'application/json',
            success: function (data) {
                $('#tmw-modal').modal('hide');
                refreshTree("create", data);
                clearTaskModal();
                taskTable();
            },
            cache: false
        }).fail(function ($xhr) {
            if ($xhr.status == 400) {
                var data = $xhr.responseJSON;
                showErrorsOfForm(data)
            }
        });
    }

    function updatetask(task) {
        clearErrorTask();
        $.ajax({
            url: '/api/tasks',
            data: JSON.stringify(task),
            type: 'PUT',
            contentType: 'application/json',
            success: function () {
                $('#tmw-modal').modal('hide');
                refreshTree("update", task);
                clearTaskModal();
                taskTable();
            },
            cache: false
        }).fail(function ($xhr) {
            if ($xhr.status == 400) {
                var data = $xhr.responseJSON;
                showErrorsOfForm(data)
            }
        });
    };

    function deletetask(taskId) {
        $.ajax({
            type: 'DELETE',
            url: '/api/tasks/' + taskId,
            contentType: 'application/json',
            success: function () {
                refreshTree("delete", taskId);
                taskTable();
            },
            error: function (jqXHR) {

            }
        });
    }


    function clearTaskModal() {
        $('#tmw-task-name').val('');
        $('#tmw-task-createDate').val('');
        $('#tmw-task-startDate').val('');
        $('#tmw-task-endDate').val('');
        $('#tmw-task-estimateTime').val('');
        $('#tmw-task-assignTo').empty();
        $('#tmw-task-status').empty();
        $('#tmw-task-priority').empty();
        $('#tmw-task-tag').empty();
    }

    function fillSelectUser(id) {
        $('#tmw-task-assignTo').empty();
        $.ajax({
            url: 'api/users/all',
            type: 'GET',
            contentType: 'application/json',
            headers: createAuthToken(),
            success: function (data, textStatus, jqXHR) {
                var token = jqXHR.getResponseHeader('Authentication');
                window.sessionStorage.setItem("token", token);
                $.each(data, function (i, user) {
                    $('#tmw-task-assignTo').append($('<option>', {
                        value: user.id,
                        text: user.name
                    }));
                });

                if (id != null) $('#tmw-task-assignTo').val(id);

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

    function fillSelectPriority(id) {
        $('#tmw-task-priority').empty();
        $.ajax({
            url: 'api/priority',
            type: 'GET',
            contentType: 'application/json',
            headers: createAuthToken(),
            success: function (data, textStatus, jqXHR) {
                var token = jqXHR.getResponseHeader('Authentication');
                window.sessionStorage.setItem("token", token);
                $.each(data, function (i, priority) {
                    $('#tmw-task-priority').append($('<option>', {
                        value: priority.id,
                        text: priority.name
                    }));
                });

                if (id != null) $('#tmw-task-priority').val(id);

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

    function fillSelectStatus(id) {
        $('#tmw-task-status').empty();
        $.ajax({
            url: 'api/status',
            type: 'GET',
            contentType: 'application/json',
            headers: createAuthToken(),
            success: function (data, textStatus, jqXHR) {
                var token = jqXHR.getResponseHeader('Authentication');
                window.sessionStorage.setItem("token", token);
                $.each(data, function (i, status) {
                    $('#tmw-task-status').append($('<option>', {
                        value: status.id,
                        text: status.name
                    }));
                });

                if (id != null) $('#tmw-task-status').val(id);

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

    function fillSelectTags(id) {
        tags = [];
        $('#tmw-tag-multi-select').empty();
        $.ajax({
            url: 'api/tags',
            type: 'GET',
            contentType: 'application/json',
            headers: createAuthToken(),
            success: function (data, textStatus, jqXHR) {
                tags = data;
                var token = jqXHR.getResponseHeader('Authentication');
                window.sessionStorage.setItem("token", token);
                $.each(data, function (i, tag) {
                    $('#tmw-tag-multi-select').append($('<option>', {
                        value: tag.id,
                        text: tag.name
                    }));
                });

                $('#tmw-tag-multi-select').multiselect({
                    buttonWidth: '570px',
                    inheritClass: true,
                    enableCaseInsensitiveFiltering: true,
                    dropRight: true,
                    numberDisplayed: 10,
                });

                $('#tmw-tag-multi-select').multiselect('deselectAll', false);
                $('#tmw-tag-multi-select').multiselect('updateButtonText');

                if (id != null) getTagsByTask(id);
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

    function getTagsByTask(id) {
        $('#tmw-tag-multi-select').multiselect('deselectAll', false);

        $.ajax({
            url: 'api/tasks/' + id + '/tags',
            type: 'GET',
            contentType: 'application/json',
            headers: createAuthToken(),
            success: function (data, textStatus, jqXHR) {
                var token = jqXHR.getResponseHeader('Authentication');
                window.sessionStorage.setItem("token", token);

                $('#tmw-tag-multi-select').multiselect('select', getIdOfTask(data));

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

    function getIdOfTask(data) {
        var arrTagsId = [];

        data.map(function (tag) {
            arrTagsId.push(tag.id);
        });

        return arrTagsId;
    }

    function createAuthToken() {
        var token = window.sessionStorage.getItem("token");
        if (token) {
            return {
                "Authentication": token
            }
        } else {
            return {}
        }
    }

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

    $("#reg-button").click(function () {
        $("#login").hide();
        $("#registration").show();
    });

    function doRegister(regData) {
        $.ajax({
            url: "/register",
            type: "POST",
            data: JSON.stringify(regData),
            contentType: "application/json; charset=utf-8",
            success: function (data, textStatus, jqXHR, response) {
                if (response === 201) {
                    alert(response);
                }
                if (jqXHR.status == 201) {
                    location.reload();
                }
            },
            error: function (jqXHR) {
                if (jqXHR.status == 409) {
                    $("#existing-email").show();
                }
            }
        });
    }

    function doLogin(loginData) {
        $.ajax({
            url: "/login",
            type: "POST",
            data: JSON.stringify(loginData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                var userId = data["id"];
                var userName = data['username'];
                window.sessionStorage.setItem("id", userId);
                window.sessionStorage.setItem("name", userName);
                $("#user-login").text("Hello, " + userName);
                setToken(jqXHR);
                $("#logout").show();
                $("#login").hide();
                $("#main").show();
                $("#leftPanel").load("static/load-pages/taskFilter.html");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    window.sessionStorage.removeItem("token");
                    $("#password-label").show();
                } else {
                    throw new Error("an unexpected error occured: " + errorThrown);
                }
            }
        });
    }

    $("input").on("click", function () {
        $("#password-label").hide();
    });
    $("#logout").click(function () {

        $.ajax({
            url: "api/logout",
            type: "POST",
            success: function () {
                resetToken();
                location.reload();
                $("#logout").hide()
            }
        });
    });

    $("#registration-form").validate({
        submitHandler: function (form) {
            $(form).on('submit', function (event) {
                event.preventDefault();
                var $form = $(this);
                var formData = {
                    name: $form.find('input[name="rname"]').val(),
                    pass: $form.find('input[name="rpassword"]').val(),
                    email: $form.find('input[name="remail"]').val()
                };
                doRegister(formData);
            });
        },
        rules: {
            rname: {
                required: true,
                minlength: 5
            },
            remail: {
                required: true,
                email: true
            },
            rpassword: {
                required: true,
                minlength: 5
            },
            rconfirm: {
                required: true,
                minlength: 5,
                equalTo: "#reg-password"
            }

        },
        messages: {
            rname: {
                required: "Please enter your name",
                minlength: "Your name must consist of at least 5 characters"
            },
            rpassword: {
                required: "Please provide a password",
                minlength: "Your password must be at least 5 characters long"
            },
            rconfirm: {
                required: "Please provide a password",
                minlength: "Your password must be at least 5 characters long",
                equalTo: "Please enter the same password as above"
            }
        }
    });

    $("#loginForm").validate(
        {
            submitHandler: function (form) {
                $(form).submit(function (event) {
                    event.preventDefault();
                    var $form = $(this);
                    var formData = {
                        userEmail: $form.find('input[name="email"]').val(),
                        password: $form.find('input[name="password"]').val()
                    };
                    doLogin(formData);
                });
            }
        }
    );

    function clearErrorTask() {

        $('#tmw-task-name').css({
            "border-color": "",
            "border-width": "",
            "border-style": ""
        });
        $('#tmw-task-endDate').css({
            "border-color": "",
            "border-width": "",
            "border-style": ""
        });
        $('#tmw-task-startDate').css({
            "border-color": "",
            "border-width": "",
            "border-style": ""
        });
        $('#tmw-task-estimateTime').css({
            "border-color": "",
            "border-width": "",
            "border-style": ""
        });

        $('#tmw-task-name-error').empty();
        $('#tmw-task-startDate-error').empty();
        $('#tmw-task-endDate-error').empty();
        $('#tmw-task-estimateTime-error').empty();
    }

    function showErrorsOfForm(data) {
        for (var i = 0; i < data.fieldErrors.length; i++) {
            if (data.fieldErrors[i].field == 'name') {
                $('#tmw-task-name').css({
                    "border-color": "#FF0000",
                    "border-width": "1px",
                    "border-style": "solid"
                });
                $('#tmw-task-name-error').text(data.fieldErrors[i].message).css('color', 'red');
            }
            if (data.fieldErrors[i].field == 'startDate') {
                $('#tmw-task-startDate').css({
                    "border-color": "#FF0000",
                    "border-width": "1px",
                    "border-style": "solid"
                });
                $('#tmw-task-startDate-error').text(data.fieldErrors[i].message).css('color', 'red');
            }
            if (data.fieldErrors[i].field == 'endDate') {
                $('#tmw-task-endDate').css({
                    "border-color": "#FF0000",
                    "border-width": "1px",
                    "border-style": "solid"
                });
                $('#tmw-task-endDate-error').text(data.fieldErrors[i].message).css('color', 'red');
            }
            if (data.fieldErrors[i].field == 'estimateTime') {
                $('#tmw-task-estimateTime').css({
                    "border-color": "#FF0000",
                    "border-width": "1px",
                    "border-style": "solid"
                });
                $('#tmw-task-estimateTime-error').text(data.fieldErrors[i].message).css('color', 'red');
            }
        }
    }

    var startWorkDay = '09:00';
    var endWorkDay = '17:00';
    var timeSlotDuration = '00:30:00';

    $('#tmw-graphic').click(function () {
        $('#tmw-main-calendar').removeClass('hidden');

        $('#tmw-graphic').addClass('hidden');
        $('#tmw-graphic-exit').removeClass('hidden');

        taskCalendar();
    });

    $('#tmw-graphic-exit').click(function () {
        $('#tmw-main-calendar').addClass('hidden');

        $('#tmw-graphic').removeClass('hidden');
        $('#tmw-graphic-exit').addClass('hidden');

        $('#tmw-task-calendar').fullCalendar('destroy');
    });

    var plannedTasks = [];
    var taskCalendar = function () {
        $.ajax({
            url: '/api/users/all',
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {
                var resources = [];
                for (var i = 0; i < data.length; i++) {
                    resources.push({
                        id: data[i].id,
                        title: data[i].name
                    });
                }

                plannedTasks = [];
                $.ajax({
                    url: 'api/tasks/planning',
                    type: 'GET',
                    contentType: 'application/json',
                    headers: createAuthToken(),
                    success: function (data, textStatus, jqXHR) {
                        setToken(jqXHR);

                        for (var i = 0; i < data.length; i++) {
                            if (data[i].planningDate == null) continue;

                            var hours = parseInt(data[i].estimateTime.split(':')[0]),
                                minutes = parseInt(data[i].estimateTime.split(':')[1]);

                            plannedTasks.push({
                                id: data[i].id,
                                resourceId: data[i].assignTo,

                                title: data[i].name,

                                start: moment(data[i].planningDate),
                                end: moment(data[i].planningDate).addWorkingTime(hours, 'hours', minutes, 'minutes', 0, 'seconds'),

                                color: setColorTask(data[i]),
                                est: data[i].estimateTime
                            });
                        }

                        $('#tmw-task-calendar').fullCalendar({
                            schedulerLicenseKey: 'CC-Attribution-NonCommercial-NoDerivatives',
                            editable: true,
                            droppable: true,
                            height: 'auto',
                            timezone: 'local',
                            minTime: startWorkDay,
                            maxTime: endWorkDay,
                            slotDuration: timeSlotDuration,
                            defaultTimedEventDuration: '00:30:00',
                            slotWidth: '25',
                            forceEventDuration: true,
                            weekends: false,
                            header: {
                                left: 'prev,next',
                                center: 'title',
                                right: '_timelineDay,_timelineWeek,timelineMonth'
                            },
                            views: {
                                _timelineDay: {
                                    type: 'timeline',
                                    duration: {days: 1},
                                    buttonText: 'Day',
                                    slotLabelFormat: ['H:mm']
                                },
                                _timelineWeek: {
                                    type: 'timeline',
                                    duration: {weeks: 1},
                                    buttonText: 'Week',
                                    slotLabelFormat: ['dddd, D MMMM', 'H:mm']
                                }
                            },
                            defaultView: '_timelineDay',
                            resourceLabelText: 'Users',
                            resourceAreaWidth: '17%',
                            resources: resources,
                            events: plannedTasks,


                            drop: function (date, jsEvent, ui, resourceId) {
                                // $(this).remove();

                                var table = $('#tmw-task-table').DataTable();
                                table.row($(this)).remove().draw();

                                if ($('#tmw-task-table tbody tr').length === 0) {
                                    $('#tmw-task-table').empty();
                                }
                            },

                            eventReceive: handleCalendarTaskEdit,
                            eventDrop: handleCalendarTaskEdit,
                            eventResize: resizeTaskOnCalendar,

                            eventClick: function (event) {
                                showFull(event.id);
                            }
                        });

                        makeTableRowsDraggable();
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        if (jqXHR.status === 401) {
                            resetToken();
                        } else {
                            throw new Error("an unexpected error occured: " + errorThrown);
                        }
                    }
                });
            },
            cache: false
        });
    };

//============================================================================================================

    var resizeTaskOnCalendar = function (event) {
        var msDiff = event.end.workingDiff(event.start);

        var hours = Math.floor(msDiff / 1000 / 60 / 60);
        var minutes = Math.floor(msDiff / 1000 / 60) - (hours * 60);

        var hours = ('0' + event.end.workingDiff(event.start, 'hours')).slice(-2),
            minutes = ('0' + event.end.workingDiff(event.start, 'minutes') % 60).slice(-2);

        event.est = hours + ':' + minutes + ':00';

        handleCalendarTaskEdit(event);
    };

    var handleCalendarTaskEdit = function (event) {
        var hours = parseInt(event.est.split(':')[0]),
            minutes = parseInt(event.est.split(':')[1]);

        if (!event.start.isWorkingTime()) {
            event.start = moment(event.end).subtractWorkingTime(hours, 'hours', minutes, 'minutes', 0, 'seconds');
        }

        if (!event.end.isWorkingTime()) {
            event.end = moment(event.start).addWorkingTime(hours, 'hours', minutes, 'minutes', 0, 'seconds');
        }

        $.ajax({
            url: '/api/tasks/planning/' + event.id,
            type: 'GET',
            contentType: 'application/json',

            success: function (data) {


                data.assignTo = event.resourceId;

                var planningDate = event.start.valueOf();
                data.planningDate = planningDate;

                var hours = ('0' + event.end.workingDiff(event.start, 'hours')).slice(-2),
                    minutes = ('0' + event.end.workingDiff(event.start, 'minutes') % 60).slice(-2);

                var estimateTime = hours + ':' + minutes + ':00';

                data.estimateTime = estimateTime;

                $.ajax({
                    url: '/api/tasks/planning',
                    data: JSON.stringify(data),
                    type: 'PUT',
                    contentType: 'application/json',

                    success: function () {
                        $.ajax({
                            url: '/api/tasks/planning/' + event.id,
                            type: 'GET',
                            contentType: 'application/json',

                            success: function (innerData) {
                                event.color = setColorTask(innerData);
                                $('#tmw-task-calendar').fullCalendar('updateEvent', event);
                            }
                        });
                    }
                });
            }
        });
    };

    var makeTableRowsDraggable = function () {
        $('#tmw-main-table tbody tr').each(function () {
            var table = $('#tmw-task-table').DataTable();
            var tid = table.row(this).data()[0];
            var estimateTime = table.row(this).data()[3];

            $(this).data('event', {
                id: tid,
                title: $.trim($(this).find('td').first().text()),
                stick: true,
                est: estimateTime
            });

            $(this).draggable({
                zIndex: 999,
                revert: true,
                revertDuration: 0,
                appendTo: $(document.body),

                helper: function (event) {
                    $(event.currentTarget).addClass('active');

                    var table = $('#tmw-task-table').DataTable();
                    estimateTime = table.row(this).data()[3] || '00:30:00';

                    $('#tmw-task-calendar').fullCalendar('getView').calendar.defaultTimedEventDuration = moment.duration(estimateTime);

                    return $(event.currentTarget).clone();
                },

                stop: function () {
                    $('#tmw-main-table tbody tr').removeClass('active');
                }
            });
        });
    };

    var setColorTask = function (statusId) {
        if (statusId == 1) return '#ff3c38';
        if (statusId == 2) return '#34ff16';
        if (statusId == 3) return '#ff53d4';
        return '#0000ff';
    };

});

