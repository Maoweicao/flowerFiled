package cn.liuxin.flowerFiled.View;

import cn.liuxin.flowerFiled.Util.Util;

import javax.swing.*;
import java.awt.event.*;
import java.util.IllegalFormatException;

public class OptionsDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton simpleRB;
    private JRadioButton normalRB;
    private JRadioButton hardRB;
    private JRadioButton expertRB;
    private JRadioButton customRB;
    private JComboBox rowNum;
    private JComboBox colNum;
    private JComboBox bombNum;
    private JCheckBox soundCB;
    private JCheckBox musicCB;
    private JCheckBox safeFirst;
    private JRadioButton smallGrid;
    private JRadioButton mediumGrid;
    private JRadioButton largeGrid;

    public OptionsDialog() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        if(Util.getUtil().getGridSize()==Util.GridSmall)
        {
            smallGrid.setSelected(true);
        }else{
            smallGrid.setSelected(false);
        }
        if(Util.getUtil().getGridSize()==Util.GridMedium)
        {
            mediumGrid.setSelected(true);
        }else{
            mediumGrid.setSelected(false);
        }
        if(Util.getUtil().getGridSize()==Util.GridLarge)
        {
            largeGrid.setSelected(true);
        }else{
            largeGrid.setSelected(false);
        }
        if(Util.getUtil().getDifficty()==Util.DifficutyEasy)
        {
            simpleRB.setSelected(true);
        }else{
            simpleRB.setSelected(false);
        }
        if(Util.getUtil().getDifficty()==Util.DifficutyNormal)
        {
            normalRB.setSelected(true);
        }else{
            normalRB.setSelected(false);
        }
        if(Util.getUtil().getDifficty()==Util.DifficutyHard)
        {
            hardRB.setSelected(true);
        }else{
            hardRB.setSelected(false);
        }
        if(Util.getUtil().getDifficty()==Util.DifficutyExpert)
        {
            expertRB.setSelected(true);
        }else{
            expertRB.setSelected(false);
        }
        if(Util.getUtil().getDifficty()==Util.DifficutyCustom)
        {
            customRB.setSelected(true);
        }else{
            customRB.setSelected(false);
        }
        rowNum.addItem("9");
        rowNum.addItem("16");
        rowNum.addItem("30");
        colNum.addItem("9");
        colNum.addItem("16");
        colNum.addItem("30");
        bombNum.addItem("10");
        bombNum.addItem("36");
        bombNum.addItem("54");
        bombNum.addItem("100");
        rowNum.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                String num=rowNum.getEditor().getItem().toString();
                try{
                    Integer.parseInt(num);
                }catch (NumberFormatException n)
                {
                    rowNum.setSelectedIndex(0);
                    JOptionPane.showMessageDialog(null, "输入不合法！请重新输入，已重置为9.");
                }
            }
        });
        try{
            rowNum.setSelectedItem(Integer.valueOf(Util.getUtil().getGridRow()).toString());
            colNum.setSelectedItem(Integer.valueOf(Util.getUtil().getGridCol()).toString());
            bombNum.setSelectedItem(Integer.valueOf(Util.getUtil().getFlowersNum()).toString());
        }catch (Exception e)
        {
            rowNum.setSelectedIndex(0);
            colNum.setSelectedIndex(0);
            bombNum.setSelectedIndex(0);
        }
        if(customRB.isSelected())
        {
            colNum.setEnabled(true);
            rowNum.setEnabled(true);
            bombNum.setEnabled(true);
        }else{
            colNum.setEnabled(false);
            rowNum.setEnabled(false);
            bombNum.setEnabled(false);
        }
        MouseListener difficutyFGroupML = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(customRB.isSelected())
                {
                    colNum.setEnabled(true);
                    rowNum.setEnabled(true);
                    bombNum.setEnabled(true);
                }else{
                    colNum.setEnabled(false);
                    rowNum.setEnabled(false);
                    bombNum.setEnabled(false);
                }
            }
        };
        simpleRB.addMouseListener(difficutyFGroupML);
        hardRB.addMouseListener(difficutyFGroupML);
        normalRB.addMouseListener(difficutyFGroupML);
        expertRB.addMouseListener(difficutyFGroupML);
        customRB.addMouseListener(difficutyFGroupML);
        if(Util.getUtil().isAllowMusic())
        {
            musicCB.setSelected(true);
        }else{
            musicCB.setSelected(false);
        }
        if(Util.getUtil().isAllowSound())
        {
            soundCB.setSelected(true);
        }else{
            soundCB.setSelected(false);
        }
        if(Util.getUtil().isSafeFirst())
        {
            safeFirst.setSelected(true);
        }else{
            safeFirst.setSelected(false);
        }
    }

    private void onOK() {
        // add your code here
        boolean allowDispose = true;
        if(simpleRB.isSelected())
        {
            Util.getUtil().setEasyMode();
        }else if(normalRB.isSelected())
        {
            Util.getUtil().setNomralMode();
        }else if(hardRB.isSelected())
        {
            Util.getUtil().setHardMode();
        }else if(expertRB.isSelected())
        {
            Util.getUtil().setExpertMode();
        }else if(customRB.isSelected())
        {
            try{
                int row=Integer.parseInt(rowNum.getSelectedItem().toString());
                int col=Integer.parseInt(colNum.getSelectedItem().toString());
                int bomb=Integer.parseInt(bombNum.getSelectedItem().toString());
                if(row*col<bomb)
                {
                    throw new RuntimeException();
                }
                Util.getUtil().setCustomMode(row,col,bomb);
            }catch (NumberFormatException e)
            {
                allowDispose=false;
                JOptionPane.showMessageDialog(null, "检测到非法数字，请检查游戏区域设置。已重置设定。");
                rowNum.setSelectedIndex(0);
                colNum.setSelectedIndex(0);
                bombNum.setSelectedIndex(0);
            }catch (RuntimeException r)
            {
                JOptionPane.showMessageDialog(null, "雷的数量大于格子数量，无法实现。已重置设定。");
                rowNum.setSelectedIndex(0);
                colNum.setSelectedIndex(0);
                bombNum.setSelectedIndex(0);
                allowDispose=false;
            }
        }

        if(smallGrid.isSelected())
        {
            Util.getUtil().setGirdSmall();
        }else if(mediumGrid.isSelected())
        {
            Util.getUtil().setGirdMedium();
        }else if(largeGrid.isSelected())
        {
            Util.getUtil().setGirdLarge();
        }

        if(safeFirst.isSelected())
        {
            Util.getUtil().setSafeFirst(true);
        }else{
            Util.getUtil().setSafeFirst(false);
        }

        if(musicCB.isSelected())
        {
            Util.getUtil().setAllowMusic(true);
        }else{
            Util.getUtil().setAllowMusic(false);
        }

        if(soundCB.isSelected())
        {
            Util.getUtil().setAllowSound(true);
        }else{
            Util.getUtil().setAllowSound(false);
        }
        if(allowDispose)
        {
            JOptionPane.showMessageDialog(this, "修改设定之后需要重新开始游戏才能使用新的设定。");
            dispose();
        }

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
