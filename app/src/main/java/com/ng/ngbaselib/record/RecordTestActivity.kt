package com.ng.ngbaselib.record

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.os.IBinder
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ng.ngbaselib.BaseActivity
import com.ng.ngbaselib.BaseViewModel
import com.ng.ngbaselib.R
import com.ng.ngbaselib.databinding.ActivityRecordBinding


/**
 * 描述:
 * @author Jzn
 * @date 2021/1/13
 */
class RecordTestActivity : BaseActivity<BaseViewModel, ActivityRecordBinding>() {
    override fun createViewBinding(): ActivityRecordBinding = ActivityRecordBinding.inflate(
        layoutInflater
    )

    private var projectionManager: MediaProjectionManager? = null
    private var recordService: RecordService? = null


    override fun createViewModel(): BaseViewModel? = null

    override fun layoutId(): Int = R.layout.activity_record

    override fun initView(savedInstanceState: Bundle?) {

        projectionManager = getSystemService(MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        mBinding!!.recordBtn.isEnabled = false
        mBinding!!.recordBtn.setOnClickListener {
            if (recordService!!.isRunning) {
                recordService!!.stopRecord()
                mBinding!!.recordBtn.text = "开始"
            } else {
                recordService!!.startRecord()
                mBinding!!.recordBtn.text = "停止"

            }
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            !== PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                STORAGE_REQUEST_CODE
            )
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            !== PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                AUDIO_REQUEST_CODE
            )
        }
        val intent = Intent(this, RecordService::class.java)
        bindService(intent, connection, BIND_AUTO_CREATE)

        val captureIntent = projectionManager!!.createScreenCaptureIntent()
        startActivityForResult(captureIntent, RECORD_REQUEST_CODE)
    }

    override fun initData() {
    }

    override fun onRetryBtnClick() {
    }

    private val RECORD_REQUEST_CODE = 101
    private val STORAGE_REQUEST_CODE = 102
    private val AUDIO_REQUEST_CODE = 103


    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RECORD_REQUEST_CODE && resultCode == RESULT_OK) {
//            try {
//                val mWindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
//                val metrics = DisplayMetrics()
//                mWindowManager.defaultDisplay.getMetrics(metrics)
//            } catch (e: Exception) {
//                Log.e(TAG, "MediaProjection error")
//            }

            val service = Intent(this, RecordService::class.java)
            service.putExtra("code", resultCode)
            service.putExtra("data", data)
            startForegroundService(service)

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == STORAGE_REQUEST_CODE || requestCode == AUDIO_REQUEST_CODE) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                finish()
            }
        }
    }


    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val metrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(metrics)
            val binder: RecordService.RecordBinder = service as RecordService.RecordBinder
            recordService = binder.getRecordService()
            recordService!!.setConfig(metrics.widthPixels, metrics.heightPixels, metrics.densityDpi)
            mBinding!!.recordBtn.isEnabled = true
            mBinding!!.recordBtn.text = (if (recordService!!.isRunning) getString(R.string.stop_record) else getString(
                R.string.start_record
            ))
        }

        override fun onServiceDisconnected(arg0: ComponentName) {}
    }
}