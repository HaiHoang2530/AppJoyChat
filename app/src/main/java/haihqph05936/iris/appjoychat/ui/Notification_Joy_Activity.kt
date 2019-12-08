package haihqph05936.iris.appjoychat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import haihqph05936.iris.appjoychat.R
import kotlinx.android.synthetic.main.activity_notification__joy_.*
import kotlinx.android.synthetic.main.custom_dialog_activity.*
import kotlinx.android.synthetic.main.custom_dialog_activity.view.*
import kotlinx.android.synthetic.main.custom_dialog_activity.view.edt_dialog_Them

class Notification_Joy_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification__joy_)
        search_img_notifition.setOnClickListener {
            val intent = Intent(this,Search_Joy_Activity::class.java)
            startActivity(intent)
        }
        img_plus.setOnClickListener {
        val dialogview =LayoutInflater.from(this).inflate(R.layout.custom_dialog_activity,null)
            val mbuldel= AlertDialog.Builder(this).setView(dialogview)
            val maletdialo = mbuldel.show()
            dialogview.btn_Kthem_name.setOnClickListener {
                maletdialo.dismiss()
            }
            dialogview.btn_them_name.setOnClickListener {
                edt_dialog_Them.text.toString().trim()


            }
        }
    }
}
