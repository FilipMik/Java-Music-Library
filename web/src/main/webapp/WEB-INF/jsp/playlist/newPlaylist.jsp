<%--
  Author: Klara Minsterova
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%@ page contentType="text/html;charset=UTF-8" language="java" %>

<my:pagetemplate title="Playlist">
<jsp:attribute name="body">
    <form:form method="post" action="${pageContext.request.contextPath}/playlist/create" modelAttribute="playlist" cssClass="form-horizontal">

        <div class="form-group ${name_error?'has-error':''}">
            <form:label path="title" cssClass="col-sm-2 control-label">Title</form:label>
            <div class="col-sm-10">
                <form:input path="title" cssClass="form-control"/>
                <form:errors path="title" cssClass="help-block"/>
            </div>
        </div>

        <button class="btn btn-primary col-md-offset-2" type="submit">Create playlist</button>

    </form:form>
</jsp:attribute>
</my:pagetemplate>
