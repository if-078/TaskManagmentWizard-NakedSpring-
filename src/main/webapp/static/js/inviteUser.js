$('#tmw-invite-btn').on('click', function () {
    $('#inviteUser').modal('show');
});

$('#tmw-btn-send-invite').on('click', function () {
    var inviteUserEmail = document.getElementById("tmw-invite-email").value;

    $.ajax({
        url: '/api/tasks/invite/' + userId + '/' + selectedProjectId,
        data: JSON.stringify(inviteUserEmail),
        type: 'PUT',
        contentType: 'application/json'
    });

    $('#inviteUser').modal('hide');

});