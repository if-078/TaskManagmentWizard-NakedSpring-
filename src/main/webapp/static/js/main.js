
$(document).ready(function () {

    // STATE OF APPLIED FILTERS
    var state = {
        parentid : 0,
        dateFrom : 0,
        dateTo : 0,
        status: [],
        priority: [],
        tag: []
    };
    // STATE OF APPLIED FILTERS


    // ON CLICK - SELECT TIME - ALL
    $('#tmw-time-all-btn').click(function (){
        $('#tmw-info-selected-time').html('Selected Time : All');
        state.dateFrom = 0;
        state.dateTo = 10000000000000;
        taskTable();
    });


    // ON CLICK - SELECT TIME - TODAY
    $('#tmw-time-today-btn').click(function (){
        $('#tmw-info-selected-time').html('Selected Time : Today');
        var currentData = new Date();
        var numberDay = currentData.getDay();
        state.dateFrom = currentData.setHours(0,0,0,0);
        state.dateTo = currentData.setHours(23,59,59,0);
        taskTable();
    });


    // ON CLICK - SELECT TIME - WEEK
    $('#tmw-time-week-btn').click(function (){
        $('#tmw-info-selected-time').html('Selected Time : Week');
        var currentData = new Date();
        var numberDay = currentData.getDay();
        state.dateFrom = currentData.setHours(0,0,0,0) - (numberDay-1)*86400000;
        state.dateTo = currentData.setHours(23,59,59,0) + (6-numberDay)*86400000;
        taskTable();
    });


    // ON CLICK - SELECT TIME - CUSTOM
    $('.datepicker').datepicker({
        autoclose: true
    });

    $('#tmw-time-btn-group > button').click(function (e) {
        e.preventDefault();
        $('#tmw-time-btn-group > button, #tmw-time-custom-btn').removeClass('active');
        $(this).addClass('active');
    });

    $('#tmw-time-custom-apply-btn').click(function (e) {
        e.preventDefault();

        if (!$('#tmw-time-custom-from').val() || !$('#tmw-time-custom-to').val()) {
            return;
        }

        state.dateFrom = Date.parse( $('#tmw-time-custom-from').val() );
        state.dateTo = Date.parse( $('#tmw-time-custom-to').val() ) + 86399999;

        $('#tmw-info-selected-time').html('Selected Time : ' + $('#tmw-time-custom-from').val() + " - " + $('#tmw-time-custom-to').val());
        $('#tmw-time-btn-group > button, #tmw-time-custom-btn').removeClass('active');
        $('#tmw-time-custom-btn').addClass('active');
        taskTable();
    });

    $('#tmw-time-custom-cancel-btn').click(function (e) {

    });

    // ON CLICK FILTER - STATUS
    var stateStatus = [];
    $('#tmw-status-btn').click(function () {

        // AJAX return response all STATUS
        $.ajax({
            url: 'status',
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {
                var response = JSON.parse(data);
                var newStatuses = [];

                for (var i = 0; i < response.length; i++) {
                    var newStatus = {
                        id: response[i].id,
                        name: response[i].name,
                        checked: false
                    };
                    for (var j = 0; j < stateStatus.length; j++) {
                        if (stateStatus[j].id === newStatus.id) {
                            newStatus.checked = stateStatus[j].checked;
                            break;
                        }
                    }
                    newStatuses.push(newStatus);
                }

                stateStatus = newStatuses;

                $('#tmw-status-list').empty();
                for (var i = 0; i < stateStatus.length; i++) {
                    $('#tmw-status-list').append($('<li class="col-xs-6"><a href="#"><input type="checkbox" value="' + stateStatus[i].id + '" ' + (stateStatus[i].checked ? 'checked' : '') + '> ' + stateStatus[i].name + '</a></li>'));
                }
            }
        });

    });

    $('#tmw-status-list').on('change', 'input', function () {
        for (var i = 0; i < stateStatus.length; i++) {
            if (stateStatus[i].id == this.value) {
                stateStatus[i].checked = this.checked;
                break;
            }
        }
    });

    // ON CLICK FILTER - PRIORITY
    var statePriority = [];
    $('#tmw-priority-btn').click(function () {

        // AJAX return response all PRIORITY
        $.ajax({
            url: 'priority',
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {
                var response = JSON.parse(data);
                var newPriorityes = [];

                for (var i = 0; i < response.length; i++) {
                    var newPriority = {
                        id: response[i].id,
                        name: response[i].name,
                        checked: false
                    };
                    for (var j = 0; j < statePriority.length; j++) {
                        if (statePriority[j].id === newPriority.id) {
                            newPriority.checked = statePriority[j].checked;
                            break;
                        }
                    }
                    newPriorityes.push(newPriority);
                }

                statePriority = newPriorityes;

                $('#tmw-priority-list').empty();
                for (var i = 0; i < statePriority.length; i++) {
                    $('#tmw-priority-list').append($('<li><a href="#"><input type="checkbox" value="' + statePriority[i].id + '" ' + (statePriority[i].checked ? 'checked' : '') + '> ' + statePriority[i].name + '</a></li>'));
                }
            }
        });
    });

    $('#tmw-priority-list').on('change', 'input', function () {
        for (var i = 0; i < statePriority.length; i++) {
            if (statePriority[i].id == this.value) {
                statePriority[i].checked = this.checked;
                break;
            }
        }
    });

    // ON CLICK FILTER - TAG
    var stateTags = [];
    $('#tmw-tags-btn').click(function () {

        // AJAX return response all TAG
        $.ajax({
            url: 'tags',
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {
                var response = data;
                var newTags = [];

                for (var i = 0; i < response.length; i++) {
                    var newTag = {
                        id: response[i].id,
                        name: response[i].name,
                        checked: false
                    };
                    for (var j = 0; j < stateTags.length; j++) {
                        if (stateTags[j].id === newTag.id) {
                            newTag.checked = stateTags[j].checked;
                            break;
                        }
                    }
                    newTags.push(newTag);
                }

                stateTags = newTags;

                $('#tmw-tags-list').empty();
                for (var i = 0; i < stateTags.length; i++) {
                    $('#tmw-tags-list').append($('<li class="col-xs-6"><a href="#"><input type="checkbox" value="' + stateTags[i].id + '" ' + (stateTags[i].checked ? 'checked' : '') + '> ' + stateTags[i].name + '</a></li>'));
                }
            }
        });
    });

    $('#tmw-tags-list').on('change', 'input', function () {
        for (var i = 0; i < stateTags.length; i++) {
            if (stateTags[i].id == this.value) {
                stateTags[i].checked = this.checked;
                break;
            }
        }
    });

    //ON CLICK APPLY FILTERS --> STATUS, PRIORITY, TAG
    $('#tmw-apply-btn').click(function () {

        state.status = stateStatus;
        state.priority = statePriority;
        state.tag = stateTags;

        var infoSelectedStatus = 'Selected Status : ';
        var isPresentSelectedStatus = false;
        for (var i = 0; i < state.status.length; i++) {
            if(state.status[i].checked) {
                infoSelectedStatus += state.status[i].name + '; ';
                isPresentSelectedStatus = true;
            }
        }
        if(isPresentSelectedStatus){
            $('#tmw-info-selected-status').html(infoSelectedStatus);
        }else{
            $('#tmw-info-selected-status').html('Selected Status : All');
        }

        var infoSelectedPriority = 'Selected Priority : ';
        var isPresentSelectedPriority = false;
        for (var i = 0; i < state.priority.length; i++) {
            if(state.priority[i].checked) {
                infoSelectedPriority += state.priority[i].name + '; ';
                isPresentSelectedPriority = true;
            }
        }
        if(isPresentSelectedPriority){
            $('#tmw-info-selected-priority').html(infoSelectedPriority);
        }else{
            $('#tmw-info-selected-priority').html('Selected Priority : All');
        }

        var infoSelectedTag = 'Selected Tag : ';
        var isPresentSelectedTag = false;
        for (var i = 0; i < state.tag.length; i++) {
            if(state.tag[i].checked) {
                infoSelectedTag += state.tag[i].name + '; ';
                isPresentSelectedTag = true;
            }
        }
        if(isPresentSelectedTag){
            $('#tmw-info-selected-tag').html(infoSelectedTag);
        }else{
            $('#tmw-info-selected-tag').html('Selected Tag : All');
        }

        taskTable();
    });

    // ON CLICK RESET FILTERS --> STATUS, PRIORITY, TAG
    $('#tmw-reset-btn').click(function () {
        state.status = [];
        state.priority = [];
        state.tag = [];
        stateStatus = [];
        statePriority = [];
        stateTags = [];
        $('#tmw-info-selected-status').html('Selected Status : All');
        $('#tmw-info-selected-priority').html('Selected Priority : All');
        $('#tmw-info-selected-tag').html('Selected Tag : All');

        taskTable();
    });



    // CREATION MAIN NODE AND CHILDREN IN TREE
    $('#tmw-treeview').jstree({
        core: {
            data: {
                url: function (node) {
//				$("ul.jstree-container-ul a.jstree-anchor").attr("title", "node.name");
                    switch (node.id) {
                        case '#':
                            var rootNode = {
                                id: '$',
                                text: 'All Projects',
                                children: true
                            };
                            return 'data:application/json,' + encodeURIComponent(JSON.stringify(rootNode));

                        case '$':
                            return 'tasks/tree/0';

                        default:
                            return 'tasks/tree/' + node.id;
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

    // OUTPUT TABLE SUBTASKS FOR SELECTED ROOT-TASK
    $('#tmw-treeview').on('select_node.jstree', function (event, data) {
        var hasChildren = (data.node.children.length > 0 || !data.node.state.loaded);
        if (hasChildren){
            state.parentid = data.node.id !== '$' ? data.node.id : 0;
            taskTable();
        }else{
            showFull(data.node.id);
        }
    });

    // GENERATION STRING PARAMETERS FOR GET REQUEST
    var generatedRequestParameters = function(){

        var parameters = '?parentid=' + state.parentid + '&date='+ state.dateFrom + ',' + state.dateTo;

        parameters = parameters + '&status=';
        for (var i = 0; i < state.status.length; i++) {
            if (state.status[i].checked){
                parameters = parameters + state.status[i].id + ',';
            }
        }
        parameters = parameters.slice(0,-1);

        parameters = parameters + '&priority=';
        for (var i = 0; i < state.priority.length; i++) {
            if (state.priority[i].checked){
                parameters = parameters + state.priority[i].id + ',';
            }
        }
        parameters = parameters.slice(0,-1);

        parameters = parameters + '&tag=';
        for (var i = 0; i < state.tag.length; i++) {
            if (state.tag[i].checked){
                parameters = parameters + state.tag[i].id + ',';
            }
        }
        parameters = parameters.slice(0,-1);

        return parameters;
    }

    // RECEIVING CHILDREN FOR ROOT-TASK AND CONCLUSION IN THE TABLE
    var taskTableInit = false;
    var taskTable = function () {
        $.ajax({
            url: 'tasks/filter' + generatedRequestParameters(),
            type: 'GET',
            contentType: 'application/json',

            success: function (data) {
                var rows = []

                for (var i = 0; i < data.length; i++) {
                    rows.push(Object.values(data[i]));
                }

                if (taskTableInit) {
                    $('#tmw-task-table').DataTable().destroy();
                }

                taskTableInit = true;

                if (rows.length > 0) {
                    $('#tmw-task-table').css('visibility', 'visible');

                    $('#tmw-task-table').DataTable({
                        data: rows,

                        columns: [
                            {title: "ID", visible: false},
                            {title: "Name"},
                            {title: "Start Date"},
                            {title: "Est. Time"},
                            {title: "Assignee"},
                            {title: "Status"},
                            {title: "Priority"}
                        ],

                        paging: false,
                        info: false,
                        searching: false
                    });
                } else {
                    $('#tmw-task-table').css('visibility', 'hidden');
                }
            }
        });
    }

    // SHOW FULL INFORMATION ABOUT THE TASK
    var showFull = function (id) {

        // AJAX return response full info one User
        $.ajax({
            url: 'tasks/view/' + id,
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {

                $('#tmw-user-field1').html(data.name);
                $('#tmw-user-field2').html(data.createdDate);
                $('#tmw-user-field3').html(data.startDate);
                $('#tmw-user-field4').html(data.estimateTime);
                $('#tmw-user-field5').html(data.assignTo);
                $('#tmw-user-field6').html(data.status);
                $('#tmw-user-field7').html(data.priority);

                $('#tmw-modal').modal('show');
            }
        });
    };

    // GET FULL INFORMATION ABOUT THE TASK
    $('#tmw-task-table').on('click', 'tr', function () {
        var table = $('#tmw-task-table').DataTable();
        var taskId = table.row(this).data()[0];
        showFull(taskId);
    });
});
