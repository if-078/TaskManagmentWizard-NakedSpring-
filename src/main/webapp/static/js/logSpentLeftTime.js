
$('#tmw-btn-task-worklog').on('click', function () {

    $('#tmw-worklog').modal('show');
    console.log('currentEditTaskId = ', currentEditTaskId);



});

/*
$('#t????????').on('click', function () {
    $('#tmw-log-time-task').modal('show');
});
*/

$('#tmw-btn-add-spent-time').on('click', function () {
    var spentTime = document.getElementById("tmw-set-spent-time").value;
    console.log('set spent time = ', spentTime);

    /*
    $.ajax({
        url: '/api/tasks/logtask/' + userId + '/' + selectedProjectId,
        data: JSON.stringify(spentTime),
        type: 'PUT',
        contentType: 'application/json'
    });
    */

    $('#tmw-worklog').modal('hide');

});