<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link href="${ct}/resources/css/jquery.bxslider.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ct}/resources/js/jquery.bxslider.min.js" ></script>
<script type="text/javascript" src="${ct}/resources/js/prefixfree.js" ></script>
<link href="${ct}/resources/css/marquee.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	$(document).ready(function(){
		$('.bxslider').bxSlider({
			mode: 'horizontal',
			captions: true,
			auto: true,
			autoControls: true,
			speed: 1000
		});
	});
</script>

<div id="Tin_Noi_Bat_C" class="main">
	<div id="Tin_Noi_Bat_C_Chinh" class="Top_News">
		<ul class="bxslider">
			<c:forEach items="${hotNewses}" var="hotNews">
				<li>
					<a href='${ct}/${hotNews.newsCatalog.url}/${hotNews.id}'><img alt="" src="${hotNews.image}" title="${hotNews.title}"></a>
				</li>
			</c:forEach>
		</ul>
	</div>
	<div id="Tin_Noi_Bat_C_Khac" class="main">
		<div class='TieuDe'>
			<img alt="" src="${ct}/resources/images/hot_news.png" style="width: 20px; height: 20px; vertical-align: middle;" />
			<span style="">Các tin nổi bật!</span>
		</div>
		<div class="hot_newses">
			<ul class="marquee">
				<c:forEach items="${hotNewses}" var="hotNews">
					<li>
						<a href='${ct}/${hotNews.newsCatalog.url}/${hotNews.id}' >
							<span title='cssbody=[boxbody] singleclickstop=[on] cssheader=[boxheader] header=[${hotNews.title}]  body=[${hotNews.summary}]'>
								<c:out value="${hotNews.title}"></c:out> <br/>
								<fmt:formatDate var="postedDate" value="${hotNews.postedDate}" pattern="dd/MM/yyyy" />
								<span class='ngaythang'>${postedDate}</span>
							</span>
						</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		<%-- <marquee behavior="scroll" direction="Up" scrolldelay="60" scrollamount="1" onmouseout="this.start()" onmouseover="this.stop()">
			<ul>
				<c:forEach items="${hotNewses}" var="hotNews">
					<li>
						<a href='${ct}/${hotNews.newsCatalog.url}/${hotNews.id}' >
							<span title='cssbody=[boxbody] singleclickstop=[on] cssheader=[boxheader] header=[${hotNews.title}]  body=[${hotNews.summary}]'>
								<c:out value="${hotNews.title}"></c:out> <br/>
								<fmt:formatDate var="postedDate" value="${hotNews.postedDate}" pattern="dd/MM/yyyy" />
								<span class='ngaythang'>${postedDate}</span>
							</span>
						</a>
					</li>
				</c:forEach>
			</ul>
		</marquee> --%>
	</div>
	<div class="space5">
		&nbsp;
	</div>
</div>