package com.bitlogictechnologies.geoloc;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mike-berg on 06-07-2017.
 */

public class BreedListAdapter extends ArrayAdapter<String> {
    //create list container to load list as an array from database
    private ArrayList<String> comp_name;
    private ArrayList<String> s_location;
    private ArrayList<String> s_contact;
    private ArrayList<String> s_sign_type;
    private ArrayList<String> face;
    private ArrayList<String> gps_lon;
    private ArrayList<String> gps_lat;
    private ArrayList<String> s_size;
    private Activity context;
    //Definds a conatin method to hold the list
    public BreedListAdapter(Activity context, ArrayList<String> comp_name,ArrayList<String>s_location, ArrayList<String> s_contact, ArrayList<String> s_sign_type, ArrayList<String> face,ArrayList<String> gps_lon, ArrayList<String> gps_lat, ArrayList<String> s_size) {
        super(context, R.layout.main_list_def, comp_name);
        this.context = context;
        this.comp_name =comp_name;
        this.s_location=s_location;
        this.s_contact = s_contact;
        this.s_sign_type = s_sign_type;
        this.face=face;
        this.gps_lon=gps_lon;
        this.gps_lat=gps_lat;
        this.s_size=s_size;
    }
   //Makes the list a viewer object
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //creates a new inflate object ready to display the listview row items
        LayoutInflater inflater = context.getLayoutInflater();
        //defines a view object that is to display items in the listview based on a design defined in xml
        View listViewItem = inflater.inflate(R.layout.main_list_def, null, true);
        //references the fields in xml to load the list into one at a time
        TextView tv_com_name = (TextView) listViewItem.findViewById(R.id.tv_company);
        TextView tv_location = (TextView) listViewItem.findViewById(R.id.tv_location);
        TextView tv_contact = (TextView) listViewItem.findViewById(R.id.tv_contact);
        TextView tv_sign_type = (TextView) listViewItem.findViewById(R.id.tv_sign_type);
        TextView tv_face = (TextView) listViewItem.findViewById(R.id.tv_face);
        TextView tv_gps_lon = (TextView) listViewItem.findViewById(R.id.tv_gps_lon);
        TextView tv_gps_lat = (TextView) listViewItem.findViewById(R.id.tv_gps_lat);
        TextView tv_size = (TextView) listViewItem.findViewById(R.id.tv_size);

        tv_com_name.setText(comp_name.get(position));
        tv_location.setText("Location: "+s_location.get(position));
        tv_contact.setText("Contact: "+s_contact.get(position));
        tv_sign_type.setText("Sign Type: "+s_sign_type.get(position));
        tv_face.setText("Face(s): "+face.get(position));
        tv_gps_lon.setText("GPS LONG: "+gps_lon.get(position));
        tv_gps_lat.setText("GPS LAT: "+gps_lat.get(position));
        tv_size.setText("Size: "+s_size.get(position));

        return  listViewItem;
    }
}