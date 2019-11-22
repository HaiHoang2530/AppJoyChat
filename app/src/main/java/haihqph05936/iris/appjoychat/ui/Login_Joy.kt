package haihqph05936.iris.appjoychat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import haihqph05936.iris.appjoychat.R
import kotlinx.android.synthetic.main.activity_login__joy.*

class Login_Joy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login__joy)
        login_btn.setOnClickListener {
            val intent =Intent(this,Home_Joy_Activity::class.java)
            startActivity(intent)
        }
        account_login_txt.setOnClickListener {
            val intent =Intent(this,Sign_In_Joy_Activity::class.java)
            startActivity(intent)
        }
    }
}
