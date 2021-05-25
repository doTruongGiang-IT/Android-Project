package com.example.bai_tap_qua_trinh_1a_lan_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSubmit = findViewById(R.id.submit);
        RadioGroup rbSize = (RadioGroup) findViewById(R.id.radio_group);
        RadioGroup rbTortilla = findViewById(R.id.radio_group2);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
//              Radio Size
                try {
                    String selectedRadioValueS = ((RadioButton) findViewById(rbSize.getCheckedRadioButtonId())).getText().toString();
                    intent.putExtra("SizeSelected", selectedRadioValueS);
                } catch (NullPointerException msg) {
                    Toast.makeText(MainActivity.this, "Select Size", Toast.LENGTH_SHORT).show();
                    return;
                }
                ;
//              Radio Tortilla
                try {
                    String selectedRadioValueT = ((RadioButton) findViewById(rbTortilla.getCheckedRadioButtonId())).getText().toString();
                    intent.putExtra("TortillaSelected", selectedRadioValueT);
                } catch (NullPointerException msg) {
                    Toast.makeText(MainActivity.this, "Select Tortilla", Toast.LENGTH_SHORT).show();
                    return;
                }
                ;

//              Checkbox Fillings
                String text = "";
                CheckBox beef = findViewById(R.id.beef);
                CheckBox rice = findViewById(R.id.rice);
                CheckBox chicken = findViewById(R.id.chicken);
                CheckBox beans = findViewById(R.id.beans);
                CheckBox whitefish = findViewById(R.id.white_fish);
                CheckBox pico = findViewById(R.id.pico_de_pallo);
                CheckBox cheese = findViewById(R.id.cheese);
                CheckBox guacamle = findViewById(R.id.guacamole);
                CheckBox seafood = findViewById(R.id.sea_food);
                CheckBox lbt = findViewById(R.id.lbt);
                CheckBox[] fillings = new CheckBox[]{beef, rice, chicken, beans, whitefish, pico, cheese, guacamle, seafood, lbt};
                for (int i = 0; i < fillings.length; i++) {
                    if (fillings[i].isChecked()) {
                        text += fillings[i].getText() + ", ";
                    }
                }
                ;
                if (text.length() > 0)
                    text = text.substring(0, text.length() - 2);
                String fillingsCheckboxSelected = text.toString();
                intent.putExtra("fillingsCheckboxSelected", fillingsCheckboxSelected);

//              Checkbox Beverage
                CheckBox soda = findViewById(R.id.soda);
                CheckBox margarita = findViewById(R.id.margarita);
                CheckBox cerveza = findViewById(R.id.cerveza);
                CheckBox tequila = findViewById(R.id.tequila);
                CheckBox[] beverages = new CheckBox[]{soda, margarita, cerveza, tequila};

                String text2 = "";
                for (int i = 0; i < beverages.length; i++) {
                    if (beverages[i].isChecked()) {
                        text2 += beverages[i].getText() + ", ";
                    }
                }
                ;

                if (text2.length() > 0)
                    text2 = text2.substring(0, text2.length() - 2);

                intent.putExtra("beverageCheckboxSelected", text2);
//            Start Secondary class (send)
                startActivity(intent);
            }
        });

    }
}