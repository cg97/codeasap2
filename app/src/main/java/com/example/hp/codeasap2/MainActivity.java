package com.example.hp.codeasap2;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static String feedback;
    Button submit;
    EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit = (Button) findViewById(R.id.button);
        e1 = (EditText) findViewById(R.id.editText);
        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        feedback = e1.getText().toString();
        Intent intt = new Intent(getApplicationContext(), GetLocation.class);
        intt.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intt);
        //submition code-location,feedback
        //Toast.makeText(this, "Submition done successfully", Toast.LENGTH_LONG).show();

    }
}