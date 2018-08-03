/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sachsenschnitzel.quizplayer;

import com.sachsenschnitzel.quizplayer.Quiz.Question;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

/**
 *
 * @author schnitzel
 */
public class QuestionPanel extends JPanel{
    //private Container parent;
    private Question question;
    
    public QuestionPanel(Question question){
        //this.parent = parent;
        this.question = question;
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
            column[i].setBackground(new Color(1-i*1.0f/column.length, 0.8f, i*1.0f/column.length));
            super.add(column[i]);
        }
        
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
        title.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        column[1].add(title);
        
        
        column[2].setLayout(new GridLayout(rows, cols, gap, gap));
        column[2].setBorder(BorderFactory.createEmptyBorder(0, gap, 0, gap));
        
        JButton[] answers = new JButton[rows*cols];
        for(int i = 0; i < answers.length; i++){
            answers[i] = new JButton(q_and_as[i+1]);
            answers[i].setBackground(new Color(0.1f, 1-i*1.0f/answers.length, i*1.0f/answers.length));
            column[2].add(answers[i]);
        }
    }
}
