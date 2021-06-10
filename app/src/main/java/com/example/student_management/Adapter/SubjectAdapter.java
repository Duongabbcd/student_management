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
import com.example.student_management.model.SubjectResponse;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> implements Filterable {
        List<SubjectResponse> list ;
        List<SubjectResponse> listOld ;


    public SubjectAdapter() {
    }

    public SubjectAdapter(List<SubjectResponse> list) {
        this.list = list;
        this.listOld = list ;
    }

    public void setSubject(List<SubjectResponse> list ){this.list = list ;
        this.listOld =list ;}

    @NonNull
    @Override
    public SubjectAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_card, parent, false);;
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.ViewHolder holder, int position) {
        SubjectResponse s =list.get(position);

        holder.subid.setText("Subject ID is : " +s.getTvid());
        holder.subjectname.setText(s.getTvsubjectname() );
        holder.quantity.setText("Quantity: " + s.getTvquantity());
        holder.semester.setText("Semester: " + s.getTvsemester());
    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        }
       else
           return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subjectname , quantity ,semester ,subid;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subid =itemView.findViewById(R.id.subid);
            subjectname = itemView.findViewById(R.id.subjectname);
            quantity = itemView.findViewById(R.id.quantity);
            semester = itemView.findViewById(R.id.semester);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString() ;
                if(strSearch.isEmpty()){
                    list=listOld;
                }else{
                    List<SubjectResponse> subjectResponseList = new ArrayList<>() ;
                    for(SubjectResponse subjectResponse : listOld){
                        if(subjectResponse.getTvsubjectname().toLowerCase().contains(strSearch.toLowerCase())){
                            subjectResponseList.add(subjectResponse) ;
                        }
                    }

                    list = subjectResponseList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = list ;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                list= (List<SubjectResponse>) results.values ;
                notifyDataSetChanged();
            }
        };
    }
}
