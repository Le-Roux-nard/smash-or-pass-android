package bzh.lerouxard.smashorpass.cardstackview.internal;

import android.view.animation.Interpolator;

import bzh.lerouxard.smashorpass.cardstackview.Direction;

public interface AnimationSetting {
    Direction getDirection();
    int getDuration();
    Interpolator getInterpolator();
}
