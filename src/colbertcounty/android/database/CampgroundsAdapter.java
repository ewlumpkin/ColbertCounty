package colbertcounty.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CampgroundsAdapter {

	// Database table name
	public static final String DATABASE_TABLE_1 = "common_names";

	// Database table columns for DATABASE_TABLE_1
	public static final String CAMPGROUND_ROW_ID = "_id";
	public static final String CAMPGROUND_NAME = "campground_name";
	public static final String CAMPGROUND_ADDRESS = "common_name_count";
	public static final String CAMPGROUND_PHONE = "campground_phone";

	// Object for SQLiteDatabase
	private SQLiteDatabase database;

	//
	public static final String TAG = "CAMPGROUNDS_TABLE";
	private ColbertCountyDBHelper colbert_county_db_helper;

	public CampgroundsAdapter() {
	}

	public CampgroundsAdapter open(Context context) throws SQLException {
		Log.i(TAG, "Opening DataBase Connection....");
		colbert_county_db_helper = new ColbertCountyDBHelper(context);
		database = colbert_county_db_helper.getWritableDatabase();
		return this;
	}

	public void close() {
		database.close();
	}

	public boolean deleteCampgrounds(long rowId) {
		return database.delete(DATABASE_TABLE_1, CAMPGROUND_ROW_ID + "="
				+ rowId, null) > 0;
	}

	public Cursor fetchAllCampgrounds() {
		return database.query(DATABASE_TABLE_1, new String[] {
				CAMPGROUND_ROW_ID, CAMPGROUND_NAME, CAMPGROUND_ADDRESS,
				CAMPGROUND_PHONE }, null, null, null, null, CAMPGROUND_NAME);
	}

	public Cursor fetchCampgrounds(long commonNameId) throws SQLException {

		Cursor mCursor = database.query(true, DATABASE_TABLE_1, new String[] {
				CAMPGROUND_ROW_ID, CAMPGROUND_NAME, CAMPGROUND_ADDRESS,
				CAMPGROUND_PHONE }, CAMPGROUND_ROW_ID + "=" + commonNameId,
				null, null, null, null, null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public Cursor fetchCampgroundAddress(long commonNameId) throws SQLException {

		Cursor mCursor = database.query(true, DATABASE_TABLE_1, new String[] {
				CAMPGROUND_ROW_ID, CAMPGROUND_ADDRESS, CAMPGROUND_PHONE },
				CAMPGROUND_ROW_ID + "=" + commonNameId, null, null, null, null,
				null);

		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public boolean updateCampground(int commonNameId, String campgroundName,
			String commonNameCount, String campgroundPhone) {
		ContentValues args = new ContentValues();
		args.put(CAMPGROUND_NAME, campgroundName);
		args.put(CAMPGROUND_ADDRESS, commonNameCount);
		args.put(CAMPGROUND_PHONE, campgroundPhone);

		return database.update(DATABASE_TABLE_1, args, CAMPGROUND_ROW_ID + "="
				+ commonNameId, null) > 0;
	}
}
