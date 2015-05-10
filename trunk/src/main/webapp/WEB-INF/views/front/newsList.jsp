<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="MainDMTin">
	<div id="Khau_HieuKH">
		<img src="${ct}/Upload/Galery/Image/14218445_Anh-giao-dien.JPG" />
	</div>
	
	<div id="Tin_Theo_DM">
		<div class="Tin_Theo_DM">
			<c:forEach items="${specialSiteInfos}" var="specialSiteInfo" varStatus="s">
				<c:if test="${s.index % 2 == 0}">
					<c:set var="leftOrRight" value="Tin_Theo_DM_Left" scope="request" />
				</c:if>
				<c:if test="${s.index % 2 == 1}">
					<c:set var="leftOrRight" value="Tin_Theo_DM_Right" scope="request" />
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
						<c:forEach items="${specialSiteInfo.newses}" var="news">
							<div class='TinTC'>
								<span title='cssbody=[boxbody] singleclickstop=[on] cssheader=[boxheader] header=[${news.title}]  body=[${news.summary}]'>
									<a href='${ct}/${specialSiteInfo.newsCatalog.url}/${news.id}'>
										<img class='fl' src='${news.image}' />
									</a>
									<p class='title'>
										<fmt:formatDate var="postedDate" value="${news.postedDate}" pattern="dd/MM/yyyy" />
										<a href='${ct}/${specialSiteInfo.newsCatalog.url}/${news.id}'>${news.title}<br/><span class='ngaythang'><c:out value="${postedDate}"></c:out></span></a>
									</p>
								</span>
								<p> 
								<p class='desc'> </p>
								<div></div>
							</div>
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