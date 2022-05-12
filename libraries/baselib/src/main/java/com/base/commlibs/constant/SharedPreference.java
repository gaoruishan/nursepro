package com.base.commlibs.constant;

import com.base.commlibs.BaseActivity;
import com.base.commlibs.BaseFragment;
import com.base.commlibs.bean.NurseConfig;
import com.raisound.speech.http.response.Scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * SharedPreference
 * 当前登录用户数据存储KEY
 * @author DevLix126
 * created at 2018/8/24 9:11
 */
public class SharedPreference extends NurseConfig {


    public static final String HTTP = "HTTP";
    public static final String WEBIPS = "WEBIPS";
    //    public static final String WEBPATH = "WEBPATH";
    public static final String LOGONLOCTYPE = "LOGONLOCTYPE";
    public static final String WINDOWNAME = "WINDOWNAME";
    public static final String REMEM = "REMEM";
    public static final String REMEM_USERCODE = "REMEM_USERCODE";

    public static final String USERCODE = "USERCODE";
    public static final String USERID = "USERID";
    public static final String USERNAME = "USERNAME";

    public static final String HOSPITALROWID = "HOSPITALROWID";

    public static final String GROUPID = "GROUPID";
    public static final String GROUPDESC = "GROUPDESC";

    public static final String LINKLOC = "LINKLOC";
    public static final String LOCID = "LOCID";
    public static final String LOCDESC = "LOCDESC";

    public static final String WARDID = "WARDID";

    public static final String SCHSTDATETIME = "SCHSTDATETIME";
    public static final String SCHENDATETIME = "SCHENDATETIME";
    public static final String CURDATETIME = "CURDATETIME";

    public static final String LOCSLISTJSON = "LOCSLISTJSON";
    public static final String WINSLISTJSON = "WINSLISTJSON";

    public static final String LIGHT = "LIGHT";
    public static final String SOUND = "SOUND";
    public static final String VIBRATOR = "VIBRATOR";
    public static final String MUlTISCAN = "MUlTISCAN";


    public static final String EXITTIME = "EXITTIME";
    public static final String CHECKTIME = "CHECKTIME";


    //pda系统时间与获取的服务器 时间
    public static final String SYSYTEMTIME = "SYSYTEMTIME";
    public static final String SERVETIME = "SERVETIME";

    public static final String LOCALREQUEST = "LOCALREQUEST";


    public static final String WINDOWS = "WINDOWS";
    public static final String DEFAULTWINDOW = "DEFAULTWINDOW";

    //生命体征列表
    public static final String DISPLAYLIST = "DISPLAYLIST";
    public static final String CUR_MAINSUBLISTBEAN = "curMainSubListBean";

    //临时变量定位报错方法名
    public static String MethodName = "";
    public static String DHC_CALLBACK_JSON = "";

    public static final String BLOODSCANTIMES = "BLOODSCANTIMES";

