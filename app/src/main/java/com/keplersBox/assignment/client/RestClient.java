package com.keplersBox.assignment.client;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keplersBox.assignment.BuildConfig;

import java.util.concurrent.Callable;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static android.content.ContentValues.TAG;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by kibrom on 3/15/18.
 */


public class RestClient {


    protected OkHttpClient client;

    protected Request.Builder builder;

    protected ObjectMapper mapper;

    protected String host = BuildConfig.URL;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public RestClient() {

        client = new OkHttpClient();

        builder = new Request.Builder();

        mapper = new ObjectMapper();
    }

    protected static <T> Observable<T> makeObservable(final Callable<T> func) {

        return Observable.create(

                new Observable.OnSubscribe<T>() {

                    @Override
                    public void call(Subscriber<? super T> subscriber) {

                        try {

                            subscriber.onNext(func.call());

                            subscriber.onCompleted();

                        } catch(Exception ex) {

                            subscriber.onError(ex);

                            Log.e(TAG, "Error reading from the database", ex);
                        }
                    }
                });
    }

}
