<%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 01.10.2016
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Details</title>
    <link href="//fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700" rel="stylesheet" type="text/css">
    <link href="//fonts.googleapis.com/css?family=Dorsa" rel="stylesheet" type="text/css">
    <link href="resources/css/bootstrap.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="webjars/jquery/2.2.3/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>
    <link href="resources/css/megamenu.css" rel="stylesheet" type="text/css">
    <link href="resources/css/style.css" rel="stylesheet" type="text/css">
    <link href="resources/css/component.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
    <link href="resources/css/login.css" rel="stylesheet" type="text/css">
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


<div class='container' style="margin-block-start: 50px;">
    <div class='panel panel-primary dialog-panel'>
        <div class='panel-heading'>
            <h5>Redact your profile</h5>
            <button type="button" class="bottom-right button alert-info" onclick="changepass()">Change Password</button>
        </div>
        <div class='panel-body'>
            <form:form id="register-form" method="post" onsubmit="return validdd()" action="changeDetails" commandName="loggedClient">
                <spring:bind path="name">
                    <div class="">
                        <label for="name" class="control-label col-xs-3">Name</label>
                        <div class="col-xs-8 bot form-group">
                            <form:input type="text" data-smk-pattern="([a-zA-z]+)||([а-яА-Я]+)" path="name" class="form-control" id="name" name="name" placeholder="Name" maxlength="15"></form:input>
                            <form:errors cssStyle="color: red" path="name"></form:errors>
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="surname">
                    <div class="">
                        <label for="surname" class="control-label col-xs-3">Surname</label>

                        <div class="col-xs-8 bot form-group">
                            <form:input type="text" path="surname" class="form-control" id="surname" name="surname" placeholder="Surname" maxlength="15"/>
                            <form:errors cssStyle="color: red" path="surname"></form:errors>
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="email">
                    <div class="form-group" hidden>
                        <label for="email" class="control-label col-xs-3">Email</label>

                        <div class="col-xs-8 bot">
                            <form:input type="email" path="email" class="form-control" id="email" name="email" placeholder="email"/>
                            <form:errors cssStyle="color: red" path="email"></form:errors>
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="birth">
                    <div class="">
                        <label for="birthday" class="control-label col-xs-3">Birthday</label>

                        <div class="col-xs-8 bot form-group" id = "datetimepicker1">
                            <form:input type="text"  data-smk-pattern="\\d{2}/\\d{2}/\\d{4}" path="birth" class="form-control" id="birthday" name="birth" placeholder="Birthday"/>
                            <form:errors cssStyle="color: red" path="birth"></form:errors>
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="adress.country">
                    <div class="">
                        <label for="country" class="control-label col-xs-3">Country</label>

                        <div class="col-xs-8 bot form-group">
                            <form:input type="text" data-smk-pattern="([a-zA-z]+)||([а-яА-Я]+)" path="adress.country" class="form-control" id="country" name="country" placeholder="Country" maxlength="15"/>
                            <form:errors cssStyle="color: red" path="adress.country"></form:errors>
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="adress.city">
                    <div class="">
                        <label for="city" class="control-label col-xs-3">City</label>

                        <div class="col-xs-8 bot form-group">
                            <form:input type="text" data-smk-pattern="([a-zA-z]+)||([а-яА-Я]+)"  path="adress.city" class="form-control" id="city" name="city" placeholder="City" maxlength="15"/>
                            <form:errors cssStyle="color: red" path="adress.city"></form:errors>
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="adress.street">
                    <div class="">
                        <label for="street" class="control-label col-xs-3">Street</label>

                        <div class="col-xs-8 bot form-group">
                            <form:input type="text" data-smk-pattern="([a-zA-z]+)||([а-яА-Я]+)" path="adress.street" class="form-control" id="street" name="street" placeholder="Street" maxlength="15"/>
                            <form:errors cssStyle="color: red" path="adress.street"></form:errors>
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="adress.house">
                    <div class="">
                        <label for="house" class="control-label col-xs-3">House</label>

                        <div class="col-xs-8 bot form-group">
                            <form:input type="number" path="adress.house" class="form-control" id="house" name="house" placeholder="House" maxlength="15"/>
                            <form:errors cssStyle="color: red" path="adress.house"></form:errors>
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="adress.app">
                    <div class="">
                        <label for="app" class="control-label col-xs-3">Apartment</label>

                        <div class="col-xs-8 bot form-group">
                            <form:input type="number" path="adress.app" class="form-control" id="app" name="app" placeholder="Apartment" maxlength="15"/>
                            <form:errors cssStyle="color: red" path="adress.app"></form:errors>
                        </div>
                    </div>
                </spring:bind>
                <spring:bind path="adress.zip">
                    <div class="">
                        <label for="zip" class="control-label col-xs-3">Zip</label>

                        <div class="col-xs-8 bot form-group">
                            <form:input type="number" path="adress.zip" class="form-control" maxlength="6" id="zip" name="zip" placeholder="Zip"/>
                            <form:errors cssStyle="color: red" path="adress.zip"></form:errors>
                        </div>
                    </div>
                </spring:bind>

                <div class="form-group">
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <button type="submit" class="btn btn-primary" style="margin-top: 38px;margin-inline-start: 187px;">Save</button>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title">Changing password</h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="passchange" onsubmit="return false">
                <div class="form-group">
                    <label for="oldPassword" class="control-label col-xs-3">Current password</label>
                    <div class="col-xs-8">
                        <input type="password" class="form-control" data-smk-strongPass="weak" id="oldPassword" name="password" placeholder="" required/>
                        <%--<form:errors cssStyle="color: red" path="password"></form:errors>--%>
                        <div class="help-block mine hidden" style="color: red" >Typed wrong password</div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="pass1" class="control-label col-xs-3">Enter new password</label>
                    <div class="col-xs-8">
                        <input type="password" class="form-control" data-smk-strongPass="weak" id="pass1"
                               name="pass1" placeholder="" required/>
                        <%--<form:errors cssStyle="color: red" path="password"></form:errors>--%>
                    </div>
                </div>
                    <div class="form-group">
                        <label for="pass2" class="control-label col-xs-3">Confirm a password</label>
                        <div class="col-xs-8">
                            <input type="password" class="form-control" data-smk-strongPass="weak" id="pass2"
                                   name="pass2" placeholder="" required/>
                            <%--<form:errors cssStyle="color: red" path="password"></form:errors>--%>
                        </div>
                    </div>
                    <input type="submit" class="button bg-primary" id="btnEqualPass" value="Change password">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="resources/js/moments/moment-with-locales.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" href="resources/js/css/bootstrap-datetimepicker.min.css" />
