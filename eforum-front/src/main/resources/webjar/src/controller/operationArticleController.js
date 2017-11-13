var app = require('../app');
app.controller('operationArticleController', function ($scope, operationArticleService) {
    operationArticleService.registerOperationArticleCallBack($scope);
    $scope.save = function () {
        operationArticleService.updateArticle($scope);
    }
});