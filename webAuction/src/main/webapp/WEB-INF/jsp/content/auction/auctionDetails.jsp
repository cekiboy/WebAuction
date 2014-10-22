<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<h2><c:out value="${auction.name}"/></h2>
<div class="mainDiv">
	<div class= "left">
		<table>
			<c:set var="imageIndex" value="${1}"></c:set>
			<c:forEach items="${auction.images}" var="image">
            <tr id="gallery">
            	<c:if test="${imageIndex==1}">
                	<td id="${imageIndex}" class="${imageIndex}"><img src="<c:url value='/images/${image.fileName} '/>" class="imageDetails"/></td>
                </c:if>
                <c:if test="${imageIndex!=1}">
                	<td id="${imageIndex}" class="${imageIndex}" style="display:none;"><img src="<c:url value='/images/${image.fileName} '/>" class="imageDetails"/></td>
                </c:if>
            </tr>
            <tr id="galleryLinks">
            	<c:if test="${imageIndex==1}">
            		<td id="link${imageIndex}" class="link${imageIndex}" style="display:none;"><a href="#" onclick="browseThrough(${imageIndex})" >image ${imageIndex}</a></td>
                </c:if>
                <c:if test="${imageIndex!=1}">
                	<td id="link${imageIndex}" class="link${imageIndex}"><a href="#" onclick="browseThrough(${imageIndex})" >image ${imageIndex}</a></td>
                </c:if>
            </tr>
            <c:set var="imageIndex" value="${imageIndex+1}"></c:set>
            </c:forEach>
        </table>
		<fmt:message key="common.label.description" />
		<c:out value="${auction.description}"/><br>
		<fmt:message key="common.label.user" />
		<c:out value="${auction.user.username}"/><br>
		
		<fmt:message key="common.label.buyOutPrice" />
		<c:out value="${auction.buyOutPrice}"/><br>
		<fmt:message key="common.label.startTime" />
		<fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${auction.startTime}"/><br>
		<fmt:message key="common.label.endTime" />
		<fmt:formatDate type="both" dateStyle="medium" timeStyle="short" value="${auction.endTime}"/><br>
		
	</div>
	<div class="upperRight">
		<b><fmt:message key="common.label.currentPrice" /></b>
		<c:choose>
		    <c:when test="${empty auction.auctionBids}">
		       <c:out value="${auction.startPrice}"/><br>
		    </c:when>
		    <c:otherwise>
		        <c:out value="${auction.auctionBids[0].bidValue}"/><br>
		    </c:otherwise>
		</c:choose>
		
		<c:url var="action" value="/auctions" />
		<form:form id="formAuctionBid" action="${action}" method="post" modelAttribute="auctionBid">
			<fieldset>
				<form:hidden path="id" />
				<form:label path="bidValue"><fmt:message key="common.label.makeBid" /></form:label>
				<form:input path="bidValue" cssErrorClass="error" /><div class="errorMessage">${smallBidError}</div>
				<form:errors path="bidValue" cssClass="errorMessage" /><br>
			</fieldset>
			<div class="highlightcolorBlack">
				<button type="submit" name="auctionId" value="${auction.id}" class="button"><fmt:message key="common.label.addBid"/></button>
				<button type="submit" name="cancel" class="button"><fmt:message key="common.action.cancel" /></button>
			</div>
		</form:form>
		<br>
		<br>
		<br>
		<c:if test="${not empty auction.auctionBids}">
			
	        <fmt:message key="common.label.allBids" />
			    
			<div class = "scroll">
				<table class="dataTable">
					<thead>
						<tr>
							<th><fmt:message key="common.label.bidTime" /></th>
							<th><fmt:message key="common.label.bidUser" /></th>
							<th><fmt:message key="common.label.bidValue" /></th>
							<th>&nbsp;</th>
							<th>&nbsp;</th>
						</tr>
					</thead>
					<tbody >
						<c:forEach items="${auction.auctionBids}" var="auctionBid">
							<tr>
								<td><c:out value="${auctionBid.bidDate}"/></td>
								<td><c:out value="${auctionBid.user.username}"/></td>
								<td><c:out value="${auctionBid.bidValue}"/></td>
							</tr>
						</c:forEach>
					</tbody>
					</table>
			</div>
			</c:if>	
	<div class="bordered">
		<b><fmt:message key="common.label.buyOutPrice" /></b>
		<c:out value="${auction.buyOutPrice}"/><br>
		<c:choose>
		    <c:when test="${empty auction.auctionBids}">
		       <div class="highlightcolorBlack"><a href="<c:url value="/auctions/buyNow/${auction.id}"/>" class="button"><fmt:message key="common.action.buyNow" /></a></div>	
		    </c:when>
		    <c:otherwise>
		        <c:if test="${auction.auctionBids[0].bidValue < auction.buyOutPrice}">
					<div class="highlightcolorBlack"><a href="<c:url value="/auctions/buyNow/${auction.id}"/>" class="button"><fmt:message key="common.action.buyNow" /></a></div>	
				</c:if>
		    </c:otherwise>
		</c:choose>
		
		</div>
	</div>
</div>