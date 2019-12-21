
const userIndex = {
    path: '/userIndex/:userId',
    name: 'userIndex',
    meta:{require_login: true},
    components:{
        nav:{
            template: `
            <div>
                 <nav-tpl :is_login="true" :index_url="'/userIndex/'+ userId">
                    <el-button @click="clickNewTeam" slot="login_right" class="btn-menu-item" type="text"
                     icon="el-icon-circle-plus-outline">新建团队</el-button>
                 </nav-tpl>
            </div>
            `,
            data(){
                return{
                    userId: this.$route.params.userId
                }
            },
            methods: {
                clickNewTeam(){
                    logger.debug('userIndex 点击新建团队');
                    Event.$emit('show-add-team-dialog');
                }
            }
        },
        context: {
            template: `
        <div>
            <el-tabs :tab-position="el_tabs.tabHeaderPosition" :type="el_tabs.tabType">
                <el-tab-pane>
                    <span slot="label">
                    全部团队<span class="badge badge-light">{{allTeams.length}}</span>
                    </span>
                    <card-team-list-tpl   :teams="allTeams"></card-team-list-tpl>
                </el-tab-pane>
                <el-tab-pane>
                    <span slot="label">
                    创建的团队<span class="badge badge-light">{{createTeams.length}}</span>
                    </span>
                    <card-team-list-tpl   :teams="createTeams"></card-team-list-tpl>
                </el-tab-pane>
                <el-tab-pane>
                    <span slot="label">
                    加入的团队<span class="badge badge-light">{{joinTeams.length}}</span>
                    </span>
                    <card-team-list-tpl  :teams="joinTeams"></card-team-list-tpl>
                </el-tab-pane>
            </el-tabs>
            <add-team-dialog-tpl @create_team_success="create_team_on_vue"></add-team-dialog-tpl>
            <more-team-dialog-tpl @delete_team_success="delete_team_on_vue" ></more-team-dialog-tpl>
            <user-info-edit-dialog-tpl></user-info-edit-dialog-tpl>
        </div>
            `,
            data: function () {
                return {
                    el_tabs: {
                        tabHeaderPosition: 'top',
                        // tabType: 'card'
                    },
                    createTeams: [],
                    joinTeams: [],
                    userId: null,
                };
            },
            mounted: function () {
                const me = this;
                const userId = this.$route.params.userId;// 当用户主页挂载完成后，要准备到数据库获取数据
                this.userId = userId;
                logger.debug('userIndex mounted 当前userId:', userId);
                Event.$on('team_card_clicked', this.onClickTeam);// 监听团队卡片点击事件
                // 获取数据
                this.getUserTeamInfo(userId);
            }
            ,
            methods: {
                getUserTeamInfo: function (userId) {
                    let me = this;
                    http.getCreateTeamsByUserId(userId)
                        .then(msg=>{
                            logger.debug('getCreateTeamsByUserId', msg.data.data);
                            me.createTeams = msg.data.data.createTeams;
                        });
                    http.getJoinTeamsByUserId(userId)
                        .then(msg=>{
                            logger.debug('getJoinTeamsByUserId', msg.data.data);
                            me.joinTeams = msg.data.data.joinTeams;
                        });
                }
                ,
                onClickTeam: function (team) {
                    logger.debug('userIndex >> onClickTeam 点击了一个团队卡片', team);
                    // Event.$emit('on-click-team-card', this.userId, team);
                    this.$router.push({name: 'teamIndex',
                        params: {userId:this.userId, teamId: team.team_id}});
                },
                delete_team_on_vue(team){
                    logger.debug('userIndex delete_team_on_vue 删除某个团队', team);
                    let index = this.createTeams.indexOf(team);
                    logger.debug('userIndex delete_team_on_vue 删除某个团队。当前删除下标为', index);
                    //删除
                    const del = this.createTeams.splice(index, 1);
                },
                create_team_on_vue(team){
                    logger.debug('userIndex create_team_on_vue 添加某个团队', team);
                    this.createTeams.push(team);
                }
            },
            computed:{
                allTeams(){
                    return this.createTeams.concat(this.joinTeams);
                }
            }
        }
    }
};
