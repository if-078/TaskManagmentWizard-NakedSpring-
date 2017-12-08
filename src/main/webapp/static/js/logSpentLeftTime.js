$('#tmw-btn-task-worklog').on('click', function () {


    console.log('currentEditTaskId = ', currentEditTaskId);


    $.ajax({
        url: '/api/tasks/' + currentEditTaskId,
        type: 'GET',
        contentType: 'application/json',

        success: function (task) {

            console.log('task leftTime = ', task.leftTime);

            $.ajax({
                url: 'api/tasks/tree/' + currentEditTaskId + '?userId=' + userId,
                type: 'GET',
                contentType: 'application/json',

                success: function (data) {

                    document.getElementById("tmw-total-spent-time").value = convertMinuteToHoursAndMinuteForWorkLog(task.spentTime);
                    document.getElementById("tmw-set-spent-time").value = '00:00';
                    document.getElementById("tmw-set-left-time").value = convertMinuteToHoursAndMinuteForWorkLog(task.leftTime);

                    if (data.length == 0) {
                        console.log('setEnable');
                        //document.getElementById("tmw-set-spent-time").setAttribute("disabled", false);
                        //document.getElementById("tmw-set-left-time").setAttribute("disabled", false);
                        $('#tmw-btn-add-spent-time').removeClass('disabled');
                    } else {
                        console.log('setDisable');
                        //document.getElementById("tmw-set-spent-time").setAttribute("disabled", true);
                        //document.getElementById("tmw-set-left-time").setAttribute("disabled", true);
                        $('#tmw-btn-add-spent-time').addClass('disabled');
                    }

                    $('#tmw-worklog').modal('show');

                }
            });
        }
    });


});


var count = 0;
$('#tmw-btn-add-spent-time').on('click', function () {

    $('#tmw-worklog').modal('hide');

    //var spentTime = document.getElementById("tmw-set-spent-time").value;
    console.log('count = ', ++count);

    //var myObj = { "name":"John", "age":31, "city":"New York" };

    var spentTime = {

        "userId":userId,
        "taskId":currentEditTaskId,
        "date":"2017-10-01",
        "logTime":100
    };

    console.log(spentTime);

    $.ajax({
        url: 'api/tasks/logTask' + '?userId=' + userId + '&taskId=' + currentEditTaskId + '&logTime=' + 100,
        type: 'GET',
        contentType: 'application/json',

        success: function () {

        }
    });


});


var convertMinuteToHoursAndMinuteForWorkLog = function (timeMinutes) {

    var wHours = parseInt(timeMinutes / 60);
    if (('' + wHours).length < 2) wHours = '0' + wHours;
    var wMinutes = parseInt(timeMinutes % 60);
    if (('' + wMinutes).length < 2) wMinutes = '0' + wMinutes;

    return ('' + wHours + ':' + wMinutes);
}