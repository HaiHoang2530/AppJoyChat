package haihqph05936.iris.appjoychat.model

import java.util.*


class Joy_chat_Model {

    /*private var text_joy: String = ""
    private var time: Long = 0
    private var sendBy: String = ""
    private var type: Int = 0
    private var rec: String = ""
*/


    public  var text_joy: String=""
    public var time: Long = 0
    public var rec: String? = null
    public var sendBy: String=""
    public var type: Int = 0



    fun getMessageTime(): Long {
        return time
    }


    fun getMessageText(): String {
        return text_joy
    }


    fun Chat() {}

    fun Chat(messageText: String, send: String, type: Int,recc:String){
        this.text_joy = messageText
        this.sendBy = send
        this.type = type
        time = Date().time
    }

}