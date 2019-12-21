// 一个任务卡片的显示
Vue.component("card-work-tpl", {
    template: `
    <!--vue给dom元素添加点击事件时要用@click.native，按钮、选择器、步骤条之类的组件用@click-->
    <el-card  @click.native="on_card_click()" class="work-card cursor-hand" shadow="hover" 
                style="margin-bottom: 10px;height: 130px">
        <div class="float-right" style="margin-right: -10px;margin-top: -10px;">
            <el-button style="padding:0px;" type="text">
            </el-button>
        </div>
<!--        更多操作的按钮-->
        <div v-if="work.publish_user_id == $store.getters.getLoginUserId" class="float-right" style="margin-top: -8px;margin-right: 5px;">
            <el-popover
                width="180"
                v-model="moreVisitable">
                <p>确定删除任务：
                    <div class="text-info text-break">
                    {{work.name}}
                    </div>
                吗？</p>
                <div>
                    <el-button type="danger" size="mini" icon="el-icon-delete" @click="on_click_delete_true_button">确 定</el-button>
                    <el-button size="mini" type="primary" @click="moreVisitable = false">取 消</el-button>
                </div>
                <el-button @click="e=>(e.stopPropagation())" slot="reference" 
                    icon="el-icon-more-outline" style="padding: 0px;" type="text">
                </el-button>
            </el-popover>
        </div>
        <div style="margin-bottom: 10pt; " class="font-weight-bold">
            {{work.name}}
        </div>
        <div style="margin-bottom: 5pt;">
              <small>
                 <i class="el-icon-user text-truncate" style="width: 130px">执行者：
                    <template v-if="work.execUser">
                        {{work.execUser.name}} 
                    </template>
                 </i>
                <i class="el-icon-s-custom text-truncate" style="width: 130px" >发布者：
                    <template v-if="work.publishUser">
                        {{work.publishUser.name}}
                    </template>
                </i>   
              </small>
        </div>
        <div style="margin-bottom: 2pt;">
            <span style="font-size:  small">
                <i class="el-icon-date">开始时间：{{work.start_time}}</i>
            </span>
        </div>    
        <div >
            <span style="font-size:  small">
                <i class="el-icon-date">截至时间：{{work.end_time}}</i>
            </span>
        </div>
    </el-card>
  `,
    data(){
        return {
          moreVisitable: false
        }
    },
    props: ["work"],
    methods: {
        // 点击一个卡片，
        on_card_click: function () {
            logger.debug('card-work-tpl 被点击', this.work);
            // 触发事件 通知其他组件点击了卡片
            Event.$emit('on_card_click', this.work);
        },
        // 当点击了确定删除按钮
        on_click_delete_true_button(){
            const me = this;
            this.moreVisitable = false;
            logger.debug('card-work-tpl on_click_delete_button 点击了确定删除按钮');
            me.$emit('delete-work-success', me.work);
            http.deleteWorkByWorkId(this.work.work_id)
                .then(res=>{
                    const data = res.data;
                    logger.debug('card-work-tpl deleteWorkByWorkId 响应信息', data);
                    // 删除成功

                });
        }
    },
    filters:{
    }
});