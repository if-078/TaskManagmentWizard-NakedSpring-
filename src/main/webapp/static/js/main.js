var datatoken = window.sessionStorage.getItem("token");
if (datatoken != undefined | datatoken != null) {
    $("#main").show();
    $("#user-login").text("Hello, " + window.sessionStorage.getItem("name"));
    $("#logout").show();
    $("")
} else {
    $("#main").hide();
    $("#login").show();

}

function createAuthToken() {
    var token = window.sessionStorage.getItem("token");
    if (token) {
        return {
            "Authentication": token
        }
    } else {
        return {}
    }
}



var userId = window.sessionStorage.getItem('id');
var userName = window.sessionStorage.getItem('name');
var selectedTaskId;
var selectedTaskText;

// STATE OF APPLIED FILTERS
var state = {
    projectId: 0,
    parentId: 0,
    dateFrom: 0,
    dateTo: 0,
    status: [],
    priority: [],
    tag: [],
    comments: []
};

