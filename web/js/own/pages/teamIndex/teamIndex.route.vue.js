const teamIndex = {

    path: '/teamIndex/:teamId/:userId',
    name: 'teamIndex',
    meta: {require_login: true},
    components: {
        nav: {
            template: `
             <nav-tpl  :is_login="true" :index_url="'/userIndex/'+ userId">
                <el-tooltip slot="breadcrumb" class="item" effect="dark" :content="teamName" placement="bottom">
                   <el-breadcrumb-item  class="text-truncate" style="width: 62pt">
                        {{teamName}}
                    </el-breadcrumb-item>
                </el-tooltip>
                
    <!--                显示在导航栏上-->
                     <el-button
                     v-if="$store.getters.getWinSize.width > 350"
                     slot="login_right" @click="click_new_work" class="btn-menu-item" type="text"
                         icon="el-icon-circle-plus-outline">
                         新建<span v-if="$store.getters.getWinSize.width > 400">任务</span>
                     </el-button>
    <!--                 如果屏幕过小，就显示在菜单里-->
                     <el-dropdown-item v-else slot="user-dropdown-item" @click="click_new_work" >
                        <span class="el-icon-circle-plus-outline">新建任务</span>
                     </el-dropdown-item>
                 
             </nav-tpl>
            `,
            data() {
                return {
                    teamName: null,
                    userId: null,
                }
            },
            created() {
                Event.$on('update-team-index-nav', this.update);
                const me = this;

            },
            methods: {
                update(teamName, userId) {
                    logger.debug('teamIndex.route nav update 收到更新事件：', teamName, userId);
                    this.teamName = teamName;
                    this.userId = userId;
                },
                click_new_work() {
                    Event.$emit('show-new-work-dialog',
                        this.$store.getters.getTeamUsers,
                        this.$store.getters.getWorkStatusList);
                }
            }
        },
        context: {
            template: `
<div>
    <el-tabs>
        <el-tab-pane label="任务视图">
            <span slot="label">
            任务视图<span class="badge badge-light">{{works.length}}</span>
            </span>
            <!-- 任务视图 -->
            <el-tabs type="border-card">
                <!--遍历工作状态-->
                <template v-for="status in workStatus">
                    <el-tab-pane>
                        <span slot="label">
                            {{status.status_name}}
                            <span class="badge badge-light">{{eachStatusWorks[status.work_status_id] | getWorksLength}}</span>
                        </span>
                        <card-work-list-tpl @delete-work-success="delete_work_on_vue" 
                        :works="eachStatusWorks[status.work_status_id]"></card-work-list-tpl>
                    </el-tab-pane>
                </template>
            </el-tabs>
        </el-tab-pane>
        <el-tab-pane>
            <span slot="label">
            我创建的任务<span class="badge badge-light">{{createWorks.length}}</span>
            </span>
            <card-work-list-tpl :works="createWorks"></card-work-list-tpl>
        </el-tab-pane>
        <el-tab-pane>
            <span slot="label">
            我执行的任务<span class="badge badge-light">{{execWorks.length}}</span>
            </span>
           <card-work-list-tpl :works="execWorks"></card-work-list-tpl>
        </el-tab-pane>
        <el-tab-pane>
            <span slot="label">
            成员<span class="badge badge-light">{{teamUsers.length}}</span>
            </span>
            <team-user-table-tpl 
            :users="teamUsers" 
            :captain_user_id="teamInfo.captain_user_id"
            :team_id="teamInfo.team_id"
            @out_user_success="delete_team_user_on_vue"
            @join_user_success="add_team_user_on_vue"></team-user-table-tpl>
        </el-tab-pane>
    </el-tabs>
    <drawers-work-tpl></drawers-work-tpl>
    <user-info-edit-dialog-tpl></user-info-edit-dialog-tpl>
    <new-work-dialog-tpl @create_work_success="create_work_on_vue"></new-work-dialog-tpl>
 </div>
        `,
            data: function () {
                return {
                    workStatus: [],// 所有的任务状态种类
                    works: [],
                    loading: 0,
                    userId: null,// 当前用户编号
                    teamId: null,// 当前团队编号
                    teamInfo: {}
                };
            },
            mounted: function () {
                this.userId = this.$route.params.userId;
                this.teamId = this.$route.params.teamId;
                const me = this;
                Event.$on('on_card_click', w => {// 卡片点击事件
                    // 触发打开抽屉事件
                    logger.debug('teamIndex 监听到 on_card_click ', w);
                    Event.$emit('show_work_drawers', w, me.workStatus, me.teamUsers);
                });
                this.getWorkInfo();
            },
            methods: {
                // 获取工作信息
                getWorkInfo() {
                    const me = this;
                    http.getWorks(this.userId, this.teamId)
                        .then(res => {
                            logger.debug('getWorks', res.data);
                            me.works = res.data.data.works;
                        });
                    http.getWorkStatus()
                        .then(res => {
                            const data = res.data;
                            logger.debug('getWorkStatus', data);
                            me.workStatus = data.data.workStatus;
                            me.$store.commit('setWorkStatusList', data.data.workStatus);
                        });
                    http.getTeamInfo(this.teamId)
                        .then(res => {
                            logger.debug('getTeamInfo', res.data);
                            me.teamInfo = res.data.data.teamInfo;
                            me.$store.commit('changeSelectTeam', res.data.data.teamInfo);
                            Event.$emit('update-team-index-nav', res.data.data.teamInfo.team_name, me.userId);
                        });
                },
                delete_work_on_vue(work) {
                    logger.debug('teamIndex delete_work_on_vue 删除某个任务。当前删除任务为为', work);
                    let index = this.works.indexOf(work);
                    logger.debug('teamIndex delete_work_on_vue 删除某个任务。当前删除下标为', index);
                    //删除
                    const del = this.works.splice(index, 1);
                },
                delete_team_user_on_vue(user) {
                    logger.debug('teamIndex delete_team_user_on_vue 踢出某个用户', user);
                    let index = this.teamInfo.joinUserList.indexOf(user);
                    logger.debug('teamIndex delete_team_user_on_vue 踢出某个用户。当前删除下标为', index);
                    //删除
                    const del = this.teamInfo.joinUserList.splice(index, 1);
                },
                add_team_user_on_vue(user){
                    logger.debug('teamIndex add_team_user_on_vue 添加某个用户', user);
                    this.teamInfo.joinUserList.push(user);
                },
                create_work_on_vue(work){
                    this.works.push(work);
                }

            },// 定义计算属性
            computed: {
                allWorks() {
                    let ar = [];
                    ar = ar.concat(this.execWorks).concat(this.createWorks);
                    return ar;
                },
                // 根据任务的状态分组
                eachStatusWorks() {
                    let eachStatus = {};
                    if (this.works === undefined) return {};
                    const works = this.works;
                    for (let j = 0; j < works.length; j++) {
                        let w = works[j];
                        if (!eachStatus[w.work_status_id])
                            eachStatus[w.work_status_id] = [];
                        eachStatus[w.work_status_id].push(w);
                    }
                    logger.debug('eachStatusWorks:', eachStatus);
                    return eachStatus;
                },
                teamUsers() {
                    let a = [];
                    a = a.concat(this.teamInfo.joinUserList);
                    a.push(this.teamInfo.captainUser);
                    return a;
                },
                createWorks() {// 自己创建的任务
                    const me = this;
                    // 这里要使用双等号
                    return me.works.filter(w => {
                        return w.publish_user_id == me.userId;
                    });
                },
                execWorks() {// 自己要执行的任务
                    const me = this;
                    return me.works.filter(w => {
                        return w.exec_user_id == me.userId;
                    });
                }
            },
            filters: {
                // 获取一个任务数组的长度，当某个状态没有任务的时候能够正常显示为0
                getWorksLength(val) {
                    if (val === undefined) return 0;
                    return val.length;
                }
            }
        }
    }

};