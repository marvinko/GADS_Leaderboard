package com.andela.gads;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.andela.gads.api.SubmitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissionActivity extends AppCompatActivity {

    Button button;
    EditText nameInput;
    EditText lastNameInput;
    EditText emailInput;
    EditText linkProjectInput;

    //DialogBoxes
    Dialog submitDialog;
    Dialog successDialog;
    Dialog failureDialog;
    Context myContext;

    //Retrofit Instance
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://docs.google.com/forms/d/e/")
            .addConverterFactory(GsonConverterFactory.create());
    public static Retrofit retrofit = builder.build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);


        myContext = this;
        button = findViewById(R.id.button);
        nameInput = findViewById(R.id.editText);
        lastNameInput = findViewById(R.id.editText2);
        emailInput = findViewById(R.id.editText3);
        linkProjectInput = findViewById(R.id.editText4);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name,lastName,email,linkProject;
                name = nameInput.getText().toString();
                lastName = lastNameInput.getText().toString();
                email = emailInput.getText().toString();
                linkProject = linkProjectInput.getText().toString();
                ShowPopup(myContext,name,lastName,email,linkProject);

            }
        });


    }



    private void executesendProject(final Context context, String name, String lastName, String email, String linkProject){
        SubmitClient submitClient = retrofit.create(SubmitClient.class);
        Call<ResponseBody>call =  submitClient.sendProject(
                name,
                lastName,
                email,
                linkProject
        );

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //Toast.makeText(SubmissionActivity.this,"Successful"+response.toString(),Toast.LENGTH_LONG).show();
                showSuccessPopup(context);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Toast.makeText(SubmissionActivity.this,"Error",Toast.LENGTH_LONG).show();
                showFailurePopup(context);
            }
        });

    }

    public void ShowPopup(final Context context, final String fname, final String flastName, final String femail, final String flinkProject){
        submitDialog = new Dialog(context);
        ImageView cancelSubmit;
        Button confirmSubmit;
        submitDialog.setContentView(R.layout.submit_dialog);
        confirmSubmit = submitDialog.findViewById(R.id.confirm_button);
        cancelSubmit = submitDialog.findViewById(R.id.cancel_actionImg);

        confirmSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitDialog.hide();
                executesendProject(context,fname,flastName,femail,flinkProject);
            }
        });
        cancelSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitDialog.dismiss();
            }
        });
        submitDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        submitDialog.show();

    }

    public void showSuccessPopup(Context context){
        successDialog = new Dialog(context);
        successDialog.setContentView(R.layout.success_dialog);
        successDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        successDialog.show();
    }
    public void showFailurePopup(Context context){
        failureDialog = new Dialog(context);
        failureDialog.setContentView(R.layout.failure_dialog);
        failureDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        failureDialog.show();

    }

}
