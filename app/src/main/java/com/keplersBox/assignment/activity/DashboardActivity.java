package com.keplersBox.assignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.keplersBox.assignment.R;
import com.keplersBox.assignment.adapter.EmailListAdapter;
import com.keplersBox.assignment.model.Email;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kibrom on 3/15/18.
 */

public class DashboardActivity extends AppCompatActivity {

    private ListView emailListView;

    private List<Email> emailList;

    private Toolbar toolbar;

    private static final String EMAIL_DETAIL = "emailDetail";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

       initializeView();


        //Dummy data to populate dashboard
        emailList = new ArrayList<>();

        Email abebeEmail = new Email();

        abebeEmail.setFrom(getString(R.string.dummy_from));

        abebeEmail.setSubject(getString(R.string.dummy_subject));

        abebeEmail.setBody(getString(R.string.dummy_body));

        for(int i = 0 ; i < 15 ; i++){

            emailList.add(abebeEmail);

        }

        ListAdapter adapter = new EmailListAdapter(this, emailList);

        emailListView.setAdapter(adapter);

        emailListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(DashboardActivity.this,EmailDetailActivity.class);

                intent.putExtra(EMAIL_DETAIL, emailList.get(position));

                startActivity(intent);

            }
        });
    }

    private void initializeView(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(R.string.dashboard_title);

        emailListView = (ListView) findViewById(R.id.emailListView);

    }
}
