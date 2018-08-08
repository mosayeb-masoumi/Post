package com.example.tornado.post;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView mResponseTv;
    APIService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText titleEt = (EditText) findViewById(R.id.et_title);
        final EditText bodyEt = (EditText) findViewById(R.id.et_body);
        Button submitBtn = (Button) findViewById(R.id.btn_submit);
        mResponseTv = (TextView) findViewById(R.id.tv_response);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title=titleEt.getText().toString();
                String  body=bodyEt.getText().toString();

                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(body)){

                    sendPost(title,body);
                }

            }
        });

    }

    private void sendPost(String title, String body) {

        mApiService=APIClient.getClient().create(APIService.class);
        Call<Model> call=mApiService.model(title,body, (long) 1);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                if(response.isSuccessful()){
                  showResponse(response.body().toString()) ;
                }else{
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();}
        });
    }

    private void showResponse(String response) {
        if(mResponseTv.getVisibility()==View.GONE){
            mResponseTv.setVisibility(View.VISIBLE);
        }

        mResponseTv.setText(response);
    }


}
