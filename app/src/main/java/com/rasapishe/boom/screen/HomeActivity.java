package com.rasapishe.boom.screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.rasapishe.boom.R;
import com.rasapishe.boom.enums.BankId;
import com.rasapishe.boom.service.TosanServices;
import com.rasapishe.boom.service.dto.DepositTransferReq;
import com.rasapishe.boom.service.dto.DepositTransferResp;
import com.rasapishe.boom.service.dto.ServiceCallBack;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class HomeActivity extends AppCompatActivity {

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        searchView = (SearchView)findViewById(R.id.searchview);
        if(searchView != null)
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Intent intent = new Intent(HomeActivity.this, BusinessListActivity.class);
                    intent.putExtra(BusinessListActivity.QUERY_KEY, query);
                    startActivity(intent);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });

        findViewById(R.id.allBusinessButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BusinessListActivity.class);
                intent.putExtra(BusinessListActivity.TITLE_KEY, R.string.btn_category_business);
                startActivity(intent);
            }
        });

        findViewById(R.id.myBusinessButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MyBusinessListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
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
           finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //Sample for transfer call
    public void tranfer() {
        DepositTransferReq req = new DepositTransferReq();

        TosanServices tosanServices = new TosanServices();

        tosanServices.depositeTransfer(req, BankId.ANSBIR, "1111", "2016-05-11T23:15:05Z", new ServiceCallBack() {

            @Override
            public void onLoginFailed() {
                //toast user
            }

            @Override
            public void onLoginBoomFailed() {
                //toast user

            }

            @Override
            public void onDepositTransferFailed() {
                //toast user

            }

            @Override
            public void onDepositTransferSuccess(DepositTransferResp depositTransferResp) {
                //toast success, new result screen
            }
        });

    }
}
