<%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 30.09.2016
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.ilya.model.enums_utils.Role" %>
<%@ page import="com.ilya.model.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="resources/css/style.css" rel="stylesheet" type="text/css">
    <link href="resources/css/component.css" rel="stylesheet" type="text/css">
    <link href="resources/css/megamenu.css" rel="stylesheet" type="text/css" media="all">
    <link href="//fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700" rel="stylesheet" type="text/css">
    <link href="//fonts.googleapis.com/css?family=Dorsa" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.11/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="webjars/jquery/2.2.3/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>
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
                    <a class="login" href="#">
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
            <c:if test="${loggedClient != null}">
                <c:set var="isAdmin" value='<%=((Client)session.getAttribute("loggedClient")).getRoles().contains(Role.ROLE_ADMIN)%>'></c:set>
            </c:if>
            <div class="menu">
                <ul class="megamenu skyblue "><li class="showhide" style="display: none;"><span class="title">MENU</span><span class="icon1"></span><span class="icon2"></span></li>
                    <c:if test="${isAdmin}">
                        <li style="display: inline;"><a class="color10" href="toClients">Clients list</a></li>
                    </c:if>
                    <li style="display: inline;"><a class="color3" href="orders?clientId=${loggedClient.id}">Orders list</a></li>
                    <li style="display: inline;"><a class="color7" href="#">News</a></li>
                    <div class="clearfix"> </div>
                </ul>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="jumbotron">
        <div class="shadow">
            <h3>Orders of  ${loggedClient.name}</h3>
            <br>

            <div class="view-box">
                <table class="table table-striped display" id="data1">
                    <thead>
                    <tr>
                        <th>Order number</th>
                        <th>Payment Status</th>
                        <th>Delivery Status</th>
                        <th>Date</th>
                        <th>Delivery</th>
                        <th>Act</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>



</body>
<script type="text/javascript" src="webjars/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="resources/js/moments/moment-with-locales.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" href="resources/js/css/bootstrap-datetimepicker.min.css" />
<script type="text/javascript">

    var datatableApi;

    $(function () {
        datatableApi = $('#data1').DataTable({
            "paging": true,
            "info": true,
            "columns": [
                {
                    "data": "id"
                },
                {
                    "data": "deliveryStatus"
                },
                {
                    "data": "payStatus"
                },
                {
                    "data": "date"
                },
                {
                    "data": "delivery"
                },
                {
                    "defaultContent": "Show items",
                    "render" : function (data,type,row) {
                        return "<a href='adminSingleorder?clientId=${clientId}&orderId="+row.id+"' id='myOrder'>Order details </a>"
                    }
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        });
        updateTable();
//        painterrr({id:67,delivery_status:'proxyId',date:'dd',pay_status:'proxyId',delivery:'dd'})
    });

    function painterrr(data) {
        datatableApi.clear();
        datatableApi.rows.add(data).draw();
    }

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

    function updateTable() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        var addr = 'ajax/orders/adminGet?clientId='+${clientId};
        $.ajax({
            url: addr,
            type: 'Get',
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (data) {
                painterrr(data);
            }
        });
//        $.get(addr, function (data) {
//            painterrr(data);
//        });

        $(document).ajaxError(function (event, jqXHR, options, jsExc) {
            // failNoty(event, jqXHR, options, jsExc);
        });
    }
</script>
</html>
