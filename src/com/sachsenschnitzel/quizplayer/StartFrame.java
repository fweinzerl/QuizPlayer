/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sachsenschnitzel.quizplayer;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author schnitzel
 */
public class StartFrame extends JFrame{
    public StartFrame(){
        final int rows = 7;
        
        Container c = super.getContentPane();
        JPanel myPane = new JPanel();
        GridLayout gl = new GridLayout(rows, 1);
        myPane.setLayout(gl);
        //myPane.setBackground(Color.RED);
        c.add(myPane);
        
        JPanel[] grid = new JPanel[rows];
        for(int i = 0; i < grid.length; i++){
            grid[i] = new JPanel();
            //grid[i].setBackground(new Color(i*1.0f/rows, i*1.0f/rows, i*1.0f/rows));
            myPane.add(grid[i]);
        }
        
        JLabel title = new JLabel("Oag schweres Quiz!");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
        title.setBackground(new Color(0.6f, 0.6f, 0.9f));
        title.setOpaque(true);
        title.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        grid[1].add(title);
        grid[1].setAlignmentY(BOTTOM_ALIGNMENT);
        JTextField name1 = new JTextField(20);
        //GridLayout gl3 = new GridLayout(1, 2);
        //grid[3].setLayout(gl3);
        grid[3].add(new JLabel("Team 1: "));
        //JPanel help1 = new JPanel(); help1.setAlignmentX(TOP_ALIGNMENT); help1.setAlignmentY(TOP_ALIGNMENT);
        //help1.add(name1);
        grid[3].add(name1);
        JTextField name2 = new JTextField(20);
        grid[4].add(new JLabel("Team 2: "));
        grid[4].add(name2);
        JTextField fileField = new JTextField(17);
        grid[5].add(new JLabel("Quiz-Data: "));
        grid[5].add(fileField);
        JButton btnStartQuiz = new JButton("->");
        btnStartQuiz.setBackground(new Color(0f, 0f, 0.25f));
        btnStartQuiz.setForeground(Color.WHITE);
        grid[6].setLayout(new FlowLayout(FlowLayout.TRAILING));
        btnStartQuiz.setAlignmentX(CENTER_ALIGNMENT);
        grid[6].add(btnStartQuiz);
        
        super.setVisible(true);
        super.setSize(800, 600);
        super.setLocationRelativeTo(null);
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
