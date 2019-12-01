package haihqph05936.iris.appjoychat.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import haihqph05936.iris.appjoychat.R
import haihqph05936.iris.appjoychat.presenter.Signup_joy_presenter
import haihqph05936.iris.appjoychat.views.Signup_joy_View
import kotlinx.android.synthetic.main.activity_sign__in__joy_.*
import java.util.*

class Sign_In_Joy_Activity : AppCompatActivity(), Signup_joy_View {
    lateinit var signup_joy_presenter: Signup_joy_presenter
    private lateinit var mauthue: FirebaseAuth

    override fun error() {
        Toast.makeText(this, "Vui long nhap lai!", Toast.LENGTH_LONG).show()
    }

    override fun inValidatInfor() {

    }

    override fun createUsers() {

    }

    override fun navigateViews() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val mSignup_joy_presenter: Signup_joy_presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign__in__joy_)
        mauthue = FirebaseAuth.getInstance()
        signup_joy_presenter = Signup_joy_presenter(this, mauthue)
        sign_btn.setOnClickListener {
            signup_joy_presenter?.validatfont(
                user_sgin_edt.text.toString().trim()
                , password_sign_edt.text.toString().trim(),
                phone_sign_edt.text.toString().trim().toInt(), email_sign_edt.text.toString().trim()
            )
        }

    }

    public override fun onStart() {
        super.onStart()
        //check if users
        val currentUser = mauthue.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser: FirebaseUser?) {

    }


}
