package com.example.solitaryhelper.view.dest.main.tapfragments

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentEatingAloneBinding
import com.example.solitaryhelper.view.base.BaseFragment
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.fragment_fake_kakao_talk.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class FragmentEatingAlone :
    BaseFragment<FragmentEatingAloneBinding>(R.layout.fragment_eating_alone) {


    companion object {

        const val REQUEST_ACCESS_COARSE_LOCATION_AND_ACCESS_FINE_LOCATION = 1111
    }

    var fusedLocationClient: FusedLocationProviderClient? = null
    var locationCallback: LocationCallback? = null
    var locationRequest: LocationRequest? = null

    val mapView by lazy { MapView(requireActivity()) }

    var playerLatitude: Double? = null
    var playerLongitude: Double? = null
    override fun FragmentEatingAloneBinding.setEventListener() {
        buttonSendEatingHouse.setOnClickListener {
        }

    }

    override fun FragmentEatingAloneBinding.setCreateView() {
        initLocation()
        setMap()


    }

    private fun FragmentEatingAloneBinding.setMap() {
        mapLocationPlus()
        mapViewContainer.addView(mapView)

        if (playerLatitude == null || playerLongitude == null)
            mapView.setMapCenterPoint(
                MapPoint.mapPointWithGeoCoord(37.551444, 126.994359),
                true)
        else
            mapView.setMapCenterPoint(
                MapPoint.mapPointWithGeoCoord(
                    playerLatitude!!,
                    playerLongitude!!
                ), true
            )


        mapView.setZoomLevel(7, true)
        mapView.zoomIn(true)
        mapView.zoomOut(true)


    }

    private fun mapLocationPlus() {

            var latitudRandom: Double
            var longitudeRandom: Double

            var i = 0
            while (i < 100) {


                mapView.addPOIItem(MapPOIItem().apply {

                    if (playerLatitude == null || playerLongitude == null) {
                        latitudRandom = Math.random() * (37.651444 - 37.450000) + 37.450000
                        longitudeRandom = Math.random() * (127.094359 - 126.850000) + 126.850000
                    } else {
                        latitudRandom =
                            Math.random() * (playerLatitude!!+0.1 - playerLatitude!! - 0.2) + playerLatitude!!+0.1 - 0.2
                        longitudeRandom =
                            Math.random() * (playerLongitude!!+0.1 - playerLongitude!! - 0.2) + playerLongitude!!+0.1 - 0.2
                    }
                    itemName =
                        "근처에 확인된 혼밥집들입니다.\n 빠른 혼밥 장소를 찾으시려면 상단버튼을 눌러주세요 "
                    tag = i
                    mapPoint = MapPoint.mapPointWithGeoCoord(latitudRandom, longitudeRandom)
                    markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.
                    selectedMarkerType =
                        MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
                } )
                i++
            }
    }

    private fun initLocation() {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                REQUEST_ACCESS_COARSE_LOCATION_AND_ACCESS_FINE_LOCATION
            )

        } else {
            fusedLocationClient!!.lastLocation
                .addOnSuccessListener { location ->
                    if (location == null) {
                        Log.e("opop", "location get fail")
                    } else {
                        Log.d("opop2", "${location.latitude} , ${location.longitude}")
                    }
                }
                .addOnFailureListener {
                    Log.e("opop3", "location error is ${it.message}")
                    it.printStackTrace()
                }

//            fusedLocationClient!!.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())

            locationRequest = LocationRequest.create()
            locationRequest?.run {
                priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                interval = 60 * 1000
            }

            locationCallback =
                object : LocationCallback() {
                    override fun onLocationResult(locationResult: LocationResult?) {
                        locationResult?.let {
                            for ((i, location) in it.locations.withIndex()) {
                                playerLatitude = location.latitude;playerLongitude =
                                    location.longitude
                                Log.d("opop8", "#$i ${location.latitude} , ${location.longitude}")
                            }
                        }
                    }
                }
        }

    }


}