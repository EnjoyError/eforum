var app = require('../app');
/**
 * 通知服务
 */
app.service('notificationService', function () {
    var messageMap = {};
    /**
     * 注册消息回调函数，即注册消息订阅者
     * @param messageId 消息Id
     * @param callBackFunction 回调函数
     */
    this.registerCallBack = function (messageId, callBackFunction) {
        for (var prop in messageMap) {
            if (messageMap.hasOwnProperty(prop)) {
                if (prop == messageId) {
                    throw "消息[" + messageId + "]已经注册过回调函数了，不允许重复注册!";
                }
            }
        }
        messageMap[messageId] = callBackFunction;
    }

    /**
     * 发布消息
     * @param messageId 消息Id
     */
    this.pubNotification = function (messageId, param) {
        for (var prop in messageMap) {
            if (messageMap.hasOwnProperty(prop)) {
                if (prop == messageId) {
                    messageMap[messageId](param);
                    return ;
                }
            }
        }
    }
});