package com.example.student_management.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_management.R;
import com.example.student_management.model.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> implements Filterable {
    List<UserResponse> userResponses;
    List<UserResponse> usersOld ;

    public UserAdapter() {
    }

    public UserAdapter(List<UserResponse> userResponses) {
        this.userResponses = userResponses;
        this.usersOld = userResponses;
    }

    public void setUser(List<UserResponse> userResponses){this.userResponses = userResponses;
        this.usersOld = userResponses;}

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_card, parent, false);;
        return new UserAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        UserResponse u  = userResponses.get(position);

        holder.usid.setText("ID : "+u.getId());
        holder.username.setText("Username : "+u.getUsername());
        holder.fullname.setText("Fullname : "+u.getFullName());
        holder.address.setText("Address : "+u.getAddress());
        holder.phone.setText("Phone : "+u.getPhone());
        holder.email.setText("Mail : "+u.getEmail());
    }

    @Override
    public int getItemCount() {
        if(userResponses != null) {
            return userResponses.size();
        }
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView usid ,username,fullname ,address ,phone ,email ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            usid = itemView.findViewById(R.id.usid);
            username = itemView.findViewById(R.id.username);
            fullname = itemView.findViewById(R.id.fullname);
            address = itemView.findViewById(R.id.address);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString() ;
                if(strSearch.isEmpty()){
                    userResponses =usersOld;
                }else{
                    List<UserResponse> userResponseList = new ArrayList<>() ;
                    for(UserResponse userResponse : usersOld){
                        if(userResponse.getUsername().toLowerCase().contains(strSearch.toLowerCase())){
                            userResponseList.add(userResponse) ;
                        }
                    }

                    userResponses = userResponseList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = userResponses;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                userResponses = (List<UserResponse>) results.values ;
                notifyDataSetChanged();
            }
        };
    }

}
