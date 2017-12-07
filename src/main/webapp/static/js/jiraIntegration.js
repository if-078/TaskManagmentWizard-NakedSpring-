var taskIdByJiraKey;
var projectIdByJiraKey;

$('#jira-integration').on('click', function () {
    console.log('wqwqwqwqwqwq');
    $('#jira-modal-authorization').modal('show');


    $('#jira-ok').on('click', function () {
        /*var jLink = $('#jira-link').val();
        var jUsername = $('#jira-login').val();
        var jPassword = $('#jira-password').val();*/
        var jLink = "tmw-sveta.atlassian.net";
        var jUsername = "ovcharuksveta@ukr.net";
        var jPassword = "svetasveta";
        var jiraToken = jUsername + ":" + jPassword;
        var encode = btoa(jiraToken);
        var jUrl = "https://" + jLink + "/rest/api/2/project";
        var projectsModelData = [];
        $.ajax({
            url: 'api/jira/get-projects',
            data: JSON.stringify({
                "url": jUrl,
                "creds": encode
            }),
            type: 'POST',
            contentType: 'application/json',
            headers: createAuthToken(),
            success: function (data) {
                projectsModelData=data;
                $.each(data, function () {
                    var ul = document.getElementById("jira-projects-list");
                    var li = document.createElement("li");
                    var a = document.createElement("a");
                    a.setAttribute('class', 'a-class');
                    a.setAttribute('id', 'a-element-' + b);
                    a.appendChild(document.createTextNode(data[b].name));
                    li.appendChild(a);
                    ul.appendChild(li);
                    keys[b] = data[b].key;
                    b++;
                });
                $(".a-class").on('click', function (event) {
                    chooseProject = this.textContent;
                    key = (this.id).replace(/^\D+/g, '');
                    chooseProject = projectsModelData[key];
                    $("#choose-project-text").html("You will import project: " + chooseProject.name + " with key " + chooseProject.key);
                });
            },
            cache: false,
            fail: (function ($xhr) {
                console.log("task DON`T CREATED");
            })
        });

        $('#jira-modal-authorization').modal('hide');
        $('#jira-modal-choose-project').modal('show');


        var chooseProject;
        var keys = [];
        var key;
        var b = 0;
        var c = 0;
        $('.a-class').each(function () {
            console.log(c);
            c++;
        });


        $('#jira-project-ok').on('click', function () {
            console.log(chooseProject.name);
            console.log(chooseProject.key);

            var urlGetIssues = "https://" + jLink + "/rest/api/2/search?jql=project=" + chooseProject.key;

            $.ajax({
                url: 'api/jira/get-issues',
                data: JSON.stringify({
                    "url": urlGetIssues,
                    "creds": encode,
                    "projectKey": chooseProject.key
                }),
                type: 'POST',
                contentType: 'application/json',
                headers: createAuthToken(),
/*<<<<<<< HEAD
                success: function (data) {
                    var project = {
                        "name": chooseProject.name,
                        "jiraKey": chooseProject.key,
                        "parentId": 0
                    };
                    //console.log(data);
                    console.log(project);

                    createJiraTask(project);
                    //console.log(data);
=======*/

                success: function () {

                        $.ajax({
                            url: 'api/tasks/jira-import',
                            data: JSON.stringify(chooseProject),
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


                },
                cache: false
            }).fail(function ($xhr) {
                console.log("issues don`t get");
            });
            /*var project = {
                "name": chooseProject,
                "jiraKey": key,
                "parentId":0
            };*/




        })


        /* console.log(encode);



        /* $.ajax({
             //url: "https://sveta-site.atlassian.net/rest/auth/1/session",
             //url: "https://sveta-site.atlassian.net/rest/api/latest/issue/FP-3",
             //url: "https://http://ssu-jira.softserveinc.com/rest/api/2/issue/TTT-3",
             //headers: JSON.stringify(jiraData),
             */
        /*headers: {
                        'Access-Control-Allow-Origin': "https://tmw-sveta.atlassian.net",
                        'Access-Control-Allow-Headers': "Origin, X-Requested-With, Content-Type, Accept",
                        'Authorization': 'Basic ' + encode
                    },*/
        /*
                    url: jUrl,
                    //Origin: 'https://tmw-sveta.atlassian.net',
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
                        var chooseProject;
                        var keys = {};
                        var key;

                        var b = 0;
                        $.each(data.key, function() {
                            var ul = document.getElementById("jira-projects-list");
                            var li = document.createElement("li");
                            var a = document.createElement("a");
                            a.setAttribute('class', 'a-class')
                            a.setAttribute('id', 'a-element' + b);
                            a.appendChild(document.createTextNode(data.name));
                            li.appendChild(a);
                            ul.appendChild(li);
                            keys[b] = data.key;
                            b++;
                        });

                        b = 0;
                        $('.a-class').each(function() {
                            $('#a-element'+b).on('click', function () {
                                console.log(this.textContent);
                                chooseProject = this.textContent;
                                key = keys[b];
                                var div = document.getElementById("choose-project-text");
                                div.appendChild(document.createTextNode("You will import project: " + this.textContent));
                            });
                            b++;
                        });

                        $('#jira-project-ok').on('click', function () {
                            var project = {
                                "name": chooseProject,
                                "jiraKey": key
                            };
                            createJiraTask(project);
                            getProjectId(key);

                            var urlGetIssues = "https://" + jLink + "/rest/api/2/search?jql=project=" + key;

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

                                    $.each(data.issues, function() {
                                        if (this.fields.parent == null) {
                                            var assignTo;
                                            if ((this.fields.assignee.emailAddress == jUsername) || (this.fields.assignee.key = jUsername)) {
                                                assignTo = userId;
                                            } else {
                                                assignTo = null;
                                            }
                                            task = {
                                                "name": this.fields.summary,
                                                */
        /*"createdDate": this.fields.created,
                                                //"startDate": ,
                                                "endDate": this.fields.duedate,
                                                "estimateTime": this.fields.aggregatetimeestimate,*/
        /*
                                                "assignTo": assignTo,
                                                "statusId": this.fields.status.statusCategory.id,
                                                "priorityId": this.fields.priority.id,
                                                "parentId": projectIdByJiraKey,
                                                //"tags":
                                                "jiraKey": this.key
                                            };

                                            createJiraTask(task);
                                            getTaskId(task.jiraKey);
                                            console.log(taskIdByJiraKey);


                                            if (this.subtasks != null) {
                                                $.each(data.issues.subtasks, function() {
                                                    subtask = {
                                                        "name": this.fields.summary,
                                                        */
        /*"createdDate": this.fields.created,
                                                        //"startDate": ,
                                                        "endDate": this.fields.duedate,
                                                        "estimateTime": this.fields.aggregatetimeestimate,
                                                        "assignTo": this.fields.assignee,*/
        /*
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



                                        $.ajax({
                                            url: "https://" + jLink + "/rest/api/2/issue/" + this.key,
                                            type: "GET",
                                            data: JSON.stringify(),
                                            dataType: "json",
                                            beforeSend: function(xhr, settings) { xhr.setRequestHeader('Authorization','Basic ' + encode); },
                                            contentType: "application/json",
                                            success: function (data) {
                                                if (data.comment.comments != null) {
                                                    $.each(data.comment.comments, function() {
                                                        if (this.author.key == jUsername || this.author.email == jUsername) {
                                                            var commentJira = {
                                                                "comment_text": this.body,
                                                                "created_date": this.created,
                                                                "task_id": taskIdByJiraKey,
                                                                "user_id": userId
                                                            };

                                                            createJiraCommente(commentJira);

                                                        }
                                                    })
                                                }

                                                */
        /*$each(data.labels, function() {
                                                    if (this != null) {

                                                    }
                                                })*/
        /*
                                            },
                                            error: function() {
                                                console.log("error while get issue to add comment")
                                            }
                                        });
                                    });
                                },
                                error: function() {
                                    console.log("error while get project issues")
                                }
                            });
                        })
                    },
                    error: function () {
                        console.log("error login to jira")
                    }
                });*/
    })
});


function createJiraTask(task) {
    $.ajax({
        url: 'api/tasks/jira-import',
        data: JSON.stringify(task),
        //data: JSON.parse(task),
        //data: task,
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

function getProjectId(key) {
    $.ajax({
        url: 'api/tasks/id/' + key,
        type: 'GET',
        contentType: 'application/json',
        headers: createAuthToken(),

        success: function (data, textStatus, jqXHR) {
            /*var token = jqXHR.getResponseHeader('Authentication');
            window.sessionStorage.setItem("token", token);*/
            projectIdByJiraKey = data;
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

function createJiraComment(comment) {
    $.ajax({
        url: 'api/comment',
        data: JSON.stringify(comment),
        type: 'POST',
        contentType: 'application/json',
        headers: createAuthToken(),
        success: function (data) {
            console.log("Comment ADDED");
        },
        cache: false
    }).fail(function ($xhr) {
        console.log("comment DON`T ADDED");
    });

}