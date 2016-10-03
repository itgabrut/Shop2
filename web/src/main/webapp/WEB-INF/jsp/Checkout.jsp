<%@ page import="java.util.Map" %>
<%@ page import="com.ilya.model.enums_utils.Role" %>
<%@ page import="com.ilya.model.Client" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 02.09.2016
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title> Checkout</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="keywords" content="Watches Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Custom Theme files -->
    <link href="resources/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="resources/css/style.css" rel="stylesheet" type="text/css">
    <link href="resources/css/component.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="webjars/jquery/2.2.3/jquery.min.js"></script>
    <!-- Custom Theme files -->
    <!--webfont-->
    <link href="//fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700" rel="stylesheet" type="text/css">
    <link href="//fonts.googleapis.com/css?family=Dorsa" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>
    <!-- start menu -->
    <link href="resources/css/megamenu.css" rel="stylesheet" type="text/css" media="all">
    <script type="text/javascript" src="resources/js/megamenu.js"></script>
    <%--<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>--%>
    <script src="resources/js/jquery.easydropdown.js"></script>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<div class="men_banner">
    <div class="container">
        <div class="header_top">
            <!--<div class="header_top_left">-->
            <!--<div class="box_11"><a href="checkout.html">-->
            <!--<h4><p>Cart: <span class="simpleCart_total">$0.00</span> (<span id="simpleCart_quantity" class="simpleCart_quantity">0</span> items)</p><img src="../images/bag.png" alt=""><div class="clearfix"> </div></h4>-->
            <!--</a></div>-->
            <!--<p class="empty"><a href="javascript:;" class="simpleCart_empty">Empty Cart</a></p>-->
            <!--<div class="clearfix"> </div>-->
            <!--</div>-->
            <div class="header_top_right">

                <ul class="header_user_info">
                    <c:if test="${loggedClient==null}">
                    <a class="login" href="Register.jsp">
                        </c:if>
                        <c:if test="${loggedClient!=null}">
                        <a class="login" href="orders">
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
                <%--<script src="js/classie1.js"></script>--%>
                <%--<script src="js/uisearch.js"></script>--%>
                <%--<script>--%>
                    <%--new UISearch( document.getElementById( 'sb-search' ) );--%>
                <%--</script>--%>
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

                    <c:if test='${isAdmin}'>
                    <li style="display: inline;"><a class="color10" href="help">Clients list</a></li>
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
<div class="account-in">
    <div class="container">
        <div class="check_box">
            <div class="col-md-9 cart-items">
                <h1>My Shopping Bag </h1>
                <%--<script>$(document).ready(function(c) {--%>
                    <%--$('.close1').on('click', function(c){--%>
                        <%--$('.cart-header').fadeOut('slow', function(c){--%>
                            <%--$('.cart-header').remove();--%>
                        <%--});--%>
                    <%--});--%>
                <%--});--%>
                <%--</script>--%>


                <c:forEach var="item" items="${itemsToCheckout}">
                    <jsp:useBean id="item" scope="page" class="com.ilya.model.Item"></jsp:useBean>
                <div class="cart-header" >
                    <div class="close1"  id="${item.id}" onclick="closeMyclose(${item.id})" > </div>
                    <div class="cart-sec simpleCart_shelfItem">
                        <div class="cart-item cyc">
                            <img src="fotoserver/db?fotoId=${item.id}" class="img-responsive" alt="">
                        </div>
                        <div class="cart-item-info">
                            <h3><a href="single?id=${item.id}">${item.name}</a></h3>
                            <h3><a id="price${item.id}" name="${item.price}" href="single?id=${item.id}"><fmt:formatNumber pattern="#,##0 $" value="${item.price}"/></a></h3>
                            <ul class="qty">
                                <%
                                    Map<String,Integer> map = (Map<String, Integer>) session.getAttribute("itemsMap");
                                    String key = String.valueOf(item.getId());
                                    int val = map.get(key);
                                %>
                                <h2><li>Qty : <p id="quantity${item.id}"><%=val%></p></li></h2>

                            </ul>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
                </c:forEach>
                <%--<script>$(document).ready(function(c) {--%>
                    <%--$('.close2').on('click', function(c){--%>
                        <%--$('.cart-header2').fadeOut('slow', function(c){--%>
                            <%--$('.cart-header2').remove();--%>
                        <%--});--%>
                    <%--});--%>
                <%--});--%>
                <%--</script>--%>
                <%--<div class="cart-header2">--%>
                    <%--<div class="close2"> </div>--%>
                    <%--<div class="cart-sec simpleCart_shelfItem">--%>
                        <%--<div class="cart-item cyc">--%>
                            <%--<img src="../images/m4.jpg" class="img-responsive" alt="">--%>
                        <%--</div>--%>
                        <%--<div class="cart-item-info">--%>
                            <%--<h3><a href="#">Mountain Hopper(XS R034)</a><span>Model No: 3578</span></h3>--%>
                            <%--<ul class="qty">--%>
                                <%--<li><p>Size : 5</p></li>--%>
                                <%--<li><p>Qty : 1</p></li>--%>
                            <%--</ul>--%>
                            <%--<div class="delivery">--%>
                                <%--<p>Service Charges : Rs.100.00</p>--%>
                                <%--<span>Delivered in 2-3 business days</span>--%>
                                <%--<div class="clearfix"></div>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<div class="clearfix"></div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
            <script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/1.4.5/numeral.min.js"></script>

            <fmt:setLocale value="en_US"/>
            <div class="col-md-3 cart-total">
                <a class="continue" href="getitems">Continue to store</a>
                <div class="price-details">
                    <h3>Price Details</h3>
                    <span>Total</span>
                    <span class="total">

                    </span>
                    <span>Discount</span>
                    <span class="total1">---</span>
                    <span>Delivery Charges</span>
                    <span class="total1"><fmt:formatNumber pattern="#,##0 $" value="150"/></span>
                    <div class="clearfix"></div>
                </div>
                <ul class="total_price">
                    <li class="last_price"> <h4>TOTAL</h4></li>
                    <li class="last_price"><span id="lastPrice"></span></li>
                    <div class="clearfix"> </div>
                </ul>
                <div class="clearfix"></div>
                <c:if test="${loggedClient==null}">
                <a class="order" href="login">Place Order</a>
                </c:if>
                <c:if test="${loggedClient!=null}">
                    <a class="order" onclick="sendToPermitOrder()" href="#">Place Order</a>
                </c:if>

            </div>
            <div class="clearfix"></div>
        </div>
    </div>
