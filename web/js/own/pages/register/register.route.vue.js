let register =
    {
        path: '/register',
        components:{
            nav:{
                template: `
                    <nav-tpl ></nav-tpl>
                `
            },
            context: {
                template: `
                 <el-row type="flex" justify="center">
                        <el-col :xs="17" :sm="12" :md="7" :lg="6">
                        <div>
                            <h1 class="text-center">register</h1>
                            <el-form  status-icon :rules="rules" :model="registerFrom" ref="registerFrom">
                                <el-form-item clearable prop="name" label="用户名">
                                    <el-input 
                                    @input="registerFrom.name = inputFilter(registerFrom.name)"
                                    clearable  
                                    maxlength="20" show-word-limit 
                                    v-model="registerFrom.name" placeholder="用户名"></el-input>
                                </el-form-item>
                                <el-form-item prop="password" label="密码">
                                    <el-input 
                                    @input="registerFrom.password = inputFilter(registerFrom.password)"
                                    type="password" show-password v-model="registerFrom.password" placeholder="密码"></el-input>
                                </el-form-item>
                                <el-form-item prop="password2">
                                    <el-input 
                                    @input="registerFrom.password2 = inputFilter(registerFrom.password2)"
                                    type="password" show-password v-model="registerFrom.password2" placeholder="确认密码"></el-input>
                                </el-form-item>
                                <el-form-item  >
                                    <el-row type="flex" justify="center">
                                        <el-col :span="22" class="text-center">
                                            <el-button @click="onClickRegister" type="primary" style="display: inline-block;width: 90%;">确 认
                                            </el-button>
                                        </el-col>
                                    </el-row>
                                </el-form-item>
                            </el-form>
                        </div>
                    </el-col>
                </el-row>
            `,
                data: function () {
                    let nameExist = (rule, value, callback) => {
                        if (!value || value === '') {
                            callback(new Error('请输入名称'));
                        } else {
                            http.isCanUseName(value)
                                .then(res=>{
                                    const data = res.data;
                                    if(data.data.isCanUse)
                                        callback();
                                    else {
                                        callback(new Error('当前用户名不可用！'));
                                    }
                                });
                        }
                    };
                    const pw = (rule, value, callback)=>{
                        if (!value || value === '') {
                            callback(new Error('请输入密码'));
                        } else {
                            callback();
                        }
                    };
                    const pw2 = (rule, value, callback) => {
                        if (value === '') {
                            callback(new Error('请再次输入密码'));
                        } else if (value !== this.registerFrom.password) {
                            callback(new Error('两次输入密码不一致!'));
                        } else {
                            callback();
                        }
                    };
                    return {
                        registerFrom: {},
                        // 定义表单输入规则
                        rules: {
                            name: [
                                {validator: nameExist, trigger: 'blur'},
                                { required: true, message: '请输入名称', trigger: 'blur' },
                                { min: 2, message: '长度至少为 2 ！', trigger: 'blur' }
                            ],
                            password:[
                                { required: true, message: '请输入密码', trigger: 'blur' },
                                { validator: pw,  trigger: 'blur' },
                                { min: 6, max: 20, message: '长度至少为 6 - 20 之间！', trigger: 'blur' }
                            ],
                            password2:[
                                { required: true, message: '请再次输入密码', trigger: 'blur' },
                                { validator: pw2, trigger: 'blur' },
                            ]
                        },
                    }
                },
                methods:{
                    onClickRegister(){
                        const me = this;
                        this.$refs.registerFrom.validate((valid) => {
                            if (valid) {// 如果通过了验证器，就提交
                                http.registerUser(this.registerFrom.name, this.registerFrom.password)
                                    .then(res=>{
                                        const data = res.data;
                                        logger.debug('register onClickRegister注册响应', data);
                                        // 注册成功后跳转到用户的主页面
                                        me.$router.push({name:'userIndex',
                                            params:{userId: data.data.user.user_id}});
                                    });
                            } else {
                                console.log('error submit!!');
                                return false;
                            }
                        });
                    }
                }
            }
        }
    };

