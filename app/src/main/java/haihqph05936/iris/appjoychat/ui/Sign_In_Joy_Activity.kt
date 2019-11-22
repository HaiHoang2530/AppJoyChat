package haihqph05936.iris.appjoychat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import haihqph05936.iris.appjoychat.R
import kotlinx.android.synthetic.main.activity_sign__in__joy_.*

class Sign_In_Joy_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign__in__joy_)
        sign_btn.setOnClickListener {
            val inten =Intent(this,Login_Joy::class.java)
            startActivity(inten)
        }
    }
}
