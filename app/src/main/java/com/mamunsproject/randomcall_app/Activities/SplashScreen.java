package com.mamunsproject.randomcall_app.Activities;


import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mamunsproject.randomcall_app.MainActivity;
import com.mamunsproject.randomcall_app.R;

public class SplashScreen extends AppCompatActivity {
    long SPLASH_TIME=3000;
    private FirebaseAuth mAuth;
    String TAG="Login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String uid = user.getUid();
            Thread timer = new Thread() {
                public void run() {
                    try {
                        sleep(SPLASH_TIME);
                        FirebaseUser user = mAuth.getCurrentUser();
                        String user_id = user.getUid();
                        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                        current_user_db.child("status").setValue("online");
                        Intent i = new Intent(SplashScreen.this, MainActivity.class);
                       /* i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                Intent.FLAG_ACTIVITY_NEW_TASK);*/
                        startActivity(i);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {


                    }
                }
            };
            timer.start();
        }
        else
        {
            Log.e(TAG,"usernull");

            mAuth.signInAnonymously()
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Thread timer = new Thread() {
                                    public void run() {
                                        try {
                                            sleep(SPLASH_TIME);

                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        } finally {


                                            String user_id = mAuth.getCurrentUser().getUid();
                                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                                            current_user_db.child("status").setValue("online");
                                            current_user_db.child("history").child("-").setValue("true");
                                            Intent i=new Intent(SplashScreen.this,MainActivity.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                                                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                                                    Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(i);
                                        }
                                    }
                                };
                                timer.start();
                                Log.d(TAG, "signInAnonymously:success");

//                            updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("KDKKDK", "signInAnonymously:failure", task.getException());
                                Toast.makeText(SplashScreen.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
//                            updateUI(null);

                            }

                            // ...
                        }
                    });
        }


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        FirebaseUser user = mAuth.getCurrentUser();
//        String user_id = mAuth.getCurrentUser().getUid();
//        DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
//        current_user_db.child("status").setValue("offline");
//    }
}
