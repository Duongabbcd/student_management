package com.example.student_management.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_management.MarkActivity;
import com.example.student_management.R;
import com.example.student_management.model.MarkResResponse;

import java.util.ArrayList;
import java.util.List;

public class MarkAdapter extends RecyclerView.Adapter<MarkAdapter.ViewHolder> implements Filterable {
    List<MarkResResponse>  markList ;
    List<MarkResResponse> markListOld ;

    MarkActivity markActivity ;

    public MarkAdapter() {
    }

    public MarkAdapter(List<MarkResResponse> markList) {
        this.markList = markList;
        this.markListOld = markList;
    }

    public void setMark(List<MarkResResponse> markList ){
        this.markList = markList ;
        this.markListOld =markList ;}

    @NonNull
    @Override
    public MarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mark_card,parent,false) ;
        return new MarkAdapter.ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull MarkAdapter.ViewHolder holder, int position) {
            MarkResResponse m = markList.get(position);

            holder.mId.setText(""+m.getId());
            holder.msubjectName.setText(m.getSubjectName());
            holder.mdeligence.setText(""+m.getDeligence());
        holder.midpoint1.setText(""+m.getMidpoint1());
        holder.midpoint2.setText(""+m.getMidpoint2());
        holder.status.setText(m.getStatus());
        holder.mUser.setText(m.getFullName());
    }


    @Override
    public int getItemCount() {
        if(markList != null) {
            return markList.size();
        }
        else
            return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mId ,msubjectName , mdeligence  ,midpoint1 ,midpoint2 ,status , mUser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mId = itemView.findViewById(R.id.mId) ;
            msubjectName = itemView.findViewById(R.id.msubjectName) ;
            mdeligence = itemView.findViewById(R.id.mdeligence);
            midpoint1 = itemView.findViewById(R.id.midpoint1);
            midpoint2 = itemView.findViewById(R.id.midpoint2);
            status = itemView.findViewById(R.id.status);
            mUser = itemView.findViewById(R.id.mUser);

        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString() ;
                if(strSearch.isEmpty()){
                    markList=markListOld;
                }else{
                    List<MarkResResponse> markResResponseList= new ArrayList<>() ;
                    for(MarkResResponse markResResponse : markListOld){
                        if(markResResponse.getFullName().toLowerCase().contains(strSearch.toLowerCase())){
                            markResResponseList.add(markResResponse) ;
                        }
                    }

                    markList = markResResponseList ;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = markList ;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                markList= (List<MarkResResponse>) results.values ;
                notifyDataSetChanged();
            }
        };
    }

}
