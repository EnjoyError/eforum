var app = require('../app');
var tpl = require('../templates/image.html');

app.directive('imageDirective',function(){
	return {
		restrict: 'E',
		template: tpl,
		replace: true,
	} 
});


app.directive('enlargePic',function(){//enlargePic指令名称，写在需要用到的地方img中即可实现放大图片
    return{    
        restrict: "AE",    
        link: function(scope,elem){    
            elem.bind('click',function($event){    
                var img = $event.srcElement || $event.target;    
                angular.element(document.querySelector(".mask"))[0].style.display = "block";    
                var bigPic = angular.element(document.querySelector(".bigPic"))[0];    
                var widthAndHeight = util.getImgWidthAndHeight(img);
                var height = widthAndHeight.height;
                var width = widthAndHeight.width
                if(height > 420){
                	height = 420;
                }
                if(width > 720){
                	width = 720;
                }
                var subHeight = (420 - height)/2;
                subHeight = (subHeight/420)*100;
                subHeight = subHeight + '%'
                height = height + 'px';
                width = width + 'px';
                bigPic.style.height = height;
                bigPic.style.width = width;
                bigPic.style.top = subHeight;
                bigPic.src = img.src;
                
                var maskBox = angular.element(document.querySelector(".mask-box"))[0];
                maskBox.style.height =  util.getPageHeight();
            })    
        }    
    }    
});

app.directive('closePic',function(){
    return{    
        restrict: "AE",    
        link: function(scope,elem){    
            elem.bind('click',function($event){    
                angular.element(document.querySelector(".mask"))[0].style.display = "none";    
            })    
        }    
    }    
});