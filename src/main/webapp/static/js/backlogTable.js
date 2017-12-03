
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


//--------------------------------------------------------------------------------------------------------------------



var taskTableInit = false;
var taskTable = function () {

    $('#tmw-task-table').css('visibility', 'visible');

    if (selectedTaskId==0) {
        if (taskTableInit) {
            $('#tmw-task-table').css('visibility', 'hidden');
            $('#tmw-task-table').DataTable().destroy();
            taskTableInit = false;
        }
        $('#tmw-task-calendar').fullCalendar('destroy');

        return;
    }


    $.ajax({
        url: 'api/tasks/filter' + generatedRequestParameters() + '&planing=false' + '&userId=' + userId ,
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


                    // make list rows for table only non-root tasks
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

                    // convert estimate time from int minute --> format time
                    var setHour;
                    var setMinute;
                    var slotMin = timeSlotHour * 60 + timeSlotMin;
                    for (var i = 0; i < rows.length; i++){
                        rows[i][6] = Math.ceil(rows[i][6]/slotMin) * slotMin;
                        setHour = '' + parseInt(rows[i][6]/60);
                        if (setHour.length<2) setHour = '0' + setHour;
                        setMinute = '' + rows[i][6]%60;
                        if (setMinute.length<2) setMinute = '0' + setMinute;
                        rows[i][6] = '' + setHour + ':' + setMinute + ':00';
                        rows[i][18] = '' + setHour + ':' + setMinute + ':00';
                    }

                    if (taskTableInit) {
                        $('#tmw-task-table').DataTable().destroy();
                        taskTableInit = false;
                    }
                    $('#tmw-task-calendar').fullCalendar('destroy');
                    taskCalendar();


                    $('#tmw-task-table').DataTable({
                        data: rows,

                        columns: [
                            {title: "ID", visible: false},
                            {title: "Name", visible: true},
                            {title: "Create Date", visible: false},
                            {title: "Planning Date", visible: false},
                            {title: "Draft Planning", visible: true},
                            {title: "End Date", visible: false},
                            {title: "Est. Time", visible: true},
                            {title: "Spent Time", visible: true},
                            {title: "Left Time", visible: true},
                            {title: "AssignTo", visible: false},
                            {title: "Statud ID", visible: false},
                            {title: "Priority ID", visible: false},
                            {title: "Parent ID", visible: false},
                            {title: "Author ID", visible: false},
                            {title: "Project ID", visible: false},
                            {title: "Assignee", visible: true},
                            {title: "Status", visible: true},
                            {title: "Priority", visible: true}
                        ],

                        paging: false,
                        info: false,
                        searching: false
                    });

                    taskTableInit = true;

                    if(rows.length>0) {
                        makeTableRowsDraggable();
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

