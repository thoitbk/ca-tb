<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="exTag" uri="/WEB-INF/tag/extags.tld" %>
<div ID="Banner">
	<embed width="100%" height="114" align="middle" src="images/banner.swf" wmode="transparent">
</div>
<div>
	<exTag:menu menuHierarchy="${MENU_HIERARCHY}" request="${pageContext.request}" menuId="menu-bar" selectedClass="active"/>
</div>
<div id="TopInfo">
	<div id="InfoLeft">
		<span>  Hôm nay, thứ 6 ngày 16/01/2015  </span>    
	</div>
	<div id="marque">
		<marquee behavior="scroll" direction="left" scrolldelay="60" scrollamount="2">
			Công an tỉnh Thái Bình chủ động, kỷ cương, trách nhiệm, hiệu quả - Đảm bảo tốt an ninh trật tự phục vụ xây dựng nông thôn mới <span></span>
		</marquee>
	</div>
	<div id="SearchBox">
		<input type="button" onclick="javascript:search(document.getElementById('q').value);" class="btTimKiem" value="Search">
		<input name="WPQ1q" id="q" class="txtSearch">
		<a href="Tim-Kiem/Google.html">                 
		<img src="Images/Google_Search.png" border="0" alt"Google" /></a>  
		<script>
			function search(q) {
				document.location = "/Tim-Kiem/" + q;
			}
		</script>
	</div>
</div>