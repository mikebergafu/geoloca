package com.bitlogictechnologies.geoloc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by Mike-berg on 04-07-2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "signagedb";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String qry = "CREATE TABLE IF NOT EXISTS tbl_sign " +
                    "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "sign_id TEXT, " +
                    "company_name TEXT, " +
                    "location TEXT, " +
                    "contact TEXT, " +
                    "sign_type TEXT, " +
                    "face INTEGER, " +
                    "gps_lon REAL, " +
                    "gps_lat REAL, " +
                    "size REAL ) ";

            db.execSQL(qry);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS tbl_sign";
        db.execSQL(sql);

        onCreate(db);
    }

    public void exportDB(){
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source=null;
        FileChannel destination=null;
        String currentDBPath = "/data/"+ "com.bitlogictechnologies.databreed" +"/databases/"+ DATABASE_NAME ;
        String backupDBPath = DATABASE_NAME ;
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            System.out.println("Database Exported");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}


