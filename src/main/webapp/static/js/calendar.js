var isSelectedNewProject = true;
var isSelectedCalendar = true;
var taskCalendarInit = false;

var timeSlotHour = 0;
var timeSlotMin = 30;

var startWorkDay = '09:00';
var endWorkDay = '17:00';
var timeSlotDuration = '0' + timeSlotHour + ':' + timeSlotMin + ':00';

var plannedTasks = [];

var taskCalendar = function () {
    $.ajax({
        url: '/api/users/team/' + selectedTaskId + '?userId=' + userId,
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
                url: 'api/tasks/filter' + generatedRequestParameters() + '&planing=true' + '&userId=' + userId,
                type: 'GET',
                contentType: 'application/json',
                headers: createAuthToken(),
                success: function (data, textStatus, jqXHR) {
                    setToken(jqXHR);


                    for (var i = 0; i < data.length; i++) {
                        if (data[i].planningDate == null) continue;

                        var readEstimateTime = parseInt(data[i].estimateTime);

                        var hours = parseInt(readEstimateTime / 60);
                        if (('' + hours).length < 2) hours = '0' + hours;
                        var minutes = parseInt(readEstimateTime % 60);
                        if (('' + minutes).length < 2) minutes = '0' + minutes;

                        data[i].estimateTime = '' + hours + ':' + minutes + ':00';

                        plannedTasks.push({
                            id: data[i].id,
                            resourceId: data[i].assignTo,

                            title: data[i].name,

                            start: moment(data[i].planningDate),
                            end: moment(data[i].planningDate).addWorkingTime(parseInt(hours), 'hours', parseInt(minutes), 'minutes', 0, 'seconds'),

                            color: setColorTask(data[i]),
                            est: data[i].estimateTime,

                            createdDate: data[i].createdDate,
                            planningDate: data[i].planningDate,
                            startDate: data[i].startDate,
                            endDate: data[i].endDate,
                            estimateTime: data[i].estimateTime,
                            spentTime: convertMinuteToHoursAndMinute(data[i].spentTime),
                            leftTime: convertMinuteToHoursAndMinute(data[i].leftTime),
                            assignTo: data[i].assignTo,
                            statusId: data[i].statusId,
                            priorityId: data[i].priorityId,
                            parentId: data[i].parentId,
                            authorId: data[i].authorId,
                            projectId: data[i].projectId,

                            author: data[i].author,
                            assign: data[i].assign,
                            status: data[i].status,
                            priority: data[i].priority,

                            editable: ((userId == data[i].assignTo) || (userId == data[i].authorId) ||
                            (firstManagerId == userId) || (secondaryManagerId == userId))
                        });
                    }

                    if (!isSelectedNewProject) {
                        $('#tmw-task-calendar').fullCalendar('removeEvents');
                        $('#tmw-task-calendar').fullCalendar('renderEvents', plannedTasks, true);
                    } else {
                        $('#tmw-task-calendar').fullCalendar({
                            schedulerLicenseKey: 'CC-Attribution-NonCommercial-NoDerivatives',
                            editable: true,
                            droppable: true,
                            height: 'parent', // 'auto',
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
                            defaultView: '_timelineWeek',
                            resourceLabelText: 'Users',
                            resourceAreaWidth: '15%',
                            resources: resources,
                            events: plannedTasks,


                            drop: function (date, jsEvent, ui, resourceId) {
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
                                $('#tmw-worklog').modal('show');
                            },

                            eventDragStop: function (event, e) {
                                var rect = document.querySelector('#tmw-task-table').getBoundingClientRect();

                                var isEventOverTable = e.clientX > rect.left && e.clientX < rect.right &&
                                    e.clientY > rect.top && e.clientY < rect.bottom;

                                if (isEventOverTable) {
                                    $('#tmw-task-calendar').fullCalendar('removeEvents', event._id);

                                    $('#tmw-task-table').DataTable().rows.add([[
                                        event.id,               // ID
                                        event.title,            // Name
                                        event.createdDate,      // Create Date
                                        event.planningDate,     // Planning Date
                                        event.startDate,        // Draft Date
                                        event.endDate,          // End Date
                                        event.estimateTime,     // Est. Time
                                        event.spentTime,        // Spent Time
                                        event.leftTime,         // Left Time
                                        event.assignTo,         // Assign To
                                        event.statusId,         // Status ID
                                        event.priorityId,       // Priority ID
                                        event.parentId,         // Parent ID
                                        event.authorId,         // Author ID
                                        event.projectId,        // Project ID
                                        event.author,           // Author
                                        event.assign,           // Assignee
                                        event.status,           // Status
                                        event.priority,         // Priority
                                        event.estimateTime      // Alt. Est. Time
                                    ]]).draw();

                                    makeTableRowsDraggable();
                                    deleteTaskPlanning(event.id);
                                }

                            },

                            dragRevertDuration: 0
                        });

                        taskCalendarInit = true;
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

    updateCalendarTask(event);
};

var updateCalendarTask = function (event) {
    $.ajax({
        url: '/api/tasks/' + event.id,
        type: 'GET',
        contentType: 'application/json',

        success: function (data) {
            data.assignTo = event.resourceId;

            var planningDate = event.start.valueOf();
            data.planningDate = planningDate;

            var hours = ('0' + event.end.workingDiff(event.start, 'hours')).slice(-2),
                minutes = ('0' + event.end.workingDiff(event.start, 'minutes') % 60).slice(-2);
            data.estimateTime = parseInt(hours) * 60 + parseInt(minutes);

            console.log(data);

            $.ajax({
                url: '/api/tasks/setPlanning/' + userId,
                data: JSON.stringify(data),
                type: 'PUT',
                contentType: 'application/json',

                success: function () {
                    $.ajax({
                        url: '/api/tasks/' + event.id,
                        type: 'GET',
                        contentType: 'application/json',

                        success: function (innerData) {
                            event.color = setColorTask(innerData);
                            $('#tmw-task-calendar').fullCalendar('updateEvent', event);
                            //taskCalendar();
                            showDataOnCalendarAndTable();
                        }
                    });
                }
            });
        }
    });
};


var deleteTaskPlanning = function (id) {
    $.ajax({
        url: '/api/tasks/deletePlanning/' + id + '/' + userId,
        type: 'GET',
        contentType: 'application/json',
        success: function () {
        }
    });
};


var updateCalendar = function () {
    var $calendar = $('#tmw-task-calendar');
    $calendar.fullCalendar('clientEvents').forEach(function (event) {
        $calendar.fullCalendar('removeEvents');
    });
};

var setColorTask = function (data) {
    if ((data.assignTo == userId) || (data.authorId == userId) ||
        (firstManagerId == userId) || (secondaryManagerId == userId)) {
        if (data.statusId == 3) return '#0000ff';
        if (data.priorityId == 1) return '#ff0000';
        if (data.priorityId == 2) return '#00f000';
        if (data.priorityId == 3) return '#f000f0';
    } else {
        if (data.statusId == 3) return '#a0a0ff';
        if (data.priorityId == 1) return '#ffa0a0';
        if (data.priorityId == 2) return '#a0ffa0';
        if (data.priorityId == 3) return '#ffa0eb';
    }
    return '#c2bdc1'
};


$('#tmw-btn-table-view').on("click", function () {

    $('#tmw-btn-table-view').addClass('fc-state-active');
    $('#tmw-btn-calendar-view').removeClass('fc-state-active');
    $('#tmw-main-calendar').addClass('hidden');
    $('#tmw-main-planning-table').removeClass('hidden');

    isSelectedCalendar = false;

    taskCalendarInit = false;
    $('#tmw-task-calendar').fullCalendar('destroy');

    taskPlanningTable();

    taskTable();

});

$('#tmw-btn-calendar-view').on("click", function () {

    $('#tmw-btn-calendar-view').addClass('fc-state-active');
    $('#tmw-btn-table-view').removeClass('fc-state-active');
    $('#tmw-main-planning-table').addClass('hidden');
    $('#tmw-main-calendar').removeClass('hidden');

    isSelectedCalendar = true;
    isSelectedNewProject = true;

    taskCalendar();
    taskTable();

});
