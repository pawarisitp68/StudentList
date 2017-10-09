package com.example.user.studentlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {
    private EditText et_stu,et_name,et_class;
    private Button btn_insert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);
        Bundle bundle = getIntent().getExtras();

        bindView();

        btn_insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                insertData();
            }
        });
        if (bundle !=null){
            final String position = bundle.getString("position");
            btn_insert.setText("update");
            btn_insert.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    updateDate(position);
                }
            });
        }
    }

    private void bindView(){
        et_stu = (EditText) findViewById(R.id.et_stu);
        et_name = (EditText) findViewById(R.id.et_name);
        et_class = (EditText) findViewById(R.id.et_class);
        btn_insert = (Button) findViewById(R.id.btn_insert);
    }

    private void insertData(){
        String stu = et_stu.getText().toString();
        String name = et_name.getText().toString();
        String room= et_class.getText().toString();
        if (!stu.isEmpty()&&!name.isEmpty()&&!room.isEmpty()){
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.insertUser(stu,name,room);
            finish();
        }else{
            Toast.makeText(this,"กรุณากรอกข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
        }
    }

    private void updateDate(String id) {
        String stu = et_stu.getText().toString();
        String name = et_name.getText().toString();
        String room = et_class.getText().toString();
        if (!stu.isEmpty() && !name.isEmpty() && !room.isEmpty()) {
            DatabaseHelper helper = new DatabaseHelper(this);
            helper.insertUser(stu, name, room);
            finish();
        } else {
            Toast.makeText(this, "กรุณากรอกข้อมูลให้ครบ", Toast.LENGTH_SHORT).show();
        }
    }
}
