package com.example.solitaryhelper.viewmodel
import android.content.Context
import androidx.lifecycle.ViewModel
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentSkillBinding
import com.example.solitaryhelper.view.itemDecoration.HorizontalMarginItemDecoration

class SkillViewModel:ViewModel() {


     fun setViewPagerSetting(viewPager: ViewPager2,context:Context) {
        /**
         * 복붙한거라서 시간내서 꼭 연구하기.
         */
            viewPager.offscreenPageLimit = 1


            // 다음 및 이전 항목을 수평으로 번역하는 PageTransformer 추가
            // 화면 중앙을 향하여 표시됩니다.

            val nextItemVisiblePx = context.resources.getDimension(R.dimen.viewpager_next_item_visible)
            val currentItemHorizontalMarginPx =
                context.resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
            val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx

            val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslationX * position
                // 다음 줄은 항목의 높이를 조정합니다. 이 효과를 원하지 않으면 제거 할 수 있습니다.

                page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
                // 페이딩 효과를 원하면 다음 줄의 주석 처리를 제거하십시오.
                // page.alpha = 0.25f + (1-abs (위치))
            }
            viewPager.setPageTransformer(pageTransformer)

            // ItemDecoration은 현재 (중앙) 항목의 가로 여백을 제공하므로
            // 전체 화면 너비를 차지하지 않습니다. 그것 없이는 항목이 겹칩니다.

            val itemDecoration = HorizontalMarginItemDecoration(
                context, R.dimen.viewpager_current_item_horizontal_margin)
            viewPager.addItemDecoration(itemDecoration)
    }
}