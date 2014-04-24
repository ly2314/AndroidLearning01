package com.ly2314.Test1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends Activity {

	private TextView _textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		
		_textView = (TextView) findViewById(R.id.textView1);
		String text = getIntent().getStringExtra("text");
		_textView.setText(text);
	}
}
