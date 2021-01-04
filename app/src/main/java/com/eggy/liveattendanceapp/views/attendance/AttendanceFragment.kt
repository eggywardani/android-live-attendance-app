package com.eggy.liveattendanceapp.views.attendance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eggy.liveattendanceapp.R
import com.eggy.liveattendanceapp.databinding.FragmentAttendanceBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class AttendanceFragment : Fragment(), OnMapReadyCallback {

    private var mapAttendance:SupportMapFragment? = null
    private var map:GoogleMap? = null
    private var binding:FragmentAttendanceBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAttendanceBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMaps()
    }

    private fun setupMaps() {
        mapAttendance = childFragmentManager.findFragmentById(R.id.map_attendance) as SupportMapFragment
        mapAttendance?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap
        val location = LatLng(-6.798676406932624, 110.83369126786808)
        map?.addMarker(
            MarkerOptions()
                .position(location)
                .title("My Location")
        )
        map?.moveCamera(CameraUpdateFactory.newLatLng(location))
        map?.animateCamera(CameraUpdateFactory.zoomTo(20f))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding= null
    }

}