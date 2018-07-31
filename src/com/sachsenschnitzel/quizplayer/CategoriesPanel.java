/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sachsenschnitzel.quizplayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author schnitzel
 */
public class CategoriesPanel extends JPanel{
    public CategoriesPanel(){
        final int rows = 3;
        final int cols = 3;
        final int gap = 30;
        
        JPanel mainPane = new JPanel();
        mainPane.setLayout(new GridLayout(rows, cols, gap, gap));
        mainPane.setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
        JPanel scorePane = new JPanel();
        scorePane.setLayout(new GridLayout(1, 2));
        this.setLayout(new BorderLayout());
        this.add(mainPane, BorderLayout.CENTER);
        this.add(scorePane, BorderLayout.SOUTH);
        
        
        JButton[] grid = new JButton[rows*cols];
        for(int i = 0; i < grid.length; i++){
            grid[i] = new JButton("<html><font size='18' color='white'>cat "+i+"</font></html>");
            grid[i].setBackground(new Color(1-i*1.0f/grid.length, 0.1f, i*1.0f/grid.length));
            mainPane.add(grid[i]);
        }
        
        JPanel scorePaneLeft = new JPanel();
        JPanel scorePaneRight = new JPanel();
        scorePaneLeft.setLayout(new FlowLayout(FlowLayout.LEADING));
        scorePaneRight.setLayout(new FlowLayout(FlowLayout.TRAILING));
        scorePaneLeft.add(new JLabel("<html><font size='20' color='red'>left</font></html>"));
        scorePaneRight.add(new JLabel("<html><font size='20' color='blue'>right</font></html>"));
        scorePane.add(scorePaneLeft);
        scorePane.add(scorePaneRight);
        
        //super.setVisible(true);
    }
}
