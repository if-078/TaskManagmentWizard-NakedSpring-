/*$('#jira-integration').on('click', function () {
    console.log('wqwqwqwqwqwq');
    $('#jira-modal-authorization').modal('show');


    $('#jira-ok').on('click', function () {
        var jLink = $('#jira-link').val();
        var jUsername = $('#jira-login').val();
        var jPassword = $('#jira-password').val();
        console.log(jUsername);
        console.log(jPassword);

        var jiraToken = jUsername + ":" + jPassword;

        var jUrl = "https://" + jLink + "/rest/api/2/" + "issue/FP-1";
        //var jUrl = "https://" + jLink + "/rest/api/2/" + "search?jql=project=SP&maxResults=20";
        //var jUrl = "https://" + jLink + "/rest/auth/1/session";

        //var str = "ovochevarka@gmail.com:ovochevarka1996";

        var encode = btoa(jiraToken);
        console.log(encode);

        $.ajax({
            //url: "https://sveta-site.atlassian.net/rest/auth/1/session",
            //url: "https://sveta-site.atlassian.net/rest/api/latest/issue/FP-3",
            //url: "https://http://ssu-jira.softserveinc.com/rest/api/2/issue/TTT-3",
            //headers: JSON.stringify(jiraData),
            url: jUrl,
            crossDomain: true,
            type: "GET",
            data: JSON.stringify(),
            contentType: "application/json",
            beforeSend: function(xhr, settings) { xhr.setRequestHeader('Authorization','Basic ' + encode); },
            dataType: "json",
            success: function (data) {
                //$('#jira-modal').modal('hide');
                $('#jira-modal-authorization').modal('hide');
                $('#jira-modal-choose-project').modal('show');
                console.log("login to jira");
                console.log(data);

                $('#jira-project-ok').on('click', function () {
                var key = $('#jira-project-key').val();
                var urlGetIssues = "https://" + jLink + "/rest/api/2/search?jql=project=" + key + "&maxResults=20";
                    $.ajax({
                        url: urlGetIssues,
                        type: "GET",
                        data: JSON.stringify(),
                        dataType: "json",
                        beforeSend: function(xhr, settings) { xhr.setRequestHeader('Authorization','Basic ' + encode); },
                        contentType: "application/json",
                        success: function (data) {
                            console.log("yes");
                            console.log(data);
                        },
                        error: function() {
                            console.log("no")
                        }
                    });
                })
            },
            error: function () {
                console.log("error login to jira")
            }
        });
    })
});*/


var taskIdByJiraKey;

