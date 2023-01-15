package com.example.quranicverses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;
import java.net.URI;

public class MainActivity extends AppCompatActivity {
    EditText surah_num;
    EditText verse_num;
    Button submit,commit;
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surah_num=findViewById(R.id.surah_val);
        verse_num=findViewById(R.id.verse_val);
        submit=findViewById(R.id.submit);
        txtView=findViewById(R.id.Show_verse);
        commit=findViewById(R.id.commit);
        QDH q1=new QDH();
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri webpage = Uri.parse("https://github.com/saliha-qaisar/QuranicVerses/pulse/monthly");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(intent);

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(surah_num.getText())) {
                    surah_num.setError("Enter Surah Number to see the verse");
                    int i=q1.SSP[0];
                    txtView.setText(String.valueOf(i));

                } else if (TextUtils.isEmpty(verse_num.getText())) {
                    verse_num.setError("Enter Verse Number to see the verse");
                }
                else if(Integer.parseInt(surah_num.getText().toString())>114||Integer.parseInt(surah_num.getText().toString())<=0)
                {
                    surah_num.setError("Enter Surah Number from 1-114");
                }
                else
                {
                    int sN=Integer.parseInt(surah_num.getText().toString());
                    int startingIndex;
                    int count;
                    sN=sN-1;
                    startingIndex=q1.SSP[sN];

                    count=q1.surahAyatCount[sN];
                    System.out.println(count);
                    if(Integer.parseInt(verse_num.getText().toString())<=0||Integer.parseInt(verse_num.getText().toString())>count)
                    {
                        verse_num.setError("Enter Verse Number from 1 to "+ count);
                    }
                    else
                    {
                        QuranArabicText qt=new QuranArabicText();
                        int vN=Integer.parseInt(verse_num.getText().toString());
                        vN=vN-1;
                        int indexOfQP=startingIndex+vN;
                        String verse=qt.QuranArabicText[indexOfQP];
                        txtView.setText(verse);
                    }


                }


                ;
            }
        });
    }
}