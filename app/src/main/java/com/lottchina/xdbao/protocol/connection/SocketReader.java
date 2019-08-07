package com.lottchina.xdbao.protocol.connection;


import com.lottchina.baselib.utils.GsonUtil;
import com.lottchina.xdbao.protocol.Command;
import com.lottchina.xdbao.protocol.comm.PMessage;
import com.lottchina.xdbao.protocol.comm.PReader;
import com.lottchina.xdbao.protocol.message.ActiveRepMessage;
import com.lottchina.xdbao.protocol.message.BindTerminalRepMessage;
import com.lottchina.xdbao.protocol.message.ConnectRepMessage;
import com.lottchina.xdbao.protocol.message.MessageHead;
import com.lottchina.xdbao.utils.log.Logger;
import com.lottchina.xdbao.utils.log.LoggerFactory;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SocketReader extends PReader {
	private static final Logger log = LoggerFactory.getLogger(SocketReader.class);
	
	protected DataInputStream in;

	public SocketReader(InputStream is) {
		in = new DataInputStream(is);
	}

	public synchronized PMessage read() throws IOException {
		int total_Length = in.readInt();
		int head_lenth = in.readInt();
		
		byte headBytes[] = new byte[head_lenth];
		byte bodyBytes[] = new byte[total_Length-head_lenth-4];
		in.readFully(headBytes);
		in.readFully(bodyBytes);
		
		String headMsg = new String(headBytes,"UTF-8");
		String bodyMsg = new String(bodyBytes,"UTF-8");

		
//		MessageHead obj = JSON.parseObject(headMsg,MessageHead.class);
		MessageHead obj = GsonUtil.GsonToBean(headMsg,MessageHead.class);

		String cmd = obj.getCmd();
		if(!Command.CMD_STATION_CONNECT_ACTIVE.equals(cmd)){
			log.info("接收到数据：{},{}",headMsg,bodyMsg);
		}
		if(Command.CMD_STATION_CONNET.equals(cmd)){
			return new ConnectRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_CONNECT_ACTIVE.equals(cmd)){
			return new ActiveRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_BIND_TERMINAL.equals(cmd)){
			return new BindTerminalRepMessage(obj,bodyMsg);
		}
		/*else if(Command.CMD_STATION_UNBIND_TERMINAL.equals(cmd)){
			return new UnBindTerminalRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_TICKET_GET.equals(cmd)){
			return new TicketGetRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_OFFLINE_TICKET_GET.equals(cmd)){
			return new OfflineTicketGetRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_TICKET_RECOVERY.equals(cmd)){
			return new TicketRecoveryRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_BONUS_TICKET_FINISH.equals(cmd)){
			return new BonusTicketPrintFinishRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_BONUS_GET.equals(cmd)){
			return new BonusGetRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_TICKET_DATA_GET.equals(cmd)){
			return new TicketDataGetRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_BONUS_DETAIL_GET.equals(cmd)){
			return new BonusDetailGetRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_BONUS_FINISH.equals(cmd)){
			return new BonusFinishRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_TICKET_FINISH.equals(cmd)){
			return new TicketFinishRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_MESSAGE.equals(cmd)){
			return new PushMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_DATA.equals(cmd)){
			return new GetDataRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_TICKET_PRINT_GET.equals(cmd)){
			return new TicketPrintGetRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_TICKET_PRINT_FINISH.equals(cmd)){
			return new PrintTicketFinishRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_TAKE_PRINT_FINISH.equals(cmd)){
			return new TakeTicketFinishRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_UPLOAD_REPORT.equals(cmd)){
			return new ReportUploadRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_SEND_MSG.equals(cmd)){
			return new SendMessageRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_STATION_UPLOAD_OPEN_NUMBER.equals(cmd)){
			return new TermOpenNumberUploadRepMessage(obj,bodyMsg);
		}else if(Command.CMD_STATION_TICKET_SCHEME_LIST.equals(cmd)){
			return new TicketSchemeGetRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_NEXT_STEP.equals(cmd)){
			return new NextStepRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_TERMINAL_LOGIN.equals(cmd)){
			return new LoginTerminalMessage(obj,bodyMsg);
		}else if(Command.CMD_LOCKED_TICKETS.equals(cmd)){
			return new LocketTicketRepMessage(obj,bodyMsg);
		}else if(Command.CMD_PHOTO_TICKETS.equals(cmd)){
			return new TicketsPhotoRepMessage(obj,bodyMsg);
		}
		else if(Command.CMD_LOAD_ALL_PROGRAM.equals(cmd)){
			return new TicketGetRepMessage(obj,bodyMsg);
		}*/
		return null;
	}

}