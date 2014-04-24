package com.ly2314.Test1;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

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
		_textView.setText(text);
		
		WriteFile(text);
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
}
