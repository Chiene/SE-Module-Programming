package net.tcp.client;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPCM {

	private static Socket socket;

	public final static int GET = 66;

	public TCPCM() {
		// TODO Auto-generated constructor stub
		socket = new Socket();
	}

	public boolean connectServer(InetAddress serverip) throws UnknownHostException, IOException {
		if (!socket.isConnected()) {
			socket = new Socket(InetAddress.getLocalHost(), 9090);
		}
		return socket.isConnected();
	}

	public void inputMoves(int MoveCode) throws IOException {
		assert (MoveCode != KeyEvent.VK_UP || MoveCode != KeyEvent.VK_DOWN || MoveCode != KeyEvent.VK_LEFT
				|| MoveCode != KeyEvent.VK_RIGHT || MoveCode != KeyEvent.VK_UP || MoveCode != TCPCM.GET
				|| !socket.isConnected());

		OutputStream output = socket.getOutputStream();
		output.write(MoveCode);
		output.flush();
	}

}
