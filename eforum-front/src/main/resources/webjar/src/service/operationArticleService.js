var app = require('../app');
var $ = require('jquery');
app.service('operationArticleService', function($http,notificationService,articleService) {

    /**
     * 注册点击编辑帖子的回调函数
     * @param $scope
     */
    this.registerOperationArticleCallBack = function($scope){
        if(!notificationService.isAlreadyRegisterCallBack("OPERATION_ARTICLE")){
            notificationService.registerCallBack("OPERATION_ARTICLE", function(articleId){
                articleService.getArticleById(articleId, function(result){
                    $scope.isJH = result.data.message.isEssence;
                    if(result.data.message && result.data.message.topGrade  > 0){
                        $scope.isZD = true;
                    } else {
                        $scope.isZD = false;
                    }
                    $scope.article = result.data.message;
                });
            });
        }
    };

    /**
     * 保存帖子
     * @param $scope
     */
    this.updateArticle = function($scope){
        var state = $('#isJH').bootstrapSwitch('state');
        var article = {
            id: $scope.article.id
        };
        if ($scope.isZD) {
            article.topGrade = 1;
        } else {
            article.topGrade = 0;
        }
        article.isEssence = $scope.isJH;
        articleService.update(article);
    };
});