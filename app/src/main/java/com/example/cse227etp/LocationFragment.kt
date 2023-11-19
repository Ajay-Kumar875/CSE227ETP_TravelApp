package com.example.cse227etp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import java.util.Locale

class LocationFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private val LOCATION_PERMISSION_REQUEST_CODE = 1
    private lateinit var locationTextView: TextView
    private lateinit var getLocationButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_location, container, false)

        val mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationTextView = view.findViewById(R.id.locationTextView)
        getLocationButton = view.findViewById(R.id.getLocationButton)

        getLocationButton.setOnClickListener {
            if (checkLocationPermission()) {
                displayLocation()
            } else {
                requestLocationPermission()
            }
        }


        val btnShowLocation = view?.findViewById<Button>(R.id.btnShowLocation)
        if (btnShowLocation != null) {
            btnShowLocation.setOnClickListener { showLocation(it) }
        }
        return view

    }

    private fun checkLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }


    private fun displayLocation() {
        val fusedLocationClient = activity?.let {
            LocationServices.getFusedLocationProviderClient(it)
        }

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient?.lastLocation
            ?.addOnSuccessListener { location ->
                location?.let {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    locationTextView.text = "Latitude: $latitude\nLongitude: $longitude"

                    mMap.isMyLocationEnabled = true
                    var zoomLevel = 15f

                    val userLocation = LatLng(latitude, longitude)
                    mMap.addMarker(MarkerOptions().position(userLocation).title("My Location"))
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, zoomLevel))

                    mMap.addMarker(
                        MarkerOptions()
                            .position(userLocation)
                            .title("My Location")
                            .snippet("Description")
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    )
                } ?: run {
                    locationTextView.text = "Unable to retrieve location"
                }
            } ?: run {
            locationTextView.text = "Location service unavailable"

        }

    }


    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        displayLocation()
    }

    fun showLocation(view: View) {
        displayLocation()
    }

//    private fun enableMyLocation(lat:Double, lon: Double) {
//        if (ContextCompat.checkSelfPermission(
//                requireContext(),
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//
//            ActivityCompat.requestPermissions(
//                requireActivity(),
//                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
//                LOCATION_PERMISSION_REQUEST_CODE
//            )
//
//        } else {
//            mMap.isMyLocationEnabled = true
//
//            var latitude = 37.7749
//            var longitude = -122.4194
//            var zoomLevel = 15f
//
//            val userLocation = LatLng(latitude, longitude)
//            mMap.addMarker(MarkerOptions().position(userLocation).title("My Location"))
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, zoomLevel))
//
//            mMap.addMarker(
//                MarkerOptions()
//                    .position(userLocation)
//                    .title("My Location")
//                    .snippet("Description")
//                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
//            )
//        }
//    }
}