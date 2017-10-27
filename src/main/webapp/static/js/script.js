    $(document).ready(function () {

        //$("#task-container").load('/static/load-pages/task.html');
        var task = {};
        var table = $('#task-table').DataTable();
        $('#btn-modal-task').click( function () {clearInputtask()});
        var url = 'tasks' //default URL

        $("#welcome-container").show();

        $("#tasks-btn").click(function () {
            clearContent();
            $(".task").remove();
            var urlAllTaks = 'tasks';

            console.log("before initial Table");
            table.destroy();
            //$("#task-table-container").load('/static/load-pages/taskTable.html');

            initialTableOftasks(urlAllTaks);

            $("#task-container").show();

            console.log("after initial Table");
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

        function initialTableOftasks(URL) {
            task = {}
            table.destroy();
            //$("#task-table-container").load('/static/load-pages/taskTable.html');
            $.ajax({
                type: 'GET',
                url: URL,
                contentType: 'application/json',
                async: true,
                success: function(data) {
                    $("#task-table-container").load('/static/load-pages/taskTable.html');
                    //table.clear().draw();
                    //table.destroy();
                     console.log(table);


                    table = $('#task-table').DataTable({
                        "destroy": true,
                        "aaData": data,
                        "aoColumns": [
                            {"mDataProp":"id"},
                            {"mDataProp":"name"},
                            {"mDataProp":"createdDate"},
                            {"mDataProp":"startDate"},
                            {"mDataProp":"endDate"},
                            {"mDataProp":"estimateTime"},
                            {"mDataProp":"assignTo"},
                            {"mDataProp":"statusId"},
                            {"mDataProp":"priorityId"},
                            {"mDataProp":"parentId"},
                        ]
                    });
                    // delete task and row
                    $('#task-table tbody').on( 'click', 'tr', function () {
                        if ( $(this).hasClass('selected') ) {
                            $(this).removeClass('selected');
                        }
                        else {
                            table.$('tr.selected').removeClass('selected');
                            $(this).addClass('selected');
                            $(this).addClass('active').siblings().removeClass('active');
                        }
                    } );
                    $('#delete-task').on("click", function () {
                        var taskId = $('tr.selected td:first-child').text();
                        console.log("-================");
                        console.log(taskId);
                        console.log("-================");

                        deletetask(taskId);
                        table.row('.selected').remove().draw( false );
                    } );
                    // update task and row
                    $('#task-table tbody').on( 'dblclick', 'tr', function () {
                        $(this).addClass("selected").siblings().removeClass("selected");
                        task={};
                        task = table.row( this ).data();

                        console.log("----- update ---");
                        console.log(task);
                        console.log("----- update ---");


                        var taskId = $('tr.selected td:first-child').text();
                        //fill data
                        $('#input-name').val(task.name);
                        $('#input-createdDate').val(task.createdDate);
                        $('#input-startDate').val(task.startDate);
                        $('#input-endDate').val(task.endDate);
                        $('#input-estimateTime').val(task.estimateTime);
                        $('#input-assignTo').val(task.assignTo);
                        $('#input-statusId').val(task.statusId);
                        $('#input-priorityId').val(task.priorityId);
                        $('#input-parentId').val(task.parentId);
                        // open modal window
                        $('#modal-new-task').modal('show');
                    } );
                    $('#create-task').on( 'click',function () {
                        console.log("before create of update")
                        console.log(task);
                        createOrUpdatetask(table, task);
                    } );
                },
                error: function(jqXHR) {
                    console.log(jqXHR.status)
                }
            });
        }
        function deletetask(taskId){
            $.ajax({
                type: 'DELETE',
                url: 'tasks/' + taskId,
                contentType: 'application/json',
                error: function(jqXHR) {
                    console.log(jqXHR.status)
                }
            });
        }
        function createOrUpdatetask(table, task){
            console.log("cereateOR Update")
            console.log(task);

            if (jQuery.isEmptyObject(task)) {
                console.log("is empty task");
                console.log(task);
                task =
                {
                    "name":$('#input-name').val(),
                    "createdDate":$('#input-createdDate').val(),
                    "startDate":$('#input-startDate').val(),
                    "endDate":$('#input-endDate').val(),
                    "estimateTime":$('#input-estimateTime').val(),
                    "assignTo":$('#input-assignTo').val(),
                    "statusId":$('#input-statusId').val(),
                    "priorityId":$('#input-priorityId').val(),
                    "parentId":$('#input-parentId').val()
                }
                createtask(table, task);
            }else{
                console.log("not empty task");

                console.log(task);

                task.name = $('#input-name').val(),
                task.createdDate= $('#input-createdDate').val(),
                task.startDate= $('#input-startDate').val(),
                task.endDate= $('#input-endDate').val(),
                task.estimateTime= $('#input-estimateTime').val(),
                task.assignTo= $('#input-assignTo').val(),
                task.statusId= $('#input-statusId').val(),
                task.priorityId= $('#input-priorityId').val(),
                task.parentId= $('#input-parentId').val(),

                updatetask(table, task);
            }

            task = {}
        }
        function createtask(table, task){
            $.ajax({
                url: 'tasks',
                data: JSON.stringify(task),
                type: 'POST',
                contentType: 'application/json',
                success: function (data) {
                    // add row to table
                    table.row.add( {
                        "id":data.id,
                        "name":data.name,
                        "createdDate":data.createdDate,
                        "startDate":data.startDate,
                        "endDate":data.endDate,
                        "estimateTime":data.estimateTime,
                        "assignTo":data.assignTo,
                        "statusId":data.statusId,
                        "priorityId":data.priorityId,
                        "parentId":data.parentId
                    } ).draw();
                    $('#modal-new-task').modal('hide');
                    clearInputtask();
                },
                cache: false
            }).fail(function ($xhr) {
                if($xhr.status == 400){
                    var data = $xhr.responseJSON;
                }
            });
        }
        function updatetask(table, task){
            $.ajax({
                url: 'tasks',
                data: JSON.stringify(task),
                type: 'PUT',
                contentType: 'application/json',
                success: function () {
                    // update row of table
                    table.row('.selected').data(task).draw();
                    $('#modal-new-task').modal('hide');
                    clearInputtask();
                },
                cache: false
            }).fail(function ($xhr) {
                if($xhr.status == 400){
                    var data = $xhr.responseJSON;
                }
            });
        }
        function clearInputtask(){
            $('#input-name').val(''),
                $('#input-createdDate').val(''),
                $('#input-startDate').val(''),
                $('#input-endDate').val(''),
                $('#input-estimateTime').val(''),
                $('#input-assignTo').val(''),
                $('#input-statusId').val(''),
                $('#input-priorityId').val(''),
                $('#input-parentId').val('')
        }

        function getOneTask(id) {
            var url = "tasks/" + id + "/subtasks";
            console.log(url);

            if(table != null){
                console.log("not null table");
                //table.clear().draw();
            } else {
                console.log("table empty")
            }

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




