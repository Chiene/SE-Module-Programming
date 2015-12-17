package UDPModule.Tool;

import java.util.Iterator;
import java.util.Vector;

import UDPModule.Enum.ServerCommandType;
import UDPModule.Enum.SpriteType;

public class Validation {
	static String actionMode[] = {
			ServerCommandType.ADD.toString(),
			ServerCommandType.UPDATE.toString()
			};
	static String spriteCType[] = {
			SpriteType.ITEM.toString(),
			SpriteType.VIRTUALCHARACTER.toString()
			};
	
	public static boolean validateUpdateMsg(String msg)
	{
		Vector<String> actionVector = new Vector<>();
		Vector<String> spriteVector = new Vector<>();
		for (String mode : actionMode) {
			actionVector.add(mode);
		}
		for (String sprite : spriteCType) {
			spriteVector.add(sprite);
		}
		
		String msgToken[] = StreamParser.getMsgToken(msg);
		if(!actionVector.contains(msgToken[0])) return false;
		if(!spriteVector.contains(msgToken[1])) return false;	
		return true;
	}
}
