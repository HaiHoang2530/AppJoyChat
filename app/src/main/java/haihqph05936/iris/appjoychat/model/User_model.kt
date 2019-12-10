package haihqph05936.iris.appjoychat.model

import com.google.firebase.database.Exclude
import java.io.Serializable


data class User_model(
    val user_id_md: String? = "",
    var user_name_md: String? = "",
    var password_md: String? = "",
    var phone_md: Int = 0,
    var email_chat_md: String? = "",
    var avarta_md: String? = "",
    var isOnline_md: Boolean = false

):Serializable {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to user_id_md,
            "users " to user_name_md,
            "passwor" to password_md,
            "phone" to phone_md,
            "email" to email_chat_md,
            "avarta" to avarta_md,
            "isonline" to isOnline_md

        )
    }
}

