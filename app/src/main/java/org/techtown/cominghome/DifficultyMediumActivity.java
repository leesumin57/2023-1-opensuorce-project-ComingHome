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

public class DifficultyMediumActivity extends AppCompatActivity {
    // 문제 목록
    private final List<Pair<String, Boolean>> questionList = new ArrayList<Pair<String, Boolean>>() {{
        add(new Pair<>("반려동물에게 초콜릿을 먹이면 안 된다.", true));
        add(new Pair<>("고양이는 차가운 물을 먹으면 위험하다.", false));
        add(new Pair<>("반려동물은 목욕을 자주 시켜주면 좋다.", false));
        add(new Pair<>("유기동물 보호 시설에서 수용하는 기간은 보호 시설마다 다르다.", true));
        add(new Pair<>("유기동물의 입양 시 건강진단 및 예방접종 비용은 입양자가 부담해야 한다.", true));
    }};

    // 각 문제에 대한 풀이
    private final List<String> solutionList = new ArrayList<String>() {{
        add("초콜릿은 강아지와 고양이 등의 동물에게 카카오 페인톨이나 카페인 같은 성분이 포함되어 있어서 위험하다. 이러한 성분은 반려동물의 심장, 신장 및 중추 신경계에 영향을 미칠 수 있다.");
        add("차가운 물은 고양이에게 위험하지 않다. 하지만, 냉동식품이나 차가운 음식은 소화가 어려워지며, 고양이의 소화기관에 문제를 일으킬 수 있어서 위험할 수 있다.");
        add("반려동물의 목욕은 자주 시키면 오히려 피부 문제를 일으킬 수 있다. 무리하게 자주 샤워를 시키면 반려동물의 털과 피부가 건조해져 가려움증이나 피부염 등의 문제를 유발할 수 있다.");
        add("유기동물 보호 시설에서 수용하는 기간은 보호 시설마다 다르며, 보호 시설의 종류, 지역, 상황에 따라 달라질 수 있다.");
        add("유기동물보호법 시행규칙 제11조에 따르면, 입양을 희망하는 자가 동물병원에서 건강진단을 받아야 하고, 그 비용은 입양자가 부담해야 한다.");
    }};

    private int currentQuestionIndex = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.difficulty_medium);

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
                    Intent intent = new Intent(DifficultyMediumActivity.this, QuizActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // 다음 문제로 이동
                    Intent intent = new Intent(DifficultyMediumActivity.this, DifficultyMediumActivity.class);
                    intent.putExtra("currentQuestionIndex", currentQuestionIndex + 1);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}