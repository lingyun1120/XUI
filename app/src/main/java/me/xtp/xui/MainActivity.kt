package me.xtp.xui

import android.Manifest
import android.content.Intent
import android.os.Bundle
import com.permissionx.guolindev.PermissionX
import kotlinx.android.synthetic.main.activity_main.*
import me.xtp.xui.jetpack.CameraXActivity
import me.xtp.xui.base.BaseActivity
import me.xtp.xui.jetpack.ViewPager2Activity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCamera.setOnClickListener {
            PermissionX.init(this)
                .permissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request { allGranted, grantedList, deniedList ->
                    if (allGranted) {
                        val intent = Intent(this@MainActivity, CameraXActivity::class.java)
                        startActivity(intent)
                    } else {
                    }
                }
        }

        btnVP2.setOnClickListener {
            val intent = Intent(this@MainActivity, ViewPager2Activity::class.java)
            startActivity(intent)
        }
    }
}