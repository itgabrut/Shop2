<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Order details</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="css/component.css" rel="stylesheet" type="text/css">
    <link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all">
    <link href="//fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700" rel="stylesheet" type="text/css">
    <link href="//fonts.googleapis.com/css?family=Dorsa" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="webjars/jquery/2.2.3/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>
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
                        <a class="login" href="Register.jsp"/>
                    </c:if>
                    <c:if test="${loggedClient!=null}">
                    <a class="login" href="orders?clientId=${loggedClient.id}">
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
                <div class="clearfix"> </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="header_bottom">
            <div class="logo">
                <h1><a href="getitems"><span class="m_1">F</span>urniture</a></h1>
            </div>
            <div class="menu">
                <ul class="megamenu skyblue "><li class="showhide" style="display: none;"><span class="title">MENU</span><span class="icon1"></span><span class="icon2"></span></li>
                    <li style="display: inline;"><a class="color3" href="orders?clientId=${loggedClient.id}">Orders list</a></li>
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
            <div class="col-md-6 cart-items">
                <h1>Order details </h1>
                <c:forEach var="map" items="${itemsForOrderMap}">
                    <jsp:useBean id="item" scope="page" class="com.ilya.model.Item"></jsp:useBean>
                    <div class="cart-header" >
                        <div class="cart-sec simpleCart_shelfItem">
                            <div class="cart-item cyc">
                                <img src="fotoserver?fotoId=${map.key.id}" class="img-responsive" alt="">
                            </div>
                            <div class="cart-item-info">
                                <h3><a href="single?id=${map.key.id}">${map.key.name}</a></h3>
                                <h3><a id="price" name="${map.key.price}" quan="${map.value}" href="single?id=${map.key.id}"></a></h3>
                                <ul class="qty">
                                    <h2><li>Qty : <p id="quantity${map.key.id}">${map.value}</p></li></h2>

                                </ul>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </c:forEach>
                </div>
            <div class="col-md-3 cart-total">
                <a class="continue" href="getitems">Continue to store</a>
                <ul class="total_price">
                    <li class="last_price"> <h4>TOTAL</h4></li>
                    <li class="last_price"><span id="lastPrice">300 $</span></li>
                    <div class="clearfix"> </div>
                </ul>
                <div class="clearfix"></div>

            </div>
            <div class="clearfix"></div>
            </div>
            <script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/1.4.5/numeral.min.js"></script>
            </div>
        </div>
    </div>



</body>
<script>
    var tot = 0;
     $('a#price').each(function () {
         var dd = $(this).attr('name')*$(this).attr('quan');
        var num = numeral(dd).format('0,0 $');
         $(this).html(num);
         tot += dd;
    })

    $('span#lastPrice').html(numeral(tot).format('0,0 $'));

    function logoutt() {
        $.ajax({
            type : 'PUT',
            url : 'login',
            success: function () {
                window.location.href = "getitems";
            }
        })
    }
</script>
</html>