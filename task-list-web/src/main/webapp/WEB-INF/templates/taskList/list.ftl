<#import "/spring.ftl" as spring>
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
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
    <link href="<@spring.url '/static/css/dashboard.css' />" rel="stylesheet">


    <!-- Custom styles for this template -->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="<@spring.url '/static/js/jquery-1.12.4.js' />" type="text/javascript"></script>
    <script src="<@spring.url '/static/plugins/bootstrap-3.3.7-dist/js/bootstrap.js' />"
            type="text/javascript"></script>
    <script src="<@spring.url '/static/js/jquery.form.js' />" type="text/javascript"></script>
    <script src="<@spring.url '/static/js/vue.js' />" type="text/javascript"></script>

    <!-- 定义component模板 -->
    <script type="text/x-template" id="grid-template">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Content</th>
                <th style="width: 27%">Action</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="entry in data">
                <td>{{entry.content}}</td>
                <td>
                    <input type="hidden" v-text="entry.id"/>
                    <button class="btn btn-success doneButton" type="button" name="">Done!</button>
                </td>
            </tr>
            </tbody>
        </table>
    </script>

    <script type="text/x-template" id="modal-template">
        <div id="tipModal" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;
                        </button>
                        <h4 class="modal-title">啦啦啦:</h4>
                    </div>
                    <div class="modal-body">
                        <p id="tipMessage" v-text="tipMessage"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="okButton">Okay</button>
                    </div>
                    <!-- form end -->
                </div>
            </div>
        </div>
    </script>

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
                <li><a href="/login/signOut" id="signOut">退出</a></li>
            </ul>
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
        <div>
            <!-- 这里要开始vue的模板 -->
            <template id="list" style="display:none">
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <h2 class="sub-header">Waiting Tasks...</h2>
                    <div class="table-responsive">
                        <demo-grid :data="testData">

                        </demo-grid>
                    </div>
                </div>
            </template>

            <template id="history" style="display:none">

            </template>

            <div id="app">
                <router-view></router-view>
            </div>
        </div>


    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<@spring.url '/static/js/vue.js' />" type="text/javascript"></script>
    <script src="<@spring.url '/static/js/vue-router.js' />" type="text/javascript"></script>
    <script src="<@spring.url '/static/js/vue-resource.js' />" type="text/javascript"></script>
    <script src="<@spring.url '/static/js/lodash.js' />" type="text/javascript"></script>
    <script src="<@spring.url '/static/js/axios.js' />" type="text/javascript"></script>
    <script src="<@spring.url '/static/templates/taskList/list-api.js' />" type="text/javascript"></script>
    <script src="<@spring.url '/static/templates/taskList/list-component.js' />" type="text/javascript"></script>
    <script src="<@spring.url '/static/templates/taskList/list-router.js' />" type="text/javascript"></script>
</body>
</html>
