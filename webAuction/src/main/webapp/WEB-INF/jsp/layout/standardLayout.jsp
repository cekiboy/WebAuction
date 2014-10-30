<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<tiles:importAttribute name="title" />

<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%-- 		<title><fmt:message key="${title}" /></title> --%>
		<link href="<c:url value="/css/screen.css"/>" rel="stylesheet" type="text/css" />
		<link href="http://www.wettbewerbe-aktuell.de/grafik/wa_mini_logo.png" rel="shortcut icon" type="image/x-icon" />
		<script src="<c:url value="/js/lib/jquery/jquery-1.10.2.min.js"/>"></script>
		<script src="<c:url value="/js/main.js"/>"></script>
	</head>
	<body>
		<header>
			<div class="top darkNoise">
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<a href="<c:url value="/users/edit"/>"> <fmt:message key="common.header.account"/></a>
					<a href="<c:url value="/j_spring_security_logout" />" > <fmt:message key="common.logout" /></a>
					<a href="#"><fmt:message key="common.loggedin" /> <sec:authentication property="principal.username"/></a>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<a href="<c:url value="/login" />" > <fmt:message key="common.login" /></a>
					<a href="<c:url value="users/new"/>"> <fmt:message key="common.header.register"/></a>
				</sec:authorize>
				<a href="<c:url value="/home?lang=sr" />"><fmt:message key="common.header.language.sr" /></a>
				<a href="<c:url value="/home?lang=en" />"><fmt:message key="common.header.language.en" /></a>
			</div>
			<h1><fmt:message key="common.header.title"/></h1>
			<ul id="mainMenu">
				<li class="highlightcolorBlack"><a class="dashBoard" href="<c:url value="/home"/>"><fmt:message key="common.menu.home"/></a></li>
			    <li class="highlightcolorBlack"><a class="auctions" href="<c:url value="/auctions"/>"><fmt:message key="common.menu.auctions"/></a></li>
				
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<li class="highlightcolorBlack"><a class="myAuctions" href="<c:url value="/auctions/myAuctions"/>"><fmt:message key="common.menu.myAuctions"/></a></li>
					<li class="highlightcolorBlack"><a class="myBids" href="<c:url value="/auctions/myBids"/>"><fmt:message key="common.menu.myBids"/></a></li>
					<li class="highlightcolorBlack"><a class="new" href="<c:url value="/auctions/new"/>" ><fmt:message key="page.auctions.action.addNewAuction" /></a></li>
				</sec:authorize>
				<li class="highlightcolorBlack"><a class="search" href="<c:url value="/search"/>"><fmt:message key="common.menu.search"/></a></li>
			</ul>
		</header>
		<section id="mainContent">
			<tiles:insertAttribute name="content" />
		</section>
		<footer>
			<div class="darkNoise"><fmt:message key="common.footer.copyright"/></div>
		</footer>
	</body>
</html>
