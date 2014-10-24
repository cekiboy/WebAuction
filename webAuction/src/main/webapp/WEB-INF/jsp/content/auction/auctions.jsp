<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<h2><fmt:message key="page.auctions.header" /></h2>
<input type="hidden" id="listSize" name="listSize" value="${fn:length(auctions)}"/>
<table class="dataTable">
	<thead>
		<tr>
			<th><fmt:message key="common.label.image" /></th>
			<th><fmt:message key="common.label.name" /></th>
			<th><fmt:message key="common.label.user" /></th>
			<th><fmt:message key="common.label.currentPrice" /></th>
			<th><fmt:message key="common.label.endTime" /></th>
			<th><fmt:message key="common.label.expiresIn" /></th>
			<th>&nbsp;</th>
			<th>&nbsp;</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="auctionIndex" value="${0}"></c:set>
		
		<c:forEach items="${auctions}" var="auction">
		<input type="hidden" id="endTime${auctionIndex}" name="endTime" value="${auction.endTime}"/>
			<tr>
				<td><img src="<c:url value='/images/${auction.images[0].fileName} '/>" class="imageThumbnail"/></td>
				<td><c:out value="${auction.name}"/></td>
				<td><c:out value="${auction.user.username}"/></td>
				<c:choose>
				    <c:when test="${empty auction.auctionBids}">
				       <td><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${auction.startPrice}"/></td>
				    </c:when>
				    <c:otherwise>
				        <td><fmt:formatNumber minFractionDigits="2" maxFractionDigits="2" value="${auction.auctionBids[0].bidValue}"/></td>
				    </c:otherwise>
				</c:choose>
				
				<td><fmt:formatDate type="both" 
            dateStyle="medium" timeStyle="short" value="${auction.endTime}"/></td>
				<td><span id="countdown${auctionIndex}"></span></td>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
					<td class="highlightcolorBlack"><a href="<c:url value="/auctions/details/${auction.id}"/>" class="button"><fmt:message key="common.action.details" /></a></td>
				</sec:authorize>
				
			</tr>
			<c:set var="auctionIndex" value="${auctionIndex+1}"></c:set>
		</c:forEach>
	</tbody>
</table>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="highlightcolorBlack"><a href="<c:url value="/auctions/new"/>" class="button"><fmt:message key="page.auctions.action.addNewAuction" /></a></div>
</sec:authorize>
