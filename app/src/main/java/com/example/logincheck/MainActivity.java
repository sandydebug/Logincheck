package com.example.logincheck;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.inappmessaging.FirebaseInAppMessaging;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    TextView textView,textView1,textView2;
    EditText editText,editText1;
    Button button;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=(EditText)findViewById(R.id.name);
        editText1=(EditText)findViewById(R.id.password);
        button=(Button)findViewById(R.id.login);
        textView=(TextView)findViewById(R.id.textView);
        textView1=(TextView)findViewById(R.id.forgot);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        final FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();


        if(firebaseUser!=null){
            startActivity(new Intent(MainActivity.this, Loggedin.class));
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().isEmpty() || editText1.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter all the details", Toast.LENGTH_SHORT).show();
                }
                else{
                    validate(editText.getText().toString(),editText1.getText().toString());
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Registration.class));
            }
        });
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Please enter your registered mail id and the press forgot password ",Toast.LENGTH_LONG).show();
                }
                else{
                firebaseAuth.sendPasswordResetEmail(editText.getText().toString());
                    Toast.makeText(MainActivity.this,"Mail with rest link to your registered mail ",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void validate(String username,String password){

        progressDialog.setMessage("Hang on while we connect you ");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    emailVerification();
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"Login Failed!!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

private void emailVerification(){
        FirebaseUser firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        Boolean email=firebaseUser.isEmailVerified();
        if(email){
            finish();
            startActivity(new Intent(MainActivity.this,Loggedin.class));
        }
        else{
            Toast.makeText(MainActivity.this,"Verify your mail",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();

        }
}
}
