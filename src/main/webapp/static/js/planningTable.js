var taskPlanningTableInit = false;

var taskPlanningTable = function () {

    $.ajax({
        url: 'api/tasks/filter' + generatedRequestParameters() + '&planing=true' + '&userId=' + userId,
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

                //convert planning_date from milliseconds to dateTime
                rows[i][3] = moment(rows[i][3]).format('YYYY-MM-DD hh:mm');

                //convert estimate time from minutes to HH:MM
                rows[i][6] = Math.ceil(rows[i][6] / slotMin) * slotMin;
                setHour = '' + parseInt(rows[i][6] / 60);
                if (setHour.length < 2) setHour = '0' + setHour;
                setMinute = '' + rows[i][6] % 60;
                if (setMinute.length < 2) setMinute = '0' + setMinute;
                rows[i][6] = '' + setHour + ':' + setMinute + ':00';

                rows[i][7] = convertMinuteToHoursAndMinute(rows[i][7]);
                rows[i][8] = convertMinuteToHoursAndMinute(rows[i][8]);
            }

            if (taskPlanningTableInit) {
                $('#tmw-task-planning-table').DataTable().destroy();
                taskPlanningTableInit = false;
            }
            $('#tmw-main-planning-table').css('visibility', 'hidden');
            showPlanningTable();


            $('#tmw-task-planning-table').DataTable({
                data: rows,

                columns: [
                    {title: "ID", visible: false},
                    {title: "Name", visible: true},
                    {title: "Create Date", visible: false},
                    {title: "Planning Date", visible: true},
                    {title: "Draft Planning", visible: false},
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
                scrollY: '35.5vh',
                scrollCollapse: true
            });

            $('#tmw-main-planning-table').css('visibility', 'visible');
            taskPlanningTableInit = true;

            if (rows.length > 0) {
                makePlanningTableRowsDraggable();
            }

            showPlanningTable();

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


var makePlanningTableRowsDraggable = function () {
    $('#tmw-main-planning-table tbody tr').each(function () {

        var table = $('#tmw-task-planning-table').DataTable();

        if ((table.row(this).data()[9] == userId) || (table.row(this).data()[13] == userId) ||
            (firstManagerId == userId) || (secondaryManagerId == userId)) {

            $(this).draggable({
                zIndex: 999,
                revert: true,
                revertDuration: 0,
                appendTo: $(document.body),

                helper: function (event) {
                    $(event.currentTarget).addClass('active');

                    dataFromPlanningTable.id = table.row(this).data()[0];               // ID
                    dataFromPlanningTable.title = table.row(this).data()[1];            // Name
                    dataFromPlanningTable.createdDate = table.row(this).data()[2];      // Create Date
                    dataFromPlanningTable.planningDate = table.row(this).data()[3];     // Planning Date
                    dataFromPlanningTable.startDate = table.row(this).data()[4];        // Draft Date
                    dataFromPlanningTable.endDate = table.row(this).data()[5];          // End Date
                    dataFromPlanningTable.estimateTime = table.row(this).data()[6];     // Est. Time
                    dataFromPlanningTable.spentTime = table.row(this).data()[7];        // Spent Time
                    dataFromPlanningTable.leftTime = table.row(this).data()[8];         // Left Time
                    dataFromPlanningTable.assignTo = table.row(this).data()[9];         // Assign To
                    dataFromPlanningTable.statusId = table.row(this).data()[10];        // Status ID
                    dataFromPlanningTable.priorityId = table.row(this).data()[11];      // Priority ID
                    dataFromPlanningTable.parentId = table.row(this).data()[12];        // Parent ID
                    dataFromPlanningTable.authorId = table.row(this).data()[13];        // Author ID
                    dataFromPlanningTable.projectId = table.row(this).data()[14];       // Project ID
                    dataFromPlanningTable.assign = table.row(this).data()[15];          // Assignee
                    dataFromPlanningTable.status = table.row(this).data()[16];          // Status
                    dataFromPlanningTable.priority = table.row(this).data()[17];        // Priority

                    return $(event.currentTarget).clone();
                },

                stop: function (event) {

                    var rect = document.querySelector('#tmw-task-table').getBoundingClientRect();

                    var isEventOverTable = event.clientX > rect.left && event.clientX < rect.right &&
                        event.clientY > rect.top && event.clientY < rect.bottom + 50;

                    if (isEventOverTable) {

                        $('#tmw-main-planning-table tbody tr').removeClass('active');
                        var table = $('#tmw-task-planning-table').DataTable();
                        table.row($(this)).remove().draw();

                        $('#tmw-task-calendar').fullCalendar('removeEvents', event._id);

                        $('#tmw-task-table').DataTable().rows.add([[
                            dataFromPlanningTable.id,               // ID
                            dataFromPlanningTable.title,            // Name
                            dataFromPlanningTable.createdDate,      // Create Date
                            dataFromPlanningTable.planningDate,     // Planning Date
                            dataFromPlanningTable.startDate,        // Draft Date
                            dataFromPlanningTable.endDate,          // End Date
                            dataFromPlanningTable.estimateTime,     // Est. Time
                            dataFromPlanningTable.spentTime,        // Spent Time
                            dataFromPlanningTable.leftTime,         // Left Time
                            dataFromPlanningTable.assignTo,         // Assign To
                            dataFromPlanningTable.statusId,         // Status ID
                            dataFromPlanningTable.priorityId,       // Priority ID
                            dataFromPlanningTable.parentId,         // Parent ID
                            dataFromPlanningTable.authorId,         // Author ID
                            dataFromPlanningTable.projectId,        // Project ID
                            dataFromPlanningTable.assign,           // Assignee
                            dataFromPlanningTable.status,           // Status
                            dataFromPlanningTable.priority          // Priority
                        ]]).draw();
                    }
                    deleteTaskPlanning(dataFromPlanningTable.id);

                }
            });

            $(this).addClass('tmw-draggable');
            $(this).removeClass('tmw-disabled');
        } else {
            $(this).addClass('tmw-disabled');
        }
    });
};

var dataFromPlanningTable = [];

var hideButtonSwitchCalendarTable = function () {
    $('#tmw-btn-switch').css('visibility', 'hidden');
};
hideButtonSwitchCalendarTable();


var showButtonSwitchCalendarTable = function () {
    $('#tmw-btn-switch').css('visibility', 'visible');
};

var hidePlanningTable = function () {
    $('#tmw-task-planning-table').css('visibility', 'hidden');
};


var showPlanningTable = function () {
    $('#tmw-task-planning-table').css('visibility', 'visible');
};


