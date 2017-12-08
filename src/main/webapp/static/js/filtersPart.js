var _draggable = $.fn.draggable;

Object.defineProperty($.fn, 'draggable', {
  value: _draggable,
  enumerable: false,
  writable: false,
  configurable: false
});

function boxMessage() {
  var d = new Date();
  var strDate = d.getFullYear() + "/" + (d.getMonth() + 1) + "/"
      + (d.getDate());
  // console.log("Load succes");
}


var tagLoader = function (param, success, error) {
  $.ajax({
    url: 'api/tags?projectId=' + selectedProjectId,
    type: 'GET',
    contentType: 'application/json',
    dataType: 'json',
    success: function (data, jqXHR) {
      var items = $.map(data, function (item, index) {
        return {
          id: item.id,
          name: item.name
        };
      });
      success(items);
    },
    error: function () {
      error.apply(this, arguments);
      console.log("tags not loaded some error");
    }
  });
}
