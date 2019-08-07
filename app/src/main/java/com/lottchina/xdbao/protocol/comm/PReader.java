package com.lottchina.xdbao.protocol.comm;

import java.io.IOException;

public abstract class PReader {
	public abstract PMessage read() throws IOException;
}
