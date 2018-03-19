package com.keplersBox.assignment.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.keplersBox.assignment.R;
import com.keplersBox.assignment.model.User;
import com.keplersBox.assignment.service.UserService;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kibrom on 3/15/18.
 */

public class SignupActivity extends AppCompatActivity {

    private AwesomeValidation awesomeValidation;

    private TextInputEditText email;

    private TextInputEditText password;

    private TextInputEditText comfirmPassword;

    private Button createAccount;

    private TextView login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);

        initializeView();

        addListener();

    }

    private void initializeView(){

        email = (TextInputEditText)findViewById(R.id.signUpemail);

        password = (TextInputEditText)findViewById(R.id.signUpPassword);

        comfirmPassword = (TextInputEditText)findViewById(R.id.signUpComfirPassword);

        createAccount = (Button)findViewById(R.id.signUpCreatAccount);

        login = (TextView)findViewById(R.id.signUpLogin);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this, R.id.signUpemail, Patterns.EMAIL_ADDRESS, R.string.emailerror);

    }

    private void addListener(){

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!password.getText().toString().equals(comfirmPassword.getText().toString())){

                    comfirmPassword.setError(getString(R.string.passwordDidntMatch));

                }else {

                    if (awesomeValidation.validate()) {

                        User user = new User();

                        user.setEmail(email.getText().toString());

                        user.setPassword(password.getText().toString());

                        UserService.getInstance().registerUser(user)

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

                                        Intent intent = new Intent(SignupActivity.this,LoginActivity.class);

                                        startActivity(intent);

                                    }
                                });

                    }
                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);

                startActivity(intent);
            }
        });

    }
}
