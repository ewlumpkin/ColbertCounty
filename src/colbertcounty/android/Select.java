package colbertcounty.android;

import balu.android.R;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class Select extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select);

		View continueButton = findViewById(R.id.about_button);
		continueButton.setOnClickListener(this);

		View commonNamesButton = findViewById(R.id.common_names);
		commonNamesButton.setOnClickListener(this);

		View unCommonNamesButton = findViewById(R.id.un_common_names);
		unCommonNamesButton.setOnClickListener(this);

		View stormSheltersButton = findViewById(R.id.storm_shelters);
		stormSheltersButton.setOnClickListener(this);

		View namesPopularButton = findViewById(R.id.courthouse_map);
		namesPopularButton.setOnClickListener(this);

		View campMapButton = findViewById(R.id.county_website);
		campMapButton.setOnClickListener(this);

	}

	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.about_button:
			Intent i = new Intent(this, About.class);
			startActivity(i);
			break;
		case R.id.common_names:
			Intent cn = new Intent(this, Campgrounds.class);
			startActivity(cn);
			break;
		case R.id.un_common_names:
			Intent ucn = new Intent(this, CountyServices.class);
			startActivity(ucn);
			break;
		case R.id.storm_shelters:
			Intent ss = new Intent(this, StormShelters.class);
			startActivity(ss);
			break;
		case R.id.courthouse_map:
			Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
					Uri.parse("geo:0,0?q=34.733501,-87.703643"));
			intent.setClassName("com.google.android.apps.maps",
					"com.google.android.maps.MapsActivity");
			startActivity(intent);
			break;
		case R.id.county_website:
			String url1 = "http://www.colbertcounty.org/";
			Intent u = new Intent(Intent.ACTION_VIEW);
			u.setData(Uri.parse(url1));
			startActivity(u);
			break;
		}

	}

}
