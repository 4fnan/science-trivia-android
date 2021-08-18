package com.afnan.sciencetrivia;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionsView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionsView extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuestionsView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Question1.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionsView newInstance(String param1, String param2) {
        QuestionsView fragment = new QuestionsView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_questions, container, false);
    }

    //Declared field variables
    private Button nxt;
    private Button opt1;
    private Button opt2;
    private Button opt3;
    private Button opt4;
    private TextView questionBox;
    Quiz quiz = new Quiz();
    private int index = 0;
    private int arrayLength = 0;
    private NavController nav;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        arrayLength = quiz.getQuestions().length - 1;

        //call to a method that randomises the questions and options index
        arrayRandomiser();


        nav = Navigation.findNavController(view);
        nxt = view.findViewById(R.id.btnNext);
        nxt.setVisibility(View.GONE);
        getQuestion(view);

        // When the next button is pressed this functions generates the buttons and enable them
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                getQuestion(view);
                opt1.setBackgroundResource(R.drawable.custom_optbtn);
                opt2.setBackgroundResource(R.drawable.custom_optbtn);
                opt3.setBackgroundResource(R.drawable.custom_optbtn);
                opt4.setBackgroundResource(R.drawable.custom_optbtn);
                opt1.setEnabled(true);
                opt2.setEnabled(true);
                opt3.setEnabled(true);
                opt4.setEnabled(true);

            }
        });

        //listeners for each option buttons that calls the check answer method
        // passing it the text of the buttons

        opt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer((String) opt1.getText(), "opt1");
            }
        });

        opt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer((String) opt2.getText(), "opt2");
            }
        });

        opt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer((String) opt3.getText(), "opt3");
            }
        });

        opt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer((String) opt4.getText(), "opt4");
            }
        });

    }

    // This method populates the questions and options into the TextView widget and buttons
    // by fetching the questions and options from the array
    public void getQuestion(View view) {

        if (index <= quiz.getQuestions().length - 1 &&
                index <= quiz.getOption1().length - 1 &&
                index <= quiz.getOption2().length - 1 &&
                index <= quiz.getOption3().length - 1 &&
                index <= quiz.getOption4().length - 1 &&
                index <= quiz.getAnswer().length - 1) {

            questionBox = view.findViewById(R.id.textViewQ);
            questionBox.setText(quiz.getQuestions()[index]);

            opt1 = view.findViewById(R.id.btnOpt1);
            opt1.setText(quiz.getOption1()[index]);

            opt2 = view.findViewById(R.id.btnOpt2);
            opt2.setText(quiz.getOption2()[index]);

            opt3 = view.findViewById(R.id.btnOpt3);
            opt3.setText(quiz.getOption3()[index]);

            opt4 = view.findViewById(R.id.btnOpt4);
            opt4.setText(quiz.getOption4()[index]);

            nxt = view.findViewById(R.id.btnNext);
            nxt.setVisibility(View.GONE);
        }

        else {
            //creates a new Bundle to put the score and passed as a parameter to the NavController
            //to retrieve it in the GameOver fragment activity to display the score.
            Bundle bundle = new Bundle();
            bundle.putInt("Score", quiz.score);
            nav.navigate(R.id.action_questionsView_to_gameOver, bundle);
        }
    }
//  checkAnswer method to check the answers and change the buttons to green or red depending on the answer
    public void checkAnswer(String answer, String selectedOption) {
        opt1.setEnabled(false);
        opt2.setEnabled(false);
        opt3.setEnabled(false);
        opt4.setEnabled(false);


        if (answer == quiz.getAnswer()[index]) {
            quiz.score = quiz.score +1;
            nxt.setVisibility(View.VISIBLE);
            if (selectedOption == "opt1") {
                opt1.setBackgroundResource(R.drawable.custom_optbtngreen);
            }

            if (selectedOption == "opt2") {
                opt2.setBackgroundResource(R.drawable.custom_optbtngreen);
            }

            if (selectedOption == "opt3") {
                opt3.setBackgroundResource(R.drawable.custom_optbtngreen);
            }

            if (selectedOption == "opt4") {
                opt4.setBackgroundResource(R.drawable.custom_optbtngreen);
            }

        }

        else {
            nxt.setVisibility(View.VISIBLE);
            if (selectedOption == "opt1") {
                opt1.setBackgroundResource(R.drawable.custom_optbtnred);
            }

            if (selectedOption == "opt2") {
                opt2.setBackgroundResource(R.drawable.custom_optbtnred);
            }

            if (selectedOption == "opt3") {
                opt3.setBackgroundResource(R.drawable.custom_optbtnred);
            }

            if (selectedOption == "opt4") {
                opt4.setBackgroundResource(R.drawable.custom_optbtnred);
            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    //Array randomiser to reorder questions and options order
    public void arrayRandomiser()
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = arrayLength- 1; i > 0; i--)

        {
            int index = rnd.nextInt(i + 1);

            String RandomQuestion = quiz.getQuestions()[index];
            quiz.getQuestions()[index] = quiz.getQuestions()[i];
            quiz.getQuestions()[i] = RandomQuestion;

            String optionA = quiz.getOption1()[index];
            quiz.getOption1()[index] = quiz.getOption1()[i];
            quiz.getOption1()[i] = optionA;

            String optionB = quiz.getOption2()[index];
            quiz.getOption2()[index] = quiz.getOption2()[i];
            quiz.getOption2()[i] = optionB;

            String optionC = quiz.getOption3()[index];
            quiz.getOption3()[index] = quiz.getOption3()[i];
            quiz.getOption3()[i] = optionC;

            String optionD = quiz.getOption4()[index];
            quiz.getOption4()[index] = quiz.getOption4()[i];
            quiz.getOption4()[i] = optionD;

            String answer = quiz.getAnswer()[index];
            quiz.getAnswer()[index] = quiz.getAnswer()[i];
            quiz.getAnswer()[i] = answer;
        }
    }


}