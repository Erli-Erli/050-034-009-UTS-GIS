package com.test.a050034009utsgaa;

import static android.content.Context.LOCATION_SERVICE;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;

import pl.droidsonroids.gif.GifImageView;

public class BlankFragment extends Fragment implements LocationListener {
    GifImageView gifImageView;
    FusedLocationProviderClient client;
    TextView tv_address;
    EditText et_latitude, et_longitude;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        // Inflate the layout for this fragment
        btn = view.findViewById(R.id.btn_dapatkan_lokasi);
        tv_address = view.findViewById(R.id.tv_current_address);
        tv_address.setGravity(Gravity.CENTER_HORIZONTAL);
        et_latitude = view.findViewById(R.id.etLatitude);
        et_longitude = view.findViewById(R.id.etLongitude);
        gifImageView = view.findViewById(R.id.fire_gif);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },100);
        }
        //initialize location client
        client = LocationServices.getFusedLocationProviderClient(getActivity());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getLocation();
                    gifImageView.setVisibility(View.VISIBLE);
                }
        });
        return view;
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, BlankFragment.this);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        et_latitude.setText(String.valueOf(location.getLatitude()));
        et_longitude.setText(String.valueOf(location.getLongitude()));
        try {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);
            tv_address.setText(address);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
