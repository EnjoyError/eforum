var app = require('../app');

app.controller('editMessageController', function ($scope, notificationService, messageService) {
    if (!notificationService.isAlreadyRegisterCallBack("OPEN_MESSAGE_PANEL")) {
        notificationService.registerCallBack("OPEN_MESSAGE_PANEL", function (userId) {
            $scope.userId = userId;
        });
    }
    $scope.send = function () {
        messageService.send($scope.message, $scope.userId, function (result) {
            if (result.data.success) {
                modal.alert("发送成功");
                $scope.$hide();
            } else {
                modal.showMsg(result.data.message);
            }
        });
    };
});