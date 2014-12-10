package com.example.app1.home;

import it.gmariotti.cardslib.library.internal.Card;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app1.R;

public class MyCard extends Card {

	public MyCard(Context context) {
		super(context);
	}

	public MyCard(Context context, int innerLayout) {
		super(context, innerLayout);
	}

	@Override
	public void setupInnerViewElements(ViewGroup parent, View view) {
        if (view != null) {
            TextView mTitleView = (TextView) view.findViewById(R.id.myCardInnerText);
            if (mTitleView != null)
                mTitleView.setText(mTitle);
        }
	}

}
