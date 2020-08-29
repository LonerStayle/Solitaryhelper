package com.example.solitaryhelper.view.dest.main.tapfragments

import android.Manifest
import android.R.attr
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Resources
import android.net.Uri
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentEatingAloneBinding
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.dialog.DialogSimple
import com.example.solitaryhelper.view.utill.toastDebugTest
import com.example.solitaryhelper.view.utill.toastLongTime
import com.example.solitaryhelper.view.utill.toastShort
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.fragment_eating_alone.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView
import java.util.*
import kotlin.time.milliseconds


class FragmentEatingAlone :
    BaseFragment<FragmentEatingAloneBinding>(R.layout.fragment_eating_alone) {


    companion object {
        const val REQUEST_ACCESS_COARSE_LOCATION_AND_ACCESS_FINE_LOCATION = 1111
    }

    var fusedLocationClient: FusedLocationProviderClient? = null
    var locationCallback: LocationCallback? = null
    var locationRequest: LocationRequest? = null
    private val mapView by lazy { MapView(requireActivity()) }
    var playerLatitude: Double? = null
    var playerLongitude: Double? = null

    private val eatingList by lazy { resources.getStringArray(R.array.eatingList) }
    private var fristRun: Boolean = false

    override fun onAttach(context: Context) {
        mapView
        super.onAttach(context)
    }

    override fun FragmentEatingAloneBinding.setEventListener() {

        buttonClickListener()
    }


    override fun FragmentEatingAloneBinding.setCreateView() {
        initLocation()
        mapViewContainer.addView(mapView)
        mapLocationPlus()
        setMap()

    }

    private fun FragmentEatingAloneBinding.buttonClickListener() {

        buttonSendEatingHouse.setOnClickListener {

            try {
                val shuffled = eatingList.toList().shuffled()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(3500L)
                    context?.toastDebugTest("다시 이용하고 싶으면 \n뒤로가기를 눌러 앱으로 돌아가주세요")
                    delay(2000L)
                    context?.toastDebugTest("믿고 기다리시면 알아서 화면이 변합니다!")

                }

                val url = "kakaomap://search?q=${shuffled[0]}&p=$playerLatitude,$playerLongitude"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent)
                context?.toastLongTime("혼밥 찾기 버튼을 누를때 마다 \n새로운 장소를 추천해드립니다. ")


            } catch (e: Exception) {
                CoroutineScope(Dispatchers.Main).launch {
                    delay(3500L)
                    context?.toastDebugTest("설치 완료 후 뒤로가기를 눌러 \n앱으로 돌아가서 다시 버튼을 누르시면 실행이 됩니다.")
                }
                context?.toastLongTime("카카오 지도와 연동되어 사용됩니다.\n설치를 하셔야 혼밥 찾기를 사용하실 수 있습니다.")
                val url = "https://play.google.com/store/apps/details?id=net.daum.android.map"
//                    https://map.kakao.com/?q=맛집&p=37.537229,127.005515
//                    https://play.google.com/store/apps/details?id=net.daum.android.map
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent)
            }

        }
    }

    private fun setMap() {


        if (playerLatitude == null || playerLongitude == null)
            mapView.setMapCenterPoint(
                MapPoint.mapPointWithGeoCoord(37.551444, 126.994359),
                true
            )
        else
            mapView.setMapCenterPoint(
                MapPoint.mapPointWithGeoCoord(
                    playerLatitude!!,
                    playerLongitude!!
                ), true
            )


        mapView.setZoomLevel(3, true)
        mapView.zoomIn(true)
        mapView.zoomOut(true)


    }

    private fun mapLocationPlus() {
        CoroutineScope(Dispatchers.Main).launch {
            var latitudRandom: Double
            var longitudeRandom: Double

            var i = 0
            while (i < 20) {

                mapView.addPOIItem(MapPOIItem().apply {

                    if (playerLatitude == null || playerLongitude == null) {
                        latitudRandom =
                            Math.random() * ((37.551444 + 0.005) - (37.551444 - 0.005)) + (37.551444 - 0.005)
                        longitudeRandom =
                            Math.random() * ((126.994359 + 0.005) - (126.994359 - 0.005)) + (126.994359 - 0.005)

                        itemName =
                            "서울 기준 혼밥집 위치를 잡아봤습니다.\n 빠른 혼밥 장소를 찾으시려면 상단버튼을 눌러주세요 "
                    } else {
                        latitudRandom =
                            Math.random() * (playerLatitude!! + 0.005 - playerLatitude!! - 0.005) + playerLatitude!! - 0.005
                        longitudeRandom =
                            Math.random() * (playerLongitude!! + 0.005 - playerLongitude!! - 0.005) + playerLongitude!! - 0.005

                        itemName =
                            "근처에 확인된 혼밥집들입니다.\n 빠른 혼밥 장소를 찾으시려면 상단버튼을 눌러주세요 "
                    }
                    tag = i
                    mapPoint = MapPoint.mapPointWithGeoCoord(latitudRandom, longitudeRandom)
                    markerType = MapPOIItem.MarkerType.BluePin // 기본으로 제공하는 BluePin 마커 모양.
                    selectedMarkerType =
                        MapPOIItem.MarkerType.RedPin // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
                })
                i++
            }
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
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    requireActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                DialogSimple.show(requireContext(),
                    "사용자의 위치권한 요청.",
                    "빠른 혼밥을 찾기위해 당신의 권한이 필요합니다." +
                            "걱정마세요 앱 나갈때마다 사용자의 위치정보는 리셋돼요 ",
                    "응 믿어볼게 ",
                    {
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            ),
                            REQUEST_ACCESS_COARSE_LOCATION_AND_ACCESS_FINE_LOCATION
                        )
                    },
                    "미안 거절할게",
                    { return@show })
            }

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
                                playerLatitude = location.latitude; playerLongitude =location.longitude
                                Log.d("opop8", "#$i ${location.latitude} , ${location.longitude}")
                            }
                        }
                    }
                }

            fusedLocationClient?.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper())
        }

    }

//    override fun onResume() {
//        if (fristRun)
//            mapViewContainer.addView(mapView)
//        else
//            fristRun = false
//
//        super.onResume()
//    }

    override fun onPause() {
        mapViewContainer.removeView(mapView)
        fusedLocationClient?.removeLocationUpdates(locationCallback)
        binding.mapViewAfter.visibility = View.VISIBLE
        super.onPause()
    }

//    override fun onStop() {

//        super.onStop()
}




