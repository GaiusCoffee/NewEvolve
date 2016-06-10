package io.github.gaiusandhartsolutions.newevolve;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener {

    // Constants
    private static final int RC_SIGN_IN = 912;
    // Variables
    private App A;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Initialize Global App Object */
        A = (App) getApplication();
        A.setContext(getApplicationContext());

        /* Check if already logged in */
        if (A.getAuth().getCurrentUser() != null) {
            goToMain();
            return;
        }

        /* Initialize Google Objects */
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.app_webclientid))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        /* Configure SignInButton */
        final SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setScopes(gso.getScopeArray());
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                A.logUITouch("sign_in_button", "button");
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();

                // LDB Auth
                A.setToLocalConfig(App.AUTH_NAME, account.getDisplayName());
                A.setToLocalConfig(App.AUTH_EMAIL, account.getEmail());
                A.setToLocalConfig(App.AUTH_PHOTOURL, account.getPhotoUrl().toString());

                // Firebase Auth
                AuthCredential credential = GoogleAuthProvider.getCredential(
                        account.getIdToken(), null);
                A.getAuth().signInWithCredential(credential)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this,
                                            "Firebase Signin Failed :(", Toast.LENGTH_SHORT).show();
                                } else {
                                    goToMain();
                                }
                            }
                        });
            } else {
                Toast.makeText(this, "Google Signin Failed :(", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void goToMain() {
        // TODO: Go to Main
    }
}
