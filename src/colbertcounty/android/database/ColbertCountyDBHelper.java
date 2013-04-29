package colbertcounty.android.database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import balu.android.R;

public class ColbertCountyDBHelper extends SQLiteOpenHelper {

	// Name and version of Database.
	public static final String DATABASE_NAME = "baby_names_database";
	public static final int DATABASE_VERSION = 1;

	// Names of Tables in Database
	public static final String DATABASE_TABLE_1 = "common_names";
	public static final String DATABASE_TABLE_2 = "un_common_names";
	public static final String DATABASE_TABLE_3 = "storm_shelters";

	// Columns present in DATABASE_TABLE_1
	public static final String CAMGROUND_ROW_ID = "_id";
	public static final String CAMPGROUND_NAME = "campground_name";
	public static final String CAMPGROUND_ADDRESS = "common_name_count";
	public static final String CAMPGROUND_PHONE = "campground_phone";

	// Columns present in DATABASE_TABLE_2
	public static final String COUNTY_SERVICE_ROWID = "_id";
	public static final String COUNTY_SERVICE_NAME = "un_common_name";
	public static final String COUNTY_SERVICE_PHONE = "un_common_name_meaning";
	public static final String COUNTY_SERVICE_ADDRESS = "un_common_name_address";

	// Columns present in DATABASE_TABLE_3
	public static final String STORM_SHELTERS_ROWID = "_id";
	public static final String STORM_SHELTERS_NAME = "storm_shelters_name";
	public static final String STORM_SHELTERS_LOCATION = "storm_shelters_location";

	// SQL query string for creating DATABASE_TABLE_1
	static final String CREATE_DATABASE_TABLE_1 = "create table "
			+ DATABASE_TABLE_1 + " (" + CAMGROUND_ROW_ID
			+ " integer primary key autoincrement, " + CAMPGROUND_ADDRESS
			+ " text not null, " + CAMPGROUND_NAME + " text not null, "
			+ CAMPGROUND_PHONE + " text not null);";

	// SQL query string for creating DATABASE_TABLE_2
	static final String CREATE_DATABASE_TABLE_2 = "create table "
			+ DATABASE_TABLE_2 + " (" + COUNTY_SERVICE_ROWID
			+ " integer primary key autoincrement, " + COUNTY_SERVICE_NAME
			+ " text not null, " + COUNTY_SERVICE_ADDRESS + " text not null, "
			+ COUNTY_SERVICE_PHONE + " text not null);";

	// SQL query string for creating DATABASE_TABLE_3
	static final String CREATE_DATABASE_TABLE_3 = "create table "
			+ DATABASE_TABLE_3 + " (" + STORM_SHELTERS_ROWID
			+ " integer primary key autoincrement, " + STORM_SHELTERS_NAME
			+ " text not null, " + STORM_SHELTERS_LOCATION + " text not null);";

	// TAGs for tables. Used in Log Cat.
	public static final String TAG_1 = "COMMON_NAMES_TABLE";
	public static final String TAG_2 = "UNCOMMON_NAMES_TABLE";
	public static final String TAG_3 = "STORM_SHELTERS_TABLE";

	// Object for a SQLiteDatabase
	public SQLiteDatabase mDb;
	private Context context;

	// Constructor
	public ColbertCountyDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// Creating Table
		Log.i(TAG_1, "Creating Table: " + CREATE_DATABASE_TABLE_1);
		db.execSQL(CREATE_DATABASE_TABLE_1);
		insertDataIntoCommonNames(db);

		// Creating Table
		Log.i(TAG_2, "Creating Table: " + CREATE_DATABASE_TABLE_2);
		db.execSQL(CREATE_DATABASE_TABLE_2);
		insertDataIntoUnCommonNames(db);

		// Creating Table
		Log.i(TAG_2, "Creating Table: " + CREATE_DATABASE_TABLE_3);
		db.execSQL(CREATE_DATABASE_TABLE_3);
		insertDataIntoStormShelters(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TAG_1, "Upgrading database from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");

		// We have to drop existing database tables
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_1);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_2);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_3);

		// Now, re-create the database.
		onCreate(db);

	}

	private void insertDataIntoCommonNames(SQLiteDatabase db) {

		try {
			InputStream is = context.getResources().openRawResource(
					R.raw.commonnames);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String strLine = null;

			while ((strLine = (br.readLine()).trim()) != null) {
				String[] temp;

				temp = strLine.split(":");

				ContentValues initialValues = new ContentValues();

				initialValues.put(CAMPGROUND_NAME, temp[0].trim());
				initialValues.put(CAMPGROUND_ADDRESS, temp[1].trim());
				initialValues.put(CAMPGROUND_PHONE, temp[2].trim());

				db.insert(DATABASE_TABLE_1, null, initialValues);
			}

			is.close();
		} catch (Exception e) {
			Log.i(TAG_1, "Error while inserting common names into table");
		}

	}

	private void insertDataIntoUnCommonNames(SQLiteDatabase db) {

		try {
			InputStream is = context.getResources().openRawResource(
					R.raw.uncommonnames);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String strLine = null;

			while ((strLine = br.readLine()) != null) {
				String[] temp;

				strLine = strLine.trim();
				temp = strLine.split(":");

				ContentValues initialValues = new ContentValues();

				initialValues.put(COUNTY_SERVICE_NAME, temp[0]);
				initialValues.put(COUNTY_SERVICE_PHONE, temp[1].trim());
				initialValues.put(COUNTY_SERVICE_ADDRESS, temp[2]);

				db.insert(DATABASE_TABLE_2, null, initialValues);
			}
			is.close();
		} catch (Exception e) {
			Log.i(TAG_2, "Error while inserting common names into table");
		}
	}

	private void insertDataIntoStormShelters(SQLiteDatabase db) {

		try {
			InputStream is = context.getResources().openRawResource(
					R.raw.stormshelters);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String strLine = null;

			while ((strLine = (br.readLine()).trim()) != null) {
				String[] temp;

				temp = strLine.split(":");

				ContentValues initialValues = new ContentValues();

				initialValues.put(STORM_SHELTERS_NAME, temp[0].trim());
				initialValues.put(STORM_SHELTERS_LOCATION, temp[1].trim());

				db.insert(DATABASE_TABLE_3, null, initialValues);
			}

			is.close();
		} catch (Exception e) {
			Log.i(TAG_1, "Error while inserting common names into table");
		}

	}
}
