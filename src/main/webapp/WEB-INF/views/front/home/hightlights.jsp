<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="Tin_Noi_Bat_C" class="main">
	<div id="Tin_Noi_Bat_C_Chinh" class="Top_News">
		<a href=''>
			<span class='TieuDeTC'>Công an Thái Bình</span>
			<img src='Upload/15116630_LE-THI-DUA-BIA.png'/>
		</a>
		<div class="NoiDung">
			<a href='' alt='' title=''></a>OK
			<span class='ngaythang'> (16/01/2015)</span>
		</div>
	</div>
	<div id="Tin_Noi_Bat_C_Khac" class="main">
		<div class='TieuDe'>
			<img alt="" src="${ct}/resources/images/hot_news.png" style="width: 20px; height: 20px; vertical-align: middle;" />
			<span style="">Các tin nổi bật!</span>
		</div>
		<marquee behavior="scroll" direction="Up" scrolldelay="60" scrollamount="1" onmouseout="this.start()" onmouseover="this.stop()">
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
		</marquee>
	</div>
	<div class="space5">
		&nbsp;
	</div>
</div>