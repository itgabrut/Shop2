<%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 08.09.2016
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <link href="css/login.css" rel="stylesheet" type="text/css">
    <link href="css/component.css" rel="stylesheet" type="text/css">
    <link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all">
    <link href="//fonts.googleapis.com/css?family=PT+Sans+Narrow:400,700" rel="stylesheet" type="text/css">
    <link href="//fonts.googleapis.com/css?family=Dorsa" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="webjars/jquery/2.2.3/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>
    <%--<script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>--%>
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
                    <li style="display: inline;"><a class="color7" href="404.html">News</a></li>
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
                            <a href="#" class="active" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link">Register</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" onsubmit="sendLogIn();return false;" style="display: block;">
                                <div class="form-group">
                                    <input type="text" name="email" id="username" tabindex="1" class="form-control" placeholder="Email" value="">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
                                </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <button type="submit" class="btn btn-primary" onsubmit="sendLogIn()">Log In</button>
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <form id="register-form" onsubmit="sendRegistration();return false;" style="display: none;">
                                <div class="form-group">
                                    <label for="name" class="control-label col-xs-3">Name</label>

                                    <div class="col-xs-8 bot">
                                        <input type="text" class="form-control" id="name" name="name" placeholder="Name" maxlength="15" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="surname" class="control-label col-xs-3">Surname</label>

                                    <div class="col-xs-8 bot">
                                        <input type="text" class="form-control" id="surname" name="surname" placeholder="Surname" maxlength="15" required>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="email" class="control-label col-xs-3">Email</label>

                                    <div class="col-xs-8 bot">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="email"
                                               data-error="Bruh, that email address is invalid" required>
                                        <div class="help-block invisible alert-danger">Bruh, that email address is invalid</div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="birthday" class="control-label col-xs-3">Birthday</label>

                                    <div class="col-xs-8 bot" id = "datetimepicker1">
                                        <input type="date" class="form-control" id="birthday" name="birth" placeholder="Birthday">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="country" class="control-label col-xs-3">Country</label>

                                    <div class="col-xs-8 bot">
                                        <input type="text" class="form-control" id="country" name="country" placeholder="Country" maxlength="15" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="city" class="control-label col-xs-3">City</label>

                                    <div class="col-xs-8 bot">
                                        <input type="text" class="form-control" id="city" name="city" placeholder="City" maxlength="15" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="street" class="control-label col-xs-3">Street</label>

                                    <div class="col-xs-8 bot">
                                        <input type="text" class="form-control" id="street" name="street" placeholder="Street" maxlength="15" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="house" class="control-label col-xs-3">House</label>

                                    <div class="col-xs-8 bot">
                                        <input type="number" class="form-control" id="house" name="house" placeholder="House" maxlength="15" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="app" class="control-label col-xs-3">Apartment</label>

                                    <div class="col-xs-8 bot">
                                        <input type="number" class="form-control" id="app" name="app" placeholder="Apartment" maxlength="15" required>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="zip" class="control-label col-xs-3">Zip</label>

                                    <div class="col-xs-8 bot">
                                        <input type="number" class="form-control" id="zip" name="zip" placeholder="Zip">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="inputPassword" class="control-label col-xs-3">Password</label>
                                        <div class="col-xs-8">
                                            <input type="password" class="form-control" minlength="6" id="inputPassword" name="password" placeholder="" required>
                                            <div class="help-block">Minimum of 6 characters</div>
                                        </div>
                                    </div>
                                <div class="form-group">
                                    <label for="InputPasswordConfirm" class="control-label col-xs-3">Confirm a password</label>
                                        <div class="col-xs-8">
                                            <input type="password" class="form-control" minlength="6" id="inputPasswordConfirm"
                                                   name="password" placeholder="" required >
                                            <div class="help-block hidden" id="passcnf">Whoops, these don't match</div>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <button type="submit" class="btn btn-primary" onsubmit="sendRegistration()">Save</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
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
    $(function() {

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
<script type="text/javascript" src="js/moments/moment-with-locales.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" href="js/css/bootstrap-datetimepicker.min.css" />
<script>

    $('#datetimepicker1').datetimepicker({pickTime: false});

    function check() {
        if($('#inputPassword').val()!==$('#inputPasswordConfirm').val()){
            $('#passcnf').removeClass('hidden');
            $('#passcnf').addClass('show alert alert-danger');
            return false;
        }
        else{
            $('#passcnf').addClass('hidden');
            return true;
        }
    }

    function sendRegistration() {
        if(check()==true) {
            var form = $('#register-form');
            var arr = form.serializeArray();
            var toSend = {
                name: arr[0].value,
                surname: arr[1].value,
                email: arr[2].value,
                birth: arr[3].value,
                adress: {
                    country: arr[4].value,
                    city: arr[5].value,
                    street: arr[6].value,
                    house: arr[7].value,
                    app: arr[8].value,
                    zip: arr[9].value
                },
                password: arr[10].value
            };

            $.ajax({
                type: "POST",
                url: 'ajax/users',
                data: JSON.stringify(toSend),
                success: function () {
                    window.location.href = "getitems"
                },
                error: function (xhr, ajaxOptions, thrownError) {
                    alert('EMAIL ALREADY EXISTS');
                    $('.help-block.invisible').removeClass('invisible');
                }
            })
            return false;
        }
        else return false;
    }
    function check() {
        if($('#inputPassword').val()!==$('#inputPasswordConfirm').val()){
            $('#passcnf').removeClass('hidden');
            $('#passcnf').addClass('show alert alert-danger');
            return false;
        }
        else{
            $('#passcnf').addClass('hidden');
            return true;
        }
    }
    function sendLogIn() {
        var form = $('#login-form');
        $.ajax({
            type : 'POST',
            url: 'login',
            data: form.serialize(),
            success: function () {
                window.location.href = "getitems";
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert('ERROR: EMAIL OR PASSWORD INCORRECT');
            }
        })
    }
</script>

</html>
