package com.gladiator.code.merchant.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gladiator.code.merchant.R;
import com.gladiator.code.merchant.Utils.SharedPreferenceStore;

public class LoadOffLineAmountActivity extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private EditText eAmount;
    private Button btnLoadOfflineAmount;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_off_line_amount);

        mContext =getBaseContext();

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        eAmount = (EditText) findViewById(R.id.et_amount);
        btnLoadOfflineAmount = (Button) findViewById(R.id.btn_load);

        btnLoadOfflineAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(eAmount.getText().toString()) && !TextUtils.isDigitsOnly(eAmount.getText().toString())){
                    Toast.makeText(mContext, "Enter proper amount", Toast.LENGTH_LONG).show();
                }else{
                    btnLoadOfflineAmount.setEnabled(false);
                    mProgressBar.setVisibility(View.VISIBLE);
                    new LoadOfflineAmount(Integer.parseInt(eAmount.getText().toString())).execute();
                }

            }
        });

    }

    public void loadOfflineAmount(View view) {

    }

    class LoadOfflineAmount extends AsyncTask<Void, Void, Boolean>{

        private final int amount;

        public LoadOfflineAmount(int amount){
            this.amount = amount;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            btnLoadOfflineAmount.setEnabled(true);
            mProgressBar.setVisibility(View.INVISIBLE);
            int currentAmount = SharedPreferenceStore.getValue(mContext, SharedPreferenceStore.KEY_OFFLINE_AMOUNT, 0);
            SharedPreferenceStore.storeValue(mContext, SharedPreferenceStore.KEY_OFFLINE_AMOUNT, amount + currentAmount);
            finish();
        }
    }

}
