package colbertcounty.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class StormSheltersAdapter {

	// Database table name
	public static final String DATABASE_TABLE_1 = "storm_shelters";

	// Database table columns for DATABASE_TABLE_1
	public static final String STORM_SHELTERS_ROWID = "_id";
	public static final String STORM_SHELTERS_NAME = "storm_shelters_name";
	public static final String STORM_SHELTERS_LOCATION = "storm_shelters_location";

	// Object for SQLiteDatabase
	private SQLiteDatabase database;

	//
	public static final String TAG = "STORM_SHELTERS_TABLE";
	private ColbertCountyDBHelper colbert_county_db_helper;

	public StormSheltersAdapter(Context context) {
	}

	public StormSheltersAdapter open(Context context) throws SQLException {
		Log.i(TAG, "Opening DataBase Connection....");
		colbert_county_db_helper = new ColbertCountyDBHelper(context);
		database = colbert_county_db_helper.getWritableDatabase();
		return this;
	}

	public void close() {
		database.close();
	}

	public boolean deleteStormShelters(long rowId) {
		return database.delete(DATABASE_TABLE_1, STORM_SHELTERS_ROWID + "="
				+ rowId, null) > 0;
	}

	public Cursor fetchAllStormShelters() {
		return database.query(DATABASE_TABLE_1, new String[] {
				STORM_SHELTERS_ROWID, STORM_SHELTERS_NAME,
				STORM_SHELTERS_LOCATION }, null, null, null, null,
				STORM_SHELTERS_NAME);
	}

	public Cursor fetchStormShelters(long stormsheltersId) throws SQLException {

		Cursor mCursor = database.query(true, DATABASE_TABLE_1, new String[] {
				STORM_SHELTERS_ROWID, STORM_SHELTERS_NAME,
				STORM_SHELTERS_LOCATION }, STORM_SHELTERS_ROWID + "="
				+ stormsheltersId, null, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public Cursor fetchStormSheltersLocation(long commonNameId)
			throws SQLException {

		Cursor mCursor = database.query(true, DATABASE_TABLE_1, new String[] {
				STORM_SHELTERS_ROWID, STORM_SHELTERS_LOCATION },
				STORM_SHELTERS_ROWID + "=" + commonNameId, null, null, null,
				null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public boolean updateStormShelters(int stormSheltersId,
			String stormSheltersName, String stormSheltersLocation) {
		ContentValues args = new ContentValues();
		args.put(STORM_SHELTERS_NAME, stormSheltersName);
		args.put(STORM_SHELTERS_LOCATION, stormSheltersLocation);

		return database.update(DATABASE_TABLE_1, args, STORM_SHELTERS_ROWID
				+ "=" + stormSheltersId, null) > 0;
	}
}
