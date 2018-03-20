package com.example.hp.codeasap2;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText email;
    EditText password;
    FirebaseAuth firebaseAuth;
    Button btnadd;
    FirebaseAuth.AuthStateListener authListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        btnadd=(Button)findViewById(R.id.signup);
        firebaseAuth=FirebaseAuth.getInstance();
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    Intent intt= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intt);
                    Toast.makeText(getApplicationContext(), "Intenting", Toast.LENGTH_LONG).show();

                }
            }
        };
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == btnadd) {
                    String emiall = email.getText().toString().trim();
                    String passwordd = password.getText().toString().trim();
                    if (TextUtils.isEmpty(emiall)) {
                        Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_LONG).show();
                    }
                    if (TextUtils.isEmpty(passwordd))
                    {Toast.makeText(getApplicationContext(), "please enter password", Toast.LENGTH_LONG).show();}
                    Toast.makeText(getApplicationContext(), "password="+passwordd, Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "email="+emiall, Toast.LENGTH_LONG).show();

                     firebaseAuth.createUserWithEmailAndPassword(emiall, passwordd).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful())
                            {
                                Intent intt=new Intent(getApplicationContext(),MainActivity.class);
                                intt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intt);
                                Toast.makeText(getApplicationContext(),"yipeee",Toast.LENGTH_LONG).show();
                            }
                            else
                            {

                            }

                        }
                    });
                }
            }
        });
    }
    @Override
    protected void onStart()
    {
        super.onStart();
        firebaseAuth.addAuthStateListener(authListener);
    }


}
