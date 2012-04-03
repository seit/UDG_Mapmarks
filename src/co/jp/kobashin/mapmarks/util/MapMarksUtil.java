package co.jp.kobashin.mapmarks.util;

public class MapMarksUtil {
	public final static String LOGTAG = "MAPMARKS";

	/* preference */
	public static String PREF_NAME = "bookmark_pref";
	public static String PREF_KEY_MODE = "mode";
	public static int TEMP_MODE = 0x01;
	public static int INPUT_MODE = 0x02;

	/* db */
	public static final String TEMP_CATEGOTY = "temp";
	public static final String BASE_RECORD = "bookmark_records";
	public static final String PRIMARY_KEY_COLUMN = "_id";
	public static final String URL_COLUMN = "url";
	public static final String NAME_COLUMN = "name";
	public static final String COTEGORY_COLUMN = "category";
	public static final String DATE_COLUMN = "date";

	/* sql */
	public static String INSERT_COLUMN = "INSERT INTO "
			+ BASE_RECORD
			+ " (url, name, category, date) VALUES( '%s', '%s', '%s', datetime());";
	public static String UPDATE_NAME_CATEGORY_COLUMN = "UPDATE " + BASE_RECORD
			+ " SET name='%s', category='%s' WHERE url='%s';";

	/* Serice Command If */
	public static final String CMD_ID = "InputDataServiceCommandId";
	public static final String CMD_VAL = "InputDataServiceCommandValue";
	public static final int CMD_ERROR = 0x00;
	public static final int CMD_SAVE = 0x01;
}
