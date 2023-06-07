package org.techtown.cominghome;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class DifficultyHardActivity extends AppCompatActivity {
    // 문제 목록
    private final List<Pair<String, Boolean>> questionList = new ArrayList<Pair<String, Boolean>>() {{
        add(new Pair<>("우리나라에서 동물유기는 동물보호법 제 11조에 의하여, 200만원 이하의 과태료가 부과된다.", false));
        add(new Pair<>("유기동물 문제 해결을 위해 우리나라에서는 ‘유기동물의 예방 및 관리에 관한 법률’을 시행하고 있다.", true));
        add(new Pair<>("유기동물보호 시설에서 유기동물을 분양하는 경우, 반드시 비용을 받아야 한다.", false));
        add(new Pair<>("유기동물 보호소에서는 모든 동물에게 새로운 가정을 찾아주는 것이 목적이다.", false));
        add(new Pair<>("모든 유기동물은 입양 후에 바로 집으로 갈 수 있다.", false));
    }};

    // 각 문제에 대한 풀이
    private final List<String> solutionList = new ArrayList<String>() {{
        add("우리나라에서 동물유기는 2008년부터 시행된 동물보호법 제 11조에 의하여, 300만원 이하의 과태료가 부과된다");
        add("우리나라에서는 유기동물 문제 해결을 위해 ‘유기동물의 예방 및 관리에 관한 법률’을 시행하고 있으며, 유기동물을 보호하고, 안락사를 방지하고, 입양 활성화를 위한 다양한 제도를 마련하고 있다.");
        add("유기동물보호법 제13조에 따르면, 유기동물보호 시설에서 분양하는 경우, 담당관청이 정하는 기준에 따라 비용을 받거나, 무상으로 분양할 수 있다.");
        add("유기동물 보호소에서는 입양뿐만 아니라 유기동물의 임시 보호, 치료 등을 모두 수행한다.");
        add("유기동물의 건강상태, 성격 등을 고려하여 입양 전에 사전조사와 입양자 교육 등을 거쳐야 한다.");
    }};

    private int currentQuestionIndex = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty_hard);
        TextView textQuestion = findViewById(R.id.text_question);
        Button buttonTrue = findViewById(R.id.button_true);
        Button buttonNext = findViewById(R.id.button_next);
        Button buttonFalse = findViewById(R.id.button_false);
        TextView textResult = findViewById(R.id.text_result);
        currentQuestionIndex = getIntent().getIntExtra("currentQuestionIndex", 0);
        final int questionCount = questionList.size();
        Pair<String, Boolean> currentQuestion = questionList.get(currentQuestionIndex);
        String question = currentQuestion.first;
        boolean answer = currentQuestion.second;
        textQuestion.setText(question);
        String questionNumberText = "[ " + (currentQuestionIndex + 1) + " / " + questionCount + " ]";
        ((TextView) findViewById(R.id.text_question_number)).setText(questionNumberText);
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer) {
                    textResult.setText(" <정답> ");
                    textResult.setGravity(Gravity.CENTER);
                    textResult.setVisibility(View.VISIBLE);
                    buttonNext.setVisibility(View.VISIBLE);
                    buttonTrue.setEnabled(false);
                    buttonFalse.setEnabled(false);
                } else {
                    String solution = solutionList.get(currentQuestionIndex);
                    textResult.setText("<오답>\n" + solution);
                    textResult.setGravity(Gravity.CENTER);
                    textResult.setVisibility(View.VISIBLE);
                    buttonNext.setVisibility(View.VISIBLE);
                    buttonTrue.setEnabled(false);
                    buttonFalse.setEnabled(false);
                }
            }
        });
        buttonFalse.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!answer) {
                    textResult.setText(" <정답> ");
                    textResult.setGravity(Gravity.CENTER);
                    ;
                    textResult.setVisibility(View.VISIBLE);
                    buttonNext.setVisibility(View.VISIBLE);
                    buttonTrue.setEnabled(false);
                    buttonFalse.setEnabled(false);
                } else {
                    String solution = solutionList.get(currentQuestionIndex);
                    textResult.setText("<오답>\n" + solution);
                    textResult.setGravity(Gravity.CENTER);
                    textResult.setVisibility(View.VISIBLE);
                    buttonNext.setVisibility(View.VISIBLE);
                    buttonTrue.setEnabled(false);
                    buttonFalse.setEnabled(false);
                }
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (currentQuestionIndex == questionCount - 1) {
                    Intent intent = new Intent(DifficultyHardActivity.this, QuizActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(DifficultyHardActivity.this, DifficultyHardActivity.class);
                    intent.putExtra("currentQuestionIndex", currentQuestionIndex + 1);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
