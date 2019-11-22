package haihqph05936.iris.appjoychat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import haihqph05936.iris.appjoychat.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       Handler().postDelayed({
           val  maniIntent= Intent(this,Login_Joy::class.java)
           startActivity(maniIntent)
           finish()
       },3000)
    }
}
