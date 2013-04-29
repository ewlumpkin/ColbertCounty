package colbertcounty.android;

import colbertcounty.android.database.CountyServicesAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import balu.android.R;

public class CountyServices extends ListActivity {

	private static final int COUNTY_SERVICES_ACTIVITY_START = 0;
	CountyServicesAdapter ucnTable;
	ListView ucnListView;
	Cursor c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.county_services_list);

		ucnTable = new CountyServicesAdapter(this);
		ucnTable.open(getApplicationContext());

		c = ucnTable.fetchAllCountyServices();
		startManagingCursor(c);

		if (c != null) {
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
					R.layout.county_services_row, c,
					new String[] { c.getColumnName(1) },
					new int[] { R.id.unCommonName });

			setListAdapter(adapter);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		c.moveToPosition(position);
		Intent i = new Intent(this, CountyServicesDescrition.class);
		i.putExtra(CountyServicesAdapter.COUNTY_SERVICE_ROWID, id);
		i.putExtra(CountyServicesAdapter.COUNTY_SERVICE_NAME, c.getString(c
				.getColumnIndexOrThrow(CountyServicesAdapter.COUNTY_SERVICE_NAME)));
		i.putExtra(
				CountyServicesAdapter.COUNTY_SERVICE_ADDRESS,
				c.getString(c
						.getColumnIndexOrThrow(CountyServicesAdapter.COUNTY_SERVICE_ADDRESS)));
		i.putExtra(
				CountyServicesAdapter.COUNTY_SERVICE_PHONE,
				c.getString(c
						.getColumnIndexOrThrow(CountyServicesAdapter.COUNTY_SERVICE_PHONE)));
		startActivityForResult(i, COUNTY_SERVICES_ACTIVITY_START);
	}

	protected void onActivityResult(int requestCode, int resultCode,
			Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		// Bundle extras = intent.getExtras();
		switch (requestCode) {
		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		c.close();
		ucnTable.close();
	}

}
