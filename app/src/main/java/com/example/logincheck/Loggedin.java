package com.example.logincheck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Loggedin extends AppCompatActivity {

    private Button logout;
    private FirebaseAuth firebaseAuth;
    private FirebaseStorage firebaseStorage;
    private FirebaseDatabase firebaseDatabase;
    String conv="";
    private Button button4;
    private ViewFlipper viewFlipper;
    private ImageView imageView4,imageView5;
    private TextView textView3,textView4;
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);


        firebaseAuth=FirebaseAuth.getInstance();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.logout:{
                new AlertDialog.Builder(this)
                        .setTitle("LOGOUT")
                        .setMessage("Are you sure you want to logout ?  I suggest spend some more time :) ")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                firebaseAuth.signOut();
                                finish();
                                startActivity(new Intent(Loggedin.this,MainActivity.class));
                            }
                        })

                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
               }
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

  /*  public void update(View view){

        DocumentReference user =db.collection("FILES").document("images");
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isComplete()){
                    DocumentSnapshot documentSnapshot=task.getResult();
                    StringBuilder img1=new StringBuilder("");
                    img1.append(documentSnapshot.get("textView3"));
                    textView3.setText(img1.toString());
                    String imgurl=textView3.getText().toString();
                    Picasso.get().load(imgurl).into(imageView4);

                    StringBuilder img2=new StringBuilder("");
                    img2.append(documentSnapshot.get("textView4"));
                    textView4.setText(img2.toString());
                    String imgurl2=textView4.getText().toString();
                    Picasso.get().load(imgurl2).into(imageView5);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Loggedin.this,"FAILED",Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
