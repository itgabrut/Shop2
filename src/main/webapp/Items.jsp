<%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 31.08.2016
  Time: 21:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Shop</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="keywords" content="Watches Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design">
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Custom Theme files -->
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="css/component.css" rel="stylesheet" type="text/css">
    <!-- Custom Theme files -->
    <!--webfont-->
    <script type="text/javascript" src="webjars/jquery/2.2.3/jquery.min.js"></script>
    <link href="//fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700" rel="stylesheet" type="text/css">
    <link href="//fonts.googleapis.com/css?family=Dorsa" rel="stylesheet" type="text/css">
    <%--<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>--%>
    <!-- start menu -->
    <link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all">
    <%--<script type="text/javascript" src="js/megamenu.js"></script>--%>
    <%--<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>--%>
    <script src="js/jquery.easydropdown.js"></script>
    <%--<script src="js/simpleCart.min.js"> </script>--%>
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>

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
                        <img src="images/bag.png" alt="">
                        <div class="clearfix"> </div></h4>
                </a></div>
                <p class="empty"><a href="javascript:;" onclick="emptyCart()" class="simpleCart_empty">Empty Cart</a></p>
                <div class="clearfix"> </div>
            </div>
            <div class="header_top_right">

                <ul class="header_user_info">
                    <a class="login" href="login.html">
                        <i class="user"> </i>
                        <li class="user_desc">My Account</li>
                    </a>
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
                <script src="js/classie1.js"></script>
                <script src="js/uisearch.js"></script>
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
                <h1><a href="index.html"><span class="m_1">F</span>urniture</a></h1>
            </div>
            <div class="menu">
                <ul class="megamenu skyblue "><li class="showhide" style="display: none;"><span class="title">MENU</span><span class="icon1"></span><span class="icon2"></span></li>
                    <li style="display: inline;"><a class="color10" href="brands.html">Clients list</a></li>
                    <li style="display: inline;"><a class="color3" href="index.html">Orders list</a></li>
                    <li style="display: inline;"><a class="color7" href="404.html">News</a></li>
                    <div class="clearfix"> </div>
                </ul>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>
<div class="men">
    <div class="container">
        <div class="col-md-4 sidebar_men">
            <h3>Categories</h3>
            <ul class="product-categories color">
                <c:forEach items="${themeList}" var="theme">
                <li class="cat-item cat-item-42"><a href="getitems?theme=${theme}">${theme}</a> </li>
                </c:forEach>
            </ul>
            <input type="button" value="Add item" class="btn btn-info" placeholder="Add item" id="additem" onclick="addCat()"/>

        </div>
        <div class="col-md-8 mens_right">
            <div class="dreamcrub">
                <div class="clearfix"></div>
            </div>
            <div class="mens-toolbar">
                <%--<div class="sort">--%>
                    <%--<div class="sort-by">--%>
                        <%--<label>Sort By</label>--%>
                        <%--<select>--%>
                            <%--<option value="">--%>
                                <%--Position                </option>--%>
                            <%--<option value="">--%>
                                <%--Name                </option>--%>
                            <%--<option value="">--%>
                                <%--Price                </option>--%>
                        <%--</select>--%>
                        <%--<a href=""><img src="images/arrow2.gif" alt="" class="v-middle"></a>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<ul class="women_pagenation dc_paginationA dc_paginationA06">--%>
                    <%--<li><a href="#" class="previous">Page : </a></li>--%>
                    <%--<li class="active"><a href="#">1</a></li>--%>
                    <%--<li><a href="#">2</a></li>--%>
                <%--</ul>--%>
                <div id="page-selection"></div>
                <div class="clearfix"></div>
            </div>
            <div id="cbp-vm" class="cbp-vm-switcher cbp-vm-view-grid">
                <div class="cbp-vm-options">
                    <a href="#" class="cbp-vm-icon cbp-vm-grid cbp-vm-selected" data-view="cbp-vm-view-grid" title="grid">Grid View</a>
                    <a href="#" class="cbp-vm-icon cbp-vm-list" data-view="cbp-vm-view-list" title="list">List View</a>
                </div>
                <div class="pages">
                    <div class="limiter visible-desktop">
                        <label>Show</label>
                        <select>
                            <option value="" selected="selected">
                                9                </option>
                            <option value="">
                                15                </option>
                            <option value="">
                                30                </option>
                        </select> per page
                    </div>
                </div>
                <div class="clearfix"></div>
                <ul>
                    <c:forEach items="${itemList}" var="item">
                        <jsp:useBean id="item" scope="page" type="com.ilya.model.Item"/>
                        <a class="itemId" style="display:none;">${item.id}</a>

                        <li class="simpleCart_shelfItem">
                            <a class="cbp-vm-image" href="http://localhost:8080/universe/getitems?id=${item.id}">
                            </a><div class="view view-first"><a class="cbp-vm-image" href="http://localhost:8080/universe/getitems?id=${item.id}">
                        </a><div class="inner_content clearfix"><a class="cbp-vm-image" href="http://localhost:8080/universe/getitems?id=${item.id}">
                        </a><div class="product_image"><a class="cbp-vm-image" href="http://localhost:8080/universe/getitems?id=${item.id}">
                            <div class="mask1"><img src="http://localhost:8080/universe/fotoserver?fotoId=${item.id}" height="400px" width="220"  alt="image" class="img-responsive zoom-img"></div>
                            <div class="mask">
                                <div class="info">Quick View</div>
                            </div>
                        </a><div class="product_container"><a class="cbp-vm-image" href="single.html?id=${item.id}">
                            <h4>${item.name}</h4>
                            <p>${item.theme}</p>
                            <div class="price mount item_price">${item.price} $</div>
                        </a>
                            <div style="display: inline-block">
                        <a class="button item_add cbp-vm-icon cbp-vm-add" id="addItemTo" onclick="addItemToBucket(${item.id},${item.price})" href="#">Add to cart</a>
                                <form id="formdelete" method='post' action="getitems">
                                    <input type="text" style="display: none" value="${item.id}" name="id" >
                            <button class="button item_add cbp-vm-icon cbp-vm-add">Delete item</button>
                                </form>
                        </div>
                        </div>
                        </div>
                        </div>
                        </div>
                        </li>
                        </c:forEach>
                </ul>
            </div>
            <script>
                $(function () {
                    var a = 0;
                    $(".simpleCart_shelfItem").each(function (ind,el) {
                        a=a+1;
                       var n = a%3;
                        if(n === 0){
                            $(this).addClass("last");
                        }
                    })
                })
            </script>
            <script src="js/cbpViewModeSwitch.js" type="text/javascript"></script>
            <script src="js/classie.js" type="text/javascript"></script>
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

