package com.rafael.proffy.ui.forgot

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.rafael.proffy.R
import com.rafael.proffy.databinding.ActivityForgotPasswordBinding
import com.rafael.proffy.ui.finallyComponent.FinallyActivity
import com.rafael.proffy.utils.EmailValidator

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        val view = binding.root

        enableEdgeToEdge()
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val keyboard = insets.getInsets(WindowInsetsCompat.Type.ime())
            v.setPadding(systemBars.left, 0, systemBars.right, maxOf(systemBars.bottom, keyboard.bottom))
            binding.containerBackground.setPadding(0, systemBars.top, 0, 0)

            if (insets.isVisible(WindowInsetsCompat.Type.ime())) {
                scrollToBottom()
            }
            insets
        }

        val buttonGoBack = binding.buttonGoBack
        val textInputEmail = binding.textInputEditEmail
        val buttonSendEmail = binding.buttonSendEmail

        buttonGoBack.setOnClickListener {
            goBack()
        }

        textInputEmail.addTextChangedListener {
            validateEmail(binding.textInputLayoutEmail.error != null)
        }

        textInputEmail.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                validateEmail(true)
            }
        }

        buttonSendEmail.setOnClickListener {
            if (validateEmail(true)) {
                sendDoneForgotPassword()
            }
        }
    }

    private fun goBack() {
        finish()
    }

    private fun scrollToBottom() {
        binding.main.post {
            binding.main.smoothScrollTo(0, binding.contentContainer.bottom)
        }
    }

    private fun validateEmail(showError: Boolean): Boolean {
        val email = binding.textInputEditEmail.text?.toString()?.trim() ?: ""
        val isValid = EmailValidator.isValid(email)

        binding.textInputLayoutEmail.error = when {
            isValid || !showError -> null
            email.isEmpty() -> getString(R.string.forgot_password_email_required)
            else -> getString(R.string.forgot_password_email_invalid)
        }
        setButtonState(isValid)
        return isValid
    }

    private fun setButtonState(enabled: Boolean) {
        val buttonSendEmail = binding.buttonSendEmail
        buttonSendEmail.isEnabled = enabled
        buttonSendEmail.setText(
            if (enabled) R.string.forgot_password_button_send else R.string.forgot_password_button_disabled
        )

        val color = ContextCompat.getColor(this, if (enabled) R.color.green else R.color.shape_disable)
        buttonSendEmail.backgroundTintList = ColorStateList.valueOf(color)

        val textColor = ContextCompat.getColor(
            this,
            if (enabled) R.color.shape_01_white else R.color.text_complement
        )
        buttonSendEmail.setTextColor(textColor)
    }

    private fun sendDoneForgotPassword() {
        val intent = Intent(this, FinallyActivity::class.java)
        intent.putExtra("textViewTitleFinally", getString(R.string.forgot_password_done_title))
        intent.putExtra("textViewSubtitleFinally", getString(R.string.forgot_password_done_subtitle))
        intent.putExtra("titleButton", getString(R.string.forgot_password_done_button))

        startActivity(intent)
        finish()
    }
}
