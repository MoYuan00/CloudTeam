/**
 * 一个任务的抽屉
 */
Vue.component('drawers-work-tpl', {
    template: `
<div>
    <!--当点击任务的时候弹出抽屉-->
    <el-drawer size="#"
               :visible.sync="drawers.workDrawers"
               direction="rtl"
               title="任务详情"
               :destroy-on-close="true"
    >
            <el-container style="width: 275px">
                <el-main>
                    <el-form :model="work">
                        <form-item-mini-tpl>
                            <el-input @change="onWorkNameChanged(tempWorkName)" placeholder="任务名" slot="item" 
                            v-model="tempWorkName"></el-input>
                        </form-item-mini-tpl>
                        <form-item-mini-tpl>
                            <span slot="label"><i class="el-icon-circle-check"></i>状态</span>
                            <tag-tpl :data="work.workStatus"
                                     :data_list="workStatusList"
                                     data_show_name="status_name"
                                     @on_tag_tpl_value_change="onWorkStatusUpdate"
                                     slot="item">
                            </tag-tpl>
                        </form-item-mini-tpl>
                        <form-item-mini-tpl>
                            <span slot="label"><i class="el-icon-user"></i>执行者</span>
                            <tag-tpl :data="work.execUser" :data_list="teamUsers"
                                        data_show_name="name"
                                     @on_tag_tpl_value_change="onWorkExecUserChanging" 
                                     slot="item">
                            </tag-tpl>
                        </form-item-mini-tpl>
                        <form-item-mini-tpl>
                            <div slot="label"><i class="el-icon-date"></i>时间</div>
                            <span slot="item">
                                <el-date-picker placeholder="开始日期" size="mini" type="datetime"
                                                v-model="work.start_time" value-format="yyyy-MM-dd">
                                </el-date-picker>
                                <div><small>至</small></div>
                                <el-date-picker placeholder="结束日期" size="mini" type="datetime"
                                                v-model="work.end_time" value-format="yyyy-MM-dd">
                                </el-date-picker>
                            </span>
                        </form-item-mini-tpl>
                        <work-msg-list-tpl :msg_list="work.workMsgList"></work-msg-list-tpl>
                        <form-item-mini-tpl>
                            <el-input type="text" slot="item" v-model="inputWorkMsg">
                                <el-button @click="onClickReportWorkMsg(inputWorkMsg)" 
                                type="primary" slot="append">发布</el-button>
                            </el-input>
                        </form-item-mini-tpl>
                    </el-form>
                </el-main>
            </el-container>
    </el-drawer>
</div>
    `,
    data: function () {
        return {
            drawers: {
                workDrawers: false
            },
            work: {},
            teamUsers: [],
            workStatusList: [],
            inputWorkMsg: null,
            tempWorkName: null
        }

    },
    mounted: function () {
        Event.$on('show_work_drawers', this.on_work_card_click);// 监听事件
    },
    methods: {
        // 获取抽屉的大小
        getWorkDrawersSize: function () {
            return "#";
        },
        on_work_card_click: function (work, workStatusList, teamUsers) {
            logger.debug('drawers-work-tpl 监听到显示事件 show_work_drawers',
                {work,workStatusList,teamUsers});
            // 点击卡片，自己就显示
            this.drawers.workDrawers = true;
            this.work = work;
            this.tempWorkName = this.work.name;
            this.workStatusList = workStatusList;
            this.teamUsers = teamUsers;
        },
        // 状态切换的时候调用此方法
        onWorkStatusUpdate: function (work_status) {
            logger.debug('drawers-work-tpl onWorkStatusUpdate 接收到更新事件：', work_status);
            const me = this;
            http.updateWorkWorkStatusId(this.work.work_id, work_status.work_status_id)
                .then(res=>{
                    const data = res.data;
                    logger.debug("drawers-work-tpl updateWorkWorkStatusId响应", data);
                    if(data.errNo === 0){// 更新成功
                        // 更新状态
                        this.work.workStatus = work_status;
                        this.work.work_status_id = work_status.work_status_id;
                    }
                });

        },
        onWorkExecUserChanging: function (execUser) {
            logger.debug('drawers-work-tpl onWorkExecUserChanging 接收到更新事件：', execUser);
            http.updateWorkExecUserId(this.work.work_id, execUser.user_id)
                .then(res=>{
                    const data = res.data;
                    logger.debug("drawers-work-tpl updateWorkExecUserId 响应", data);
                    if(data.errNo === 0){// 更新成功
                        // 更新状态
                        this.work.execUser = execUser;
                    }
                });
        },
        onWorkNameChanged(name){
            logger.debug('drawers-work-tpl onWorkNameChanged 接收到更新事件：', name);
            http.updateWorkName(this.work.work_id, name)
                .then(res=>{
                    const data = res.data;
                    logger.debug("drawers-work-tpl updateWorkExecUserId 响应", data);
                    if(data.errNo === 0){// 更新成功
                        // 更新状态
                        this.work.name = name;
                    }
                });
        },
        onClickReportWorkMsg(work_msg){// 点击发布消息按钮
            const me = this;
            logger.debug('drawers-work-tpl onClickReportWorkMsg 点击了发布按钮', work_msg);
            if(!work_msg || work_msg === ''){
                Event.$emit('on-show-msg', '输入消息为空！','warning');
                return;
            }
            http.reportWorkMsg(this.work.work_id, this.$store.getters.getLoginUserId, logger.getNowData(), work_msg)
                .then(res => {
                    const data = res.data;
                    logger.debug('reportWorkMsg 返回信息', data);
                    if (data.errNo === 0) {
                        me.work.workMsgList.push(data.data.workMsg);
                    }
                });
            this.inputWorkMsg = null;
        }
    }
});