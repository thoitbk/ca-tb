<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<p />

<script>            
	jQuery(document).ready(function() {
		var offset = 220;
		var duration = 500;
		jQuery(window).scroll(function() {
			if (jQuery(this).scrollTop() > offset) {
				jQuery('.back-to-top').fadeIn(duration);
			} else {
				jQuery('.back-to-top').fadeOut(duration);
			}
		});
		
		jQuery('.back-to-top').click(function(event) {
			event.preventDefault();
			jQuery('html, body').animate({scrollTop: 0}, duration);
			return false;
		})
	});
</script>
<div class="Duong_ke"></div>
<div class="Thong_Tin">
	<div style="text-align: center; position: relative;">
		<span style="font-weight: bold; color: #830909; line-height: 5px;">Cổng thông tin điện tử Công an tỉnh Thái Bình</span><br />
		<span style="color: #805050; line-height: 5px;">
			Địa chỉ: Đường Lê Quý Đôn - Thành phố Thái Bình - Tỉnh Thái Bình. Điện thoại: (036) 3870100 <br />
			Email: conganthaibinh@gmail.com - Website: http://conganthaibinh.gov.vn <br />
			Bản quyền thuộc về Công an tỉnh Thái Bình
		</span>
		<div class="dev">Thiết kế và lập trình bởi <strong><a href="http://thoitbk.blogspot.com/" target="_blank" style="text-decoration: none; color: red;">Trần Anh Thơ</a></strong> - Cán bộ Phòng Tham mưu Công an tỉnh Thái Bình</div>
	</div>
</div>