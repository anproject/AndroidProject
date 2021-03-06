package org.Factory;

import org.FrameWork.AppManager;

import org.Game.MissailPackage.Missail;
import org.Game.MovePackage.UpMovePattern;

import java.lang.reflect.InvocationTargetException;

public class MissailFactory {

    public static Missail missailMaker(Class missailName)
    {
        Missail missail = null;
        try {
            missail = (Missail)Class.forName(missailName.getName()).getConstructor().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        missail.set_State(MovePatternFactory.createMovePattern(UpMovePattern.class));
        return missail;
    }
    public static Missail missailMaker(Class missailName,int x,int y ,int damage,float speed)
    {
        Missail missail = null;
        try {
            missail = (Missail)Class.forName(missailName.getName()).getConstructor().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        missail.set_State(speed,damage);
        missail.setPosition(x,y);
        missail.set_State(MovePatternFactory.createMovePattern(UpMovePattern.class));
        return missail;
    }
    public static Missail missailMaker(Class missailName, float x, float y)
    {
        Missail missail = null;
        try {
            missail = (Missail)Class.forName(missailName.getName()).getConstructor().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        missail.setPosition(x,y);
        missail.set_State(MovePatternFactory.createMovePattern(UpMovePattern.class));
        return missail;
    }
    public static Missail ChargingmissailMaker(Class missailName)
    {
        Missail missail = null;
        try {
            missail = (Missail)Class.forName(missailName.getName()).getConstructor().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        missail.setBitmap(AppManager.getInstance().reSizing(missail.getM_bitmap(),missail.getM_bitmap().getWidth()*2,100));
        missail.set_State(MovePatternFactory.createMovePattern(UpMovePattern.class));
        return missail;
    }
    public static Missail ChargingmissailMaker(Class missailName, float x, float y, int damage, float speed)
    {
        Missail missail = null;
        try {
            missail = (Missail)Class.forName(missailName.getName()).getConstructor().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        missail.setBitmap(AppManager.getInstance().reSizing(missail.getM_bitmap(),missail.getM_bitmap().getWidth()*2,100));
        missail.set_State(speed,damage);
        missail.setPosition(x,y);
        missail.set_State(MovePatternFactory.createMovePattern(UpMovePattern.class));
        return missail;
    }
    public static Missail ChargingmissailMaker(Class missailName,int x,int y)
    {
        Missail missail = null;
        try {
            missail = (Missail)Class.forName(missailName.getName()).getConstructor().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        missail.setChargingImage();
        missail.set_State(missail.getMissail_speed()*3,missail.getDamage()*2);
        missail.setPosition(x,y);
        missail.set_State(MovePatternFactory.createMovePattern(UpMovePattern.class));
        return missail;
    }
    public static Missail createBossMissailMaker(Class missailName,int x,int y)
    {
        Missail missail = null;
        try {
            missail = (Missail)Class.forName(missailName.getName()).getConstructor().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        missail.setPosition(x,y);
        missail.set_State(MovePatternFactory.createRandomMovePattern());
        return missail;
    }
}
//클래스 객체를 받아, 해당 클래스 기본 생성자를 이용해서 만드는 인스턴스 객체를 세팅하여 만들어 반환한다.
