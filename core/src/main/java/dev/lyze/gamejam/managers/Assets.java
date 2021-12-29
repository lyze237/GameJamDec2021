package dev.lyze.gamejam.managers;

import com.artemis.Manager;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import lombok.Getter;
import net.mgsx.gltf.loaders.gltf.GLTFAssetLoader;
import net.mgsx.gltf.scene3d.scene.SceneAsset;

public class Assets extends Manager {
    private final AssetManager assetManager = new AssetManager();

    public Assets() {
        assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        assetManager.setLoader(SceneAsset.class, ".gltf", new GLTFAssetLoader());

        for (File file : File.values())
            assetManager.load(file.getPath(), file.getClazz());

        assetManager.finishLoading();
    }

    public TiledMap getMap(File file) {
        return (TiledMap) get(file);
    }

    public SceneAsset getSceneAsset(File file) {
        return (SceneAsset) get(file);
    }

    public Object get(File file) {
        return assetManager.get(file.getPath(), file.getClazz());
    }

    public enum File {
        MODEL_TestTower("turrets/DevTower.gltf", SceneAsset.class),
        MAP_DevMap("maps/DevMap.tmx",TiledMap.class);

        @Getter
        private final String path;
        @Getter
        private final Class<?> clazz;

        File(String path, Class<?> clazz) {
            this.path = path;
            this.clazz = clazz;
        }
    }
}
