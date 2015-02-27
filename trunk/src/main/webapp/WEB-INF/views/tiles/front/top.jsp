<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div ID="Banner">
	<embed width="100%" height="114" align="middle" src="images/banner.swf" wmode="transparent">
</div>
<!-- jsp:include to build menu by referring to @menu controller -->
<div id="nav" class="menu">
	<ul class="menuParent">
		<li class="home">  
			<a href="home">
				<span> <img src="resources/images/TopMenu_home.png" border="0px" width="15" height="15" /></span>
			</a>  
		</li>
		<li>  <a href="/Gioi-Thieu/Chung">  
			<span> Giới thiệu  </span>
			</a>
		</li>
		<li>
			<a class="" href="/Tin-Tuc/Tin_Hoat_Dong">
			<span class="">   Tin hoạt động </span></a>
		</li>
		<li>
			<a class="" href="/Tin-Tuc/Tin_ANTT">
			<span class="">   ANTT trong tỉnh </span></a>
		</li>
		<li>
			<a class="" href="/Tin-Tuc/Huong_Ve_CS">
			<span class="">   Hướng về CS </span></a>
		</li>
		<li>
			<a class="" href="/Tin-Tuc/Cai_Cach_HC">
			<span class="">   Cải cách HC </span></a>
		</li>
		<li>
			<a class="" href="/Tin-Tuc/Nguoi_Tot">
			<span class="">   Người tốt việc tốt </span></a>
		</li>
		<li>
			<a class="" href="/Tin-Tuc/CD_CM">
			<span class="">   Chuyên đề-chuyên mục </span></a>
		</li>
		<li>
			<a class="" href="/Tin-Tuc/Tin_Trong_Nganh">
			<span class="">   Tin trong ngành </span></a>
		</li>
		<li>
			<a class="" href="/Tin-Tuc/Tin_Trong_Tinh">
			<span class="">   Tin trong tỉnh </span></a>
		</li>
	</ul>
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