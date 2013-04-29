package colbertcounty.android;

import colbertcounty.android.database.CountyServicesAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import balu.android.R;

public class CountyServicesDescrition extends Activity {

	String county_service_name;
	String county_service_address;
	String county_service_phone;
	long county_service_rowid;

	CharSequence phone = "\nPhone Number:\n";
	CharSequence address = "\nAddress:\n";
	CharSequence description;
	public static TextView tv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.county_services_description);

		Bundle extras = getIntent().getExtras();

		county_service_rowid = extras
				.getLong(CountyServicesAdapter.COUNTY_SERVICE_ROWID);
		county_service_name = extras.getString(CountyServicesAdapter.COUNTY_SERVICE_NAME);
		county_service_phone = extras.getString(
				CountyServicesAdapter.COUNTY_SERVICE_PHONE).toString();
		county_service_address = extras.getString(
				CountyServicesAdapter.COUNTY_SERVICE_ADDRESS).toString();

		description = county_service_name + phone + county_service_phone + address
				+ county_service_address;
		tv = (TextView) findViewById(R.id.un_common_name_description_text);

		tv.setText(description);

	}

}
