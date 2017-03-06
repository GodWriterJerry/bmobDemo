package com.example.yls.bmobdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity {
    private Button btn1,btn2;
    private TextView tv1;
    private List<Person>data=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private PersonAdapter mPersonAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1= (Button) findViewById(R.id.btn_1);
        btn2= (Button) findViewById(R.id.btn_2);
        mRecyclerView= (RecyclerView) findViewById(R.id.rv_1);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mPersonAdapter=new PersonAdapter(data);
        mRecyclerView.setAdapter(mPersonAdapter);
        Bmob.initialize(this, "411d05d98700cf8f3ffb72ef0ae4d43a");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryAll();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,newPerson.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void queryAll() {
        BmobQuery<Person> query = new BmobQuery<Person>();
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> list, BmobException e) {
                if(e==null){
                    for (int i = 0; i < list.size(); i++) {
                        data.add(new Person(list.get(i).getAddress(),list.get(i).getAge(),list.get(i).getName()));
                    }
                    mPersonAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void queryOne(){
        BmobQuery<Person> bmobQuery=new BmobQuery<Person>();
        bmobQuery.getObject("3040961068", new QueryListener<Person>() {
            @Override
            public void done(Person person, BmobException e) {
                tv1.setText(person.getName()+"    "+person.getAddress()+"    "+person.getAge());
            }
        });
    }
    private void add(){
        Person p1=new Person();
        p1.setName("kitty");
        p1.setAge(19);
        p1.setAddress("天源路789号");
        p1.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this,"插入成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"很遗憾，查询失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /*private void add(String name,int age,String address){
        Person p1=new Person();
        p1.setName(name);
        p1.setAge(age);
        p1.setAddress(address);
        p1.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    Toast.makeText(MainActivity.this,"插入成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"很遗憾，查询失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/
}
