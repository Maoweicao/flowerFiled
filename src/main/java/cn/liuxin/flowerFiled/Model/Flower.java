package cn.liuxin.flowerFiled.Model;
/*
Copyright 2019 Liuxin

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
import cn.liuxin.flowerFiled.Util.Util;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 花（雷）的数据结构类
 */
public class Flower {
    //画的内部结构
    private int[][] flowers;
    public final static int FLOWERS = -1;
    public final static int FLAGS = 10;
    private Semaphore redayToPlay = new Semaphore(0);
    ///横向方位内，雷的X轴坐标
    int[] xNums;
    ///纵向方位内，雷的Y轴坐标
    int[] yNums;
    public Flower()
    {
        int rowNum=Util.getUtil().getGridRow();
        int colNum=Util.getUtil().getGridCol();
        int countFlowers = 0;
        flowers = new int[rowNum][colNum];
        ///横向方位内，雷的X轴坐标
        xNums = new int[countFlowers];
        ///纵向方位内，雷的Y轴坐标
        yNums = new int[countFlowers];
    }
    public void initFlowersMat(int avoidX, int avoidY) {
        Random random = new Random();
        int countFlowers=Util.getUtil().getFlowersNum();
        int rowNum = Util.getUtil().getGridRow();
        int colNum = Util.getUtil().getGridCol();
        flowers = new int[rowNum][colNum];
        ///横向方位内，雷的X轴坐标
        xNums = new int[countFlowers];
        ///纵向方位内，雷的Y轴坐标
        yNums = new int[countFlowers];
        ///生成步数 = 雷的数量
        for (int i = 0; i < countFlowers; i++) {
            //X坐标不允许超过横向格子数量
            xNums[i] = random.nextInt(rowNum-1);
            //Y坐标不允许超过纵向格子数量
            yNums[i] = random.nextInt(colNum-1);
            //进行坐标查重
            for (int j = 0; j < i; j++) {
                //如果与前面格子比横纵坐标一样，则说明重复，同时也要避开额外的区域。
                if (xNums[i] == xNums[j] || xNums[i] == avoidX) {
                    if (yNums[i] == yNums[j] || yNums[i] == avoidY) {
                        int mode = random.nextInt(2);
                        switch (mode) {
                            //掷骰子，骰子1，则修改X坐标
                            case 0:
                                xNums[i]++;
                                if (xNums[i] > rowNum) {
                                    //如果范围超界，后退一步，然后再反向后退一步，下同
                                    xNums[i] -= 2;
                                }
                                break;
                            //掷骰子，骰子1，则修改Y坐标
                            case 1:
                                yNums[i]++;
                                if (yNums[i] > colNum) {
                                    yNums[i] -= 2;
                                }
                                break;
                        }
                    }
                }
            }  //进行雷标记
            flowers[xNums[i]][yNums[i]] = FLOWERS;
        }
        //进行数量标记
        for (int i = 0; i < countFlowers; i++) {
            //扫描雷的矩阵
            if (xNums[i] > 0) {
                //雷的x桌标大于零
                if (flowers[xNums[i] - 1][yNums[i]] != FLOWERS)
                    flowers[xNums[i] - 1][yNums[i]]++;
                if (yNums[i] > 0) {
                    if (flowers[xNums[i] - 1][yNums[i] -1] != FLOWERS)
                        flowers[xNums[i] - 1][yNums[i] - 1]++;
                }
                if (yNums[i] < colNum - 1) {
                    if (flowers[xNums[i] - 1][yNums[i] + 1] != FLOWERS)
                        flowers[xNums[i] - 1][yNums[i] + 1]++;
                }
            }
            if (xNums[i] < rowNum - 1) {
                //雷的x桌标大于零
                if (flowers[xNums[i] + 1][yNums[i]] != FLOWERS)
                    flowers[xNums[i] + 1][yNums[i]]++;
                if (yNums[i] > 0) {
                    if (flowers[xNums[i] + 1][yNums[i] - 1] != FLOWERS)
                        flowers[xNums[i] + 1][yNums[i] - 1]++;
                }
                if (yNums[i] < colNum - 1) {
                    if (flowers[xNums[i] + 1][yNums[i] + 1] != FLOWERS)
                        flowers[xNums[i] + 1][yNums[i] + 1]++;
                }
            }
            if (yNums[i] > 0) {
                if (flowers[xNums[i]][yNums[i] - 1] != FLOWERS)
                    flowers[xNums[i]][yNums[i] - 1]++;
            }
            if (yNums[i] < colNum - 1) {
                if (flowers[xNums[i]][yNums[i] + 1] != FLOWERS)
                    flowers[xNums[i]][yNums[i] + 1]++;
            }
        }
        redayToPlay.release();
    }
    public void initFlowersMat() {
        Random random = new Random();
        int countFlowers=Util.getUtil().getFlowersNum();
        int rowNum = Util.getUtil().getGridRow();
        int colNum = Util.getUtil().getGridCol();
        flowers = new int[rowNum][colNum];
        ///横向方位内，雷的X轴坐标
        xNums = new int[countFlowers];
        ///纵向方位内，雷的Y轴坐标
        yNums = new int[countFlowers];
        ///生成步数 = 雷的数量
        for (int i = 0; i < countFlowers; i++) {
            //X坐标不允许超过横向格子数量
            xNums[i] = random.nextInt(rowNum-1);
            //Y坐标不允许超过纵向格子数量
            yNums[i] = random.nextInt(colNum-1);
            //进行坐标查重
            for (int j = 0; j < i; j++) {
                //如果与前面格子比横纵坐标一样，则说明重复，同时也要避开额外的区域。
                if (xNums[i] == xNums[j] ) {
                    if (yNums[i] == yNums[j] ) {
                        int mode = random.nextInt(2);
                        switch (mode) {
                            //掷骰子，骰子1，则修改X坐标
                            case 0:
                                xNums[i]++;
                                if (xNums[i] > rowNum) {
                                    //如果范围超界，后退一步，然后再反向后退一步，下同
                                    xNums[i] -= 2;
                                }
                                break;
                            //掷骰子，骰子1，则修改Y坐标
                            case 1:
                                yNums[i]++;
                                if (yNums[i] > colNum) {
                                    yNums[i] -= 2;
                                }
                                break;
                        }
                    }
                }
            }  //进行雷标记
            flowers[xNums[i]][yNums[i]] = FLOWERS;
        }
        //进行数量标记
        for (int i = 0; i < countFlowers; i++) {
            //扫描雷的矩阵
            if (xNums[i] > 0) {
                //雷的x桌标大于零
                if (flowers[xNums[i] - 1][yNums[i]] != FLOWERS)
                    flowers[xNums[i] - 1][yNums[i]]++;
                if (yNums[i] > 0) {
                    if (flowers[xNums[i] - 1][yNums[i] -1] != FLOWERS)
                        flowers[xNums[i] - 1][yNums[i] - 1]++;
                }
                if (yNums[i] < colNum - 1) {
                    if (flowers[xNums[i] - 1][yNums[i] + 1] != FLOWERS)
                        flowers[xNums[i] - 1][yNums[i] + 1]++;
                }
            }
            if (xNums[i] < rowNum - 1) {
                //雷的x桌标大于零
                if (flowers[xNums[i] + 1][yNums[i]] != FLOWERS)
                    flowers[xNums[i] + 1][yNums[i]]++;
                if (yNums[i] > 0) {
                    if (flowers[xNums[i] + 1][yNums[i] - 1] != FLOWERS)
                        flowers[xNums[i] + 1][yNums[i] - 1]++;
                }
                if (yNums[i] < colNum - 1) {
                    if (flowers[xNums[i] + 1][yNums[i] + 1] != FLOWERS)
                        flowers[xNums[i] + 1][yNums[i] + 1]++;
                }
            }
            if (yNums[i] > 0) {
                if (flowers[xNums[i]][yNums[i] - 1] != FLOWERS)
                    flowers[xNums[i]][yNums[i] - 1]++;
            }
            if (yNums[i] < colNum - 1) {
                if (flowers[xNums[i]][yNums[i] + 1] != FLOWERS)
                    flowers[xNums[i]][yNums[i] + 1]++;
            }
        }
        redayToPlay.release();
    }
    public void printMat() {
        int rowNum=Util.getUtil().getGridRow();
        int colNum=Util.getUtil().getGridCol();
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++)
                System.out.printf("%d\t",flowers[i][j]);
            System.out.println();
        }
    }

    public int[][] getFlowers() {
        return flowers;
    }

    public int[] getxNums() {
        return xNums;
    }

    public int[] getyNums() {
        return yNums;
    }

    public Semaphore getRedayToPlay() {
        return redayToPlay;
    }

    public void setRedayToPlay(Semaphore redayToPlay) {
        this.redayToPlay = redayToPlay;
    }
}
