package com.bitlogictechnologies.geoloc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mike-berg on 05-07-2017.
 */

    public class ViewRecord extends AppCompatActivity {
    ListView lv_sign_list;
    BreedListAdapter dataListAdapter;
    ArrayList<String> signComList, signContactList, signTypeList, signLocationList, signFaceList, signGpsLonList, signGpsLatList,signSizeList;
    TableController tc=new TableController(this);
    private TextView tv_record_count;
    DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_record_ui);
        readRecords();
        int recordCount = tc.count();
        tv_record_count = (TextView) findViewById(R.id.lbl_record);
        tv_record_count.setText(recordCount + " records found.");
    }

    public void countRecords() {
        int recordCount = tc.count();
        tv_record_count = (TextView) findViewById(R.id.lbl_record);
        tv_record_count.setText(recordCount + " records found.");
    }

    public void count_record(View view){
        countRecords();
        (this).readRecords();
    }

    public void readRecords() {

        signComList=new ArrayList<String>();
        signLocationList=new ArrayList<String>();
        signContactList=new ArrayList<String>();
        signTypeList=new ArrayList<String>();
        signFaceList=new ArrayList<String>();
        signGpsLonList=new ArrayList<String>();
        signGpsLatList=new ArrayList<String>();
        signSizeList=new ArrayList<String>();


        signComList=tc.get_com_name();
        signLocationList=tc.get_location();
        signContactList=tc.get_contact();
        signTypeList=tc.get_sign_type();
        signFaceList=tc.get_faces();
        signGpsLonList=tc.get_gps_lon();
        signGpsLatList=tc.get_gpr_lat();
        signSizeList=tc.get_size();


        dataListAdapter=new BreedListAdapter(this,signComList,signLocationList,signContactList,signTypeList,signFaceList,signGpsLonList,signGpsLatList,signSizeList);
        lv_sign_list=(ListView) findViewById(R.id.lv_sign_data);
        lv_sign_list.setAdapter(dataListAdapter);
    }

    public void export_db(){
       // dataBaseHelper=new DataBaseHelper(this);
        //dataBaseHelper.exportDB();

    }
}
