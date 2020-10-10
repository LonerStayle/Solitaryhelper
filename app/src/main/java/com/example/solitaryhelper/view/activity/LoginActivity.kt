package com.example.solitaryhelper.view.activity

import android.content.Intent
import android.util.Log
import android.view.View
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ActivityLoginBinding
import com.example.solitaryhelper.databinding.DialogPhoneauthBinding
import com.example.solitaryhelper.view.base.BaseActivity
import com.example.solitaryhelper.view.dialog.DialogCustom
import com.example.solitaryhelper.view.utill.toastShort
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.*
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val phoneAuth by lazy {
        PhoneAuthProvider.getInstance()
    }

    private val auth by lazy { FirebaseAuth.getInstance() }

    private val dialog by lazy {
        DialogCustom<DialogPhoneauthBinding>(
            this,
            R.layout.dialog_phoneauth,
            R.style.Theme_AppCompat_Light_Dialog_Alert
        )
    }

    private lateinit var verificationId: String
    private lateinit var varFieldVerificationCode: String
    private var time = 0
    private var timerTask: TimerTask? = null
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    override fun ActivityLoginBinding.setData() {
        loginActivity = this@LoginActivity
        dialog.dialogViewCreate()
        setVisibleControl(View.GONE)
        buttonMain.setOnClickListener {
            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
        }
    }

    fun setButtonPhoneAuthSignUpClickListener(v: View) {
        dialog.dialogCreate.show()
        dialog.dialogCustomCreateBinding.loginActivity = this@LoginActivity
    }


    fun setButtonPhoneNumberCheckClickListener(v: View) {
        val phoneNumber = dialog.dialogCustomCreateBinding.editTextPhoneNumber.toString()

        phoneAuth.verifyPhoneNumber(
            phoneNumber, 120,
            TimeUnit.SECONDS, this,
            phoneAuthComplete
        )
        Log.d("phoneAuthCheck", "확인 버튼 클릭 확인")
    }

    private val phoneAuthComplete =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                setVisibleControl(View.VISIBLE)
                Log.d("phoneAuthCheck", "Complete?")
                timerTask = object : TimerTask() {
                    override fun run() {
                        runOnUiThread {
                            time++
                            dialog.dialogCustomCreateBinding.textViewCountTime.text =
                                String.format("남은 시간:0%d", time)

                            if (time == 120) {
                                time = 0
                                timerTask!!.cancel()
                            }
                        }
                    }
                }
            }

            override fun onVerificationFailed(firebaseException: FirebaseException) {
                Log.d("phoneAuthCheck", "why 안돼$firebaseException")
                applicationContext.toastShort("전화번호를 다시 확인해주세요")
                return
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                this@LoginActivity.verificationId = verificationId
                resendToken = token
                super.onCodeSent(verificationId, token)
            }
        }

    private fun setVisibleControl(visible: Int) {
        for (i in 3 until dialog.dialogCustomCreateBinding.layoutRoot.childCount) {
            dialog.dialogCustomCreateBinding.layoutRoot.getChildAt(i).visibility = visible
        }
    }

    fun setButtonPhoneAuthRequestCheckClickListener(v: View) {
        varFieldVerificationCode =
            dialog.dialogCustomCreateBinding.editTextPhoneNumberCheck.text.toString()
    }

    fun setButtonPhoneNumberAuthCreateIdClickListener(v: View) {
        val credential = PhoneAuthProvider.getCredential(verificationId, varFieldVerificationCode)
        auth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                startActivity(Intent(this, MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
                })
            } else {


            }
        }
    }

}