<%@ page import="com.ilya.model.enums_utils.Role" %>
<%@ page import="com.ilya.model.Client" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 04.09.2016
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html><head>
    <title>Single Item</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="keywords" content="Watches Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link href="resources/css/bootstrap.css" rel="stylesheet" type="text/css">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Custom Theme files -->
    <link href="resources/css/style.css" rel="stylesheet" type="text/css">
    <!-- Custom Theme files -->
    <!--webfont-->
    <link href="//fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700" rel="stylesheet" type="text/css">
    <link href="//fonts.googleapis.com/css?family=Dorsa" rel="stylesheet" type="text/css">
    <!-- start menu -->
    <script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
    <link href="resources/css/megamenu.css" rel="stylesheet" type="text/css" media="all">
    <script src="resources/js/jquery.easydropdown.js"></script>
    <script src="resources/js/easyResponsiveTabs.js" type="text/javascript"></script>
    <script type="text/javascript" src="webjars/jquery/2.2.3/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#horizontalTab').easyResponsiveTabs({
                type: 'default', //Types: default, vertical, accordion
                width: 'auto', //auto or any width like 600px
                fit: true   // 100% fit in a container
            });
        });
    </script>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
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
                <!----search-scripts---->
                <script src="resources/js/classie1.js"></script>
                <script src="resources/js/uisearch.js"></script>
                <script>
                    new UISearch( document.getElementById( 'sb-search' ) );
                </script>
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
                <ul class="megamenu skyblue"><li class="showhide" style="display: none;"><span class="title">MENU</span><span class="icon1"></span><span class="icon2"></span></li>
                    </li>
                    <c:if test="${isAdmin}">
                    <li style="display: inline;"><a class="color10" href="help">Clients list</a></li>
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
<jsp:useBean id="item" scope="request" type="com.ilya.model.Item"/>
<div class="men">
    <div class="container">
        <div class="col-md-9 single_top">
            <div class="single_left">
                <div class="labout span_1_of_a1">
                    <div class="flexslider">
                        <ul class="slides">
                            <li data-thumb="fotoserver/db?fotoId=${item.id}">
                                <img src="fotoserver/db?fotoId=${item.id}" />
                            </li>
                            <c:forEach items="${fotosList}" var="uri">
                            <li data-thumb="fotoserver/sys?path=${uri}">
                                <img src="fotoserver/sys?path=${uri}" />
                            </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="cont1 span_2_of_a1 simpleCart_shelfItem">
                    <h1>${item.name}</h1>
                    <c:if test="${item.quantity >0}">
                    <p class="availability">Availability: <span class="color">In stock : ${item.quantity}</span></p>
                    </c:if>
                    <c:if test="${item.quantity == 0}">
                        <p class="availability">Availability: <span class="color">Unavailable</span></p>
                    </c:if>
                    <div class="price_single">
                        <span class="amount item_price actual"><fmt:formatNumber value = "${item.price}" pattern="#,##0 $"/> </span>
                    </div>
                    <h2 class="quick">Quick Overview:</h2>
                    <p class="quick_desc"> ${item.description}</p>

                    <div class="quantity_box">
                        <ul class="product-qty">
                            <span>Quantity:</span>
                            <select id="selectf">
                                <%--<option>1</option>--%>
                                <%--<option>2</option>--%>
                                <%--<option>3</option>--%>
                                <%--<option>4</option>--%>
                                <%--<option>5</option>--%>
                                <%--<option>6</option>--%>
                            </select>
                        </ul>

                        <div class="clearfix"></div>
                    </div>
                    <script>
                        var quan = ${item.quantity};
                        if(quan==0)$('.quantity_box').css({display : 'none'});
                        for(var i=1;i<=quan;i++){
                            $('#selectf').append('<option>'+i+'</option>');
                            if(i>6)break;
                        }
                    </script>
                    <c:if test="${item.quantity > 0}">
                    <a href="#" class="btn btn-primary btn-normal btn-inline btn_form button item_add item_1" onclick="addItemToBucket(${item.id},${item.price},$('#selectf').val())" target="_self">Add to cart</a>
                    </c:if>
                </div>
                <div class="clearfix"> </div>
            </div>
            <div class="sap_tabs">
                <div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">

                    <div class="resp-tabs-container">
                        <div class="tab-1 resp-tab-content resp-tab-content-active" aria-labelledby="tab_item-0" style="display:block">
                            <div class="facts">
                                <ul class="tab_list">
                                    <li><a href="#">Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequadsdsdtaugue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationclaritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothicae</a></li>


                                    <li><a href="#">Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.</a></li>
                                </ul>
                            </div>
                        </div>


                    </div>
                </div>
            </div>
        </div>
        <c:if test="${isAdmin}">
        <div class="col-md-3 form-group">

            <form class="form-horizontal" action="single/files" method="post" id="filesForm" enctype="multipart/form-data" data-toggle="validator" role="form">
                <input type="text" style="display: none" name="theme" value="${item.theme}">
                <input type="text" style="display: none" name="itemName" value="${item.name}">
                <input type="text" style="display: none" name="id" value="${item.id}">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group" style="margin-left: 20px">
                    <div class="col-xs-9">
                        <label class="btn btn-primary" for="my-file-selector1">
                            <input id="my-file-selector1" name="files" type="file" style="display:none;" onchange="$('#upload-file-info1').html($(this).val());">
                            Upload file
                        </label>
                        <span class='label label-info' id="upload-file-info1"></span>
                    </div>
                </div>
                <div class="form-group" style="margin-left: 20px">
                    <div class="col-xs-9">
                        <label class="btn btn-primary" for="my-file-selector2">
                            <input id="my-file-selector2" name="files" type="file" style="display:none;" onchange="$('#upload-file-info2').html($(this).val());">
                            Upload file
                        </label>
                        <span class='label label-info' id="upload-file-info2"></span>
                    </div>
                </div>
                <div class="form-group" style="margin-left: 20px">
                    <div class="col-xs-9">
                        <label class="btn btn-primary" for="my-file-selector3">
                            <input id="my-file-selector3" name="files" type="file" style="display:none;" onchange="$('#upload-file-info3').html($(this).val());">
                            Upload file
                        </label>
                        <span class='label label-info' id="upload-file-info3"></span>
                    </div>
                </div>
                <button type="submit" class="btn btn-info" style="margin-block-start: 2%; margin-left: 60px;">Save changes</button>
            </form>

            <div>
                <ul>
                    <c:forEach items="${fotosList}" var="uri">
                    <li style="margin-bottom: 20%">
                        <img src="fotoserver/sys?path=${uri}" style="height: 120px ; width: 120px">
                        <a class="btn btn-danger" href="fotoserver/del?delete=${uri}&itemId=${item.id}" style="margin-block-start: 5%; margin-left: 20px;"  >Delete foto</a>
                    </li>
                    </c:forEach>

                    <%--<li style="margin-bottom: 20%">--%>
                        <%--<img src="images/m1.jpg"  style="height: 120px ; width: 120px">--%>
                        <%--<button class="btn btn-danger" style="margin-block-start: 5%; margin-left: 20px;"  >Delete foto</button>--%>
                    <%--</li>--%>
                    <%--<li style="margin-bottom: 20%" >--%>
                        <%--<img src="images/m3.jpg" style="height: 120px ; width: 120px">--%>
                        <%--<button class="btn btn-danger" style="margin-block-start: 5%; margin-left: 20px;"  >Delete foto</button>--%>
                    <%--</li>--%>
                </ul>
            </div>

            <a class="btn btn-primary btn-normal" onclick="addCat()" style="margin-left: 15% ">Redact item</a>
        </div>
        </c:if>
        <div class="clearfix"> </div>
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
<!-- FlexSlider -->
<script defer="" src="resources/js/jquery.flexslider.js"></script>
<link rel="stylesheet" href="resources/css/flexslider.css" type="text/css" media="screen">
<script>
    // Can also be used with $(document).ready()
    $(window).load(function() {
        $('.flexslider').flexslider({
            animation: "slide",
            controlNav: "thumbnails"
        });
    });
