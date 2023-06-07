package org.techtown.cominghome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class CenterListActivity extends AppCompatActivity {
    private Button buttonHome, buttonBoard, buttonQuiz, buttonMypage;
    public static ArrayList<center> centerList =new ArrayList<center>();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centerlist);

        setUpData();
        setUpList();
        setUpOnClickListener();
        searchCenter();

        // bottom navigation buttons
        buttonHome = findViewById(R.id.button_home);
        buttonBoard = findViewById(R.id.button_board);
        buttonQuiz = findViewById(R.id.button_quiz);
        buttonMypage = findViewById(R.id.button_mypage);

        // set onClickListeners for bottom navigation buttons
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CenterListActivity.this, AnimalActivity.class);
                startActivity(intent);
            }
        });
        buttonBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CenterListActivity.this, CenterListActivity.class);
                startActivity(intent);
            }
        });
        buttonQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CenterListActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });
        buttonMypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CenterListActivity.this, MypageActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setUpData(){
        center cheongjue = new center("0", "청주 반려동물보호센터", R.drawable.cheongjue);
        centerList.add(cheongjue);
        center sejong = new center("1", "세종 유기동물보호센터", R.drawable.sejong);
        centerList.add(sejong);
        center busan1 = new center("2", "부산 하얀비둘기", R.drawable.busan1);
        centerList.add(busan1);
        center busan2 = new center("3", "부산 동물보호관리협회", R.drawable.busan2);
        centerList.add(busan2);
        center daejeon = new center("4", "대전 동물보호관리협회", R.drawable.daejeon);
        centerList.add(daejeon);
        center incheon1 = new center("5", "인천광역시 수의사회", R.drawable.incheon1);
        centerList.add(incheon1);
        center incheon2 = new center("6", "인천 가정동물병원", R.drawable.incheon2);
        centerList.add(incheon2);
        center jeju= new center("7", "제주 동물보호센터", R.drawable.jeju);
        centerList.add(jeju);
        center seoul1= new center("8", "서울 C.T종합동물병원", R.drawable.seoul1);
        centerList.add(seoul1);
        center seoul2= new center("9", "서울 동물복지지원센터", R.drawable.seoul2);
        centerList.add(seoul2);
        center seoul3= new center("10", "서울 한국동물구조관리협회", R.drawable.seoul3);
        centerList.add(seoul3);
        center seoul4= new center("11", "서울 서초동물사랑센터", R.drawable.seoul4);
        centerList.add(seoul4);
        center danyang= new center("12", "단양 유기동물보호소", R.drawable.danyang);
        centerList.add(danyang);
        center jechen= new center("13", "제천 동물보호센터", R.drawable.jechen);
        centerList.add(jechen);
        center chungju= new center("14", "충주 동물보호센터", R.drawable.chungju);
        centerList.add(chungju);
        center gongju= new center("15", "공주 동물보호소", R.drawable.gongju);
        centerList.add(gongju);
        center cheonan= new center("16", "천안 유기동물보호소", R.drawable.cheonan);
        centerList.add(cheonan);
        center jeonju= new center("17", "전주 아프리카동물병원", R.drawable.jeonju);
        centerList.add(jeonju);
        center gumi= new center("18", "구미 동물보호센터", R.drawable.gumi);
        centerList.add(gumi);
        center daegu= new center("19", "대구 유기동물보호협회", R.drawable.daegu);
        centerList.add(daegu);
        center ulsan= new center("20", "울산 유기동물보호센터", R.drawable.ulsan);
        centerList.add(ulsan);



    }

    private void setUpList(){

        listView = findViewById(R.id.center_listView);

        CenterAdapter adapter = new CenterAdapter(getApplicationContext(),0, centerList);
        listView.setAdapter(adapter);
    }

    private void setUpOnClickListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                center selectCenter = (center) listView.getItemAtPosition(position);
                Intent showDetail = new Intent(getApplicationContext(),DetailActivity.class);
                showDetail.putExtra("id", selectCenter.getId());
                startActivity(showDetail);
            }
        });
    }


    private void searchCenter(){
        SearchView searchView=findViewById(R.id.center_search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<center> filterCenter = new ArrayList<>();
                for (int i = 0; i< centerList.size(); i++){
                    center center = centerList.get(i);

                    if(center.getName().toLowerCase().contains(newText.toLowerCase())){
                        filterCenter.add(center);
                    }
                }
                CenterAdapter adapter = new CenterAdapter(getApplicationContext(),0, filterCenter);
                listView.setAdapter(adapter);
                return false;
            }
        });
    }




}