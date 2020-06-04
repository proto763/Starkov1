package com.example.starkov;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.starkov.data.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class RegistrationActivity extends AppCompatActivity {
    
    @BindView(R.id.nickname) EditText nicknameET;
    @BindView(R.id.email) EditText emailET;
    @BindView(R.id.phone) EditText phoneET;
    @BindView(R.id.password) EditText passwordET;
    @BindView(R.id.regBT) Button regBT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ButterKnife.bind(this);

        regBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final User user = new User();
                user.RegisterNewUserInDatabase(
                        nicknameET.getText().toString(),
                        emailET.getText().toString(),
                        passwordET.getText().toString(),
                        phoneET.getText().toString(),
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Intent intent = new Intent();
                                intent.putExtra("user", user);
                                setResult(RESULT_OK, intent);
                                finish();
                            }
                        },
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toasty.error(getApplicationContext(), "Нет соединения с сервером").show();
                            }
                        });
            }
        });
    }

}