<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="MainDMTin">
	<div id="Khau_HieuKH">
		<img src="${ct}/Upload/Galery/Image/14218445_Anh-giao-dien.JPG" />
	</div>
	
	<div id="special_sites">
		<div class="special_sites">
			<c:forEach items="${specialSiteInfos}" var="specialSiteInfo" varStatus="s">
				<c:if test="${s.index % 2 == 0}">
					<c:set var="leftOrRight" value="special_site_left" scope="request" />
				</c:if>
				<c:if test="${s.index % 2 == 1}">
					<c:set var="leftOrRight" value="special_site_right" scope="request" />
				</c:if>
				<div class="${leftOrRight}">
					<div class="TieuDe">
						<div class="TieuDe_dau"></div>
						<div class="TieuDe_ND">
							<a href='${ct}/${specialSiteInfo.newsCatalog.url}'>${specialSiteInfo.newsCatalog.name}</a>
						</div>
						<div class="TieuDe_Cuoi"></div>
					</div>
					<div class="Khung">
						<c:forEach items="${specialSiteInfo.newses}" var="news" varStatus="status">
							<c:if test="${status.index == 0}">
								<a href='${ct}/${specialSiteInfo.newsCatalog.url}/${news.id}'>
									<img class='news_thumb' src='${news.image}' alt="Ảnh" />
								</a>
								<p class="main_news_title">
									<a href="${ct}/${specialSiteInfo.newsCatalog.url}/${news.id}">${news.title}</a>
								</p>
								<p class="lead">
									<c:out value="${news.summary}"></c:out>
								</p>
							</c:if>
							<c:if test="${status.index != 0}">
								
							</c:if>
							<span title='cssbody=[boxbody] singleclickstop=[on] cssheader=[boxheader] header=[${news.title}]  body=[${news.summary}]'>
								<a href='${ct}/${specialSiteInfo.newsCatalog.url}/${news.id}'>
									<img class='fl' src='${news.image}' />
								</a>
								<p class=''>
									<fmt:formatDate var="postedDate" value="${news.postedDate}" pattern="dd/MM/yyyy" />
									<a href='${ct}/${specialSiteInfo.newsCatalog.url}/${news.id}'>${news.title}<br/><span class='ngaythang'><c:out value="${postedDate}"></c:out></span></a>
								</p>
							</span>
							<p> 
							<p class='desc'> </p>
						</c:forEach>
						<div class="XemChiTiet">
							<a href='${ct}/${specialSiteInfo.newsCatalog.url}'>${COMMONINFO.detailsCaption}</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>