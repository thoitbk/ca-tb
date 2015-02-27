<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<a class="" href='http://congan.thaibinh.gov.vn/Gop-y' >
				<img src="131023593_icon_cqcsdt.jpg" border="0px" />
				<span class="">   Góp ý cho LL Công an nhân dân </span></a>
				<div class="menuchild">
					<ul>
					</ul>
				</div>
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
	<script type="text/javascript" src="resources/js/jquery.hoveraccordion.js"></script>
	<link type="text/css" href="resources/css/jquery.ui.base.css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="resources/css/jquery.ad-gallery.css">
	<script>
		$(function() {
			$("#tabs").tabs({
				ajaxOptions: {
					error: function(xhr, status, index, anchor) {
						$(anchor.hash).html(
				"Có lỗi trong quá trình tải dữ liệu!");
					}
				}
			});
		});
	</script>
	<div id="tabs">
		<ul>
			<li><a href="http://congan.thaibinh.gov.vn/VideoImage/TabVideo.aspx">
				Videos
				</a>
			</li>
			<li><a href="http://congan.thaibinh.gov.vn/VideoImage/Tab/TabGaleryImage.aspx">
				Ảnh
				</a>
			</li>
		</ul>
	</div>
</div>
<div id="Lien_Ket_Web">
	<script type='text/javascript'>
		function customviewRedirect(obj){if(obj.selectedIndex > -1){var url = obj.options[obj.selectedIndex].value;if(url != '')window.open(url);}}
	</script>
	<select class='ddl'  onchange='customviewRedirect(this);'>
		<option value=''>--- Liên kết Website ---</option>
		<option value='http://google.com.vn/'>Google</option>
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