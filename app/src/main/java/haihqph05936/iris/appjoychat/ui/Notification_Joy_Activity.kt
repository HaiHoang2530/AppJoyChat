package haihqph05936.iris.appjoychat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import haihqph05936.iris.appjoychat.R
import haihqph05936.iris.appjoychat.adapter.UserAdapter
import haihqph05936.iris.appjoychat.model.User_model
import kotlinx.android.synthetic.main.activity_notification__joy_.*
import kotlinx.android.synthetic.main.custom_dialog_activity.*
import kotlinx.android.synthetic.main.custom_dialog_activity.view.*
import java.util.ArrayList

class Notification_Joy_Activity : AppCompatActivity() {
    lateinit var adapter: UserAdapter
    lateinit var database: DatabaseReference
    lateinit var userModel: User_model
    var users = arrayListOf<User_model>()
    lateinit var username: String
    lateinit var idlogin: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification__joy_)
        //recycler view
        ry_notifition.layoutManager = LinearLayoutManager(this)
        ry_notifition.setHasFixedSize(true)
        username = this.intent.getStringExtra("username")
        idlogin = this.intent.getStringExtra("fcmId")
        txt_chat.setText(username)
        database = FirebaseDatabase.getInstance().reference.child("Users")
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (npsnapshot in dataSnapshot.children) {
                        userModel = npsnapshot.getValue(User_model::class.java)!!
                        users.add(userModel)


                    }
                    adapter = UserAdapter(this@Notification_Joy_Activity, users, username, idlogin)
                    ry_notifition.adapter = adapter
                    adapter.notifyDataSetChanged()
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        database.addListenerForSingleValueEvent(postListener)
        search_img_notifition.setOnClickListener {
            val intent = Intent(this, Search_Joy_Activity::class.java)
            startActivity(intent)
        }
        img_plus.setOnClickListener {
            val dialogview =
                LayoutInflater.from(this).inflate(R.layout.custom_dialog_activity, null)
            val mbuldel = AlertDialog.Builder(this).setView(dialogview)
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
