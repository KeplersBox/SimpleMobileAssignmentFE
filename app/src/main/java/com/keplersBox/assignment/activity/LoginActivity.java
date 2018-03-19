package com.keplersBox.assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.keplersBox.assignment.R;
import com.keplersBox.assignment.model.User;
import com.keplersBox.assignment.service.UserService;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kibrom on 3/15/18.
 */

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText email;

    private TextInputEditText password;

    private Button login;

    private TextView createAccount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        initializeView();

        addListener();

    }

    private void initializeView(){

        email = (TextInputEditText)findViewById(R.id.loginEmail);

        password = (TextInputEditText)findViewById(R.id.loginPassword);

        login = (Button)findViewById(R.id.btnLogin);

        createAccount = (TextView)findViewById(R.id.loginCreatAccount);
    }

    private void addListener(){

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);

                startActivity(intent);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User user = new User();

                user.setEmail(email.getText().toString());

                user.setPassword(password.getText().toString());

                UserService.getInstance().loginUser(user)

                        .subscribeOn(Schedulers.io())

                        .observeOn(AndroidSchedulers.mainThread())

                        .subscribe(new Observer<Boolean>() {
                            @Override
                            public void onCompleted() {


                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(Boolean isLoggedIn) {

                                if(isLoggedIn){

                                    Intent intent = new Intent(LoginActivity.this,DashboardActivity.class);

                                    startActivity(intent);
                                }else {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                                    builder.setMessage(R.string.dialog_message)
                                            .setTitle(R.string.dialog_title);

                                    AlertDialog dialog = builder.create();

                                    dialog.show();
                                }

                            }
                        });

            }
        });

    }

}
