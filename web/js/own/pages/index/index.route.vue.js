let index = {
    path: '/',
    components: {
        nav:{
            template: `
                <nav-tpl :is_login="false"></nav-tpl>
            `
        },
        context:{
            template: `
                <div>
                    <h2 class="text-center">
                    协作，让团队实现目标
                    </h2>
                    <div class="text-center">
                            <p style=" max-width: 90%; margin: 20px auto" >
                            一切工作都可以从 Teambition 开始。无论是策划活动、研发软件、制造机器人、建设发电站或者发射卫星，团队成员以更高效的协作方式，为目标不断创造成果。
                            </p>
                    </div>
                      <el-carousel :interval="4000" type="card" height="200px">
                        <el-carousel-item v-for="item in ['范冰青','郑权','张傲然','王飞','林丽华']" :key="item">
                          <h3 class="medium text-center">{{ item }}</h3>
                        </el-carousel-item>
                      </el-carousel>
                </div>
            `
        }
    }
};

