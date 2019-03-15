package com.example.maindata;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URI;
import java.sql.BatchUpdateException;

public class ContentDisplay extends AppCompatActivity {
    Button mbtn_user1;
    Button mbtn_user2;
    Button mbtn_job1;
    Button mbtn_job2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_display);
        mbtn_job1 = (Button)findViewById(R.id.Btn_Job1);
        mbtn_job2 = (Button)findViewById(R.id.Btn_Job2);
        mbtn_user1 = (Button)findViewById(R.id.Btn_User1);
        mbtn_user2 = (Button)findViewById(R.id.Btn_User2);
        setlisteners();
    }


    private void setlisteners(){
        OnClick onClick = new OnClick();
        mbtn_user1.setOnClickListener(onClick);
        mbtn_user2.setOnClickListener(onClick);
        mbtn_job1.setOnClickListener(onClick);
        mbtn_job2.setOnClickListener(onClick);

    }



    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.Btn_User1:
                    //设置URI
                    Uri uri_user = Uri.parse("content://com.example.maindata.myprovider/user");

                    //插入表中数据
                    ContentValues values = new ContentValues();
                    values.put("_id",3);
                    values.put("name","lverson");
                    //获取ContentResolver
                    ContentResolver resolver = getContentResolver();


                    //通过ContentResolver向ContentProvider中插入数据
                    resolver.insert(uri_user,values);

                    //通过ContentResolver向ContentProvider中查询数据
                    Cursor cursor = resolver.query(uri_user,new String[]{"_id","name"},null,null,null);

                    while (cursor.moveToNext()){
                        System.out.println("querybook:" + cursor.getInt(0) + " " + cursor.getString(1));
                        //将表中数据全部输出
                    }
                    cursor.close();
                    //关闭游标
                    break;

                case R.id.Btn_Job1:
                    Uri uri_job = Uri.parse("content://com.example.maindata.myprovider/job");

                    ContentValues values2 = new ContentValues();
                    values2.put("_id",3);
                    values2.put("job","NBA Player");

                    ContentResolver resolver2 = getContentResolver();

                    resolver2.insert(uri_job,values2);

                    Cursor cursor2 = resolver2.query(uri_job,new String[]{"_id","job"},null,null,null);
                    while (cursor2.moveToNext()){
                        System.out.println("queryjob:" + cursor2.getInt(0) + "" + cursor2.getString(1));
                    }

                    cursor2.close();
                    break;
                 default:
                     break;
            }

        }
    }




}
