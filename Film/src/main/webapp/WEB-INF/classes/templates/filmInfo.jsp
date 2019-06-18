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
                            <li><a href="/film/search">Search</a></li>
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
                    <table width="100%" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="45%">
                                <img src="<c:url value='${filmInfo.imageId}'/>"/><br />
                            </td>
                            <td width="55%">
                                <h1 class="heading"><c:out value="${filmInfo.name}"/></h1>
                                <p>Release: <c:out value="${filmInfo.fDate}" /></p><br />
                                <p>Genre: <c:out value="${filmInfo.genreFilm}"/></p><br />
                                <p>Rate: <c:out value="${filmInfo.averageRate}"/></p><br />
                                <p>About: <c:out value="${filmInfo.aboutFilm}" /></p><br />
                                <c:if test="${rateInfoFilm != null}">
                                    <form method="GET" action="/rate/edit">
                                        <input type="range" min="0" max="5" value="${rateInfoFilm.rateRateFilm}" step="1" id="rateStar1" name="rate">
                                        <input value="${filmInfo.name}" name="nameFilm" type="hidden">
                                        <input value="${SessionUser.userName}" name="nameUser" type="hidden">
                                        <button type="submit" value="submit">Add</button>
                                    </form>
                                </c:if>
                                <c:if test="${rateInfoFilm == null}">
                                        <form method="GET" action="/rate/add">
                                            <input type="range" min="0" max="5" value="0" step="1" id="rateStar2" name="rate">
                                            <input value="${filmInfo.name}" name="nameFilm" type="hidden">
                                            <input value="${SessionUser.userName}" name="nameUser" type="hidden">
                                            <button type="submit" value="submit">Add</button>
                                        </form>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </div>
            </article>
        </div>
    </div>
</div>
</body>
</html>
