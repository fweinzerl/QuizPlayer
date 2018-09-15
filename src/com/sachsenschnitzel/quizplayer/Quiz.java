/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sachsenschnitzel.quizplayer;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author schnitzel
 */
public class Quiz{
    private String[] categories;
    private Question[][] questions;
    
    public Quiz(){
        categories = new String[9];
        questions = new Question[9][1];
        for(int i = 0; i < questions.length; i++){
            categories[i] = "cat "+i;
            questions[i][0] = new Question("Wenn hier einer was liegen lassen darf, dann is das wer?",
                    "Mama", "Ich!", "Ich, ich!", "Carolin");
        }
    }
    
    public Quiz(File source){
        categories = new String[9];
        questions = new Question[9][];
        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(source);

            doc.getDocumentElement().normalize();
            if(!doc.getDocumentElement().getNodeName().equals("quiz"))
                throw new IOException("File is not a quiz!");
            NodeList catList = doc.getElementsByTagName("category");
            
            for (int i = 0; i < catList.getLength(); i++) {
                Node categoryNode = catList.item(i);
                //System.out.println("\nQuiz? " + categoryNode.getNodeName());

                if (categoryNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element category = (Element) categoryNode;
                    //System.out.println("cat: " + category.getAttribute("name"));
                    categories[i] = category.getAttribute("name");
                    questions[i] = new Question[Integer.parseInt(category.getAttribute("questions"))];
                    
                    NodeList questionList = category.getElementsByTagName("question");
                    
                    for(int j = 0; j < questionList.getLength(); j++){
                        Node questionNode = questionList.item(j);
                        if (questionNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element question = (Element) questionNode;
                            //System.out.println("Q: " + question.getAttribute("text"));

                            NodeList answerList = category.getElementsByTagName("answer");
                            //System.out.println(((Element)answerList.item(0)).getAttribute("text"));
                            
                            questions[i][0] = new Question(question.getAttribute("text"),
                                    ((Element)answerList.item(0)).getAttribute("text"),
                                    ((Element)answerList.item(1)).getAttribute("text"),
                                    ((Element)answerList.item(2)).getAttribute("text"),
                                    ((Element)answerList.item(3)).getAttribute("text"));
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String getCategoryName(int category){
        return categories[category];
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
