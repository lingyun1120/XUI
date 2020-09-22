package me.xtp.xui.jetpack

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_viewpager2.*
import me.xtp.xui.R
import me.xtp.xui.base.BaseActivity

class ViewPager2Activity : BaseActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_viewpager2)

		initView()
	}

	private fun initView() {
		viewPager.offscreenPageLimit = 1
		viewPager.setPageTransformer(ViewPager2Transformer())
		val adapter = ViewPager2Adapter()
		viewPager.adapter = adapter
	}
}