package com.example.assignment.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.CompoundButtonCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.Models.Root;
import com.example.assignment.R;


public class ExpandAdapter extends RecyclerView.Adapter<ExpandAdapter.ParentViewModel> {

    Root[] roots;
    Context context;

    public ExpandAdapter(Root[] roots, Context context) {
        this.roots = roots;
        this.context = context;
    }

    @NonNull
    @Override
    public ParentViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view, parent, false);
        return new ParentViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentViewModel holder, int position) {
        try {
            holder.tvMake.setText(roots[position].getMake());
            holder.tvId.setText(String.valueOf(roots[position].getId()));
            holder.tvVin.setText(roots[position].getVin());
            holder.tvYear.setText(String.valueOf(roots[position].getYear()));
            holder.tvExpandMake.setText(roots[position].getMake());
            holder.tvValue.setText(context.getString(R.string.doller) + roots[position].getValue());
            holder.tvLength.setText(roots[position].getLength() + context.getString(R.string.one_space) + context.getString(R.string.ft));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return roots.length;
    }


    public class ParentViewModel extends RecyclerView.ViewHolder {
        public ConstraintLayout constraintLayout;
        TextView tvMake, tvVin, tvId, tvYear, tvValue, tvLength, tvExpandMake;

        public ParentViewModel(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            constraintLayout.setBackgroundColor(Color.WHITE);

            LinearLayout parentLayout = new LinearLayout(itemView.getContext());
            parentLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams parentLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10);
            parentLayoutParams.leftMargin = 50;
            parentLayout.setLayoutParams(parentLayoutParams);


            CheckBox checkBox = new CheckBox(itemView.getContext());
            LinearLayout.LayoutParams checkBoxParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            checkBoxParams.leftMargin = 50;
            checkBox.setLayoutParams(checkBoxParams);
            if (Build.VERSION.SDK_INT < 21) {
                CompoundButtonCompat.setButtonTintList(checkBox, ColorStateList.valueOf(Color.GRAY));
            } else {
                checkBox.setButtonTintList(ColorStateList.valueOf(Color.GRAY));
            }


            tvMake = new TextView(itemView.getContext());
            LinearLayout.LayoutParams tvMakeHeadingParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 8);
            tvMakeHeadingParams.leftMargin = 10;
            tvMake.setLayoutParams(tvMakeHeadingParams);
            tvMake.setTextColor(Color.BLACK);
            tvMake.setTypeface(tvMake.getTypeface(), Typeface.BOLD);
            tvMake.setTextSize(18);


            tvId = new TextView(itemView.getContext());
            LinearLayout.LayoutParams tvIdHeadingParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tvId.setLayoutParams(tvIdHeadingParams);
            tvId.setTextColor(Color.BLACK);
            tvId.setTextSize(16);


            ImageView imageView = new ImageView(itemView.getContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_keyboard_arrow_right_24));
            imageView.setTag("up_arrow");
            parentLayout.addView(checkBox);
            parentLayout.addView(tvId);
            parentLayout.addView(tvMake);
            parentLayout.addView(imageView);

            constraintLayout.addView(parentLayout);

            //Expand Layout
            LinearLayout expandLayout = new LinearLayout(itemView.getContext());
            expandLayout.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            expandLayout.setLayoutParams(linearParams);

            //Vin layout with horizontal layout
            LinearLayout vinLayout = new LinearLayout(itemView.getContext());
            vinLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams vinLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10);
            vinLayoutParams.leftMargin = 70;
            vinLayout.setLayoutParams(vinLayoutParams);

            TextView tvVinHeading = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsVinHeading = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 7);
            layoutParamsVinHeading.topMargin = 50;
            tvVinHeading.setText(context.getString(R.string.vin));
            tvVinHeading.setTextColor(Color.GRAY);
            tvVinHeading.setTextSize(16);
            tvVinHeading.setLayoutParams(layoutParamsVinHeading);

            tvVin = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsVin = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 3);
            layoutParamsVin.topMargin = 50;
            tvVin.setTextColor(Color.BLACK);
            tvVin.setTextSize(20);
            tvVin.setTypeface(tvMake.getTypeface(), Typeface.BOLD);
            tvVin.setLayoutParams(layoutParamsVin);

            vinLayout.addView(tvVinHeading);
            vinLayout.addView(tvVin);


            //Year layout with horizontal layout
            LinearLayout yearLayout = new LinearLayout(itemView.getContext());
            yearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams expandLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10);
            expandLayoutParams.leftMargin = 70;
            yearLayout.setLayoutParams(expandLayoutParams);


            TextView tvYearHeading = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsYearHeading = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 7);
            tvYearHeading.setText(context.getString(R.string.year));
            tvYearHeading.setTextColor(Color.GRAY);
            tvYearHeading.setTextSize(16);
            tvYearHeading.setLayoutParams(layoutParamsYearHeading);

            tvYear = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsYear = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 3);
            tvYear.setTextSize(20);
            tvYear.setTextColor(Color.BLACK);
            tvYear.setTypeface(tvMake.getTypeface(), Typeface.BOLD);
            tvYear.setLayoutParams(layoutParamsYear);

            yearLayout.addView(tvYearHeading);
            yearLayout.addView(tvYear);

            //Make layout with horizontal layout
            LinearLayout makeLayout = new LinearLayout(itemView.getContext());
            makeLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams makeLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10);
            makeLayoutParams.leftMargin = 70;
            makeLayout.setLayoutParams(makeLayoutParams);


            TextView tvMakeHeading = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsMakeHeading = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 7);
            tvMakeHeading.setText(context.getString(R.string.make));
            tvMakeHeading.setTextColor(Color.GRAY);
            tvMakeHeading.setTextSize(16);
            tvMakeHeading.setLayoutParams(layoutParamsMakeHeading);

            tvExpandMake = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsMake = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 3);
            tvExpandMake.setTextSize(20);
            tvExpandMake.setTypeface(tvMake.getTypeface(), Typeface.BOLD);
            tvExpandMake.setTextColor(Color.BLACK);
            tvExpandMake.setLayoutParams(layoutParamsMake);

            makeLayout.addView(tvMakeHeading);
            makeLayout.addView(tvExpandMake);

            //Value layout with horizontal layout
            LinearLayout valueLayout = new LinearLayout(itemView.getContext());
            valueLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams valueLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10);
            valueLayoutParams.leftMargin = 70;
            valueLayout.setLayoutParams(valueLayoutParams);


            TextView tvValueHeading = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsValueHeading = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 7);
            tvValueHeading.setText(context.getString(R.string.value));
            tvValueHeading.setTextColor(Color.GRAY);
            tvValueHeading.setTextSize(16);
            tvValueHeading.setLayoutParams(layoutParamsValueHeading);

            tvValue = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsValue = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 3);
            tvValue.setTextSize(20);
            tvValue.setTypeface(tvMake.getTypeface(), Typeface.BOLD);
            tvValue.setTextColor(Color.BLACK);
            tvValue.setLayoutParams(layoutParamsValue);

            valueLayout.addView(tvValueHeading);
            valueLayout.addView(tvValue);

            //Length layout with horizontal layout
            LinearLayout lengthLayout = new LinearLayout(itemView.getContext());
            lengthLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams lengthLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10);
            lengthLayoutParams.leftMargin = 70;
            lengthLayout.setLayoutParams(lengthLayoutParams);


            TextView tvLengthHeading = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsLengthHeading = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 7);
            tvLengthHeading.setText(context.getString(R.string.length));
            tvLengthHeading.setTextSize(16);
            tvLengthHeading.setTextColor(Color.GRAY);
            tvLengthHeading.setLayoutParams(layoutParamsLengthHeading);

            tvLength = new TextView(itemView.getContext());
            LinearLayout.LayoutParams layoutParamsLength = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 3);
            tvLength.setTextSize(20);
            tvLength.setTypeface(tvMake.getTypeface(), Typeface.BOLD);
            tvLength.setTextColor(Color.BLACK);
            tvLength.setLayoutParams(layoutParamsLength);

            lengthLayout.addView(tvLengthHeading);
            lengthLayout.addView(tvLength);


            //adding All Layouts
            expandLayout.addView(vinLayout);
            expandLayout.addView(yearLayout);
            expandLayout.addView(makeLayout);
            expandLayout.addView(valueLayout);
            expandLayout.addView(lengthLayout);

            expandLayout.setVisibility(View.GONE);
            constraintLayout.addView(expandLayout);


            itemView.setOnClickListener(view -> {

                if (imageView.getTag().equals(context.getString(R.string.up_arrow))) {
                    expandLayout.setVisibility(View.VISIBLE);
                    imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_keyboard_arrow_down_24));
                    imageView.setTag(context.getString(R.string.down_arrow));
                } else {
                    expandLayout.setVisibility(View.GONE);
                    imageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_baseline_keyboard_arrow_right_24));
                    imageView.setTag(context.getString(R.string.up_arrow));
                }


            });
        }
    }


}
