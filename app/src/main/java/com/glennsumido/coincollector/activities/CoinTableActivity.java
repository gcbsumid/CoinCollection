package com.glennsumido.coincollector.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.glennsumido.coincollector.R;
import com.glennsumido.coincollector.fragments.CoinDenominationsFragment;
import com.glennsumido.coincollector.fragments.SetSelectionFragment;
import com.glennsumido.coincollector.objects.Country;

public class CoinTableActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_table);

        Intent intent = getIntent();
        Country country = (Country)intent.getSerializableExtra("COUNTRY");
        if (country == null) {
            Log.e("CoinTableActivity", "No country passed into the new activity");
        }
        Log.i("CoinTableActivity", String.format("Country: %s", country.getCountryName().toString()));

        FragmentManager fragmentManager = this.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, CoinDenominationsFragment.newInstance(country))
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_coin_table, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_denomination) {
            // Todo: open a popup form add denomination
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
