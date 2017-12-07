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
var selectedProjectId = 0;
var selectedTaskId = 0;
var selectedTaskText = '';
var firstManagerId = 0;
var secondaryManagerId = 0;
var currentEditTaskId = 0;

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