<script type="text/javascript" src="resources/js/smoke.min.js"></script>
<link rel="stylesheet" href="resources/css/smoke.css" />

    <script>
        
        function validdd() {
            if( $('#register-form').smkValidate() ){
                // Code here
                return true;
            }
            else return false;
        }

        function changepass() {
            $('#editRow').modal();
        }

        function sendChange() {
            var oldpass = [{pass : $('#oldPassword').val()},{passnew1 : $('#pass1').val()},{passnew2 : $('#pass2').val()}];
            var data2 = $('#passchange').serializeArray();
             $.ajax({
                 type : 'Post',
                 url : 'pass',
                 data : data2,
                 beforeSend: function (xhr) {
                     xhr.setRequestHeader(header, token);
                 },
                 success : function (data) {
                     $('#passchange').smkClear();
                     $('#editRow').modal('hide');
                 },
                 error : function (xhr, ajaxOptions, thrownError) {
                     $.smkAlert({
                         text: 'Incorrect password',
                         type: 'danger',
                         position:'top-center'
                     });
                     $('#passchange').smkClear();
                     $('.help-block.mine').removeClass('hidden');
                 }
             });
        }

        var ajaxUrl = 'rest/admin';
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

    $('#datetimepicker1').datetimepicker({pickTime: false});

    $('#btnEqualPass').click(function() {
        if ($('#passchange').smkValidate()) {
            if( $.smkEqualPass('#pass1', '#pass2') ){
//                $.smkAlert({
//                    text: $('#pass1').val(),
//                    type: 'success'
//                });
                sendChange();
            }

        }
    });
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
    </script>
</html>
