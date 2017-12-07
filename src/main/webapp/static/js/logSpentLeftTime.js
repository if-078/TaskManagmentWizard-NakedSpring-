
/*
$('#t????????').on('click', function () {
    $('#tmw-log-time-task').modal('show');
});
*/

$('#tmw-btn-spent-time').on('click', function () {
    var spentTime = document.getElementById("spent-time").value;
    console.log('spentTime = ', spentTime);

    /*
    $.ajax({
        url: '/api/tasks/logtask/' + userId + '/' + selectedProjectId,
        data: JSON.stringify(spentTime),
        type: 'PUT',
        contentType: 'application/json'
    });
    */

    $('#tmw-log-time-task').modal('hide');

});