<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="vertical-menu">
	<ul class="accordion">
		<li class="item selected_item" id="info">
			<img src="resources/images/info.png" class="icon_menu" />
			<h3>Quản trị tin tức</h3>
			<ul class="child">
				<li id="info_1">
					<img src="resources/images/add_news.gif" class="bullet_menu" />
					<a href="#">Thêm mới tin</a>
				</li>
				<li id="info_2">
					<img src="resources/images/earth_f.gif" class="bullet_menu" />
					<a href="#">Quản trị tin</a>
				</li>
				<li id="info_3">
					<img src="resources/images/template_f.gif" class="bullet_menu" />
					<a href="#">Duyệt tin</a>
				</li>
				<li id="info_4">
					<img src="resources/images/book_f.gif" class="bullet_menu" />
					<a href="#">Quản trị danh mục tin</a>
				</li>
			</ul>
		</li>
		<li class="item" id="doc">
			<img src="resources/images/doc.png" class="icon_menu" />
			<h3>Quản trị văn bản</h3>
			<ul class="child">
				<li id="doc_1">
					<img src="resources/images/add_news.gif" class="bullet_menu" />
					<a href="#">Quản trị TTHC</a>
				</li>
				<li id="doc_2">
					<img src="resources/images/earth_f.gif" class="bullet_menu" />
					<a href="#">Quản trị văn bản</a>
				</li>
			</ul>
		</li>
		<li class="item" id="question">
			<img src="resources/images/question.png" class="icon_menu" />
			<h3>Quản trị hỏi đáp</h3>
			<ul class="child">
				<li id="question_1">
					<img src="resources/images/add_news.gif" class="bullet_menu" />
					<a href="#">Quản trị DM hỏi đáp</a>
				</li>
				<li id="question_2">
					<img src="resources/images/earth_f.gif" class="bullet_menu" />
					<a href="#">Quản trị câu hỏi</a>
				</li>
			</ul>
		</li>
		<li class="item" id="media">
			<img src="resources/images/media.png" class="icon_menu" />
			<h3>Quản trị ảnh - video</h3>
			<ul class="child">
				<li id="media_1">
					<img src="resources/images/add_news.gif" class="bullet_menu" />
					<a href="#">Danh mục Ảnh</a>
				</li>
				<li id="media_2">
					<img src="resources/images/earth_f.gif" class="bullet_menu" />
					<a href="#">Danh mục Video</a>
				</li>
				<li id="media_3">
					<img src="resources/images/template_f.gif" class="bullet_menu" />
					<a href="#">Quản trị Ảnh</a>
				</li>
				<li id="media_4">
					<img src="resources/images/book_f.gif" class="bullet_menu" />
					<a href="#">Quản trị Video</a>
				</li>
			</ul>
		</li>
		<li class="item" id="catalog">
			<img src="resources/images/catalog.png" class="icon_menu" />
			<h3>Quản trị danh mục</h3>
			<ul class="child">
				<li id="catalog_1">
					<img src="resources/images/home_f.gif" class="bullet_menu" />
					<a href="cm/position/add">Quản trị chức danh</a>
				</li>
				<li id="catalog_2">
					<img src="resources/images/Depart.png" class="bullet_menu" />
					<a href="cm/department/add">Quản trị phòng ban</a>
				</li>
				<li id="catalog_3">
					<img src="resources/images/Depart.png" class="bullet_menu" />
					<a href="cm/documentType/add">Quản trị loại văn bản</a>
				</li>
				<li id="catalog_4">
					<img src="resources/images/Depart.png" class="bullet_menu" />
					<a href="cm/field/add">Quản trị lĩnh vực</a>
				</li>
			</ul>
		</li>
		<li class="item" id="other">
			<img src="resources/images/link.png" class="icon_menu" />
			<h3>Thông tin khác</h3>
			<ul class="child">
				<li id="other_1">
					<img src="resources/images/add_news.gif" class="bullet_menu" />
					<a href="cm/link/add">Quản trị liên kết</a>
				</li>
				<li id="other_2">
					<img src="resources/images/earth_f.gif" class="bullet_menu" />
					<a href="cm/adv/add">Quản trị quảng cáo</a>
				</li>
				<li id="other_3">
					<img src="resources/images/template_f.gif" class="bullet_menu" />
					<a href="cm/intro/add">Thông tin chung</a>
				</li>
				<li id="other_4">
					<img src="resources/images/book_f.gif" class="bullet_menu" />
					<a href="cm/criminalDenouncement">Thông tin liên hệ</a>
				</li>
			</ul>
		</li>
		<li class="item" id="system">
			<img src="resources/images/system.png" class="icon_menu" />
			<h3>Quản trị hệ thống</h3>
			<ul class="child">
				<li id="item_1">
					<img src="resources/images/add_news.gif" class="bullet_menu" />
					<a href="#">Thêm mới người dùng</a>
				</li>
				<li id="item_2">
					<img src="resources/images/earth_f.gif" class="bullet_menu" />
					<a href="#">Phân quyền người dùng</a>
				</li>
				<li id="item_3">
					<img src="resources/images/template_f.gif" class="bullet_menu" />
					<a href="#">Phân quyền nhóm người dùng</a>
				</li>
				<li id="item_4">
					<img src="resources/images/book_f.gif" class="bullet_menu" />
					<a href="#">Quản trị nhóm người sử dụng</a>
				</li>
				<li id="item_5">
					<img src="resources/images/book_f.gif" class="bullet_menu" />
					<a href="#">Danh sách người sử dụng</a>
				</li>
				<li id="item_6">
					<img src="resources/images/UserList.png" class="bullet_menu" />
					<a href="#">Thông tin cá nhân</a>
				</li>
				<li id="item_7">
					<img src="resources/images/User.png" class="bullet_menu" />
					<a href="#">URL</a>
				</li>
				<li id="item_7">
					<img src="resources/images/home_f.gif" class="bullet_menu" />
					<a href="#">Quản trị cấu hình</a>
				</li>
				<li id="item_9">
					<img src="resources/images/home_f.gif" class="bullet_menu" />
					<a href="#">Quản trị giao diện</a>
				</li>
			</ul>
		</li>
	</ul>
</div>