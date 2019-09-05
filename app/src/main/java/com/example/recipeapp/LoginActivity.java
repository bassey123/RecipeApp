package com.example.recipeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login_btn = findViewById(R.id.login_btn);
        final MaterialEditText email = findViewById(R.id.email);
        final MaterialEditText pwd = findViewById(R.id.password);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString();
                String pas = pwd.getText().toString();

                if (TextUtils.isEmpty(pas) || !Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    email.setError("Enter a valid Email");
                }

                if (TextUtils.isEmpty(pas) || pas.length() < 6) {
                    pwd.setError("Enter a strong Password");
                }

                if (!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(mail) && Patterns.EMAIL_ADDRESS.matcher(mail).matches() && pas.length() >= 6) {
                    startActivity(new Intent(getApplicationContext(), RecipeActivity.class));
                }
            }
        });
    }
}
