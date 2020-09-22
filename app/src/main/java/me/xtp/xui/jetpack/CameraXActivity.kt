package me.xtp.xui.jetpack

import android.os.Bundle
import android.view.WindowManager
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_camerax.*
import me.xtp.xui.R
import me.xtp.xui.base.BaseActivity
import java.nio.ByteBuffer
import java.util.concurrent.Executors


class CameraXActivity : BaseActivity() {

	private var myAnalyzer : ImageAnalysis? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
		setContentView(R.layout.activity_camerax)

		startCamera()
	}

	private fun startCamera() {
		val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

		cameraProviderFuture.addListener({
			// Used to bind the lifecycle of cameras to the lifecycle owner
			val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

			// Preview
			val preview = Preview.Builder().build()
			preview.setSurfaceProvider(viewFinder.surfaceProvider)

			myAnalyzer = ImageAnalysis.Builder().build()
			myAnalyzer?.setAnalyzer(
				Executors.newSingleThreadExecutor(),
				{ image ->
					val buffer: ByteBuffer = image.planes[0].buffer
					val b = ByteArray(buffer.remaining())
					buffer.get(b)
					// 按你的需要处理图片吧
					image.close()
				}
			)

			// Select back camera as a default
			val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

			try {
				// Unbind use cases before rebinding
				cameraProvider.unbindAll()
				// Bind use cases to camera
				cameraProvider.bindToLifecycle(this, cameraSelector, preview, myAnalyzer)
			} catch (exc: Exception) {
				exc.printStackTrace()
			}

		}, ContextCompat.getMainExecutor(this))

	}
}