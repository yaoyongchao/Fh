package com.lottchina.xdbao.protocol.message;

public class SuccessRepMessageBody extends MessageBody{

	 private Error err;

	 public Error getErr() {
		 return err;
	 }
	 public void setErr(Error err) {
		 this.err = err;
	 }
}