</div>

<div class="footer">
    <div class="container">
        <div class="newsletter">
            <h3>Newsletter</h3>
            <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard</p>
            <form>
                <input type="text" value="" onfocus="this.value='';" onblur="if (this.value == '') {this.value ='';}">
                <input type="submit" value="SUBSCRIBE">
            </form>
        </div>
        <div class="cssmenu">
            <ul>
                <li class="active"><a href="#">Privacy</a></li>
                <li><a href="#">Terms</a></li>
                <li><a href="#">Shop</a></li>
                <li><a href="#">About</a></li>
                <li><a href="contact.html">Contact</a></li>
            </ul>
        </div>
        <ul class="social">
            <li><a href=""> <i class="instagram"> </i> </a></li>
            <li><a href=""><i class="fb"> </i> </a></li>
            <li><a href=""><i class="tw"> </i> </a></li>
        </ul>
        <div class="clearfix"></div>
        <div class="copy">
            <p> � 2015 Watches. All Rights Reserved | Design by <a href="http://w3layouts.com/" target="_blank">W3layouts</a></p>
        </div>
    </div>
</div>
<script>
      var vv = numeral(${totalPrice}).format('0,0 $');
      var vv1 = numeral(${totalPrice}+150).format('0,0 $');
      $('span.total').html(vv);
    $('#lastPrice').html(vv1);
</script>
<script>
    function logoutt() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: 'loggout',
            type: 'POST',
            data: token,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                window.location = "getitems";
            },
            error: function (data) {
                console.log(data);
            }
        });
    }

    function closeMyclose(el) {
        var summmTotal = numeral().unformat($('span.total').html());
        var itemPrice = $('#price'+el).attr("name");
        summmTotal -= itemPrice;
        $('span.total').html(numeral(summmTotal).format('0,0 $'));
        summmTotal +=150;
        $('#lastPrice').html(numeral(summmTotal).format('0,0 $'));
        var count = $('#quantity'+el).html();
        if(count==1) {
            $('#' + el).parent().fadeOut('slow', function (c) {
                $('#' + el).parent().remove();
            });
        }
        else {
            count--;
            $('#quantity'+el).html(count);
        }
        var data = {
            itemId : el,
            itemPrice : itemPrice
        };
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url : 'checkout',
            method : 'Post',
            data : data,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            }
        });
    }
    
    function sendToPermitOrder() {

        var mass = [];
        if ($('.close1').length<1) {
            alert("Your Card is empty");
            return false;
        }
        $('.close1').each(function () {
            var id = $(this).attr('id');
            var quan = $('#quantity'+id).html();
            var obj = {
                itemId : id,
                quantity : quan
            }
            mass.push(obj);
        })
        var jssobj = {
            arr : mass
        }
        var toSend = JSON.stringify(jssobj);
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajax({
            url: 'orderstopost',
            method: "POST",
            data: toSend ,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function () {
                window.location.href = 'orders'
            }
        });
    }
</script>
</body>


</html>
