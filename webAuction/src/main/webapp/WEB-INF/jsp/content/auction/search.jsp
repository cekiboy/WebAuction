<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<h2 style="text-align: left" ><fmt:message key="page.search.header" /></h2>


	<c:url var="action" value="/search"/>
	<form:form id="formSearch" action="${action}" method="post" modelAttribute="searchDTO">
	<fieldset>
		
		<form:select path="searchType" multiple="false">
		    <form:options items="${searchTypes}" />
		</form:select>
		
		
		<form:input path="searchValue" cssErrorClass="error" />
		<form:errors path="searchValue" cssClass="errorMessage" /><br>
	</fieldset>
	<div class="highlightcolorBlack">
		<button type="submit" name="search" class="button"><fmt:message key="common.action.search" /></button>
	</div>
</form:form>	
