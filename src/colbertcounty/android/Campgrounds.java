package colbertcounty.android;

import colbertcounty.android.database.CampgroundsAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import balu.android.R;

public class Campgrounds extends ListActivity {

	CampgroundsAdapter cnTable;
	ListView cnListView;
	Cursor c;

	private static final int CAMPGROUND_ACTIVITY_START = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.campgrounds_list);

		cnTable = new CampgroundsAdapter();
		cnTable.open(getApplicationContext());

		c = cnTable.fetchAllCampgrounds();
		startManagingCursor(c);

		if (c != null) {
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
					R.layout.campgrounds_rows, c,
					new String[] { c.getColumnName(1) },
					new int[] { R.id.commonName });
			setListAdapter(adapter);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		c.moveToPosition(position);

		Intent i = new Intent(this, CampgroundsDescription.class);
		i.putExtra(CampgroundsAdapter.CAMPGROUND_ROW_ID, id);
		i.putExtra(CampgroundsAdapter.CAMPGROUND_NAME, c.getString(c
				.getColumnIndexOrThrow(CampgroundsAdapter.CAMPGROUND_NAME)));
		i.putExtra(CampgroundsAdapter.CAMPGROUND_ADDRESS, c.getString(c
				.getColumnIndexOrThrow(CampgroundsAdapter.CAMPGROUND_ADDRESS)));
		i.putExtra(CampgroundsAdapter.CAMPGROUND_PHONE, c.getString(c
				.getColumnIndexOrThrow(CampgroundsAdapter.CAMPGROUND_PHONE)));
		startActivityForResult(i, CAMPGROUND_ACTIVITY_START);
	}

	@Override
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
		cnTable.close();
	}

}