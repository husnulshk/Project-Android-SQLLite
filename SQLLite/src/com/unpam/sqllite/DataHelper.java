package com.unpam.sqllite;


	import android.content.ContentValues; 
	import android.content.Context; 
	import android.database.Cursor; 
	import android.database.sqlite.SQLiteDatabase; 
	import android.database.sqlite.SQLiteOpenHelper; 
	import android.database.sqlite.SQLiteStatement; 
	import android.util.Log; 
	 
	public class DataHelper { 
	  private static final String DATABASE_NAME = "notepad.db"; 
	  private static final int DATABASE_VERSION = 1; 
	  private static final String TABLE_NAME = "notes"; 
	  private Context context; 
	  private SQLiteDatabase db; 
	  private SQLiteStatement insertStmt; 
	  
	  private static final String INSERT = "insert into " + 
	TABLE_NAME + "(judul, isi) values (?,?)"; 
	 
	  public DataHelper(Context context) { 
	    this.context = context; 
	    OpenHelper openHelper = new OpenHelper(this.context); 
	    this.db = openHelper.getWritableDatabase(); 
	    this.insertStmt = this.db.compileStatement(INSERT); 
	  } 
	 
	  private static class OpenHelper extends SQLiteOpenHelper { 
	    OpenHelper(Context context) { 
	      super(context, DATABASE_NAME, null, 
	DATABASE_VERSION); 
	    } 
	 
	    @Override 
	    public void onCreate(SQLiteDatabase db) { 
	      db.execSQL("CREATE TABLE " + TABLE_NAME + "(_id INTEGER PRIMARY KEY, judul TEXT, isi TEXT)"); 
	    } 
	 
	    @Override 
	    public void onUpgrade(SQLiteDatabase db, int 
	oldVersion, int newVersion) { 
	      Log.w("Example", "Upgrading database, this will drop tables and recreate."); 
	      db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); 
	      onCreate(db); 
	    } 
	  } 
	 
	  public long insert(String judul, String isi) { 
	    this.insertStmt.bindString(1, judul); 
	    this.insertStmt.bindString(2, isi); 
	    return this.insertStmt.executeInsert(); 
	  } 
	 
	  public long insert2(String judul, String isi) { 
	    ContentValues cv = new ContentValues(); 
	    cv.put("judul", judul); 
	    cv.put("isi", isi); 
	    return db.insert(TABLE_NAME, null, cv); 
	  } 
	 
	  public Cursor getAll() { 
	    return db.query(TABLE_NAME, null, null, null, null, 
	null, "_id DESC"); 
	  } 
	 
	  public Cursor getById(int id) { 
	    return db.query(TABLE_NAME, null, "_id=" + id, null, 
	null, null, null); 
	  }
	  public void close() { 
		    db.close(); 
		  } 
		 
		  public int deleteById(int id) { 
		    return db.delete(TABLE_NAME, "_id =" + id, null); 
		  } 
		 
		  public int updateById(int id, String judul, String isi) { 
		    ContentValues cv = new ContentValues(); 
		    cv.put("judul", judul); 
		    cv.put("isi", isi); 
		    return db.update(TABLE_NAME, cv, "_id = " + id, null); 
		  } 
	}


