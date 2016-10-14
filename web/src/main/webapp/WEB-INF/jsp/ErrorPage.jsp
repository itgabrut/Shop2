<%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 14.10.2016
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.ilya.model.enums_utils.Role" %>
<%@ page import="com.ilya.model.Client" %>
<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Shop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="keywords" content="Watches Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link href="resources/css/bootstrap.css" rel="stylesheet" type="text/css">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Custom Theme files -->
    <link href="resources/css/style.css" rel="stylesheet" type="text/css">
    <link href="resources/css/component.css" rel="stylesheet" type="text/css">
    <!-- Custom Theme files -->
    <!--webfont-->
    <script type="text/javascript" src="webjars/jquery/2.2.3/jquery.min.js"></script>
    <link href="//fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700" rel="stylesheet" type="text/css">
    <link href="//fonts.googleapis.com/css?family=Dorsa" rel="stylesheet" type="text/css">
    <%--<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>--%>
    <!-- start menu -->
    <link href="resources/css/megamenu.css" rel="stylesheet" type="text/css" media="all">
    <%--<script type="text/javascript" src="js/megamenu.js"></script>--%>
    <%--<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>--%>
    <script src="resources/js/jquery.easydropdown.js"></script>
    <%--<script src="js/simpleCart.min.js"> </script>--%>
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <style>
        ul.product-categories li {
            padding: 9px 9px;
            border-bottom: 1px solid #eee;
            margin-bottom: 5px;
            text-transform: uppercase;
            margin-left: 0px;
        }
        .list-group-item:first-child {
            border-top-left-radius: 2px;
            border-top-right-radius: 41px;
        }
        ul.product-categories li {
            padding: 9px 9px;
            border-bottom: 1px solid #eee;
            margin-bottom: 5px;
            text-transform: none;
            margin-left: 0px;
        }
    </style>
</head>
<body>
<div class="men_banner">
    <div class="container">
        <div class="header_top">
            <div class="header_top_left">
                <div class="box_11"><a href="checkout">
                    <h4><p>Cart: <span class="simpleCart_total" id="simpleCart_total">
                        <c:if test="${totalPrice!=null}">${totalPrice} $ </c:if>
                        <c:if test="${totalPrice==null}">0 $ </c:if>
                    </span>
                        <span id="simpleCart_quantity" class="simpleCart_quantity">
                            <c:if test="${quantity!=null}">${quantity}</c:if>
                         <c:if test="${quantity==null}">0</c:if>
                        </span> items</p>
                        <img src="resources/images/bag.png" alt="">
                        <div class="clearfix"> </div></h4>
                </a></div>
                <p class="empty"><a href="javascript:;" onclick="emptyCart()" class="simpleCart_empty">Empty Cart</a></p>
                <div class="clearfix"> </div>
            </div>
            <div class="header_top_right">

                <ul class="header_user_info">
                    <c:if test="${loggedClient==null}">
                    <a class="login" href="login">
                        </c:if>
                        <c:if test="${loggedClient!=null}">
                        <a class="login" href="toDetails">
                            </c:if>
                            <i class="user"> </i>
                            <li class="user_desc">My Account</li>
                        </a>
                        <c:if test="${loggedClient!=null}">
                        <p class="empty"><a style="margin-left: -100%;" href="javascript:;" onclick="logoutt()" class="simpleCart_empty">LOG OUT</a></p>
                        </c:if>
                        <div class="clearfix"> </div>
                </ul>
                <!-- start search-->
                <div class="search-box">
                    <div id="sb-search" class="sb-search">
                        <form>
                            <input class="sb-search-input" placeholder="Enter your search term..." type="search" name="search" id="search">
                            <input class="sb-search-submit" type="submit" value="">
                            <span class="sb-icon-search"> </span>
                        </form>
                    </div>
                </div>
                <!----search-scripts---->
                <script src="resources/js/classie1.js"></script>
                <script src="resources/js/uisearch.js"></script>
                <script>
                    new UISearch( document.getElementById( 'sb-search' ) );
                </script>

                <form action="single" method="post" style="display: none">
                    <input type="text" name="id">
                    <input type="text" name="quantity">
                </form>
                <!----//search-scripts---->
                <div class="clearfix"> </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="header_bottom">
            <div class="logo">
                <h1><a href="getitems"><span class="m_1">F</span>urniture</a></h1>
            </div>
            <c:if test="${loggedClient != null}">
                <c:set var="isAdmin" value='<%=((Client)session.getAttribute("loggedClient")).getRoles().contains(Role.ROLE_ADMIN)%>'></c:set>
            </c:if>
            <div class="menu">
                <ul class="megamenu skyblue "><li class="showhide" style="display: none;"><span class="title">MENU</span><span class="icon1"></span><span class="icon2"></span></li>
                    <c:if test="${isAdmin}">
                        <li style="display: inline;"><a class="color10" href="toClients">Clients list</a></li>
                    </c:if>
                    <li style="display: inline;"><a class="color3" href="orders">Orders list</a></li>
                    <li style="display: inline;"><a class="color7" href="#">News</a></li>
                    <div class="clearfix"> </div>
                </ul>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>


<br/>

<h1 style="align:center">Error Page</h1>
<p style="align:center" >Application has encountered an error. Please contact support on ...</p>

<!--
    Failed URL: ${url}
    Exception:  ${exception.message}
        <c:forEach items="${exception.stackTrace}" var="ste">    ${ste}
         </c:forEach>
  -->

</body>
</html>
