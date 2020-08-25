package com.example.solitaryhelper.view.dest.main.tapfragments

import android.view.ViewGroup
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentEatingAloneBinding
import com.example.solitaryhelper.view.base.BaseFragment
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import  net.daum.mf.map.api.MapView


class FragmentEatingAlone:BaseFragment<FragmentEatingAloneBinding>(R.layout.fragment_eating_alone) {
    override fun FragmentEatingAloneBinding.setEventListener() {


    }

    override fun FragmentEatingAloneBinding.setCreateView() {
        val map = MapView(requireContext())
        mapView.addView(map)
        val marker = MapPOIItem()

//
//        marker.itemName = "(주)블루모바일"
//        marker.tag = 0
//        marker.mapPoint =
//        marker.markerType = MapPOIItem.MarkerType.BluePin
//        marker.selectedMarkerType = MapPOIItem.MarkerType.RedPin
//
//        map.addPOIItem(marker)
//        map.setDefaultCurrentLocationMarker()
    }
}