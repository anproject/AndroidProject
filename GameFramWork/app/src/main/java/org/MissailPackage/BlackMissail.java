package org.MissailPackage;

import android.graphics.Bitmap;

import com.example.gameframework.R;
import com.example.gameframework.org.FrameWork.AppManager;

public class BlackMissail extends Missail {
    public final int m_Damage = 5;
    public final int m_Speed = 5;
    public final  int m_Limit = 10;
    public BlackMissail(Bitmap bitmap, int x, int y, float speed, int damage)
    {
        super(bitmap);
        setPosition(x,y);
        set_State(speed,damage);
        m_bitmap = AppManager.getInstance().reSizing(bitmap,100,100);
    }

    public BlackMissail() {
        super(AppManager.getInstance().getBitMap(R.drawable.black_missile));
        m_bitmap = AppManager.getInstance().reSizing(m_bitmap,100,100);
        set_State(m_Speed,m_Damage);
    }

    public BlackMissail(Bitmap m_bitmap) {
        super(m_bitmap);
        m_bitmap = AppManager.getInstance().reSizing(m_bitmap,100,100);
        set_State(m_Speed,m_Damage);
    }
    @Override
    public void Update()
    {
        m_y -= missail_speed;
        super.Update();
        if(this.m_y < 0)
        {
            this.setM_state(Missail.STATE_OUT);
        }
    }
    @Override
    public int getLimit() {
        return m_Limit;
    }
}
