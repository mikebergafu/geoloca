package com.bitlogictechnologies.geoloc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike-berg on 04-07-2017.
 */

public class TableController extends DataBaseHelper {

    public TableController(Context context ){
        super(context);
    }

    public boolean create(Entities entities) {

        ContentValues values = new ContentValues();

        //values.put("sign_id", entities.sign_id);
        values.put("company_name", entities.company_name);
        values.put("location", entities.location);
        values.put("contact", entities.contact);
        values.put("sign_type", entities.sign_type);
        values.put("face", entities.s_face);
        values.put("gps_lon", entities.gps_lon);
        values.put("gps_lat", entities.gps_lat);
        values.put("size", entities.size);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("tbl_sign", null, values) > 0;
        db.close();
        return createSuccessful;
    }
    //Counts total Records in sign table
    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM tbl_sign";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }
    // Get records and store them in an
    public List<Entities> read() {

        ArrayList<Entities> recordsList = new ArrayList<Entities>();

        String sql = "SELECT * FROM tbl_sign ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String company_name = cursor.getString(cursor.getColumnIndex("company_name"));
                String s_location = cursor.getString(cursor.getColumnIndex("location"));
                String contact = cursor.getString(cursor.getColumnIndex("contact"));
                String sign_type = cursor.getString(cursor.getColumnIndex("sign_type"));
                String face = cursor.getString(cursor.getColumnIndex("face"));
                float lon = cursor.getFloat(cursor.getColumnIndex("gps_lon"));
                float lat = cursor.getFloat(cursor.getColumnIndex("gps_lat"));
                float size = cursor.getFloat(cursor.getColumnIndex("size"));

                Entities SignEntity = new Entities();
                SignEntity.company_name=company_name;
                SignEntity.location=s_location;
                SignEntity.contact=contact;
                SignEntity.sign_type=sign_type;
                SignEntity.s_face=face;
                SignEntity.gps_lon=lon;
                SignEntity.gps_lat=lat;
                SignEntity.size=size;

                recordsList.add(SignEntity);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }


    //Retrieve company name from db
    public ArrayList<String> get_com_name() {
        ArrayList<String> ll = new ArrayList<String>();
        String query = "SELECT company_name FROM tbl_sign ORDER BY id DESC";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur = db.rawQuery(query, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    ll.add(cur.getString(0));

                } while (cur.moveToNext());

                db.close();
                cur.close();
            }
        }
        System.out.println("Database content "+ll.toString());
        return ll;
    }

    //Retrieve contact from db
    public ArrayList<String> get_contact() {
        ArrayList<String> ll = new ArrayList<String>();
        String query = "SELECT contact FROM tbl_sign ORDER BY id DESC";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur = db.rawQuery(query, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    ll.add(cur.getString(0));

                } while (cur.moveToNext());

                db.close();
            }
        }
        System.out.println("Database content "+ll.toString());
        return ll;
    }
    //Retrieve sign type from db
    public ArrayList<String> get_sign_type() {
        ArrayList<String> ll = new ArrayList<String>();
        String query = "SELECT sign_type FROM tbl_sign ORDER BY id DESC";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur = db.rawQuery(query, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    ll.add(cur.getString(0));

                } while (cur.moveToNext());

                db.close();
            }
        }
        System.out.println("Database content "+ll.toString());
        return ll;
    }

    //Retrieve sign type from db
    public ArrayList<String> get_faces() {
        ArrayList<String> ll = new ArrayList<String>();
        String query = "SELECT face FROM tbl_sign ORDER BY id DESC";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur = db.rawQuery(query, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    ll.add(cur.getString(0));

                } while (cur.moveToNext());

                db.close();
            }
        }
        System.out.println("Database content "+ll.toString());
        return ll;
    }

    //Retrieve sign type from db
    public ArrayList<String> get_location() {
        ArrayList<String> ll = new ArrayList<String>();
        String query = "SELECT location FROM tbl_sign ORDER BY id DESC";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur = db.rawQuery(query, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    ll.add(cur.getString(0));

                } while (cur.moveToNext());

                db.close();
            }
        }
        System.out.println("Database content "+ll.toString());
        return ll;
    }

    //Retrieve sign type from db
    public ArrayList<String> get_gps_lon() {
        ArrayList<String> ll = new ArrayList<String>();
        String query = "SELECT gps_lon FROM tbl_sign ORDER BY id DESC";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur = db.rawQuery(query, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    ll.add(cur.getString(0));

                } while (cur.moveToNext());

                db.close();
            }
        }
        System.out.println("Database content "+ll.toString());
        return ll;
    }

    //Retrieve sign type from db
    public ArrayList<String> get_gpr_lat() {
        ArrayList<String> ll = new ArrayList<String>();
        String query = "SELECT gps_lat FROM tbl_sign ORDER BY id DESC";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur = db.rawQuery(query, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    ll.add(cur.getString(0));

                } while (cur.moveToNext());

                db.close();
            }
        }
        System.out.println("Database content "+ll.toString());
        return ll;
    }

    //Retrieve sign type from db
    public ArrayList<String> get_size() {
        ArrayList<String> ll = new ArrayList<String>();
        String query = "SELECT size FROM tbl_sign ORDER BY id DESC";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur = db.rawQuery(query, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    ll.add(cur.getString(0));

                } while (cur.moveToNext());

                db.close();
            }
        }
        System.out.println("Database content "+ll.toString());
        return ll;
    }

}
