var app = require('../app');
var $ = require('jquery');
app.service('operationUserService', function($http,notificationService,articleService, $route) {

    /**
     * 注册点击编辑帖子的回调函数
     * @param $scope
     */
    this.registerOperationArticleCallBack = function ($scope) {
        notificationService.unRegisterCallBack("OPERATION_ARTICLE");    //防止回调函数里$scope闭包的问题，所以每次都先注销再重新注册
        notificationService.registerCallBack("OPERATION_ARTICLE", function (articleId) {
            articleService.getArticleById(articleId, function (result) {
                $scope.isJH = result.data.message.isEssence;
                if (result.data.message && result.data.message.topGrade > 0) {
                    $scope.isZD = true;
                } else {
                    $scope.isZD = false;
                }
                $scope.article = result.data.message;
                $('.switch #isJH').bootstrapSwitch("state",$scope.isJH);
                $('.switch #isZD').bootstrapSwitch("state",$scope.isZD);
            });
        });
    };

    /**
     * 保存帖子
     * @param $scope
     */
    this.updateArticle = function($scope) {
        $scope.isJH = $('.switch #isJH').bootstrapSwitch('state');
        $scope.isZD = $('.switch #isZD').bootstrapSwitch('state');
        var article = {
            id: $scope.article.id
        };
        if ($scope.isZD) {
            article.topGrade = 1;
        } else {
            article.topGrade = 0;
        }
        article.isEssence = $scope.isJH;
        articleService.update(article, function(result) {
            if (result.data.success) {
                modal.alert('保存成功');
                notificationService.pubNotification("REFRESH_ARTICLE_LIST");
                $scope.$hide();
                //$route.reload();
            } else {
                modal.showMsg(result.data.message);
            }
        });
    };
});