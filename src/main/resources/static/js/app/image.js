var image = {
  init : function () {
    var _this = this;
    $('#btn-update').on('click', function () {
      _this.update();
    });
    $('#btn-delete').on('click', function () {
      _this.delete();
    });
  },

  update : function () {
    var data = {
      imageId: $('#image-id').val(),
      name: $('#name').val(),
      description: $('#description').val(),

    };
    $.ajax({
      type: 'PUT',
      url: '/v2/images',
      dataType: 'json',
      contentType:'application/json; charset=utf-8',
      data: JSON.stringify(data)
    }).done(function() {
      alert('이미지가 수정되었습니다.');
      window.location.href = '/v2/images';
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },

  delete : function () {
    var data = {
      imageId: $('#image-id').val(),
    };
    $.ajax({
      type: 'DELETE',
      url: '/v2/images',
      dataType: 'json',
      contentType:'application/json; charset=utf-8',
      data: JSON.stringify(data)
    }).done(function() {
      alert('이미지가 삭제되었습니다.');
      window.location.href = '/v2/images';
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  }

};

image.init();