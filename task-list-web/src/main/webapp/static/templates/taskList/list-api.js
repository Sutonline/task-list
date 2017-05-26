/**
 * Created by yongkang.zhang on 2017/5/25.
 */
(function (root, $http) {
    var api = {};
    api.taskUri = "/taskList/";
    api.query = function (q, page, pageSize, func1, func2) {
        $http.get('url').then(function (response) {
            func1(response)
        }, function (response) {
            if (func2) func2(response);
        });
    };

    api.getTodos = function (url, func) {
        $http.get(api.taskUri + url).then(function (response) {
            func(response.data);
        });
    };

    api.post = function (url, q, func) {
        $http.post(url, q).then(function (response) {
            func(response.data);
        });
    };

    root.TaskApi = api;
})(window, Vue.http);