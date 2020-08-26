package com.example.solitaryhelper.view.dest.main.tapfragments

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentEatingAloneBinding
import com.example.solitaryhelper.view.base.BaseFragment
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class FragmentEatingAlone:BaseFragment<FragmentEatingAloneBinding>(R.layout.fragment_eating_alone) {

    var REQUIRED_PERMISSIONS = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    override fun FragmentEatingAloneBinding.setEventListener() {


    }

    override fun FragmentEatingAloneBinding.setCreateView() {
  val mapView = MapView(requireActivity())
        mapViewContainer.addView(mapView)

        val MARKER_POINT =
            MapPoint.mapPointWithGeoCoord(37.54892296550104, 126.99089033876304)

        val marker = MapPOIItem()
        marker.itemName = "Default Marker"
        marker.tag = 0
        marker.mapPoint = MARKER_POINT
        marker.markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.

        marker.selectedMarkerType =
            MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.


        mapView.addPOIItem(marker)

    }



}