package com.lottchina.xdbao.protocol.message;

public class SendMessageMessageBody extends MessageBody{

	private int customer_id;
	private String content;

	public SendMessageMessageBody(int memberId, String content) {
		super();
		this.customer_id = memberId;
		this.content = content;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "SendMessageMessageBody{" +
				"memberId=" + customer_id +
				", content='" + content + '\'' +
				'}';
	}
}
