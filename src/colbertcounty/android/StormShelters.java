package colbertcounty.android;

import colbertcounty.android.database.StormSheltersAdapter;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import balu.android.R;

public class StormShelters extends ListActivity {

	private static final int STORM_SHELTERS_ACTIVITY_START = 0;
	StormSheltersAdapter ssTable;
	ListView ssListView;
	Cursor c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.storm_shelters_list);

		ssTable = new StormSheltersAdapter(this);
		ssTable.open(getApplicationContext());

		c = ssTable.fetchAllStormShelters();
		startManagingCursor(c);

		if (c != null) {
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
					R.layout.storm_shelters_row, c,
					new String[] { c.getColumnName(1) },
					new int[] { R.id.stormShelters });

			setListAdapter(adapter);
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		c.moveToPosition(position);
		Intent i = new Intent(this, StormSheltersDescription.class);
		i.putExtra(StormSheltersAdapter.STORM_SHELTERS_ROWID, id);
		i.putExtra(
				StormSheltersAdapter.STORM_SHELTERS_NAME,
				c.getString(c
						.getColumnIndexOrThrow(StormSheltersAdapter.STORM_SHELTERS_NAME)));
		i.putExtra(
				StormSheltersAdapter.STORM_SHELTERS_LOCATION,
				c.getString(c
						.getColumnIndexOrThrow(StormSheltersAdapter.STORM_SHELTERS_LOCATION)));
		startActivityForResult(i, STORM_SHELTERS_ACTIVITY_START);
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
		ssTable.close();
	}

}
