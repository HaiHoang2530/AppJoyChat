package haihqph05936.iris.appjoychat.model

import com.google.firebase.database.Exclude


data class User_model(
    val user_id_md:String?,
    var user_name_md: String?="",
     var password_md: String?="",
    var phone_md: Int = 0,
     var email_chat_md: String?="",
     var avarta_md: String?="",
    var isOnline_md: Boolean = false

)
