let routes = [
    login,
    register,
    index,
    userIndex,
    teamIndex
];
const store = new Vuex.Store({
    state: {
        loginUser: {},

        nowSelectTeam: {},
        // 任务状态
        workStatusList: [],
        windows: {width: 0, height: 0}
    },
    getters: {
        getWinSize(state) {
            return state.windows;
        },
        getTeam(state) {
            return state.nowSelectTeam;
        },
        getTeamId(state) {
            return state.nowSelectTeam.team_id;
        },
        getTeamUsers(state) {
            let a = [];
            a = clone(state.nowSelectTeam.joinUserList);
            a.push(state.nowSelectTeam.captainUser);
            return a;
        },
        getWorkStatusList(state) {
            return state.workStatusList;
        },
        isLogin(state) {
            return store.getters.getLoginUserId ? true : false;
        },
        getLoginUserId(state) {
            if (store.getters.getLoginUser && store.getters.getLoginUser.user_id)
                return store.getters.getLoginUser.user_id;
            return null;
        },
        getLoginUser(state) {
            const user = state.loginUser;
            const me = this;
            if (!user || !user.user_id) {// 如果当前没有登录的用户，就去尝试获取一下
                http.getLoginUser()
                    .then(res => {
                        const data = res.data;
                        logger.debug('store getLoginUser getLoginUser获取用户响应', data);
                        if (data.errNo == 0)
                            state.loginUser = data.data.user;
                        else// 没有登录
                            Event.$emit('require-login');
                    });
            }
            logger.debug('store getLoginUser获取当前登录用户', state.loginUser);
            return state.loginUser;
        }
    },
    mutations: {
        changeLoginUser(state, loginUser) {
            logger.debug('store changeLoginUser', loginUser);
            state.loginUser = loginUser;
        },
        changeSelectTeam(state, team) {
            logger.debug('store changeSelectTeam', team);
            state.nowSelectTeam = team;
        },
        setWorkStatusList(state, workStatus) {
            logger.debug('store setWorkStatus', workStatus);
            state.workStatusList = workStatus;
        },
        resize(state, size) {
            logger.debug('store resize', [size.width, size.height]);
            state.windows.width = size.width;
            state.windows.height = size.height;
        },
    }
});

function getWinSize() {
    const width = $(window).width();// jquery
    const height = window.innerHeight;// bom
    return {width: width, height: height};
}

// 获取窗口大小
function getDomSize() {
    let offsetWid = document.documentElement.clientWidth;// dom
    let offsetHei = document.documentElement.clientHeight;
    if (/(Android)/i.test(navigator.userAgent)) {     // 判断是否为Android手机
        offsetWid = screen.width;
        offsetHei = screen.height;
    } else if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)) {  // 判断是否为苹果手机
        offsetWid = document.documentElement.clientWidth;
        offsetHei = document.documentElement.clientHeight;
    }
    return {width: offsetWid, height: offsetHei};
}

// 先获取一次窗口大小
store.commit('resize', getWinSize());
// 监听窗口大小变化，并实时更新
window.onresize = function () {
    store.commit('resize', getWinSize());
};
let router = new VueRouter({
    routes: routes
});

router.beforeEach((to, from, next) => {
    // TODO 不知道如何同步获取当前登录用户， axios 不行, 考虑使用ajax
    // logger.debug('beforeEach:to', to);
    // let logged_in = store.getters.isLogin;
    // if(!logged_in && to.matched.some(item=>{
    //     return item.meta.require_login;
    // })){// 如果没有登录，并且要求必须登录，
    //     logger.debug('如果没有登录，并且要求必须登录');
    //     const user = store.getters.getLoginUser;// 获取用户，如果获取不成功会自动跳转到登录
    //     next({name:'login'});
    // }else{
    next();
    // }
});
// 自己测试猜测 如果使用了router，那么不可以嵌套有其他的Vue实例父级或子级都不行
let app = new Vue({
    el: '#app',
    router: router,
    // 把 store 对象提供给 “store” 选项，这可以把 store 的实例注入所有的子组件 且子组件能通过 this.$store 访问到
    store,
    data: {
        loading: 0,
        loadingText: ''
    },
    created: function () {
        // 显示一条提示信息
        Event.$on('on-show-msg', (msg, type) => {
            if (msg) {
                this.$message({message: msg, type: type, showClose: true, duration: 2400});
            }
        });
    },
    mounted() {
        const me = this;
        // 监听加载中事件
        Event.$on('start-loading', (loginText) => {
            // logger.debug('router on start-loading触发加载事件', me.loading);
            if (!me.loading++) {// 如果从没有加载切换到加载状态
            }
            if (loginText)
                me.loadingText = loginText;
            else me.loadingText = null;
        });
        Event.$on('finish-loading', () => {
            // logger.debug('router on finish-loading触发加载完成事件', me.loading);
            if (me.loading > 0) {// 如果从没有加载切换到加载状态
                // 添加300毫秒延迟，确保可以显示出加载层
                setTimeout(() => {
                    me.loading--;
                }, 200);
            }
        });
        // 当前没有登录，必须要登录
        Event.$on('require-login', () => {
            // if(me.$router.)
            Event.$emit('start-loading', '当前没有登录2秒后跳到登录页面');
            logger.debug('当前没有登录，即将跳转到登录页面！');
            setTimeout(
                () => {
                    Event.$emit('finish-loading');
                    me.$router.push({name: 'login'});
                }, 2000);
        });
    }
});



