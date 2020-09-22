package me.xtp.xui.jetpack

import android.os.Build
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.nextUp
import kotlin.math.sign

/**
 * 循环卡片效果
 */
class ViewPager2Transformer : ViewPager2.PageTransformer {
    private val minScale = 0.88

    private var curPrimary: View? = null

    override fun transformPage(page: View, position: Float) {
        val isPrimary = abs(position) <= 0.3f

        //change child order
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            page.elevation = if (page != curPrimary) -abs(position) else 1.0f
        }

        //scale
        var offset = abs(position)
        if (offset > 1.0) {
            offset = 1.0f
        }
        val fraction = (1 - minScale) * offset
        val scale = (1 - fraction).toFloat()
        page.scaleY = scale
        page.scaleX = scale


        //translation
        val offsetByScale = page.width * (1 - scale) / 2
        val itemPosition = abs(position).nextUp()
        val finalOffset = offsetByScale + (page.width * scale * itemPosition) / 2

        val direction = -sign(position)
        val cardTrans = finalOffset * direction * abs(position)
        page.translationX = cardTrans
    }

}