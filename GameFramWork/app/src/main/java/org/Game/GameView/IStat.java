package org.Game.GameView;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface IStat {
    void init(int background);
    void Destroy();
    void Update();
    void Render(Canvas canvas);
    boolean onTouchEvent(MotionEvent event);
}