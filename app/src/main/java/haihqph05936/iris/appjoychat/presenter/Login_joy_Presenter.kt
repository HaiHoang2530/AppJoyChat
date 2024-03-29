package haihqph05936.iris.appjoychat.presenter


import com.google.firebase.database.*
import haihqph05936.iris.appjoychat.model.User_model

import haihqph05936.iris.appjoychat.views.Login_joy_View
import java.util.HashMap


class Login_joy_Presenter(
    internal val loginView: Login_joy_View,
    databaseReference: DatabaseReference
) {
    var database = databaseReference
    fun isvalidatform(user_name: String, password: String) {
        if (user_name.isEmpty()) {
            loginView.error()
        } else if (password.isEmpty()) {
            loginView.error()
        }
        loginJoy(user_name, password)
    }

    fun loginJoy(user_name: String, password: String) {
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                if (dataSnapshot.getValue() == null) {
                    loginView.error()
                } else {
                    val post = dataSnapshot.getValue(User_model::class.java)
                    if (post?.user_name_md.equals(user_name) && post?.password_md.equals(password)) {
                        val id = post?.user_id_md
                        if (id != null) {
                            loginView.loginJoy(user_name, id)
                        }

                    } else {
                        loginView.error()
                    }
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        }
        database.child(user_name).addValueEventListener(postListener)
    }
}


