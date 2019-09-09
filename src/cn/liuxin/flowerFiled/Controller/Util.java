package cn.liuxin.flowerFiled.Controller;

public class Util {
    private static Util util;
    private int gridRow;
    private int gridCol;
    private int xLength;
    private int yLength;
    private int flowersNum;
    private int gridSize;

    public final int GridSmall = 25;
    public final int GridMemdie =50;
    public final int GridLarge = 100;
    public final int GridNumNINE=9;
    public final int GridNumSIXTEEN=16;
    public final int GridNumTHREETH=30;

    private Util()
    {
        gridRow=GridNumNINE;
        gridCol=GridNumNINE;
        gridSize=GridMemdie;
        xLength=gridRow*gridSize;
        yLength=gridCol*gridSize;
        flowersNum=GridNumNINE;
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
}
