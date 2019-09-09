package cn.liuxin.flowerFiled.Controller;
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

    public final int GridSmall = 25;
    public final int GridMemdie =50;
    public final int GridLarge = 100;
    public final int GridNumNINE=9;
    public final int GridNumSIXTEEN=16;
    public final int GridNumTHREETH=30;

    private Util()
    {
        gridRow=GridNumSIXTEEN;
        gridCol=GridNumSIXTEEN;
        gridSize=GridMemdie;
        xLength=gridRow*gridSize;
        yLength=gridCol*gridSize;
        flowersNum=10;
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
