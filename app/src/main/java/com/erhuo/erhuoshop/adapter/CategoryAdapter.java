package com.erhuo.erhuoshop.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.erhuo.erhuoshop.R;
import com.erhuo.erhuoshop.bean.Category;

import java.util.List;

public class CategoryAdapter extends com.erhuo.erhuoshop.adapter.SimpleAdapter<Category> {

    public CategoryAdapter(Context context, List<Category> datas) {
        super(context, R.layout.template_single_text, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, Category item) {
        viewHoder.getTextView(R.id.textView).setText(item.getName());
    }
}
