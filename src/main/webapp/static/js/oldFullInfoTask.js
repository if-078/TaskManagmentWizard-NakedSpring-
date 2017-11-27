var tags = [];
var taskID = null;
var taskDTO = {};


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
        headers: createAuthToken(),
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
        headers: createAuthToken(),
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
        headers: createAuthToken(),
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
