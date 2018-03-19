package com.keplersBox.assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.keplersBox.assignment.R;
import com.keplersBox.assignment.model.Email;

/**
 * Created by kibrom on 3/17/18.
 */

public class EmailDetailActivity extends AppCompatActivity {

    private TextView emailDetailFrom;

    private TextView emailDetailSubject;

    private TextView emailDetailBody;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_email_detail);

        emailDetailFrom = (TextView)findViewById(R.id.email_detail_from);

        emailDetailSubject = (TextView)findViewById(R.id.email_detail_subject);

        emailDetailBody = (TextView)findViewById(R.id.email_detail_body);

        Intent i = getIntent();

        Email email = (Email) i.getParcelableExtra("emailDetail");

        emailDetailFrom.setText(email.getFrom());

        emailDetailSubject.setText(email.getSubject());

        emailDetailBody.setText(email.getBody());


    }
}
