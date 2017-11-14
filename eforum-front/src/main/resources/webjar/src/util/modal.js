/***
 * 模态框封装
 */
function Modal() {
    this.reg = new RegExp("\\[([^\\[\\]]*?)\\]", 'igm');
    this.ahtml = "<div class=\"modal-dialog modal-sm\" style='margin-top: 15%;'>\n" +
        "        <div class=\"modal-content\">\n" +
        "            <div class=\"modal-header\">\n" +
        "                <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span aria-hidden=\"true\">×</span><span class=\"sr-only\">Close</span></button>\n" +
        "                <h5 class=\"modal-title\"><i class=\"fa fa-exclamation-circle\"></i> [Title]</h5>\n" +
        "            </div>\n" +
        "            <div class=\"modal-body small\">\n" +
        "                <p>[Message]</p>\n" +
        "                <input class=\"input-sm\" style=\"width: 100%;display: none;\"/>\n" +
        "            </div>\n" +
        "            <div class=\"modal-footer\" >\n" +
        "                <button type=\"button\" class=\"btn btn-primary ok\" data-dismiss=\"modal\">[BtnOk]</button>\n" +
        "                <button type=\"button\" class=\"btn btn-default cancel\" data-dismiss=\"modal\">[BtnCancel]</button>\n" +
        "            </div>\n" +
        "        </div>\n" +
        "    </div>";
}

Modal.fn = Modal.prototype;

Modal.fn.$ = require('jquery');

/**
 * eforum弹框alert
 */
Modal.fn.alert = function (message,sec) {
    message = message || "error";
    sec = sec || 1500;
    //创建弹出内容
    this.$(document.querySelector("#eforumAlert div p")).eq(0).html(message);
    this.$(document.querySelector("#eforumAlert")).css("display", "block");
    //sec秒后隐藏
    setTimeout('angular.element(document.querySelector("#eforumAlert")).css("display","none");', sec);
}

/***
 * 模态框
 * options参数说明
 * {
                msg: "提示内容",
                title: "操作提示",
                btnOk: "确定",
                btnCancel: "取消",
                callback:function(data){        //按钮触发回调

                    点击确定  data = {"result":true,text:输入的内容}

                    点击取消或关闭  data = {"result":false,text:""}

                }
            }
 */
Modal.fn.showModal = function(options){
    var eforum_modal = this.$(document.querySelector("#eforum-modal"));
    var ops = {
        msg: "提示内容",
        title: "操作提示",
        btnOk: "确定",
        btnCancel: "取消",
        callback:function () {

        }
    };

    for(var key in options){
        ops[key] = options[key];
    }

    var html = eforum_modal.html().replace(this.reg, function (node, key) {
        return {
            Title: ops.title,
            Message: ops.msg,
            BtnOk: ops.btnOk,
            BtnCancel: ops.btnCancel
        }[key];
    });

    eforum_modal.html(html);
    eforum_modal.modal({
        width: 250,
        backdrop: 'static'
    });
    var callback = options.callback;
    if (callback && callback instanceof Function) {
        eforum_modal.find('.ok').click(function () {
            //结果 返回true  输入框内容
            callback({"result":true,"text":eforum_modal.find('input').eq(0).val()})
        });
        //结果返回false
        eforum_modal.find('.cancel').click(function () { callback({"result":false}) });
        eforum_modal.find('.close').click(function () { callback({"result":false}) });
    }
}


/***
 * 提示框
 * message : 提示内容
 * sec : 过几秒自动关机  默认为空
 */
Modal.fn.showMsg = function(message,sec) {
    var options = {
        msg : message,
        title : "提示",
        btnOk : "确定"
    }
    var eforum_modal = this.$(document.querySelector("#eforum-modal"));
    eforum_modal.html(this.ahtml);  // 复原
    eforum_modal.find('.ok').removeClass('btn-success').addClass('btn-primary');
    eforum_modal.find('.cancel').hide();
    this.showModal(options);
    if(sec) {
        setTimeout(function () {
            eforum_modal.find('.ok').click();
        }, sec);
    }
}

/***
 * confirm框(提示标题、提示内容、确定按钮、取消按钮)
 * options参数说明
 * {
                msg: "提示内容",
                title: "操作提示",
                btnOk: "确定",
                btnCancel: "取消",
                callback:function(data){        //按钮触发回调

                    点击确定  data = {"result":true,text:输入的内容}

                    点击取消或关闭  data = {"result":false,text:""}
                }
            }
 返回值:点击确定返回ture 否则返回false
 */
Modal.fn.showConfirm = function(options) {
    var eforum_modal = this.$(document.querySelector("#eforum-modal"));
    eforum_modal.html(this.ahtml); // 复原
    eforum_modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
    eforum_modal.find('.cancel').show();
    this.showModal(options);
}

/***
 * prompt(提示标题、输入框、确定按钮、取消按钮)
 * options参数说明
 * {
                title: "操作提示",
                btnOk: "确定",
                btnCancel: "取消",
                callback:function(data){        //按钮触发回调

                    点击确定  data = {"result":true,text:输入的内容}

                    点击取消或关闭  data = {"result":false,text:""}
                }
            }
 */
Modal.fn.showPrompt = function(options) {
    var eforum_modal = this.$(document.querySelector("#eforum-modal"));
    eforum_modal.html(this.ahtml); // 复原
    eforum_modal.find('.ok').removeClass('btn-primary').addClass('btn-success');
    eforum_modal.find('p').hide();
    eforum_modal.find('input').show();
    eforum_modal.find('.cancel').show();
    if(this.showModal(options)){
        return eforum_modal.find('input').eq(0).val();
    }else{
        return null;
    }
}

module.exports = Modal;