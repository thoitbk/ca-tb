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
<div id="Media">
	<div id="tabs">
		<ul>
			<li><a href="">
				Videos
				</a>
			</li>
			<li><a href="">
				Ảnh
				</a>
			</li>
		</ul>
	</div>
</div>
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