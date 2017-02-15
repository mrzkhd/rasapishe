package com.rasapishe.boom.screen;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.rasapishe.boom.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

public class FactorActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    AppCompatRadioButton normalRadioButton;
    AppCompatRadioButton sotRadioButton;
    TextView totalAmountTextView;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factor);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        normalRadioButton = (AppCompatRadioButton) findViewById(R.id.radio_normal);
        sotRadioButton = (AppCompatRadioButton) findViewById(R.id.radio_sot);
        totalAmountTextView = (TextView) findViewById(R.id.totalAmount);

        normalRadioButton.setOnCheckedChangeListener(this);
        sotRadioButton.setOnCheckedChangeListener(this);
        findViewById(R.id.createButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialDialog.Builder builder = new MaterialDialog.Builder(FactorActivity.this)
                        .positiveText(R.string.ok_button_text)
                        .title(R.string.membership_dialog_title)
                        .titleGravity(GravityEnum.START)
                        .buttonsGravity(GravityEnum.START)
                        .content(R.string.success_factor_dialog_text)
                        .callback(new MaterialDialog.ButtonCallback() {
                            @Override
                            public void onPositive(MaterialDialog dialog) {
                                finish();
                            }
                        });
                builder.build().show();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (sotRadioButton.isChecked()) {
            findViewById(R.id.countView).setVisibility(View.VISIBLE);
            findViewById(R.id.startDate).setVisibility(View.VISIBLE);
            totalAmountTextView.setText("600,000 ریال");
        } else {
            findViewById(R.id.countView).setVisibility(View.GONE);
            findViewById(R.id.startDate).setVisibility(View.GONE);
            totalAmountTextView.setText("200,000 ریال");
        }
    }
}
