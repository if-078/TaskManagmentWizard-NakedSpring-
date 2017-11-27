var timeSlotHour = 0;
var timeSlotMin = 30;

var startWorkDay = '09:00';
var endWorkDay = '17:00';
var timeSlotDuration = '0' + timeSlotHour + ':' + timeSlotMin + ':00';

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
    taskTable();
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

                        var readEstimateTime = parseInt(data[i].estimateTime);

                        var hours = parseInt(readEstimateTime/60);
                        if ((''+hours).length<2) hours = '0' + hours;
                        var minutes = parseInt(readEstimateTime%60);
                        if ((''+minutes).length<2) minutes = '0' + minutes;

                        data[i].estimateTime = '' + hours + ':' + minutes + ':00';

                        plannedTasks.push({
                            id: data[i].id,
                            resourceId: data[i].assignTo,

                            title: data[i].name,

                            start: moment(data[i].planningDate),
                            end: moment(data[i].planningDate).addWorkingTime(parseInt(hours), 'hours', parseInt(minutes), 'minutes', 0, 'seconds'),

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
                            showFull(event);
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

    updateCalendarTask(event);

};


var updateCalendarTask = function (event) {
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

            data.estimateTime = parseInt(hours) * 60 + parseInt(minutes);

            //var estimateTime = hours + ':' + minutes + ':00';
            //data.estimateTime = estimateTime;

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
}



var makeTableRowsDraggable = function () {
    $('#tmw-main-table tbody tr').each(function () {
        var table = $('#tmw-task-table').DataTable();
        var tid = table.row(this).data()[0];
        var estimateTime = table.row(this).data()[7];

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
                estimateTime = (!table.row(this).data()[7] || table.row(this).data()[7] === '00:00:00')
                    ? '08:00:00'
                    : table.row(this).data()[7];

                $('#tmw-task-calendar').fullCalendar('getView').calendar.defaultTimedEventDuration = moment.duration(estimateTime);

                return $(event.currentTarget).clone();
            },

            stop: function () {
                $('#tmw-main-table tbody tr').removeClass('active');
            }
        });
    });
};



var setColorTask = function (data) {
    if (data.statusId == 3) return '#4750ff';
    if (data.priorityId == 1) return '#ff3c38';
    if (data.priorityId == 2) return '#34ff16';
    if (data.priorityId == 3) return '#ff53d4';

    return '#c2bdc1'
};

