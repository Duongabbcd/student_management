package com.example.student_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.student_management.Adapter.UserAdapter;
import com.example.student_management.api.ApiService;
import com.example.student_management.model.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    private RecyclerView user_recycleView ;
    private TextView userNo ;
    private Button userbtn ;
    private UserAdapter userAdapter ;
    private SearchView searchView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        user_recycleView = findViewById(R.id.user_recycleView) ;
        userNo = findViewById(R.id.userNo) ;

        ApiService.apiService.allUser("Bearer "+ MainActivity.u.getAccessToken()).enqueue(new Callback<List<UserResponse>>() {
            @Override
            public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
                parseData(response.body());
                List<UserResponse> userResponseList =response.body();
                System.out.println(userResponseList.size()+"----------------Size");

                userNo.setText("Total Users : "+ userResponseList.size());

            }

            @Override
            public void onFailure(Call<List<UserResponse>> call, Throwable t) {

            }
        });
    }

    private void parseData(List<UserResponse> body) {
        user_recycleView.setLayoutManager(new LinearLayoutManager(UserActivity.this));
        userAdapter  = new UserAdapter(body);
        user_recycleView.setAdapter(userAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE) ;
        searchView = (SearchView) menu.findItem(R.id.mSearch).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                userAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                userAdapter.getFilter().filter(newText);
                return false;
            }
        });
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