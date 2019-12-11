package haihqph05936.iris.appjoychat.adapter

import android.content.Context
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import haihqph05936.iris.appjoychat.R
import haihqph05936.iris.appjoychat.model.Joy_chat_Model
import kotlinx.android.synthetic.main.item_chat_friend.view.*
import kotlinx.android.synthetic.main.item_chat_seen.view.*

class Chat_Adapter(
    var context: Context,
    var listChat: MutableList<Joy_chat_Model>,
    var peo: String
) :
    RecyclerView.Adapter<Chat_Adapter.MyViewHolder>() {
    private val TYPE_RECEIVE_MESSAGE = 0
    private val TYPE_SEND_MESSAGE = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var layout = -1
        when (viewType) {
            TYPE_RECEIVE_MESSAGE -> layout = R.layout.item_chat_seen

            TYPE_SEND_MESSAGE -> layout = R.layout.item_chat_friend
        }

        val v = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)

        return MyViewHolder( v)
    }

    override fun getItemCount(): Int {
        return listChat.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val chat = listChat.get(position)
        //holder.txt_text_chat.text= chat.getMessageText()
        Log.e("mmm",chat.getMessageText())
       /* holder.txt_text_chat.setText(chat.text_joy)*/
        //holder.txt_time_chat.setText(DateFormat.format("hh:mm", chat.time))
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txt_text_chat = itemView.tv_mess_chat
        var txt_time_chat = itemView.tv_time_chat

    }


}