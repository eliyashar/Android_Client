package com.android.PcTivo;

import java.util.ArrayList;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;

public class MovieListActivity extends ListActivity {
	private ListView mMovieList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mMovieList = (ListView) findViewById(R.id.movie_list);
		ArrayList moviesList = new ArrayList<String>();
//		mMovieList.setAdapter(new MovieListAdapter(this, moviesList, 0,	null, null, null));
	}
}
