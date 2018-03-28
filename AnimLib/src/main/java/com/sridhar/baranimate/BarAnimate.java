package com.sridhar.baranimate;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class BarAnimate extends LinearLayout {

    /** Core Items*/
    private Context mContext;
    private AttributeSet attrs;
    private int styleAttr;
    private View view;
    String edtText;
    /** Core Components*/
    Button button;
    BarView barView;
    EditText edtBarCount;
    HorizontalScrollView scrollView;

    public BarAnimate(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BarAnimate(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        this.attrs=attrs;
        initView();
    }

    private void initView() {
        this.view = this;
        inflate(mContext, R.layout.activity_bar, this);

        scrollView = findViewById(R.id.horizontalScrollView);
        barView = findViewById(R.id.bar_view);
        button = findViewById(R.id.bar_button);
        edtBarCount = findViewById(R.id.edtBarcount);

        randomSet(15,barView);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                edtText = edtBarCount.getText().toString();
                if(edtText.length() > 0)
                    randomSet(Integer.parseInt(edtText),barView);
                else
                    Toast.makeText(mContext, "Give some bar count", Toast.LENGTH_SHORT).show();
            }
        });



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollView.setOnScrollChangeListener(new OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    edtText = edtBarCount.getText().toString();
                    if(edtText.length() > 0){
                        randomSet(Integer.parseInt(edtText),barView);
                    } else {
                        randomSet(15, barView);
                    }

                }
            });
        }

    }

    private void randomSet(int random, BarView barView) {
        ArrayList<String> test = new ArrayList<String>();
        for (int i=0; i<random; i++){
            test.add("test"+i);
        }
        barView.setBottomTextList(test);

        ArrayList<Integer> barDataList = new ArrayList<Integer>();
        for(int i=0; i<random; i++){
            barDataList.add((int)(Math.random() * 20));
        }
        barView.setDataList(barDataList,10);
    }
}
