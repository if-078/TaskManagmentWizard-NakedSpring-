
function setFilerValues() {
    var myFilter = new Filter($("#start").val(), $("#end").val());
    myFilter.setPriority($("#priorityBox").val().split(","));
    myFilter.setStatus($("#statusBox").val().split(","));
    myFilter.setTag($("#tagBox").val().split(","));
    alert('action');
    console.log(JSON.stringify(myFilter));
    return myFilter
}

function boxMessage() {
    var d = new Date();
    var strDate = d.getFullYear() + "/" + (d.getMonth()+1) + "/" + (d.getDate());
    console.log(strDate + " " + $(this).attr('id')+" box data loaded");
}