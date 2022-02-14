package itamar.stern.barcodescanner.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.util.isNotEmpty
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import itamar.stern.barcodescanner.R
import itamar.stern.barcodescanner.ScannerApplication
import itamar.stern.barcodescanner.databinding.FragmentScanBinding
import itamar.stern.barcodescanner.models.HistoryItem
import java.lang.Exception
const val TAG = "myLog"

class ScanFragment : Fragment() {

    private lateinit var binding: FragmentScanBinding
    private lateinit var cameraSource: CameraSource
    private lateinit var detector: BarcodeDetector

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setupControls()
        binding.buttonScanAgain.visibility = View.VISIBLE
        binding.buttonScanAgain.setOnClickListener {
            setupControls()
            binding.cameraSurfaceView.visibility = View.VISIBLE
        }
    }

    private fun setupControls() {
        detector = BarcodeDetector.Builder(requireContext()).build()
        cameraSource = CameraSource.Builder(requireContext(), detector)
            .setAutoFocusEnabled(true)
            .build()
        binding.cameraSurfaceView.holder.addCallback(surfaceCallback)
        detector.setProcessor(processor)
    }

    private val surfaceCallback = object : SurfaceHolder.Callback {
        @SuppressLint("MissingPermission")
        override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
            try {
                cameraSource.start(surfaceHolder)
            } catch (e: Exception){
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {
            cameraSource.stop()
        }

    }

    private val processor = object : Detector.Processor<Barcode>{
        override fun release() {
        }

        override fun receiveDetections(detections: Detector.Detections<Barcode>) {
            if(detections.detectedItems.isNotEmpty()){
                val qrCodes : SparseArray<Barcode> = detections.detectedItems
                val code = qrCodes.valueAt(0)
                binding.textScanResult.text = code.displayValue
                ScannerApplication.roomDB.historyDao().addHistory(
                    HistoryItem(code.displayValue)
                )
                binding.cameraSurfaceView.visibility = View.GONE


            } else {
                binding.textScanResult.text = ""
            }
        }

    }

}