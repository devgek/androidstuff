package com.example.app1.home;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.app1.R;

public class CardListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.cardlist);

//		Card card1 = new Card(this);
//		CardHeader cardHeader1 = new CardHeader(this);
//		cardHeader1.setTitle("Karte 1");
//		card1.addCardHeader(cardHeader1);
//		CardThumbnail cardThumbnail1 = new CardThumbnail(this);
//		cardThumbnail1.setDrawableResource(R.drawable.ic_launcher);
//		card1.addCardThumbnail(cardThumbnail1);
//		
//		MyCard card2 = new MyCard(this, R.layout.my_card_layout);
//		card2.setTitle(getString(R.string.firstName) + " " + getString(R.string.lastName));
//		CardHeader cardHeader2 = new CardHeader(this);
//		cardHeader2.setTitle("Karte 2");
//		card2.addCardHeader(cardHeader2);
//		
//		ArrayList<Card> cards = new ArrayList<Card>();
//		cards.add(card1);
//		cards.add(card2);
//		
//		CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(this, cards);
		
		ListView listView = (ListView) findViewById(R.id.lvCardList);
//        listView.setAdapter(mCardArrayAdapter);
	}

}
