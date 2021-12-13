package dev.lyze.gamejam.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.var;

@NoArgsConstructor
public class MapComponent extends Component {
    @Getter
    private TextureRegion texture;

    public MapComponent(String path) {
        TiledMap map = new TmxMapLoader().load(path);
        var renderer = new OrthogonalTiledMapRenderer(map);

        var layer = findTileLayer(map);

        var buffer = new FrameBuffer(Pixmap.Format.RGBA8888, layer.getTileWidth() * layer.getWidth(), layer.getTileHeight() * layer.getHeight(), false);
        var cam = new OrthographicCamera();
        cam.setToOrtho(true, buffer.getWidth(), buffer.getHeight());

        buffer.begin();
        ScreenUtils.clear(Color.CLEAR);

        renderer.setView(cam);
        renderer.render();

        buffer.end();

        texture = new TextureRegion(buffer.getColorBufferTexture());
        texture.getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        texture.flip(false, true);
    }

    private TiledMapTileLayer findTileLayer(TiledMap map) {
        for (MapLayer mapLayer : map.getLayers())
            if (mapLayer instanceof TiledMapTileLayer)
                return ((TiledMapTileLayer) mapLayer);

        return null;
    }
}
