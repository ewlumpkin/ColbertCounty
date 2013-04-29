package colbertcounty.android;

import colbertcounty.android.database.CampgroundsAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import balu.android.R;

public class CampgroundsDescription extends Activity {

	String campground_name;
	String campground_address;
	String campground_phone;
	long campground_rowid;

	CharSequence address = "\nAddress:\n";
	CharSequence phone = "\nPhone Number:\n";
	CharSequence description;

	public static TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.campgrounds_description);

		Bundle extras = getIntent().getExtras();

		campground_rowid = extras
				.getLong(CampgroundsAdapter.CAMPGROUND_ROW_ID);
		campground_name = extras.getString(CampgroundsAdapter.CAMPGROUND_NAME);
		campground_address = extras.getString(
				CampgroundsAdapter.CAMPGROUND_ADDRESS).toString();
		campground_phone = extras
				.getString(CampgroundsAdapter.CAMPGROUND_PHONE).toString();

		description = campground_name + address + campground_address + phone
				+ campground_phone;
		tv = (TextView) findViewById(R.id.common_name_description_text);

		tv.setText(description);

	}

}
