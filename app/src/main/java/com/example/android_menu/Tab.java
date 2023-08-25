package com.example.android_menu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;

public class Tab extends AppCompatActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.tab);
        TabHost tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec;
        spec = tabs.newTabSpec("tag1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("1-Clock");
        tabs.addTab(spec);
        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("2-Login");
        tabs.addTab(spec);
        tabs.setCurrentTab(0);
        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        tabs.addTab(spec);
        Button btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText txtPerson
                        = (EditText) findViewById(R.id.txtPerson);
                String theUser =
                        txtPerson.getText().toString();
                txtPerson.setText("Hola " + theUser);
            }
        });
    }
}

