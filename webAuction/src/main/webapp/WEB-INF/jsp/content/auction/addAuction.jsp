<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<h2><fmt:message key="page.addAuction.header" /></h2>
<c:url var="action" value="/auctions" />
<form:form id="formAuction" action="${action}" method="post" 
										modelAttribute="auctionFilesDTO" enctype="multipart/form-data">
	<fieldset>
		<form:hidden path="auction.id" />
		<form:label path="auction.name"><fmt:message key="common.label.name" /></form:label>
		<form:input path="auction.name" cssErrorClass="error" />
		<form:errors path="auction.name" cssClass="errorMessage" /><br>
		<form:label path="auction.description"><fmt:message key="common.label.description" /></form:label>
		<form:textarea path="auction.description" cssErrorClass="error" />
		<form:errors path="auction.description" cssClass="errorMessage" /><br>
		<form:label path="auction.startPrice"><fmt:message key="common.label.startPrice" /></form:label>
		<form:input id="startPrice" path="auction.startPrice" cssErrorClass="error" />
		<form:errors path="auction.startPrice" cssClass="errorMessage" /><br>
		<form:label path="auction.buyOutPrice"><fmt:message key="common.label.buyOutPrice" /></form:label>
		<form:input id="buyOutPrice" path="auction.buyOutPrice" cssErrorClass="error" />
		<form:errors path="auction.buyOutPrice" cssClass="errorMessage" /><br>
		<label><fmt:message key="common.label.auctionDuration"/></label>
		<select name="duration">
			<option value="1">1</option>
			<option value="5">5</option>
			<option value="10">10</option>
			<option value="15">15</option>
			<option value="20">20</option>
			<option value="30">30</option>
		</select><br>
	    <table id="fileTable"></table>
	    <form:errors path="files" cssClass="errorMessage" /><br><br>
	    <input id="addFile" type="button" value="<fmt:message key="common.button.addAnother"/>" />
	</fieldset>
	
	<div class="highlightcolorBlack">
		<button type="submit" name="save" class="button"><fmt:message key="common.action.save" /></button>
		<button type="submit" name="cancel" class="button"><fmt:message key="common.action.cancel" /></button>
	</div>
</form:form>


