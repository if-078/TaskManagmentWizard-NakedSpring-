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
                "creds": encode,
                "name":jUsername,
                "password":jPassword,
                "userId":window.sessionStorage.getItem("id")
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
                    "projectKey": chooseProject.key,
                    "projectName":chooseProject.name,
                    "userId": window.sessionStorage.getItem("id"),
                    "name":jUsername,
                }),
                type: 'POST',
                contentType: 'application/json',
                headers: createAuthToken(),

                success: function () {
                    $('#jira-modal-choose-project').modal('hide');
                    $('#jira-modal-success').modal('show');
                },
                cache: false
            }).fail(function ($xhr) {

                    /*var data = $xhr.responseJSON;
                    console.log($xhr.responseJSON);*/

            });
        })

    })
});
