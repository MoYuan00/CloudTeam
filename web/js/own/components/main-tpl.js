// 内容区域模板
Vue.component("main-tpl", {
    template: `
            <el-main  style="padding-left: 3px;padding-right: 3px;">
                <div>
                    <!--这里加一层是为了当屏幕特别小的时候能够左右没有留白。-->
                    <el-row type="flex" justify="center">
                        <el-col :xs="24" :sm="23" :md="19" :lg="20">
                        <div style="padding: 0px 3px">
                            <!--插入自定义内容-->
                            <slot>slot not have context</slot>
                        </div>
                         </el-col>
                        <!--这里加一层是为了当屏幕特别小的时候能够左右没有留白。-->
                    </el-row>
                </div>
            </el-main>
        `,
    data(){
        return {
            height:null
        }
    },

});