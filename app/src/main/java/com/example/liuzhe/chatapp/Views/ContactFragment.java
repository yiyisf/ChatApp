package com.example.liuzhe.chatapp.Views;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.liuzhe.chatapp.ChatApp;
import com.example.liuzhe.chatapp.R;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;


/**
 * Created by liuzhe on 15/10/5.
 */
public class ContactFragment extends Fragment {

    private Context context;
    public double latitude;
    public double longitudel;

    TextView la, longi;
    //    LocationManager locationManager = (LocationManager) getSystemService(getContext().LOCATION_SERVICE)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        context = ChatApp.getInstance();

        return inflater.inflate(R.layout.fragment_contacts, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LocationManager locationmanager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        Location location = locationmanager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location == null){
            location = locationmanager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (location != null) {
            latitude = location.getLatitude();
            longitudel = location.getLongitude();

            la = (TextView) getActivity().findViewById(R.id.latitude);
            la.setText("latitude is:" + latitude);

            longi = (TextView) getActivity().findViewById(R.id.longitudel);
            longi.setText("longitude is:" + longitudel);
        }
    }


}
