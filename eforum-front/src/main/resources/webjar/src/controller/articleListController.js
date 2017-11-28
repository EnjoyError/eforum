var app = require('../app');
var $ = require('jquery');

app.controller('articleListController', function ($scope, $sce, $location, articleService, userService, notificationService, globalService) {
    $scope.viewArticle = function (id) {
        globalService.saveCurrentListPageNum($scope.paginationConf.currentPage);
        globalService.savePageSize($scope.paginationConf.itemsPerPage);
        $location.path('/article').search({
            id: id
        });
    };

    notificationService.unRegisterCallBack("REFRESH_ARTICLE_LIST");
    notificationService.registerCallBack("REFRESH_ARTICLE_LIST", function () {
        getArticleList();
    });

    function getArticleList() {
        var pageIndex = $scope.paginationConf.currentPage;
        var pageSize = $scope.paginationConf.itemsPerPage;

        articleService.listArticle(pageIndex, pageSize, function (result) {
            if (result.data.success) {
                var message = result.data.message;
                // 配置分页基本参数
                $scope.paginationConf.currentPage = message.pageIndex;
                $scope.paginationConf.totalItems = message.dataCount;
                $scope.paginationConf.itemsPerPage = message.pageSize;
                $scope.paginationConf.pagesLength = message.pageCount;
                $scope.articleList = articleService.parseArticleList(message);
            } else {
                modal.showMsg(result.data.message);
            }
        });
    }

    // 配置分页基本参数
    $scope.paginationConf = {
        currentPage: globalService.getCurrentListPageNum(),
        itemsPerPage: globalService.getPageSize(),
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            getArticleList();
        }
    };

    //首次触发查询
    getArticleList();


    $scope.writeUserInfoToDataContent = function ($tooltip) {
        var userId = $tooltip.$scope.$parent.$parent.x.createUserId;
        var articleId = $tooltip.$scope.$parent.$parent.x.id;
        userService.findUser(userId, function (result) {
            if (result.data.success) {
                var message = result.data.message;
                $tooltip.$scope.userInfo = message;
            } else {
                modal.showMsg(result.data.message);
            }
        });
    };

    $scope.openArticleManagerPanel = function ($modal) {
        $('.switch input').bootstrapSwitch({
            size: 'mini',
            onColor: 'success',
            onText: '是',
            offText: '否'
        });
        notificationService.pubNotification("OPERATION_ARTICLE", $modal.$id);
    };
});