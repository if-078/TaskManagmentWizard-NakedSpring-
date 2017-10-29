    $(document).ready(function () {

        $("#task-container").hide();
        $("#task-container").load('static/load-pages/task.html');
        $('#btn-modal-task').click( function () {clearInputtask()});

        $("#tasks-btn").click(function () {
            var urlAllTaks = 'tasks';
            initialTableOftasks(urlAllTaks);

            $("#task-container").show();
        });

        $("#today-task-btn").click(function () {
            var urlForToDayTask = 'tasks/today'
            initialTableOftasks(urlForToDayTask);

            $("#task-container").show();
        });
        $("#sprint-task-btn").click(function () {
            var urlForSprint = 'tasks/sprint'
            initialTableOftasks(urlForSprint);

            $("#task-container").show();
        });

        /*makar. tree*/
        var userId = 1;

        $('.root-document').click(function(e) {
            e.preventDefault();
            if (this.id=="empty") {
                $(this).attr("id", "full");
                getChildTasksByUser(userId, "0");
            }
            else {
                $('a.trigger').text("●");
                $('.open').find("a.trigger").text("●");
                $('.open').removeClass('open');
            }
        });

        $('.trigger').click(function(e){
            e.preventDefault();
            var childUl = $(this).siblings("ul.tree-parent");
            if( childUl.hasClass('open') ){
                $(this).text("●");
                childUl.removeClass('open');
                $(this).find().hide();
            } else {
                $(this).text("...");
                childUl.addClass('open');
                $.ajax({
                    url: "tasks",
                    type: "GET",
                    contentType: 'application/json',
                    success: function (data) {
                        var id = data.id;
                        var name = data.name;
                        var user = data.userId;
                        var parent = data.parentId;
                        $().append("<li id='t" + id + "' class='tree-item view'><a href='" + id +
                        "' class='ion-document'>" + name + "</a><ul class='tree-parent'></ul></li>");
                        getChildTasksByUser(user, id);
                    }
                });

            }

        });

        $('.ion-document').click(function(e) {
            e.preventDefault();
            clearContent();
            $(".task").remove();
            $("#task-container").show();
        });

        function getTasks(id) {
            var url = "tasks/" + id + "/subtasks";
            console.log(url);
            console.log("table no else");
            initialTableOftasks(url);
        }

        function getChildTasksByUser(userId, parentId){
        console.log("get started");
            $.ajax({
                url: "tasks/assign_to/" + userId + "/parent/" + parentId,
                type: "GET",
                contentType: "application/json",
                success: function (data) {
                    console.log("success")
                    renderTreeTasks(data);
                }
            });
        }

        function renderTreeTasks(list) {
            /*var list1 = [];
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
                getTasks($(this).attr("href"));
                $("#task-container").show();
            })
        }*/
            for (var task = 0; task < list.length; task++) {
                var id = list[task].id;
                var name = list[task].name;
                var user = list[task].userId;
                var parent = list[task].parentId;
                    //function taskHasParent(id){
                $.ajax({
                        type: 'GET',
                        url: 'tasks/child/' + id,
                        contentType: 'application/json',
                        success: function (data) {
                        if (data) {
                            $(".tree").append("<li id='t" + id + "' class='tree-item'><a href='' class='trigger'>●</a>" +
                            "<a href = '" + id + "' class='ion-document'>" + name + "</a><ul class='tree-parent'></ul></li>");
                        }
                        else {
                            $(".tree").append("<li id='t" + id + "' class='tree-item'><a href = '" + id +
                            "' class='ion-document'>" + name + "</a></li>");
                        }
                        }
                });


            }
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
    });




