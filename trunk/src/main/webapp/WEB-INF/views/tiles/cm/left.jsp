<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="/WEB-INF/tag/functions.tld"%>

<c:set var="menu" value="${f:getActiveMenuId(pageContext.request)}" scope="request"></c:set>
<c:set var="menuId" value="${menu[0]}" scope="request"></c:set>
<c:set var="menuItemId" value="${menu[1]}" scope="request"></c:set>

<div id="vertical-menu">
	<ul class="accordion">
		<li class="item ${f:getMenuClass(menuId, 'info', 'selected_item')}" id="info">
			<img src="${ct}/resources/images/info.png" class="icon_menu" />
			<h3>Quản trị tin tức</h3>
			<ul class="child">
				<li id="info_1" class="${f:getMenuClass(menuItemId, 'info_1', 'selected_subitem')}">
					<img src="${ct}/resources/images/add_news.gif" class="bullet_menu" />
					<a href="#">Thêm mới tin</a>
				</li>
				<li id="info_2" class="${f:getMenuClass(menuItemId, 'info_2', 'selected_subitem')}">
					<img src="${ct}/resources/images/earth_f.gif" class="bullet_menu" />
					<a href="#">Quản trị tin</a>
				</li>
				<li id="info_3" class="${f:getMenuClass(menuItemId, 'info_3', 'selected_subitem')}">
					<img src="${ct}/resources/images/template_f.gif" class="bullet_menu" />
					<a href="#">Duyệt tin</a>
				</li>
				<li id="info_4" class="${f:getMenuClass(menuItemId, 'info_4', 'selected_subitem')}">
					<img src="${ct}/resources/images/book_f.gif" class="bullet_menu" />
					<a href="#">Quản trị danh mục tin</a>
				</li>
			</ul>
		</li>
		<li class="item ${f:getMenuClass(menuId, 'doc', 'selected_item')}" id="doc">
			<img src="${ct}/resources/images/doc.png" class="icon_menu" />
			<h3>Quản trị văn bản</h3>
			<ul class="child">
				<li id="doc_1" class="${f:getMenuClass(menuItemId, 'doc_1', 'selected_subitem')}">
					<img src="${ct}/resources/images/add_news.gif" class="bullet_menu" />
					<a href="#">Quản trị TTHC</a>
				</li>
				<li id="doc_2" class="${f:getMenuClass(menuItemId, 'doc_2', 'selected_subitem')}">
					<img src="${ct}/resources/images/earth_f.gif" class="bullet_menu" />
					<a href="#">Quản trị văn bản</a>
				</li>
			</ul>
		</li>
		<li class="item ${f:getMenuClass(menuId, 'question', 'selected_item')}" id="question">
			<img src="${ct}/resources/images/question.png" class="icon_menu" />
			<h3>Quản trị hỏi đáp</h3>
			<ul class="child">
				<li id="question_1" class="${f:getMenuClass(menuItemId, 'question_1', 'selected_subitem')}">
					<img src="${ct}/resources/images/add_news.gif" class="bullet_menu" />
					<a href="#">Quản trị DM hỏi đáp</a>
				</li>
				<li id="question_2" class="${f:getMenuClass(menuItemId, 'question_2', 'selected_subitem')}">
					<img src="${ct}/resources/images/earth_f.gif" class="bullet_menu" />
					<a href="#">Quản trị câu hỏi</a>
				</li>
			</ul>
		</li>
		<li class="item ${f:getMenuClass(menuId, 'media', 'selected_item')}" id="media">
			<img src="${ct}/resources/images/media.png" class="icon_menu" />
			<h3>Quản trị ảnh - video</h3>
			<ul class="child">
				<li id="media_1" class="${f:getMenuClass(menuItemId, 'media_1', 'selected_subitem')}">
					<img src="${ct}/resources/images/add_news.gif" class="bullet_menu" />
					<a href="#">Danh mục Ảnh</a>
				</li>
				<li id="media_2" class="${f:getMenuClass(menuItemId, 'media_2', 'selected_subitem')}">
					<img src="${ct}/resources/images/earth_f.gif" class="bullet_menu" />
					<a href="#">Danh mục Video</a>
				</li>
				<li id="media_3" class="${f:getMenuClass(menuItemId, 'media_3', 'selected_subitem')}">
					<img src="${ct}/resources/images/template_f.gif" class="bullet_menu" />
					<a href="#">Quản trị Ảnh</a>
				</li>
				<li id="media_4" class="${f:getMenuClass(menuItemId, 'media_4', 'selected_subitem')}">
					<img src="${ct}/resources/images/book_f.gif" class="bullet_menu" />
					<a href="#">Quản trị Video</a>
				</li>
			</ul>
		</li>
		<li class="item ${f:getMenuClass(menuId, 'catalog', 'selected_item')}" id="catalog">
			<img src="${ct}/resources/images/catalog.png" class="icon_menu" />
			<h3>Quản trị danh mục</h3>
			<ul class="child">
				<li id="catalog_1" class="${f:getMenuClass(menuItemId, 'catalog_1', 'selected_subitem')}">
					<img src="${ct}/resources/images/home_f.gif" class="bullet_menu" />
					<a href="${ct}/cm/position/add">Quản trị chức danh</a>
				</li>
				<li id="catalog_2" class="${f:getMenuClass(menuItemId, 'catalog_2', 'selected_subitem')}">
					<img src="${ct}/resources/images/Depart.png" class="bullet_menu" />
					<a href="${ct}/cm/department/add">Quản trị phòng ban</a>
				</li>
				<li id="catalog_3" class="${f:getMenuClass(menuItemId, 'catalog_3', 'selected_subitem')}">
					<img src="${ct}/resources/images/Depart.png" class="bullet_menu" />
					<a href="${ct}/cm/documentType/add">Quản trị loại văn bản</a>
				</li>
				<li id="catalog_4" class="${f:getMenuClass(menuItemId, 'catalog_4', 'selected_subitem')}">
					<img src="${ct}/resources/images/Depart.png" class="bullet_menu" />
					<a href="${ct}/cm/field/add">Quản trị lĩnh vực</a>
				</li>
			</ul>
		</li>
		<li class="item ${f:getMenuClass(menuId, 'other', 'selected_item')}" id="other">
			<img src="${ct}/resources/images/link.png" class="icon_menu" />
			<h3>Thông tin khác</h3>
			<ul class="child">
				<li id="other_1" class="${f:getMenuClass(menuItemId, 'other_1', 'selected_subitem')}">
					<img src="${ct}/resources/images/add_news.gif" class="bullet_menu" />
					<a href="${ct}/cm/link/add">Quản trị liên kết</a>
				</li>
				<li id="other_2" class="${f:getMenuClass(menuItemId, 'other_2', 'selected_subitem')}">
					<img src="${ct}/resources/images/earth_f.gif" class="bullet_menu" />
					<a href="${ct}/cm/adv/add">Quản trị quảng cáo</a>
				</li>
				<li id="other_3" class="${f:getMenuClass(menuItemId, 'other_3', 'selected_subitem')}">
					<img src="${ct}/resources/images/template_f.gif" class="bullet_menu" />
					<a href="${ct}/cm/intro/add">Thông tin chung</a>
				</li>
				<li id="other_4" class="${f:getMenuClass(menuItemId, 'other_4', 'selected_subitem')}">
					<img src="${ct}/resources/images/book_f.gif" class="bullet_menu" />
					<a href="${ct}/cm/criminalDenouncement">Thông tin liên hệ</a>
				</li>
			</ul>
		</li>
		<li class="item ${f:getMenuClass(menuId, 'system', 'selected_item')}" id="system">
			<img src="${ct}/resources/images/system.png" class="icon_menu" />
			<h3>Quản trị hệ thống</h3>
			<ul class="child">
				<li id="system_1" class="${f:getMenuClass(menuItemId, 'system_1', 'selected_subitem')}">
					<img src="${ct}/resources/images/add_news.gif" class="bullet_menu" />
					<a href="${ct}/cm/user/add">Thêm mới người dùng</a>
				</li>
				<li id="system_2" class="${f:getMenuClass(menuItemId, 'system_2', 'selected_subitem')}">
					<img src="${ct}/resources/images/earth_f.gif" class="bullet_menu" />
					<a href="${ct}/cm/manageUserRole">Phân quyền người dùng</a>
				</li>
				<li id="system_3" class="${f:getMenuClass(menuItemId, 'system_3', 'selected_subitem')}">
					<img src="${ct}/resources/images/template_f.gif" class="bullet_menu" />
					<a href="${ct}/cm/showPermission">Phân quyền nhóm người dùng</a>
				</li>
				<li id="system_4" class="${f:getMenuClass(menuItemId, 'system_4', 'selected_subitem')}">
					<img src="${ct}/resources/images/book_f.gif" class="bullet_menu" />
					<a href="${ct}/cm/role/add">Quản trị nhóm người sử dụng</a>
				</li>
				<li id="system_5" class="${f:getMenuClass(menuItemId, 'system_5', 'selected_subitem')}">
					<img src="${ct}/resources/images/book_f.gif" class="bullet_menu" />
					<a href="#">Danh sách người sử dụng</a>
				</li>
				<li id="system_6" class="${f:getMenuClass(menuItemId, 'system_6', 'selected_subitem')}">
					<img src="${ct}/resources/images/UserList.png" class="bullet_menu" />
					<a href="#">Thông tin cá nhân</a>
				</li>
				<li id="system_7" class="${f:getMenuClass(menuItemId, 'system_7', 'selected_subitem')}">
					<img src="${ct}/resources/images/User.png" class="bullet_menu" />
					<a href="${ct}/cm/permission/add">Quản trị quyền</a>
				</li>
				<li id="system_8" class="${f:getMenuClass(menuItemId, 'system_8', 'selected_subitem')}">
					<img src="${ct}/resources/images/home_f.gif" class="bullet_menu" />
					<a href="#">Quản trị cấu hình</a>
				</li>
				<li id="system_9" class="${f:getMenuClass(menuItemId, 'system_9', 'selected_subitem')}">
					<img src="${ct}/resources/images/home_f.gif" class="bullet_menu" />
					<a href="#">Quản trị giao diện</a>
				</li>
			</ul>
		</li>
	</ul>
</div>