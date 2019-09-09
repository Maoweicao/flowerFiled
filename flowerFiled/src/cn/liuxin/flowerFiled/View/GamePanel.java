package cn.liuxin.flowerFiled.View;

import cn.liuxin.flowerFiled.Controller.Judge;
import cn.liuxin.flowerFiled.Controller.Util;
import cn.liuxin.flowerFiled.Model.Flower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
    private int rows;
    private int cols;
    private int bombCount;
    private int[][] bombMap;
    private final int BLOCKWIDTH=50;
    private final int BLOCKHIGH=50;
    private JLabel[][] labels;
    private FlowButton[][] buttons;
    private Flower flower;
    private final int[][] offset={{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1},{-1,0}};
    private Image backgroudImage;



    public GamePanel(int rows, int cols, int bombCount,Flower flower) {
        this.rows = rows;
        this.cols = cols;
        this.setVisible(false);
        this.bombCount = bombCount;
        this.flower=flower;
        this.setLayout(null);
        labels=new JLabel[rows][cols];
        buttons=new FlowButton[rows][cols];
        bombMap=flower.getFlowers();
        backgroudImage=Toolkit.getDefaultToolkit().getImage(".\\001.jpg");
        initButtons();
        initLabels();
        drawMat();

        this.repaint();
        this.setVisible(true);
    }

    //初始化标签
    private void initLabels()
    {
        for(int i=0;i<this.rows;i++)
        {
            for(int j=0;j<this.cols;j++)
            {
                JLabel l = new JLabel("",JLabel.CENTER);
                l.setBounds(j*BLOCKWIDTH,i*BLOCKHIGH,BLOCKWIDTH,BLOCKHIGH);
                l.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                l.setOpaque(true);
                l.setBackground(Color.GRAY);
                this.add(l);
                labels[i][j]=l;
            }

        }
    }
    //绘制数组
    private void drawMat()
    {
        Font font = new Font("微软雅黑",Font.BOLD,30);
        int[][] mat=flower.getFlowers();
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                String text;
                Color bColor,fColor;
                switch (mat[i][j])
                {
                    case Flower.FLOWERS:
                        text="*";
                        bColor=Color.DARK_GRAY;
                        fColor=Color.RED;
                        break;
                    case 1:
                        text="1";
                        bColor=Color.GRAY;
                        fColor=Color.GREEN;
                        break;
                    case 2:
                        text="2";
                        bColor=Color.GRAY;
                        fColor=Color.BLUE;
                        break;
                    case 3:
                        text="3";
                        bColor=Color.GRAY;
                        fColor=Color.RED;
                        break;
                    case 4:
                        text="4";
                        bColor=Color.GRAY;
                        fColor=Color.YELLOW;
                        break;
                    case 5:
                        text="5";
                        bColor=Color.GRAY;
                        fColor=Color.GREEN;
                        break;
                    case 6:
                        text="6";
                        bColor=Color.GRAY;
                        fColor=Color.BLUE;
                        break;
                    case 7:
                        text="7";
                        bColor=Color.GRAY;
                        fColor=Color.ORANGE;
                        break;
                    case 8:
                        text="8";
                        bColor=Color.GRAY;
                        fColor=Color.RED;
                        break;
                    default:
                        text="";
                        bColor=Color.GRAY;
                        fColor=Color.GRAY;
                }
                this.labels[i][j].setFont(font);
                this.labels[i][j].setBackground(bColor);
                this.labels[i][j].setForeground(fColor);
                this.labels[i][j].setText(text);
            }
        }
    }
    ///绘制雷
    @Deprecated
    private void drawBombs()
    {
        bombCount= Util.getUtil().getFlowersNum();
        int[] xNums=flower.getxNums();
        int[] yNums=flower.getyNums();
        for(int i=0;i<bombCount;i++)
        {
                this.labels[xNums[i]][yNums[i]].setText("*");
                this.labels[xNums[i]][yNums[i]].setBackground(Color.darkGray);
                this.labels[xNums[i]][yNums[i]].setForeground(Color.red);
        }
    }

    ///绘制按钮
    private void initButtons()
    {
        FlowButton btn;
        int xNums[]=flower.getxNums();
        int yNums[]=flower.getyNums();
//        for(int i=0;i<xNums.length;i++)
//        {
//            System.out.println("xNums["+i+"]:"+xNums[i]);
//        }
//        for(int i=0;i<yNums.length;i++)
//        {
//            System.out.println("yNums["+i+"]:"+yNums[i]);
//        }
        for(int i=0;i<rows;i++) {
            for (int j = 0; j < cols; j++) {
//                System.out.println("j"+j+"i:"+i);
                btn = new FlowButton(i, j);
                btn.setBounds(j*BLOCKWIDTH, i*BLOCKHIGH, BLOCKWIDTH, BLOCKHIGH);
                this.add(btn);
                buttons[i][j]=btn;
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!((FlowButton)e.getSource()).isHaveFlags())
                        {
                            open((FlowButton)e.getSource(),((FlowButton) e.getSource()).isBomb());
                            if(Judge.getJudge().isWin())
                            {
                                Judge.getJudge().showWin();
                            }
                        }
                    }
                });
            }
        }
        for(int i=0;i<bombCount;i++)
        {
            if(xNums[i]<rows)
                if(yNums[i]<cols)
                    buttons[xNums[i]][yNums[i]].setType(Flower.FLOWERS);
        }
    }
    public int[] returnSize()
    {
        int[] a={this.cols * BLOCKWIDTH+10,this.rows*BLOCKHIGH+40};
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

    private void open(FlowButton btn,boolean isBomb)
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
                    for(int i=0;i<rows;i++)
                        for(int j=0;j<cols;j++)
                        {
                            buttons[i][j].setVisible(false);
                        }
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroudImage, 0, 0, this);
    }
}
