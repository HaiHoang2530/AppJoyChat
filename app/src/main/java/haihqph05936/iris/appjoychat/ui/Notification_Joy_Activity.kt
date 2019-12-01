package haihqph05936.iris.appjoychat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import haihqph05936.iris.appjoychat.R
import kotlinx.android.synthetic.main.activity_notification__joy_.*

class Notification_Joy_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification__joy_)
        search_img_notifition.setOnClickListener {
            val intent = Intent(this,Search_Joy_Activity::class.java)
            startActivity(intent)
        }
    }
}
