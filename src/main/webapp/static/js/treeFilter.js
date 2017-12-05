
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

// OUTPUT TABLE SUBTASKS FOR SELECTED TASK
$('#tmw-treeview').on('select_node.jstree', function (event, data) {

    var hasChildren = (data.node.children.length > 0 || !data.node.state.loaded);
    if (hasChildren) {
        $('#tmw-treeview').jstree('open_node', '' + data.node.id);
        console.log('nodeId = ', data.node.id);
    }

    state.parentId = data.node.id !== '$' ? data.node.id : 0;
    selectedTaskId = state.parentId;
    selectedTaskText =  data.node.text;

    if (data.node.parent === '$') {
        hideButtonSwitchCalendarTable();
        hideDraftPlanningTable();
        $('#tmw-task-calendar').fullCalendar('destroy');
        taskCalendarInit = false;
    }

    taskTable();

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