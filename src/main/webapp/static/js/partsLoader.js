$("#leftPanel").ready(function(){
  if(window.sessionStorage.getItem("token")) {
    $("#leftPanel").load("static/load-pages/taskFilter.html");
  }
});