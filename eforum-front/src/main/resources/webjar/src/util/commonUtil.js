var app = require('../app');

app.service('util', function() {
	/**
	 * 获取图片原始宽和高
	 */
	this.getImgWidthAndHeight = function(img) {
		var nWidth, nHeight
		if (img.naturalWidth) { // 现代浏览器
			nWidth = img.naturalWidth
			nHeight = img.naturalHeight
		} else { // IE6/7/8
			var imgae = new Image()
			image.src = img.src
			nWidth = image.naturalWidth
			nHeight = image.naturalHeight
		}
		return { width:nWidth, height:nHeight }
	}

    /**
     * 获取页面高度
     */
	this.getPageHeight = function(){
        return document.body.scrollHeight + 180 + 'px';
	}

    /**
	 * 关闭弹出面板
     */
	this.closePopBox = function(){
        var eles = angular.element(document.querySelectorAll(".popBoxContent"));
        angular.forEach(eles,function(ele){
        	ele.style.display = "none";
		});
        angular.element(document.querySelector(".popBoxPage"))[0].style.display = "none";
	}

    /**
	 * 打开弹出面板
     */
	this.openPopBox = function(){
        angular.element(document.querySelector(".popBoxPage"))[0].style.display = "block";
	}

    /**
     * eforum弹框alert
     */
    this.alert = function (message){
        message = message||"error";
        //创建弹出内容
        angular.element(document.querySelector("#eForumTableAlertHide div p")).eq(0).html(message);
        angular.element(document.querySelector("#eForumTableAlertHide")).css("display","block");
        alert("asdfas");
        //2秒后隐藏
        setTimeout('angular.element(document.querySelector("#eForumTableAlertHide")).css("display","none");', 2000);
    }
});