package dev.lyze.gamejam.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import dev.lyze.gamejam.components.DecalComponent;
import lombok.var;

@All(DecalComponent.class)
public class DecalsRenderingSystem extends IteratingSystem {
    private DecalBatch batch;

    private ComponentMapper<DecalComponent> decalMapper;

    @Wire private PerspectiveCamera camera;

    @Override
    protected void initialize() {
        batch = new DecalBatch(new CameraGroupStrategy(camera));
    }

    @Override
    protected void process(int entityId) {
        var decal = decalMapper.get(entityId);
        batch.add(decal.getDecal());
    }

    @Override
    protected void end() {
        batch.flush();
    }
}
