package org.Game.MissailPackage;

import android.graphics.Bitmap;

import com.example.gameframework.R;
import org.FrameWork.AppManager;

public class BossMissail extends Missail {
    public final float speed = 7;
    public final int damage =5;
    public final int m_Limit = 10;
    public BossMissail(Bitmap bitmap, int x, int y, float speed, int damage)
    {
        super(bitmap);
        setPosition(x,y);
        set_State(speed,damage);
    }
    public BossMissail(Bitmap m_bitmap) {
        super(m_bitmap);
    }
    public BossMissail()
    {
        super(AppManager.getInstance().getBitMap(R.drawable.boss_missile1));
        m_bitmap = AppManager.getInstance().reSizing(m_bitmap,AppManager.getInstance().getM_GameView().getFullWidth()/6,AppManager.getInstance().getM_GameView().getFullHeight()/8);
        set_State(speed,damage);
    }

    @Override
    public int getLimit() {
        return m_Limit;
    }

}