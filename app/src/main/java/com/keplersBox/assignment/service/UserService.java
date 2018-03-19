package com.keplersBox.assignment.service;

import com.keplersBox.assignment.client.RestClient;
import com.keplersBox.assignment.model.User;
import com.keplersBox.assignment.utils.ApiEndPoints;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by kibrom on 3/15/18.
 */

public class UserService extends RestClient {

    private static UserService instance;

    public static UserService getInstance() {

        if (instance == null) {

            instance = new UserService();
        }

        return instance;
    }

    public Observable<Boolean> registerUser(User user) {

        return makeObservable(registerUserCallable(user))

                .subscribeOn(Schedulers.computation());

    }

    // Register user
    Callable<Boolean> registerUserCallable(final User _user){

        return new Callable<Boolean>() {
            @Override
            public Boolean call() throws JSONException,IOException {

                JSONObject user = new JSONObject();

                user.put("email", _user.getEmail());

                user.put("password",_user.getPassword());

                RequestBody body = RequestBody.create(JSON, user.toString());

                Request request = builder.url(host + ApiEndPoints.USER + "/register").post(body).build();

                Response response = client.newCall(request).execute();

                if (response.code() == 200) {

                    return true;

                }

                return false;
            }
        };
    }

//    Login user

    public Observable<Boolean> loginUser(User user) {

        return makeObservable(loginUserCallable(user))

                .subscribeOn(Schedulers.computation());

    }

    // Register Click
    Callable<Boolean> loginUserCallable(final User _user){

        return new Callable<Boolean>() {
            @Override
            public Boolean call() throws JSONException,IOException {

                Request request = builder.url(host + ApiEndPoints.USER + "/getUserByEmail?email="+ _user.getEmail()).get().build();

                Response response = client.newCall(request).execute();

                JSONObject jsonUser = new JSONObject(response.body().string());

                if (response.code() == 200) {

                    User userDetail = mapper.readValue(jsonUser.toString(),User.class);

                    if(_user.getPassword().equals(userDetail.getPassword())){

                        return true;

                    }

                }

                return false;
            }
        };
    }

}
