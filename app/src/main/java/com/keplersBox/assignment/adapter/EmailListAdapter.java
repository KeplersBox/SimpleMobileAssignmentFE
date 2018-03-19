package com.keplersBox.assignment.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.keplersBox.assignment.R;
import com.keplersBox.assignment.model.Email;

import java.util.List;

/**
 * Created by kibrom on 3/15/18.
 */

public class EmailListAdapter extends ArrayAdapter<Email> {

    private final Activity context;

    private List<Email> emails;
    @Override
    public int getCount() {

        return emails.size();
    }

    public EmailListAdapter(Activity context, List<Email>_email) {

        super(context, R.layout.list_email, _email);

        this.context = context;

        emails = _email;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View rowView = inflater.inflate(R.layout.list_email, null, true);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.email_sender_photo);

        TextView emailFrom = (TextView) rowView.findViewById(R.id.email_from);

        TextView emailSubject = (TextView) rowView.findViewById(R.id.email_subject);

        TextView emailBody = (TextView) rowView.findViewById(R.id.email_body);

        emailFrom.setText(emails.get(position).getFrom());

        emailSubject.setText(emails.get(position).getSubject());

        emailBody.setText(emails.get(position).getBody());

//        imageView.setImageDrawable(emails.get(position).getSenderPhoto());

        return rowView;

    }

}

