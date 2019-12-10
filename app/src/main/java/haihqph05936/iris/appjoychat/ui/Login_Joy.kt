package haihqph05936.iris.appjoychat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserManager
import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import haihqph05936.iris.appjoychat.R
import haihqph05936.iris.appjoychat.model.User_model
import haihqph05936.iris.appjoychat.presenter.Login_joy_Presenter
import haihqph05936.iris.appjoychat.views.Login_joy_View
import kotlinx.android.synthetic.main.activity_login__joy.*

class Login_Joy : AppCompatActivity(), Login_joy_View {
    private lateinit var loginPresenter: Login_joy_Presenter
    private lateinit var database: DatabaseReference
    override fun loginJoy(username: String, id: String) {
        val intent = Intent(this, Notification_Joy_Activity::class.java)
        intent.putExtra("username",username)
        intent.putExtra("fcmId",id)
        startActivity(intent)
    }
    override fun error() {
        Toast.makeText(this, "Vui lòng nhập lại!", Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login__joy)
        database = FirebaseDatabase.getInstance().reference.child("Users")
        loginPresenter = Login_joy_Presenter(this, database)
        login_btn.setOnClickListener {
            loginPresenter.isvalidatform(
                user_login_edt.text.toString().trim(), password_login_edt.text.toString().trim()
            )
        }
        account_login_txt.setOnClickListener {
            val intent = Intent(this, Sign_up_Joy_Activity::class.java)
            startActivity(intent)
        }
    }
}
