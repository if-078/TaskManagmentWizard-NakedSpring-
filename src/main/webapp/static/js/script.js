    $(document).ready(function () {
        $("#welcome-container").show();
        $("#tasks-btn").click(function () {
            clearContent();
            $(".task").remove();
            getTasks();
            $("#task-container").show();
        })
        $("#for-today-btn").click(function () {
            clearContent();
            $(".task").remove();
            getTodayTasks();
            $("#today-container").show();
        })
        $("#sprint-btn").click(function () {
            clearContent();
            $(".task").remove();
            getSprintTasks();
            $("#sprint-container").show();
        })
        $("#showForm").click(function (event) {
            showForm();  
            event.preventDefault(); 
            $("#showForm").submit(function(event){
            hideForm();
            event.preventDefault(); 
        });
                 
        })

        $('#showAddTaskForm').click(function (event) {
            showAddForm();  
            event.preventDefault(); 
            $("#addTaskSubmit").click(function(event){
            addTask();
            event.preventDefault(); 
        });
        });

    });

    function getTasks() {
        $.ajax({
            url: "tasks/",
            type: "GET",
            success: function (data) {
                var list = JSON.parse(data);
                renderTasks(list);
            }
        });
    }

    function clearContent() {
        $(".content").hide();
    }

    function renderTasks(list) {

        for (var task = 0; task < list.length; task++) {
            var id = "task" + list[task].id;
            var searchId = "#" + id;
            var name = list[task].name;
            var priority = list[task].priorityId;
            var status = list[task].statusId;
            var createdDate = list[task].createdDate;
            $("#task-container").append("<div id='" + id + "' class='task'></div>");
            $(searchId).html("<p class='name'>" + name + "</p><p class='priority'>Priority: " + getPriority(priority) + "</p><p class='status'>Status: " + getStatus(status) + "</p><p class='createdday'>" + createdDate + "</p>");
        }
    }
    function getTodayTasks() {
        $.ajax({
            url: "tasks/today",
            type: "GET",
            success: function (data) {
                var list = JSON.parse(data);
                renderForTodayTasks(list);
            }
        });
    }
    function renderForTodayTasks(list) {

        for (var task = 0; task < list.length; task++) {
            var id = "task" + list[task].id;
            var searchId = "#" + id;
            var name = list[task].name;
            var priority = list[task].priorityId;
            var status = list[task].statusId;
            var createdDate = list[task].createdDate;
            $("#today-container").append("<div id='" + id + "' class='task'></div>");
            $(searchId).html("<p class='name'>" + name + "</p><p class='priority'>Priority: " + getPriority(priority) + "</p><p class='status'>Status: " + getStatus(status) + "</p><p class='createdday'>" + createdDate + "</p>");
        }
    }

    function getSprintTasks() {
        $.ajax({
            url: "tasks/sprint",
            type: "GET",
            success: function (data) {
                var list = JSON.parse(data);
                renderSprintTasks(list);
            }
        });
    }
    function renderSprintTasks(list) {

        for (var task = 0; task < list.length; task++) {
            var id = "task" + list[task].id;
            var searchId = "#" + id;
            var name = list[task].name;
            var priority = list[task].priorityId;
            var status = list[task].statusId;
            var createdDate = list[task].createdDate;
            $("#sprint-container").append("<div id='" + id + "' class='task'></div>");
            $(searchId).html("<p class='name'>" + name + "</p><p class='priority'>Priority: " + getPriority(priority) + "</p><p class='status'>Status: " + getStatus(status) + "</p><p class='createdday'>" + createdDate + "</p>");
        }
    }
    function showForm() {
        $("#taskForm").show(500, function () {
            $("#showForm").hide();
        });
    }

    function hideForm() {
        $("#showForm").show(500, function () {
            $("#taskForm").fadeOut();
        });

    }



    function showAddForm() {
        $("#addTaskForm").show(500, function () {
            $("#showAddTaskForm").hide();
        });
    }

    function hideAddForm() {
        $("#showAddTaskForm").show(500, function () {
            $("#addTaskForm").fadeOut();
        });

    }


    function addTask() {
        var list = {
            name: $('#name').val(),
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val()
        }
        console.log(JSON.stringify(list));
        $.ajax({
            url:'tasks/', 
            type:'POST',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(list), 
            success: function(data) {
                hideAddForm();
            }
        });
    }

    function getPriority(id) {
        var list;
        $.ajax({
            url: "priority/" + id,
            type: "GET",
            async: false,
            success: function (data) {
                list = JSON.parse(data).name;
            }
        });
        return list;
    }

     function getStatus(id) {
        var list;
        $.ajax({
            url: "status/" + id,
            type: "GET",
            async: false,
            success: function (data) {
                list = JSON.parse(data).name;
            }
        });
        return list;
    }
