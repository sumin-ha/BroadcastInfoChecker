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
        $('#link-menuInfoManualRegister').on('click', function() {
            _this.linkMenuInfoManualRegister();
        }),
        $('#btn-menu-manual-register').on('click', function() {
            _this.manualRegister();
        });
    },

    linkMenuInfoRegister : function () {
        location.href = '/menuInfoRegister';
    },

    linkMenuInfoCheck : function () {
        location.href = '/menuInfoCheck';
    },

    linkMenuInfoManualRegister : function () {
        location.href = '/menuInfoManualRegister';
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
            var checkVal = $(this).val();

            let user = {
                broadcastTitle : $('#broadcastTitle'+checkVal).val(),
                broadcastContext : $('#broadcastContext'+checkVal).val(),
                broadcastTag : $('#broadcastTag'+checkVal).val(),
                broadcastDate : $('#broadcastDate'+checkVal).val(),
                tweetAccount : $('#account'+checkVal).val(),
                source : "https://twitter.com/" + $('#account'+checkVal).val() + "/status/" + checkVal
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
            alert('태그를 제외한 항목은 필수 항목입니다.');
        });
    },

    infoCheckUpdate: function () {
        var data = [];
        $('input[type="checkbox"]:checked').each(function() {
            var checkVal = $(this).val();

            let user = {
                id : checkVal,
                broadcastTitle : $('#broadcastTitle'+checkVal).val(),
                broadcastContext : $('#broadcastContext'+checkVal).val(),
                broadcastTag : $('#broadcastTag'+checkVal).val(),
                broadcastDate : $('#broadcastDate'+checkVal).val(),
                tweetAccount : $('#account'+checkVal).val(),
                source : $('#url'+checkVal).val()
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
            alert('태그를 제외한 항목은 필수 항목입니다.');
        });
    },

    infoCheckDelete: function () {
        var data = [];
        $('input[type="checkbox"]:checked').each(function() {
            var checkVal = $(this).val();
            data.push(checkVal);
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

    manualRegister : function () {
        var data = [];

        let user = {
            broadcastTitle : $('#broadcastTitle').val(),
            broadcastContext : $('#broadcastContext').val(),
            broadcastTag : $('#broadcastTag').val(),
            broadcastDate : $('#broadcastDate').val(),
            tweetAccount : $('#account').val(),
            source : $('#url').val()
        };
        data.push(user);

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
            alert('정제 된 정보가 수동 등록 되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert('태그를 제외한 항목은 필수 항목입니다.');
        });
    }
}

main.init();