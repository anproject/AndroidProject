package org.Game.Enemy;

import android.graphics.Bitmap;

import com.example.gameframework.R;
import org.FrameWork.AppManager;
import org.FrameWork.GraphicManager;

public class Turtle extends Enermy {
    public Turtle(Bitmap m_bitmap) {
        super(m_bitmap);
        setM_DestroyBitmap(AppManager.getInstance().getBitMap(R.drawable.destroy_boom_1));
    }
    public Turtle()
    {
        super(GraphicManager.getInstance().getEnemy(Turtle.class));
        this.initSpriteData(m_bitmap.getWidth()/4,m_bitmap.getHeight(),20,4);
        setM_DestroyBitmap(AppManager.getInstance().getBitMap(R.drawable.destroy_boom_1));
    }

    @Override
    public void set_State(int hp, float speed) {
        this.hp = hp;
        this.speed = speed;
    }
    @Override
    public void setM_DestroyBitmap(Bitmap bitmap) {
        m_DestroyBitmap = bitmap;
    }
}
