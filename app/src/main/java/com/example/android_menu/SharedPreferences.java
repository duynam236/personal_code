package com.example.android_menu;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SharedPreferences  extends AppCompatActivity implements View.OnClickListener {
android.content.SharedPreferences sharedPreferences;

    android.content.SharedPreferences.Editor editor;

    EditText etData;
    Button btnSave;
    Button btnClear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preferences);

        initPreferences();

        etData = (EditText) findViewById(R.id.et_data);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnClear = (Button) findViewById(R.id.btn_clear);

        String savedData = sharedPreferences.getString("DATA", "");
        etData.setText(savedData);

        btnSave.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override

    public void onClick(View view) {
        if (view == btnSave) {
            // Nếu click vào nút Save, ta sẽ lưu dữ liệu lại.
            String data = etData.getText().toString();
            // "DATA" là key, data tham số thứ 2 là value.
            editor.putString("DATA", data);
            editor.commit();
            Toast.makeText(getApplicationContext(),"Click SAVE",Toast.LENGTH_SHORT).show();
        } else if (view == btnClear) {
            // Nếu click vào nút Clear, ta sẽ xoá dữ liệu đi.
            etData.setText("");
            editor.clear();
            editor.commit();
            Toast.makeText(getApplicationContext(),"Click CLEAR",Toast.LENGTH_SHORT).show();
        }
    }

    private void initPreferences() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }
}
