var server = {
  init : function () {
    var _this = this;
    $('#btn-delete').on('click', function () {
      _this.delete();
    });
    $('#btn-create').on('click', function () {
      _this.create();
    });
    $('#btn-status').on('click', function () {
      _this.toggle_status();
    });
    $('#flavor').on('change', function (e) {
      _this.show_id(e, "flavorId");
    });
    $('#image').on('change', function (e) {
      _this.show_id(e, "imageId");
    });
    $('#network').on('change', function (e) {
      _this.show_id(e, "networkId");
    });

  },
  create : function () {
    var data = {
      name: $('#name').val(),
      flavor: $('#flavor').val(),
      image: $('#image').val(),
      network: $('#network').val(),

    };
    $.ajax({
      type: 'POST',
      url: '/server',
      dataType: 'json',
      contentType:'application/json; charset=utf-8',
      data: JSON.stringify(data)
    }).done(function() {
      alert('서버가 생성되었습니다.');
      window.location.href = '/servers';
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },
  delete : function () {
    var data = {
      serverId: $('#serverId').val(),
    };
    $.ajax({
      type: 'DELETE',
      url: '/server',
      dataType: 'json',
      contentType:'application/json; charset=utf-8',
      data: JSON.stringify(data)
    }).done(function() {
      alert('서버가 삭제되었습니다.');
      window.location.href = '/servers';
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },
  toggle_status : function () {
    var action = $('#status').val() == "ACTIVE" ? "STOP" : "START"
    var data = {
      serverId: $('#serverId').val(),
      action: action
    };
    $.ajax({
      type: 'PUT',
      url: '/server',
      dataType: 'json',
      contentType:'application/json; charset=utf-8',
      data: JSON.stringify(data)
    }).done(function() {
      if ($('#status').val() == "ACTIVE"){
        alert('서버가 중지되었습니다.');
      }
      else{
        alert('서버가 시작되었습니다.');
      }
      window.location.href = '/servers';
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },
  show_id : function (e, inputId) {
    $('#'+inputId).val("ID : " + e.target.value)
  }

};

server.init();