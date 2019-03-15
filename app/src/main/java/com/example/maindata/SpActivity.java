package com.example.maindata;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SpActivity extends AppCompatActivity {
    private Button mbtn_save;
    private Button mbtn_display;
    private EditText edit_name;
    private EditText edit_age;
    private TextView text_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        mbtn_save = (Button) findViewById(R.id.Btn_Save);
        mbtn_display = (Button) findViewById(R.id.Btn_Display);
        edit_age = (EditText) findViewById(R.id.Edit_Age);
        edit_name = (EditText) findViewById(R.id.Edit_Name);
        text_1 = (TextView) findViewById(R.id.Btn_Tv);
        setlisteners();

        SharedPreferences sharedPreferences = getSharedPreferences("JOJO", Context.MODE_PRIVATE);
        String nameValue = sharedPreferences.getString("name","");
        int ageValue = sharedPreferences.getInt("age",1);
        edit_name.setText(nameValue);
        edit_age.setText(String.valueOf(ageValue));

    }
    private void setlisteners(){
        OnClick onClick = new OnClick();
        mbtn_save.setOnClickListener(onClick);
        mbtn_display.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
          //  MODE_WORLD_READABLE 模式已经被废弃。
          //  SharedPreferences sharedPreferences = getSharedPreferences("JOJO",Context.MODE_WORLD_READABLE+Context.MODE_WORLD_WRITEABLE);
            SharedPreferences sharedPreferences = getSharedPreferences("JOJO",Context.MODE_PRIVATE);

            switch (v.getId()){
                case R.id.Btn_Save:
                   String name = edit_name.getText().toString();
                   int age = Integer.parseInt(edit_age.getText().toString());
                   SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                    editor.putString("name",name);
                    editor.putInt("age", age);
                    editor.commit();
                    Toast.makeText(SpActivity.this,"保存成功",Toast.LENGTH_LONG).show();
                    break;
                case R.id.Btn_Display:
                    String nameValue = sharedPreferences.getString("name","");
                    int ageValue = sharedPreferences.getInt("age",1);
                    text_1.setText("姓名：" + nameValue + "，年龄： " + ageValue);
                    break;
                default:
                    break;


            }

        }
    }
}
