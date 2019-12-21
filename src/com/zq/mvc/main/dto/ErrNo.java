package com.zq.mvc.main.dto;

/**
 * 定义所有的错误码，以及代表的错误信息
 */
public class ErrNo {
    public static final ErrBean noErr = new ErrBean(0,
            null);

    public static final ErrBean requiredParamHasNull = new ErrBean(-1,
            "必须的数据对象有空值！参数名错误和必须的值为null都将会导致此错误。");

    public static final ErrBean noLoginUser = new ErrBean(-2,
            "登录超时！请重新登录！");

    public static final ErrBean loginFail = new ErrBean(2,
            "登录失败！登录名不存在或者密码错误！");


    public static final ErrBean updateUserFail = new ErrBean(11,
            "更新用户信息失败，可能是出现了null数据或者格式不正确！");

    public static final ErrBean appendUserFail = new ErrBean(12,
            "添加用户失败了，可能是出现了null数据或者格式不正确");

    public static final ErrBean deleteUserFail = new ErrBean(13,
            "删除用户失败了，可能是用户已经不存在。！");

    public static final ErrBean findUserFail = new ErrBean(14,
            "查找用户失败");


    public static final ErrBean updateTeamFail = new ErrBean(21,
            "更新团队失败了，可能是出现了null数据或者格式不正确！");

    public static final ErrBean appendTeamFail = new ErrBean(22,
            "添加团队失败！");

    public static final ErrBean deleteTeamFail = new ErrBean(23,
            "删除团队失败！");


    public static final ErrBean updateTeamFileFail = new ErrBean(31,
            "更新团队文件失败！");

    public static final ErrBean appendTeamFileFail = new ErrBean(32,
            "添加团队文件失败！");

    public static final ErrBean deleteTeamFileFail = new ErrBean(33,
            "删除团队文件失败！");


    public static final ErrBean updateWorkMsgFail = new ErrBean(31,
            "更新消息信息失败！");

    public static final ErrBean appendWorkMsgFail = new ErrBean(42,
            "添加任务消息失败！");

    public static final ErrBean deleteWorkMsgFail = new ErrBean(43,
            "删除任务消息失败！");


    public static final ErrBean updateWorkFail = new ErrBean(51,
            "更新任务失败！");

    public static final ErrBean appendWorkFail = new ErrBean(52,
            "添加任务失败！");

    public static final ErrBean deleteWorkFail = new ErrBean(53,
            "删除任务失败！");

    public static final ErrBean deleteJoinTeamFail = new ErrBean(63,
            "移除用户失败！");

    public static final ErrBean appendJoinTeamFail = new ErrBean(62,
            "加入团队失败！");


    /**
     * 错误码，以及错误信息类
     */
    public static class ErrBean{
        /**
         * 错误码
         */
        private int errNo;
        /**
         * 错误信息
         */
        private String errMessage;
        /**
         * 额外的错误信息
         */
        private StringBuffer appendMsg;
        private ErrBean(int errNo, String errMessage) {
            this.errNo = errNo;
            this.errMessage = errMessage;
        }
        public int getErrNo() {
            return errNo;
        }
        public String getErrMessage() {
            if (appendMsg == null) return errMessage;
            if(errMessage == null) return appendMsg.toString();
            return errMessage + appendMsg;
        }
        /**
         * 添加额外的错误信息
         * @param msg
         * @return
         */
        public ErrBean appendMessage(Object msg){
            if(msg == null) return this;
            if(appendMsg == null) appendMsg = new StringBuffer();
            appendMsg.append(msg);
            return this;
        }
    }
}