    public static final String DATA_MAIN_CONFIG = "DATAMAINCONFIG";
    public static final String APP_VERSION_CODE = "APP_VERSION_CODE";
    //皮试双签
    public static final String MSG_SKIN_FLAG = "MSG_SKIN_FLAG";
    //日志
    public static final String LOG_FLAG = "LOG_FLAG";
    //通知
    public static final String MSG_NOTICE_FLAG = "MSG_NOTICE_FLAG";
    //全局View
    public static final String GLOBAL_VIEW_FLAG = "GLOBAL_VIEW_FLAG";
    //输液背景状态
    public static final String ORD_STATE_FLAG = "ORD_STATE_FLAG";
    //采血复核
    public static final String BLOOD_CHECK_FLAG = "BLOOD_CHECK_FLAG";
    //皮试日期差值
    public static final String SKIN_DATE_OFFSET = "SKIN_DATE_OFFSET";
    //医嘱查询床位筛选记住筛选记录
    public static final String ORDERSEARCHE_BEDSELECTED = "ORDERSEARCHE_BEDSELECTED";
    //更新
    public static final String UPDATE_URL = "UPDATE_URL";
    //模块名称
    public static final String MODULE_TITLE = "MODULE_TITLE";
    public static final String MODULE_ID = "MODULE_ID";
    //更新是否http
    public static final String IS_HTTP = "IS_HTTP";
    //是否手动输入
    public static final String IS_HAND_INPUT = "IS_HAND_INPUT";
    //是否Logcat
    public static final String IS_SHOW_LOGCAT = "IS_SHOW_LOGCAT";
    //当前用户工作量
    public static final String IS_SHOW_CUR_USER_WORKLOAD = "IS_SHOW_CUR_USER_WORKLOAD";
    //是否网络状态日志
    public static final String NET_LOG = "NET_LOG";
    //是否开启webSocket
    public static final String WEBSOCKET_FLAG = "WEBSOCKET_FLAG";
    //显示弹框时间 毫秒
    public static final String IS_SHOW_DIALOG_TIME = "IS_SHOW_DIALOG_TIME";
    //扫码界面-提示语
    public static final String SCAN_LABEL_PAT_HAND = "SCAN_LABEL_PAT_HAND";
    public static final String SCAN_LABEL_PAT_HAND_INFO = "SCAN_LABEL_PAT_HAND_INFO";
    //皮试预警
    public static final String WARNING_TIME = "WARNING_TIME";

    //判断是否显示底部菜单栏

    public static List FRAGMENTARY = new ArrayList<HashMap>();
    //存放Fragment
    public static HashMap<String, BaseFragment> FRAGMENTMAP = new HashMap<String, BaseFragment>();
    public static final String MAP_SHOW = "MAP_SHOW";
    //记录上一个fragment，可返回（挽回一次误操作跳转）
    public static BaseActivity LastActivity;
    //记录当前fragment，导航栏不显示
    public static String Fragment_show;

    //记录当前模式
    public static final String SINGLEMODEL = "SINGLEMODEL";

    //语音相关
    public static final String BTN_VOICE_SHOW = "BTN_VOICE_SHOW";//语音功能是否开启
    public static List<Scene> SCENE_LIST = null;
    public static final String VOICE_DATETIME_POINT = "VOICE_DATETIME_POINT";
    public static final String VOICE_PAT_LIST = "VOICE_PAT_LIST";
    public static final String VOICE_VISAL_LIST = "VOICE_VISAL_LIST";
    public static final String VOICE_PATINFO_JSON = "VOICE_PATINFO_JSON";
    public static final String VOICE_IP = "VOICE_IP";
    public static final String VOICE_PORT = "VOICE_PORT";
    public static final String VOICE_GROUPID = "VOICE_GROUPID";
    public static final String VOICE_SCORE = "VOICE_SCORE";

    //CA相关
    public static final String CA_VenderCode = "CA_VenderCode";
    public static final String CA_SIGN_GUID = "CA_SIGN_GUID";
    public static final String CA_LOGIN_certCN = "CA_LOGIN_certCN";
    public static final String CA_LOGIN_certContainer = "CA_LOGIN_certContainer";
    public static final String CA_LOGIN_certDN = "CA_LOGIN_certDN";
    public static final String CA_LOGIN_certNo = "CA_LOGIN_certNo";
    public static final String CA_LOGIN_expireTime = "CA_LOGIN_expireTime";
    public static final String CA_LOGIN_signCert = "CA_LOGIN_signCert";
    public static final String CA_LOGIN_signStatus = "CA_LOGIN_signStatus";
    public static final String CA_LOGIN_signToken = "CA_LOGIN_signToken";
    public static final String CA_LOGIN_userCertCode = "CA_LOGIN_userCertCode";
    public static final String CA_hisUserID = "CA_hisUserID";
    public static final String CA_hisUserName = "CA_hisUserName";
    public static final String CA_LOGIN_PIN_FLAG = "CA_LOGIN_PIN_FLAG";



