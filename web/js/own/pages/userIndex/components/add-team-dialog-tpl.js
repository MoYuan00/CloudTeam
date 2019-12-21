Vue.component('add-team-dialog-tpl', {
    template: `
    <el-dialog :destroy-on-close="false" center :visible.sync="visible" title="创建团队" width="270px">
        <el-form  :model="team"  >
            <el-form-item prop="name">
                <el-input maxlength="20" show-word-limit 
                @input="team.team_name = inputFilter(team.team_name)"
                clearable placeholder="团队名称 (必填) " v-model="team.team_name" >
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-input :autosize="{ minRows: 2, maxRows: 4}" maxlength="400" show-word-limit type="textarea" v-model="team.team_detail" placeholder="团队简介 (选填) ">
                </el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="on_click_create_true_team">完成并创建</el-button>
        </div>
    </el-dialog>
    `,
    data() {
        return {
            team: {},
            visible: false,
        }
    },
    mounted() {
        Event.$on('show-add-team-dialog', this.showDialog);
    },
    methods: {
        on_click_create_true_team() {
            const me = this;
            logger.debug('add-team-dialog-tpl on_click_create_true_team点击了创建');
            if (me.team && me.team.team_name && me.team.team_name !== '') {// 如果有团队名
                http.appendTeam(this.team)
                    .then(res => {
                        const data = res.data;
                        logger.debug('on_click_create_true_team appendTeam响应', data);
                        if (data.errNo == 0) {// 如果成功
                            me.$emit('create_team_success', data.data.team);
                        }
                    });
                this.visible = false;
            }else{
                this.$alert('必须!必须!必须!要填写团队名', 'tip', {
                    confirmButtonText: '确定',
                    callback: action => {
                    }
                });
            }
        },
        showDialog() {
            logger.debug('add-team-dialog-tpl 监听到show-add-team-dialog事件 ');
            this.visible = true;
        }
    }
});