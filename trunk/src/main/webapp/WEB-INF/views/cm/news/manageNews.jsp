<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="exTag" uri="/WEB-INF/tag/extags.tld" %>
<link href="${ct}/resources/css/table.css" rel="stylesheet" type="text/css" />
<script src="${ct}/resources/js/cm/anim.js" type="text/javascript"></script>

<!-- datetime picker -->
<link href="${ct}/resources/css/jquery-ui.css" rel="stylesheet" type="text/css" />
<script src="${ct}/resources/js/jquery-ui.js" type="text/javascript"></script>
<script src="${ct}/resources/js/datetimepicker-vi.js" type="text/javascript"></script>

<script>
	jQuery(function ($) {
		$( "#from" ).datepicker();
		$( "#to" ).datepicker();
		$("#clear_1").click(function() {
			$("#from").val('');
		})
		$("#clear_2").click(function() {
			$("#to").val('');
		})
	});
</script>

<div class="TieuDe">     
	<div class="TieuDe_ND">
		QUẢN TRỊ TIN TỨC
	</div>
</div>
<div>
	<form:form commandName="searchNewsViewModel" method="get" id="searchNewsViewModel">
		<table class="center" style="width: 90%">
			<tr>
				<td align="left" width="15%">
					<span class="lblBlack">Danh mục tin tức</span>
				</td>
				<td colspan="3">
					<form:select path="newsCatalogId" id="newsCatalogId" cssStyle="width: 100%;" cssClass="combobox">
						<form:option value="" label="------ Chọn danh mục tin tức ------"></form:option>
						<form:options items="${newsCatalogs}"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<td align="left">
					<span class="lblBlack">Trạng thái tin</span>
				</td>
				<td colspan="3">
					<form:select path="newsStatus" id="newsStatus" cssStyle="width: 100%;" cssClass="combobox">
						<form:option value="" label="------ Chọn trạng thái tin ------"></form:option>
						<form:options items="${newsStatuses}"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<td align="left">
					<span class="lblBlack">Tin nóng</span>
				</td>
				<td colspan="3">
					<form:checkbox path="hotNews" id="hotNews" />
				</td>
			</tr>
			<tr>
				<td align="left">
					<span class="lblBlack">Tác giả</span>
				</td>
				<td colspan="3">
					<form:input path="author" id="author" cssClass="textbox" cssStyle="width: 100%;" cssErrorClass="textbox_error" />
				</td>
			</tr>
			<tr>
				<td align="left">
					<span class="lblBlack">Tiêu đề tin</span>
				</td>
				<td colspan="3">
					<form:input path="title" id="title" cssClass="textbox" cssStyle="width: 100%;" cssErrorClass="textbox_error" />
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<span class="lblBlack">Ngày đăng</span>
				</td>
			</tr>
			<tr>
				<td align="right" width="15%">
					<span class="lblBlack">Từ ngày </span>
				</td>
				<td width="30%">
					<form:input path="from" id="from" cssClass="textbox" cssStyle="width: 80%;" cssErrorClass="textbox_error" />
					<img src="${ct}/resources/images/clear.png" style="width: 1.2em; height: 1.2em; vertical-align: middle;" alt="Xóa" id="clear_1" />
				</td>
				<td align="right" width="15%">
					<span class="lblBlack">Đến ngày </span>
				</td>
				<td width="30%">
					<form:input path="to" id="to" cssClass="textbox" cssStyle="width: 80%;" cssErrorClass="textbox_error" />
					<img src="${ct}/resources/images/clear.png" style="width: 1.2em; height: 1.2em; vertical-align: middle;" alt="Xóa" id="clear_2" />
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="submit" value="Tìm kiếm" class="button" />
				</td>
			</tr>
		</table>
	</form:form>

	<table class="responstable">
		<tr class="header">
			<th width="5%">
				<input type="checkbox" name="selectAll" id="selectAll">
			</th>
			<th width="15%">Tên danh mục</th>
			<th width="60%">Tiêu đề tin</th>
			<th width="10%">Ngày đăng tin</th>
			<th width="5%">Tin nóng</th>
			<th width="10%">Trạng thái</th>
			<th>Cập nhật</th>
		</tr>
		<c:forEach items="${newses}" var="news">
			<tr>
				<td width="5%">
					<input type="checkbox" name="newsId" id="newsId" value="${news.id}" class="checkbox" />
				</td>
				<td width="15%"><c:out value="${news.newsCatalog.name}"></c:out></td>
				<td width="50%"><a href="" class="news_title"><c:out value="${news.title}"></c:out></a></td>
				<td width="10%">
					<fmt:formatDate var="postedDate" value="${news.postedDate}" pattern="dd/MM/yyyy" />
					<c:out value="${postedDate}"></c:out>
				</td>
				<td width="5%" style="text-align: center;">
					<c:if test="${news.hotNews}">
						<input type="checkbox" checked="checked" disabled="disabled" />
					</c:if>
					<c:if test="${!news.hotNews}">
						<input type="checkbox" disabled="disabled" />
					</c:if>
				</td>
				<td width="10%">
					<c:out value="${newsStatuses[news.status]}"></c:out>
				</td>
				<td>
					
					<a href='<exTag:link link="${ct}/cm/news/update/${news.id}" params="${params}" />'>
						<img src="${ct}/resources/images/update.png" alt="Cập nhật" class="update" />
					</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="7" style="text-align: right; background-color: #FFF; padding: 0.7em;">
				<exTag:paging pageInfo="${pageInfo}" link="${ct}/cm/news/manage" cssClass="page_link" params="${params}" />
			</td>
		</tr>
	    <tr>
	    	<td colspan="7" style="text-align: left; background-color: #FFF; padding: 0.7em;">
	    		<a href="${ct}/cm/news/delete" id="delNews"><img alt="Xóa" src="${ct}/resources/images/delete.png" class="delete" title="Xóa" /></a>&#8592; Click vào đây để xóa
	    	</td>
	    </tr>
	</table>
</div>