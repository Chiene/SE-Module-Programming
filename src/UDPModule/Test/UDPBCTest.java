package UDPModule.Test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Test;
import Mock.UDPUSMock;
import Stub.CDCStub;
import Stub.TCPSMStub;
import UDPModule.UDPBC;
import UDPModule.Enum.*;

public class UDPBCTest {
	private CDCStub _cdc;
	private TCPSMStub _tcpsm;
	private UDPBC udpBroadCast;
	private UDPUSMock udpusMock;

	public UDPBCTest() {
		// TODO Auto-generated constructor stub
		_cdc = new CDCStub();
		_tcpsm = new TCPSMStub();
		udpBroadCast = new UDPBC(_tcpsm, _cdc);
		udpusMock = new UDPUSMock();
	}
	

	@After
	public void setDown() {
		_cdc.clearData();
		_tcpsm.clearIpTable();
		udpBroadCast.endUDPBroadCast();
		udpusMock.stopUDPServer();
	}

	@Test
	public void testBroadCastOneMessage() {
		_tcpsm.insertIp("127.0.0.1");
		String data = ServerCommandType.ADD.toString() + " " + SpriteType.ITEM.toString() + " " + "user1 1 1";
		_cdc.insertData(data);
		udpBroadCast.startUDPBroadCast();
		udpusMock.receiveData();
		while(udpusMock.getResult().isEmpty()){}
		assertTrue(data.equals(udpusMock.getResult()));
	}

}