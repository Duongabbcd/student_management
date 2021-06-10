package com.example.student_management;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.student_management.Adapter.MarkAdapter;
import com.example.student_management.api.ApiService;
import com.example.student_management.model.MarkResResponse;
import com.example.student_management.model.MarkResponse;
import com.example.student_management.model.UserLoginResponse;
import com.facebook.login.LoginManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClientActivity extends AppCompatActivity implements View.OnClickListener, Serializable {
    TextView cusername ,cfullname , caddress ,cmail , cphone ;
    TextView cliNo ;
    RecyclerView cliRecycle;
    private MarkAdapter markAdapter ;

    private TextView txtWellcome;
    private ImageButton imbLogout;
    private ImageButton imgBackpress;
    public static UserLoginResponse u;
    private List<MarkResResponse> marks ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        init();

        initActionBar();
        imbLogout.setVisibility(View.GONE);
        buttonEffect(imbLogout);

        Intent intent= getIntent();

        u = (UserLoginResponse) intent.getSerializableExtra("user");
        System.out.println(u.getAccessToken());

        if(u!=null) {
            txtWellcome.setText("Hello "+u.getFullName());
            imbLogout.setVisibility(View.VISIBLE);
        }

        marks = new ArrayList<>();
        cusername.setText("Username : " + u.getUsername());
        cfullname.setText("Fullname : " + u.getFullName());
        caddress.setText("Address : " + u.getAddress());
        cmail.setText("Mail : " + u.getEmail());
        cphone.setText("Phone : " + u.getPhone());


        ApiService.apiService.allMark("Bearer "+ClientActivity.u.getAccessToken()).enqueue(new Callback<List<MarkResResponse>>() {
            @Override
            public void onResponse(Call<List<MarkResResponse>> call, Response<List<MarkResResponse>> response) {
                    ApiService.apiService.getMarkbyUser(u.getId() , "Bearer "+ClientActivity.u.getAccessToken()).enqueue(new Callback<List<MarkResResponse>>() {
                        @Override
                        public void onResponse(Call<List<MarkResResponse>> call, Response<List<MarkResResponse>> response) {
                            parseDate(response.body());
                            List<MarkResResponse> markResResponses = response.body();

                            cliNo.setText("All marks you are having : "+ markResResponses.size());
                        }

                        @Override
                        public void onFailure(Call<List<MarkResResponse>> call, Throwable t) {

                        }
                    });
            }

            @Override
            public void onFailure(Call<List<MarkResResponse>> call, Throwable t) {

            }
        });

    }
    private void parseDate(List<MarkResResponse> body) {
        cliRecycle.setLayoutManager(new LinearLayoutManager(ClientActivity.this));
        markAdapter  =  new MarkAdapter(body) ;
        cliRecycle.setAdapter(markAdapter);

    }

    public void init(){
        cusername = findViewById(R.id.cusername);
        cfullname = findViewById(R.id.cfullname);
        caddress = findViewById(R.id.caddress);
        cmail = findViewById(R.id.cmail);
        cphone = findViewById(R.id.cphone);
        cliNo = findViewById(R.id.cliNo);
        cliRecycle = findViewById(R.id.cliRecycle);

    }

    public static void buttonEffect(View button) {
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0FFD700, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        LoginManager.getInstance().logOut();
        System.out.println("Hay qua !");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoginManager.getInstance().logOut();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LoginManager.getInstance().logOut();
    }

    @Override
    public void onClick(View v) {
        if(v== imgBackpress)
        {
            onBackPressed();
        }
        if(v==imbLogout)
        {

            Intent intent1 = new Intent(ClientActivity.this,ClientActivity.class);
            LoginManager.getInstance().logOut();
            startActivity(intent1);
        }
    }

    public void initActionBar()
    {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        txtWellcome = findViewById(R.id.txtWellcome);
        imbLogout = findViewById(R.id.imgLogout);
        imbLogout.setOnClickListener(this);
        imgBackpress  = findViewById( R.id.imbbackpress);
        buttonEffect(imgBackpress);
        imgBackpress.setOnClickListener(this);
    }

}