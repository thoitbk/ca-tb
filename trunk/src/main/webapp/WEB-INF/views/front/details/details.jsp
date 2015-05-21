<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="Tin_Chi_Tiet">
	<div class="DanhMuc">
		<strong><a href="${ct}/home">${COMMONINFO.homePage}</a> > ${newsCatalog.name}</strong>
	</div>
	<div id="ctl05_pnlDetailNews">
		<div class="TieuDeTin">
			${news.title}
		</div>
		<p class="NgayCapNhat">
			<fmt:formatDate var="postedDate" value="${news.postedDate}" pattern="dd/MM/yyyy" />
			${COMMONINFO.postedDate} ${postedDate}
		</p>
		<span class="MoTa">${news.summary}</span>
		<div class="space">
		</div>
		<span class="news-detail">
			${content.content}
		</span>
		<div class="space">
		</div>
		<div class="Tacgia">
			${COMMONINFO.author} ${news.author}
		</div>
		<div class="action-print-mail">
			<span>
				<a href="javascript:window.print()"><img src="/Images/printer.gif">${COMMONINFO.print}</a>
			</span>
		</div>
		<div class="space">
		</div>
		<div id="Tin_Chi_Tiet_Khac">
			<div class="TinCungChuDe">
				${COMMONINFO.sameSubjectTitle}
			</div>
			<div>
				<div id="ctl05_otherNews">
					<ul>
						<li>
							<a href="/Tin-Tuc/Tin_Hoat_Dong/104_Cong-an-tinh-So-ket-5-thang-hoat-dong-cua-Cong-Thong-tin-dien-tu-va-tap-huan-nghiep-vu-bao-chi"><span title="cssbody=[boxbody] singleclickstop=[on] cssheader=[boxheader] header=[Công an tỉnh Sơ kết 5 tháng hoạt động của Cổng Thông tin điện tử và tập huấn nghiệp vụ báo chí] img=[<img src=&quot;/Upload/13114249_5-thang.JPG&quot; align=&quot;LEFT&quot;/>]  body=[]"> Công an tỉnh Sơ kết 5 tháng hoạt động của Cổng Thông tin điện tử và tập huấn nghiệp vụ báo chí<i>(04/11/2013)</i></span></a>
						</li>
					</ul>
				</div>
			</div>
			<div class="XemChiTiet">
				<a href="/Tin-Tuc/Tin_Hoat_Dong">  Xem chi tiết &gt;&gt;  </a>
			</div>
		</div>
	</div>
</div>