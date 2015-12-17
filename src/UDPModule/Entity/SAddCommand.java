package UDPModule.Entity;

import Stub.DOMStub;
import UDPModule.Factory.SpriteCategoryFactory;

public class SAddCommand extends SCommand{

	@Override
	public void handle(DOMStub dom,String spriteType,String msg) {
		// TODO Auto-generated method stub
		SpriteCategory spriteCategory = SpriteCategoryFactory.getSpriteCategory(spriteType);
		spriteCategory.add(dom,msg);
	}

}
