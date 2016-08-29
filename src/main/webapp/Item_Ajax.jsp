<%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 28.08.2016
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="webjars/datatables/1.10.11/css/jquery.dataTables.min.css">
    <script type="text/javascript" src="webjars/jquery/2.2.3/jquery.min.js"></script>
    <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.js"></script>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <nav class="navbar navbar-default" role="navigation">
                <div class="navbar-header">

                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                    </button> <a class="navbar-brand" href="#">Brand</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="#">Link</a>
                        </li>
                        <li>
                            <a href="#">Link</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">Action</a>
                                </li>
                                <li>
                                    <a href="#">Another action</a>
                                </li>
                                <li>
                                    <a href="#">Something else here</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">Separated link</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">One more separated link</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input class="form-control" type="text" />
                        </div>
                        <button type="submit" class="btn btn-default">
                            Submit
                        </button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#">Link</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown<strong class="caret"></strong></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="#">Action</a>
                                </li>
                                <li>
                                    <a href="#">Another action</a>
                                </li>
                                <li>
                                    <a href="#">Something else here</a>
                                </li>
                                <li class="divider">
                                </li>
                                <li>
                                    <a href="#">Separated link</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>

            </nav>
        </div>
    </div>
</div>

<div class="jumbotron">
    <div class="container">
    <div class="shadow">
        <h3>Список товаров</h3>

        <div class="view-box">
            <a class="btn btn-sm btn-info" onclick="add()">Добавить товар</a>

            <table class="table table-striped display" id="datatable">
                <thead>
                <tr>
                    <th aria-hidden="true">Id</th>
                    <th>Name</th>
                    <th>Price</th>
                    <th>Theme</th>
                    <th>Quantity</th>
                    <th>Description</th>
                    <th>Image</th>
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
                <form class="form-horizontal" action="ajax/items" method="post" id="detailsForm" enctype="multipart/form-data" data-toggle="validator" role="form">
                    <input type="text" hidden="hidden" id="id" name="id">

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
<script type="text/javascript" src="js/dataTableUtils.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">

    var ajaxUrl = 'ajax/items';
    var datatableApi;

    function updateTable() {
        $.get(ajaxUrl, function (data) {
            updateTableByData(data);
        });
    }
    function makeEditable() {
        $('#detailsForm').submit(function () {
            save();
            return false;
        });

        $(document).ajaxError(function (event, jqXHR, options, jsExc) {
            // failNoty(event, jqXHR, options, jsExc);
        });
    }
//    function save() {
//        var form = $('#detailsForm');
//        $.ajax({
//            type: "POST",
//            url: ajaxUrl,
//            data: form.serialize(),
//            success: function () {
//                $('#editRow').modal('hide');
//                updateTable();
//                // successNoty('Saved');
//            }
//        })
//    }

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
                    "data": "price"
                },
                {
                    "data": "theme"
                },
                {
                    "data": "quantity"
                },
                {
                    "data": "description"
                },
                {
                    "defaultContent" : "",
                    "render" : function (data,type,row) {
                        return "<img "+
                            "id = 'imgFromModel'"+
                                        "alt='Нет изображения' width='200' height='200'"+
                            "src = 'http://localhost:8080/universe/ajax/items?fotoId="+row.id+"'/>";
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
    });
</script>
</html>
