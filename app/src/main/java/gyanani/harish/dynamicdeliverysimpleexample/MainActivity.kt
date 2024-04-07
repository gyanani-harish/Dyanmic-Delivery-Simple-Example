package gyanani.harish.dynamicdeliverysimpleexample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener

private const val DYNAMIC_MODULE_FACE_DETECTOR = "facedetector"

class MainActivity : AppCompatActivity() {
    private lateinit var manager: SplitInstallManager
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById<TextView>(R.id.textView)
            .apply {
                text = "App Module Main Activity"
                setOnClickListener {
                    onTextClicked()
                }
            }
    }

    private fun onTextClicked() {
        val request = SplitInstallRequest.newBuilder()
            .addModule(DYNAMIC_MODULE_FACE_DETECTOR)
            .build()

        manager = SplitInstallManagerFactory.create(this)

        manager.startInstall(request)
            .addOnSuccessListener { sessionId -> /* Handle success */
                textView.text = buildString {
                    append(textView.text.toString())
                    append("\nModule Downloaded")
                }
                Log.i("HarishJi", "Module Downloaded")
                if (manager.installedModules.contains(DYNAMIC_MODULE_FACE_DETECTOR)) {
                    try {
                        val intent = Intent().setClassName(
                            packageName,
                            "gyanani.harish.dynamicdeliverysimpleexample.facedetector.FaceDetectorActivity"
                        )
                        startActivity(intent)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        textView.text = buildString {
                            append(textView.text.toString())
                            append(" \nstartActivity exception=")
                            append(e.toString())
                        }
                        Log.e("HarishJi", "startActivity exception=", e)
                    }
                } else {
                    textView.text = buildString {
                        append(textView.text.toString())
                        append(" \nModule is not installed; handle accordingly")
                    }
                    Log.e("HarishJi", "Module is not installed; handle accordingly")
                }

            }
            .addOnFailureListener { exception -> /* Handle failure */
                textView.text = buildString {
                    append(textView.text.toString())
                    append(" \nISSUE IN MODULE DOWNLOADING")
                    append(exception.toString())
                }
                Log.e("HarishJi", "ISSUE IN MODULE DOWNLOADING", exception)
            }
    }


    val listener = SplitInstallStateUpdatedListener { state ->
        if (state.moduleNames().contains(DYNAMIC_MODULE_FACE_DETECTOR)) {
            if (this::textView.isInitialized) {
                textView.text = buildString {
                    append(textView.text.toString())
                    append(" \n")
                    append(state.toString())
                }
            }
            Log.i("HarishJi", "Status=" + state.toString())
            // Handle state changes (downloading, installed, etc.)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        SplitCompat.installActivity(this)
    }

    override fun onStart() {
        super.onStart()
        if (this::manager.isInitialized) {
            manager.registerListener(listener)
        }
    }

    override fun onStop() {
        super.onStop()
        if (this::manager.isInitialized) {
            manager.unregisterListener(listener)
        }
    }
}