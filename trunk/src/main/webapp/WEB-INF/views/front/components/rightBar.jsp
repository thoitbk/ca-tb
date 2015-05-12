<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="Dien_thoai_LienHe">
	<div class="TieuDe">
		ĐIỆN THOẠI CẦN BIẾT
	</div>
	<div class="main">
		<ul>
			<li>
				Trực ban CA tỉnh: <span>036.3870123</span>
			</li>
		</ul>
	</div>
</div>

<div id="Chuyen_Muc">
	<div class="TieuDe">Chuyên Mục</div>
	<div id="menuRight" class="menu">
		<ul class="main">
			<li>
				
			</li>
		</ul>
	</div>
</div>
<div id="Thong_Bao">
	<div class="TieuDe">
		THÔNG BÁO CÔNG AN TỈNH
	</div>
	<div class="main">
		<ul>
			<li>
				<span title='cssbody=[boxbody] singleclickstop=[on] cssheader=[boxheader] header=[inform]  body=[content]'>
					<a href=''>inform</a>
				</span>
			</li>
		</ul>
		<div class="XemChiTiet">
		</div>
	</div>
</div>
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
	<a href=http://thaibinh.xnc.vn/ target='_blank'> <img src="1" width='204'/> </a>
	<a href=http://thaibinh.gov.vn/ target='_blank'> <img src="1" border='0' width='204'/> </a>
	</span>
</div>
<div id="Luot_Truy_Cap">
	<div class='truycap'>Số lượt truy cập:<br /> </div>
	<div class="space5">
		&nbsp;
	</div>
</div>