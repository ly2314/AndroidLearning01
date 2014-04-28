package com.ly2314.Test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MessageActivity extends Activity {

	private TextView _textView;
	private static final String FILE_NAME = "text.txt";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		
		_textView = (TextView) findViewById(R.id.textView1);
		String text = getIntent().getStringExtra("text");
		//_textView.setText(text);
		
		//WriteFile(text);
		//WriteFileToExternalStorage(text);
		SaveParseData("text", text);
		//_textView.setText(ReadFile());
	}
	
	private void SaveParseData(String key, String value)
	{
        ParseObject testObject = new ParseObject("Message");
        testObject.put(key, value);
        testObject.saveInBackground(new SaveCallback() {
			
			@Override
			public void done(ParseException arg0) {
				// TODO Auto-generated method stub
				if (arg0 == null)
				{
					ParseDataSaved(true);
				}
				else
				{
					arg0.printStackTrace();
					ParseDataSaved(false);
				}
			}
		});
	}
	
	private void LoadParseData()
	{
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Message");
		query.findInBackground(new FindCallback<ParseObject>()
		{
		    public void done(List<ParseObject> messages, ParseException e)
		    {
		    	String text = "";
		        if (e == null)
		        {
		            for (ParseObject message : messages)
		            {
		            	text += message.getString("text") + "\n";
		            }
		        }
		        else
		        {
		            Log.d("score", "Error: " + e.getMessage());
		        }
		        _textView.setText(text);
		    }
		});
	}
	
	private void ParseDataSaved(Boolean success)
	{
		if (success)
		{
			Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();	
			LoadParseData();
		}
		else
			Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();		
	}
	
	private void WriteFile(String txt)
	{
		try
		{
			txt += "\n";
			
			FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_APPEND);
			fos.write(txt.getBytes());
			fos.flush();
			fos.close();
			
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressLint("NewApi")
	private void WriteFileToExternalStorage(String txt)
	{
		File docDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
		if (docDir.exists() == false)
		{
			docDir.mkdir();
		}
		
		File file = new File(docDir, FILE_NAME);
		
		try
		{
			txt += "\n";
			
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(txt.getBytes());
			fos.flush();
			fos.close();
			
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String ReadFile()
	{
		String str = "";
		try {
			FileInputStream fis = openFileInput(FILE_NAME);
			byte[] _b = new byte[1024];
			while (fis.read(_b) != -1)
			{
				str += new String(_b);
			}
			fis.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
}
