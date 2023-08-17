package com.example.android_menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class PopupMenuExample extends Activity implements PopupMenu.OnMenuItemClickListener {
    private Button btn01;
    private Button btn02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_menu);
        this.btn01 = findViewById(R.id.popup_menu_btn_01);
        this.btn02 = findViewById(R.id.popup_menu_btn_02);
        btn01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PopupMenu popup = new PopupMenu(PopupMenuExample.this, this.btn02);
//                popup.setOnMenuItemClickListener(PopupMenuExample.this);
//                popup.inflate(R.menu.popup_menu);
//                popup.show();
                handleBtn01Click();
            }
        });
    }

    private void handleBtn01Click() {
        PopupMenu popupMenu = new PopupMenu(this, this.btn02);
        popupMenu.inflate(R.menu.popup_menu);
//        Menu menu = popupMenu.getMenu();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return onMenuItemClick(item);
            }
        });
        popupMenu.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        int itemId = item.getItemId();
        if (itemId == R.id.popup_item01) {
            return true;
        } else if (itemId == R.id.popup_item02) {
            return true;
        } else if (itemId == R.id.popup_item03) {
            return true;
        }
        return false;
    }
}
