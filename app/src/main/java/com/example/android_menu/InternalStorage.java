package com.example.android_menu;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternalStorage extends Activity implements View.OnClickListener {
    private String filename = "internalStorage.txt";
    private String filepath = "Directory";
    File internalFile;
    EditText inputText;
    TextView responseText;
    Button saveBtn, displayBtn;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.internal_storage);

        inputText = (EditText) findViewById(R.id.inputText);
        responseText = (TextView) findViewById(R.id.responseText);
        saveBtn = (Button) findViewById(R.id.btnSave);
        displayBtn = (Button) findViewById(R.id.btnDisplay);

        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
        internalFile = new File(directory, filename);

        saveBtn.setOnClickListener(this);
        displayBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSave) {
            try {
                FileOutputStream fos = new FileOutputStream(internalFile);
                fos.write(inputText.getText().toString().getBytes());
                fos.close();
                inputText.setText("");
                Toast.makeText(this, "Write to " + filename, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (view.getId() == R.id.btnDisplay) {
            try {
                // Đọc file
                FileInputStream fis = new FileInputStream(internalFile);
                DataInputStream in = new DataInputStream(fis);
                BufferedReader br = new BufferedReader(new InputStreamReader(in));

                String strLine;
                String data = "";
                while ((strLine = br.readLine()) != null) {
                    data = data + "\r\n" + strLine;
                }
                in.close();

                responseText.setText(data);
                Toast.makeText(this, "Read from " + filename, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
