// 头部导航栏模板 - 依赖于element-ui.index.js
Vue.component("nav-tpl", {
    template: `
            <main-tpl style="padding: 0px">
            <div>
<!--            左边的面包屑导航-->
                <span class="float-left" style="margin-top: 20px;">
                    <el-breadcrumb separator-class="el-icon-arrow-right">
                        <slot name="index_breadcrumb" >
                                 <el-breadcrumb-item   >
                                    <span class="el-icon-s-management" style="color: #409EFF;"></span>
                                    <router-link class="font-weight-bolder" :to="index_url || '/'">CloudTeam</router-link>
                                 </el-breadcrumb-item>
                        </slot>
                        <slot name="breadcrumb"></slot>
                    </el-breadcrumb>
                </span>
<!--               如果登录 显示消息图标-->
<!--                <span v-if="is_login" class="float-left" style="margin-top: 17px;padding: 0px 10px;">-->
<!--                    <el-badge :max="9" type="primary" value="12">-->
<!--                        <el-button icon="el-icon-bell" style="padding: 0px;" type="text">-->
<!--                        </el-button>-->
<!--                    </el-badge>-->
<!--                </span>-->
                
                <el-menu class="float-right" mode="horizontal" style="margin-top: -1px;">
<!--                登录和没登陆显示应该不同，通过is_login判断-->
                    <slot v-if="!is_login" name="login_menu_item">
                           <router-link  to="/login">
                                <el-button class="btn-menu-item" type="text">
                                    登录
                                </el-button>
                            </router-link>
                     </slot>
                     <slot name="menu-item"></slot>
                </el-menu>
                 <div v-if="is_login" class="float-right btn-menu-item" style="margin-top: 10px;margin-left: 5px">
                        <nav-menu-item-user-tpl>
                            <slot name="user-dropdown-item"></slot>
                        </nav-menu-item-user-tpl>
                 </div>
                <span v-if="is_login" class="float-right">
                    <slot name="login_right"></slot>
                </span>
            </div>
</main-tpl>
    `,
    props: ['is_login', 'index_url'],
});