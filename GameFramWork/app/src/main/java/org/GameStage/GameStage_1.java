package org.GameStage;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.FontRes;
import android.util.Log;

import com.example.gameframework.R;
import com.example.gameframework.org.FrameWork.AppManager;

import org.Game.GameState;
import org.GameStateCollect.GameClear;

public class GameStage_1 extends GameState {
    @Override
    public void init() {
        setM_player(AppManager.getInstance().getPlayer());
        this.m_BossContain = false;
        this.m_EnemyLimit = 25;
        this.m_BossTime= 10000;
        super.init();
    }

    @Override
    public void Update() {
        super.Update();
        if (this.m_StageClear) {
            AppManager.getInstance().getM_GameView().changeGameState(AppManager.getInstance().m_stage.gameStates[1]);
        }
    }

    @Override
    public void Render(Canvas canvas) {
        super.Render(canvas);
        canvas.drawText("Destroy :"+Integer.toString(destroy_enem),0,30, AppManager.getInstance().getPaint());
    }
}
