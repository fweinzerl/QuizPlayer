/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sachsenschnitzel.quizplayer;

import com.sachsenschnitzel.quizplayer.Quiz.Question;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author schnitzel
 */
public class QuestionPanel extends JPanel{
    //private Container parent;
    private Question question;
    private boolean resolved, correct;
    private JToggleButton[] answers;
    
    public QuestionPanel(Question question){
        //this.parent = parent;
        this.question = question;
        resolved = false;
        String[] q_and_as = question.getQuestionAndAnswers();
        final int sections = 4;
        final int rows = 2;
        final int cols = 2;
        final int gap = 30;
        
        super.setLayout(new GridLayout(sections, 1));
        //mainPane.setBorder(BorderFactory.createEmptyBorder(gap, gap, gap, gap));
        
        JPanel[] column = new JPanel[sections];
        for(int i = 0; i < column.length; i++){
            column[i] = new JPanel();
            //column[i].setBackground(new Color(1-i*1.0f/column.length, 0.8f, i*1.0f/column.length));
            super.add(column[i]);
        }
        
        column[0].setLayout(new FlowLayout(FlowLayout.LEADING));
        JButton btnBack = new JButton("<-");
        JButton btnResolve = new JButton("!");
        btnBack.setBackground(new Color(0.1f, 0.1f, 0.9f));
        btnBack.setForeground(Color.WHITE);
        btnResolve.setBackground(new Color(0.1f, 0.1f, 0.9f));
        btnResolve.setForeground(Color.WHITE);
        column[0].add(btnBack);
        column[0].add(btnResolve);
        btnBack.addActionListener((ActionEvent e) -> {
                Start.showCatPanel();
        });
        btnResolve.addActionListener((ActionEvent e) -> {
                if(correct = answers[0].isSelected())
                    answers[0].setForeground(new Color(0.1f, 0.6f, 0.1f));
                else{
                    answers[0].setForeground(new Color(0.1f, 0.6f, 0.1f));
                    JToggleButton selected = (answers[1].isSelected())?answers[1]:
                                            ((answers[2].isSelected())?answers[2]:answers[3]);
                    selected.setForeground(Color.RED);
                }
                btnResolve.setEnabled(false);
                resolved=true;
            });
        
        JTextArea title = new JTextArea(
                q_and_as[0], 
                1, 
                18);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 30));
        title.setLineWrap(true);
        title.setWrapStyleWord(true);
        title.setEditable(false);
        title.setBackground(new Color(1f, 0.6f, 0.6f));
        title.setOpaque(false);
        title.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        column[1].add(title);
        
        
        column[2].setLayout(new GridLayout(rows, cols, gap, gap));
        column[2].setBorder(BorderFactory.createEmptyBorder(0, gap, 0, gap));
        
        ButtonGroup answersGroup = new ButtonGroup();
        answers = new JToggleButton[rows*cols];
        for(int i = 0; i < answers.length; i++){
            answers[i] = new JToggleButton(q_and_as[i+1]);
            answers[i].setFont(new Font(answers[i].getFont().getName(), Font.BOLD, 14));
            answersGroup.add(answers[i]);
            //answers[i].setBackground(new Color(0.1f, 1-i*1.0f/answers.length, i*1.0f/answers.length));
            //column[2].add(answers[i]);
        }
        
        //shuffle answers
        boolean[] added = new boolean[answers.length];
        for(int i = 0; i < added.length; i++){
            int pos = (int)(Math.random()*(added.length-i));
            int addIndex = 0; //counts to available position
            for(int j = 0; j < pos+1; j++, addIndex++)
                while(added[addIndex])
                    addIndex++;
            added[--addIndex] = true;
            //System.out.println("add at " + addIndex);
            column[2].add(answers[addIndex]);
        }
    }
    
    /**
     @return -1 if question was not answered.
     */
    public int hasAnsweredCorrectly(){
        if(!resolved)
            return -1;
        //else
        if(correct)
            return 1;
        else
            return 0;
    }
}
