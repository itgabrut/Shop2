<%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 26.08.2016
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.11/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="webjars/jquery/2.2.3/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>
    <link href="css/megamenu.css" rel="stylesheet" type="text/css">
    <link href="css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/bootstrap-checkbox.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
</head>


<body>

<div class="menu">
    <ul class="megamenu skyblue "><li class="showhide" style="display: none;"><span class="title">MENU</span><span class="icon1"></span><span class="icon2"></span></li>
        <li style="display: inline;"><a class="color10" href="help">Clients list</a></li>
        <li style="display: inline;"><a class="color3" href="orders?clientId=${loggedClient.id}">Orders list</a></li>
        <li style="display: inline;"><a class="color7" href="404.html">News</a></li>
        <div class="clearfix"> </div>
    </ul>
</div>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3>Список клиентов</h3>

            <div class="view-box">
                <a class="btn btn-sm btn-info" onclick="add()">Добавить клиента</a>

                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th aria-hidden="true">Id</th>
                        <th>Name</th>
                        <th>Surname</th>
                        <th>Email</th>
                        <th>Roles</th>
                        <th>Registered</th>
                        <th>Birthday</th>
                        <th>Adress</th>
                        <th>Act</th>
                        <th>Act</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="detailsForm" autocomplete="off" data-toggle="validator" role="form">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="Name" maxlength="15" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="surname" class="control-label col-xs-3">Surname</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="surname" name="surname" placeholder="Surname" maxlength="15" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3">Email</label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="email"
                                   data-error="Bruh, that email address is invalid" required>
                            <div class="help-block invisible alert-danger">Choose another e-mail</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="birthday" class="control-label col-xs-3">Birthday</label>

                        <div class="col-xs-9" id = "datetimepicker1">
                            <input type="date" class="form-control" id="birthday" name="birth" placeholder="Birthday">
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="country" class="control-label col-xs-3">Country</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="country" name="country" placeholder="Country" maxlength="15" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="city" class="control-label col-xs-3">City</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="city" name="city" placeholder="City" maxlength="15" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="street" class="control-label col-xs-3">Street</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="street" name="street" placeholder="Street" maxlength="15" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="house" class="control-label col-xs-3">House</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="house" name="house" placeholder="House" maxlength="15" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="app" class="control-label col-xs-3">Apartment</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="app" name="app" placeholder="Apartment" maxlength="15" >
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="zip" class="control-label col-xs-3">Zip</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="zip" name="zip" placeholder="Zip">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="check" class="control-label col-xs-3 checkbox">Make Admin</label>

                        <div class="col-xs-9">
                            <input type="checkbox" class="form-control" id="check" name="check">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputPassword" class="control-label col-xs-3">Password</label>
                        <div class="form-inline row">
                          <div class="col-xs-4">
                            <input type="password" class="form-control" minlength="6" id="inputPassword" name="password" placeholder="" required>
                            <div class="help-block">Minimum of 6 characters</div>
                          </div>
                            <div class="col-xs-4">
                                <input type="password" class="form-control" minlength="6" id="inputPasswordConfirm"
                                       name="password" placeholder="" required>
                                <div class="help-block hidden" id="passcnf">Whoops, these don't match</div>
                            </div>
                            </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary" onsubmit="check()">Save</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<%--contentType: "application/json; charset=UTF-8",--%>

<script type="text/javascript" src="webjars/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<%--<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>--%>
<script type="text/javascript" src="js/dataTableUtils.js"></script>
<script type="text/javascript" src="js/moments/moment-with-locales.min.js"></script>
<script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
<link rel="stylesheet" href="js/css/bootstrap-datetimepicker.min.css" />
<script type="text/javascript">

    var ajaxUrl = 'ajax/users';
    var datatableApi;

    function updateTable() {
        $.get(ajaxUrl, function (data) {
            updateTableByData(data);
        });
    }
    function save() {
        var form = $('#detailsForm');
//         $.ajax({
//             type: "POST",
//             url: ajaxUrl,
//             data: form.serialize(),
//             success: function () {
//                 $('#editRow').modal('hide');
//                 updateTable();
//                 // successNoty('Saved');
//             }
//         });
        var arr = form.serializeArray();

        var toSend = {
            id : arr[0].value,
            name : arr[1].value,
            surname : arr[2].value,
            email : arr[3].value,
            birth : arr[4].value,
            adress : {
                country : arr[5].value,
                city : arr[6].value,
                street : arr[7].value,
                house : arr[8].value,
                app : arr[9].value,
                zip : arr[10].value
            },
            roles : arr[11].value == 'on' ? 'ROLE_ADMIN' : 'ROLE_USER',
            password : arr[12].value
        };
        var ddd = JSON.stringify(toSend);
        $.ajax({
            type : "POST",
            url : ajaxUrl,
            data : ddd,
            success : function () {
                $('#editRow').modal('hide');
                updateTable();
            },
            error: function (xhr, ajaxOptions, thrownError) {
                alert(thrownError);
                $('.help-block.invisible').removeClass('invisible');
            }
        })
    }
    function makeEditable() {
        $('#detailsForm').submit(function () {
            if(check()==true) {
                save();
            }
            return false;
        });

        $(document).ajaxError(function (event, jqXHR, options, jsExc) {
            // failNoty(event, jqXHR, options, jsExc);
        });
    }

    // $(document).ready(function () {
    $(function () {
        datatableApi = $('#datatable').DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "id"
                },
                {
                    "data": "name"
                },
                {
                    "data": "surname"
                },
                {
                    "data": "email"
                },
                {
                    "data": "roles"
                },
                {
                    "data": "registered"
                },
                {
                    "data": "birth"
                },
                {
                    "data": "adress",
                    "render" : function (data,type,row) {
                        return data.country+" </br>"+data.city+" </br>"+data.street+" "
                                +" "+data.house+"/"+data.app+" </br> zip: "+data.zip;
                    }
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false,
                    "render" : renderEditBtn
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false,
                    "render" : renderDeleteBtn
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        });
        datatableApi.column(0).visible(false);
        updateTable();
        makeEditable();
        $(':checkbox').checkboxpicker();
    });
</script>
</html>
