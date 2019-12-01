package haihqph05936.iris.appjoychat.presenter

import android.text.style.UpdateAppearance
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.core.Context
import haihqph05936.iris.appjoychat.views.Signup_joy_View

class Signup_joy_presenter(signup_joy_view: Signup_joy_View, auth: FirebaseAuth) {
    var mauth = auth
    var signup_joy_view = signup_joy_view


    fun firebaseauth(user_name: String, pass: String, phone: Int, email: String) {


        mauth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    navidate_signup(user_name,pass,phone,email)
                } else {
                   signup_joy_view.error()
                }


            }

    }

    fun validatfont(user_name: String, pass: String, phone: Int, email: String) {
        signup_joy_view.inValidatInfor()
        if (user_name.trim().isEmpty()) {
            signup_joy_view.error()
            return
        } else if (pass.trim().isEmpty()) {
            signup_joy_view.error()

            return
        } else if (pass.length > 6) {
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

    fun navidate_signup(user_name: String,pass: String,phone: Int,email: String) {
        val currenUser =FirebaseAuth.getInstance().currentUser!!.uid
        val userRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("Users")
        val userMap  = HashMap<String,Any>()
        userMap["userID"]= currenUser
        userMap["user_name"]= currenUser
        userMap["password"]= currenUser
        userMap["phone"]= currenUser
        userMap["email"]= currenUser
        userMap["avarta"]= "gs://appjoychat.appspot.com/images/download.jpg"
        userMap["isOnline"]=false
        userRef.child(currenUser).setValue(userMap)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){

                }
            }


        signup_joy_view.navigateViews()
    }

}