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
});