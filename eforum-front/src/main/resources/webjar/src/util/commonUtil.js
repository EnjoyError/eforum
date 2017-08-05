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
});