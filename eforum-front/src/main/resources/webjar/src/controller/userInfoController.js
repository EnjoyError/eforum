var app = require('../app');
var $ = require('jquery');
app.controller('userInfoController', function ($scope,notificationService) {
    $scope.openUserInfoPanel = function ($tooltip) {
        var userId = $tooltip.$scope.$parent.$parent.userInfo.id;
        $('.switch input').bootstrapSwitch({
            size : 'mini',
            onColor : 'success',
            onText : '是',
            offText : '否'
        });
        notificationService.pubNotification("OPERATION_USER", userId);
    };

    $scope.openMessagePanel = function ($tooltip) {
        var userId = $tooltip.$scope.$parent.$parent.userInfo.id;
        notificationService.pubNotification("OPEN_MESSAGE_PANEL", userId);
    };
});