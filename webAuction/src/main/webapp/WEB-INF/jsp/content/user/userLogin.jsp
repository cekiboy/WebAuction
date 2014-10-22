<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<h2><fmt:message key="common.login" /></h2>
<sec:authorize access="isAuthenticated()">
	<c:redirect url="/home"/>
</sec:authorize>
<form name='f' action="j_spring_security_check" method='POST'>
	<table>
	    <tbody>
		    <tr><td><fmt:message key="page.login.label.username" /></td><td><input type="text" name="j_username" value=""></td></tr>
		    <tr><td><fmt:message key="page.login.label.password" /></td><td><input type="password" name="j_password"></td></tr>
		    <tr><td><input type="checkbox" name="_spring_security_remember_me"><fmt:message key="page.login.label.rememberMe" /></td></tr>
		<!--     <tr><td colspan="2"><input name="submit" type="submit"></td></tr> -->
		<!--     <tr><td colspan="2"><input name="reset" type="reset"></td></tr> -->
	  </tbody>
	</table>
	<div class="highlightcolorBlack">
		<button type="submit" name="submit" class="button"><fmt:message key="common.login" /></button>
		<button type="reset" name="reset" class="button"><fmt:message key="common.reset" /></button>
	</div>
</form>