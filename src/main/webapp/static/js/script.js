    $(document).ready(function () {
        $("#task-container").load('static/load-pages/task.html');
        $('#btn-modal-task').click( function () {clearInputtask()});
        var url = 'tasks' //default URL

        $("#welcome-container").show();

        $("#tasks-btn").click(function () {
            clearContent();
            $(".task").remove();

           // $(".task-container").empty();
            var urlAllTaks = 'tasks';

            console.log("before initial Table");
           //$("#task-container").load('static/load-pages/task.html');


/*           $.ajax({
                       url: "static/load-pages/task.html",
                       context: document.body,
                       success: function(responseText) {
                           $("#task-container").html(responseText);
                           $("#task-container").find("script").each(function(i) {
                               console.log(eval($(this).text()));
                               //eval($(this).text());
                           });
                       }
                   });*/
            $("#task-container").load('static/load-pages/task.html');
            initialTableOftasks(urlAllTaks);

          /* $("#task-container").load('static/load-pages/task.html', function(responseTxt, statusTxt, xhr){
                  var responseAsObject = $.parseJSON(responseTxt);
                  //console.log(responseAsObject.initialTableOftasks(urlAllTaks));
                  console.log(responseTxt);
               });*/
            //initialTableOftasks(urlAllTaks);

            $("#task-container").show();

            console.log("after initial Table");
            //$("#task-container").load('static/load-pages/task.html');
             //initialTableOftasks(urlAllTaks);
        });
        $("#for-today-btn").click(function () {
            clearContent();
            $(".task").remove();
            getTodayTasks();
            $("#today-container").show();
        });
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
            //getTasks();
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

        function getOneTask(id) {
            var url = "tasks/" + id + "/subtasks";
            console.log(url);

            console.log("table no else");

            initialTableOftasks(url);
        }

        function renderTreeTasks(list, user_id) {
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
                    $(".tree").append("<li id='t" + id + "' class='tree-item'><a href = '" + id +
                    "' class='ion-document'>" + name + "</a></li>");
                }
                else {
                    var par_name = $("#t" + parent + " a:first").text();
                    if (par_name!="●") {
                        $("#t" + parent).html("<a href='' class='trigger'>●</a>" +
                        "<a href='" + parent + "' class='ion-document'>" + par_name + "</a><ul class='tree-parent'></ul>");
                        $("#t"+parent+" ul").append("<li id='t" + id + "' class='tree-item view'></li>");
                        $("#t"+parent+" ul li").append("<a href='" + id + "' class='ion-document'>" +
                        name + "</a>");
                    }
                    else {
                        $("#t"+parent+" ul").append("<li id='t" + id + "' class='tree-item view'><a href='" + id +
                        "' class='ion-document'>" + name + "</a></li>");
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
                    renderTreeTasks(data, user_id);
                }
            });
        }

        function clearContent() {
            $(".content").hide();
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
    });




