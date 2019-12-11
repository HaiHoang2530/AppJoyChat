package haihqph05936.iris.appjoychat.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import haihqph05936.iris.appjoychat.R
import haihqph05936.iris.appjoychat.adapter.Chat_Adapter
import haihqph05936.iris.appjoychat.model.Joy_chat_Model
import haihqph05936.iris.appjoychat.model.User_model
import kotlinx.android.synthetic.main.activity_chat__seen__joy_.*
import java.util.ArrayList
import java.util.HashMap

class Chat_Seen_Joy_Activity : AppCompatActivity() {
    lateinit var adapter: Chat_Adapter
    lateinit var dataref: DatabaseReference
    lateinit var change: DatabaseReference
    lateinit var ref: DatabaseReference
    lateinit var rec: DatabaseReference
    var type: Int = 0
    lateinit var username: String
    lateinit var idfriend: String
    lateinit var userfriend: String
    lateinit var idusers: String
    internal var listchat: MutableList<Joy_chat_Model> = ArrayList<Joy_chat_Model>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat__seen__joy_)
        username = this.intent.getStringExtra("username")
        idusers = this.intent.getStringExtra("fcmId")
        userfriend = this.intent.getStringExtra("sender")
        idfriend = this.intent.getStringExtra("senderfcm")

        user_seen.setText(userfriend)
        dataref = FirebaseDatabase.getInstance().getReference("Messager")
        ref = dataref.child(userfriend).child(username).child("massegerlist")
        rec = dataref.child(username).child(userfriend).child("massegerlist")

        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val comment = dataSnapshot.getValue(Joy_chat_Model::class.java)
                listchat.add(comment!!)
                adapter = Chat_Adapter(this@Chat_Seen_Joy_Activity, listchat, idfriend)
                ry_chat.setAdapter(adapter)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {


                val newComment = dataSnapshot.getValue(Joy_chat_Model::class.java)
                val commentKey = dataSnapshot.key

                // ...
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                val commentKey = dataSnapshot.key

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {

                val movedComment = dataSnapshot.getValue(Joy_chat_Model::class.java)
                val commentKey = dataSnapshot.key

                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        ref.addChildEventListener(childEventListener)
        val manager1 = LinearLayoutManager(this)
        manager1.stackFromEnd = true
        manager1.orientation = RecyclerView.VERTICAL
        ry_chat.scrollToPosition(listchat.size - 1)
        ry_chat.setLayoutManager(manager1)
        img_seen.setOnClickListener {
            if (edt_text_chat.text.toString().equals("")) {
                Toast.makeText(this, "Hay viet gi do", Toast.LENGTH_LONG).show()
            } else {
                val map2 = mutableMapOf<String, String>()
                val map = mutableMapOf<String, String>()
                val id = mutableMapOf<String, String>()
                map2["title"] = userfriend
                map2["body"] = edt_text_chat.text.toString()
                map["notification"] = map2.toString()
                map["to"] = idusers
                id["send"]=userfriend
                id["rec"]=username
                val chat =Joy_chat_Model()
                chat.text_joy = edt_text_chat.text.toString()
                chat.rec = idfriend
                chat.type
                rec.push().setValue(chat)
                ref.push().setValue(chat)

            }
        }
        img_back_chat.setOnClickListener { finish() }

    }

}
