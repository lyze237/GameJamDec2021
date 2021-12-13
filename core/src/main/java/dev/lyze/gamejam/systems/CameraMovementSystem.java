package dev.lyze.gamejam.systems;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;

public class CameraMovementSystem extends BaseSystem {
    private FirstPersonCameraController cameraController;

    @Wire private PerspectiveCamera camera;

    @Override
    protected void initialize() {
        cameraController = new FirstPersonCameraController(camera);
        cameraController.setVelocity(50);
        Gdx.input.setInputProcessor(cameraController);
    }

    @Override
    protected void processSystem() {
        cameraController.update(world.delta);
    }
}
