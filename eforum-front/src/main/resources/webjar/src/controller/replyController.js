var app = require('../app');
var $ = require('jquery');
app.controller('replyController', function ($scope, $sce, $window, $routeParams, replyService, articleService, userService) {
    $scope.commitReply = function () {
        var replyContent = $('#summernote').summernote('code');
        var articleId = $scope.$parent.articleId;
        replyService.commitReply(articleId, replyContent, function (result) {
            if (result.data.success) {
                modal.alert("回复成功");
                $('#summernote').summernote('code', "");
                refreshReply($scope, $sce);
            } else {
                modal.showMsg(result.data.message);
            }
        });
    }

    var refreshReply = function () {
        var pageNum = $scope.paginationConf.currentPage;
        var pageSize = $scope.paginationConf.itemsPerPage;
        replyService.getReplyByArticleId($scope.$parent.articleId, pageNum, pageSize, function (result) {
            if (result.data.success) {
                var message = result.data.message;
                $scope.replys = result.data.message.data;
                $scope.paginationConf.currentPage = message.pageIndex;
                $scope.paginationConf.totalItems = message.dataCount;
                $scope.paginationConf.itemsPerPage = message.pageSize;
                $scope.paginationConf.pagesLength = message.pageCount;
                angular.forEach($scope.replys, function (reply) {
                    reply.content = $sce.trustAsHtml(reply.content);
                });
            } else {
                modal.showMsg(result.data.message);
            }
        });
    };

    // 配置分页基本参数
    $scope.paginationConf = {
        currentPage: 1,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            refreshReply();
        }
    };

    refreshReply();


    $('#summernote').summernote({
        lang: 'zh-CN',
        placeholder: '在这里输入你的回复',
        minHeight: 100,
        dialogsFade: true,
        dialogsInBody: true,
        callbacks: {
            onImageUpload: sendFile
        }
    });

    function sendFile(files) {
        articleService.uploadImages(files, function (result) {
            if (result.success) {
                var imageUrls = result.message;
                for (i in imageUrls) {
                    $('#summernote').summernote('insertImage',
                        imageUrls[i], 'img');
                }
            } else {
                console.log(result.message);
            }
        }, function (result) {
            modal.showMsg("请求失败 " + result);
        });
    };
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

});