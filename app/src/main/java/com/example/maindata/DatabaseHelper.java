package com.example.maindata;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //数据库版本号
    private static Integer Version = 1;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //上下文对象、数据库名称、一个可选的游标工厂（通常是Null）、当前数据库的版本（值必须是整数并且是递增的状态）

        //必须通过super()调用父类的构造函数
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context,String name,int version){
        this(context,name,null,version);
    }

    public DatabaseHelper(Context context,String name){
        this(context,name,Version);
    }



    //当数据库第一次创建时调用
    //用来创建数据库 表 以及初始化数据
    //SQLite支持的数据类型：整数、字符串、日期、二进制
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("创建数据库和表");
        //创建数据库一张表
        //通过execSQL()执行SQL语句（此处创建了一个名为person的表）
        String sql = "CreatE tAble user(id int primary key ,name varchar(200))";
        db.execSQL(sql);
        //这个方法还没调用，数据库实际上是没被创建的
        //直到getWritableDatabase()/getReadableDatabase()第一次被调用时才会进行创建/打开
    }


    //当数据库升级时则自动调用（版本发生变化时）
    //用来耿欣数据库表结构
    //创建SQLiteOpenHelper子类对象时，必须传入一个version参数
    //该参数=当前数据库版本，若该版本高于之前版本，就调用onUpgrade()
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //数据库、旧版本数据库、新版本数据库
        System.out.println("更新数据库版本为：" + newVersion);
    //    String sql = "alter table person add sex varchar(8)"
        //   db.execSQL(sql);
    }






}