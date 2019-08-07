package com.lottchina.xdbao.protocol;

public class Command {

	
	public static final String CMD_STATION_CONNET = "SKT_S01";//校验Toen
	public static final String CMD_STATION_CONNECT_ACTIVE = "SKT_BT01";
	public static final String CMD_STATION_BIND_TERMINAL = "SKT_T01";
	public static final String CMD_STATION_UNBIND_TERMINAL = "SKT_T02";
	public static final String CMD_STATION_TICKET_GRAB_GET = "SKT_TK01";
	public static final String CMD_STATION_TICKET_FINISH = "SKT_TK02";
	public static final String CMD_STATION_BONUS_GET = "SKT_TK03";
	public static final String CMD_STATION_BONUS_DETAIL_GET = "SKT_TK04";
	public static final String CMD_STATION_BONUS_FINISH = "SKT_TK05";
	public static final String CMD_STATION_TICKET_RECOVERY = "SKT_TK06";
	public static final String CMD_STATION_BONUS_TICKET_FINISH = "SKT_TK07";

	public static final String CMD_STATION_REGIST = "S01";
	public static final String CMD_STATION_LOGIN = "S02";
	public static final String CMD_STATION_GET_VERIFYCODE = "U01";
	public static final String CMD_STATION_VERIFYCODE_LOGIN = "U02";
	public static final String CMD_STATION_INFO_UPDATE = "S03";
	public static final String CMD_STATION_GET_DETAIL = "S04";

	public static final String CMD_STATION_ADD_TERMINAL = "ST01";
	public static final String CMD_STATION_EDIT_TERMINAL = "ST02";
	public static final String CMD_STATION_BOX_VERSION_UPDATE = "ST03";
	public static final String CMD_STATION_MESSAGE= "SKTB_M01";
	public static final String CMD_STATION_DATA= "SKT_RP01";
	public static final String CMD_STATION_GET_ACCOUNT_DETAIL_LIST = "SA01";
	public static final String CMD_STATION_CASH_MONEY = "SA02";

	public static final String CMD_STATION_ADD_SPREAD_CHANNEL = "SC01";
	public static final String CMD_STATION_GET_OFFLINE_BONUS_LIST = "STI08";
	public static final String CMD_STATION_GET_TICKET_DATA = "STI06";
	public static final String CMD_STATION_GET_MEMBER_LIST = "SCUS01";
	public static final String CMD_STATION_MEMBER_CHARGE = "SCUS02";
	public static final String CMD_STATION_MEMBER_NAME = "SCUS06";
	public static final String CMD_STATION_TICKET_GET = "SKT_TK11";
	public static final String CMD_STATION_OFFLINE_TICKET_GET = "SKT_TK08";
	public static final String CMD_STATION_EXCEPTION_TICKET_GET = "STI01";
	public static final String CMD_STATION_EXCEPTION_TICKET_TRYAGAIN = "STI03";
	public static final String CMD_STATION_EXCEPTION_TICKET_REFUND = "STI04";
	public static final String CMD_STATION_TICKET_DATA_GET = "SKT_RP02";
	public static final String CMD_STATION_OFFLINE_TICKET_DATA_CONFIRM = "STI09";
	public static final String CMD_STATION_GET_MEMBER_TICKET_DETAIL_LIST = "STI10";

	public static final String CMD_STATION_TICKET_PRINT_GET = "SKT_TP01";
	public static final String CMD_STATION_TICKET_PRINT_FINISH="SKT_TP02";
	public static final String CMD_STATION_TAKE_PRINT_FINISH="SKT_TP03";
	public static final String CMD_STATION_MEMBER_CASH_GET = "SCUS03";
	public static final String CMD_STATION_MEMBER_CASH_SUCCESS = "SCUS04";

	public static final String CMD_STATION_GET_MEMBER_ACCOUNT_DETAIL_LIST = "SCUSA01";


	public static final String CMD_STATION_CHANEL_REMARK_SET = "SC02";
	public static final String CMD_STATION_CHANEL_MEMBER_DATA = "SC03";

	public static final String CMD_STATION_GET_MEMBER_DATA = "SCUS05";

	public static final String CMD_STATION_SEND_MSG= "SKT_MSG01";

	public static final String CMD_STATION_SCAN_ODDE= "STI05";

	public static final String CMD_APP_CHECK_VERSION = "APP01";

	public static final String CMD_STATION_GET_MEMBER_STAT = "SR01";

	public static final String CMD_STATION_SCHEME_UPLOAD= "JST101";
	public static final String CMD_STATION_SCHEME_GET_LIST= "JST102";
	public static final String CMD_STATION_SCHEME_GET_DETAIL= "JST103";
	public static final String CMD_STATION_GET_MEMBER_SCHEME_DETAIL_LIST = "JST013";
	public static final String CMD_STATION_GET_SCHEME_TICKET_DETAIL_LIST = "CUST02";
	public static final String CMD_STATION_SCHEME_GET_JOINT_LIST= "JST301";
	public static final String CMD_APP_CHECK_SYSTIME= "SYS01";

	public static final String CMD_STATION_TICKET_PRINT_STOP_NOTIFY = "JST038";

	public static final String CMD_STATION_UPLOAD_REPORT = "SKT_TR01";
	public static final String CMD_STATION_UPLOAD_OPEN_NUMBER = "SKT_TM01";

	public static final String CMD_DELETE_CHANNEL= "JST043";
	public static final String CMD_UPDATE_CHANNEL= "JST044";



	public static final String CMD_GETTERM_BY_TERMID = "TM05";

	public static final String CMD_STATION_TICKET_PIC_UPLOAD = "STI11";

	public static final String CMD_STATION_GET_QRCODE= "JST501";
	//订单出票之 获取方案列表Scheme list
	public static final String CMD_STATION_TICKET_SCHEME_LIST = "SKT_TK12";

	//下一步
	public static final String CMD_NEXT_STEP = "US02";
	//终端登录接口
	public static final String CMD_TERMINAL_LOGIN = "ST04";
	//查看当前终端锁定票信息。
	public static final String CMD_LOCKED_TICKETS = "SKT_RP03";
	//获取待上传照片票的列表。
	public static final String CMD_PHOTO_TICKETS = "SKT_TK14";
//获取待上传照片票的列表。
	public static final String CMD_PHOTO_UPLOAD = "SKT_TK15";
	//获取收款接单的列表
	public static final String CMD_RECEIPT_ORDER = "SSCH01";
	//获取账户余额
	public static final String CMD_ACCOUNT_BALANCE = "JCT06";
	// 结算 Settlement
	public static final String CMD_SETTLEMENT = "JST030";
	// 删除无效方案
	public static final String CMD_DELETE_PROGRAM = "JST040";
	// 获取整个方案的票方案
	public static final String CMD_LOAD_ALL_PROGRAM = "SKT_TK10";
	// 方案返奖
	public static final String CMD_AWARD = "SSCH05";
	// 方案退款
	public static final String CMD_REFUND = "SSCH04";
	// 撤单
	public static final String CMD_WITHDRAWAL = "SSCH03";


}

