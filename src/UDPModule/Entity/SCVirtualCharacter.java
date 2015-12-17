package UDPModule.Entity;

import Stub.DOMStub;

public class SCVirtualCharacter extends SpriteCategory{


	@Override
	public void add(DOMStub dom,String msg) {
		// TODO Auto-generated method stub
		dom.addVirtualCharacter(msg);
	}

	@Override
	public void update(DOMStub dom,String msg) {
		// TODO Auto-generated method stub
		dom.updateVirtualCharacter(msg);
	}

}
