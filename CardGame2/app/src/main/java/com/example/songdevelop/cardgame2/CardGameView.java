package com.example.songdevelop.cardgame2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Random;

public class CardGameView extends View {
    public int m_count_fail=0;
    public TextView m_counting;
    private Bitmap m_BackGroundImage;
    private Bitmap m_CardBackSide;
    private Bitmap m_Card_Red;
    private Bitmap m_Card_Blue;
    private Bitmap m_Card_Green;
    private MediaPlayer m_Background_Music;
    public SoundPool m_Sound_Pool;
    public int m_Effect_id_1;
    public T_GameChecking thread;
    public Card m_SelectCard_1= null,m_SelectCard_2= null;
    private StateGame state;
    public Card m_Shuffle[][];
    public int fullWidth=0,fullHeight=0,width=0,height=0;
    public int x=0,y=0;
    private Context super_context;

    public CardGameView(Context context) {
        super(context);
        super_context = context;
        m_Sound_Pool = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
        m_Effect_id_1 = (int) m_Sound_Pool.load(context,R.raw.effect1,1);
        state = new StateGame();
        m_Background_Music = MediaPlayer.create(context,R.raw.background);
        m_BackGroundImage = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        m_CardBackSide = BitmapFactory.decodeResource(getResources(),R.drawable.backside);
        m_Card_Blue= BitmapFactory.decodeResource(getResources(),R.drawable.front_blue);
        m_Card_Green= BitmapFactory.decodeResource(getResources(),R.drawable.front_green);
        m_Card_Red = BitmapFactory.decodeResource(getResources(),R.drawable.front_red);
        m_Background_Music.setLooping(true);
        m_Background_Music.start();
        m_Shuffle = new Card[3][2];
        setCardSize();
        setCardResizing();
        setCardInit();
        thread = new T_GameChecking(this);
        thread.start();
    }
    public Context getSuper_context()
    {
        return super_context;
    }
    public void setCardSize()
    {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        fullWidth = metrics.widthPixels;
        fullHeight = metrics.heightPixels;
        width = fullWidth/3;
        height = fullHeight/2;
        Double m_width = width*0.7,m_height = height*0.5;
        width = m_width.intValue();
        height = m_height.intValue();
        this.x =  fullWidth/7;
        this.y = fullHeight/3;
    }
    public void setCardInit()
    {
        m_Shuffle[0][0] = new Card(Card.IMG_RED);
        m_Shuffle[0][1] = new Card(Card.IMG_RED);
        m_Shuffle[1][0] = new Card(Card.IMG_GREEN);
        m_Shuffle[1][1] = new Card(Card.IMG_GREEN);
        m_Shuffle[2][0] = new Card(Card.IMG_BLUE);
        m_Shuffle[2][1] = new Card(Card.IMG_BLUE);
    }
    public void setCardShuffle()
    {
        Random rand = new Random();
        Card temp;
        int randx[] = new int[2];
        int randy[] = new int[2];
        for(int i = 0 ; i <10;i++) {
            randx[0] = rand.nextInt(3);
            randy[0] = rand.nextInt(2);
            randx[1] = rand.nextInt(3);
            randy[1] = rand.nextInt(2);

            temp = m_Shuffle[randx[0]][randy[0]];
            m_Shuffle[randx[0]][randy[0]] = m_Shuffle[randx[1]][randy[1]];
            m_Shuffle[randx[1]][randy[1]] = temp;
        }
    }
    /*
    public void setCardShuffle()
    {
        Queue<Integer> item[] = new Queue[3];
        item[0] = new LinkedList<Integer>();
        item[1] = new LinkedList<Integer>();
        item[2] = new LinkedList<Integer>();

        for(int i = 0 ; i <3;i++)
        {
            for(int j = 0 ; j <2;j++)
            {
                item[i].add(i+1);
            }
        }
        Random rand = new Random();
        for(int i = 0 ; i <2;i++)
        {
            for(int j = 0 ; j <3;j++)
            {
                int choose = rand.nextInt(3);
                while(item[choose].isEmpty()){
                    choose = rand.nextInt(3);
                }
                m_Shuffle[j][i] = new Card(item[choose].remove());
            }
        }
    }*/
    public void startGame()
    {
        setCardShuffle();
        for(int i = 0 ; i<2;i++ ) {
            for (int j = 0; j < 3; j++)
            {
               m_Shuffle[j][i].m_State = Card.CARD_CLOSE;
            }
        }
        m_count_fail =0;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        state.Game(state,event,this);
        invalidate();
        return super.onTouchEvent(event);
    }
    public boolean isFinish()
    {
        for(int i=0;i<2;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(m_Shuffle[j][i].m_State != Card.CARD_MATCHED){
                    return false;
                }
            }
        }
        return true;
    }
    public void checkMatch()
    {
        if(m_SelectCard_1 == null || m_SelectCard_2 == null) return;
        if(m_SelectCard_1.m_Color == m_SelectCard_2.m_Color)
        {
            m_SelectCard_1.m_State = Card.CARD_MATCHED;
            m_SelectCard_2.m_State = Card.CARD_MATCHED;
            m_SelectCard_1 = null;
            m_SelectCard_2 = null;
        }
        else
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            m_SelectCard_1.m_State = Card.CARD_CLOSE;
            m_SelectCard_2.m_State = Card.CARD_CLOSE;
            m_SelectCard_1 = null;
            m_SelectCard_2 = null;
            m_count_fail+=1;
        }
        if(isFinish()) {
            state.setCurrent(state.getEnd());
        }
        postInvalidate();
    }
    public void setCardResizing()
    {
        m_BackGroundImage = Bitmap.createScaledBitmap(m_BackGroundImage, fullWidth, fullHeight, true);
        m_CardBackSide = Bitmap.createScaledBitmap(m_CardBackSide, width, height, true);
        m_Card_Green = Bitmap.createScaledBitmap(m_Card_Green, width, height, true);
        m_Card_Blue = Bitmap.createScaledBitmap(m_Card_Blue, width, height, true);
        m_Card_Red = Bitmap.createScaledBitmap(m_Card_Red, width, height, true);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        canvas.drawBitmap(m_BackGroundImage,0,0,null);
        for(int y=0;y<2;y++)
        {
            for(int x = 0;x<3;x++)
            {
                if(m_Shuffle[x][y].m_State == Card.CARD_MATCHED
                        || m_Shuffle[x][y].m_State == Card.CARD_SHOW
                        || m_Shuffle[x][y].m_State == Card.CARD_PLAYEROPEN)
                {
                    if (m_Shuffle[x][y].m_Color == Card.IMG_RED)
                        canvas.drawBitmap(m_Card_Red, this.x + ((width+10)*x), this.y + ((height+10) * y), null);
                    else if (m_Shuffle[x][y].m_Color == Card.IMG_GREEN)
                        canvas.drawBitmap(m_Card_Green, this.x + ((width+10)* x), this.y + ((height+10) * y), null);
                    else if (m_Shuffle[x][y].m_Color == Card.IMG_BLUE)
                        canvas.drawBitmap(m_Card_Blue, this.x + ((width+10) * x), this.y + ((height+10) * y), null);
                }
                else {
                    canvas.drawBitmap(m_CardBackSide, this.x + ((width+10) * x), this.y + ((height+10) * y), null);
                }
            }
        }
    }
}
