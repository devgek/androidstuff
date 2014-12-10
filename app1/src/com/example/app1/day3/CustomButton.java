package com.example.app1.day3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.Button;

public class CustomButton extends Button {

	public CustomButton(Context context) {
		super(context);
	}

	public CustomButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		Paint p = new Paint();
		p.setColor(Color.RED);
		int x = getMeasuredWidth() / 2;
		int y = getMeasuredHeight() / 2;
		canvas.drawCircle(x, y, 35, p);
		
		canvas.save();
		super.onDraw(canvas);
		canvas.restore();
	}

}
