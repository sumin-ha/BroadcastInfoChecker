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
        $('#link-menuInfoCheck').on('click', function() {
            _this.linkMenuInfoCheck();
        }),
        $('#btn-menu-info-check-update').on('click', function() {
            _this.infoCheckUpdate();
        }),
        $('#btn-menu-info-check-delete').on('click', function() {
            _this.infoCheckDelete();
        }),


        $('#btn-menuInfoRegister').on('click', function() {
            _this.update();
        });
    },

    linkMenuInfoRegister : function () {
        location.href = '/menuInfoRegister';
    },

    linkMenuInfoCheck : function () {
        location.href = '/menuInfoCheck';
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
        var data = [];
        $('input[type="checkbox"]:checked').each(function() {
            var CheckVal = $(this).val();

            let user = {
                broadcastTitle : $('#broadcastTitle'+CheckVal).val(),
                broadcastContext : $('#broadcastContext'+CheckVal).val(),
                broadcastTag : $('#broadcastTag'+CheckVal).val(),
                broadcastDate : $('#broadcastDate'+CheckVal).val(),
                tweetAccount : $('#account'+CheckVal).val(),
                source : CheckVal
            };
            data.push(user);
        });

        if(data.length == 0) {
           alert('선택 된 정보가 없습니다.');
           return;
        }

        $.ajax({
            type:'POST',
            url:'/api/info/register',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data) {
            alert('정제 된 정보가 등록 되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    infoCheckUpdate: function () {
        var data = [];
        $('input[type="checkbox"]:checked').each(function() {
            var CheckVal = $(this).val();

            let user = {
                id : $('#id'+CheckVal).val(),
                broadcastTitle : $('#broadcastTitle'+CheckVal).val(),
                broadcastContext : $('#broadcastContext'+CheckVal).val(),
                broadcastTag : $('#broadcastTag'+CheckVal).val(),
                broadcastDate : $('#broadcastDate'+CheckVal).val(),
                tweetAccount : $('#account'+CheckVal).val(),
                source : CheckVal
            };
            data.push(user);
        });

        if(data.length == 0) {
           alert('선택 된 정보가 없습니다.');
           return;
        }

        $.ajax({
            type:'POST',
            url:'/api/info/update',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data) {
            alert('정제 된 정보가 수정 되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    infoCheckDelete: function () {
        var data = [];
        $('input[type="checkbox"]:checked').each(function() {
            var CheckVal = $(this).val();
            var id = $('#id'+CheckVal).val();
            data.push(id);
        });

        if(data.length == 0) {
           alert('선택 된 정보가 없습니다.');
           return;
        }

        $.ajax({
            type:'POST',
            url:'/api/info/delete',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(data) {
            alert('정제 된 정보가 삭제 되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function () {

    }
}

main.init();