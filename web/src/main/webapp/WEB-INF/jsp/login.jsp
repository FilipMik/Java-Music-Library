<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:message var="title" key="auth.login"/>

<my:pagetemplate title="${title}">
<jsp:attribute name="body">

    <div class="jumbotron">
        <form method="POST" role="form">
            <div class="form-group">
                <label for="username"><f:message key="auth.email"/>:</label>
                <input type="text" id="username" name="username" class="form-control" required/>
                <br>
                <label for="password"><f:message key="auth.password"/>:</label>
                <input type="password" id="password" name="password" class="form-control" required/>
            </div>
            <c:if test="${param.error != null}">
                <p class="alert" style="color:red"><f:message key="auth.error"/></p>
            </c:if>
            <div class="form-actions">
                <button type="submit" value="Log In" class="btn btn-default">
                    <f:message key="auth.login"/>
                </button>
            </div>
        </form>
    </div>
    <p>* Hint for reviewers:</p>
    <p>admin login: s@s.cz, password: aaaa</p>
    <p>user login: k@k.cz, password: aaaa</p>

</jsp:attribute>
</my:pagetemplate>
