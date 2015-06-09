<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="exTag" uri="/WEB-INF/tag/extags.tld" %>
<div ID="Banner">
	<embed width="100%" height="114" align="middle" src="${ct}/images/banner.swf" wmode="transparent">
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
		<form action="${ct}/search" method="get">
			<input type="text" name="q" id="q" placeholder="Tìm kiếm..." />
			<input type="submit" value="Tìm kiếm" />
		</form>
	</div>
</div>