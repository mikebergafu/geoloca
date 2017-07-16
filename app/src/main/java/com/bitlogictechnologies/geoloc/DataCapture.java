package com.bitlogictechnologies.geoloc;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mike-berg on 02-07-2017.
 */

public class DataCapture extends AppCompatActivity implements LocationListener {
    Entities SignEntity = new Entities();
    TableController tc=new TableController(this);

    private LocationManager locationManager;
    String mprovider;
    private AutoCompleteTextView longitude;
    private AutoCompleteTextView latitude;
    private Button btn_take_image, btn_submit;
    private String pg_title = "Data Capture Form";
    private TextView txtPgTitle;
    private Spinner txt_sign_type;
    private EditText txt_sign_id, txt_company_name,txt_location,txt_contact,txt_face, txt_size;
    Location location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_capture_ui);
        btn_submit=(Button) findViewById(R.id.btn_submit);
        //create reference to all EditText field in layout
        txt_company_name=(EditText)findViewById(R.id.txt_company_name);
        txt_location=(EditText)findViewById(R.id.txt_location);
        txt_contact=(EditText)findViewById(R.id.txt_contact);
        txt_sign_type=(Spinner)findViewById(R.id.txt_sign_type);
        txt_face=(EditText)findViewById(R.id.txt_face);
        txt_size=(EditText)findViewById(R.id.txt_size);

        longitude = (AutoCompleteTextView) findViewById(R.id.txt_long);
        latitude = (AutoCompleteTextView) findViewById(R.id.txt_lat);
        longitude.setFocusable(false);
        latitude.setFocusable(false);

        //Location variables
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        mprovider = locationManager.getBestProvider(criteria, false);
        if (mprovider != null && !mprovider.equals("")) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            location = locationManager.getLastKnownLocation(mprovider);
            locationManager.requestLocationUpdates(mprovider, 15000, 1, this);

            if (location != null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(), "No Location Provider Found Check Your Code", Toast.LENGTH_SHORT).show();
        }

        txtPgTitle = (TextView) findViewById(R.id.lbl_pg_title);
        txtPgTitle.setText(pg_title);
        btn_take_image = (Button) findViewById(R.id.btn_take_image);
        btn_take_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadScreen();
            }
        });

    //handles submit action button on the data capture form
    btn_submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Retrieves user entered data from the fields on the datacapture form
            String company_name = txt_company_name.getText().toString();
            String s_location = txt_location.getText().toString();
            String contact = txt_contact.getText().toString();
            String sign_type = txt_sign_type.getSelectedItem().toString();
            String face = txt_face.getText().toString();
            float lon = Float.parseFloat(longitude.getText().toString());
            float lat = Float.parseFloat(latitude.getText().toString());
            float size = Float.parseFloat(txt_size.getText().toString());
            //Assign field data to elements of the Entities
            SignEntity.company_name=company_name;
            SignEntity.location=s_location;
            SignEntity.contact=contact;
            SignEntity.sign_type=sign_type;
            SignEntity.s_face=face;
            SignEntity.gps_lon=lon;
            SignEntity.gps_lat=lat;
            SignEntity.size=size;

            //Instantiate success variable to track the success or failure of the submission to database
            boolean createSuccessful = tc.create(SignEntity);

            if(createSuccessful){
                Toast.makeText(DataCapture.this, "Sign Board information was saved.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(DataCapture.this, "Unable to save Sign Board information.", Toast.LENGTH_SHORT).show();
            }

        }
    });

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter.createFromResource(this, R.array.sign_type_array, R.layout.spinner_lu);
        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(R.layout.spinner_lu);
        // Apply the adapter to the spinner
        txt_sign_type.setAdapter(staticAdapter);
    }


    //loads login screen after
    public void loadScreen() {
        Intent intent = new Intent(this, SignCapture.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    @Override
    public void onLocationChanged(Location location) {
        String lon = String.valueOf(location.getLongitude());
        String lat = String.valueOf(location.getLatitude());
        longitude.setText(lon);
        latitude.setText(lat);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public void getActualLocation(Location location) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + " " + obj.getLocality();
            String addre=add;
            TextView txt_location=(TextView)findViewById(R.id.txt_location);
            txt_location.setText(addre);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void go_to_records(View view){
        Intent intent = new Intent(this, ViewRecord.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    public void get_location(View view){
        getActualLocation(location);

    }

}
