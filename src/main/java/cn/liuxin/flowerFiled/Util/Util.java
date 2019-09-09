package cn.liuxin.flowerFiled.Util;
/*
Copyright 2019 Liuxin

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
public class Util {
    private static Util util;
    private int gridRow;
    private int gridCol;
    private int xLength;
    private int yLength;
    private int flowersNum;
    private int gridSize;
    private int difficty;

    public static final int GridSmall = 25;
    public static final int GridMedium =50;
    public static final int GridLarge = 100;
    public static final int GridNumNINE=9;
    public static final int GridNumSIXTEEN=16;
    public static final int GridNumTHREETH=30;

    public static final int DifficutyEasy=0;
    public static final int DifficutyNormal=1;
    public static final int DifficutyHard=2;
    public static final int DifficutyExpert=3;
    public static final int DifficutyCustom=4;

    private boolean safeFirst;
    private boolean allowMusic=true;
    private boolean allowSound=true;
    private boolean finishFirstClick=false;

    private Util()
    {
        setEasyMode();
        setGirdMedium();
    }

    public static Util getUtil()
    {
        if(util==null)
            util=new Util();
        return util;
    }

    public int getGridRow() {
        return gridRow;
    }

    public void setGridRow(int gridRow) {
        this.gridRow = gridRow;
    }

    public int getGridCol() {
        return gridCol;
    }

    public void setGridCol(int gridCol) {
        this.gridCol = gridCol;
    }

    public int getxLength() {
        return xLength;
    }

    public void setxLength() {
        this.xLength = gridRow*gridSize;
    }

    public int getyLength() {
        return yLength;
    }

    public void setyLength() {
        this.yLength = gridCol*gridSize;
    }

    public int getFlowersNum() {
        return flowersNum;
    }

    public void setFlowersNum(int flowersNum) {
        this.flowersNum = flowersNum;
    }
    public void setGirdSmall()
    {
        gridSize=GridSmall;
        xLength=gridRow*gridSize;
        yLength=gridCol*gridSize;
    }

    public void setGirdMedium()
    {
        gridSize=GridMedium;
        xLength=gridRow*gridSize;
        yLength=gridCol*gridSize;
    }

    public void setGirdLarge()
    {
        gridSize=GridLarge;
        xLength=gridRow*gridSize;
        yLength=gridCol*gridSize;
    }

    public void setEasyMode()
    {
        difficty=DifficutyEasy;
        gridRow=GridNumNINE;
        gridCol=GridNumNINE;
        flowersNum=10;
    }

    public void setNomralMode()
    {
        difficty=DifficutyNormal;
        gridRow=GridNumSIXTEEN;
        gridCol=GridNumSIXTEEN;
        flowersNum=36;
    }

    public void setHardMode()
    {
        difficty=DifficutyHard;
        gridRow=GridNumTHREETH;
        gridCol=GridNumSIXTEEN;
        flowersNum=54;
    }

    public void setExpertMode()
    {
        difficty=DifficutyExpert;
        gridRow=GridNumTHREETH;
        gridCol=GridNumSIXTEEN;
        flowersNum=100;
    }

    public void setCustomMode(int rows,int cols,int bombCount)
    {
        difficty=DifficutyCustom;
        gridRow=rows;
        gridCol=cols;
        flowersNum=bombCount;
    }

    public boolean isSafeFirst() {
        return safeFirst;
    }

    public void setSafeFirst(boolean safeFirst) {
        this.safeFirst = safeFirst;
    }

    public boolean isAllowMusic() {
        return allowMusic;
    }

    public void setAllowMusic(boolean allowMusic) {
        this.allowMusic = allowMusic;
    }

    public boolean isAllowSound() {
        return allowSound;
    }

    public void setAllowSound(boolean allowSound) {
        this.allowSound = allowSound;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public int getDifficty() {
        return difficty;
    }

    public void setDifficty(int difficty) {
        this.difficty = difficty;
    }

    public boolean isFinishFirstClick() {
        return finishFirstClick;
    }

    public void setFinishFirstClick(boolean finishFirstClick) {
        this.finishFirstClick = finishFirstClick;
    }
}

