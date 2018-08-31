package com.sachsenschnitzel.quizplayer;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Start{
    private static Container contentPane;
    private static StartPanel startPanel;
    private static CategoriesPanel catPanel;
    private static QuestionPanel qPanel;
    
    public static void main(String[] args){
        JFrame frame = new JFrame();
        contentPane = frame.getContentPane();
        contentPane.add(startPanel = new StartPanel());
        
        frame.setTitle("QuizPlayer");
        frame.setVisible(true);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void showCatPanel(CategoriesPanel categoriesPanel){
        catPanel = categoriesPanel;
        //contentPane.add(catPanel);
        showCatPanel();
    }
    
    public static void showQPanel(QuestionPanel questionPanel){
        qPanel = questionPanel;
        //contentPane.add(qPanel);
        showQPanel();
    }
    
    public static void showStartPanel(){
        contentPane.remove(0);
        contentPane.add(startPanel);
        SwingUtilities.updateComponentTreeUI(contentPane);
    }
    
    public static void showCatPanel(){
        contentPane.remove(0);
        contentPane.add(catPanel);
        SwingUtilities.updateComponentTreeUI(contentPane);
        catPanel.resume();
        SwingUtilities.updateComponentTreeUI(contentPane);
    }
    
    public static void showQPanel(){
        contentPane.remove(0);
        contentPane.add(qPanel);
        SwingUtilities.updateComponentTreeUI(contentPane);
    }
}
