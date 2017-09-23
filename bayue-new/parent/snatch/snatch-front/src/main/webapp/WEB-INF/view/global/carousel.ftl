<#-- 首页轮播图  -->

<script type="text/javascript" src="${domain}/statics/plugin/slider/jssor.slider.min.js"></script>

<!-- Jssor Slider Begin -->
<script>
    jssor_slider1_starter = function (containerId) {
        var options = {
            $AutoPlay: 1, //[Optional] Auto play or not, to enable slideshow, this option must be set to greater than 0. Default value is 0. 0: no auto play, 1: continuously, 2: stop at last slide, 4: stop on click, 8: stop on user navigation (by arrow/bullet/thumbnail/drag/arrow key navigation)
            $DragOrientation: 1 //[Optional] Orientation to drag slide, 0 no drag, 1 horizental, 2 vertical, 3 either, default value is 1 (Note that the $DragOrientation should be the same as $PlayOrientation when $Cols is greater than 1, or parking position is not 0)
        };

        var jssor_slider1 = new $JssorSlider$(containerId, options);
    };
</script>

<div id="slider1_container" style="position: relative; top: 0px; left: 0px; width: 100%; height: 300px;">
    <!-- Slides Container -->
    <div u="slides" style="position: absolute; left: 0px; top: 0px; width: 1180px; height: 300px; overflow: hidden;">
    	<#--
        <div><img u="image" src="${domain}/statics/images/testImages/landscape/01.jpg" /></div>
        <div><img u="image" src="${domain}/statics/images/testImages/landscape/02.jpg" /></div>
        <div><img u="image" src="${domain}/statics/images/testImages/landscape/03.jpg" /></div>
        -->
        <#if carousels?default([])?size !=0>
        	<#list carousels as carousel>
        	<div><img u="image" src="${carousel.picture}" /></div>
        	</#list>
        </#if>
    </div>
    
    <!-- Trigger -->
    <script>
        jssor_slider1_starter('slider1_container');
    </script>
</div>
<!-- Jssor Slider End -->