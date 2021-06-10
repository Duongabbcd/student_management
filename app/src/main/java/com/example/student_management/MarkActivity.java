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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.example.student_management.Adapter.MarkAdapter;
import com.example.student_management.api.ApiService;
import com.example.student_management.model.Mark;
import com.example.student_management.model.MarkResResponse;
import com.example.student_management.model.MarkResponse;
import com.example.student_management.model.Subject;
import com.example.student_management.model.SubjectResponse;
import com.example.student_management.model.User;
import com.example.student_management.model.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MarkActivity extends AppCompatActivity {

    private EditText mID ,mdeligence , mamid1 ,mamid2 ,mUsID , mSubID  ,mStatus;
    private String status;
    private long markID ,User_ID ,Subject_ID ;
    private double deligence , midpoint1 , midpoint2 ;

    private  Button mFind ,mAdd, mUpdate;
    private  TextView marNo ;
    private RecyclerView markRecycle ;

    private MarkAdapter markAdapter ;

    private SearchView searchView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);

          init();


          mFind.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                 ApiService.apiService.allMark("Bearer "+MainActivity.u.getAccessToken()).enqueue(new Callback<List<MarkResResponse>>() {
                     @Override
                     public void onResponse(Call<List<MarkResResponse>> call, Response<List<MarkResResponse>> response) {
                         parseDate(response.body());
                         List<MarkResResponse> markResResponses = response.body();

                        marNo.setText("Total Marks Have Been Added : "+ markResResponses.size());
                     }

                     @Override
                     public void onFailure(Call<List<MarkResResponse>> call, Throwable t) {

                     }
                 });
              }
          });

          mAdd.setOnClickListener(new View.OnClickListener() {

              @Override
              public void onClick(View v) {
                  deligence=Double.parseDouble(mdeligence.getText().toString());
                  midpoint1=Double.parseDouble(mamid1.getText().toString());
                  midpoint2=Double.parseDouble(mamid2.getText().toString());
                  if(deligence == 0 || midpoint1 == 0 || midpoint2 == 0) {
                      status ="Failed";
                  }
                  else{
                      status="Succeed";
                  }
                  String user_id = mUsID.getText().toString();
                  String sub_id = mSubID.getText().toString();
                  Subject sub = new Subject(sub_id);
                  User us = new User(user_id);
                  MarkResponse mark = new MarkResponse(mdeligence.getText().toString(),mamid1.getText().toString(),mamid2.getText().toString(),status,sub,us);
                ApiService.apiService.createMark(mark ,"Bearer "+MainActivity.u.getAccessToken()).enqueue(new Callback<Mark>() {
                    @Override
                    public void onResponse(Call<Mark> call, Response<Mark> response) {
                        if(response.body() != null){
                            Toast.makeText(MarkActivity.this, "Success",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Mark> call, Throwable t) {

                    }
                });
              }
          });


        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                markID = Long.parseLong(mID.getText().toString());
                deligence  = Double.parseDouble(mdeligence.getText().toString());
                midpoint1= Double.parseDouble(mamid1.getText().toString());
                midpoint2= Double.parseDouble(mamid2.getText().toString());
                status=mStatus.getText().toString();
                String user_id = mUsID.getText().toString();
                String sub_id = mSubID.getText().toString();
                Subject sub = new Subject(sub_id);
                User us = new User(user_id);
                MarkResponse mark = new MarkResponse(mID.getText().toString(),mdeligence.getText().toString(),mamid1.getText().toString(),mamid2.getText().toString(),status,sub,us);
                ApiService.apiService.updateMark(mark,"Bearer "+MainActivity.u.getAccessToken()).enqueue(new Callback<Mark>() {
                    @Override
                    public void onResponse(Call<Mark> call, Response<Mark> response) {
                        if(response.body() != null){
                            Toast.makeText(MarkActivity.this,"Upload Successfully !",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Mark> call, Throwable t) {

                    }
                });
            }
        });

    }


    private void parseDate(List<MarkResResponse> body) {
        markRecycle.setLayoutManager(new LinearLayoutManager(MarkActivity.this));
        markAdapter  =  new MarkAdapter(body) ;
        markRecycle.setAdapter(markAdapter);

    }


    public void init(){
        mID = findViewById(R.id.maID);
        mdeligence = findViewById(R.id.deligence) ;
        mamid1 = findViewById(R.id.mamid1);
        mamid2 = findViewById(R.id.mamid2);
        mUsID = findViewById(R.id.mUsID);
        mSubID = findViewById(R.id.mSubID);
        mStatus=findViewById(R.id.mStatus);

        mFind =findViewById(R.id.mBtn1) ;
        mAdd = findViewById(R.id.mBtn2) ;
        mUpdate = findViewById(R.id.mBtn3) ;

        marNo = findViewById(R.id.marNo) ;
        markRecycle = findViewById(R.id.markRecycle) ;

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
                markAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               markAdapter.getFilter().filter(newText);
                return false;
            }
      });
        return super.onCreateOptionsMenu(menu);

        }


        @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.mBack:
                    onBackPressed();
                    break;

            }
            return super.onOptionsItemSelected(item);
        }

}