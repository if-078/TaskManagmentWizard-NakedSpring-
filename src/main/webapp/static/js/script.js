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
            $("#sprint-container").show();
        })
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
            var priority = list[task].priority;
            var createdDate = list[task].created_date;
            $("#task-container").append("<div id='" + id + "' class='task'></div>");
            $(searchId).html("<p class='name'>" + name + "</p><p class='priority'>" + priority + "</p><p class='createdday'>" + createdDate + "</p><div class='task-nav'><button class='tag-btn'>tag</button><button class='priority-btn'>priority</button> <button class='status-btn'>status</button></div>");
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
            var priority = list[task].priority;
            var createdDate = list[task].created_date;
            $("#today-container").append("<div id='" + id + "' class='task'></div>");
            $(searchId).html("<p class='name'>" + name + "</p><p class='priority'>" + priority + "</p><p class='createdday'>" + createdDate + "</p><div class='task-nav'><button class='tag-btn'>tag</button><button class='priority-btn'>priority</button> <button class='status-btn'>status</button></div>");
        }
    }

