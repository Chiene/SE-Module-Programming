package UDPModule;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import Stub.CDCStub;
import Stub.TCPSMStub;
import UDPModule.Tool.StreamParser;
import UDPModule.Tool.Validation;

public class UDPBC implements IUDPBC {

	private int port = 27016;;
	private long updateSecond = 50;

	private TCPSMStub _tcpsm;
	private CDCStub _cdc;

	private Timer _broadCaseTimer;

	private DatagramSocket _socket;

	public UDPBC(TCPSMStub tcpsm, CDCStub cdc) {
		_tcpsm = tcpsm;
		_cdc = cdc;
		_broadCaseTimer = new Timer();

	}

	@Override
	public void startUDPBroadCast() {
		// TODO Auto-generated method stub
		try {
			_socket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			_socket.close();
			e.printStackTrace();
		}
		_broadCaseTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					broadCastToClient();
				} catch (IOException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}, 0, updateSecond);

	}

	@Override
	public void endUDPBroadCast() {
		// TODO Auto-generated method stub
		if (_broadCaseTimer != null) {
			_socket.close();
			_broadCaseTimer.cancel();
		}
	}

	private void broadCastToClient() throws IOException, UnknownHostException {
		Vector<String> IPTable = _tcpsm.getClientIPTable();
		Vector<String> data = _cdc.getUpdateInfo();

		for (String message : data) {
			if(!Validation.validateUpdateMsg(message)) continue;
			byte buffer[] = message.getBytes();
			for (String ip : IPTable) {
				_socket.send(new DatagramPacket(buffer, buffer.length, InetAddress.getByName(ip), port));
			}
		}

	}

}