</script>

<div class="modal fade" id="weditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" id="closeform" onclick="closeform()" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="single/update" method="post" id="detailsForm" enctype="multipart/form-data" data-toggle="validator" role="form">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="${item.name}" value="${item.name}" maxlength="35" required>
                        </div>
                    </div>
                    <input type="text" style="display: none" name="id" value="${item.id}">
                    <input type="text" style="display: none" name="oldTheme" value="${item.theme}">
                    <input type="text" style="display: none" name="oldName" value="${item.name}">
                    <div class="form-group">
                        <label for="theme" class="control-label col-xs-3">Category</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="theme" name="theme" placeholder="${item.theme}" value="${item.theme}" maxlength="100" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Description</label>

                        <div class="col-xs-9">
                            <textarea class="form-control" id="description" name="description" placeholder="${item.description}">
                                ${item.description}
                                </textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="price" class="control-label col-xs-3">Price</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="price" name="price" placeholder="${item.price}" value="${item.price}" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="quantity" class="control-label col-xs-3">Quantity</label>

                        <div class="col-xs-9">
                            <input type="number" min="0" class="form-control" id="quantity" name="quantity" placeholder="${item.quantity}" value="${item.quantity}" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-9">
                            <label class="btn btn-primary" for="my-file-selector">
                                <input id="my-file-selector" name="file" type="file" style="display:none;" onchange="$('#upload-file-info').html($(this).val());">
                                Change logo image
                            </label>
                            <span class='label label-info' id="upload-file-info"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    function addCat() {
        $('.modal-title').text("Redact item");
//        $('#id').val("0");
        $('#weditRow').addClass("in");
        $('#weditRow').css({ display: "block" });
//        $('#weditRow').modal();
    }
    function closeform() {
        $('#weditRow').removeClass("in");
        $('#weditRow').css({ display: "none" });
    }

    function addItemToBucket(iid,price,quan) {
        var adder = price*parseInt(quan);
        var dataObj = {
            itemId : iid,
            totalPrice : adder,
            quantityToAdd : parseInt(quan)
        };
        $.ajax({
            type: 'Post',
            url: "checkout",
            data: dataObj,
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            }
        });

        var res = parseInt($(".simpleCart_total").html())+adder;
        $(".simpleCart_total").html(res + ' $ ');
        var quantity = parseInt($("#simpleCart_quantity").html());
        quantity = quantity + parseInt(quan);
        $("#simpleCart_quantity").html(quantity);
    }

    function emptyCart() {
        $("#simpleCart_quantity").html(0);
        $('#simpleCart_total').html("0 $");
        $.ajax({
            type : 'Put',
            url : "checkout",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            }
        });
    }

    function logoutt() {
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
</script>

</body></html>
