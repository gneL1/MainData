package com.example.maindata;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

public class DataDisplay extends AppCompatActivity {
    Button btn_create;
    Button btn_update;
    Button btn_insert;
    Button btn_change;
    Button btn_query;
    Button btn_delete;
    Button btn_delete_base;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);
        btn_create = (Button) findViewById(R.id.Btn_Create);
        btn_update = (Button)findViewById(R.id.Btn_Update);
        btn_insert = (Button) findViewById(R.id.Btn_Insert);
        btn_change = (Button) findViewById(R.id.Btn_Change);
        btn_query = (Button) findViewById(R.id.Btn_Query);
        btn_delete = (Button) findViewById(R.id.Btn_Delete);
        btn_delete_base = (Button) findViewById(R.id.Btn_Delete_Base);
        setlisteners();
    }

    private void setlisteners(){
        OnClick onClick = new OnClick();
        btn_delete_base.setOnClickListener(onClick);
        btn_delete.setOnClickListener(onClick);
        btn_query.setOnClickListener(onClick);
        btn_change.setOnClickListener(onClick);
        btn_insert.setOnClickListener(onClick);
        btn_update.setOnClickListener(onClick);
        btn_create.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.Btn_Create:
                    //创建SQLiteOpenHelper子类对象
                    DatabaseHelper dbHelper = new DatabaseHelper(DataDisplay.this,"test_carson");
                    //创建或打开数据库
                    SQLiteDatabase sqLiteOpenHelper = dbHelper.getReadableDatabase();
               //     dbHelper.onCreate(sqLiteOpenHelper);

                    break;
                case R.id.Btn_Update:
                    DatabaseHelper dbHelper_updare = new DatabaseHelper(DataDisplay.this,"test_carson",2);
                    SQLiteDatabase sqLiteDatabase = dbHelper_updare.getReadableDatabase();
                    break;


                case R.id.Btn_Insert:
                    System.out.println("插入数据");
                    DatabaseHelper dbHelper1 = new DatabaseHelper(DataDisplay.this,"test_carson",2);
                    SQLiteDatabase sqLiteDatabase1 = dbHelper1.getWritableDatabase();

                    //创建ContentValues的对象
                    ContentValues values1 = new ContentValues();

                    //向该对象中插入键值对
                    values1.put("id",1);
                    values1.put("name","carson");
                    //key = 列名，  value = 插入的值
                    //key值只能是String类型，Value可存储基本数据类型和String类型



                    //调用insert()方法将数据插入到数据库中
                    sqLiteDatabase1.insert("user",null,values1);
                    //参数1：要操作的表名称
                    //参数2：SQL不允许空列，若ContentValues是空，那么这一列被明确的指明为NULL值
                    //参数3：ContentValues对象

                    //也可以采用SQL语句插入
                    //String sql = "insert into user (id,name) values (1,‘carson')";
                    //db.execSQL(sql);


                    //关闭数据库
                    sqLiteDatabase1.close();
                    break;

                case R.id.Btn_Query://除了“查询”，所有的数据库操作都可使用 SQL 语句
                    System.out.println("查询数据");

                    DatabaseHelper dbHelper4 = new DatabaseHelper(DataDisplay.this,"test_carson",2);

                    SQLiteDatabase sqLiteDatabase4 = dbHelper4.getReadableDatabase();

                    //调用SQLiteDatabase对象的query方法进行查询
                    //返回一个Curson对象：由数据库查询返回的结果集对象
                    Cursor cursor = sqLiteDatabase4.query("user",new String[]{"id","name"},"id=?",
                            new String[]{"1"},null,null,null);
                    //table:要操作的表
                    //columns:查询的列所有名称集
                    //selection:WHERE之后的条件语句，可以使用占位符
                    //groupBy:指定分组的列名
                    //having:指定分组条件，配合groupBy使用
                    //limit指定分页参数
                    //distinct可以指定"true"或"false"表示要不要过滤重复值

                    //所有方法将返回一个Cursor对象，代表数据集的游标

                    String id = null;
                    String name = null;

                    //将光标移动到下一行，从而判断该结果集是否还有下一条数据
                    //如果有则返回true,没有则返回false
                    while (cursor.moveToNext()){
                        id = cursor.getString(cursor.getColumnIndex("id"));
                        name = cursor.getString(cursor.getColumnIndex("name"));
                        //输出查询结果
                        System.out.println("查询到的数据时：" + "id: " + id + "  " + "name: " +name );
                    }

                    //关闭数据库
                    sqLiteDatabase4.close();
                    break;


                case R.id.Btn_Change:
                    System.out.println("修改数据");

                    DatabaseHelper dbHelper2 = new DatabaseHelper(DataDisplay.this,"test_carson",2);

                    SQLiteDatabase sqLiteDatabase2 = dbHelper2.getWritableDatabase();

                    //创建一个ContentValues对象
                    ContentValues values2 = new ContentValues();
                    values2.put("name","zhangsan");

                    //调用update方法修改数据库
                    sqLiteDatabase2.update("user",values2,"id=?",new String[]{"1"});
                    //参数1：表名（String)
                    //参数2：需要修改的ContentValues对象
                    //参数3：WhereBIAODASHI（String),需数据更新的行；若该参数为null，就会修改所有行;?号是占位符
                    //参数4：WHERE选择语句的参数（String[])，琢个替换WHERE表达式中的"?"占位符；

                    //对应SQL语句：
                    //String sql = "update [user] set name = 'zhangsan' where id='1'";
                    //db.execSQL(sql);


                    //关闭数据库
                    sqLiteDatabase2.close();
                    break;

                case R.id.Btn_Delete:
                    System.out.println("删除数据");

                    DatabaseHelper dbHelper3 = new DatabaseHelper(DataDisplay.this,"test_carson",2);

                    SQLiteDatabase sqLiteDatabase3 = dbHelper3.getWritableDatabase();

                    //删除数据
                    sqLiteDatabase3.delete("user","id=?",new String[]{"1"});
                    // 参数1：表名(String)
                    // 参数2：WHERE表达式（String），需删除数据的行； 若该参数为 null, 就会删除所有行；？号是占位符
                    // 参数3：WHERE选择语句的参数(String[]), 逐个替换 WHERE表达式中 的“？”占位符;

                    //对应SQL语句：
                    //String sql = "delete from user where id=’1‘"；
                    //db.execSQL(sql);


                    //关闭数据库
                    sqLiteDatabase3.close();

                    break;

                case R.id.Btn_Delete_Base:
                    System.out.println("删除数据库");

                    DatabaseHelper dbHelper5 = new DatabaseHelper(DataDisplay.this,"test_carson",2);

                    SQLiteDatabase sqLiteDatabase5 = dbHelper5.getReadableDatabase();

                    //删除名为test.db的数据库
                    deleteDatabase("test_carson");
                    break;

                default:
                    break;




            }


        }
    }




}
