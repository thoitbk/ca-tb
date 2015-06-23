<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="/WEB-INF/tag/functions.tld"%>

<%@ include file="contact.jsp" %>
<div id="Chuyen_Muc">
	<div class="TieuDe">
		<img alt="" src="${ct}/resources/images/communication.png" style="width: 20px; height: 20px; vertical-align: middle;" />
		Chuyên Mục
	</div>
	<div id="menuRight" class="menu">
		<ul class="main">
			<c:forEach items="${rightTopNewsCatalogs}" var="rightTopNewsCatalog">
				<li>
					<a href="${news_ct}/${rightTopNewsCatalog.url}" class="rightTopLink">${rightTopNewsCatalog.name}</a>
				</li>
			</c:forEach>
			<c:forEach items="${columns}" var="column">
				<li>
					<a href="${ct}/${column.url}" class="rightTopLink">${column.name}</a>
				</li>
			</c:forEach>
		</ul>
	</div>
</div>
<c:if test="${rightCenterNews != null}">
	<div id="Thong_Bao">
		<div class="TieuDe">
			<img alt="" src="${ct}/resources/images/common_infor.png" style="width: 20px; height: 20px; vertical-align: middle;" />
			<c:out value="${rightCenterNews.newsCatalog.name}"></c:out>
		</div>
		<div class="main">
			<ul>
				<c:forEach items="${rightCenterNews.newses}" var="news">
					<li>
						<span title='cssbody=[boxbody] singleclickstop=[on] cssheader=[boxheader] header=[${news.title}]  body=[${news.summary}]'>
							<a href='${news_ct}/${rightCenterNews.newsCatalog.url}/${news.id}/${f:toFriendlyUrl(news.title)}'>${news.title}</a>
						</span>
					</li>
				</c:forEach>
			</ul>
			<div class="XemChiTiet">
				<a href='${news_ct}/${rightCenterNews.newsCatalog.url}'>${COMMONINFO.detailsCaption}</a>
			</div>
		</div>
	</div>
</c:if>

<link href="${ct}/resources/css/jquery-ui-tab.css" rel="stylesheet" type="text/css" />

<div id="tabs">
	<ul>
		<li><a href="#image_gallery">Ảnh</a></li>
		<li><a href="#video_gallery">Video</a></li>
	</ul>
	<div id="image_gallery" style="padding-top: 5px;">
		<div class="image_container" style="border: 1px solid #D0D0D0;">
			<div id="image_slider" style="display: none; position: relative; margin: 0 auto; width: 120px; height: 90px; overflow: hidden;">
		        <div u="loading" style="position: absolute; top: 0px; left: 0px;">
		            <div style="filter: alpha(opacity=70); opacity:0.7; position: absolute; display: block;
		                background-color: #000000; top: 0px; left: 0px;width: 100%;height:100%;">
		            </div>
		            <div style="position: absolute; display: block; background: url(${ct}/jssor/img/loading.gif) no-repeat center center;
		                top: 0px; left: 0px;width: 100%;height:100%;">
		            </div>
		        </div>
				<div u="slides" style="cursor: move; position: absolute; left: 0px; top: 0px; width: 120px; height: 90px; overflow: hidden;">
					<c:forEach items="${images}" var="image">
						<div>
		                    <img u="image" src="${image.file}" alt="" />
		                </div>
					</c:forEach>
				</div>
			</div>
		</div>
		<div class="XemChiTiet">
			<a href='${ct}/thu-vien-anh' style="color: #FF0000">${COMMONINFO.detailsCaption}</a>
		</div>
	</div>
	<div id="video_gallery">zxczxc</div>
</div>

<script>
	$("#tabs").tabs();
</script>

<div id="Lien_Ket_Web">
	<select name="linkSites" id="linkSites" class="ddl">
		<option value="">------ Liên kết website ------</option>
		<c:forEach items="${LINK_LIST}" var="link">
			<option value="${link.linkSite}" class="${link.openBlank ? 'blank' : ''}">${link.title}</option>
		</c:forEach>
	</select>
</div>
<div id="Quang_Cao">
	<span>
	<a href=http://thaibinh.xnc.vn/ target='_blank'> <img src="/quangcao1.jpg" width='204'/> </a>
	<a href=http://thaibinh.gov.vn/ target='_blank'> <img src="/quangcao2.jpg" border='0' width='204'/> </a>
	</span>
</div>
<div id="Luot_Truy_Cap">
	<div class='truycap'>Số lượt truy cập:<br /> </div>
	<div class="space5">
		&nbsp;
	</div>
</div>

<script src="${ct}/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ct}/resources/js/docs.min.js" type="text/javascript"></script>
<script src="${ct}/resources/js/ie10-viewport-bug-workaround.js" type="text/javascript"></script>

<script src="${ct}/resources/js/jssor.slider.mini.js" type="text/javascript"></script>

