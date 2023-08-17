package com.example.android_menu;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
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

public class ExternalStorage extends Activity implements View.OnClickListener {
    private String filename = "externalStorage.txt";
    private String filepath = "Directory";
    File externalFile;
    EditText inputText;
    TextView responseText;
    Button saveBtn, displayBtn;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.external_storage);

        inputText = (EditText) findViewById(R.id.inputTextExternal);
        responseText = (TextView) findViewById(R.id.responseTextExternal);
        saveBtn = (Button) findViewById(R.id.btnSaveExternal);
        displayBtn = (Button) findViewById(R.id.btnDisplayExternal);

        saveBtn.setOnClickListener(this);
        displayBtn.setOnClickListener(this);

        if (isExternalStorageReadable() || !isExternalStorageWritable())
            saveBtn.setEnabled(false);
        else
            externalFile = new File(getExternalFilesDir(filepath), filename);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnSaveExternal) {
            try {
                FileOutputStream fos = new FileOutputStream(externalFile);
                fos.write(inputText.getText().toString().getBytes());
                fos.close();
                inputText.setText("");
                Toast.makeText(this, "Write to " + filename , Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (view.getId() == R.id.btnDisplayExternal) {
            try {
                FileInputStream fis = new FileInputStream(externalFile);
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

    private static boolean isExternalStorageReadable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState);
    }
    private static boolean isExternalStorageWritable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(extStorageState);
    }
}
