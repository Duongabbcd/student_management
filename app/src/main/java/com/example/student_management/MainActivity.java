package com.example.student_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.student_management.model.UserLoginResponse;
import com.facebook.login.LoginManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Serializable {

    private TextView txtWellcome;
    private ImageButton imbLogout;
    private ImageButton imgBackpress;
    public static UserLoginResponse u;

    private BottomNavigationView bottomNavigationView ;
    Fragment selectedFragment = null ;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

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
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.mMark :
                            selectedFragment =null;
                            startActivity(new Intent(MainActivity.this,MarkActivity.class));
                            break ;

                        case R.id.mUser :
                            selectedFragment =null;
                            startActivity(new Intent(MainActivity.this,UserActivity.class));
                            break ;

                        case R.id.mSubject:
                            selectedFragment = null;
                            startActivity(new Intent(MainActivity.this,SubjectActivity.class));
                            break ;
                        case R.id.mAccount:
                            selectedFragment = null;
                            startActivity(new Intent(MainActivity.this,RegisterActivity.class));
                            break ;

                    }

                    if(selectedFragment != null){
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment)
                                .commit();
                    }
                    return true ;

                }
            };

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

            Intent intent1 = new Intent(MainActivity.this,MainActivity.class);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);

        return super.onCreateOptionsMenu(menu);

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.mBack :
                onBackPressed();
                break ;

        }

        return super.onOptionsItemSelected(item);
    }
}

