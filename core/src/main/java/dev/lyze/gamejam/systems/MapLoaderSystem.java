package dev.lyze.gamejam.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import dev.lyze.gamejam.components.DecalComponent;
import dev.lyze.gamejam.components.MapToDecalConverter;
import dev.lyze.gamejam.components.MapReferenceComponent;
import dev.lyze.gamejam.managers.Assets;
import lombok.var;

@All({MapReferenceComponent.class, MapToDecalConverter.class, DecalComponent.class})
public class MapLoaderSystem extends IteratingSystem {
    private ComponentMapper<MapToDecalConverter> mapToDecalMapper;
    private ComponentMapper<MapReferenceComponent> mapReferenceMapper;
    private ComponentMapper<DecalComponent> decalMapper;

    @Wire private Assets assets;

    @Override
    protected void inserted(int entityId) {
        var mapToDecal = mapToDecalMapper.get(entityId);
        var mapReference = mapReferenceMapper.get(entityId);
        var decal = decalMapper.get(entityId);

        var tiledMap = assets.getMap(mapReference.getFile());
        mapToDecal.convert(tiledMap);

        decal.setDecal(Decal.newDecal(mapToDecal.getTexture()));
        decal.getDecal().rotateX(90);
    }

    protected void process(int entityId) { }
}
