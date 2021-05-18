package com.example.workouttrackertoothbrook.ui.tracker;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

import com.example.workouttrackertoothbrook.MainActivity;
import com.example.workouttrackertoothbrook.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;

public class trackerFragment extends Fragment implements LocationListener {

    private trackerViewModel viewModel;
    private FusedLocationProviderClient fusedLocationClient;
    private GoogleMap gMap;
    private LocationManager manager;
    private String provider;
    private ArrayList<LatLng> points;
    private Button startStopButton;
    private boolean started;
    private TextView time;
    private long timer;
    private Thread timeUpdater;
    private boolean stop;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
           /* if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            fusedLocationClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // Got last known location. In some rare situations this can be null.
                    Log.d("workout", "location success");
                    if (location == null) {
                        Log.d("workout", "location is null");
                    }
                    if (location != null) {
                        gMap = googleMap;
                        // Logic to handle location object
                        Log.d("workout", "location is not null");
                        LatLng myLoc = new LatLng(location.getLatitude(), location.getLongitude());
                        //gMap.addMarker(new MarkerOptions().position(myLoc).title("here I Started"));
                        //gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 10));


                    }
                }
            });
*/
            gMap = googleMap;
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_tracker, container, false);

        points= new ArrayList<>();
        startStopButton= root.findViewById(R.id.startStopButton);
        time= root.findViewById(R.id.timeView);
        started=false;
        stop=false;
        timeUpdater= new Thread(()->{
            while (true) {
                while (started) {
                    long timeTakenInMilis = System.currentTimeMillis()-timer;
                    int seconds = (int) (timeTakenInMilis / 1000) % 60;
                    int minutes = (int) ((timeTakenInMilis / (1000 * 60)) % 60);
                    int hours = (int) ((timeTakenInMilis / (1000 * 60 * 60)) % 24);
                    String timeToShow= hours + ":" + minutes +":" + seconds;
                    time.setText(timeToShow);
                }
            }
        });
        timeUpdater.start();
        //fusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        Criteria criteria = new Criteria();
        provider = manager.getBestProvider(criteria, false);
        Location location = manager.getLastKnownLocation(provider);


        // Initialize the location fields
        if (location != null) {
            Log.d("workout", "Provider " + provider + " has been selected.");
            onLocationChanged(location);

        }
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
        viewModel=  new ViewModelProvider(this).get(com.example.workouttrackertoothbrook.ui.tracker.trackerViewModel.class);
        startStopButton.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            if (!started) {
                stop=false;
                timer= System.currentTimeMillis();
                gMap.clear();
                points= new ArrayList<>();
                started=true;
                manager.requestLocationUpdates(provider, 400, 3, this::onLocationChanged);
                startStopButton.setText(R.string.stop);
            }
            else {
                started= false;
                stop=true;
                manager.removeUpdates(this::onLocationChanged);
                long timeTaken= System.currentTimeMillis()-timer;
                viewModel.saveWorkout(timeTaken);
                startStopButton.setText(R.string.start_workout);
            }


        });
        onLocationChanged(manager.getLastKnownLocation(provider));
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onLocationChanged(Location location) {
        //Log.d("workout", "location changed");
        if (!stop) {
            LatLng myLoc = new LatLng(location.getLatitude(), location.getLongitude());
            if (gMap != null) {


                if (!points.isEmpty()) {
                    gMap.addMarker(new MarkerOptions().position(myLoc).title("")).setVisible(false);
                    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 15));
                    gMap.addPolyline(new PolylineOptions().add(points.get(points.size() - 1), myLoc));
                } else {
                    gMap.addMarker(new MarkerOptions().position(myLoc).title("start"));
                    gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLoc, 15));
                }
                points.add(myLoc);
            }
        }
    }
}