package com.emcsthai.builderpatten;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText edtFistName;
    private EditText edtLastName;
    private EditText edtAge;
    private EditText edtPhone;
    private EditText edtAddress;

    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();

        btnSave.setOnClickListener(onButtonClickListener);
    }

    private void initInstance() {

        edtFistName = (EditText) findViewById(R.id.edt_first_name);
        edtLastName = (EditText) findViewById(R.id.edt_last_name);
        edtAge = (EditText) findViewById(R.id.edt_age);
        edtPhone = (EditText) findViewById(R.id.edt_phone);
        edtAddress = (EditText) findViewById(R.id.edt_address);

        btnSave = (Button) findViewById(R.id.btn_save);
    }

    final View.OnClickListener onButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v == btnSave) {

                String message = "Save Completed";

                try {

                    User user = new User.Builder()
                            .firstName(edtFistName.getText().toString())
                            .lastName(edtLastName.getText().toString())
                            .age(edtAge.getText().toString())
                            .phone(edtPhone.getText().toString())
                            .address(edtAddress.getText().toString())
                            .build();

                    GlobalManager.getInstance().addUser(user);

                } catch (IllegalArgumentException | IllegalStateException e) {
                    Throwable t = e.getCause();
                    switch (t.getMessage()) {
                        case User.EnumException.AGE:
                            message = e.getMessage();
                            edtAge.setError(message);
                            edtAge.setClickable(true);
                            break;
                        case User.EnumException.PHONE:
                            message = e.getMessage();
                            edtPhone.setError(message);
                            edtPhone.setClickable(true);
                            break;
                    }
                } finally {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                }

                if (message.equals("Save Completed")) {
                    ArrayList<User> userList = GlobalManager.getInstance().getUserList();
                    Toast.makeText(MainActivity.this, "User Size : " + userList.size(), Toast.LENGTH_SHORT).show();
                    edtFistName.setText("");
                    edtLastName.setText("");
                    edtAge.setText("");
                    edtPhone.setText("");
                    edtAddress.setText("");
                }
            }
        }
    };
}
