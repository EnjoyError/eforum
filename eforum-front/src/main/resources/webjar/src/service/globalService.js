var app = require('../app');
/**
 * 用来保存一些全局信息
 */
app.service('globalService', function () {
    this.globalInfo = {};
    this.isFromArticleToList = false;

    /**
     * 获取当前List页面
     * @returns {*}
     */
    this.getCurrentListPageNum = function () {
        if (this.globalInfo.currentListPage && this.isFromArticleToList) {
            return this.globalInfo.currentListPage;
        } else {
            return 1;
        }
    };

    /**
     * 保存当前指定的页面
     * @param pageNum
     */
    this.saveCurrentListPageNum = function (pageNum) {
        this.globalInfo.currentListPage = pageNum;
    };

    /**
     * 保存页面大小
     * @param pageSize
     */
    this.savePageSize = function (pageSize) {
        this.globalInfo.pageSize = pageSize;
    };

    /**
     * 获取页面大小
     * @returns {number}
     */
    this.getPageSize = function () {
        if (this.globalInfo.pageSize) {
            return this.globalInfo.pageSize;
        } else {
            return 15;
        }
    };
});