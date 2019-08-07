package com.lottchina.xdbao.protocol.comm;

import java.io.IOException;

public abstract class PWriter {
	
	public abstract void write(PMessage pmessage) throws IOException;
	
}
