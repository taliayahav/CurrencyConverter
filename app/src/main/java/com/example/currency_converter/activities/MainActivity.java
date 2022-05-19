package com.example.currency_converter.activities;

import android.os.Bundle;

import com.example.currency_converter.R;
import com.example.currency_converter.lib.Utils;
import com.example.currency_converter.models.CurrencyConverter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.currency_converter.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CurrencyConverter mCurrencyConverter;
    private boolean mClearAmountAfterCalculation;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // save the mClearAmount boolean
        outState.putBoolean("CLEAR", mClearAmountAfterCalculation);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // restore the clear amount boolean from the bundle
        mClearAmountAfterCalculation = savedInstanceState.getBoolean("CLEAR");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        setupVariables();
        initializeInfoFAB();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the information, meaning the currencyFrom and percentage, from the EditTexts
                // send the information to the model using the model's setters
                String currencyFromString = binding.contentMain.includeFrom.currencyAmountFromEditText.getText().toString();
                double currencyFrom = Double.parseDouble(currencyFromString);

                // do the same for percentage
                String currencyPercentageString = binding.contentMain.includeTo.currencyPercentageEditText.getText().toString();
                double currencyPercentage = Double.parseDouble(currencyPercentageString);

                mCurrencyConverter.setCurrencyFromAmount(currencyFrom);
                mCurrencyConverter.setConversionPercentage(currencyPercentage);

                String currencyFromType = "", currencyToType = "";

                // In the Snackbar, send the output from the model's getConvertedCurrencyAmount()
                String msg = currencyFromType + " " + currencyFromString + " to " + currencyToType
                        + ": " + mCurrencyConverter.getConvertedCurrencyAmount();
                Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();

                if(mClearAmountAfterCalculation){
                    binding.contentMain.includeFrom.currencyAmountFromEditText.setText("");
                    binding.contentMain.includeTo.currencyPercentageEditText.setText("");
                }
            }
        });

    }

    private void setupVariables() {
        mCurrencyConverter = new CurrencyConverter();
        mClearAmountAfterCalculation = false;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_clear_text).setChecked(mClearAmountAfterCalculation);
        return super.onPrepareOptionsMenu(menu);
    }

    private void toggleMenuItem(MenuItem item) {
        item.setChecked(!item.isChecked());
        mClearAmountAfterCalculation = item.isChecked();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            showAbout();
            return true;
        } else if (id == R.id.action_clear_text) {
            toggleMenuItem(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAbout() {
        Utils.showInfoDialog(MainActivity.this, R.string.about,R.string.about_body);
    }

    private void initializeInfoFAB() {
        FloatingActionButton fab = binding.contentMain.infoFab.infoFab;
        fab.setOnClickListener(view -> newInfoFab(fab));
    }
    public void newInfoFab(View view) {
        Utils.showInfoDialog(MainActivity.this, R.string.info,R.string.info_body);
    }
}