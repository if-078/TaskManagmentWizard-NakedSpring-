
// ON CLICK - SELECT TIME - ALL
$('#tmw-time-all-btn').click(function () {
    $('#tmw-info-selected-time').html('Selected Time : All');
    state.dateFrom = 0;
    state.dateTo = 0;
    showDataOnCalendarAndTable();
});

// ON CLICK - SELECT TIME - TODAY
$('#tmw-time-today-btn').click(function () {
    $('#tmw-info-selected-time').html('Selected Time : Today');
    var currentData = new Date();
    state.dateFrom = currentData.setHours(0, 0, 0, 0);
    state.dateTo = currentData.setHours(23, 59, 59, 999);
    showDataOnCalendarAndTable();
});

// ON CLICK - SELECT TIME - WEEK
$('#tmw-time-week-btn').click(function () {
    $('#tmw-info-selected-time').html('Selected Time : Week');
    var currentData = new Date();
    var numberDay = currentData.getDay();

    state.dateFrom = currentData.setHours(0, 0, 0, 0) - (numberDay - 1) * 24 * 60 * 60 * 1000;
    state.dateTo = currentData.setHours(23, 59, 59, 999) + (8 - numberDay) * 24 * 60 * 60 * 1000;
    showDataOnCalendarAndTable();
});

// ON CLICK - SELECT TIME - CUSTOM
$('.datepicker').datepicker({
    autoclose: true
});

$('#tmw-time-btn-group > button').click(function (e) {
    e.preventDefault();
    $('#tmw-time-btn-group > button, #tmw-time-custom-btn').removeClass('active');
    $(this).addClass('active');
});

$('#tmw-time-custom-apply-btn').click(function (e) {
    e.preventDefault();

    if (!$('#tmw-time-custom-from').val() || !$('#tmw-time-custom-to').val()) {
        return;
    }

    state.dateFrom = Date.parse($('#tmw-time-custom-from').val());
    state.dateTo = Date.parse($('#tmw-time-custom-to').val()) + 24 * 60 * 60 * 1000 - 1;

    $('#tmw-time-btn-group > button, #tmw-time-custom-btn').removeClass('active');
    $('#tmw-time-custom-btn').addClass('active');
    showDataOnCalendarAndTable();
});

$('#tmw-time-custom-cancel-btn').click(function (e) {

});



//ON CLICK APPLY FILTERS --> STATUS, PRIORITY, TAG
$('#tmw-apply-btn').click(function () {
    if (!$("#statusBox").val() == "") {
        state.status = $("#statusBox").val().split(",");
    } else state.status = [];
    if (!$("#priorityBox").val() == "") {
        state.priority = $("#priorityBox").val().split(",");
    } else state.priority = [];
    if (!$("#tagBox").val() == "") {
        state.tag = $("#tagBox").val().split(",");
    } else state.tag = [];

    showDataOnCalendarAndTable();
});

// ON CLICK RESET FILTERS --> STATUS, PRIORITY, TAG
$('#tmw-reset-btn').click(function () {
    state.status = [];
    state.priority = [];
    state.tag = [];
    $("#statusBox, #priorityBox, #tagBox").combobox('clear');

    showDataOnCalendarAndTable();
});
