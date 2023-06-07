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

public class DifficultyEasyActivity extends AppCompatActivity {
    // 문제 목록
    private final List<Pair<String, Boolean>> questionList = new ArrayList<Pair<String, Boolean>>() {{
        add(new Pair<>("유기동물을 입양한 후에는 적절한 관리와 돌봄이 필요하다.", true));
        add(new Pair<>("유기동물이란, 주인 없이 길거리나 보호소에 버려진 동물을 말한다.", true));
        add(new Pair<>("유기동물 보호소는 주인을 찾을 수 없는 동물을 임시로 돌보고,새로운 주인을 찾는데 도움을 준다.", true));
        add(new Pair<>("유기동물은 반려동물에 비해 건강상의 문제가 별로 없다.", false));
        add(new Pair<>("유기동물 문제는 사회적인 문제로서, 우리 모두 해결해야 할 문제이다.", true));
    }};

    // 각 문제에 대한 풀이
    private final List<String> solutionList = new ArrayList<String>() {{
        add("유기동물을 입양한 후에는 적절한 관리와 돌봄이 필요하다. 충분한 물과 음식을 제공하고, 꾸준한 운동과 정기적인 건강검진을 받을 수 있도록 해야한다.");
        add("유기동물이란 주인이 없어서 길거리나 보호소에 버려진 동물을 의미한다.");
        add("유기동물 보호소는 주인을 찾을 수 없는 동물들을 임시로 돌보고, 새로운 주인을 찾는데 도움을 주는 곳이다.");
        add("유기동물은 주인이 없어서 반려동물보다 영양상이나 건강상의 문제가 있을 확률이 높다.");
        add("유기동물 문제는 사회적인 문제로 여겨져서 우리 모두가 해결해야 할 문제이다.");
    }};

    private int currentQuestionIndex = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty_easy);

        TextView textQuestion = findViewById(R.id.text_question);
        Button buttonTrue = findViewById(R.id.button_true);
        Button buttonFalse = findViewById(R.id.button_false);
        Button buttonNext = findViewById(R.id.button_next);
        TextView textResult = findViewById(R.id.text_result);

        // 이전 Activity에서 인덱스를 전달받음
        currentQuestionIndex = getIntent().getIntExtra("currentQuestionIndex", 0);

        // 문제 개수
        final int questionCount = questionList.size();

        // 현재 문제와 정답
        Pair<String, Boolean> currentQuestion = questionList.get(currentQuestionIndex);
        String question = currentQuestion.first;
        boolean answer = currentQuestion.second;

        // 문제와 정답을 표시
        textQuestion.setText(question);

        // 현재 문제 번호와 남은 문제 수 표시
        String questionNumberText = "[ " + (currentQuestionIndex + 1) + " / " + questionCount+" ]";
        ((TextView) findViewById(R.id.text_question_number)).setText(questionNumberText);

        // O/X 버튼 클릭 시 실행될 코드
        buttonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (answer) {
                    // 정답일 경우
                    textResult.setText(" <정답> ");
                    textResult.setGravity(Gravity.CENTER);
                    textResult.setVisibility(View.VISIBLE);
                    buttonNext.setVisibility(View.VISIBLE);
                    buttonTrue.setEnabled(false);
                    buttonFalse.setEnabled(false);
                } else {
                    // 오답일 경우
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
            @Override
            public void onClick(View v) {
                if (!answer) {
                    // 정답일 경우
                    textResult.setText(" <정답> ");
                    textResult.setGravity(Gravity.CENTER);;
                    textResult.setVisibility(View.VISIBLE);
                    buttonNext.setVisibility(View.VISIBLE);
                    buttonTrue.setEnabled(false);
                    buttonFalse.setEnabled(false);
                } else {
                    // 오답일 경우
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

        // 다음 문제 버튼 클릭 시 실행될 코드
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex == questionCount - 1) {
                    // 마지막 문제일 경우
                    Intent intent = new Intent(DifficultyEasyActivity.this, QuizActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // 다음 문제로 이동
                    Intent intent = new Intent(DifficultyEasyActivity.this, DifficultyEasyActivity.class);
                    intent.putExtra("currentQuestionIndex", currentQuestionIndex + 1);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
