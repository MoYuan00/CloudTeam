let login = {
    path: '/login',
    name: 'login',
    components: {
        nav:{
          template: `
             <nav-tpl :is_login="false">
             </nav-tpl>
          `
        },
        context: {
            template: `
            <div>
                <el-row type="flex" justify="center">
                    <el-col :xs="17" :sm="12" :md="7" :lg="6">
                        <el-form :model="user" :rules="rules" ref="user">
                            <h1 class="text-center">Login</h1>
                            <el-form-item prop="name" label="用户名：">
                                <el-input @input="user.name = inputFilter(user.name)" 
                                clearable v-model="user.name" placeholder="用户名"></el-input>
                            </el-form-item>
                            <el-form-item prop="password" label="密码：">
                                <el-input @input="user.password = inputFilter(user.password)" clearable 
                                type="password" auto v-model="user.password" placeholder="密码"></el-input>
                            </el-form-item>
                            <el-row type="flex" justify="center">
                                <el-col :span="16" class="text-center">
                                    <el-button @click="click_login" size="medium"  type="primary" style="display: inline-block;width: 80%;">登录
                                    </el-button>
                                </el-col>
                                <el-col :span="8" class="text-center">
                                <router-link to="/register">
                                    <el-button plain size="medium" type="primary" style="display: inline-block;width: 80%;">注册
                                    </el-button>
                                </router-link>
                                </el-col>
                            </el-row>
                        </el-form>
                    </el-col>
                </el-row>
        </div>

            `,
            data: function () {
                const notHasSpace = (rule, value, callback)=>{
                    if(value.toString().trim() === ''){
                        value = value.toString().trim();
                        callback(new Error("不允许为空字符！"));
                    }else if(value.toString().trim().length < 2){
                        callback("长度至少为 2 ！");
                    }else{
                        callback();
                    }
                };
                return {
                    user: {
                        name: '',
                        password: ''
                    },
                    rules:{
                        name:[
                            { required: true, message: '请输入名称', trigger: 'blur' },
                            // { min: 2, message: '长度至少为 2 ！', trigger: 'blur' },
                            {validator: notHasSpace, trigger: 'blur'},
                        ],
                        password:[
                            { required: true, message: '请输入密码', trigger: 'blur' },
                        ]
                    }
                };
            },
            methods: {
                click_login: function () {
                    const me = this;
                    logger.debug('login', '点击了登录');
                    // 开始登录
                    this.$refs.user.validate((valid) => {
                        if (valid) {// 如果通过了验证器，就提交
                            http.login(this.user.name, this.user.password)
                                .then(res => {
                                    const data = res.data;
                                    logger.debug('login then', data);
                                    me.$store.commit('changeLoginUser', data.data.user);
                                    // 跳转到主页面
                                    me.$router.push({
                                        name: 'userIndex',
                                        params: {userId: data.data.user.user_id}
                                    });
                                });
                        } else {
                            console.log('error submit!!');
                            return false;
                        }
                    });

                },
            }
        }
    }
};