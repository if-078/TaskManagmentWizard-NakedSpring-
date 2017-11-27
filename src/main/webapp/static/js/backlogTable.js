
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

    console.log('userId = ',userId);
    console.log('userName = ',userName);
    $.ajax({
        url: 'api/tasks/filter' + generatedRequestParameters(),
        type: 'GET',
        contentType: 'application/json',
        headers: createAuthToken(),
        success: function (data, textStatus, jqXHR) {
            setToken(jqXHR);

            $.ajax({
                url: 'api/tasks/tree/' + state.parentid + '?userId=' + userId,
                type: 'GET',
                contentType: 'application/json',
                headers: createAuthToken(),
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


                        if ((!hasChildren)&(selectedTaskId !=0)) {
                            rows.push(Object.values(data[i]));
                        }
                    }

                    var setHour;
                    var setMinute;
                    var slotMin = timeSlotHour * 60 + timeSlotMin;
                    for (var i = 0; i < rows.length; i++){
                        rows[i][3] = Math.ceil(rows[i][3]/slotMin) * slotMin;
                        setHour = '' + parseInt(rows[i][3]/60);
                        if (setHour.length<2) setHour = '0' + setHour;
                        setMinute = '' + rows[i][3]%60;
                        if (setMinute.length<2) setMinute = '0' + setMinute;
                        rows[i][7] = '' + setHour + ':' + setMinute + ':00';
                        rows[i][3] = '' + setHour + ' h ' + setMinute + ' min';
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

