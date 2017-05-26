/**
 * Created by yongkang.zhang on 2017/5/26.
 */
Vue.config.devtools = true;
var routes = [
    {
        path: '/history',
        component: app.history
    },
    {
        path: '/',
        component: app.list
    }
];
//新建一个router实例
var router = new VueRouter({
    routes
});
//新建一个vue的实例
var App = new Vue({
    router
}).$mount('#app');
