Vue.component('more-team-dialog-tpl', {
    template: `
    <div>
        <el-dialog center title="团队设置" :visible.sync="visible" width="280px" >
            <el-form :model="team">
                <form-item-mini-tpl>
                    <span slot="label">团队名</span>
                    <el-input v-model="team.team_name" slot="item" autocomplete="off"></el-input>
                </form-item-mini-tpl>
                 <form-item-mini-tpl>
                    <span slot="label">队长名</span>
                    <el-input slot="item" disabled v-model="team.captainUser.name" autocomplete="off"></el-input>
                </form-item-mini-tpl>
                 <form-item-mini-tpl   >   
                    <span slot="label">团队创建时间</span>
                    <el-input slot="item" disabled :value="team.team_build_time"></el-input>
                </form-item-mini-tpl>
                <form-item-mini-tpl  >
                    <span slot="label">团队详情</span>
                    <el-input slot="item" type="textarea" v-model="team.team_detail"></el-input>
                </form-item-mini-tpl>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="click_save">保 存</el-button>
                <el-popover v-if="team.captainUser.user_id == $store.getters.getLoginUserId"
                  placement="top"
                  width="160"
                  v-model="configDeleteVisible">
                      <p>确定要解散此团队吗？<br/>（解散团队将是不可恢复的，将删除所有的任务以及加入的成员等信息！）</p>
                      <div style="text-align: right; margin: 0">
                        <el-button size="mini" type="text" @click="configDeleteVisible = false">取消</el-button>
                        <el-button type="danger" @click="click_remove"  
                                                     >确定</el-button>
                      </div>
                     <el-button plain type="danger" slot="reference">解 散</el-button>
                </el-popover>
            </div>
        </el-dialog>
    </div>
    `,
    data() {
        return {
            team: {
                captainUser: {}
            },
            visible: false,
            configDeleteVisible:false,
            _team: null
        }
    },
    mounted() {
        Event.$on('show-more-team-dialog', this.showDialog);
    },
    methods: {
        showDialog(team) {
            logger.debug('more-team-dialog-tpl showDialog显示', team);
            this._team = clone(team);// 保存一份，如果更新失败可以还原
            this.team = team;
            this.visible = true;
        },
        click_save() {
            const me = this;
            http.updateTeamInfo(this.team)
                .then(res => {
                    const data = res.data;
                    Event.$emit('on-show-msg', data.message);
                });

            this.visible = false;
        },
        click_remove() {// 解散团队
            const me = this;
            http.deleteTeam(this.team.team_id)
                .then(res => {
                    const data = res.data;
                    logger.debug('more-team-dialog-tpl click_remove deleteTeam删除团队响应', data);
                    me.visible = false;
                    me.$emit('delete_team_success', me.team);
                });
            me.configDeleteVisible = false;
        }
    }

});