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
</style>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Movies</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body id="top">
<div>
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
    <div class="wrapper bgded overlay">
        <section class="hoc container clear">
            <div class="sectiontitle">
                <h6 class="heading">Movie list</h6>
            </div>
                <c:forEach items="${allFilm}" var="item">
                    <ul class="nospace group elements">
                        <li class="one_third first">
                            <article><i class="fa fa-thumbs-up"></i>
                                <img src="<c:url value='${item.imageId}'/>"/>
                                <c:if test="${SessionUser.userId != null}">
                                    <a class="heading" href="/film/info?id=${item.id}" ><c:out value="${item.name}" /></a>
                                </c:if>
                                <c:if test="${SessionUser.userId == null}">
                                    <h6 class="heading"><c:out value="${item.name}" />
                                </c:if>
                                <br />Rate: <c:out value="${item.averageRate}" />
                                <c:if test="${SessionUser.userId != null}">
                                    <c:if test="${SessionUser.userRole == 'Admin'}">
                                        <li>[ <a href="/film/edit?id=${item.id}">Edit</a> | <a href="/film/delete?id=${item.id}">Delete</a> ]</li>
                                    </c:if>
                                </c:if>
                                </h6>
                                <c:if test="${SessionUser.userId != null}">
                                    <footer><a href="/film/info?id=${item.id}" >View Details &raquo;</a></footer>
                                </c:if>
                                <br/><br/>
                            </article>
                        </li>
                    </ul>
                </c:forEach>
        </section>
    </div>
</div>
</div>
</body>
</html>
