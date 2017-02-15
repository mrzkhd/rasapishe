package com.rasapishe.boom.screen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rasapishe.boom.R;
import com.rasapishe.boom.objectmodel.Business;
import com.rasapishe.boom.objectmodel.BusinessContent;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * An activity representing a single Business detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link BusinessListActivity}.
 */
public class BusinessDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String ARG_ITEM_ID = "ITEM_ID";
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(BusinessDetailActivity.class);

    private static final double iranLatitude = 32.0000;
    private static final double iranLongitude = 53.0000;

    private Business mItem;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_detail);

        if (getIntent().getExtras() != null) {
            Bundle params = getIntent().getExtras();
            mItem = BusinessContent.ITEM_MAP.get(params.getString(ARG_ITEM_ID));
            setTitle("");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        logger.debug("map should initiate itself");
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        init();
    }

    private void init() {
        if (mItem.getTitle() != null)
            ((TextView) findViewById(R.id.name)).setText(mItem.getTitle());

        ((RatingBar) findViewById(R.id.ratingBar)).setRating(mItem.getRanking());

        if (mItem.getPhone() != null)
            ((TextView) findViewById(R.id.phone)).setText(mItem.getPhone());
        else
            findViewById(R.id.phoneRow).setVisibility(View.GONE);

        if (mItem.getAddress() != null)
            ((TextView) findViewById(R.id.address)).setText(mItem.getAddress());
        else
            findViewById(R.id.address).setVisibility(View.GONE);

        if (mItem.getDescription() != null)
            ((TextView) findViewById(R.id.description)).setText(mItem.getDescription());
        else
            findViewById(R.id.description).setVisibility(View.GONE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, BusinessListActivity.class));
            return true;
        }

        if(id == R.id.action_member){
            MaterialDialog.Builder builder = new MaterialDialog.Builder(BusinessDetailActivity.this)
                    .positiveText(R.string.ok_button_text)
                    .negativeText(R.string.cancel_button_text)
                    .title(R.string.membership_dialog_title)
                    .titleGravity(GravityEnum.START)
                    .buttonsGravity(GravityEnum.START)
                    .content(R.string.membership_dialog_text);
            builder.build().show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.business_detail_menu, menu);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Bitmap pointIconBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.point);
        Bitmap resizedPointIconBitmap = Bitmap.createScaledBitmap(pointIconBitmap, pointIconBitmap.getWidth() / 2, pointIconBitmap.getHeight() / 2, false);

        BitmapDescriptor bitmapDescriptorPoint = BitmapDescriptorFactory.fromBitmap(resizedPointIconBitmap);

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(mItem.getLat(), mItem.getLang()))
                .icon(bitmapDescriptorPoint)
                .visible(true));

        logger.info("location is null: camera will move to latitude: {} and longitude: {}", iranLatitude, iranLongitude);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(iranLatitude, iranLongitude))      // Sets the center of the map to Iran
                .zoom(6)                   // Sets the zoom
                .build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }
}
