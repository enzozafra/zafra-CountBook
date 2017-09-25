package com.example.enzozafra.zafra_countbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NewCounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_counter);

        final Button okButton = (Button) findViewById(R.id.okButton);
        final Button cancelButton = (Button) findViewById(R.id.cancelButton);
        final EditText nameEdit = (EditText) findViewById(R.id.nameEditText);
        final EditText initEdit = (EditText) findViewById(R.id.initEditText);
        final EditText commentEdit = (EditText) findViewById(R.id.commentEditView);

        // TODO: button becomes enabled when one of the box passes the test/
        nameEdit.addTextChangedListener(new TextValidator(nameEdit) {
            @Override public void validate(TextView textView, String text) {
                if ((text.isEmpty() || initEdit.getText().toString().isEmpty()) && okButton.isEnabled()) {
                    okButton.setEnabled(false);
                } else {
                    okButton.setEnabled(true);
                }
                Log.d("validatetest", text);
            }
        });

        initEdit.addTextChangedListener(new TextValidator(initEdit) {
            @Override public void validate(TextView textView, String text) {
                if ((text.isEmpty() || nameEdit.getText().toString().isEmpty()) && okButton.isEnabled()) {
                    okButton.setEnabled(false);
                } else {
                    okButton.setEnabled(true);
                }
                Log.d("validatetest", text);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                Integer initialValue = Integer.valueOf(initEdit.getText().toString());
                String comment = commentEdit.getText().toString();

                Counter newCounter = new Counter(name, initialValue, comment);
                Log.d("activityresult", newCounter.toString());

                Intent output = new Intent();
                output.putExtra("NEW_COUNTER", newCounter);
                setResult(RESULT_OK, output);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
