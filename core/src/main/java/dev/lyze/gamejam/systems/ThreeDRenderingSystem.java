package dev.lyze.gamejam.systems;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import lombok.var;
import net.mgsx.gltf.loaders.gltf.GLTFLoader;
import net.mgsx.gltf.scene3d.attributes.PBRCubemapAttribute;
import net.mgsx.gltf.scene3d.attributes.PBRTextureAttribute;
import net.mgsx.gltf.scene3d.scene.SceneManager;
import net.mgsx.gltf.scene3d.scene.SceneSkybox;
import net.mgsx.gltf.scene3d.utils.EnvironmentUtil;

public class ThreeDRenderingSystem extends BaseSystem {
    @Wire private PerspectiveCamera camera;
    @Wire private SceneManager sceneManager;

    @Override
    protected void initialize() {
        // setup camera
        camera.near = 0.001f;
        camera.far = 1000f;
        sceneManager.setCamera(camera);

        // setup IBL (image based lighting)
        var environmentCubemap = EnvironmentUtil.createCubemap(new InternalFileHandleResolver(), "environment/environment_", "_0.png", EnvironmentUtil.FACE_NAMES_NEG_POS);
        var diffuseCubemap = EnvironmentUtil.createCubemap(new InternalFileHandleResolver(), "diffuse/diffuse_", "_0.jpg", EnvironmentUtil.FACE_NAMES_NEG_POS);
        var specularCubemap = EnvironmentUtil.createCubemap(new InternalFileHandleResolver(), "specular/specular_", "_", ".jpg", 10, EnvironmentUtil.FACE_NAMES_NEG_POS);
        var brdfLUT = new Texture(Gdx.files.classpath("net/mgsx/gltf/shaders/brdfLUT.png"));

        sceneManager.setAmbientLight(1f);
        sceneManager.environment.set(new PBRTextureAttribute(PBRTextureAttribute.BRDFLUTTexture, brdfLUT));
        sceneManager.environment.set(PBRCubemapAttribute.createSpecularEnv(specularCubemap));
        sceneManager.environment.set(PBRCubemapAttribute.createDiffuseEnv(diffuseCubemap));

        // setup skybox
        var skybox = new SceneSkybox(environmentCubemap);
        sceneManager.setSkyBox(skybox);
    }

    @Override
    protected void processSystem() {
        sceneManager.update(world.delta);
        sceneManager.render();
    }

    public void resize(int width, int height) {
        sceneManager.updateViewport(width, height);
    }
}

