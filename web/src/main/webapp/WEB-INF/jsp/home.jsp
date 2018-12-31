<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
<jsp:attribute name="body">

    <div class="jumbotron">
        <h1>Welcome to the Music Town!</h1>
        <br/>
        <p>Please log in before you start exploring our library</p>
        <br/>
        <div class="button">
            <my:a href="${pageContext.request.contextPath}/login"
                  class="btn btn-primary btn-lg">Go to login</my:a>
        </div>
        <br/>
    </div>

</jsp:attribute>
</my:pagetemplate>
