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
    private String name1, name2;
    private int score1, score2;    
    private int whosTurn; //0: name1's turn; 1: name2's turn
    private Quiz quiz;
    private QuestionPanel prevQPanel; //QPanel, that was just on display
    private int prevCategory;
    private int round;
    
    private JButton[] grid;
    private JLabel labelLeft, labelRight;
    
    public CategoriesPanel(Quiz quiz, String name1, String name2){
        this.quiz = quiz;
        this.name1 = name1;
        this.name2 = name2;
        score1 = score2 = round = 0;
        whosTurn = (int)(Math.random()*2);
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
                Object[] options = {"Wirklich beenden.",
                                    "Bleiben"};
                int n = JOptionPane.showOptionDialog(this,
                    "Wirklich beenden?",
                    "Eine einfache Frage",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]);
                if(n==0){
                    Start.showStartPanel();
                }
            });
        
        JPanel scorePaneLeft = new JPanel();
        JPanel scorePaneRight = new JPanel();
        scorePaneLeft.setLayout(new FlowLayout(FlowLayout.LEADING));
        scorePaneRight.setLayout(new FlowLayout(FlowLayout.TRAILING));
        labelLeft = new JLabel(name1 + ": 0");
        labelLeft.setForeground(Color.RED);
        scorePaneLeft.add(labelLeft);
        labelRight = new JLabel(name2 + ": 0");
        scorePaneRight.add(labelRight);
        labelRight.setForeground(Color.BLUE);
        if(whosTurn==0){
            labelLeft.setFont(new Font(labelLeft.getFont().getName(), Font.BOLD, 26));
            labelRight.setFont(new Font(labelRight.getFont().getName(), Font.PLAIN, 20));
        } else {
            labelLeft.setFont(new Font(labelLeft.getFont().getName(), Font.PLAIN, 20));
            labelRight.setFont(new Font(labelRight.getFont().getName(), Font.BOLD, 26));
        }
        scorePane.add(scorePaneLeft);
        scorePane.add(scorePaneRight);
        
        grid = new JButton[rows*cols];
        for(int i = 0; i < grid.length; i++){
            grid[i] = new JButton("<html><font color='white'>"
                    + quiz.getCategoryName(i)+"</font></html>");
            grid[i].setFont(new Font(grid[i].getFont().getName(), Font.PLAIN, 18));
            grid[i].setBackground(new Color((196*i)%256, (int)(196*(i*2.4))%256, (int)(196*(i*0.6+1))%256));
            mainPane.add(grid[i]);
            final int index = i;
            grid[i].addActionListener((ActionEvent e) -> {
                prevQPanel = new QuestionPanel(quiz.getQuestion(index));
                prevCategory = index;
                Start.showQPanel(prevQPanel);
            });
        }
    }
    
    /**
     Only call this after a QuestionPanel has been shown
     */
    public void resume(){
        int answered = (prevQPanel != null)?prevQPanel.hasAnsweredCorrectly():-1;
        if(answered >= 0){
            if(answered == 1)
                if(whosTurn==0)
                    score1++;
                else
                    score2++;
            labelLeft.setText(name1+": "+score1);
            labelRight.setText(name2+": "+score2);
            
            whosTurn = (whosTurn+1)%2;
            if(whosTurn==0){
                labelLeft.setFont(new Font(labelLeft.getFont().getName(), Font.BOLD, 26));
                labelRight.setFont(new Font(labelRight.getFont().getName(), Font.PLAIN, 20));
            } else {
                labelLeft.setFont(new Font(labelLeft.getFont().getName(), Font.PLAIN, 20));
                labelRight.setFont(new Font(labelRight.getFont().getName(), Font.BOLD, 26));
            }
            grid[prevCategory].setEnabled(false);
            grid[prevCategory].setBackground(Color.LIGHT_GRAY);
            grid[prevCategory].setForeground(Color.DARK_GRAY);
        }
        
        
        if(round==9){
            String winText;
            if(score1 > score2)
                winText = name1.toUpperCase() + " HAT GEWONNEN";
            else if(score2 > score1)
                winText = name2.toUpperCase() + " HAT GEWONNEN";
            else
                winText = "Unentschieden! Nächstes Mal vielleicht.";
            Object[] options = {"Nochmal!",
                                    "Beenden"};
            int n = JOptionPane.showOptionDialog(this,
                winText,
                "Glückwunsch!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
            if(n==0)
                Start.showStartPanel();
            else
                System.exit(0);
        }
        
        round++;
    }
}