<script>
    jQuery(document).ready(function ($) {
        var options = {
            $AutoPlay: true,                                    //[Optional] Whether to auto play, to enable slideshow, this option must be set to true, default value is false
            $AutoPlaySteps: 1,                                  //[Optional] Steps to go for each navigation request (this options applys only when slideshow disabled), the default value is 1
            $AutoPlayInterval: 2000,                            //[Optional] Interval (in milliseconds) to go for next slide since the previous stopped if the slider is auto playing, default value is 3000
            $PauseOnHover: 1,                                   //[Optional] Whether to pause when mouse over if a slider is auto playing, 0 no pause, 1 pause for desktop, 2 pause for touch device, 3 pause for desktop and touch device, 4 freeze for desktop, 8 freeze for touch device, 12 freeze for desktop and touch device, default value is 1

            $ArrowKeyNavigation: true,   			            //[Optional] Allows keyboard (arrow key) navigation or not, default value is false
            $SlideEasing: $JssorEasing$.$EaseOutQuint,          //[Optional] Specifies easing for right to left animation, default value is $JssorEasing$.$EaseOutQuad
            $SlideDuration: 800,                                //[Optional] Specifies default duration (swipe) for slide in milliseconds, default value is 500
            $MinDragOffsetToSlide: 20,                          //[Optional] Minimum drag offset to trigger slide , default value is 20
            //$SlideWidth: 600,                                 //[Optional] Width of every slide in pixels, default value is width of 'slides' container
            //$SlideHeight: 300,                                //[Optional] Height of every slide in pixels, default value is height of 'slides' container
            $SlideSpacing: 0, 					                //[Optional] Space between each slide in pixels, default value is 0
            $DisplayPieces: 1,                                  //[Optional] Number of pieces to display (the slideshow would be disabled if the value is set to greater than 1), the default value is 1
            $ParkingPosition: 0,                                //[Optional] The offset position to park slide (this options applys only when slideshow disabled), default value is 0.
            $UISearchMode: 1,                                   //[Optional] The way (0 parellel, 1 recursive, default value is 1) to search UI components (slides container, loading screen, navigator container, arrow navigator container, thumbnail navigator container etc).
            $PlayOrientation: 1,                                //[Optional] Orientation to play slide (for auto play, navigation), 1 horizental, 2 vertical, 5 horizental reverse, 6 vertical reverse, default value is 1
            $DragOrientation: 1,                                //[Optional] Orientation to drag slide, 0 no drag, 1 horizental, 2 vertical, 3 either, default value is 1 (Note that the $DragOrientation should be the same as $PlayOrientation when $DisplayPieces is greater than 1, or parking position is not 0)

            $ArrowNavigatorOptions: {                           //[Optional] Options to specify and enable arrow navigator or not
                $Class: $JssorArrowNavigator$,                  //[Requried] Class to create arrow navigator instance
                $ChanceToShow: 2,                               //[Required] 0 Never, 1 Mouse Over, 2 Always
                $AutoCenter: 2,                                 //[Optional] Auto center arrows in parent container, 0 No, 1 Horizontal, 2 Vertical, 3 Both, default value is 0
                $Steps: 1,                                      //[Optional] Steps to go for each navigation request, default value is 1
                $Scale: false                                   //Scales bullets navigator or not while slider scale
            },

            $BulletNavigatorOptions: {                                //[Optional] Options to specify and enable navigator or not
                $Class: $JssorBulletNavigator$,                       //[Required] Class to create navigator instance
                $ChanceToShow: 2,                               //[Required] 0 Never, 1 Mouse Over, 2 Always
                $AutoCenter: 1,                                 //[Optional] Auto center navigator in parent container, 0 None, 1 Horizontal, 2 Vertical, 3 Both, default value is 0
                $Steps: 1,                                      //[Optional] Steps to go for each navigation request, default value is 1
                $Lanes: 1,                                      //[Optional] Specify lanes to arrange items, default value is 1
                $SpacingX: 12,                                   //[Optional] Horizontal space between each item in pixel, default value is 0
                $SpacingY: 4,                                   //[Optional] Vertical space between each item in pixel, default value is 0
                $Orientation: 1,                                //[Optional] The orientation of the navigator, 1 horizontal, 2 vertical, default value is 1
                $Scale: false                                   //Scales bullets navigator or not while slider scale
            }
        };

        //Make the element 'slider1_container' visible before initialize jssor slider.
        $("#image_slider").css("display", "block");
        var jssor_slider1 = new $JssorSlider$("image_slider", options);

        //responsive code begin
        //you can remove responsive code if you don't want the slider scales while window resizes
        function ScaleSlider() {
            var parentWidth = jssor_slider1.$Elmt.parentNode.clientWidth;
            if (parentWidth) {
                jssor_slider1.$ScaleWidth(parentWidth - 0);
            }
            else
                window.setTimeout(ScaleSlider, 0);
        }
        ScaleSlider();

        $(window).bind("load", ScaleSlider);
        $(window).bind("resize", ScaleSlider);
        $(window).bind("orientationchange", ScaleSlider);
        //responsive code end
    });
</script>