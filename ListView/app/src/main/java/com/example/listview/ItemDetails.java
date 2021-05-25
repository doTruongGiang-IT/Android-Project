package com.example.listview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemDetails extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView flag;
    private TextView population, area;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        flag = (ImageView) findViewById(R.id.flag);
        population = (TextView) findViewById(R.id.population);
        area = (TextView) findViewById(R.id.area);
        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            String nationInfo = bundle.getString("nationInfo");
            nationInfo = nationInfo.substring(1, nationInfo.length()-1);
            String[] details = nationInfo.split("\\s");
            for (String info : details) {
                if(info.contains("countryName=")) {
                    toolbar.setTitle(info);
                }
                if(info.contains("countryCode=")) {
                    info = info.substring(info.length()-3, info.length()-1);
                    // Khong set duoc hinh anh
//                    flag.setImageURI(Uri.parse("https://img.geonames.org/flags/x/"+ info +".gif"));
                    new DownloadImageTask((ImageView) findViewById(R.id.flag))
                            .execute("https://img.geonames.org/flags/x/"+ info +".gif");
                }
                if(info.contains("area=")) {
                    area.setText(info + "Km2");
                }
                if(info.contains("population=")) {
                    population.setText(info);
                }
            }
        }

    }
}