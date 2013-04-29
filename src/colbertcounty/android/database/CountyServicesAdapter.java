package colbertcounty.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CountyServicesAdapter {

	public static final String DATABASE_TABLE_2 = "un_common_names";

	public static final String COUNTY_SERVICE_ROWID = "_id";
	public static final String COUNTY_SERVICE_NAME = "un_common_name";
	public static final String COUNTY_SERVICE_PHONE = "un_common_name_meaning";
	public static final String COUNTY_SERVICE_ADDRESS = "un_common_name_address";

	private SQLiteDatabase database;

	public static final String TAG = "COUNTY_SERVICES_TABLE";
	private ColbertCountyDBHelper colbert_county_db_helper;

	public CountyServicesAdapter(Context context) {

	}

	public CountyServicesAdapter open(Context context) throws SQLException {

		Log.i(TAG, "Opening DataBase Connection....");
		colbert_county_db_helper = new ColbertCountyDBHelper(context);
		database = colbert_county_db_helper.getWritableDatabase();
		return this;
	}

	public void close() {
		database.close();
	}

	public boolean deleteCountyService(long rowId) {
		return database.delete(DATABASE_TABLE_2, COUNTY_SERVICE_ROWID + "="
				+ rowId, null) > 0;
	}

	public Cursor fetchAllCountyServices() {
		return database.query(DATABASE_TABLE_2, new String[] {
				COUNTY_SERVICE_ROWID, COUNTY_SERVICE_NAME, COUNTY_SERVICE_PHONE,
				COUNTY_SERVICE_ADDRESS }, null, null, null, null,
				COUNTY_SERVICE_NAME);
	}

	public Cursor fetchCountyService(long uncommonNameId) throws SQLException {

		Cursor mCursor = database.query(true, DATABASE_TABLE_2, new String[] {
				COUNTY_SERVICE_ROWID, COUNTY_SERVICE_NAME }, COUNTY_SERVICE_ROWID
				+ "=" + uncommonNameId, null, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public boolean updateCountyService(int uncommonNameId, String uncommonName,
			String unCommonNameMeaning) {
		ContentValues args = new ContentValues();
		args.put(COUNTY_SERVICE_NAME, uncommonName);
		args.put(COUNTY_SERVICE_ADDRESS, uncommonName);
		args.put(COUNTY_SERVICE_PHONE, unCommonNameMeaning);

		return database.update(DATABASE_TABLE_2, args, COUNTY_SERVICE_ROWID,
				null) > 0;
	}

}
