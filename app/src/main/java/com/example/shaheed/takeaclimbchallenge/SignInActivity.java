package com.example.shaheed.takeaclimbchallenge;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    SignInButton gsignInButton;
    private GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        gsignInButton = findViewById(R.id.signInbtn);

        gsignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions
                        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail().build();

                googleApiClient = new GoogleApiClient.Builder(SignInActivity.this)
                        .enableAutoManage(SignInActivity.this,
                                SignInActivity.this)
                        .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions).build();

                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        AlertDialog.Builder alertbuilder = new AlertDialog.Builder(SignInActivity.this);
        alertbuilder.setTitle("Error")
                .setMessage("Details not Captured!")
                .setIcon(R.drawable.ic_person_black_24dp)
                .setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        if (requestCode == RC_SIGN_IN) {
            if (data != null){
                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                GoogleSignInAccount googleSignInAccount = result.getSignInAccount();

                Intent mIntent = new Intent(SignInActivity.this, MainActivity.class);
                mIntent.putExtra("name", googleSignInAccount.getDisplayName());
                mIntent.putExtra("image", googleSignInAccount.getPhotoUrl());
                mIntent.putExtra("email", googleSignInAccount.getEmail());
                startActivity(mIntent);
            }
            else {
                AlertDialog alertDialog = alertbuilder.create();
                alertDialog.show();
            }

            AlertDialog alertDialog = alertbuilder.create();
            alertDialog.show();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
