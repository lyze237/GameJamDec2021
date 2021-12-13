package dev.lyze.gamejam.systems.abstracts;

import com.artemis.BaseSystem;

public abstract class InitializableBaseSystem extends BaseSystem {

    private boolean created;

    @Override
    protected void begin() {
        if (!created) {
            created();
            created = true;
        }
    }

    abstract void created();
}
