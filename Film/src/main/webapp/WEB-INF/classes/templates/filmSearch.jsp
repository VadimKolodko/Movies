<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="uri" value="${req.requestURI}" />
<c:set var="url">${req.requestURL}</c:set>

<html>
<style>
    <%@include file='/resources/layout/styles/layout.css' %>
    <%@include file='/resources/layout/styles/framework.css' %>
    <%@include file='/resources/layout/styles/font-awesome.min.css' %>
    table, tbody, tr, th, td{background-color: rgba(0,0,0,0.0) !important;border: none;}
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Movies</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body id="top">
<div class="bgded" style="background-image:url('<c:url value='${filmInfo.imageId}'/>');">
    <div class="bgded">
        <div class="wrapper row1">
            <header id="header" class="hoc clear">
                <div id="logo" class="fl_left">
                    <h1><a href="/film/index">Best Movies</a></h1>
                </div>
                <nav id="mainav" class="fl_right">
                    <ul class="clear">
                        <c:if test="${SessionUser.userId != null}">
                            <c:if test="${SessionUser.userRole == 'Admin'}">
                                <li><a href="/film/add">Add movie</a></li>
                            </c:if>
                            <li><a href="/film">Back main</a></li>
                            [
                            <li class="active"><a href="">${SessionUser.userName}</a></li>
                            <li><a href="/login/delete">Exit</a></li>]
                        </c:if>
                        <c:if test="${SessionUser.userId == null}">
                            <li><a href="/login">Login</a></li>
                        </c:if>
                    </ul>
                </nav>
            </header>
        </div>
        <div class="wrapper">
            <article id="pageintro" class="hoc clear">
                <div class="transboxInfo">
                    <h1>Search</h1>
                    <footer id="footer" class="hoc clear">
                        <div class="one_third first">
                            <form:form method="POST" action = "/film/search" modelAttribute="searchSettings">
                                <table width="100%" cellspacing="0" cellpadding="0">
                                    <tr>
                                        <td><form:label path = "filtr">Filter</form:label></td>
                                        <td><form:select path="filtr">
                                            <form:option value="Comedy"/>
                                            <form:option value="Action"/>
                                            <form:option value="Thriller"/>
                                            <form:option value="Horror"/>
                                        </form:select></td>
                                    </tr>
                                    <tr>
                                        <td><form:label path = "sort">Sorting</form:label></td>
                                        <td><form:select path="sort">
                                            <form:option value="Date increase"/>
                                            <form:option value="Date descending"/>
                                            <form:option value="Average score increase"/>
                                            <form:option value="Average score decrease"/>
                                        </form:select></td>
                                    </tr>
                                    <tr>
                                        <td><form:label path = "rat">Rated</form:label></td>
                                        <td><form:select path="rat">
                                            <form:option value="Rated by me"/>
                                            <form:option value="Not rated by me"/>
                                        </form:select></td>
                                    </tr>
                                    <tr>
                                        <td colspan = "2">
                                            <button type="submit" value="submit">Search</button>
                                        </td>
                                    </tr>
                                </table>
                            </form:form>
                        </div>
                    </footer>
                </div>
            </article>
        </div>
    </div>
</div>
</body>
</html>