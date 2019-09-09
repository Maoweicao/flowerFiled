package cn.liuxin.flowerFiled.Controller;

import javax.swing.*;

public class Judge {
    private static Judge judge;
    private JFrame frame;

    private int countNum;
    private int flagsNum;
    private int bombNum;
    private int showEmptyNum;
    private int trueFlagsNum;

    private Judge()
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
        JOptionPane.showMessageDialog(frame, "恭喜您！赢了！");
    }

    public void showLose()
    {
        JOptionPane.showMessageDialog(frame, "很遗憾，祝您下次好运。");
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
}
