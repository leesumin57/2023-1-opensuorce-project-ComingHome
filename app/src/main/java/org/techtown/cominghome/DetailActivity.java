package org.techtown.cominghome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;

public class DetailActivity extends AppCompatActivity {
    center selectedCenter;
    public void call(View v){
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:043-201-2298"));
        startActivity(myIntent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSelectedAnimal();
        setValues();
    }

    private void setValues(){
        TextView tv = findViewById(R.id.center_detail_name);
        ImageView iv = findViewById(R.id.center_detail_image);

        tv.setText(selectedCenter.getName());
        iv.setImageResource(selectedCenter.getImage());
    }

    private void getSelectedAnimal(){
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        selectedCenter = CenterListActivity.centerList.get(Integer.valueOf(id));
    }

}