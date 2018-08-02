package com.sachsenschnitzel.quizplayer;

import java.awt.Container;
import javax.swing.JFrame;

public class Start{
    public static void main(String[] args){
        JFrame frame = new JFrame();
        Container c = frame.getContentPane();
        c.add(new QuestionPanel(c));
        
        frame.setTitle("QuizPlayer");
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
