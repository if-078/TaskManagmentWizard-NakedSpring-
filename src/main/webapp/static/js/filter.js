function myformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}

function Filter(startDate, endDate) {
    this.startDate=startDate;
    this.endDate=endDate;
};

Filter.prototype = {
    setStatus : function (status) {
        this.status=status;
    },
    setPriority : function (priority) {
        this.priority=priority;
    },
    setTag : function (tag) {
        this.tag=tag;
    }
}


$("#filterButton").on("click", function () {
    sendFilterOptions(setFilerValues());
});

$("#cleanButton").on("click", function () {
    clearFilterBlocks();
});


function resetForm($form) {

    $form.find('input:text, input:password, input:span, select, textarea, a').val('');
    $form.find('input:radio, input:checkbox')
        .removeAttr('checked').removeAttr('selected');
}

function clearFilterBlocks(){
    $("#statusBox, #priorityBox, #tagBox").combobox('clear');
    $("#start, #end").datebox('clear');
}

function sendFilterOptions(filter){
    $.post('users/1/filter', JSON.stringify(filter))
        .done(function () {
            console.log('filter sended');
        })
        .fail(function () {
            console.log('filter not sended');
        })
    ;
}

function setFilerValues() {
    var myFilter = new Filter($("#start").val(), $("#end").val());
    myFilter.setPriority($("#priorityBox").val().split(","));
    myFilter.setStatus($("#statusBox").val().split(","));
    myFilter.setTag($("#tagBox").val().split(","));
    alert('action');
    console.log(JSON.stringify(myFilter));
    return myFilter
}