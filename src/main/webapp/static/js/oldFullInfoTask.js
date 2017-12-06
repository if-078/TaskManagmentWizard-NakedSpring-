var tags = [];
var comments = [];
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

            var date = ((new Date(taskDTO.planningDate + 7200000)).toISOString()).split("T");
            $('#tmw-task-info').text(taskDTO.name);
            $('#tmw-task-projectId').text(taskDTO.projectId);
            $('#tmw-task-parentId').text(taskDTO.parentId);
            $('#tmw-task-draftPlanning').val(taskDTO.draftPlanning);
            if (taskDTO.planningDate != null) $('#tmw-task-planningDate').val(date[0] + " " + date[1].slice(0,-5));
            $('#tmw-task-estimateTime').val(taskDTO.estimateTime);
            $('#tmw-task-spentTime').val(taskDTO.spentTime);
            $('#tmw-task-leftTime').val(taskDTO.leftTime);
            taskDTO.author != null ? fillSelectUserAuthor(taskDTO.author.id) : $('#tmw-task-author').val('');
            taskDTO.assignTo != null ? fillSelectUserAssign(taskDTO.assignTo.id) : $('#tmw-task-assignTo').val('');
            taskDTO.priority != null ? fillSelectPriority(taskDTO.priority.id) : $('#tmw-task-priority').val('');
            taskDTO.status != null ? fillSelectStatus(taskDTO.status.id) : $('#tmw-task-status').val('');
            fillSelectTags(taskDTO.id);
            fillSelectComments(taskDTO.id);


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
$('#tmw-task-btn-save, #tmw-create-task').on('click', function () {
    console.log(taskDTO);
    createOrUpdateTask(taskDTO);
});

$('#tmw-task-btn-delete').on('click', function () {
    deleteTask(taskDTO.id);
});

$("#tmw-show-comment").on('click', function () {
    showComment();
});

$("#tmw-save-comment").on('click', function () {
    addComment();
});

var showComment = function () {
    $('#tmw-task-comment').toggle();
    $('#tmw-task-comments').toggle();
    $('#tmw-save-comment').toggle();
};


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
    deleteTask(taskID);
});

function createOrUpdateTask(taskDTO) {
    var task = {};
    var est = ($('#tmw-task-estimateTime').val()).split(":");
    var estimate = Number(est[0]) * 60 + Number(est[1]);
    var sp = ($('#tmw-task-spentTime').val()).split(":");
    var spent = Number(sp[0]) * 60 + Number(sp[1]);
    var lf = ($('#tmw-task-leftTime').val()).split(":");
    var left = Number(lf[0]) * 60 + Number(lf[1]);
    var date = $('#tmw-task-planningDate').val().replace(' ', 'T') + ".000Z";

    if ($.isEmptyObject(taskDTO)) {

        task =
            {
                "name": "New Task",
                "createdDate": new Date().getTime(),
                "planningDate": new Date().getTime() - 7200000,
                "draftPlanning": new Date().getTime(),
                "estimateTime": 480,
                "spentTime": 0,
                "leftTime": 480,
                "author": userName,
                "assignTo": userName,
                "statusId": 1,
                "priorityId": 3,
                "parentId": 0,
                "projectId":0,
                "tags": null,
                "comments": null
            }

        createTask(task);
    } else {
        task =
            {
                "id": taskDTO.id,
                "name": $('#tmw-task-info').text(),
                "createdDate": taskDTO.createdDate,
                "planningDate": new Date(date).getTime() - 7200000,
                "draftPlanning": $('#tmw-task-draftPlanning').val(),
                "estimateTime": estimate,
                "spentTime": spent,
                "leftTime": left,
                "author": $('#tmw-task-author').val(),
                "assignTo": $('#tmw-task-assignTo').find(":selected").val(),
                "statusId": $('#tmw-task-status').find(":selected").val(),
                "priorityId": $('#tmw-task-priority').find(":selected").val(),
                "parentId": taskDTO.parentId,
                "projectId": taskDTO.projectId,
                "tags": getSelectedTags(),
                "comments": getSelectedComments()
            }
        console.log(task);
        show();
        updateTask(task);
    }
}

function getSelectedTags() {
    var selectedIdOfTags = $('#tmw-tag-multi-select').val();
    var selectedTags = [];

    for (var i = 0; i < tags.length; i++) {
        for (var j = 0; j < selectedIdOfTags.length; j++) {
            if (tags[i].id == selectedIdOfTags[j]) {
                selectedTags.push(tags[i]);
                break;
            }
        }
    }
    return selectedTags;

}

function getSelectedComments() {
    return comments;
}

function createTask(task) {
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

function updateTask(task) {
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

function deleteTask(taskId) {
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
    $('#tmw-task-planningDate').val('');
    $('#tmw-task-draftPlanning').val('');
    $('#tmw-task-estimateTime').val('');
    $('#tmw-task-spentTime').val('');
    $('#tmw-task-leftTime').val('');
    $('#tmw-task-author').empty();
    $('#tmw-task-assignTo').empty();
    $('#tmw-task-status').empty();
    $('#tmw-task-priority').empty();
    $('#tmw-task-tag').empty();
    $('#tmw-task-comments').empty();
}

function fillSelectUserAssign(id) {
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

function fillSelectUserAuthor(id) {
    $('#tmw-task-author').empty();
    $.ajax({
        url: 'api/users/all',
        type: 'GET',
        contentType: 'application/json',
        headers: createAuthToken(),
        success: function (data, textStatus, jqXHR) {
            var token = jqXHR.getResponseHeader('Authentication');
            window.sessionStorage.setItem("token", token);
            $.each(data, function (i, user) {
                $('#tmw-task-author').append($('<option>', {
                    value: user.id,
                    text: user.name
                }));
            });

            if (id != null) $('#tmw-task-author').val(id);

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
        url: 'api/tags?projectId=' + selectedProjectId,
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
                buttonWidth: '100%',
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

            $('#tmw-tag-multi-select').multiselect('select', getIdTagsOfTask(data));

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

function getIdTagsOfTask(data) {
    var arrTagsId = [];

    data.map(function (tag) {
        arrTagsId.push(tag.id);
    });

    return arrTagsId;
}

function fillSelectComments(id) {
    comments = [];
    $('#tmw-task-comments').empty();
    $.ajax({
        url: 'api/comment/task/' + id,
        type: 'GET',
        contentType: 'application/json',
        headers: createAuthToken(),
        success: function (data, textStatus, jqXHR) {
            comments = data;
            var token = jqXHR.getResponseHeader('Authentication');
            window.sessionStorage.setItem("token", token);
            getComments(data);
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

var getComments = function (data) {
    $.each(data, function (i, comment) {
        var date = (new Date(comment.createdDate + 7200000).toISOString()).split("T");
        $('#tmw-task-comments').append('<div><span style="font-weight: bold; margin-bottom: 0">' +
            'Author: ' + comment.user +  ' Created: ' + date[0] + '  ' + date[1].slice(0, -5) +
            '</span><p id = "comment' + comment.id + '" style="font-style: italic; border: thick; ' +
            'test-decoration: none">' + comment.commentText + '</p></div>').css('text-align', 'left');
    });
}

var addComment = function () {
    var comment = [];
    comment.push({
        "commentText": $('#tmw-task-comment').val(),
        "createdDate": new Date().getTime(),
        "taskId": taskDTO.id,
        "userId": userId,
        "user": userName
    });
    $('#tmw-task-comment').val('');
    comments.push(comment[0]);
    getComments(comment);
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
