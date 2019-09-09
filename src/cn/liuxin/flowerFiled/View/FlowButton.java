package cn.liuxin.flowerFiled.View;

import cn.liuxin.flowerFiled.Controller.Judge;
import cn.liuxin.flowerFiled.Controller.Util;
import cn.liuxin.flowerFiled.Model.Flower;

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
    public FlowButton(int row, int col) {
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
               break;
               case MouseEvent.BUTTON3:
//                   System.out.println("右键点击");
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
