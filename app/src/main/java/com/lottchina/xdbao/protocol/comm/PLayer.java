package com.lottchina.xdbao.protocol.comm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public abstract class PLayer {
//	private static final Logger log = LoggerFactory.getLogger(PLayer.class);
	protected PLayer parent;

	private HashMap<String,PLayer> children;

	private List<PEventListener> listeners;
	
	protected String uuid;
	
	protected String userId;

	protected String token;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	protected PLayer(PLayer theParent) {
		if (theParent != null) {
			if (theParent.children == null) {
				theParent.children = new HashMap<String, PLayer>();
			}
			this.uuid = UUID.randomUUID().toString();
			theParent.children.put(uuid, this);
			parent = theParent;
		}
	}
	
	public abstract void send(PMessage pmessage) throws Exception;

	public void onReceive(PMessage message) {
		String childId = getChildId(message);
		if (childId == null) {
			PLayer child = createChild();
			child.onReceive(message);
			fireEvent(new PEvent(2, this, child));
		} else {
			PLayer child = (PLayer) children.get(childId);
			if (child == null)
				fireEvent(new PEvent(64, this, message));
			else
				child.onReceive(message);
		}
	}

	public PLayer getParent() {
		return parent;
	}
	
	public void clearPlayer(){
		if(children==null){
			return;
		}
		children.clear();
	}
	
	public PLayer getPlayer(String uuid){
		return (PLayer)children.get(uuid);
	}

	public int getChildNumber() {
		if (children == null)
			return 0;
		else
			return children.size();
	}

	protected PLayer createChild() {
		throw new UnsupportedOperationException("Not implement");
	}

	protected String getChildId(PMessage message) {
		throw new UnsupportedOperationException("Not implement");
	}
	

	public void close() {
		if (parent == null) {
			throw new UnsupportedOperationException("Not implement");
		} else {
			parent.children.remove(uuid);
			return;
		}
	}

	public void addEventListener(PEventListener l) {
		if (listeners == null)
			listeners = new ArrayList<PEventListener>();
		listeners.add(l);
	}

	public void removeEventListener(PEventListener l) {
		listeners.remove(l);
	}


	protected void fireEvent(PEvent e) {
		if (listeners == null){
			return;
		}
		try {
			for (PEventListener listener : listeners) {
				listener.handle(e);
			}
		}
		catch(Exception ex){
//			log.info("处理接收到的消息异常 "+ ex.getLocalizedMessage());
		}
	}
}
