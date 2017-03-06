package com.example.yls.bmobdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static java.lang.Integer.valueOf;

/**
 * Created by Administrator on 2017/3/6.
 */

public class newPerson extends AppCompatActivity{
    private Button btn3,btn4;
    private EditText et1,et2,et3;
    private ImageView iv2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_view);
        initViews();
    }

    private void initViews() {
        btn3= (Button) findViewById(R.id.btn_3);
        btn4= (Button) findViewById(R.id.btn_4);
        et1= (EditText) findViewById(R.id.et_1);
        et2= (EditText) findViewById(R.id.et_2);
        et3= (EditText) findViewById(R.id.et_3);
        iv2= (ImageView) findViewById(R.id.iv_2);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(newPerson.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person p1=new Person();
                p1.setName(et1.getText().toString().trim());
                p1.setAddress(et2.getText().toString().trim());
                p1.setAge(valueOf(et3.getText().toString().trim()));
                p1.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            Toast.makeText(newPerson.this,"插入成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(newPerson.this,"很遗憾，插入失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