$('#jira-integration').on('click', function () {
    console.log('wqwqwqwqwqwq');
    $('#jira-modal-authorization').modal('show');


    $('#jira-ok').on('click', function () {
        var jLink = $('#jira-link').val();
        var jUsername = $('#jira-login').val();
        var jPassword = $('#jira-password').val();
        console.log(jUsername);
        console.log(jPassword);

        var jiraToken = jUsername + ":" + jPassword;

        //var jUrl = "https://" + jLink + "/rest/api/2/" + "issue/FP-1";
        var jUrl = "https://" + jLink + "/rest/api/2/" + "search?jql=project=FP&maxResults=20";
        //var jUrl = "https://" + jLink + "/rest/auth/1/session";

        //var str = "ovochevarka@gmail.com:ovochevarka1996";

        var encode = btoa(jiraToken);
        console.log(encode);

        $.ajax({
            //url: "https://sveta-site.atlassian.net/rest/auth/1/session",
            //url: "https://sveta-site.atlassian.net/rest/api/latest/issue/FP-3",
            //url: "https://http://ssu-jira.softserveinc.com/rest/api/2/issue/TTT-3",
            //headers: JSON.stringify(jiraData),
            url: jUrl,
            crossDomain: true,
            type: "GET",
            data: JSON.stringify(),
            contentType: "application/json",
            beforeSend: function(xhr, settings) { xhr.setRequestHeader('Authorization','Basic ' + encode); },
            dataType: "json",
            success: function (data) {
                //$('#jira-modal').modal('hide');
                $('#jira-modal-authorization').modal('hide');
                $('#jira-modal-choose-project').modal('show');
                console.log("login to jira");
                console.log(data);

                $('#jira-project-ok').on('click', function () {
                    var key = $('#jira-project-key').val();
                    var urlGetIssues = "https://" + jLink + "/rest/api/2/search?jql=project=" + key;

                    /*$.getJSON(urlGetIssues, function(data) {
                        var task = {};
                        var fields = data["issues"]["fields"];
                        for (i=0; i<fields.length; i++)
                        task = {
                            "name": fields[i]["summary"],
                            "createdDate": fields[i]["created"],
                            //"startDate": ,
                            "endDate": fields[i]["duedate"],
                            "estimateTime": fields[i]["aggregatetimeestimate"],
                            "assignTo": fields[i]["assignee"],
                            "statusId": fields[i]["status"]["statusCategory"]["id"],
                            "priorityId": fields[i]["priority"]["id"],
                            "parentId": fields[i]["parent"]["fields"]["summary"],
                            //"tags":
                            "jiraKey": data["issues"][i]["key"]
                        }


                    })*/

                    $.ajax({
                        url: urlGetIssues,
                        type: "GET",
                        data: JSON.stringify(),
                        dataType: "json",
                        beforeSend: function(xhr, settings) { xhr.setRequestHeader('Authorization','Basic ' + encode); },
                        contentType: "application/json",
                        success: function (data) {
                            console.log("yes");
                            console.log(data);
                            var task = {};
                            var subtask = {};
                            /*var fields = data["issues"]["fields"];
                            //for (i=0; i<data["total"]; i++) {
                                task = {
                                    "name": fields["summary"],
                                    "createdDate": fields["created"],
                                    //"startDate": ,
                                    "endDate": fields["duedate"],
                                    "estimateTime": fields["aggregatetimeestimate"],
                                    "assignTo": fields["assignee"],
                                    "statusId": fields["status"]["statusCategory"]["id"],
                                    "priorityId": fields["priority"]["id"],
                                    "parentId": fields["parent"]["fields"]["summary"],
                                    //"tags":
                                    "jiraKey": data["issues"]["key"]
                                };
                                createtask(task);
                            //}*/

                            //var fields = data.issues.fields;
                            $.each(data.issues, function() {
                                if (this.fields.parent == null) {
                                    var assignTo;
                                    /*if (this.fields.assignee.emailAddress == jUsername) {
                                        assignTo =
                                    }*/
                                    task = {
                                        "name": this.fields.summary,
                                        /*"createdDate": this.fields.created,
                                        //"startDate": ,
                                        "endDate": this.fields.duedate,
                                        "estimateTime": this.fields.aggregatetimeestimate,
                                        "assignTo": this.fields.assignee,*/
                                        "statusId": this.fields.status.statusCategory.id,
                                        "priorityId": this.fields.priority.id,
                                        //"parentId": this.fields.parent.fields.summary,
                                        //"tags":
                                        "jiraKey": this.key



                                    };
                                    createtask(task);
                                    getTaskId(task.jiraKey);
                                    console.log(taskIdByJiraKey);


                                    if (this.fields.subtasks != null) {
                                        $.each(data.issues.fields.subtasks, function() {
                                            subtask = {
                                                "name": this.fields.summary,
                                                /*"createdDate": this.fields.created,
                                                //"startDate": ,
                                                "endDate": this.fields.duedate,
                                                "estimateTime": this.fields.aggregatetimeestimate,
                                                "assignTo": this.fields.assignee,*/
                                                "statusId": this.fields.status.statusCategory.id,
                                                "priorityId": this.fields.priority.id,
                                                "parentId": taskIdByJiraKey,
                                                //"tags":
                                                "jiraKey": this.key
                                            };
                                        });


                                    }

                                    console.log(this);
                                    console.log(this.fields.summary);
                                    console.log(this.fields.created);
                                    //"startDate": ,
                                    console.log(this.fields.duedate)
                                    console.log(this.fields.aggregatetimeestimate)
                                    console.log(this.fields.assignee)
                                    console.log(this.fields.status.statusCategory.id)
                                    console.log(this.fields.priority.id)
                                    console.log(this.fields.parent.fields.summary)
                                    //"tags":
                                    console.log(this.key);
                                }


                            });


                            /*for (i=0; i<data.total; i++) {
                                task = {
                                    "name": fields[i].summary,
                                    "createdDate": fields[i].created,
                                    //"startDate": ,
                                    "endDate": fields[i].duedate,
                                    "estimateTime": fields[i].aggregatetimeestimate,
                                    "assignTo": fields[i].assignee,
                                    "statusId": fields[i].status.statusCategory.id,
                                    "priorityId": fields[i].priority.id,
                                    "parentId": fields[i].parent.fields.summary,
                                    //"tags":
                                    "jiraKey": data.issues[i].key
                                };
                                createtask(task);
                            }*/


                        },
                        error: function() {
                            console.log("no")
                        }
                    });
                })
            },
            error: function () {
                console.log("error login to jira")
            }
        });
    })
});


function createtask(task) {

    //taskJiraDTO = {};
    $.ajax({
        url: '/api/tasks/jira-import',
        data: JSON.stringify(task),
        type: 'POST',
        contentType: 'application/json',
        headers: createAuthToken(),
        success: function (data) {
            console.log("TASK CREATED");
        },
        cache: false
    }).fail(function ($xhr) {
        console.log("task DON`T CREATED");
    });
}

function getTaskId(key) {
    $.ajax({
        url: 'api/tasks/id/' + key,
        type: 'GET',
        contentType: 'application/json',
        headers: createAuthToken(),

        success: function (data, textStatus, jqXHR) {
            /*var token = jqXHR.getResponseHeader('Authentication');
            window.sessionStorage.setItem("token", token);*/
            taskIdByJiraKey = data;
        },
        error: function (jqXHR, textStatus, errorThrown) {
            /*if (jqXHR.status === 401) {
                resetToken();
            } else {
                throw new Error("an unexpected error occured: " + errorThrown);
            }*/
            console.log("cant`t get task id by key");
        }
    });
}
