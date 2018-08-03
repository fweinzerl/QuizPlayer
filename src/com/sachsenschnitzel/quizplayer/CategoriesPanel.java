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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author schnitzel
 */
public class CategoriesPanel extends JPanel{
    //private Container parent;
    private Quiz quiz;
    
    public CategoriesPanel(Quiz quiz){
        //this.parent = parent;
        this.quiz = quiz;
        final int rows = 3;
        final int cols = 3;
        final int gap = 30;
        
        JPanel backPane = new JPanel(); //ouch
        backPane.setLayout(new FlowLayout(FlowLayout.TRAILING));
        JPanel mainPane = new JPanel();
        mainPane.setLayout(new GridLayout(rows, cols, gap, gap));
        mainPane.setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
        JPanel scorePane = new JPanel();
        scorePane.setLayout(new GridLayout(1, 2));
        super.setLayout(new BorderLayout());
        backPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        scorePane.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        super.add(backPane, BorderLayout.NORTH);
        super.add(mainPane, BorderLayout.CENTER);
        super.add(scorePane, BorderLayout.SOUTH);
        
        
        JButton btnBack = new JButton("X");
        btnBack.setBackground(new Color(0.9f, 0.1f, 0.1f));
        backPane.add(btnBack);
        btnBack.addActionListener((ActionEvent e) -> {
                Object[] options = {"Quit",
                                    "Stay"};
                int n = JOptionPane.showOptionDialog(this,
                    "Really Quit?",
                    "A Simple Question",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
                if(n==0){
                    Start.showStartPanel();
                }
            });
        
        JButton[] grid = new JButton[rows*cols];
        for(int i = 0; i < grid.length; i++){
            grid[i] = new JButton("<html><font size='18' color='white'>cat "+i+"</font></html>");
            grid[i].setBackground(new Color((196*i)%256, (int)(196*(i*2.4))%256, (int)(196*(i*0.6+1))%256));//(1-i*1.0f/grid.length, 0.1f, i*1.0f/grid.length));
            mainPane.add(grid[i]);
            final int index = i;
            grid[i].addActionListener((ActionEvent e) -> {
                Start.showQPanel(new QuestionPanel(quiz.getQuestion(index)));
            });
        }
        
        JPanel scorePaneLeft = new JPanel();
        JPanel scorePaneRight = new JPanel();
        scorePaneLeft.setLayout(new FlowLayout(FlowLayout.LEADING));
        scorePaneRight.setLayout(new FlowLayout(FlowLayout.TRAILING));
        scorePaneLeft.add(new JLabel("<html><font size='18' color='red'>Team 1</font></html>"));
        scorePaneRight.add(new JLabel("<html><font size='18' color='blue'>Team 2</font></html>"));
        scorePane.add(scorePaneLeft);
        scorePane.add(scorePaneRight);
    }
}
