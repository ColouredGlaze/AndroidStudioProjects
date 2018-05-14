package com.example.lin.mt.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText accountEdit;

    private EditText passwordEdit;

    private Button btnLogin;

    private CheckBox rememberPassword;

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEdit = findViewById(R.id.account);
        passwordEdit = findViewById(R.id.password);
        rememberPassword = findViewById(R.id.remember_password);
        btnLogin = findViewById(R.id.login);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean isRemember = sharedPreferences.getBoolean("remember_password", false);
        if (isRemember){
            String account = sharedPreferences.getString("account", "");
            accountEdit.setText(account);
            accountEdit.setSelection(account.length());
            passwordEdit.setText(sharedPreferences.getString("password", ""));
            rememberPassword.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = accountEdit.getText().toString();
                String password = passwordEdit.getText().toString();
                if ("admin".equals(account) && "123456".equals(password)){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    editor = sharedPreferences.edit();
                    if (rememberPassword.isChecked()){
                        editor.putString("account", account);
                        editor.putString("password", password);
                        editor.putBoolean("remember_password", true);
                    } else {
                        editor.clear();
                    }
                    editor.apply();
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,
                            "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
