package cn.liuxin.flowerFiled;

import cn.liuxin.flowerFiled.Controller.Judge;
import cn.liuxin.flowerFiled.Controller.Util;
import cn.liuxin.flowerFiled.Model.Flower;
import cn.liuxin.flowerFiled.View.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.lang.management.ThreadInfo;

public class Game {

    public static void main(String[] args) {
        Flower flower = new Flower();

        flower.initFlowersMat();
        flower.printMat();
        new Thread(new Runnable() {
            @Override
            public void run() {
                JFrame w =new JFrame();
                try {
                    Thread.sleep(3000);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                GamePanel mPanel = new GamePanel(9, 9, Util.getUtil().getFlowersNum(),flower);
       /* long starTime=System.currentTimeMillis();

        long endTime=System.currentTimeMillis();
        System.out.println("===================");
        System.out.println("所用时间"+((endTime-starTime)%1000)+"秒");*/

                int[] panelSize = mPanel.returnSize();
                w.setSize(panelSize[0],panelSize[1]);
                Container c =w.getContentPane();
                c.add(mPanel);
                w.setTitle("扫雷游戏");
                w.setResizable(false);
                w.setVisible(true);
                Judge.getJudge().setFrame(w);

            }
        }).start();
    }
}
