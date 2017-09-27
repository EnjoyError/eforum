var app = require('../app');
app.controller('editPasswordController', function($scope,$cookies,personalInformationService) {
    $scope.savePassword = function() {
        personalInformationService.changePassword($scope);
    };
});