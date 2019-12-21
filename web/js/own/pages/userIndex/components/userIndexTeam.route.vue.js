// const userIndexTeam = {
//     path: '/userIndex/:userId',
//     name: 'userIndex',
//     components:{
//         nav:{
//             template: `
//              <nav-tpl :is_login="true">
//              </nav-tpl>
//             `
//         },
//         context: {
//             template: `
//             <el-tabs :tab-position="el_tabs.tabHeaderPosition" :type="el_tabs.tabType">
//                 <el-tab-pane>
//                     <span slot="label">
//                     全部团队<span class="badge badge-light">{{allTeams.length}}</span>
//                     </span>
//                     <team-cards-tpl   :teams="allTeams"></team-cards-tpl>
//                 </el-tab-pane>
//                 <el-tab-pane>
//                     <span slot="label">
//                     创建的团队<span class="badge badge-light">{{createTeams.length}}</span>
//                     </span>
//                     <team-cards-tpl   :teams="createTeams"></team-cards-tpl>
//                 </el-tab-pane>
//                 <el-tab-pane>
//                     <span slot="label">
//                     加入的团队<span class="badge badge-light">{{joinTeams.length}}</span>
//                     </span>
//                     <team-cards-tpl  :teams="joinTeams"></team-cards-tpl>
//                 </el-tab-pane>
//             </el-tabs>
//             `,
//             data: function () {
//                 return {
//                     el_tabs: {
//                         tabHeaderPosition: 'left',
//                         tabType: 'card'
//                     },
//                     allTeams: [],
//                     createTeams: [],
//                     joinTeams: [],
//                     userId: null
//                 };
//             },
//             mounted: function () {
//                 const me = this;
//                 const userId = this.$route.params.userId;// 当用户主页挂载完成后，要准备到数据库获取数据
//                 this.userId = userId;
//                 logger.debug('userIndex mounted 当前userId:', userId);
//                 Event.$on('team_card_clicked', this.onClickTeam);// 监听团队卡片点击事件
//                 Event.$on("click_user_info", function () {// 点击账号设置按钮时触发
//                     logger.debug('userIndex on click_user_info接收到点击账号设置事件');
//                     me.$router.push({name:'userInfoManager', params:{userInfo: me.$store.loginUser}});
//                 });
//                 // 获取数据
//                 this.getUserTeamInfo(userId);
//             }
//             ,
//             methods: {
//                 getUserTeamInfo: function (userId) {
//                     let me = this;
//                     http.getCreateTeamsByUserId(userId)
//                         .then(msg=>{
//                             logger.debug('getCreateTeamsByUserId', msg.data.data);
//                             me.createTeams = msg.data.data.createTeams;
//                             me.allTeams = me.createTeams.concat(me.joinTeams);
//                         });
//                     http.getJoinTeamsByUserId(userId)
//                         .then(msg=>{
//                             logger.debug('getJoinTeamsByUserId', msg.data.data);
//                             me.joinTeams = msg.data.data.joinTeams;
//                             me.allTeams = me.createTeams.concat(me.joinTeams);
//                         });
//                 } ,
//                 onClickTeam: function (team) {
//                     logger.debug('userIndex >> onClickTeam 点击了一个团队卡片', team);
//                     // Event.$emit('on-click-team-card', this.userId, team);
//                     this.$router.push({name: 'teamIndex',
//                         params: {userId:this.userId, teamId: team.team_id}});
//                 }
//             }
//         }
//     }
// };
