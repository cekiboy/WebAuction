<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<h2><fmt:message key="page.addEditUser.header" /></h2>
<c:url var="action" value="/users" />
<form:form id="formUser" action="${action}" method="post" modelAttribute="user">
	<fieldset>
		<form:hidden path="id" />
		<form:hidden path="enabled" />
		<form:hidden path="authority" />
		<form:label path="name"><fmt:message key="page.addEditUser.label.name" /></form:label>
		<form:input path="name" cssErrorClass="error" />
		<form:errors path="name" cssClass="errorMessage" /><br/>
		<form:label path="username"><fmt:message key="page.login.label.username" /></form:label>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
				<form:input path="username" cssErrorClass="error" readonly="true" />
		</sec:authorize>
		<sec:authorize access="isAnonymous()">
				<form:input path="username" cssErrorClass="error" /> <c:out value="${auction.auctionBids[0].bidValue}"/><div class="errorMessage">${usernameExistsError}</div>
		</sec:authorize>
		<form:errors path="username" cssClass="errorMessage" /><br/>
		<form:label path="password"><fmt:message key="page.login.label.password" /></form:label>
		<form:password path="password" cssErrorClass="error" showPassword="TRUE" />
		<form:errors path="password" cssClass="errorMessage" /><br/>
		<form:label path="email"><fmt:message key="page.addEditUser.label.eMail" /></form:label>
		<form:input path="email" cssErrorClass="error" />
		<form:errors path="email" cssClass="errorMessage" />
	</fieldset>
	<div class="highlightcolorBlack">
		<button type="submit" name="save" class="button"><fmt:message key="common.action.save" /></button>
		<button type="submit" name="cancel" class="button"><fmt:message key="common.action.cancel" /></button>
	</div>
</form:form>
