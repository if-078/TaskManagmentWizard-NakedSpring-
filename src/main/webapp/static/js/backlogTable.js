var generatedRequestParameters = function () {
    var parameters = '?taskId=' + state.parentId + '&date=' + state.dateFrom + ',' + state.dateTo;
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

    $.ajax({
        url: 'api/tasks/filter' + generatedRequestParameters() + '&planing=false' + '&userId=' + userId,
        type: 'GET',
        contentType: 'application/json',
        headers: createAuthToken(),
        success: function (data, textStatus, jqXHR) {
            setToken(jqXHR);


            var rows = [];
            for (var i = 0; i < data.length; i++) {
                rows.push(Object.values(data[i]));
            }

            // convert estimate time from int minute --> format time
            var setHour;
            var setMinute;
            var slotMin = timeSlotHour * 60 + timeSlotMin;
            for (var i = 0; i < rows.length; i++) {
                rows[i][6] = Math.ceil(rows[i][6] / slotMin) * slotMin;
                setHour = '' + parseInt(rows[i][6] / 60);
                if (setHour.length < 2) setHour = '0' + setHour;
                setMinute = '' + rows[i][6] % 60;
                if (setMinute.length < 2) setMinute = '0' + setMinute;
                rows[i][6] = '' + setHour + ':' + setMinute + ':00';
                rows[i][18] = '' + setHour + ':' + setMinute + ':00';

                rows[i][7] = convertMinuteToHoursAndMinute(rows[i][7]);
                rows[i][8] = convertMinuteToHoursAndMinute(rows[i][8]);
            }


            if (taskTableInit) {
                $('#tmw-task-table').DataTable().destroy();
                taskTableInit = false;
            }
            $('#tmw-main-table').css('visibility', 'hidden');
            showDraftPlanningTable();


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
                searching: false,
                scrollY: '34.5vh',
                scrollCollapse: true
            });

            $('#tmw-main-table').css('visibility', 'visible');
            taskTableInit = true;

            if ((rows.length > 0) & (isSelectedCalendar)) {
                makeTableRowsDraggable();
            }


        }
    });
};


var makeTableRowsDraggable = function () {
    $('#tmw-main-table tbody tr').each(function () {

        var table = $('#tmw-task-table').DataTable();

        if ((table.row(this).data()[9] == userId) || (table.row(this).data()[13] == userId) ||
            (firstManagerId == userId) || (secondaryManagerId == userId)) {

            var tid = table.row(this).data()[0];
            var estimateTime = table.row(this).data()[18];

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
                    estimateTime = (!table.row(this).data()[18] || table.row(this).data()[18] === '00:00:00')
                        ? '08:00:00'
                        : table.row(this).data()[18];

                    $('#tmw-task-calendar').fullCalendar('getView').calendar.defaultTimedEventDuration = moment.duration(estimateTime);

                    return $(event.currentTarget).clone();
                },

                stop: function () {
                    $('#tmw-main-table tbody tr').removeClass('active');
                }
            });

            $(this).addClass('tmw-draggable');
            $(this).removeClass('tmw-disabled');
        } else {
            $(this).addClass('tmw-disabled');
        }
    });
};


var undoMakeTableRowsDraggable = function () {
    $('#tmw-main-table tbody tr').each(function () {
        $(this).draggable('disable');
        $(this).removeClass('tmw-draggable');
    });
};

var hideDraftPlanningTable = function () {
    $('#tmw-name-table').css('visibility', 'hidden');
    $('#tmw-task-table').css('visibility', 'hidden');
};
$('#tmw-name-table').css('visibility', 'hidden');


var showDraftPlanningTable = function () {
    $('#tmw-name-table').css('visibility', 'visible');
    $('#tmw-task-table').css('visibility', 'visible');
};


var convertMinuteToHoursAndMinute = function (timeMinutes) {

    var tempHours = parseInt(timeMinutes / 60);
    if (('' + tempHours).length < 2) tempHours = '0' + tempHours;
    var tempMinutes = parseInt(timeMinutes % 60);
    if (('' + tempMinutes).length < 2) tempMinutes = '0' + tempMinutes;

    return ('' + tempHours + ':' + tempMinutes + ':00');
}


