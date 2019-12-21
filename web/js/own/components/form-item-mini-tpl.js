Vue.component("form-item-mini-tpl", {
    template: `
    <el-form-item :prop="prop" size="small">
    <!--此slot 覆盖了el-form-item自带的slot-实现嵌套slot-->
            <slot name="label"></slot>
            <slot name="item"></slot>
     </el-form-item>
    `,
    props:['prop']
});
