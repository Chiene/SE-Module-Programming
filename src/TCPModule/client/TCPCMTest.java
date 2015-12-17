package net.tcp.client;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TCPCMTest {
	
	private TestServer testServer;
	
	@Before
	public void testbefore(){
		testServer = new TestServer();
		testServer.start();
		System.out.println("testServer啟動...");
	}
	
	@Test
	public void testConnectServer() throws UnknownHostException, IOException{
		boolean result = new TCPCM().connectServer(InetAddress.getLocalHost());
		Assert.assertTrue(result);
		System.out.println("assert~~");
	}
	
	@Test
	public void testInputMove() throws UnknownHostException, IOException, InterruptedException{
		System.out.println("testinputMove...");
		TCPCM tcpcm = new TCPCM();
		tcpcm.connectServer(InetAddress.getLocalHost());
		
		tcpcm.inputMoves(KeyEvent.VK_UP);
		tcpcm.inputMoves(KeyEvent.VK_DOWN);
		tcpcm.inputMoves(KeyEvent.VK_LEFT);
		tcpcm.inputMoves(KeyEvent.VK_RIGHT);
		tcpcm.inputMoves(TCPCM.GET);
		
//		this.wait(100);
	}
	
	@After
	public void testafter() throws IOException{
		testServer.closeServer();
		System.out.println("testServer關閉...");
	}
	
}

class TestServer extends Thread {
	
	private ServerSocket serverSocket;
	
	private ArrayList<Integer> recieve;
	
	public TestServer() {
		// TODO Auto-generated constructor stub
		recieve = new ArrayList<Integer>();
	}
	
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(9090);
			Socket socket = serverSocket.accept();
			InputStream input = socket.getInputStream();
			while(!serverSocket.isClosed()){
				System.out.println("testServer run...");
				
				Integer recv = 0;
				recv = input.read();
					System.out.println(recv);
					recieve.add(recv);
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeServer() throws IOException{
		serverSocket.close();
	}
	
}