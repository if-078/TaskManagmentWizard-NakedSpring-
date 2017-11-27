if(window.sessionStorage.getItem("token")) {
    $.ajaxSetup({
        headers: createAuthToken()
    });

    $("#leftPanel").load("static/load-pages/statusFilter.html");
}
