package dev.lyze.gamejam.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import dev.lyze.gamejam.components.ThreeDComponent;
import dev.lyze.gamejam.components.ThreeDReferenceComponent;
import dev.lyze.gamejam.managers.Assets;
import lombok.var;
import net.mgsx.gltf.scene3d.scene.Scene;
import net.mgsx.gltf.scene3d.scene.SceneManager;

@All({ThreeDReferenceComponent.class, ThreeDComponent.class})
public class ThreeDLoaderSystem extends IteratingSystem {
    private ComponentMapper<ThreeDReferenceComponent> referenceMapper;
    private ComponentMapper<ThreeDComponent> modelMapper;

    @Wire private Assets assets;
    @Wire private SceneManager sceneManager;

    @Override
    protected void process(int entityId) {
        var reference = referenceMapper.get(entityId);
        var model = modelMapper.get(entityId);

        model.setScene(new Scene(assets.getSceneAsset(reference.getFile()).scene));

        model.getScene().modelInstance.transform.scale(16, 16, 16);
        model.getScene().modelInstance.calculateTransforms();

        sceneManager.addScene(model.getScene());
    }
}
