package itamar.stern.barcodescanner.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import itamar.stern.barcodescanner.MainActivity
import itamar.stern.barcodescanner.R
import itamar.stern.barcodescanner.databinding.ActivityAskPermissionBinding
import itamar.stern.barcodescanner.databinding.ActivityMainBinding

class AskPermissionActivity : AppCompatActivity() {
    private val requestCodeCameraPermission = 1001
    private lateinit var binding: ActivityAskPermissionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAskPermissionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (ContextCompat.checkSelfPermission(
                this@AskPermissionActivity,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            askForCameraPermission()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }


        binding.button.setOnClickListener {
            askForCameraPermission()
        }
    }

    private fun askForCameraPermission() {
        ActivityCompat.requestPermissions(
            this@AskPermissionActivity,
            arrayOf(Manifest.permission.CAMERA),
            requestCodeCameraPermission
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == requestCodeCameraPermission && grantResults.isNotEmpty()){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                binding.textView.visibility = View.VISIBLE
                binding.button.visibility = View.VISIBLE
            }
        }
    }
}