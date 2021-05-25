package com.example.bt1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String historyInstance = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText tmpText = (EditText) findViewById(R.id.intputTmp);
        final RadioGroup tempGroup = (RadioGroup) findViewById(R.id.tempRadGroup);
        final Button convButton = (Button) findViewById(R.id.conversionButton);
        convButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = tempGroup.getCheckedRadioButtonId();
                int id = 0;
                switch (checkedId){
                    case R.id.ftoc:
                        id = 1;break;
                    case R.id.ctof:
                        id = 2;break;
                }
                Conversion(id);
            }
        });
    }
    protected void Conversion(int id){
        TextView outTemp = (TextView) findViewById(R.id.outputTmp);
        TextView inptemp = (TextView) findViewById(R.id.intputTmp);
        double inpVal = Double.parseDouble((inptemp.getText().toString()));
        double tmpVal = 0;
        if (id == 1){
            tmpVal = (inpVal-32)*5.0/9.0;
            outTemp.setText(Double.toString(tmpVal));
            Log.d("Output",Double.toString(tmpVal));
            CreateHistory(inpVal,tmpVal,id);
        }
        else {
            tmpVal = (inpVal*9.0/5.0)+32;
            outTemp.setText(Double.toString(tmpVal));
            Log.d("Output",Double.toString(tmpVal));
            CreateHistory(inpVal,tmpVal,id);
        }
    }

    protected void CreateHistory(double inpVal, double outVal, int typeConv){
        TextView historyView = (TextView) findViewById(R.id.historyView);
        String history = historyView.getText().toString();
        if(typeConv == 1){
            String newHistory = "F to C:" + Double.toString(inpVal) + " -> " + Double.toString(outVal);
            history = newHistory + System.lineSeparator() + history;
            historyInstance = history;
            historyView.setText(history);
            Log.i("History", "CreateHistory: "+history);
        }
        else{
            String newHistory = "C to F:" + Double.toString(inpVal) + " -> " + Double.toString(outVal);
            history = newHistory + System.lineSeparator() + history;
            historyInstance = history;
            historyView.setText(history);
            Log.i("History", "CreateHistory: "+history);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("historyInstance",historyInstance);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);
        historyInstance = savedInstanceState.getString("historyInstance");
        TextView historyView = (TextView) findViewById(R.id.historyView);
        historyView.setText(historyInstance);
    }
}