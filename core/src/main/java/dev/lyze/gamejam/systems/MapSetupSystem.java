package dev.lyze.gamejam.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import dev.lyze.gamejam.components.DecalComponent;
import dev.lyze.gamejam.components.MapComponent;
import lombok.var;

@All({MapComponent.class, DecalComponent.class})
public class MapSetupSystem extends IteratingSystem {
    private ComponentMapper<MapComponent> mapMapper;
    private ComponentMapper<DecalComponent> decalMapper;

    @Override
    protected void inserted(int entityId) {
        var map = mapMapper.get(entityId);
        var decal = decalMapper.get(entityId);

        decal.setDecal(Decal.newDecal(map.getTexture()));
        decal.getDecal().rotateX(90);
    }

    protected void process(int entityId) { }
}
