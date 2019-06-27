package com.example.gameframework.org.FrameWork;

import android.content.Context;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.gameframework.R;

import org.Game.GameState;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameViewThread m_thread;
    //private IStat m_state;
    private SpriteAnimation m_spr;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
      //  m_state.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       // m_state.onKeyDown(keyCode,event);
        return true;
    }

    public GameView(Context context) {
        super(context);
        setFocusable(true);
        AppManager.getInstance().setM_view(this);
        AppManager.getInstance().setM_res(getResources());
    //    changeGameState(new GameState());
        getHolder().addCallback(this);
        m_spr = new SpriteAnimation(AppManager.getInstance().getBitMap(R.drawable.player));
        m_spr.initSpriteData(62,102,10,6);
        m_thread = new GameViewThread(getHolder(),this);

    }
    public void Update(){
//        m_state.Update();
       long gameTime = System.currentTimeMillis();
        m_spr.Update(gameTime);
    }
    /*public void changeGameState(IStat _state)
    {
        if(m_state != null)
        {
            m_state.Destroy();
        }
        _state.init();
        m_state = _state;
    }*/
    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawColor(Color.BLACK);
//        m_state.Render(canvas);
            m_spr.Draw(canvas);
//        m_state.Render(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        m_thread.setRunning(true);
        m_thread.start();
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        m_thread.setRunning(false);
        while(retry)
        {
            try {
                m_thread.join();
                retry= false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}