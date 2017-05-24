<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" >
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Todo List</title>

    <!-- Bootstrap core CSS 两个的位置不能反 -->
    <link href="<@spring.url '/static/plugins/bootstrap-3.3.7-dist/css/bootstrap.css' />" rel="stylesheet">
    <link href="<@spring.ulr '/static/css/dashboard.css' />" rel="stylesheet">


    <!-- Custom styles for this template -->
    <!--<link href="dashboard.css" rel="stylesheet">-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="<@spring.url '/static/js/jquery-1.12.4.js' />" type="text/javascript"></script>
    <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>-->
    <script src="<@spring.url '/static/plugins/bootstrap-3.3.7-dist/js/bootstrap.js' />" type="text/javascript"></script>
    <script src="<@spring.url '/static/js/jquery.form.js' />" type="text/javascript"></script>
    <script src="<@spring.url '/static/js/vue.js' />" type="text/javascript"></script>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Task List</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <!--<li><a href="#">Dashboard</a></li>
                <li><a href="#">NewTask</a></li>-->
                <!--<li><a href="#">Profile</a></li>
                <li><a href="#">Help</a></li>-->
                <li><a href="/login/signOut" id="signOut">退出</a></li>
            </ul>
            <!--<form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
            </form>-->
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="/taskList/list">Current <span class="sr-only">(current)</span></a></li>
                <li><a href="#" id="newTaskButton" data-toggle="modal">New Task</a></li>
                <li><a href="#">Activities</a></li>
                <li><a href="/taskList/history">History</a></li>
            </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <!-- 这里要开始vue的模板 -->
            <div id="list">

            </div>

            <h2 class="sub-header">Waiting Tasks...</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Content</th>
                        <th width="26%">Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr th:each="task : ${tasks}">
                        <td th:text="${task.content}">

                        </td>
                        <td>
                            <input type="hidden" name="id" th:value="${task.id}"/>
                            <input type="hidden" name="label" th:value="${task.label}" />
                            <input type="hidden" name="content" th:value="${task.content}" />
                            <button class="btn btn-success doneButton" type="button" name="">Done!</button>
                            <button type="button" class="btn btn-default" name="modifyButton">Modify</button>
                            <button type="button" class="btn btn-default" name="laterButton">Later</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div>
                <!-- Modal HTML -->
                <div id="newTask" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
                                </button>
                                <h4 class="modal-title">Add Todo</h4>
                            </div>
                            <form class="form-inline" id="newTaskForm" action="/taskList/saveUpdate" method="post">
                                <div class="modal-body">
                                    <input type="hidden" id="inlineFormInputId" />

                                    <select class="form-control" name="label" id="inlineFormCustomSelect">
                                        <option th:each="label : ${labels}" th:value="${label.getName()}"
                                                th:text="${label.getValue()}"></option>
                                    </select>

                                    <!--<label class="sr-only" for="inlineFormInput">Name</label>-->
                                    <input type="text" class="form-control " id="inlineFormInput"
                                           placeholder="Something to do" name="content" style="width: 79%">

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancle</button>
                                    <button type="button" class="btn btn-primary" id="SaveTask">Save changes</button>
                                </div>
                                <!-- form end -->
                            </form>
                        </div>
                    </div>
                </div>

                <!-- tip modal -->
                <div id="tipModal" class="modal fade">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
                                </button>
                                <h4 class="modal-title">啦啦啦:</h4>
                            </div>
                            <div class="modal-body">
                                <p id="tipMessage"></p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-primary" id="okButton">Okay</button>
                            </div>
                            <!-- form end -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script>
    $(function () {
        //绑定新增任务按钮
        $('#newTaskButton').on('click', function () {
            $('#inlineFormInputId').val(0);
            $('#inlineFormInput').val('');
            $('#newTask').modal('toggle');

        });

        //绑定保存按钮
        $('#SaveTask').on('click', function () {
            var id = $('#inlineFormInputId').val();
            $('#newTask').modal('hide');
            $('#newTaskForm').ajaxSubmit({
                data:{
                    id : id
                },
                success: function (data) {
                    showData(data, '/taskList/list');
                },
                error: function () {
                    console.log('服务器繁忙!');
                }
            });
            //$('#newTaskForm').submit();
        });

        //绑定ok按钮
        $('#okButton').on('click', function () {
            $('#tipModal').hide();
        });

        //绑定完成按钮
        $('.doneButton').on('click', function () {
            var doneId = $(this).siblings('input[name="id"]').val();
            $.ajax(
                    {
                        type: 'POST',
                        url: '/taskList/done',
                        data: {
                            id: doneId
                        },
                        success: function () {
                            showData('成功!', '/taskList/list');
                        },
                        error: function () {
                            showData('服务器繁忙!');
                        }
                    }
            );
        });

        //绑定修改按钮
        $('button[name="modifyButton"]').on('click', function() {
            var taskId = $(this).siblings('input[name="id"]').val();
            var label = $(this).siblings('input[name="label"]').val();
            var content = $(this).siblings('input[name="content"]').val();
            //设置值
            $('#inlineFormCustomSelect').val(label);
            $('#inlineFormInput').val(content);
            $('#inlineFormInputId').val(taskId);
            $('#newTask').modal('show');
        });

        function showData(data, url) {
            $('#tipMessage').text(data);
            $('#tipModal').modal('show');
            setTimeout(function () {
                $('#tipModal').modal('hide');
                if (url) {
                    location.href = url;
                }
            }, 2000);
        }
    });
</script>
</body>
</html>
