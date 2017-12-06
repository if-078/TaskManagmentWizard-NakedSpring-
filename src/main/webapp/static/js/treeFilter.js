// CREATE TREEVIEW
$('#tmw-treeview').jstree({
    core: {
        data: {
            url: function (node) {
                switch (node.id) {

                    case '#':
                        var rootNode = {
                            id: '$',
                            text: 'All Projects',
                            children: true
                        };
                        return 'data:application/json,' + encodeURIComponent(JSON.stringify(rootNode));

                    case '$':
                        $.ajaxSetup({
                            headers: createAuthToken()
                        });

                        return 'api/tasks/tree/0' + '?userId=' + userId;

                    default:
                        return 'api/tasks/tree/' + node.id + '?userId=' + userId;
                }
            }
        },

        dblclick_toggle: false
    }
});


// REMOVE NODES IN A TREE
$('#tmw-treeview').on('after_close.jstree', function (e, data) {
    var tree = $('#tmw-treeview').jstree(true);
    tree.delete_node(data.node.children);
    tree._model.data[data.node.id].state.loaded = false;
});





$('#tmw-treeview').on('select_node.jstree', function (event, data) {

    // open node if node has children
    var hasChildren = (data.node.children.length > 0 || !data.node.state.loaded);
    if (hasChildren) {
        $('#tmw-treeview').jstree('open_node', '' + data.node.id);
    }

    state.parentId = data.node.id !== '$' ? data.node.id : 0;
    selectedTaskId = state.parentId;
    selectedTaskText = data.node.text;
    showDataOnCalendarAndTable();
});


// CREATE TOOLTIPE TASK
$('#tmw-treeview').on("mouseenter.jstree", function (event, data) {
    aTree();
});
var aTree = function () {
    var tasks = $("ul.jstree-container-ul a.jstree-anchor");
    for (i = 0; i < tasks.length; i++) {
        tasks[i].title = tasks[i].innerText;
    }
};


// DOUBLE-CLICK ON ANY-TASK
$('#tmw-treeview').on('dblclick.jstree', function (event, data) {

    var node = $(event.target).closest('li');
    var id = node[0].id;
    if ((data == undefined) && (id != '$')) {
        showFull(id);
    }

});


function openNodeAllProjects() {
    $('#tmw-treeview').jstree('open_node', '$');
}

$('#loginForm button').on('click', function () {
    setTimeout(openNodeAllProjects, 1500);
});


var showDataOnCalendarAndTable = function () {

    // is selected node AllProjects
    if (state.parentId == 0) {

        hideButtonSwitchCalendarTable();

        if (taskPlanningTableInit) {
            $('#tmw-task-planning-table').DataTable().destroy();
            taskPlanningTableInit = false;
        }
        hidePlanningTable();

        taskCalendarInit = false;
        $('#tmw-task-calendar').fullCalendar('destroy');

        if (taskTableInit) {
            $('#tmw-task-table').DataTable().destroy();
            taskTableInit = false;
        }
        $('#tmw-main-table').css('visibility', 'hidden');
        hideDraftPlanningTable();

        selectedProjectId = 0;
        isSelectedNewProject = true;

        return;
    }



    $.ajax({
        url: '/api/tasks/' + state.parentId,
        type: 'GET',
        contentType: 'application/json',

        success: function (task) {

            var taskProjectId = task.projectId;
            if (selectedProjectId == taskProjectId) {
                isSelectedNewProject = false;
            } else {
                taskCalendarInit = false;
                $('#tmw-task-calendar').fullCalendar('destroy');
                isSelectedNewProject = true;
            }
            selectedProjectId = taskProjectId;

            showButtonSwitchCalendarTable();
            if (isSelectedCalendar) {
                taskCalendar();
            } else {
                taskPlanningTable();
            }

            taskTable();
        }
    });

};