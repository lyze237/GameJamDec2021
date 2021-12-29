package dev.lyze.gamejam.components;

import com.artemis.Component;
import com.artemis.annotations.All;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import net.mgsx.gltf.scene3d.scene.Scene;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThreeDComponent extends Component {
    private Scene scene;
}
