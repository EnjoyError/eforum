function CommonUtil() {

}

CommonUtil.fn = CommonUtil.prototype;

/**
 * 获取图片原始宽和高
 */
CommonUtil.fn.getImgWidthAndHeight = function (img) {
    var nWidth, nHeight
    if (img.naturalWidth) { // 现代浏览器
        nWidth = img.naturalWidth;
        nHeight = img.naturalHeight;
    } else { // IE6/7/8
        var imgae = new Image();
        image.src = img.src;
        nWidth = image.naturalWidth;
        nHeight = image.naturalHeight;
    }
    return {width: nWidth, height: nHeight}
}

/**
 * 获取页面高度
 */
CommonUtil.fn.getPageHeight = function () {
    return document.body.scrollHeight + 180 + 'px';
}

/**
 * 关闭弹出面板
 */
CommonUtil.fn.closePopBox = function () {
    var eles = angular.element(document.querySelectorAll(".popBoxContent"));
    angular.forEach(eles, function (ele) {
        ele.style.display = "none";
    });
    angular.element(document.querySelector(".popBoxPage"))[0].style.display = "none";
}

/**
 * 打开弹出面板
 */
CommonUtil.fn.openPopBox = function () {
    angular.element(document.querySelector(".popBoxPage"))[0].style.display = "block";
}


module.exports = CommonUtil;