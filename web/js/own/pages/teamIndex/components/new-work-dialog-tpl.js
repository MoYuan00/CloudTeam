Vue.component('new-work-dialog-tpl', {
    template: `
    <el-dialog :destroy-on-close="false" center :visible.sync="visible" title="新任务" width="270px">
        <el-form status-icon :rules="rules" :model="work" ref="work">
            <el-form-item prop="name" size="small">
                <el-input maxlength="20" show-word-limit  
                @input="work.name = inputFilter(work.name)"
                clearable placeholder="任务名 (必填) " v-model="work.name" autocomplete="off">
                </el-input>
            </el-form-item>
            <form-item-mini-tpl>
                <span slot="label">执行者</span>
                <tag-tpl
                 slot="item" 
                 @on_tag_tpl_value_change="onExecUserChanged"
                 :data_list="teamUsers" 
                 :data="execUser" 
                 data_show_name="name"></tag-tpl>
            </form-item-mini-tpl>
             <el-form-item  size="small">
                <span slot="label">任务状态</span>
                <tag-tpl
                @on_tag_tpl_value_change="onWorkStatusChanged"
                :data_list="workStatusList" 
                :data="workStatus" 
                data_show_name="status_name" :value="workStatus" ></tag-tpl>
            </el-form-item>  
            <form-item-mini-tpl>
                <span slot="label">开始时间</span>
                <el-date-picker slot="item" placeholder="开始日期" size="mini" type="datetime"
                                v-model="work.start_time" value-format="yyyy-MM-dd">
                </el-date-picker>
            </form-item-mini-tpl> 
            <form-item-mini-tpl>
                <span slot="label">截止时间</span>
                <el-date-picker slot="item" placeholder="截止日期" size="mini" type="datetime"
                                v-model="work.end_time" value-format="yyyy-MM-dd">
                </el-date-picker>
            </form-item-mini-tpl>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="click_save">创 建</el-button>
        </div>
    </el-dialog>
   `,
    data() {
        return {
            work: {},
            execUser: {},
            workStatus: null,
            workStatusList: [],
            teamUsers: [],
            visible: false,
            rules: {
                name: [
                    {required: true, message: '请输入名称', trigger: 'blur'},
                    {min: 2, message: '至少为 2 个字符！', trigger: 'blur'}
                ]
            }
        }
    },
    mounted() {
        Event.$on('show-new-work-dialog', this.showDialog);
    },
    methods: {

        showDialog(teamUsers, workStatusList) {
            this.workStatusList = workStatusList;
            this.teamUsers = teamUsers;
            this.visible = true;
        },
        click_save() {
            const me = this;
            logger.debug('new-work-dialog-tpl click_save点击了创建任务',
                [me.work, me.execUser, me.workStatus]);
            this.$refs.work.validate((valid) => {
                if (valid) {// 如果通过了验证器，就提交
                    if (me.workStatus) {// 如果选择了状态
                        // 处理输入的数据
                        me.work.publish_user_id = me.$store.getters.getLoginUserId;
                        me.work.work_status_id = me.workStatus.work_status_id;
                        me.work.exec_user_id = me.execUser.user_id;
                        me.work.team_id = me.$store.getters.getTeamId;
                        logger.debug('new-work-dialog-tpl click_save要创建的任务为: ', me.work);
                        http.appendWork(me.work)
                            .then(res => {
                                const data = res.data;
                                logger.debug('appendWork响应', data);
                                if (data.errNo == 0) {
                                    me.$emit('create_work_success', data.data.work);
                                }
                            });
                        this.visible = false;
                    }else{
                        this.$alert('必须要为任务选择一种[任务状态]', 'tip', {
                            confirmButtonText: '确定',
                            callback: action => {
                            }
                        });
                    }
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        onWorkStatusChanged(workStatus) {
            this.workStatus = workStatus;

        },
        onExecUserChanged(execUser) {
            this.execUser = execUser;
        }
    }

});