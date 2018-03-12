package com.example.senikaru.tangooboe;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by SenikaRu on 3/3/2018.
 */

public class DBManager extends SQLiteOpenHelper {

    public static final String ROOT_DIR="/data/data/com.example.senikaru.tangooboe/databases/";
    public static final String DATABASENAME="yobi.db";

    public DBManager(Context context){
        super(context, DATABASENAME, null, 1);
        setDB(context);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {}

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public static void setDB(Context context) {

        File folder = new File(ROOT_DIR);

        if (!folder.exists())
            folder.mkdir();

        AssetManager assetManager = context.getResources().getAssets();

        File out = new File(ROOT_DIR + DATABASENAME);

        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;

        int filesize = 0;

        try {
            inputStream = assetManager.open(DATABASENAME, AssetManager.ACCESS_BUFFER);
            filesize = inputStream.available();

            if (out.length() <= 0) {
                byte[] temp = new byte[filesize];
                inputStream.read(temp);
                inputStream.close();

                out.createNewFile();
                fileOutputStream = new FileOutputStream(out);
                fileOutputStream.write(temp);
                fileOutputStream.close();
            } else {
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getChapName(String book){
        ArrayList<String> arrayList=new ArrayList<String>();

        String sql="select * from Chapters where chapter LIKE '"+book+"%'";

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, null);


            if (cursor.moveToFirst()) {
                do {
                    arrayList.add(cursor.getString(cursor.getColumnIndex("name")));

                } while (cursor.moveToNext());
            }

            db.close();
            cursor.close();
            return arrayList;

        }catch (SQLException e){
            return null;
        }
    }

    public String getChapTitle(String id){
        String Chap_title="error";
        String sql="select * from Chapters where chapter = '"+id+"'";

        try{
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(sql, null);


            if(cursor.moveToFirst())
                Chap_title=cursor.getString(cursor.getColumnIndex("name"));
            db.close();
            cursor.close();

        }catch (SQLException e){

        }
        return Chap_title;

    }


    public ArrayList<HashMap<String,String>> getAll()
    {
        ArrayList<HashMap<String,String>> arraylist=new ArrayList<HashMap<String,String>>();

        // sql select
        String sql="select * from KanjiData";


        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(sql, null);


        if(cursor.moveToFirst())
        {
            do {

                HashMap<String,String> data=new HashMap<String, String>();
                data.put("Chapter", cursor.getString(cursor.getColumnIndex("Chapter")));
                data.put("furigana", cursor.getString(cursor.getColumnIndex("furigana")));
                data.put("kanji", cursor.getString(cursor.getColumnIndex("kanji")));
                data.put("korean", cursor.getString(cursor.getColumnIndex("korean")));
                data.put("stared", cursor.getString(cursor.getColumnIndex("stared")));

                int corr, tried;
                corr=cursor.getInt(cursor.getColumnIndex("correct"));
                tried=cursor.getInt(cursor.getColumnIndex("tried"));
                if(tried!=0) {
                    if ((double) corr / (double) tried < 0.7)
                        data.put("inocori", "true");
                    else
                        data.put("inocori", "hoooorraaaaaay");
                }
                else
                    data.put("inocori", "hoooorraaaaaay");

                arraylist.add(data);

            }while(cursor.moveToNext());
        }

        db.close();
        cursor.close();
        return arraylist;
    }

    public ArrayList<HashMap<String,String>> getSearch(String phrase)
    {
        ArrayList<HashMap<String,String>> arraylist=new ArrayList<HashMap<String,String>>();

        // sql select
        //String sql="select * from KanjiData where Chapter="+phrase+" or kanji="+phrase+" or korean="+phrase+" or hiragana="+phrase;
        String sql="select * from KanjiData where kanji LIKE '%"+phrase+"%' or korean LIKE '%"+phrase+"%' or hiragana LIKE '%"+phrase+"%'" ;

        try{
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(sql, null);


            if(cursor.moveToFirst())
            {
                do {

                    HashMap<String,String> data=new HashMap<String, String>();
                    data.put("Chapter", cursor.getString(cursor.getColumnIndex("Chapter")));
                    data.put("furigana", cursor.getString(cursor.getColumnIndex("furigana")));
                    data.put("kanji", cursor.getString(cursor.getColumnIndex("kanji")));
                    data.put("korean", cursor.getString(cursor.getColumnIndex("korean")));
                    data.put("stared", cursor.getString(cursor.getColumnIndex("stared")));

                    int corr, tried;
                    corr=cursor.getInt(cursor.getColumnIndex("correct"));
                    tried=cursor.getInt(cursor.getColumnIndex("tried"));
                    if(tried!=0) {
                        if ((double) corr / (double) tried < 0.7)
                            data.put("inocori", "true");
                        else
                            data.put("inocori", "hoooorraaaaaay");
                    }
                    else
                        data.put("inocori", "hoooorraaaaaay");

                    arraylist.add(data);

                }while(cursor.moveToNext());
            }

            db.close();
            cursor.close();
            return arraylist;

        }
        catch (SQLException e){
            return null;
        }
    }

    public ArrayList<HashMap<String,String>> getChap(String phrase)
    {
        ArrayList<HashMap<String,String>> arraylist=new ArrayList<HashMap<String,String>>();

        // sql select
        //String sql="select * from KanjiData where Chapter="+phrase+" or kanji="+phrase+" or korean="+phrase+" or hiragana="+phrase;
        String sql="select * from KanjiData where Chapter = '"+phrase+"'" ;

        try{
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(sql, null);


            if(cursor.moveToFirst())
            {
                do {

                    HashMap<String,String> data=new HashMap<String, String>();
                    data.put("Chapter", cursor.getString(cursor.getColumnIndex("Chapter")));
                    data.put("furigana", cursor.getString(cursor.getColumnIndex("furigana")));
                    data.put("kanji", cursor.getString(cursor.getColumnIndex("kanji")));
                    data.put("hiragana", cursor.getString(cursor.getColumnIndex("hiragana")));
                    data.put("korean", cursor.getString(cursor.getColumnIndex("korean")));
                    data.put("stared", cursor.getString(cursor.getColumnIndex("stared")));

                    int corr, tried;
                    corr=cursor.getInt(cursor.getColumnIndex("correct"));
                    tried=cursor.getInt(cursor.getColumnIndex("tried"));
                    if(tried!=0) {
                        if ((double) corr / (double) tried < 0.7)
                            data.put("inocori", "true");
                        else
                            data.put("inocori", "hoooorraaaaaay");
                    }
                    else
                        data.put("inocori", "hoooorraaaaaay");

                    arraylist.add(data);

                }while(cursor.moveToNext());
            }

            db.close();
            cursor.close();
            return arraylist;

        }
        catch (SQLException e){
            return null;
        }
    }

    public ArrayList<HashMap<String,String>> getMine(String phrase)
    {
        ArrayList<HashMap<String,String>> arraylist=new ArrayList<HashMap<String,String>>();

        // sql select
        //String sql="select * from KanjiData where Chapter="+phrase+" or kanji="+phrase+" or korean="+phrase+" or hiragana="+phrase;
        String sql="select * from KanjiData where Chapter = '"+phrase+"'" ;

        try{
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(sql, null);


            if(cursor.moveToFirst())
            {
                do {

                    HashMap<String,String> data=new HashMap<String, String>();
                    data.put("Chapter", cursor.getString(cursor.getColumnIndex("Chapter")));
                    data.put("furigana", cursor.getString(cursor.getColumnIndex("furigana")));
                    data.put("kanji", cursor.getString(cursor.getColumnIndex("kanji")));
                    data.put("hiragana", cursor.getString(cursor.getColumnIndex("hiragana")));
                    data.put("korean", cursor.getString(cursor.getColumnIndex("korean")));
                    String starred=cursor.getString(cursor.getColumnIndex("stared"));
                    data.put("stared", starred);

                    int corr, tried;
                    corr=cursor.getInt(cursor.getColumnIndex("correct"));
                    tried=cursor.getInt(cursor.getColumnIndex("tried"));
                    if(tried!=0) {
                        if ((double) corr / (double) tried < 0.7){
                            data.put("inocori", "true");
                            arraylist.add(data);
                        }
                        else if( starred.trim().equals("1".trim())){
                            data.put("inocori", "false");
                            arraylist.add(data);
                        }
                    }
                    else if( starred.trim().equals("1".trim())){
                        data.put("inocori", "false");
                        arraylist.add(data);
                    }

                }while(cursor.moveToNext());
            }

            db.close();
            cursor.close();
            return arraylist;

        }
        catch (SQLException e){
            return null;
        }
    }


    public void setStar(Boolean star, String chp_id, String furigana){
        String sql="UPDATE KanjiData Set stared = ";

        if(star)
            sql=sql+"1";
        else
            sql=sql+"0";

        sql+=" where Chapter = '"+chp_id+"' and furigana = '"+furigana+"'";

        try{

            SQLiteDatabase db=this.getWritableDatabase();
            db.execSQL(sql);
            db.close();

        } catch (SQLException e){

        }

    }

    public void setScore (Boolean correct, String chp_id, String furigana){
        String sql_getscore="select * from KanjiData where Chapter = '"+chp_id+"' and furigana = '"+furigana+"'";

        int int_tried=0, int_correct=0;

        try{
            SQLiteDatabase db=this.getReadableDatabase();
            Cursor cursor=db.rawQuery(sql_getscore, null);
            if(cursor.moveToFirst()){
                int_tried=cursor.getInt(cursor.getColumnIndex("tried"));
                int_correct=cursor.getInt(cursor.getColumnIndex("correct"));
            }
        }catch (SQLException e){

        }

        if(correct)
            int_correct+=1;
        int_tried+=1;

        String sql_update="UPDATE KanjiData Set tried = "+ Integer.toString(int_tried)+", correct = "+Integer.toString(int_correct)+" where Chapter = '"+chp_id+"' and furigana = '"+furigana+"'";

        try{

            SQLiteDatabase db=this.getWritableDatabase();
            db.execSQL(sql_update);
            db.close();

        } catch (SQLException e){

        }

    }



}
