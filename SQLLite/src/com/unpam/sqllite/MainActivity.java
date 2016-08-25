package com.unpam.sqllite;

import android.app.Activity; 
import android.app.AlertDialog; 
import android.content.DialogInterface; 
import android.content.Intent; 
import android.database.Cursor; 
import android.os.Bundle; 
import android.view.Menu; 
import android.view.View; 
import android.view.View.OnClickListener; 
import android.widget.AdapterView; 
import android.widget.AdapterView.OnItemLongClickListener; 
import android.widget.ListView; 
import android.widget.SimpleCursorAdapter; 
import android.widget.Toast; 
 
import com.unpam.sqllite.DataHelper; 
public class MainActivity extends Activity implements 
OnClickListener, OnItemLongClickListener { 
  ListView listView; 
  SimpleCursorAdapter adapter; 
 
  @SuppressWarnings("deprecation") 
  @Override 
  protected void onCreate(Bundle savedInstanceState) { 
    super.onCreate(savedInstanceState); 
    setContentView(R.layout.activity_main); 
 
    listView = (ListView) findViewById(R.id.listView1); 
    listView. setOnItemLongClickListener(this); 
 
  findViewById(R.id.Tambahbutton).setOnClickListener(this); 
 
  findViewById(R.id.RefreshButton).setOnClickListener(this); 
  DataHelper dh = new DataHelper(this); 
  Cursor c = dh.getAll(); 
  String[] from = new String[] { "judul","isi" }; 
  int[] to = new int[] { android.R.id.text1, 
android.R.id.text2 }; 
  try{ 
    adapter = new SimpleCursorAdapter(this, 
android.R.layout.simple_list_item_2, c, from, to); 
  }catch (Exception ex){} 
listView.setAdapter(adapter); 
} 

@Override 
public boolean onCreateOptionsMenu(Menu menu) { 
  // Inflate the menu; this adds items to the action bar if it is present. 
  getMenuInflater().inflate(R.menu.activity_main, menu); 
  return true; 
} 

@Override 
protected void onResume() { 
  adapter.notifyDataSetChanged(); 
  super.onResume(); 
} 

public void onClick(View v) { 
  // TODO Auto-generated method stub 
  switch (v.getId()) { 
  case R.id.Tambahbutton: 
    startActivity(new Intent(this, 
AddSqlLiteActivity.class)); 
    break; 
  case R.id.RefreshButton: 
    DataHelper dh = new DataHelper(this); 
    Cursor c = dh.getAll(); 
    String[] from = new String[] { "judul","isi" }; 
    int[] to = new int[] { android.R.id.text1, 
android.R.id.text2 }; 
    try{ 
      adapter = new SimpleCursorAdapter(this, 
android.R.layout.simple_list_item_2, c, from, to); 
    }catch (Exception ex){} 
    listView.setAdapter(adapter); 
    break; 
  default: 
    break; 
  } 
} 

public boolean onItemLongClick(AdapterView<?> arg0, View 
arg1, int arg2, 
    long arg3) { 
  // TODO Auto-generated method stub
	final int id = (int) adapter.getItemId(arg2); 
    AlertDialog.Builder builder = new 
AlertDialog.Builder(this); 
      builder.setMessage("Apakah id="+id+" akan dihapus") 
      .setCancelable(true) 
      .setPositiveButton("Ya", new 
DialogInterface.OnClickListener() { 
       
      public void onClick(DialogInterface dialog, int 
which) { 
        // TODO Auto-generated method stub 
        hapusData(id);         
      } 
    }) 
    .setNegativeButton("Tidak", new 
DialogInterface.OnClickListener() { 
       
      public void onClick(DialogInterface dialog, int 
which) { 
        // TODO Auto-generated method stub 
        dialog.cancel(); 
      } 
    }); 
       
      AlertDialog alertDialog = builder.create(); 
      alertDialog.show(); 
    return false; 
  } 
   
  private void hapusData(long id){ 
    DataHelper dh = new DataHelper(this); 
    try{ 
      dh.deleteById((int)id); 
    }catch (Exception ex){ 
      Toast.makeText(this, "Error: "+ex.getMessage(), 
Toast.LENGTH_LONG).show(); 
    } 
  } 
 
} 