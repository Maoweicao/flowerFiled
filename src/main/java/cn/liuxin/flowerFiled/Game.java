package cn.liuxin.flowerFiled;
/*
Copyright 2019 Liuxin

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
import cn.liuxin.flowerFiled.Controller.Judge;
import cn.liuxin.flowerFiled.Controller.Music;
import cn.liuxin.flowerFiled.Controller.Sound;
import cn.liuxin.flowerFiled.Util.Util;
import cn.liuxin.flowerFiled.Model.Flower;
import cn.liuxin.flowerFiled.View.GamePanel;
import cn.liuxin.flowerFiled.View.OptionsDialog;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;


public class Game {
    static Flower flower;
    static JFrame w = new JFrame();
    static GamePanel mPanel;
    static JMenuBar menuBar;

    public static void main(String[] args) {
        //TODO：需要修复多次重启游戏之后，带有第一次避免算法的BUG
        flower = new Flower();
        initMenu();
        Util.getUtil().setSafeFirst(true);
        if(!Util.getUtil().isSafeFirst())
            flower.initFlowersMat();
        flower.printMat();
        Music.getMusic().setJxJfxPanel(mPanel);
        Sound.getSound().setJxJfxPanel(mPanel);
        mPanel = new GamePanel(Util.getUtil().getGridRow(), Util.getUtil().getGridCol(), Util.getUtil().getFlowersNum(), flower);
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new BorderLayout(0, 0));
        System.out.println("Size:" + w.getPreferredSize().width + "," + w.getPreferredSize().height);
        Container c = w.getContentPane();
        contentPanel.add(mPanel, BorderLayout.CENTER);
        c.add(contentPanel);
        System.out.println("ContentPanel:" + contentPanel.getPreferredSize().width + "," + contentPanel.getPreferredSize().height);
        System.out.println("MenuBar:" + menuBar.getPreferredSize().width + "," + menuBar.getPreferredSize().height);
        w.setSize(contentPanel.getPreferredSize().width + 6, contentPanel.getPreferredSize().height + 2 * menuBar.getPreferredSize().height + 6);
        w.setTitle("扫雷游戏");
        w.setResizable(false);
        w.setVisible(true);
        Judge.getJudge().setFrame(w);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new Thread(new Runnable() {

            public void run() {
                Music.getMusic().musicPlay();
            }
        }).start();
    }

    public static void initMenu() {
        menuBar = new JMenuBar();
        w.setJMenuBar(menuBar);

        JMenu gameMenu = createMenu("游戏", "g");
        gameMenu.setMnemonic(KeyEvent.VK_G);
        JMenuItem item;
        item = createMenuItem("新游戏", "n", null, KeyStroke.getKeyStroke("n"));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int op = JOptionPane.showConfirmDialog(w, "确定要开始新游戏吗？", "开启新的游戏", JOptionPane.YES_NO_OPTION);
                if (op == JOptionPane.YES_OPTION) {
                    Judge.getJudge().resetJudge();
                    gameRestart();
                }
            }
        });
        gameMenu.add(item);
        item = createMenuItem("选项", "s", null, KeyStroke.getKeyStroke("s"));

        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OptionsDialog dialog = new OptionsDialog();
                dialog.pack();
                dialog.setVisible(true);
            }
        });
        gameMenu.add(item);
        item = createMenuItem("退出", "e", null, KeyStroke.getKeyStroke("e"));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        gameMenu.add(item);
        JMenu aboutMenu = createMenu("关于", "a");
        aboutMenu.setMnemonic(KeyEvent.VK_A);
        aboutMenu.addMenuListener(new MenuListener() {
            public void menuSelected(MenuEvent e) {
                JOptionPane.showMessageDialog(w, "作者：猫尾草");
            }

            public void menuDeselected(MenuEvent e) {

            }

            public void menuCanceled(MenuEvent e) {

            }
        });
        menuBar.add(gameMenu);
        menuBar.add(aboutMenu);

    }

    private static JMenu createMenu(String name, String mnemonic) {
        /*
         * 根据名称和快捷键创建menu并添加到menuBar
         */
        JMenu menu = new JMenu(name);
        if (mnemonic != null)
            menu.setMnemonic(mnemonic.toCharArray()[0]);
        return menu;
    }

    private static JMenuItem createMenuItem(String name, String mnemonic, Icon icon, KeyStroke keyStroke) {
        /*
         * 根据名称和快捷键创建menu并添加到menuBar
         */
        JMenuItem menuItem = new JMenuItem(name, icon);
        if (mnemonic != null)
            menuItem.setMnemonic(mnemonic.toCharArray()[0]);
        if (keyStroke != null)
            menuItem.setAccelerator(keyStroke);
        return menuItem;
    }

    public static void gameRestart() {
        Music.getMusic().stopPlay();
        Util.getUtil().setFinishFirstClick(false);
            flower.initFlowersMat();
            flower.printMat();
            mPanel.init(flower);
        System.out.println("Size:" + w.getPreferredSize().width + "," + w.getPreferredSize().height);
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPanel.setLayout(new BorderLayout(0, 0));
        w.getContentPane().removeAll();
        mPanel.resetPreferredSize();
        contentPanel.add(mPanel, BorderLayout.CENTER);
        System.out.println("完成移除");
        w.getContentPane().add(contentPanel);
        System.out.println("完成添加");
        w.setSize(contentPanel.getPreferredSize().width + 6, contentPanel.getPreferredSize().height + 2 * menuBar.getPreferredSize().height + 6);
        w.repaint();
        mPanel.repaintButtons();
        mPanel.repaintLabels();
        if (Util.getUtil().isAllowMusic())
            Music.getMusic().musicPlay();
    }
}
