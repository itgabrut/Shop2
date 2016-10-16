<%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 08.09.2016
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Registration</title>
    <link href="resources/css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="resources/css/style.css" rel="stylesheet" type="text/css">
    <link href="resources/css/login.css" rel="stylesheet" type="text/css">
    <link href="resources/css/component.css" rel="stylesheet" type="text/css">
    <link href="resources/css/megamenu.css" rel="stylesheet" type="text/css" media="all">
    <link href="//fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700" rel="stylesheet" type="text/css">
    <link href="//fonts.googleapis.com/css?family=Dorsa" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="webjars/jquery/2.2.3/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>

    <script type="text/javascript" src="resources/js/smoke.min.js"></script>
    <link rel="stylesheet" href="resources/css/smoke.css"/>
    <c:if test="${pageContext.response.locale.language == 'ru'}">
        <script type="text/javascript" src="resources/js/ru.min.js"></script>
    </c:if>
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
                        <li class="user_desc"><spring:message code="account"/></li>
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
                <div class="clearfix"> </div>
            </div>
            <div class="clearfix"> </div>
        </div>
        <div class="header_bottom">
            <c:choose>
                <c:when test="${pageContext.response.locale.language == 'ru'}">
                    <div class="logo">
                        <h1 style="font-size: 81px"><a href="getitems"><span class="m_1"><spring:message code="title1"/></span><spring:message code="title2"/></a></h1>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="logo">
                        <h1><a href="getitems"><span class="m_1"><spring:message code="title1"/></span><spring:message code="title2"/></a></h1>
                    </div>
                </c:otherwise>
            </c:choose>
            <div class="menu">
                <ul class="megamenu skyblue "><li class="showhide" style="display: none;"><span class="title">MENU</span><span class="icon1"></span><span class="icon2"></span></li>
                    <li style="display: inline;"><a class="color3" href="orders"><spring:message code="orderslist"/></a></li>
                    <li style="display: inline;"><a class="color7" href="#"><spring:message code="news"/></a></li>
                    <div class="clearfix"> </div>
                </ul>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3" style="margin-top: 10%">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="#" class="active" id="login-form-link"><spring:message code="login"/></a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link"><spring:message code="regist"/></a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" style="display: block;" action="loggin" method="post">
                                <div class="form-group ${error == null ? '' : 'has-error'}">
                                    <input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="Email" value="">
                                </div>
                                <div class="form-group ${error == null ? '' : 'has-error'}">
                                    <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
                                    <span class="help-block" style="margin-left: 28%">${error}</span>
                                </div>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <button type="submit" class="btn btn-primary"><spring:message code="login"/></button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <form:form id="register-form" onsubmit="return valll()" modelAttribute="registration" method="post" action="reg" style="display: none;">
                                <spring:bind path="name">
                                    <div class="">
                                        <label for="name" class="control-label col-xs-3"><spring:message code="name"/></label>
                                        <div class="form-group col-xs-8 bot">
                                        <form:input type="text" data-smk-pattern="([a-zA-z]+)||([а-яА-Я]+)" path="name" class="form-control" id="name" name="name" placeholder="Name" maxlength="15"/>
                                            <form:errors cssStyle="color: red" path="name"></form:errors>
                                        </div>
                                    </div>
                                </spring:bind>
                                <spring:bind path="surname">
                                <div class="">
                                    <label for="surname" class="control-label col-xs-3"><spring:message code="surname"/></label>

                                    <div class="form-group col-xs-8 bot">
                                        <form:input type="text" data-smk-pattern="([a-zA-z]+)||([а-яА-Я]+)" path="surname" class="form-control" id="surname" name="surname" placeholder="Surname" maxlength="15"/>
                                        <form:errors cssStyle="color: red" path="surname"></form:errors>
                                    </div>
                                </div>
                                </spring:bind>
                                <spring:bind path="email">
                                <div class="">
                                    <label for="email" class="control-label col-xs-3">Email</label>
                                    <div class="form-group col-xs-8 bot">
                                        <form:input type="email" path="email" class="form-control" id="email" name="email" placeholder="email"/>
                                        <form:errors cssStyle="color: red" path="email"></form:errors>
                                    </div>
                                </div>
                                </spring:bind>
                                <spring:bind path="birth">
                                <div class="">
                                    <label for="birthday" class="control-label col-xs-3"><spring:message code="birth"/></label>
                                    <div class="form-group col-xs-8 bot" id = "datetimepicker1">
                                        <form:input type="text"  data-smk-pattern="\\d{2}/\\d{2}/\\d{4}" path="birth" class="form-control" id="birthday" name="birth" placeholder="Birthday"/>
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                                    </div>
                                </div>
                                </spring:bind>
                                <spring:bind path="adress.country">
                                <div class="">
                                    <label for="country" class="control-label col-xs-3"><spring:message code="countr"/></label>

                                    <div class="form-group col-xs-8 bot">
                                        <form:input type="text" path="adress.country" data-smk-pattern="([a-zA-z]+)||([а-яА-Я]+)" class="form-control" id="country" name="country" placeholder="Country" maxlength="15"/>
                                        <form:errors cssStyle="color: red" path="adress.country"></form:errors>
                                    </div>
                                </div>
                                </spring:bind>
                                <spring:bind path="adress.city">
                                <div class="">
                                    <label for="city" class="control-label col-xs-3"><spring:message code="city"/></label>

                                    <div class="form-group col-xs-8 bot">
                                        <form:input type="text" path="adress.city" data-smk-pattern="([a-zA-z]+)||([а-яА-Я]+)" class="form-control" id="city" name="city" placeholder="City" maxlength="15"/>
                                        <form:errors cssStyle="color: red" path="adress.city"></form:errors>
                                    </div>
                                </div>
                                </spring:bind>
                                <spring:bind path="adress.street">
                                <div class="">
                                    <label for="street" class="control-label col-xs-3"><spring:message code="str"/></label>

                                    <div class="form-group col-xs-8 bot">
                                        <form:input type="text" path="adress.street" data-smk-pattern="([a-zA-z]+)||([а-яА-Я]+)" class="form-control" id="street" name="street" placeholder="Street" maxlength="15"/>
                                        <form:errors cssStyle="color: red" path="adress.street"></form:errors>
                                    </div>
                                </div>
                                </spring:bind>
                                <spring:bind path="adress.house">
                                <div class="">
                                    <label for="house" class="control-label col-xs-3"><spring:message code="hous"/></label>

                                    <div class="form-group col-xs-8 bot">
                                        <form:input type="number" path="adress.house" class="form-control" id="house" name="house" placeholder="House" maxlength="15"/>
                                        <form:errors cssStyle="color: red" path="adress.house"></form:errors>
                                    </div>
                                </div>
                                </spring:bind>
                                <spring:bind path="adress.app">
                                <div class="">
                                    <label for="app" class="control-label col-xs-3"><spring:message code="app"/></label>

                                    <div class="form-group col-xs-8 bot">
                                        <form:input type="number" path="adress.app" class="form-control" id="app" name="app" placeholder="Apartment" maxlength="15"/>
                                        <form:errors cssStyle="color: red" path="adress.app"></form:errors>
                                    </div>
                                </div>
                                </spring:bind>
                                <spring:bind path="adress.zip">
                                <div class="">
                                    <label for="zip" class="control-label col-xs-3"><spring:message code="zip"/></label>

                                    <div class="form-group col-xs-8 bot">
                                        <form:input type="number" path="adress.zip" class="form-control" maxlength="6" id="zip" name="zip" placeholder="Zip"/>
                                        <form:errors cssStyle="color: red" path="adress.zip"></form:errors>
                                    </div>
                                </div>
                                </spring:bind>
                                <spring:bind path="password">
                                <div class="">
                                    <label for="inputPassword" class="control-label col-xs-3"><spring:message code="pass"/></label>
                                        <div class="form-group col-xs-8">
                                            <form:input type="password" data-smk-strongPass="weak" path="password" class="form-control" id="inputPassword" name="password" placeholder=""/>
                                            <form:errors cssStyle="color: red" path="password"></form:errors>
                                            <%--<div class="help-block">Minimum of 6 characters</div>--%>
                                        </div>
                                    </div>
                                </spring:bind>
                                <spring:bind path="passwordconfirm">
                                <div class="">
                                    <label for="inputPasswordConfirm" class="control-label col-xs-3"><spring:message code="confirm"/></label>
                                        <div class="form-group col-xs-8">
                                            <form:input type="password" data-smk-strongPass="weak" path="passwordconfirm" class="form-control" id="inputPasswordConfirm"
                                                   name="passwordconfirm" placeholder=""/>
                                            <form:errors cssStyle="color: red" path="password"></form:errors>
                                        </div>
                                    </div>
                                </spring:bind>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <button type="submit" class="btn btn-primary"><spring:message code="save"/></button>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                            </div>
                        </div>
                    </div>
            </div>
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
        <%--<div class="cssmenu">--%>
            <%--<ul>--%>
                <%--<li class="active"><a href="#">Privacy</a></li>--%>
                <%--<li><a href="#">Terms</a></li>--%>
                <%--<li><a href="#">Shop</a></li>--%>
                <%--<li><a href="#">About</a></li>--%>
                <%--<li><a href="contact.html">Contact</a></li>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <%--<ul class="social">--%>
            <%--<li><a href=""> <i class="instagram"> </i> </a></li>--%>
            <%--<li><a href=""><i class="fb"> </i> </a></li>--%>
            <%--<li><a href=""><i class="tw"> </i> </a></li>--%>
        <%--</ul>--%>
        <div class="clearfix"></div>
        <div class="copy">
            <p> � 2015 Watches. All Rights Reserved | Design by <a href="http://w3layouts.com/" target="_blank">W3layouts</a></p>
        </div>
    </div>
