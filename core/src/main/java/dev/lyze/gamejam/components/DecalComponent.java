package dev.lyze.gamejam.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import lombok.Getter;
import lombok.Setter;

public class DecalComponent extends Component {
    @Getter @Setter
    private Decal decal;
}
