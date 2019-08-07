package com.lottchina.xdbao.protocol.comm;


import com.lottchina.xdbao.protocol.util.Args;
import com.lottchina.xdbao.protocol.util.WatchThread;
import com.lottchina.xdbao.utils.log.Logger;
import com.lottchina.xdbao.utils.log.LoggerFactory;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.NoRouteToHostException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class PSocketConnection extends PLayer {
	private static final Logger log = LoggerFactory.getLogger(PSocketConnection.class);
	
	protected static String NOT_INIT;

	protected static String CONNECTING;

	protected static String RECONNECTING;

	protected static String CONNECTED;

	protected static String HEARTBEATING;

	protected static String RECEIVEING;

	protected static String CLOSEING;

	protected static String CLOSED;

	protected static String UNKNOWN_HOST;

	protected static String PORT_ERROR;

	protected static String CONNECT_REFUSE;

	protected static String NO_ROUTE_TO_HOST;

	protected static String RECEIVE_TIMEOUT;

	protected static String CLOSE_BY_PEER;

	protected static String RESET_BY_PEER;

	protected static String CONNECTION_CLOSED;

	protected static String COMMUNICATION_ERROR;

	protected static String CONNECT_ERROR;

	protected static String SEND_ERROR;

	protected static String RECEIVE_ERROR;

	protected static String CLOSE_ERROR;

	private String error;

	protected Date errorTime;

	protected String name;

	protected String host;

	protected int port;

	protected String localHost;

	protected int localPort;

	protected int heartbeatInterval;

	protected PReader in;

	protected PWriter out;
	
	protected boolean isConnected = false;;
	
	protected boolean canSend;
	
	protected static DateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");

	protected int readTimeout;

	protected int reconnectInterval;

	protected Socket socket;

	protected WatchThread heartbeatThread;

	protected WatchThread receiveThread;

	protected int transactionTimeout;

	
	protected boolean isManStop = false;  //是否人工停止
	
	protected boolean sendTimeOut = false;

	public PSocketConnection(Args args) {
		super(null);
		errorTime = new Date();
		port = -1;
		localPort = -1;
		init(args);
	}

	protected PSocketConnection() {
		super(null);
		errorTime = new Date();
		port = -1;
		localPort = -1;
	}

	protected void init(Args args) {
		isManStop = false;
		initResource();
		error = NOT_INIT;
		setAttributes(args);
		if (heartbeatInterval > 0) {
			class HeartbeatThread extends WatchThread {

				public void task() {
					try {
						Thread.sleep(heartbeatInterval);
					} catch (InterruptedException interruptedexception) {
					}
					if (error == null && out != null && !isManStop && canSend){
						try {
							heartbeat();
						} catch (Exception ex) {
							log.info("PSocketConnection  heartbeat error:{}",ex);
							setError(String.valueOf(PSocketConnection.SEND_ERROR)+ String.valueOf(explain(ex)));
						}
					}
				}

				public HeartbeatThread() {
					super(String.valueOf(String.valueOf(name)).concat(
							"-heartbeat"));
					setState(PSocketConnection.HEARTBEATING);
				}
			}

			heartbeatThread = new HeartbeatThread();
			heartbeatThread.start();
		}

		class ReceiveThread extends WatchThread {

			public void task() {
				try {
					if (error == null && in != null) {
						PMessage m = in.read();
						if (m != null){
							sendTimeOut = false;
							onReceive(m);
						}
					} else {
						fireConnectStatusChange(false);
						if (error != PSocketConnection.NOT_INIT){
							try {
								if(reconnectInterval == 0){
									Thread.sleep(100);
								}
								else{
									Thread.sleep(reconnectInterval);
								}
							} catch (InterruptedException interruptedexception) {}
						}
						try{
							if(!isManStop){
								log.info("准备登录... ");
							    connect();
							    if(error==null){
							    	log.info("登录成功");
							    }
							    else{
							    	log.info("登录失败，error = {}",error);
							    }
							    
							}else{
								kill();
							}
						}catch(Exception e){
							e.printStackTrace();
							log.error("登录出现异常，error = {} exception: {}",error,e);
						}
					}
				} catch (IOException ioexception) {
					ioexception.printStackTrace();
					fireConnectStatusChange(false);
					setError(explain(ioexception));
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
					try{
						if(!isManStop){
							log.info("准备登录... ");
						    connect();
						    if(error==null){
						    	log.info("登录成功");
						    }
						    else{
						    	log.info("登录失败，error = {}",error);
						    }
						}
						else{
							kill();
						}
					}catch(Exception e){
						log.error("error = {} reconnect exception: {}",error,e);
					}
				}catch(Exception e){
					e.printStackTrace();
					setError(RECEIVE_ERROR);
					log.info("ReceiveThread thread error exception {}",e);
					if(isManStop){
						kill();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
					}
				}
			}

			public ReceiveThread() {
				super(String.valueOf(String.valueOf(name)).concat("-receive"));
			}
		}
		receiveThread = new ReceiveThread();
		receiveThread.start();
	}

	public void setAttributes(Args args) {
		if (name != null
				&& name.equals(String.valueOf(String.valueOf((new StringBuffer(
						String.valueOf(String.valueOf(String.valueOf(host)))))
						.append(':').append(port)))))
			name = null;
		userId = args.get("userId", "");
		token = args.get("token", "");
		host = args.get("host", null);
		port = args.get("port", -1);
		localHost = args.get("local-host", null);
		localPort = args.get("local-port", -1);
		name = args.get("name", null);
		if (name == null)
			name = String.valueOf(String.valueOf((new StringBuffer(String
					.valueOf(String.valueOf(host)))).append(':').append(port)));
		readTimeout = 1000 * args.get("read-timeout", readTimeout / 1000);
		if (socket != null)
			try {
				socket.setSoTimeout(readTimeout);
			} catch (SocketException socketexception) {
			}
		reconnectInterval = 1000 * args.get("reconnect-interval", -1);
		heartbeatInterval = 1000 * args.get("heartbeat-interval", -1);
		transactionTimeout = 1000 * args.get("transaction-timeout", -1);
		if (error == null && host != null && port != -1 && (!host.equals(host) || port != port || !host.equals(host) || port != port)) {
			setError("需要重新连接");
			receiveThread.interrupt();
		}
	}

	public void send(PMessage message) throws Exception {
		if (error != null)
			throw new PException(String.valueOf(SEND_ERROR)
					+ String.valueOf(getError()));
		try {
			if(out==null || in == null || socket ==null){
				throw new IOException();
			}
			out.write(message);
			fireEvent(new PEvent(8, this, message));
		} catch (Exception ex) {
			fireEvent(new PEvent(16, this, message));
			setError(String.valueOf(SEND_ERROR) + String.valueOf(explain(ex)));
			throw ex;
		}
	}

	public String getName() {
		return name;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public int getReconnectInterval() {
		return reconnectInterval / 1000;
	}

	public String toString() {
		return String.valueOf(String.valueOf((new StringBuffer(
				"PSocketConnection:")).append(this).append(name).append('(').append(host)
				.append(':').append(port).append(')')));
	}

	public int getReadTimeout() {
		return readTimeout / 1000;
	}

	public boolean available() {
		return error == null;
	}

	public String getError() {
		return error;
	}

	public Date getErrorTime() {
		return errorTime;
	}

	public synchronized void close(){
		try {
			setError(CONNECTION_CLOSED);
			if (socket != null) {
				socket.close();
				in = null;
				out = null;
				socket = null;
			}
		} catch (Exception exception) {
			log.error("PSocketConnection [close] : {}  ",exception);
		}
	}
	
	private SocketStatusListener clistner;

	public void fireConnectStatusChange(boolean isConnected){
		if(this.isConnected != isConnected){
			clistner.onConnectStatusChange(isConnected);
		}
		if(!isConnected){
			clearPlayer();
		}
		this.isConnected = isConnected;
	}

	protected synchronized void connect() {
		log.info("准备建立Socket连接1");
		if (error == NOT_INIT)
			error = CONNECTING;
		else if (error == null)
			error = RECONNECTING;
		errorTime = new Date();
		if (socket != null)
			try {
				socket.close();
			} catch (IOException ioexception) {
			}
		try {
			if (port <= 0 || port > 65535) {
				setError(String.valueOf(String.valueOf((new StringBuffer(String
						.valueOf(String.valueOf(PORT_ERROR)))).append("port:")
						.append(port))));
				return;
			}
			if (localPort < -1 || localPort > 65535) {
				setError(String.valueOf(String.valueOf((new StringBuffer(String
						.valueOf(String.valueOf(PORT_ERROR)))).append(
						"local-port:").append(localPort))));
				return;
			}
			if (localHost != null) {
				boolean isConnected = false;
				InetAddress localAddr = InetAddress.getByName(localHost);
				if (localPort == -1) {
					for (int p = (int) (Math.random() * (double) 64500); p < 0xdc758; p += 13)
						try {
							socket = new Socket(host, port, localAddr,
									1025 + p % 64500);
							isConnected = true;
							break;
						} catch (IOException ioexception1) {
						} catch (SecurityException securityexception) {
						}

					if (!isConnected)
						throw new SocketException(
								"Can not find an avaliable local port");
				} else {
					socket = new Socket(host, port, localAddr, localPort);
				}
			} else {
				log.info("准备建立Socket连接  {}  {} ",host,port);
				socket = new Socket(host, port);
				log.info("准备建立Socket连接建立成功");
			}
			socket.setSoTimeout(readTimeout);
			out = getWriter(socket.getOutputStream());
			in = getReader(socket.getInputStream());
			setError(null);
		} catch (IOException ex) {
			log.info("准备建立Socket连接建立异常 {}",ex.getLocalizedMessage());
			ex.printStackTrace();
			setError(String.valueOf(CONNECT_ERROR)+ String.valueOf(explain(ex)));
		}
	}
	
	public void onSendReadTimeOut(){
		sendTimeOut = true;
	}

	protected void setError(String desc) {
		if (error == null && desc == null || desc != null && desc.equals(error))
			return;
		error = desc;
		errorTime = new Date();
		if (desc == null)
			desc = CONNECTED;
	}

	protected abstract PWriter getWriter(OutputStream outputstream);

	protected abstract PReader getReader(InputStream inputstream);

	protected void heartbeat() throws Exception {
	}

	public void initResource() {
		NOT_INIT ="连接尚未初始化。";
		CONNECTING ="正在连接。"; 
		RECONNECTING ="正在重连。";
		CONNECTED ="连接建立成功。"; 
		HEARTBEATING ="正在发送心跳消息。";
		RECEIVEING ="正在接收消息。";
		CLOSEING ="正在关闭连接。"; 
		CLOSED ="连接正常关闭。"; 
		UNKNOWN_HOST ="未知主机。(错误的IP地址或不能根据主机名得到对应的IP地址。)"; 
		PORT_ERROR ="端口号错误。(不是0~65535范围内的整数。)"; 
		CONNECT_REFUSE ="连接被对方拒绝。(连接对方指定端口不在监听状态。)"; 
		NO_ROUTE_TO_HOST ="没有通向该地址的路由。(地址不存在、对方未开机、路由不通或者物理线路故障)";
		RECEIVE_TIMEOUT ="接收消息超时。(请确认设置的read-timeout参数大于服务器心跳发送频率的2~3倍，并确认服务器是否正确发出心跳消息。)"; 
		CLOSE_BY_PEER ="连接被对方关闭。(服务器端主动地关闭了我们之间的连接。请检查服务器配置是否正确，是否有接入方地址限制。)"; 
		RESET_BY_PEER ="连接被对方重置。(服务器端非正常关闭了连接，可能是服务进程非正常退出了。例如：call dump或被管理员kill掉。)"; 
		CONNECTION_CLOSED ="连接已经关闭。";
		COMMUNICATION_ERROR ="通信异常。"; 
		CONNECT_ERROR ="建立连接失败："; 
		SEND_ERROR ="发送消息失败："; 
		RECEIVE_ERROR ="接收消息失败。"; 
		CLOSE_ERROR ="关闭连接出错。";
	}

	protected String explain(Exception ex) {
		String msg = ex.getMessage();
		if (msg == null)
			msg = "";
		if (ex instanceof PException)
			return ex.getMessage();
		if (ex instanceof EOFException)
			return CLOSE_BY_PEER;
		if (msg.indexOf("Connection reset by peer") != -1)
			return RESET_BY_PEER;
		if (msg.indexOf("SocketTimeoutException") != -1)
			return RECEIVE_TIMEOUT;
		if (ex instanceof NoRouteToHostException)
			return NO_ROUTE_TO_HOST;
		if (ex instanceof ConnectException)
			return CONNECT_REFUSE;
		if (ex instanceof UnknownHostException)
			return UNKNOWN_HOST;
		if (msg.indexOf("errno: 128") != -1) {
			return NO_ROUTE_TO_HOST;
		} else {
			return ex.toString();
		}
	}

	public SocketStatusListener getClistner() {
		return clistner;
	}

	public void setClistner(SocketStatusListener clistner) {
		this.clistner = clistner;
	}
	public boolean isCanSend() {
		return canSend;
	}

	public void setCanSend(boolean canSend) {
		this.canSend = canSend;
	}

}
