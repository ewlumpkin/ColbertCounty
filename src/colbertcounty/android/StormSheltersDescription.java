package colbertcounty.android;

import colbertcounty.android.database.StormSheltersAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import balu.android.R;

public class StormSheltersDescription extends Activity {

	String storm_shelters_name;
	String storm_shelters_location;
	long storm_shelters_rowid;

	CharSequence address = "\nAddress:\n";
	CharSequence description;
	public static TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.storm_shelters_description);

		Bundle extras = getIntent().getExtras();

		storm_shelters_rowid = extras
				.getLong(StormSheltersAdapter.STORM_SHELTERS_ROWID);
		storm_shelters_name = extras
				.getString(StormSheltersAdapter.STORM_SHELTERS_NAME);
		storm_shelters_location = extras.getString(
				StormSheltersAdapter.STORM_SHELTERS_LOCATION).toString();

		description = storm_shelters_name + address + storm_shelters_location;
		tv = (TextView) findViewById(R.id.storm_shelters_description_text);

		tv.setText(description);

	}

}
