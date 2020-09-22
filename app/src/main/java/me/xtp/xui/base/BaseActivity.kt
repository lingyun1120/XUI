package me.xtp.xui.base

import androidx.appcompat.app.AppCompatActivity
import com.gyf.immersionbar.ImmersionBar

open class BaseActivity : AppCompatActivity() {
    override fun onResume() {
        super.onResume()
        ImmersionBar.with(this)
            .statusBarDarkFont(true)
            .navigationBarEnable(false)
            .init()
    }
}