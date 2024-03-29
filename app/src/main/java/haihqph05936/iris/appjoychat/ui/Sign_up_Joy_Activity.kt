package haihqph05936.iris.appjoychat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import haihqph05936.iris.appjoychat.R
import haihqph05936.iris.appjoychat.model.User_model
import haihqph05936.iris.appjoychat.presenter.Signup_joy_presenter
import haihqph05936.iris.appjoychat.views.Signup_joy_View
import kotlinx.android.synthetic.main.activity_sign__in__joy_.*
import java.util.*

class Sign_up_Joy_Activity : AppCompatActivity(), Signup_joy_View {
    private lateinit var signup_joy_presenter: Signup_joy_presenter
    private lateinit var database: DatabaseReference


    override fun error() {
        Toast.makeText(this, "Vui lòng nhập lại !", Toast.LENGTH_LONG).show()
    }

    override fun navigateViews() {
        val intent = Intent(this, Login_Joy::class.java)
        startActivity(intent)
        Toast.makeText(this, "Đăng Ký thành công!", Toast.LENGTH_LONG).show()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign__in__joy_)

        database = FirebaseDatabase.getInstance().reference.child("Users")
        signup_joy_presenter = Signup_joy_presenter(this, database)
        sign_btn.setOnClickListener {
            signup_joy_presenter?.validatfont(
                user_sgin_edt.text.toString().trim()
                ,
                password_sign_edt.text.toString().trim(),
                phone_sign_edt.text.trim().toString().toInt(),
                email_sign_edt.text.toString().trim()
            )

        }

    }


}

