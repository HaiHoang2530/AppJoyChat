package haihqph05936.iris.appjoychat.presenter

import android.content.ContentValues.TAG
import android.text.style.UpdateAppearance
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.core.Context
import haihqph05936.iris.appjoychat.model.User_model
import haihqph05936.iris.appjoychat.views.Signup_joy_View
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener


class Signup_joy_presenter(signup_joy_view: Signup_joy_View, databaseReference: DatabaseReference) {

    var signup_joy_view = signup_joy_view
    private lateinit var id: String
    var database = databaseReference
    fun validatfont(user_name: String, pass: String, phone: Int, email: String) {
        if (user_name.trim().isEmpty()) {
            signup_joy_view.error()
            return
        } else if (pass.trim().isEmpty()) {
            signup_joy_view.error()

            return
        } else if (phone.toString().toInt() > 9 && phone.toString().toInt() < 11) {
            signup_joy_view.error()
            return
        } else if (email.trim().isEmpty()) {
            signup_joy_view.error()
            return
        }
        firebaseauth(user_name, pass, phone, email)

    }

    fun firebaseauth(user_name: String, pass: String, phone: Int, email: String) {
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    val post = dataSnapshot.getValue(User_model::class.java)
                    post?.let {
                        post.user_name_md = user_name
                        post.password_md = pass
                        post.phone_md = phone
                        post.email_chat_md = email

                    }

                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.d(TAG, databaseError.getMessage()) //Don't ignore errors!
            }
        }
        database.addListenerForSingleValueEvent(valueEventListener)
        signup_joy_view.navigateViews()

    }


}