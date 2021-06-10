package com.example.student_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student_management.api.ApiService;
import com.example.student_management.model.Account;
import com.example.student_management.model.UserLoginResponse;
import com.example.student_management.model.UserRole;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.student_management.model.UserRole.ROLE_CUSTOMER;

public class LoginActivity extends AppCompatActivity {
    TextView txtAlert ;
    EditText edusername ,edpassword ;
    Button btnlogin ;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edusername =findViewById(R.id.username);
        edpassword =findViewById(R.id.password);
        btnlogin =findViewById(R.id.btnlogin);
        txtAlert=findViewById(R.id.txtAlert);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(edusername.getText().toString()) || TextUtils.isEmpty(edpassword.getText().toString())){
                    Toast.makeText(LoginActivity.this ,"Username / Password required !",Toast.LENGTH_SHORT).show();
                }else{
                    login();
                }
            }
        });
    }

    public void login(){
       String username = edusername.getText().toString();
       String password =edpassword.getText().toString();

        UserRole ur ;
        Account account = new Account(username,password) ;
        ApiService.apiService.signIn(account).enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                UserLoginResponse u = response.body();
                if(u != null ) {
                  if(u.getId() == 2){
                      Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                      intent.putExtra("user",u);
                      startActivity(intent);
                  }
                  else{
                      Intent intent = new Intent(LoginActivity.this,ClientActivity.class);
                      intent.putExtra("user",u);
                      startActivity(intent);
                    }
                }
                else {
                    txtAlert.setVisibility(View.VISIBLE);
                    txtAlert.animate().scaleY(1).setInterpolator(new DecelerateInterpolator()).start();
                    new CountDown().start();
                    handler = new Handler() {
                        @Override
                        public void handleMessage(@NonNull Message msg) {
                            switch (msg.what) {
                                case 1000: {
                                    break;
                                }
                                case 123: {
                                    txtAlert.animate().scaleY(0).setInterpolator(new DecelerateInterpolator()).start();
                                    break;
                                }
                            }
                        }
                    };
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Log.e("false","connect false");
            }
        });
    }

    class CountDown extends Thread
    {
        @Override
        public void run() {
            int time=3;
            while(time>0)
            {
                time--;
                Message msg = new Message();
                msg.what = 1000;
                msg.arg1 = time;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    System.out.println(e);
                }
            }
            handler.sendEmptyMessage(123);
        }
    }
}