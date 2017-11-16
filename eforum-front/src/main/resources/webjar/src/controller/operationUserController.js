var app = require('../app');
var $ = require('jquery');
app.controller('operationUserController', function ($scope, notificationService, userService) {
    notificationService.unRegisterCallBack("OPERATION_USER");
    notificationService.registerCallBack("OPERATION_USER", function (userId) {
        userService.userIsJy(userId, function (result) {
                $scope.userId = userId;
                if (result.data.success) {
                    if (result.data.message) {
                        $('.switch #isJY').bootstrapSwitch("state", true);
                    } else {
                        $('.switch #isJY').bootstrapSwitch("state", false);
                    }
                } else {
                    modal.showMsg(result.data.message);
                }
            }
        );
    });
    $scope.save = function () {
        var isJy = $('.switch #isJY').bootstrapSwitch("state");
        var jy = null;
        if (isJy) {
            jy = "1";

        } else {
            jy = "0";
        }
        userService.shutupOrReleaseUser($scope.userId, jy, function (result) {
            if (result.data.success) {
                modal.alert('保存成功');
                $scope.$hide();
            } else {
                modal.showMsg(result.data.message);
            }
        });
    };
});