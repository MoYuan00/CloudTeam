Vue.component('team-user-table-tpl',{
   template:`
<div>
    <div v-if="$store.getters.getLoginUserId == captain_user_id">
       <el-button @click="visible = true">添加队员</el-button>
    </div>
    <el-table :data="users">
       
        <el-table-column width="60"  label="角色">
            <template slot-scope="scope">
                <span  >{{captain_user_id === scope.row.user_id ? '队长' : '成员'}}</span> 
            </template>
        </el-table-column>
        <el-table-column prop="name" label="名称">
        </el-table-column>
<!--        <el-table-column  label="加入时间">-->
<!--             <template slot-scope="scope">-->
<!--                <span  >{{getJoinTeam(scope.row)}}</span> -->
<!--            </template>-->
<!--        </el-table-column>-->
        <el-table-column
            fixed="right"
            label="操作"
            width="120" v-if="$store.getters.getLoginUserId == captain_user_id">
            <template slot-scope="scope">
<!--                <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>-->
                <el-button
                  
                 :disabled="captain_user_id === scope.row.user_id"
                 @click="click_out_user_team(scope.row)" type="text" size="small">踢出团队</el-button>
            </template>
        </el-table-column>
    </el-table>
    
<!--    添加队员对话框-->
     <el-dialog destroy-on-close @close="close" :destroy-on-close="false"  center :visible.sync="visible" title="新队员" width="270px">
        <el-form status-icon ref="addUser" :rules="rules" :model="addUser">
            <el-form-item prop="name">
                <el-input clearable 
                @input="addUser.name = inputFilter(addUser.name)"
                placeholder="队员名" v-model="addUser.name" autocomplete="off">
<!--                点击就检验一下表单-->
                    <el-button slot="append" @click="$refs.addUser.validate(e=>{})" icon="el-icon-search"></el-button>
                </el-input>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="click_add_user">添 加</el-button>
        </div>
    </el-dialog>
    
</div>
   `,
    props:['users', 'captain_user_id', 'team_id'],
    data(){
        let nameExist = (rule, value, callback) => {
            if (!value || value === '') {
                callback(new Error('请输入名称'));
            } else {
                http.isCanUseName(value)
                    .then(res=>{
                        const data = res.data;
                        if(data.data.isCanUse)
                            callback(new Error('当前用户名不存在'));
                        else {
                            callback();
                        }
                    });
            }
        };
       return{
           addUser:{},
           visible: false,
           rules: {
               name: [
                   {validator: nameExist, trigger: 'none'},// 设置不自动触发
                   {required: true, message: '请输入名称', trigger: 'blur'}
               ],
           }
       }
    },
    methods:{
       // 点击踢出团队
       click_out_user_team(user){
           const me = this;
            logger.debug('team-user-table-tpl click_out_user_team点击踢出团队', user);
            http.userOutTeam(this.team_id, user.user_id)
                .then(res=>{
                    const data = res.data;
                    logger.debug('userOutTeam响应', data);
                    me.$emit('out_user_success', user);
                });
       },
        click_add_user(){
           const me = this;
            this.$refs.addUser.validate((valid) => {
                if (valid) {// 如果通过了验证器，就提交
                    http.joinTeamByName(me.team_id, me.addUser.name)
                        .then(res=>{
                            const data = res.data;
                            logger.debug('register joinTeamByName', data);
                            if(data.errNo == 0){
                                me.$emit('join_user_success', data.data.user);
                            }
                        });
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        close(){
            logger.debug('关闭！');
            this.$refs.addUser.resetFields();
        }
    }

});