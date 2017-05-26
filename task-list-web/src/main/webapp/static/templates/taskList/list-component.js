/**
 * Created by yongkang.zhang on 2017/5/25.
 */

Vue.component('demo-grid', {
    template: '#grid-template',
    props: {
        data: Array
    },
    data: function () {
        return {};
    }
});

//新建app对象
var app = {};

app.list = {
    template: '#list',
    data: function (transition) {
        var q = this.$route.query;
        return {
            //$route是一个对象，包含meta, params, path, query等属性。可以从query属性解出来需要的参数
            name: 'Kevin',
            testData: [],
        };
    },
    mounted: function () {
        //加载当前待办数据
        this.getCurrentTodo();
        console.log(this.testData);
    },
    methods: {
        getCurrentTodo: function () {
            TaskApi.getTodos('selectNonFinish', function (response) {
                this.testData = response;
            }.bind(this));
        }
    }
};

app.history = {
    template: '#history'
};
