package gyanani.harish.dynamicfeature2;

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BarcodeActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode)
        findViewById<TextView>(R.id.textView).text = "Barcode Module Activity"
    }
}
