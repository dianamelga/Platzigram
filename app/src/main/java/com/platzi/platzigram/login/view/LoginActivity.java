package com.platzi.platzigram.login.view;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.platzi.platzigram.R;
import com.platzi.platzigram.login.presenter.LoginPresenter;
import com.platzi.platzigram.login.presenter.LoginPresenterImpl;
import com.platzi.platzigram.view.ContainerActivity;
import com.platzi.platzigram.view.CreateAccountActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private TextInputEditText username;
    private TextInputEditText password;
    private Button login;
    private ProgressBar progressBarLogin;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (TextInputEditText) findViewById(R.id.username);
        password = (TextInputEditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        progressBarLogin = (ProgressBar) findViewById(R.id.progressbarLogin);

        presenter = new LoginPresenterImpl(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.equals("") || password.equals("")) {
                    String toastLogin = getResources().getText(R.string.toastLogin).toString();
                    Toast.makeText(v.getContext(), toastLogin , Toast.LENGTH_SHORT).show();
                }else {
                    presenter.signIn(username.getText().toString(), password.getText().toString());

                }
            }
        });

        hideProgressBar();
    }

    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        login.setEnabled(true);
    }

    @Override
    public void disableInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        login.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
        progressBarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarLogin.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        String toastLoginError = getResources().getText(R.string.toastLoginError).toString();
        Toast.makeText(this, toastLoginError, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);

    }

    @Override
    public void goHome() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }
}