</div>

<script>
    var isvalid = false;
    $(function() {

        $('form#register-form :input').each(function () {
            $(this).attr("required","true");
            if($(this).attr("name")=="adress.app")$(this).removeAttr("required");
            if($(this).attr("name")=="adress.zip")$(this).removeAttr("required");
        });

        if( $('#register-form').smkValidate() ){
            isvalid = true;
        }
        else {
            isvalid = false;
        }

        $('#login-form-link').click(function(e) {
            $("#login-form").delay(100).fadeIn(100);
            $("#register-form").fadeOut(100);
            $('#register-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });
        $('#register-form-link').click(function(e) {
            $("#register-form").delay(100).fadeIn(100);
            $("#login-form").fadeOut(100);
            $('#login-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });

    });
</script>

</body>
<script type="text/javascript" src="resources/js/moments/moment-with-locales.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" href="resources/js/css/bootstrap-datetimepicker.min.css" />
<script>

    $('#datetimepicker1').datetimepicker({pickTime: false});

//    function check() {
//        if($('#inputPassword').val()!==$('#inputPasswordConfirm').val()){
//            $('#passcnf').removeClass('hidden');
//            $('#passcnf').addClass('show alert alert-danger');
//            return false;
//        }
//        else{
//            $('#passcnf').addClass('hidden');
//            return true;
//        }
//    }

    function valll() {
        if( $('#register-form').smkValidate() ){
            if($.smkEqualPass('#inputPassword','#inputPasswordConfirm')){
                isvalid = true;
            }
        }
        else {
            isvalid = false;
        }
        return isvalid;
    }

//    function sendRegistration() {
//        if(check()==true) {
//            var form = $('#register-form');
//            var arr = form.serializeArray();
//            var toSend = {
//                name: arr[0].value,
//                surname: arr[1].value,
//                email: arr[2].value,
//                birth: arr[3].value,
//                adress: {
//                    country: arr[4].value,
//                    city: arr[5].value,
//                    street: arr[6].value,
//                    house: arr[7].value,
//                    app: arr[8].value,
//                    zip: arr[9].value
//                },
//                password: arr[10].value
//            };
//
//            $.ajax({
//                type: "POST",
//                url: 'ajax/users',
//                data: JSON.stringify(toSend),
//                success: function () {
//                    window.location.href = "getitems"
//                },
//                error: function (xhr, ajaxOptions, thrownError) {
//                    alert('EMAIL ALREADY EXISTS');
//                    $('.help-block.invisible').removeClass('invisible');
//                }
//            })
//            return false;
//        }
//        else return false;
//    }
//    function check() {
//        if($('#inputPassword').val()!==$('#inputPasswordConfirm').val()){
//            $('#passcnf').removeClass('hidden');
//            $('#passcnf').addClass('show alert alert-danger');
//            return false;
//        }
//        else{
//            $('#passcnf').addClass('hidden');
//            return true;
//        }
//    }
//    function sendLogIn() {
//        var form = $('#login-form');
//        $.ajax({
//            type : 'POST',
//            url: 'login',
//            data: form.serialize(),
//            success: function () {
//                window.location.href = "getitems";
//            },
//            error: function (xhr, ajaxOptions, thrownError) {
//                alert('ERROR: EMAIL OR PASSWORD INCORRECT');
//            }
//        })
//    }
</script>

</html>
