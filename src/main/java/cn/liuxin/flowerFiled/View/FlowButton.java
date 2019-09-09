package cn.liuxin.flowerFiled.View;
/*
Copyright 2019 Liuxin

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
import cn.liuxin.flowerFiled.Controller.Judge;
import cn.liuxin.flowerFiled.Controller.Sound;
import cn.liuxin.flowerFiled.Model.Flower;
import cn.liuxin.flowerFiled.Util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FlowButton extends JButton implements MouseListener {
    private int type;
    private boolean haveFlags;
    private int row;
    private int col;
    private Icon icon;
    private GamePanel panel;

    public FlowButton(int row, int col,GamePanel panel) {
        this.panel=panel;
        Image image = Toolkit.getDefaultToolkit().getImage(".//qizi.png");
        icon = new ImageIcon(image);
        this.row = row;
        this.col = col;
        this.addMouseListener(this);
    }

    public FlowButton(int type)
    {
        super();
        this.type=type;
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       switch (e.getButton())
       {
           case MouseEvent.BUTTON1:
//               System.out.println("左键点击");
               //this.setVisible(false);
               Sound.getSound().soundPlayClick();
               if(Util.getUtil().isSafeFirst())
               {
                   if(!Util.getUtil().isFinishFirstClick())
                   {
                        panel.finishInit(this.row, this.col);
                        panel.repaintButtons();
                        panel.repaintLabels();
                        Util.getUtil().setFinishFirstClick(true);
                        panel.open(this, false);
                   }
               }
               break;
               case MouseEvent.BUTTON3:
//                   System.out.println("右键点击");
                   Sound.getSound().soundPlayFlag();
                   if(haveFlags)
                   {
                       if(isBomb())
                       {
                           Judge.getJudge().subTrueFlags();
                       }else{
                           Judge.getJudge().subEmptyNum();
                       }
                       Judge.getJudge().subFlags();
                       this.setIcon(null);
                       haveFlags=false;
                   }else{
                       if(isBomb())
                       {
                           Judge.getJudge().addTrueFlags();
                       }else{
                           Judge.getJudge().addEmptyNum();
                       }
                       Judge.getJudge().addFlags();
                       this.setIcon(icon);
                       haveFlags=true;
                   }
                   if(Judge.getJudge().isWin())
                   {
                       panel.showAllPanelWithoutBomb();
                       Judge.getJudge().showWin();
                   }
                  break;
       }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isHaveFlags() {
        return haveFlags;
    }

    public void setHaveFlags(boolean haveFlags) {
        this.haveFlags = haveFlags;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isBomb()
    {
        if(type== Flower.FLOWERS)
            return true;
        else
            return false;
    }

}
