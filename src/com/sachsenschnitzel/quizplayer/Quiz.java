/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sachsenschnitzel.quizplayer;

import java.io.File;

/**
 *
 * @author schnitzel
 */
public class Quiz{
    private Question[][] questions;
    
    public Quiz(){
        questions = new Question[9][1];
        for(int i = 0; i < questions.length; i++)
            questions[i][0] = new Question("Wenn hier einer was liegen lassen darf, dann is das wer?",
                    "Mama", "Ich!", "Ich, ich!", "Carolin");
    }
    
    public Quiz(File source){
        questions = new Question[9][1];
        for(int i = 0; i < questions.length; i++)
            questions[i][0] = new Question("Was machst Du da?", "Bin schon weg",
                    "Nix", "Ich such was", "Kannst Du mir helfen?");
    }
    
    public Question getQuestion(int category){
        return questions[category][(int)(Math.random()*questions[category].length)];
    }

    /**
     * A Question can have four answers and the first one is always the right one.
     * This is also the way to write a quiz-xml-file.
     */
    public static class Question{
        private String[] questionAndAnswers;
        
        public Question(String... questionAndAnswers){
            if(questionAndAnswers.length==5)
                this.questionAndAnswers = questionAndAnswers;
        }
        
        public String[] getQuestionAndAnswers(){
            return questionAndAnswers;
        }
    }
}
