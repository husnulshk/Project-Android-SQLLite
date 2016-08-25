package com.unpam.sqllite;

import android.app.Activity; 
import android.database.Cursor; 
import android.os.Bundle; 
import android.view.Menu; 
import android.widget.EditText; 
import android.widget.Toast; 
 
import com.unpam.sqllite.DataHelper; 
 
public class AddSqlLiteActivity extends Activity { 
	DataHelper dh; 
	  EditText judul, isi; 
	  int id=0; 
	 
	  @Override 
	  protected void onCreate(Bundle savedInstanceState) { 
	    super.onCreate(savedInstanceState); 
	    setContentView(R.layout.activity_add_sql_lite); 
	     
	    judul = (EditText) findViewById(R.id.Judul); 
	    isi = (EditText) findViewById(R.id.isi); 
	 
	    dh = new DataHelper(this); 
	 
	    if (getIntent().getExtras() != null) { 
	      id = getIntent().getIntExtra("_id", 0); 
	      Cursor c = dh.getById(id); 
	      if (c.moveToFirst()) { // pasti hanya satu karena _id unik 
	        do { 
	          String strJudul = 
	c.getString(c.getColumnIndex("judul")); 
	          String strIsi = 
	c.getString(c.getColumnIndex("isi")); 
	          // TODO isi field 
	        } while (c.moveToNext()); 
	      } 
	 
	    } 
	  } 
	 
	  @Override 
	  public boolean onCreateOptionsMenu(Menu menu) { 
	    // Inflate the menu; this adds items to the action bar if it is present. 
	    getMenuInflater().inflate(R.menu.activity_add_sql_lite, 
	menu); 
	    return true; 
	  } 
	 
	  @Override 
	  protected void onPause() { 
	    if (!judul.getText().toString().equals("")) { 
	      if (id > 0) { 
	        // TODO update ke db 
	      } else { 
	        id = (int) 
	dh.insert2(judul.getText().toString(), isi.getText().toString()); 
	        Toast.makeText(this,"Disimpan: " + 
	Integer.toString(id) + ": " + judul.getText(), 
	Toast.LENGTH_SHORT).show(); 
	      } 
	    } 
	    super.onPause(); 
	  } 
	
}