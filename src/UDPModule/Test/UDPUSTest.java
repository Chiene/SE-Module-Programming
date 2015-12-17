package UDPModule.Test;

import static org.junit.Assert.*;

import java.util.Timer;
import java.util.TimerTask;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import UDPModule.Enum.*;
import Mock.DOMMock;
import Stub.UDPBCStub;
import UDPModule.UDPUS;

public class UDPUSTest {
	private UDPUS _udpus;
	private UDPBCStub _udpBCStub;
	private DOMMock _domMock;

	@Before
	public void setup() {
		_domMock = new DOMMock();
		_udpus = new UDPUS(_domMock);
		_udpBCStub = new UDPBCStub();
	}

	@After
	public void setdown() {
		_udpBCStub.endUDPBroadCast();
		_udpus.stopUDPServer();
		_domMock.initResult();
	}

	@Test
	public void testUDPUSToAddItem() {
		_udpus.initUDPserver();
		_udpBCStub.startUDPBroadCast();
		String msg = "user1 1 1";
		String data = ServerCommandType.ADD.toString() + " " + SpriteType.ITEM.toString() + " " + msg;
		String expected = "addItem " + msg;
		_udpBCStub.send(data, "127.0.0.1");
		while (_domMock.getResult().isEmpty()) {
		}
		assertTrue(expected.equals(_domMock.getResult()));
	}

	@Test
	public void testUDPUSToUpdateItem() {
		_udpus.initUDPserver();
		_udpBCStub.startUDPBroadCast();
		String msg = "user2 2 2";
		String data = ServerCommandType.UPDATE.toString() + " " + SpriteType.ITEM.toString() + " " + msg;
		String expected = "updateItem " + msg;
		_udpBCStub.send(data, "127.0.0.1");
		while (_domMock.getResult().isEmpty()) {
		}
		assertTrue(expected.equals(_domMock.getResult()));
	}

	@Test
	public void testUDPUSToAddVirtualCharacter() {
		_udpus.initUDPserver();
		_udpBCStub.startUDPBroadCast();
		String msg = "user2 2 2";
		String data = ServerCommandType.ADD.toString() + " " + SpriteType.VIRTUALCHARACTER.toString() + " " + msg;
		String expected = "addVirtualCharacter " + msg;
		_udpBCStub.send(data, "127.0.0.1");
		while (_domMock.getResult().isEmpty()) {
		}
		assertTrue(expected.equals(_domMock.getResult()));
	}

	@Test
	public void testUDPUSToUpdateVirtualCharacter() {
		_udpus.initUDPserver();
		_udpBCStub.startUDPBroadCast();
		String msg = "user1 1 1";
		String data = ServerCommandType.UPDATE.toString() + " " + SpriteType.VIRTUALCHARACTER.toString() + " " + msg;
		String expected = "updateVirtualCharacter " + msg;
		_udpBCStub.send(data, "127.0.0.1");
		while (_domMock.getResult().isEmpty()) {
		}
		assertTrue(expected.equals(_domMock.getResult()));
	}

	@Test
	public void testGetErrorMessage() {
		_udpus.initUDPserver();
		_udpBCStub.startUDPBroadCast();
		String msg = "user1 1 1";
		String data = " " + " " + SpriteType.VIRTUALCHARACTER.toString() + " " + msg;
		_udpBCStub.send(data, "127.0.0.1");
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				assertTrue(_domMock.getResult().isEmpty());
				_domMock.addVirtualCharacter("Error");
			}
		}, 1000);
		while (_domMock.getResult().isEmpty()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		assertTrue(_domMock.getResult().equals("addVirtualCharacter Error"));
	}
}
