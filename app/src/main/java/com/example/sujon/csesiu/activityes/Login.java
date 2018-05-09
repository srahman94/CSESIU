package com.example.sujon.csesiu.activityes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sujon.csesiu.R;

public class Login extends AppCompatActivity {

    Button loginBtn, forgotBtn, regBtn;
    EditText name, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = findViewById(R.id.et_name);
        pass = findViewById(R.id.et_pass);

        loginBtn = findViewById(R.id.btn_login);
        forgotBtn = findViewById(R.id.btn_forget);
        regBtn = findViewById(R.id.btn_reg);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.getText().toString().isEmpty() || pass.getText().toString().isEmpty()) {
                    Toast.makeText(Login.this, "Fill the box", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Login.this, "Loged In", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
