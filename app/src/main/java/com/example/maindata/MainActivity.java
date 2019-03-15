package com.example.maindata;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mbtn1;
    Button mbtn2;
    Button mbtn3;
    Button mbtn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtn1 = (Button) findViewById(R.id.Btn_1);
        mbtn2 = (Button) findViewById(R.id.Btn_2);
        mbtn3 = (Button) findViewById(R.id.Btn_3);
        mbtn4 = (Button) findViewById(R.id.Btn_4);
        setlisteners();
    }

    private void setlisteners(){
        OnClick onClick = new OnClick();
        mbtn1.setOnClickListener(onClick);
        mbtn2.setOnClickListener(onClick);
        mbtn3.setOnClickListener(onClick);
        mbtn4.setOnClickListener(onClick);
    }


    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = null;

            switch (v.getId()) {
                case R.id.Btn_1:
                    intent = new Intent(MainActivity.this,DataDisplay.class);
                    break;
                case R.id.Btn_2:
                    intent = new Intent(MainActivity.this, ContentDisplay.class);
                    break;
                case R.id.Btn_3:
                    intent = new Intent(MainActivity.this,SpActivity.class);
                    break;
                case R.id.Btn_4:
                    intent = new Intent(MainActivity.this,VpActivity.class);
                    break;
                default:
                    break;

            }
            startActivity(intent);
        }
    }


}
