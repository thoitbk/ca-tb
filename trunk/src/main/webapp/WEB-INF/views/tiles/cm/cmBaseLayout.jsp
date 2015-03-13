<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<meta http-equiv="content-type" content="text/html;charset=utf-8" />
<head id="Head1">
	<title>Content Management</title>
	<!-- css -->
	<link href="/resources/css/Admin.css" rel="stylesheet" type="text/css" />
	<link href="/resources/css/accordion.css" rel="stylesheet" type="text/css" />
	<link href="/resources/css/notification.css" rel="stylesheet" type="text/css" />
	<!-- javascript -->
	<script src="/resources/js/jquery-1.7.1.min.js" type="text/javascript"></script>
	<script src="/resources/js/box.js" type="text/javascript"></script>
	<script src="/resources/js/popcalendar.js" type="text/javascript"></script>
	<script src="/resources/js/Utils.js" type="text/javascript"></script>
	<script src="/resources/js/accordion.js" type="text/javascript"></script>
	<script src="/resources/js/cmUtil.js" type="text/javascript"></script>
	<!-- icon -->
	<link href="/resources/images/ControlPanel.ico" rel="shortcut icon" />
</head>
<body>
	<div id="wrapper">
		<div id="top">
			<div ID="Banner">
				<embed width="100%" height="113" align="middle" src="" wmode="transparent" />
			</div>
		</div>
		<div id="pagecontents" class="main">
			<div id="contentMain">
				<div class="admincontentLeft">
					<%@include file="left.jsp" %>
				</div>
				<div class="admincontentRight">
					<div id="TopInfo">
						<div class="InfoLeft">
							<span id="ngay"></span>
						</div>
						<div class="InfoRight">
							<span>Xin chào: ${userInfo.fullName}</span>
							<a href="" ><img border="0px" alt="Thông tin người sử dụng" src="/resources/images/user_info.png" width="30px" /></a>
							<a href="/cm/logout"><img border="0px" alt="Logout"  src="/resources/images/logout.png" width="30px" /></a>        
						</div>
					</div>
					<tiles:insertAttribute name="content" />
				</div>
			</div>
		</div>
		<div id="adminfooter">
			<jsp:include page="bottom.jsp"></jsp:include>
		</div>
	</div>
</body>
</html>