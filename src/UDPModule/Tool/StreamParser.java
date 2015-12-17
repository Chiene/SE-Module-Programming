package UDPModule.Tool;

public class StreamParser {
	public static String[] getMsgToken(String msg) {
		String updateInfo = msg;
		String msgToken[] = new String[3];
		for (int i = 0; i < 2; i++) {
			int subIndex = updateInfo.indexOf(" ");
			if(subIndex == -1) break;
			msgToken[i] = updateInfo.substring(0, subIndex);
			updateInfo = updateInfo.substring(subIndex + 1);
		}
		msgToken[2] = updateInfo;
		return msgToken;
	}
}
