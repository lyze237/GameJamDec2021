package dev.lyze.gamejam;

import com.artemis.World;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import dev.lyze.gamejam.components.DecalComponent;
import dev.lyze.gamejam.components.MapComponent;
import dev.lyze.gamejam.systems.CameraMovementSystem;
import dev.lyze.gamejam.systems.DecalsRenderingSystem;
import dev.lyze.gamejam.systems.MapSetupSystem;
import dev.lyze.gamejam.systems.ThreeDRenderingSystem;
import lombok.var;
import net.mgsx.gltf.scene3d.scene.SceneManager;

public class GameScreen extends ScreenAdapter {
	private final World world;

	public GameScreen() {
		var setup = new WorldConfigurationBuilder()
				.with(new CameraMovementSystem())
				.with(new MapSetupSystem())
				.with(new DecalsRenderingSystem())
				.with(new ThreeDRenderingSystem())
				.build();

		setup.register(new SceneManager());
		setup.register(new PerspectiveCamera(80f, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

		world = new World(setup);

		world.edit(world.create())
			.add(new MapComponent("maps/DevMap.tmx"))
			.add(new DecalComponent());
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