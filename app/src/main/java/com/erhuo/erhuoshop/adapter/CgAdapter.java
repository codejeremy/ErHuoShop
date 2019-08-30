package com.erhuo.erhuoshop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.erhuo.erhuoshop.R;
import com.erhuo.erhuoshop.bean.Category;
import com.erhuo.erhuoshop.fragment.FragmentChange;
import com.erhuo.erhuoshop.fragment.FragmentChangetwo;

import java.util.List;

public class CgAdapter extends RecyclerView.Adapter<CgAdapter.ViewHolder> {
    private List<Category> mCategorylist;
    Context context;
    FragmentChange fragmentChange;
    FragmentChangetwo fragmentChange2;

    AppCompatActivity context1;


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryname;
        LinearLayout rec;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryname = itemView.findViewById(R.id.textView);
            rec = itemView.findViewById(R.id.linear);

        }
    }
    public CgAdapter(List<Category> categoryList){
        mCategorylist = categoryList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view  = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.template_single_text,viewGroup,false);
        context = viewGroup.getContext();
        context1 = (AppCompatActivity) viewGroup.getContext();


        ViewHolder holder = new ViewHolder(view);
        holder.rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getPosition();
                switch (pos){
                    case 0:
                        FragmentTransaction transaction = context1.getSupportFragmentManager().beginTransaction();
                        fragmentChange = new FragmentChange();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.cetagory_fragment,fragmentChange);
                        transaction.commit();
                        Toast.makeText(context,"点击第"+pos+"个",Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        FragmentTransaction transaction1 = context1.getSupportFragmentManager().beginTransaction();
                        fragmentChange2 = new FragmentChangetwo();
                        transaction1.addToBackStack(null);
                        transaction1.replace(R.id.cetagory_fragment,fragmentChange2);
                        transaction1.commit();
                        Toast.makeText(context,"点击第"+pos+"个",Toast.LENGTH_LONG).show();
                        break;

                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Category category = mCategorylist.get(i);
        viewHolder.categoryname.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return mCategorylist.size();
    }
}
