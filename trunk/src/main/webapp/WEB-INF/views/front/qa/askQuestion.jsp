<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="/WEB-INF/tag/functions.tld" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<link href="${ct}/resources/css/form.css" rel="stylesheet" type="text/css" />

<div id="Tin_Chi_Tiet">
	<div class="DanhMuc">
		<strong>ĐẶT CÂU HỎI</strong>
	</div>
	<form:form method="post" commandName="createCommentViewModel">
		<form:errors path="*" cssClass="alert-box warning" element="div" />
		<table style="width: 90%; font-size: 12px; border-spacing: 0 5px; margin-top: 20px;" align="center">
			<tr>
				<td>Tiêu đề câu hỏi</td>
				<td>
					<form:input path="title" id="title" maxlength="1000" cssClass="textbox" cssStyle="width: 100%;" cssErrorClass="textbox_error" />
				</td>
			</tr>
			<tr>
				<td>Tên người hỏi</td>
				<td>
					<form:input path="name" id="name" maxlength="200" cssClass="textbox" cssStyle="width: 100%;" cssErrorClass="textbox_error" />
				</td>
			</tr>
			<tr>
				<td>Địa chỉ</td>
				<td>
					<form:input path="address" id="address" maxlength="500" cssClass="textbox" cssStyle="width: 100%;" cssErrorClass="textbox_error" />
				</td>
			</tr>
			<tr>
				<td>Số điện thoại</td>
				<td>
					<form:input path="phoneNumber" id="phoneNumber" maxlength="200" cssClass="textbox" cssStyle="width: 100%;" cssErrorClass="textbox_error" />
				</td>
			</tr>
			<tr>
				<td>Email</td>
				<td>
					<form:input path="email" id="email" maxlength="200" cssClass="textbox" cssStyle="width: 100%;" cssErrorClass="textbox_error" />
				</td>
			</tr>
			<tr>
				<td>Chủ đề câu hỏi</td>
				<td>
					<form:select path="qaCatalogId" id="qaCatalogId" cssStyle="width: 100%;" cssClass="combobox">
						<form:options items="${qaCatalogMap}"/>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>Nội dung câu hỏi</td>
				<td>
					<form:textarea path="content" id="content" rows="24" cols="50" cssClass="textmulti" cssStyle="width:100%;" cssErrorClass="textmulti_error"/>
				</td>
			</tr>
			<tr>
				<td>Mã xác nhận</td>
				<td>
					<img alt="" src="${ct}/captcha-generator">
				</td>
			</tr>
			<tr>
				<td>Nhập mã xác nhận</td>
				<td>
					<form:input path="captcha" id="captcha" maxlength="10" cssClass="textbox" cssStyle="width: 200px" cssErrorClass="textbox_error" />
				</td>
			</tr>
			<tr>
				<td colspan="2" style="width: 10px;">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="Gửi câu hỏi" class="button" />
				</td>
			</tr>
		</table>
	</form:form>
</div>