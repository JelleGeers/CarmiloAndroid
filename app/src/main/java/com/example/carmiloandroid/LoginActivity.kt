package com.example.carmiloandroid


import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.authentication.storage.CredentialsManagerException
import com.auth0.android.authentication.storage.SecureCredentialsManager
import com.auth0.android.authentication.storage.SharedPreferencesStorage
import com.auth0.android.callback.BaseCallback
import com.auth0.android.provider.AuthCallback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials

class LoginActivity : AppCompatActivity() {

    private var auth0: Auth0? = null
    private var credentialsManager: SecureCredentialsManager? = null

    private val webCallback = object : AuthCallback {
        override fun onFailure(dialog: Dialog) {
            runOnUiThread { dialog.show() }
        }

        override fun onFailure(exception: AuthenticationException) {
            runOnUiThread { Toast.makeText(this@LoginActivity, "Log In - Error Occurred", Toast.LENGTH_SHORT).show() }
        }

        @TargetApi(21)
        override fun onSuccess(credentials: Credentials) {
            runOnUiThread { Toast.makeText(this@LoginActivity, "Log In - Success", Toast.LENGTH_SHORT).show() }
            credentialsManager!!.saveCredentials(credentials)
            showNextActivity(credentials)
        }
    }

    @SuppressLint("ResourceType")
    @TargetApi(21)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Setup CredentialsManager
        auth0 = Auth0(this)
        auth0!!.setLoggingEnabled(true)
        auth0!!.setOIDCConformant(true)
        credentialsManager =
                SecureCredentialsManager(this, AuthenticationAPIClient(auth0!!), SharedPreferencesStorage(this))

        //Optional - Uncomment the next line to use:
        //Require device authentication before obtaining the credentials
        //credentialsManager.requireAuthentication(this, CODE_DEVICE_AUTHENTICATION, getString(R.string.request_credentials_title), null);

        // Check if the activity was launched after a logout
        if (intent.getBooleanExtra(KEY_CLEAR_CREDENTIALS, false)) {
            credentialsManager!!.clearCredentials()
        }

        // Check if a log in button must be shown
        if (!credentialsManager!!.hasValidCredentials()) {
            setContentView(R.layout.activity_login)

            val loginButton = findViewById(R.id.loginButton) as Button
            loginButton.setOnClickListener { doLogin() }

            return
        }

        // Obtain the existing credentials and move to the next activity
        credentialsManager!!.getCredentials(object : BaseCallback<Credentials, CredentialsManagerException> {
            override fun onSuccess(credentials: Credentials) {
                showNextActivity(credentials)
            }

            override fun onFailure(error: CredentialsManagerException) {
                //Authentication cancelled by the user. Exit the app
                finish()
            }
        })
    }

    /**
     * Override required when setting up Local Authentication in the Credential Manager
     * Refer to SecureCredentialsManager#requireAuthentication method for more information.
     */
    @TargetApi(21)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (credentialsManager!!.checkAuthenticationResult(requestCode, resultCode)) {
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun showNextActivity(credentials: Credentials) {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra(EXTRA_ACCESS_TOKEN, credentials.accessToken)
        intent.putExtra(EXTRA_ID_TOKEN, credentials.idToken)
        startActivity(intent)
        finish()
    }

    private fun doLogin() {
        WebAuthProvider.init(auth0!!)
            .withScheme("carmilo")
            .withAudience(String.format("https://%s/userinfo", getString(R.string.com_auth0_domain)))
            .withScope("openid offline_access")
            .start(this, webCallback)
    }

    companion object {

        /**
         * Required when setting up Local Authentication in the Credential Manager
         * Refer to SecureCredentialsManager#requireAuthentication method for more information.
         */
        private val CODE_DEVICE_AUTHENTICATION = 22
        val KEY_CLEAR_CREDENTIALS = "com.auth0.CLEAR_CREDENTIALS"
        val EXTRA_ACCESS_TOKEN = "com.auth0.ACCESS_TOKEN"
        val EXTRA_ID_TOKEN = "com.auth0.ID_TOKEN"
    }

}