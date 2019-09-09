package cn.liuxin.flowerFiled.View;
/*
Copyright 2019 Liuxin

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
import cn.liuxin.flowerFiled.Controller.Judge;
import cn.liuxin.flowerFiled.Util.Util;
import cn.liuxin.flowerFiled.Model.Flower;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JFXPanel {
    private int rows;
    private int cols;
    private int bombCount;
    private int[][] bombMap;
    private int BLOCKWIDTH = 50;
    private int BLOCKHIGH = 50;
    private JLabel[][] labels;
    private FlowButton[][] buttons;
    private Flower flower;
    private final int[][] offset = {{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}};
    private Image backgroudImage;


    public GamePanel(int rows, int cols, int bombCount, Flower flower) {
        this.rows = rows;
        this.cols = cols;
        this.setVisible(false);
        this.bombCount = bombCount;
        this.flower = flower;
        this.setLayout(null);
        bombMap = flower.getFlowers();
        backgroudImage = Toolkit.getDefaultToolkit().getImage(".\\001.jpg");
        if(!Util.getUtil().isSafeFirst())
        {
            init();
        }
        else
        {
            initSafe();
        }
        resetPreferredSize();
    }
    public void init()
    {
        this.BLOCKWIDTH=Util.getUtil().getGridSize();
        this.BLOCKHIGH=Util.getUtil().getGridSize();
        labels = new JLabel[rows][cols];
        buttons = new FlowButton[rows][cols];
        bombMap = flower.getFlowers();
        this.removeAll();
        initButtons();
        initLabels();
        drawMat();
        bindBombToBtn();
        this.repaint();
        this.setVisible(true);
    }
    public void initSafe()
    {
        this.BLOCKWIDTH=Util.getUtil().getGridSize();
        this.BLOCKHIGH=Util.getUtil().getGridSize();
        labels = new JLabel[rows][cols];
        buttons = new FlowButton[rows][cols];
        this.removeAll();
        initButtons();
        initLabels();
        this.repaint();
        this.setVisible(true);
    }
    public void initSafe(Flower flower)
    {
        if(flower==null)
        {
            this.flower=flower;
        }
        this.BLOCKWIDTH=Util.getUtil().getGridSize();
        this.BLOCKHIGH=Util.getUtil().getGridSize();
        labels = new JLabel[rows][cols];
        buttons = new FlowButton[rows][cols];
        this.removeAll();
        initButtons();
        initLabels();
        this.repaint();
        this.setVisible(true);
    }

    public void finishInit(int row,int col)
    {
        flower.initFlowersMat(row,col);
        bombMap = flower.getFlowers();
        flower.printMat();
        drawMat();
        bindBombToBtn();
    }

    public void init(Flower flower)
    {
        this.BLOCKWIDTH=Util.getUtil().getGridSize();
        this.BLOCKHIGH=Util.getUtil().getGridSize();
        this.cols=Util.getUtil().getGridCol();
        this.rows=Util.getUtil().getGridRow();
        this.bombCount=Util.getUtil().getFlowersNum();
        this.flower=flower;
        labels = new JLabel[rows][cols];
        buttons = new FlowButton[rows][cols];
        bombMap = flower.getFlowers();
        this.removeAll();
        initButtons();
        initLabels();
        drawMat();
        bindBombToBtn();
        this.repaint();
        this.setVisible(true);
    }
    //初始化标签
    private void initLabels() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.cols; j++) {
                JLabel l = new JLabel("", JLabel.CENTER);
                l.setBounds(j * BLOCKWIDTH, i * BLOCKHIGH, BLOCKWIDTH, BLOCKHIGH);
                l.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                l.setOpaque(true);
                l.setBackground(Color.DARK_GRAY);
                this.add(l);
                labels[i][j] = l;
            }
        }
        this.validate();
    }

    //绘制数组
    private void drawMat() {
        Font font = new Font("微软雅黑", Font.BOLD, 30);
        Image flowerImg = Toolkit.getDefaultToolkit().getImage(".\\flower.png");
        Icon flowerIcon = new ImageIcon(flowerImg);
        int[][] mat = flower.getFlowers();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String text;
                Color bColor, fColor;
                switch (mat[i][j]) {
                    case Flower.FLOWERS:
                        text = "*";
                        bColor = Color.DARK_GRAY;
                        fColor = Color.RED;

                        break;
                    case 1:
                        text = "1";
                        bColor = Color.GRAY;
                        fColor = Color.GREEN;
                        break;
                    case 2:
                        text = "2";
                        bColor = Color.GRAY;
                        fColor = Color.BLUE;
                        break;
                    case 3:
                        text = "3";
                        bColor = Color.GRAY;
                        fColor = Color.RED;
                        break;
                    case 4:
                        text = "4";
                        bColor = Color.GRAY;
                        fColor = Color.YELLOW;
                        break;
                    case 5:
                        text = "5";
                        bColor = Color.GRAY;
                        fColor = Color.GREEN;
                        break;
                    case 6:
                        text = "6";
                        bColor = Color.GRAY;
                        fColor = Color.BLUE;
                        break;
                    case 7:
                        text = "7";
                        bColor = Color.GRAY;
                        fColor = Color.ORANGE;
                        break;
                    case 8:
                        text = "8";
                        bColor = Color.GRAY;
                        fColor = Color.RED;
                        break;
                    default:
                        text = "";
                        bColor = Color.GRAY;
                        fColor = Color.GRAY;
                }
                this.labels[i][j].setFont(font);
                this.labels[i][j].setBackground(bColor);
                this.labels[i][j].setForeground(fColor);
                this.labels[i][j].setText(text);
                if (labels[i][j].getText().equals("*")) {
                    this.labels[i][j].setText("");
                    this.labels[i][j].setIcon(flowerIcon);
                }
            }
        }
        this.validate();
        this.repaint();
    }

    ///绘制雷
    @Deprecated
    private void drawBombs() {
        bombCount = Util.getUtil().getFlowersNum();
        int[] xNums = flower.getxNums();
        int[] yNums = flower.getyNums();
        for (int i = 0; i < bombCount; i++) {
            this.labels[xNums[i]][yNums[i]].setText("*");
            this.labels[xNums[i]][yNums[i]].setBackground(Color.darkGray);
            this.labels[xNums[i]][yNums[i]].setForeground(Color.red);
        }
    }

    ///绘制按钮
    private void initButtons() {
        FlowButton btn;
//        for(int i=0;i<xNums.length;i++)
//        {
//            System.out.println("xNums["+i+"]:"+xNums[i]);
//        }
//        for(int i=0;i<yNums.length;i++)
//        {
//            System.out.println("yNums["+i+"]:"+yNums[i]);
//        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
//                System.out.println("j"+j+"i:"+i);
                btn = new FlowButton(i, j,this);
                btn.setBounds(j * BLOCKWIDTH, i * BLOCKHIGH, BLOCKWIDTH, BLOCKHIGH);
                this.add(btn);
                buttons[i][j] = btn;
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(Util.getUtil().isFinishFirstClick()&&Util.getUtil().isSafeFirst())
                        {
                            if (!((FlowButton) e.getSource()).isHaveFlags()) {
                                open((FlowButton) e.getSource(), ((FlowButton) e.getSource()).isBomb());
                                if (Judge.getJudge().isWin()) {
                                    showAllPanelWithoutBomb();
                                    Judge.getJudge().showWin();
                                }
                            }
                        }else if(!Util.getUtil().isSafeFirst()){
                            if (!((FlowButton) e.getSource()).isHaveFlags()) {
                                open((FlowButton) e.getSource(), ((FlowButton) e.getSource()).isBomb());
                                if (Judge.getJudge().isWin()) {
                                    showAllPanelWithoutBomb();
                                    Judge.getJudge().showWin();
                                }
                            }
                        }
                    }
                });
            }
        }
        this.validate();
    }

    public void bindBombToBtn()
    {
        int xNums[] = flower.getxNums();
        int yNums[] = flower.getyNums();
        for(int i=0;i<bombCount;i++)
        {
            if(xNums[i]<rows)
                if(yNums[i]<cols)
                    buttons[xNums[i]][yNums[i]].setType(Flower.FLOWERS);
        }
    }
    public int[] returnSize()
    {
        int[] a={this.cols *BLOCKWIDTH+buttons[0][0].getPreferredSize().width,this.rows*BLOCKHIGH+buttons[0][0].getPreferredSize().height+20};
        return a;
    }

    private boolean verify(int row,int col)
    {
        return row >= 0 && row < this.rows && col>=0 && col <this.cols;
    }

    private boolean verifyBomb(int row,int col)
    {
        if(bombMap[row][col]==Flower.FLOWERS)
            return true;
        return false;
    }

    public void open(FlowButton btn,boolean isBomb)
    {
        int[][] bombs=flower.getFlowers();
        if(!btn.isBomb())
        {
            isBomb=false;
            Judge.getJudge().addEmptyNum();
        }
        btn.setVisible(false);
        switch(bombs[btn.getRow()][btn.getCol()])
        {
            case Flower.FLOWERS:
                if(isBomb)
                {
                    showAllPanel();
                    Judge.getJudge().showLose();
                }
                break;
            case 0:
         /*   case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":*/
                for(int[] off:offset)
                {
                    int newRow = btn.getRow() + off[0];
                    int newCol = btn.getCol() + off[1];
                    if(verify(newRow, newCol))
                    {
                        if(!verifyBomb(newRow,newCol )) {
                            FlowButton fb = buttons[newRow][newCol];
                            if (fb.isVisible()) {
                                open(fb, isBomb);
                            }
                        }
                    }
                }
                break;
            default:
        }
    }
    public void showAllPanel()
    {
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
            {
                buttons[i][j].setVisible(false);
            }
    }

    public void showAllPanelWithoutBomb()
    {
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++)
            {
                buttons[i][j].addActionListener(null);
                buttons[i][j].addMouseListener(null);
                if(!buttons[i][j].isBomb())
                    buttons[i][j].setVisible(false);
                else{
                    Image flowerImg = Toolkit.getDefaultToolkit().getImage(".\\flower.png");
                    Icon flowerIcon = new ImageIcon(flowerImg);
                    buttons[i][j].setIcon(flowerIcon);
                }
            }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroudImage, 0, 0, this);
    }
    public void repaintButtons()
    {
        System.out.println("正在重新绘制按钮");
        System.out.printf("参数列表：按钮（%d,%d），矩阵（%d,%d）\n",rows,cols,buttons[0].length,buttons[0].length);
        //检查按钮矩阵是否正确
        if(rows!=buttons[0].length)
        {
            buttons=new FlowButton[rows][cols];
            initButtons();
        }
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                buttons[i][j].repaint();
            }
        }
        System.out.println("重新绘制完成");
    }

    public void repaintLabels()
    {
        System.out.println("正在重新绘制格子");
        System.out.printf("参数列表：格子（%d,%d），矩阵（%d,%d）\n",rows,cols,labels[0].length,labels[0].length);
        if(rows!=labels[0].length)
        {
            labels=new JLabel[rows][cols];
            initLabels();
        }
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                labels[i][j].repaint();
            }
        }
        System.out.println("重新绘制完成");
    }

    public void resetPreferredSize()
    {
        this.setPreferredSize(new Dimension(BLOCKWIDTH*rows,BLOCKHIGH*cols));
    }
}
