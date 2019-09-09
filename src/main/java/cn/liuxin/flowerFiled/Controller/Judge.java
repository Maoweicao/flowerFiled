package cn.liuxin.flowerFiled.Controller;
/*
Copyright 2019 Liuxin

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
import cn.liuxin.flowerFiled.Util.Util;
import cn.liuxin.flowerFiled.View.GamePanel;

import javax.swing.*;

public class Judge {
    private static Judge judge;
    private JFrame frame;
    private GamePanel panel;

    private int countNum;
    private int flagsNum;
    private int bombNum;
    private int showEmptyNum;
    private int trueFlagsNum;

    private Judge()
    {
        countNum= Util.getUtil().getGridCol()*Util.getUtil().getGridRow();
        flagsNum=0;
        bombNum=Util.getUtil().getFlowersNum();
        showEmptyNum=0;
        trueFlagsNum=0;
    }

    public void resetJudge()
    {
        countNum=Util.getUtil().getGridCol()*Util.getUtil().getGridRow();
        flagsNum=0;
        bombNum=Util.getUtil().getFlowersNum();
        showEmptyNum=0;
        trueFlagsNum=0;
    }

    public void addEmptyNum()
    {
        this.showEmptyNum++;
    }

    public void addTrueFlags()
    {
        this.trueFlagsNum++;
    }

    public void subEmptyNum()
    {
        this.showEmptyNum--;
    }

    public void subTrueFlags()
    {
        trueFlagsNum--;
    }

    public static Judge getJudge()
    {
        if(judge==null)
            judge=new Judge();
        return judge;
    }

    public boolean isWin()
    {
        if((trueFlagsNum==bombNum)&&!(flagsNum>bombNum))
        {
            return true;
        }
        if((countNum-showEmptyNum==bombNum))
        {
            return true;
        }
        return false;
    }

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }

    public int getFlagsNum() {
        return flagsNum;
    }
    public void addFlags()
    {
        flagsNum++;
    }

    public void subFlags()
    {
        flagsNum--;
    }
    public void setFlagsNum(int flagsNum) {
        this.flagsNum = flagsNum;
    }

    public int getShowEmptyNum() {
        return showEmptyNum;
    }

    public void setShowEmptyNum(int showEmptyNum) {
        this.showEmptyNum = showEmptyNum;
    }

    public int getTrueFlagsNum() {
        return trueFlagsNum;
    }

    public void setTrueFlagsNum(int trueFlagsNum) {
        this.trueFlagsNum = trueFlagsNum;
    }

    public int getBombNum() {
        return bombNum;
    }

    public void setBombNum(int bombNum) {
        this.bombNum = bombNum;
    }

    public void showWin()

    {

        Music.getMusic().stopPlay();
        JOptionPane.showMessageDialog(frame, "恭喜您！赢了！");
    }

    public void showLose()
    {
        Music.getMusic().stopPlay();
        Music.getMusic().musicPlayLose();
        JOptionPane.showMessageDialog(frame, "很遗憾，祝您下次好运。");
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public GamePanel getPanel() {
        return panel;
    }

    public void setPanel(GamePanel panel) {
        this.panel = panel;
    }
}
