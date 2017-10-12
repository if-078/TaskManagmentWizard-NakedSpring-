    $(document).ready(function () {
    	
	testdata = [{
    	    "name": "58",
    	    "created_date": "UK",
    	    "start_date": "Legal Director",
    	    "end_date": "2012-03-08 00:00:00",
    	    "assign_to": "http://...",
    	    "status_id": "http://...",
    	    "priority_id": "http://...",
    	    "parent_id": "http://..."
    	}, {
    	    "name": "58",
    	    "created_date": "UK",
    	    "start_date": "Legal Director",
    	    "end_date": "2012-03-08 00:00:00",
    	    "assign_to": "http://...",
    	    "status_id": "http://...",
    	    "priority_id": "http://...",
    	    "parent_id": "http://..."
    	}];
    	
    	 //add style to data table 
        $('#task-table').DataTable({
        	"aaData": testdata,
            "aoColumns": [{
            "mDataProp": "name"
        }, {
            "mDataProp": "created_date"
        }, {
            "mDataProp": "start_date"
        }, {
            "mDataProp": "end_date"
        }, {
            "mDataProp": "assign_to"
        },
        {
            "mDataProp": "status_id"
        },
        {
            "mDataProp": "priority_id"
        },
        {
            "mDataProp": "parent_id"
        }
        
        ],
   
        	language: {
            search: "Введіть параметри для пошуку",
            searchPlaceholder: "пошук...",
            zeroRecords: "За даними параметрами збігів не знайдено",
            paginate: {
            	next: "Наступна",
            	previous: "Попередня"
            }
          },
          order:[1, 'asc']
          ,
          columnDefs: [{
            targets: [0, 1, 2, 3, 4],
            orderable: false,
          }, {
            targets: [0, 3],
            searchable: false,
          }],
          bLengthChange: false,
          info: false,
        });

    	
    	
        $("#welcome-container").show();
        $("#tasks-btn").click(function () {
            clearContent();
            $(".task").remove();
            getTasks();
            $("#task-container").show();
        })
        $("#for-today-btn").click(function () {
            clearContent();
            $("#today-container").show();
        })
        $("#sprint-btn").click(function () {
            clearContent();
            $("#sprint-container").show();
        })
    });
    
    
function getTasksJson() {
    $.ajax({
    	url: "http://localhost:8080/tmw/tasks/",
        type: "GET",
        success: function (data) {
            console.log('----ajax ------');
            console.log(data);
            var dataTask = JSON.parse(data);
        	return JSON.parse(data);
        }
    });
}

   function getTasks() {
        $.ajax({
            url: "http://localhost:8080/tmw/tasks/",
            type: "GET",
            success: function (data) {
                console.log('----ajax ------');
                
                //var text='[{"param1":"0.00","param2":"15.00","param3":"0.00","param4":"12"},{"param1":"0.00","param2":"15.00","param3":"0.00","param4":"12"}]';
               var dataTest = JSON.parse(data);
                console.log(dataTest);
                //console.log(table.rows.add(dataTest).draw());
                console.log(dataTest);
                //var list = JSON.parse(data);
                //renderTasks(list);
            }
        });
    }

    function clearContent() {
        $(".content").hide();
    }
    
  /*  function renderTasks(list) {
    	console.log('----list ------');
    	console.log('----list ------');
        for (var task = 0; task < list.length; task++) {
            var id = "task" + list[task].id;
            var searchId = "#" + id;
            var name = list[task].name;
            var priority = list[task].priority;
            var createdDate = list[task].created_date;
            
            var $row = $('<tr>'+
            	      '<td>'+list[task].name+'</td>'+
            	      '<td>'+createdDate+'</td>'+
            	      '<td>'+createdDate+'</td>'+
            	      '<td>'+createdDate+'</td>'+
            	      '<td>'+createdDate+'</td>'+
            	      '<td>'+createdDate+'</td>'+
            	      '<td>'+list[task].priority+'</td>'+
            	      '<td>'+createdDate+'</td>'+
            	      '</tr>');  
            $('table> tbody:last').append($row);
            $("#task-container").append("<div id='" + id + "' class='task'></div>");
            $(searchId).html("<p class='name'>" + name + "</p><p class='priority'>" + priority + "</p><p class='createdday'>" + createdDate + "</p><div class='task-nav'><button class='tag-btn'>tag</button><button class='priority-btn'>priority</button> <button class='status-btn'>status</button></div>");
        }
    }*/

    /*function renderTasks(list) {

        for (var task = 0; task < list.length; task++) {
            var id = "task" + list[task].id;
            var searchId = "#" + id;
            var name = list[task].name;
            var priority = list[task].priority;
            var createdDate = list[task].created_date;
            $("#task-container").append("<div id='" + id + "' class='task'></div>");
            $(searchId).html("<p class='name'>" + name + "</p><p class='priority'>" + priority + "</p><p class='createdday'>" + createdDate + "</p><div class='task-nav'><button class='tag-btn'>tag</button><button class='priority-btn'>priority</button> <button class='status-btn'>status</button></div>");
        }
    }*/