    $(document).ready(function () {
        $("#welcome-container").show();
        $("#tasks-btn").click(function () {
            clearContent();
            $(".task").remove();
            //getTasks();
            $("#task-container").load('/static/load-pages/task.html');
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
        });

        $("#user-btn").click(function () {
            clearContent();
            $(".task").remove();
            $("#user-container").load('/static/load-pages/user.html');
            $("#user-container").show();
        });

        /*$(document.getElementById("update")).click(function () {
            clearContent();
            $(".task").remove();
            $("#update-container").show();
        })*/




       /* $("#showForm").click(function (event) {
            showForm();  
            event.preventDefault(); 
            $("#showForm").submit(function(event){
            hideForm();
            event.preventDefault();
        });
                 
        })*/

/*        $('#showAddTaskForm').click(function (event) {
            showAddForm();  
            event.preventDefault(); 
            $("#addTaskSubmit").click(function(event){
            addTask();
            event.preventDefault(); 
        });
        });*/

/*makar. tree*/
$('.trigger').click(function(e){
  e.preventDefault();
  var childUl = $(this).siblings("ul.tree-parent");
  if( childUl.hasClass('open') ){
    $(this).text("●");
    childUl.removeClass('open');
  } else {
    $(this).text("...");
    childUl.addClass('open');
  }

});

$('.ion-document').click(function(e) {
     e.preventDefault();
     clearContent();
     $(".task").remove();
     getTasks();
     $("#task-container").show();
});

$('.root-document').click(function(e) {
     e.preventDefault();
     if (this.id=="empty") {
     $(this).attr("id", "full");
     var user_id = 1;
     getTreeTasks(user_id);
     }
     else {
     $('a.trigger').text("●");
     $('.open').find("a.trigger").text("●");
     $('.open').removeClass('open');
     }
});


    });

    function getOneTask(id) {
            $.ajax({
                url: "tasks/",
                type: "GET",
                success: function (data) {
                    //var list = [JSON.parse(data)];
                    renderTasksFromTree(data, id);
                }
            });
    }
    function renderTasksFromTree(list, id) {
        console.log("---list---");
        console.log(list);
        console.log("---list---");

        for (var task = 0; task < list.length; task++) {
        if (list[task].id == id) {
            var idTask = "task" + id;
            var searchId = "#" + id;
            var name = list[task].name;
            var priority = list[task].priorityId;
            var status = list[task].statusId;
            var createdDate = list[task].createdDate;
            $("#task-container").append("<div id='" + id + "' class='task'></div>");
            $(searchId).html("<p class='name'>" + name + "</p><p class='priority'>Priority: " +
            getPriority(priority) + "</p><p class='status'>Status: " + getStatus(status) +
            "</p><p class='createdday'>" + createdDate + "</p><p><button id=\"update\">Update</button></p>");
        }
        }


/*        $("#update").click(function () {
            clearContent();
            $(".task").remove();
            $("#update-container").show();
            $("#updateTaskSubmit").click(function(event){
                updateTask();
                event.preventDefault();
            })

        })*/
    }

    function renderTreeTasks(list, user_id) {
        console.log(list);
        var list1 = [];
        for (var task = 0; task < list.length; task++) {
            if (list[task].assignTo==user_id) {
                list1.push(list[task]);
            }
        }
        list1.sort(function(a, b){return a.parentId - b.parentId});
        list=list1;
        for (var task = 0; task < list.length; task++) {
            var id = list[task].id;
            var name = list[task].name;
            var parent = list[task].parentId;
            if (parent==0) {
                $(".tree").append("<li id='t" + id + "' class='tree-item'></li>");
                $("#t" + id).html("<a href = '" + id + "' class='ion-document'>" + name + "</a>");
                }
            else {
                var par_name = $("#t" + parent + " a:first").text();
                if (par_name!="●") {
                $("#t" + parent).html("<a href='' class='trigger'>●</a>" +
                "<a href='" + id + "' class='ion-document'>" + par_name + "</a><ul class='tree-parent'>" +
                "<li id='t" + id + "' class='tree-item'><a href='" + id + "' class='ion-document'>" +
                name + "</a></li></ul>");
                }
                else {
                $("#t"+parent+" ul").append("<li id='t" + id +
                "' class='tree-item'><a href='" + id + "' class='ion-document'>" +
                name + "</a></li>");
                }
                if (par_name=="") list.push(list[task]);
            }
        }
        $('.trigger').click(function(e){
          e.preventDefault();
          var childUl = $(this).siblings("ul.tree-parent");
          if( childUl.hasClass('open') ){
            $(this).text("●");
            childUl.removeClass('open');
          } else {
            $(this).text("...");
            childUl.addClass('open');
          }
        })
            $('.ion-document').click(function(e) {
                 e.preventDefault();
                 clearContent();
                 $(".task").remove();
                 getOneTask($(this).attr("href"));
                 $("#task-container").show();
            })
    }

    function getTreeTasks(user_id) {
        $.ajax({
            url: "tasks",
            type: "GET",
            contentType: 'application/json',
            success: function (data) {
                //var list = JSON.parse(data);
                renderTreeTasks(data, user_id);
            }
        });
    }
    //makar.tree.end
    function getTasks() {
        $.ajax({
            url: "tasks",
            type: "GET",
            contentType: 'application/json',
            success: function (data) {
                //var list = JSON.parse(data);
                renderTasks(data);
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
            $(searchId).html("<p class='name'>" + name + "</p><p class='priority'>Priority: " +
            getPriority(priority) + "</p><p class='status'>Status: " + getStatus(status) +
            "</p><p class='createdday'>" + createdDate + "</p><p><button id=\"update\">Update</button></p>");
        }


        $("#update").click(function () {
            clearContent();
            $(".task").remove();
            $("#update-container").show();
            $("#updateTaskSubmit").click(function(event){
                updateTask();
                event.preventDefault(); 
            })

        })
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
            $(searchId).html("<p class='name'>" + name + "</p><p class='priority'>Priority: " +
            getPriority(priority) + "</p><p class='status'>Status: " + getStatus(status) +
            "</p><p class='createdday'>" + createdDate + "</p><p><button id=\"update\">Update</button></p>");
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
            $(searchId).html("<p class='name'>" + name + "</p><p class='priority'>Priority: " + getPriority(priority) + "</p><p class='status'>Status: " + getStatus(status) + "</p><p class='createdday'>" + createdDate + "</p><p><button id=\"update\">Update</button></p>");
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


    /*function addTask() {
        var list = {
            name: $('#name').val(),
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val()
        }
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
    }*/

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

    /*function updateTask(task) {
        
        var list = {
            name: $('#nameU').val(),
            startDate: $('#startDate').val(),
            endDate: $('#endDate').val(),
            assignTo: $('#assignTo').val(),
            //estimateTime: $('#estimateTime').val(),
            priorityId: $('#priority').val(),
            statusId: $('#status').val()
        }
        console.log(JSON.stringify(list));
        $.ajax({
            url:'tasks/update', 
            type:'PUT',
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(list), 
            success: function(data) {
                console.log("good");
                console.log(data);
            }
        });
    }
    */
