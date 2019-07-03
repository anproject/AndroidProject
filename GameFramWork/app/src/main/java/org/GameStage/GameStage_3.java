package org.GameStage;

import android.graphics.Canvas;

import com.example.gameframework.R;
import com.example.gameframework.org.FrameWork.AppManager;

import org.Game.Enemy.Flower;
import org.Game.Enemy.Goomba;
import org.Game.Enemy.OhBoss;
import org.Game.Enemy.Turtle;
import org.Game.GameState;
import org.Game.Player;

public class GameStage_3 extends GameState {
    private int stage = 3;
    @Override
    public void init(int background) {
        super.init(0);
        setM_player(new Player(AppManager.getInstance().getPlayer()));
        this.m_BossContain = false;
        this.m_StageRegenTime = 500;
        this.m_EnemyLimit = 30;
        this.m_BossTime= 10000;
        m_background.setM_bitmap(AppManager.getInstance().getBitMap(R.drawable.mario_background));
        this.enemys_name.put(0, Goomba.class);
        this.enemys_name.put(1, Turtle.class);
        this.enemys_name.put(2, Flower.class);
        this.contain_enemy = 3;
        this.boss_class = OhBoss.class;
    }

    @Override
    public void Update() {
        super.Update();
        if (this.m_StageClear) {

            AppManager.getInstance().getM_GameView().changeGameState(AppManager.getInstance().m_stage.clearStage);     }
    }

    @Override
    public void Render(Canvas canvas) {
        super.Render(canvas);
        canvas.drawText("Stage :" + stage,0,50,AppManager.getInstance().getPaint());
        canvas.drawText("Destroy :"+ destroy_enem,0,30, AppManager.getInstance().getPaint());
    }
}
