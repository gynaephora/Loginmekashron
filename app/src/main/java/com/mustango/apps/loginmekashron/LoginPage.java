package com.mustango.apps.loginmekashron;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class LoginPage extends AppCompatActivity {
    EditText loginField;
    EditText passwordField;
    Post postRequest=new Post();
    RequestItemTask requestItemTask=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        final  EditText loginField=(EditText) findViewById(R.id.editText);
        final EditText passwordField=(EditText) findViewById(R.id.editText2);
        Button loginButton=(Button) findViewById(R.id.button);



        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String json = postRequest.loginJson(loginField.getText().toString(), passwordField.getText().toString());
                Log.i("json string",json);
                requestItemTask = (RequestItemTask) new RequestItemTask()
                        .execute(json);

            }
        });
    }

    private class RequestItemTask extends AsyncTask<String,Void, String>{


        protected String doInBackground(String... string) {
            String response = "";
            String json=string[0];
            //  postRequest.PostRequest("http://isapi.mekashron.com/StartAJob/General.dll",json);
            try {
                response = postRequest.PostR("http://isapi.mekashron.com/StartAJob/General.dll/wsdl/IGeneral", json);
                Log.i("request",response);
                //  System.out.print(response);
            } catch (IOException e) {
                //обработка ошибки
            }
            return response;
        }

        @Override
        protected void onPostExecute(String request) {
            final TextView resultMessage = (TextView) findViewById(R.id.textView);
            resultMessage.setText(request);
            Log.i("request",request.toString());
        }

    }

}
