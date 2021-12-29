package dev.lyze.gamejam.components;

import com.artemis.Component;
import dev.lyze.gamejam.managers.Assets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreeDReferenceComponent extends Component {
    private Assets.File file;
}
