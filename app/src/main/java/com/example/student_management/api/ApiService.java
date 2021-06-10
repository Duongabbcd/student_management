package com.example.student_management.api;

import com.example.student_management.model.Account;
import com.example.student_management.model.Mark;
import com.example.student_management.model.MarkResResponse;
import com.example.student_management.model.MarkResponse;
import com.example.student_management.model.SubjectResponse;
import com.example.student_management.model.UserResponse;
import com.example.student_management.model.UserLoginResponse;
import com.example.student_management.model.UserResResponse;
import com.example.student_management.model.UserResSend;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @POST("api/auth/signin")
    Call<UserLoginResponse> signIn(@Body Account account);

    @POST("api/auth/signup")
    Call<UserResResponse>signUp(@Body UserResSend urs);

    @GET("api/subjects/all")
    Call<List<SubjectResponse>>allSubject(@Header("Authorization") String auth);

    @GET("api/users/all")
    Call<List<UserResponse>>allUser(@Header("Authorization") String auth);

    @GET("api/marks/all")
    Call<List<MarkResResponse>>allMark(@Header("Authorization") String auth);

    @POST("api/marks/save")
    Call<Mark>createMark(@Body MarkResponse mark, @Header("Authorization") String auth);

    @PUT("api/marks/update")
    Call<Mark>updateMark(@Body MarkResponse mark,@Header("Authorization") String auth);

    @GET("api/marks/{id}")
    Call<List<MarkResResponse>>getMarkbyUser(@Path("id") long id  ,@Header("Authorization") String auth);


}

//    @Path("id") long id ,
