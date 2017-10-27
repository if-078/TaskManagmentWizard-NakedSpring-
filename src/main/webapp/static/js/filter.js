
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

function setFilerValues() {
    var myFilter = new Filter($("#start").val(), $("#end").val());
    myFilter.setPriority($("#priorityBox").val().split(","));
    myFilter.setStatus($("#statusBox").val().split(","));
    myFilter.setTag($("#tagBox").val().split(","));
    alert('action');
    console.log(JSON.stringify(myFilter));
    return myFilter
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