<div class="modal fade" id="weditRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" id="closeform" onclick="closeform()" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" action="getitems" method="post" id="detailsForm" enctype="multipart/form-data" data-toggle="validator" role="form">
                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3">Наименование</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="Как товар называется" maxlength="15" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="theme" class="control-label col-xs-3">Категория</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="theme" name="theme" placeholder="Категория" maxlength="15" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Описание</label>

                        <div class="col-xs-9">
                            <textarea class="form-control" id="description" name="description" placeholder="Какой товар" maxlength="100" required>
                                </textarea>>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="price" class="control-label col-xs-3">Цена</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="price" name="price" placeholder="Цена в рублях" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="quantity" class="control-label col-xs-3">Количество</label>

                        <div class="col-xs-9">
                            <input type="number" min="0" class="form-control" id="quantity" name="quantity" placeholder="Количество на складе" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-9">
                            <label class="btn btn-primary" for="my-file-selector">
                                <input id="my-file-selector" name="file" type="file" style="display:none;" onchange="$('#upload-file-info').html($(this).val());">
                                Загрузить фото
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


</body>
<script src="js/jquery.bootpag.min.js"></script>
<script>
    function addCat() {
        $('.modal-title').text("Add item");
        $('#id').val("0");
        $('#weditRow').addClass("in");
        $('#weditRow').css({ display: "block" });
//        $('#weditRow').modal();
    }
    function closeform() {
        $('#weditRow').removeClass("in");
        $('#weditRow').css({ display: "none" });
    }
    $(function(){
        var mass = $(".simpleCart_shelfItem").toArray();
        var amountPages = Math.ceil(mass.length/9);
        mass.forEach(function (item,i,arr) {
            if(i>8){arr[i].style.display = "none";}
        })
        $("#page-selection").bootpag({
            total : amountPages
        }).on("page",function (event,numm) {
            for(var i=1;i<mass.length+1;i++){
                if(Math.ceil(i/9)===numm){
                    mass[i-1].style.display = "inline-block";
                }
                else {
                    mass[i-1].style.display = "none";
                }
            }
        })

    })

    function addItemToBucket(iid,price) {
        var dataObj = {
            itemId : iid,
            totalPrice : price
        }
        $.ajax({
            type: 'Post',
            url: "checkout",
            data: dataObj
        });

        var res = parseInt($(".simpleCart_total").html())+price;
        $(".simpleCart_total").html(res + ' $ ');
        var quantity = parseInt($("#simpleCart_quantity").html());
        quantity++;
        $("#simpleCart_quantity").html(quantity);
    }

    function emptyCart() {
        $("#simpleCart_quantity").html(0);
        $('#simpleCart_total').html("0 $");
        $.ajax({
            type : 'Put',
            url : "checkout"
        });
    }
</script>
</html>
