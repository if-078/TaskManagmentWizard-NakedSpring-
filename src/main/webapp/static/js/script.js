    $(document).ready(function () {

        $("#task-container").hide();
        $("#task-container").load('static/load-pages/task.html');
        $('#btn-modal-task').click( function () {clearInputtask()});

        $("#tasks-btn").click(function () {
            var urlAllTaks = 'tasks';
            initialTableOftasks(urlAllTaks);
            $("#start, #end").datebox('clear');
            $("#task-container").show();
        });

        $("#today-task-btn").click(function () {
            var urlForToDayTask = 'tasks/today'
            initialTableOftasks(urlForToDayTask);
          $("#start, #end").datebox('setValue', getCurrentFormatedDate ());
            $("#task-container").show();
        });
        $("#sprint-task-btn").click(function () {
            var urlForSprint = 'tasks/sprint'
            initialTableOftasks(urlForSprint);

            $("#task-container").show();
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

        function getCurrentFormatedDate () {
            var d = new Date();
            return d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
        }

    });




