package UDPModule.Factory;

import UDPModule.Enum.ServerCommandType;
import UDPModule.Entity.SCommand;
import UDPModule.Entity.SAddCommand;
import UDPModule.Entity.SUpdateCommand;

public class ServerActionFactory {

	public static SCommand getServerActionMode(String mode) {
		SCommand actionMode = null;
		switch (ServerCommandType.valueOf(mode)) {
		case ADD:
			actionMode = new SAddCommand();
			break;
		case UPDATE:
			actionMode = new SUpdateCommand();
			break;
		default:
			break;
		}
		return actionMode;
	}
}
