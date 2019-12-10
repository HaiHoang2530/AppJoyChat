package haihqph05936.iris.appjoychat.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        user_seen.setText(username)
        dataref = FirebaseDatabase.getInstance().getReference("Messager")
        ref = dataref.child(userfriend).child(username).child("massegerlist")
        rec = dataref.child(username).child(userfriend).child("massegerlist")
        change = dataref.child(username).child(userfriend)
        val postListener = object : ChildEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                val comment = p0.getValue(Joy_chat_Model::class.java!!)
                listchat.add(comment!!)
                for (i in listchat.indices) {
                    val query = ref.child("Room").child("ListMess")
                        .orderByChild(listchat.get(i).text_joy)
                    query.limitToFirst(5)
                }

                adapter = Chat_Adapter(this@Chat_Seen_Joy_Activity, listchat, idfriend)
                ry_chat.setAdapter(adapter)
            }

            override fun onChildRemoved(p0: DataSnapshot) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
        ref.addChildEventListener(postListener)
        val manager1 = LinearLayoutManager(this)
        manager1.stackFromEnd = true
        manager1.orientation = RecyclerView.VERTICAL
        ry_chat.scrollToPosition(listchat.size - 1)
        ry_chat.setLayoutManager(manager1)
        img_seen.setOnClickListener {

            if (edt_text_chat.equals("")) {
                val intent = Intent(this,Login_Joy::class.java)
            } else if (edt_text_chat.text.toString().equals("")) {
                Toast.makeText(this, "Hay viet gi do", Toast.LENGTH_LONG).show()
            } else {
                val sender = mutableMapOf<String, String>()
                val users = mutableMapOf<String, String>()
                val id = mutableMapOf<String, String>()
                users["title"] = userfriend
                users["body"] = edt_text_chat.text.toString()
                sender["notification"] = users.toString()
                sender["to"] = idusers
                id["send"] = userfriend
                id["rec"] = username
                change.updateChildren(id as Map<String, Any>)
                val chat = Joy_chat_Model(text_joy = "", time = 0, sendBy = "", rec = "", type = 0)
                chat.text_joy = edt_text_chat.text.toString()
                chat.rec = idfriend
                chat.type
                rec.push().setValue(
                    chat

                )
                ref.push().setValue(chat)
            }
        }

    }

}
