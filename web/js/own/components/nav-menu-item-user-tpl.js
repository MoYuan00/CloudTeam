Vue.component('nav-menu-item-user-tpl', {
    template: `
         <el-dropdown>
          <el-button icon="el-icon-user"   style="cursor: pointer;" class="text-truncate;">
          </el-button>
          <el-dropdown-menu slot="dropdown">
                <el-dropdown-item  disabled >{{this.$store.state.loginUser.name}}</el-dropdown-item>
                <slot></slot>
                <el-dropdown-item @click.native="click_userInfo">编辑资料</el-dropdown-item>
                <el-dropdown-item @click.native="click_logout">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
    `,
    data(){
        return {
        }
    },
    props:['photo_url'],
    methods:{
        click_userInfo: function() {
            logger.debug('nav-menu-item-user-tpl click_userInfo点击了账号设置');
            Event.$emit('show-user-info-edit-dialog', this.$store.getters.getLoginUser);
        },
        click_logout(){
            const me = this;
            http.logout()
                .then(res=>{
                    me.$router.push({name:'login'});
                })
                .catch(res=>{
                me.$router.push({name:'login'});
            });
        }
    }
});
