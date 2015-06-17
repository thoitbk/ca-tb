<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="exTag" uri="/WEB-INF/tag/extags.tld" %>

<link href="${ct}/resources/css/table.css" rel="stylesheet" type="text/css" />
<script src="${ct}/resources/js/cm/anim.js" type="text/javascript"></script>
<!-- upload file -->
<link href="${ct}/resources/css/bootstrap.css" type="text/css" rel="stylesheet" />
<link href="${ct}/resources/css/dropfilezone.css" type="text/css" rel="stylesheet" />
<script src="${ct}/resources/js/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ct}/resources/js/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ct}/resources/js/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ct}/resources/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ct}/resources/js/cm/uploader.js" type="text/javascript"></script>

<div class="TieuDe">     
	<div class="TieuDe_ND">
		QUẢN TRỊ VIDEO
	</div>
</div>
<div>
	<c:if test="${not empty msg}">
		<div id="alert" class="alert-box success"><c:out value="${msg}"></c:out></div>
		<c:remove var="msg" scope="session" />
	</c:if>
	<form:form method="post" commandName="videoViewModel">
		<form:errors path="*" cssClass="alert-box warning" element="div" />
		<table>
			<tr>
				<td align="left" width="15%">
					<span class="lblBlack">Chọn danh mục</span>
				</td>
				<td width="60%">
					<form:select path="videoCatalogId" id="videoCatalogId" cssStyle="width: 100%;" cssClass="combobox">
						<form:option value="" label="------ Chọn danh mục video ------"></form:option>
						<form:options items="${videoCatalogMap}"/>
					</form:select>
				</td>
				<td align="left" width="10%" style="padding-left: 2%;"><span class="lblBlack">Số thứ tự</span></td>
				<td>
					<form:input path="sqNumber" id="sqNumber" maxlength="10" cssClass="textbox" cssStyle="width: 100%;" cssErrorClass="textbox_error" />
				</td>
			</tr>
			<tr>
				<td>
					<span class="lblBlack">Tiêu đề video</span>
				</td>
				<td colspan="3">
					<form:input path="caption" id="caption" maxlength="1000" cssClass="textbox" cssStyle="width: 100%;" cssErrorClass="textbox_error" />
				</td>
			</tr>
			<tr>
				<td>
					<span class="lblBlack">Hiển thị</span>
				</td>
				<td colspan="3">
					<form:checkbox path="display"/>
				</td>
			</tr>
			<tr>
				<td>
					<span class="lblBlack">Video</span>
				</td>
				<td align="left" colspan="3">
					<div id="container" style="float: left;">
						<div id="upload-file-container" class="fade well">
							<input id="videoUpload" type="file" name="files[]" data-url="${ct}/cm/video/upload" />
						</div>
						<div id="dropzone" class="fade well">
							<span class="lblBlack">Chọn file</span>
						</div>
					</div>
					<div id="videoBox">
						<div id="uploadedVideo" style="height: 50px; vertical-align: middle; text-align: left; float: left;">
							<c:if test="${videoFile != null}">
								<a href="${videoFile.path}" id="thumbVideo"><img src="${videoFile.path}" alt="Ảnh" style="max-height: 100%; max-width: 100%;" class="thumb" /></a>
							</c:if>
						</div>
						<div id="removeVideoIcon">
							<c:if test="${videoFile != null}">
								<a href="javascript:void(0);" id="removeVideo"><img src="${ct}/resources/images/remove.png" alt="Xóa video" style="width: 20px; height: 20px" /></a>
							</c:if>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td></td>
				<td colspan="3">
					<div style="margin-top: 5px; vertical-align: middle;" id="progress" class="lblBlack"></div>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<input type="submit" value="Thêm mới" class="button" />
				</td>
			</tr>
		</table>
	</form:form>

	<table class="responstable">
		<tr class="header">
			<th width="5%">
				<input type="checkbox" name="selectAll" id="selectAll">
			</th>
			<th width="20%">Video</th>
			<th width="65%">Tiêu đề</th>
			<th width="5%">Hiển thị</th>
			<th>Cập nhật</th>
		</tr>
		<c:forEach items="${videos}" var="video">
			<tr>
				<td>
					<input type="checkbox" name="videoId" id="videoId" value="${video.id}" class="checkbox" />
				</td>
				<td>
					<a href="${video.file}" class="thumb_image"><img src="${video.file}" alt="Ảnh" class="thumb_list" /></a>
				</td>
				<td><c:out value="${video.caption}"></c:out></td>
				<td style="text-align: center;">
					<c:if test="${video.display}">
						<input type="checkbox" checked="checked" disabled="disabled" />
					</c:if>
					<c:if test="${!video.display}">
						<input type="checkbox" disabled="disabled" />
					</c:if>
				</td>
				<td>
					<a href='<exTag:link link="${ct}/cm/video/update/${video.id}" params="${params}" />'>
						<img src="${ct}/resources/images/update.png" alt="Cập nhật" class="update" />
					</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5" style="text-align: right; background-color: #FFF; padding: 0.7em;">
				<exTag:paging pageInfo="${pageInfo}" link="${ct}/cm/video/add" cssClass="page_link" params="${params}" />
			</td>
		</tr>
	    <tr>
	    	<td colspan="5" style="text-align: left; background-color: #FFF; padding: 0.7em;">
	    		<a href="${ct}/cm/video/delete" id="delVideo"><img alt="Xóa" src="${ct}/resources/images/delete.png" class="delete" title="Xóa" /></a>&#8592; Click vào đây để xóa
	    	</td>
	    </tr>
	</table>
</div>