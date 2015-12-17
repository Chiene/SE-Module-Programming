package SpriteRenderEngine;

import DynamicObjectModule.DynamicObjectModule;
import DynamicObjectModule.Entities.Entity;

public class SpriteRenderEngine {
	private DynamicObjectModule _dynamicObjectModule;

	public SpriteRenderEngine(DynamicObjectModule dynamicObjectModule) {
		assert (dynamicObjectModule != null);
		_dynamicObjectModule = dynamicObjectModule;
	}

	public void renderSprites() {
		Entity[] entities = _dynamicObjectModule.getAllDynamicObjects();
		
		for (Entity entity : entities) {
			entity.repaint();
		}
	}
}
