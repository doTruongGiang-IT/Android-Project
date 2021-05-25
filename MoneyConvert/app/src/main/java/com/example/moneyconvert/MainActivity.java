package com.example.moneyconvert;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private Button btnConvert;
    private URL url;
    private Spinner spinnerSource;
    private Spinner spinnerDest;
    private EditText mSource;
    private EditText mDest;
    private ListView listView;
    private String description;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConvert = (Button) findViewById(R.id.button1);
        spinnerSource = (Spinner) findViewById(R.id.spinner1) ;
        spinnerDest = (Spinner) findViewById(R.id.spinner2);
        mSource = (EditText) findViewById(R.id.edittext1);
        mDest = (EditText) findViewById(R.id.edittext2);

        ArrayAdapter<Currency> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, CurrencyList.listCurrency);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinnerSource.setAdapter(adapter);
        spinnerDest.setAdapter(adapter);
        //listView = (ListView) findViewById(R.id.list);
        btnConvert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String sourceCur =  spinnerSource.getSelectedItem().toString().split(" ")[0].toLowerCase();
                String destCur =  spinnerDest.getSelectedItem().toString().split(" ")[0].toLowerCase();
                new CurrencyRSSQueryTask().execute(makeURL(sourceCur,destCur));
            }
        });
    }

    public class CurrencyRSSQueryTask extends AsyncTask<URL, Void, String>
    {
        @Override
        protected String doInBackground(URL... urls)
        {
            URL url = urls[0];
            String exchangeRate = null;
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser xmlPullParser = factory.newPullParser();
                xmlPullParser.setInput(url.openConnection().getInputStream(),"UTF-8");
                int event = xmlPullParser.getEventType();
                boolean inside = false;

                while(event != XmlPullParser.END_DOCUMENT && exchangeRate == null) {
                    if(event == XmlPullParser.END_TAG && xmlPullParser.getName().equalsIgnoreCase("item")) {
                        inside = false;
                    }
                    if(event == XmlPullParser.START_TAG && xmlPullParser.getName().equalsIgnoreCase("item")) {
                        inside = true;
                    }
                    if(event == XmlPullParser.START_TAG && inside && xmlPullParser.getName().equalsIgnoreCase("description")) {
                        exchangeRate = xmlPullParser.nextText();
                        //description = xmlPullParser.nextText();
                    }
                    event = xmlPullParser.next();
                }

            } catch (XmlPullParserException | IOException e) {
                e.printStackTrace();
            }
            return exchangeRate;
        }

        @Override
        protected void onPostExecute(String s)
        {
            String a = s.split("<")[0];
            String b = a.split("=")[0];
            String c = a.split("=")[1];

            Pattern p = Pattern.compile("\\d*\\.\\d+");
            Matcher m = p.matcher(a);
            float f1=0;
            float f2=0;
            while(m.find()) {
                f1 = Float.parseFloat(m.group());
                if (f1 != 0) {
                    f2 = Float.parseFloat(m.group());
                }
            }
            float exchanged = Float.parseFloat(mSource.getText().toString()) * f2 ;
            mDest.setText(String.valueOf(exchanged));


        }
    }

    public URL makeURL(String source, String des) {

        StringBuilder sb = new StringBuilder("https://");
        sb.append(source)
                .append(".")
                .append("fxexchangerate.com/")
                .append(des)
                .append(".xml");
        try {
            return new URL(sb.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

}