var main = {
    init : function() {
        var _this = this;
        $('#link-menuInfoRegister').on('click', function() {
            _this.linkMenuInfoRegister();
        }),
        $('#btn-menu-info-register').on('click', function() {
            _this.infoRegisterSave();
        }),
        $('#link-menuInfoGetList').on('click', function() {
            _this.infoGetList();
        }),
        $('#btn-menu-info-get-List').on('click', function() {
            _this.infoGetListRegister();
        }),
        $('#btn-menuInfoCheck').on('click', function() {
            _this.update();
        }),
        $('#btn-menuInfoRegister').on('click', function() {
            _this.update();
        });
    },

    linkMenuInfoRegister : function () {
        location.href = '/menuInfoRegister';
    },

    infoRegisterSave : function () {
        var data = {
            twitterAccount : $('#twitterAccount').val(),
            keyword : $('#keyword').val()
        };

        $.ajax({
            type:'POST',
            url:'/api/account/register',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data) {
            alert('정보가 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    infoGetList : function () {
        $.ajax({
            type:'POST',
            url:'/api/get/info',
            dataType:'json',
            contentType:'application/json; charset=utf-8'
        }).done(function(data) {
            alert('정보가 추출되었습니다.');
            window.location.href = '/menuInfoGetList';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    infoGetListRegister: function () {


//        $.ajax({
//            type:'POST',
//            url:'/api/get/info',
//            dataType:'json',
//            contentType:'application/json; charset=utf-8'
//        }).done(function(data) {
//            alert('정보가 추출되었습니다.');
//            window.location.href = '/menuInfoGetList';
//        }).fail(function (error) {
//            alert(JSON.stringify(error));
//        });
    },

    delete : function () {

    }
}

main.init();