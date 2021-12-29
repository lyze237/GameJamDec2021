package dev.lyze.gamejam;

import com.artemis.World;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import dev.lyze.gamejam.components.*;
import dev.lyze.gamejam.managers.Assets;
import dev.lyze.gamejam.systems.*;
import lombok.var;
import net.mgsx.gltf.scene3d.scene.SceneManager;

public class GameScreen extends ScreenAdapter {
	private final World world;

	public GameScreen() {
		var setup = new WorldConfigurationBuilder()
				.with(new Assets())
				.with(new CameraMovementSystem())
				.with(new ThreeDLoaderSystem())
				.with(new MapLoaderSystem())
				.with(new DecalsRenderingSystem())
				.with(new ThreeDRenderingSystem())
				.build();

		setup.register(new SceneManager());
		setup.register(new PerspectiveCamera(80f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

		world = new World(setup);

		world.edit(world.create())
				.add(new MapReferenceComponent(Assets.File.MAP_DevMap))
				.add(new MapToDecalConverter())
				.add(new DecalComponent());

		world.edit(world.create())
				.add(new ThreeDReferenceComponent(Assets.File.MODEL_TestTower))
				.add(new ThreeDComponent());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		world.setDelta(delta);
		world.process();
	}

	@Override
	public void resize(int width, int height) {
		world.getSystem(ThreeDRenderingSystem.class).resize(width, height);
	}
}