package com.example.maindata;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyProvider extends ContentProvider {

    private Context mContext;
    ContentBaseHelper mDBHelper = null;
    SQLiteDatabase db = null;

    //设置ContentProvider的唯一标识符
    public static final String AUTOHORIYY = "com.example.maindata.myprovider";

    public static final int User_Code = 1;
    public static final int Job_Code = 2;

    //UriMatcher类使用：在ContentProvider中注册URI
    //                根据URI匹配ContentProvider中对应的数据表
    private static final UriMatcher mMatcher;
    static {
        mMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        //初始化
        //常量UriMacher.NO_MATCH=不匹配任何路径的返回码
        //即初始化不匹配任何东西

        //在ContentProvider中注册URI(addURI())
        mMatcher.addURI(AUTOHORIYY,"user",User_Code);
        mMatcher.addURI(AUTOHORIYY,"job",Job_Code);
        //若URI资源路径=content://cn.scu.myprovider/user,则返回注册码User_Code
        //若URI资源路径=content://cn.scu.myprovider/job,则返回注册码Job_Code


    }



    @Override
    public boolean onCreate() {
        mContext = getContext();
        //在ContentProvider创建时对数据库进行初始化
        //运行中主线程，故不能做耗时操作
        mDBHelper = new ContentBaseHelper(getContext());
        db = mDBHelper.getWritableDatabase();

        //初始化两个表的数据（先清空两个表，再各加入一个记录）
        db.execSQL("delete from user");
        db.execSQL("insert into user values(1,'Carson');");
        db.execSQL("insert into user values(2,'Kobe');");

        db.execSQL("delete from job");
        db.execSQL("insert into job values(1,'Android');");
        db.execSQL("insert into job values(2,'IOS');");
        return true;
    }


    //根据URI匹配URI_CODE，从而匹配ContentProvider中相应的表名
    private String getTableName(Uri uri){
        String tableName = null;
        switch (mMatcher.match(uri)){
            // 根据URI匹配的返回码是User_Code
            case User_Code:
                tableName = ContentBaseHelper.USER_TABLE_NAME;
                break;
            // 根据URI匹配的返回码是Job_Code
            case Job_Code:
                tableName = ContentBaseHelper.JOB_TABLE_NAME;
                break;

        }
        return tableName;
    }



    @Override
    public Cursor query(Uri uri,String[] projection, String selection, String[] selectionArgs,String sortOrder) {
        //根据URI匹配URI_CODE，从而匹配ContentProvider中相应的表名
        String table = getTableName(uri);
        System.out.println("-----------------");
        System.out.println(table);
        System.out.println("-----------------");

        //查询数据
        String sql = "delete from " + table + " where _id = 2";
        db.execSQL(sql);
        //用sql语句来操作太方便了！！！

        return db.query(table,projection,selection,selectionArgs,null,null,sortOrder,null);

    }


    @Override
    public String getType(Uri uri) {
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {
        //根据URI匹配URI_CODE,从而匹配ContentProvider中相应的表名
        String table = getTableName(uri);

        //向该表添加数据
        db.insert(table,null,values);

        //当该URI的ContentProvider数据发生变化时，通知外界（即访问该ContentProvider数据的访问者）
        mContext.getContentResolver().notifyChange(uri,null);

        //通过ContentUris类从URL中获取ID
   //     long personid = ContentUris.parseId(uri);
     //   System.out.println(personid);

        return uri;
    }

    @Override
    public int delete(Uri uri,  String selection, String[] selectionArgs) {


        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values,String selection,String[] selectionArgs) {
        return 0;
    }
}
