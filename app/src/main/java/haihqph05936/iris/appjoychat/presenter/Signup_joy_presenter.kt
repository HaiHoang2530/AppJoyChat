package haihqph05936.iris.appjoychat.presenter

import android.util.Patterns
import com.google.firebase.database.*
import haihqph05936.iris.appjoychat.model.User_model
import haihqph05936.iris.appjoychat.views.Signup_joy_View


class Signup_joy_presenter(signup_joy_view: Signup_joy_View, databaseReference: DatabaseReference) {

    var signup_joy_view = signup_joy_view
    private lateinit var id: String
    var database = databaseReference
    fun validatfont(user_name: String, pass: String, phone: Int, email: String) {
        if (user_name.trim().isEmpty()) {
            signup_joy_view.error()
            return
        } else if (pass.length < 6) {
            signup_joy_view.error()

            return
        } else if (phone.toString().length < 9) {
            signup_joy_view.error()
            return
        } else if (email.trim().isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signup_joy_view.error()
            return
        }
        firebaseauth(user_name, pass, phone, email)

    }

    fun firebaseauth(user_name: String, pass: String, phone: Int, email: String) {
        id = database.push().key.toString()
        val Users =
            User_model(id, user_name, pass, phone, email, avarta_md = "", isOnline_md = false)
        database.child(user_name).setValue(Users).addOnCompleteListener {
            val postListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val post = dataSnapshot.getValue(User_model::class.java)
                    if (post==null){
                        val Users =
                            User_model(id, user_name, pass, phone, email, avarta_md = "", isOnline_md = false)
                        database.child(user_name).setValue(Users).addOnCompleteListener {  }
                        signup_joy_view.error()
                    }else{
                        signup_joy_view.navigateViews()
                    }

                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            }
            database.addListenerForSingleValueEvent(postListener)

        }


        /* signup_joy_view.navigateViews()*/

        /* val postListener = object : ValueEventListener {
             override fun onDataChange(dataSnapshot: DataSnapshot) {
                 val post = dataSnapshot.getValue(User_model::class.java)
                 if (post==null){
                     val Users =
                         User_model(id, user_name, pass, phone, email, avarta_md = null, isOnline_md = false)
                     database.child(user_name).setValue(Users).addOnCompleteListener {  }
                 }else{
                     signup_joy_view.error()
                 }

             }

             override fun onCancelled(databaseError: DatabaseError) {

             }
         }
         database.addListenerForSingleValueEvent(postListener)
 */

    }


}