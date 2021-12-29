package dev.lyze.gamejam.components;

import com.artemis.Component;
import dev.lyze.gamejam.managers.Assets;
import lombok.*;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MapReferenceComponent extends Component {
    private Assets.File file;
}
