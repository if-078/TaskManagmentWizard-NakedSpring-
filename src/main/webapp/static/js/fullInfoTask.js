
// SHOW FULL INFORMATION ABOUT THE TASK
var showFull = function (eventTask) {

    $('#tmw-task-info').html('Information of task');
    $('#tmw-task-name').html(eventTask.id);
    $('#tmw-modal').modal('show');

    $('#tmw-task-btn-save').on('click', function () {
        console.log('EVENT TASK = ', eventTask);
        eventTask.resourceId = '1';
        updateCalendarTask(eventTask);
    });

};


$('#tmw-create-task').on('click', function () {

    var textInfo;
    if (selectedTaskId==0){
        textInfo = 'Creating new Project';
    }else {
        textInfo = 'Creating task in : ' + selectedTaskText + '    id = ' + selectedTaskId;
    }

    $('#tmw-task-info').html(textInfo);

    $('#tmw-modal').modal('show');
});


