package com.afnan.sciencetrivia;

// This class contains all the questions, options and answers for the quiz.
//Getters are used to get these arrays to populate the QuestionsView fragment
public class Quiz {

    public String[] questions =
            {"What are the products of photosynthesis?",
                    "If an atom has 3 electrons and has a neutral charge, how many protons does it contain?",
                    "Which gas is given off when calcium carbonate is heated?",
                    "Why is iron rarely used as pure iron?",
                    "Why is aluminium used to make aeroplane bodies?"};

    public String[] option1 =
            {"Carbon dioxide and water",
                    "7",
                    "Helium",
                    "It is too brittle",
                    "It is aerodynamic"};
    public String[] option2 =
            {"Glucose and oxygen",
                    "2",
                    "Carbon dioxide",
                    "It is too shiny",
                    "It is cheaper than other materials"};
    public String[] option3 =
            {"Hydrogen",
                    "3",
                    "Oxygen",
                    "It is too expensive",
                    "Because why not"};
    public String[] option4 =
            {"Lactic acid",
                    "0",
                    "Methane",
                    "It is too heavy",
                    "It is very light"};
    public String[] Answer =
            {"Glucose and oxygen",
                    "3",
                    "Carbon dioxide",
                    "It is too brittle",
                    "It is very light"};

    public int score = 0;


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public String[] getQuestions() {
        return questions;
    }

    public void setQuestions(String[] questions) {
        this.questions = questions;
    }

    public String[] getOption1() {
        return option1;
    }

    public void setOption1(String[] option1) {
        this.option1 = option1;
    }

    public String[] getOption2() {
        return option2;
    }

    public void setOption2(String[] option2) {
        this.option2 = option2;
    }

    public String[] getOption3() {
        return option3;
    }

    public void setOption3(String[] option3) {
        this.option3 = option3;
    }

    public String[] getOption4() {
        return option4;
    }

    public void setOption4(String[] option4) {
        this.option4 = option4;
    }

    public String[] getAnswer() {
        return Answer;
    }

    public void setAnswer(String[] answer) {
        Answer = answer;
    }
}