    public static final String  USERPERMISSION_TYPE = "USERPERMISSION_TYPE";//用户权限，是否患者
    public static final String  USERPERMISSION_PATCONTROL = "USERPERMISSION_PATCONTROL";//用户权限，是否可呼出


    public static final String  NUR_LINK_PHONE_JSON = "NUR_LINK_PHONE_JSON";
    public static final String  NUR_LINK_PDA_ID = "NUR_LINK_PDA_ID";
    public static final String  VOIP_ID = "VOIP_ID";
    public static final String  NUR_LINK_VOICE_IDENTIFY = "NUR_LINK_VOICE_IDENTIFY";
    //一天只更新一次通讯录
    public static final String  NUR_LINK_USERS_LIST = "NUR_LINK_USERS_LIST";
    public static final String  NUR_LINK_VOICE_IFREG = "NUR_LINK_VOICE_IFREG";
    public static final String  NUR_LINK_CALLING_HISTORY = "NUR_LINK_CALLING_HISTORY";
    public static final String  NUR_LINK_USER_TYPE = "NUR_LINK_USER_TYPE";
    //勿扰模式
    public static final String  NUR_LINK_NOBOTHERMODEL = "NUR_LINK_NOBOTHERMODEL";

    //医呼通-通用场景
    public static final String  NUR_LINK_VOCETOVOIP_SCEN = "NUR_LINK_VOCETOVOIP_SCEN";
    public static final String  M_CONTEXT = "M_CONTEXT";
    public static final String  PATLISTJSON = "PATLISTJSON";
    public static final String  VOICE_TO_VOIP_TEXTSHOW = "VOICE_TO_VOIP_TEXTSHOW";
    public static final String  VOICE_TO_VOIP_WAKEUP = "VOICE_TO_VOIP_WAKEUP";//设置打开唤醒

    //值班模式
    public static final String  NUR_LINK_WORKTYPE = "NUR_LINK_WORKTYPE";


    //    public static final String  VOIP_ADDRESS = "http://148.70.76.37:8095/voip-yy-api";//voip地址
    public static final String  VOIP_ADDRESS = "http://linephone.raisound.com:8095/voip-yy-api";//voip地址
    //    public static final String  VOIP_ADDRESS = "http://162.14.74.135:8095/voip-yy-api";//voip地址
    public static final String  HIS_ADDRESS = "140.143.194.177";//his地址
    public static final String  VOICEAPISERVE_IP = "http://111.230.139.145";//语音识别ip
    public static final int  VOICEAPISERVE_PORT = 3392;//语音识别端口
    public static final String  NOTIFY_IP = "114.242.246.246";//推送ip
    public static final int  NOTIFY_PORT = 9001;//推送端口
    public static final String NurLinkApkName = "公网shz";
    public static final int VOICE_GROUPID_VALUE = 5;

    //医呼通-ip设置相关
    public static final String  NUR_LINK_IP_HIS = "NUR_LINK_IP_HIS";
    public static final String  NUR_LINK_IP_VOIP = "NUR_LINK_IP_VOIP";
    public static final String  NUR_LINK_IP_SOUND = "NUR_LINK_IP_SOUND";
    public static final String  NUR_LINK_IP_SOUND_PORT = "NUR_LINK_IP_SOUND_PORT";
    public static final String  NUR_LINK_IP_MSG = "NUR_LINK_IP_MSG";
    //医呼通-HISip设置相关
    public static final String  NUR_LINK_PORT_HIS = "NUR_LINK_PORT_HIS";
    public static final String  NUR_LINK_PATH_HIS = "NUR_LINK_PATH_HIS";


    //    public static final String  HIS_ADDRESS = "10.17.200.154";
    public static final String HIS_PORT = "";

//    public static final String  HIS_ADDRESS = "10.105.3.98";
//    public static final String HIS_PORT = "10180";

    public static final String HIS_PATH = "/imedical/web";
    // 配置是否debug
    public static String Debug="Debug";
}
