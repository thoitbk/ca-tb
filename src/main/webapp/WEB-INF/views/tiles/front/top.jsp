<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="exTag" uri="/WEB-INF/tag/extags.tld" %>
<div id="banner">
	<img alt="" src="${ct}/resources/images/banner.PNG" width="100%" height="100%" />
	<%-- <embed width="100%" height="114" align="middle" src="${ct}/images/banner.swf" wmode="transparent"> --%>
</div>
<div>
	<exTag:menu menuHierarchy="${MENU_HIERARCHY}" request="${pageContext.request}" menuId="menu-bar" selectedClass="active"/>
</div>
<div id="TopInfo">
	<div id="InfoLeft">
		<span id="ngay"></span>    
	</div>
	<div id="marque">
		<marquee behavior="scroll" direction="left" scrolldelay="60" scrollamount="2">${COMMONINFO.marqueeTitle}</marquee>
	</div>
	<div id="SearchBox">
		<form action="${ct}/search" method="get" id="searchform" id="searchbox_001033406891335138810:mvy6sjiyygc">
			<input value="001033406891335138810:mvy6sjiyygc" name="cx" type="hidden"/>
			<input value="FORID:11" name="cof" type="hidden"/>
			<input type="text" name="s" id="s" placeholder="Tìm kiếm..." value="${param.s}" />
			<input type="submit" value="Tìm kiếm" />
		</form>
	</div>
</div>