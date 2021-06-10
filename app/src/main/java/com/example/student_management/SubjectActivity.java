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
import android.widget.Toast;

import com.example.student_management.Adapter.SubjectAdapter;
import com.example.student_management.api.ApiService;
import com.example.student_management.model.SubjectResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubjectActivity extends AppCompatActivity{
    private Button subGet ;
    private TextView subNo ;
    private RecyclerView recyclerView ;
    private SubjectAdapter subjectAdapter ;
    private SearchView searchView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        recyclerView = findViewById(R.id.sub_recycleView);
//        subGet =findViewById(R.id.subGet) ;
        subNo =findViewById(R.id.subNo) ;

        ApiService.apiService.allSubject("Bearer " +MainActivity.u.getAccessToken()).enqueue(new Callback<List<SubjectResponse>>() {
            @Override
            public void onResponse(Call<List<SubjectResponse>> call, Response<List<SubjectResponse>> response) {
                parseData(response.body());
                List<SubjectResponse> subjectResponses =response.body();
                System.out.println(subjectResponses.size()+"----------------Size");

                subNo.setText("Total Subjects : "+ subjectResponses.size());
            }

            @Override
            public void onFailure(Call<List<SubjectResponse>> call, Throwable t) {
                Toast.makeText(SubjectActivity.this, "Hong roi !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void parseData(List<SubjectResponse> body) {
        recyclerView.setLayoutManager(new LinearLayoutManager(SubjectActivity.this));
        subjectAdapter  = new SubjectAdapter(body);
           recyclerView.setAdapter(subjectAdapter);

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
                subjectAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                subjectAdapter.getFilter().filter(newText);
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