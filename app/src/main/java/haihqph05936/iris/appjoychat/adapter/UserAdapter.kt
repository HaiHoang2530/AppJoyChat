package haihqph05936.iris.appjoychat.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import haihqph05936.iris.appjoychat.R
import haihqph05936.iris.appjoychat.model.Joy_chat_Model
import haihqph05936.iris.appjoychat.model.User_model
import haihqph05936.iris.appjoychat.ui.Chat_Seen_Joy_Activity
import kotlinx.android.synthetic.main.item_user.view.*


class UserAdapter(
    var context: Context, var userlist: ArrayList<User_model>,
    var usernsem: String, var idlogin: String


) :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    lateinit var database: DatabaseReference


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var users: User_model = userlist.get(position)
        holder.txt_view_name.setText(users.user_name_md)
        if (userlist.get(position).user_name_md.equals(usernsem)) {
            holder.itemView!!.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, Chat_Seen_Joy_Activity::class.java)
            intent.putExtra("username", users.user_name_md)
            intent.putExtra("fcmId", users.user_id_md)
            intent.putExtra("sender", usernsem)
            intent.putExtra("senderfcm", idlogin)
            database = FirebaseDatabase.getInstance().reference.child("Messager").child(usernsem).child(
                users.user_name_md.toString()
            )
            val postListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.getValue()==null){
                        val chat = Joy_chat_Model(text_joy = "",time = 0,sendBy = "",rec = "",type = 0)
                       chat.rec= users.user_name_md.toString()
                        chat.sendBy=usernsem
                        database.setValue(chat,
                            DatabaseReference.CompletionListener { databaseError, databaseReference ->
                            })
                    }else{

                    }

                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            }
            database.addValueEventListener(postListener)
            context.startActivity(intent)
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_view_name = itemView.txt_name
        val txt_view_time = itemView.txt_time
        val txt_view_text = itemView.txt_text
        val img_view = itemView.img_view

    }


}