package haihqph05936.iris.appjoychat.model

import android.text.TextUtils
import android.util.Patterns


data class User_model(
    public var user_name: String,
    public var password: String,
    public var phone: Int,
    public var email_chat: String,
    public var avarta: String,
    public var isOnline: Boolean = false
)