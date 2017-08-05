var app = require('../app');
var tpl = require('../templates/image.html');

app.directive('imageDirective',function(){
	return {
		restrict: 'E',
		template: tpl,
		replace: true,
	} 
});


app.directive('enlargePic',function(util){//enlargePic指令名称，写在需要用到的地方img中即可实现放大图片  
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
                bigPic.height(widthAndHeight.height);
                bigPic.width(widthAndHeight.width);
                bigPic.src = img.src;
            })    
        }    
    }    
});

app.directive('closePic',function(util){    
    return{    
        restrict: "AE",    
        link: function(scope,elem){    
            elem.bind('click',function($event){    
                angular.element(document.querySelector(".mask"))[0].style.display = "none";    
            })    
        }    
    }    
});