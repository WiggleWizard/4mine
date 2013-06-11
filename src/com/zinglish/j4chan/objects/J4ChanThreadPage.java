package com.zinglish.j4chan.objects;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class J4ChanThreadPage
{
	// Page number
	@SerializedName("page")
	public int number;
	
	// The threads in this page
	public List<J4ChanThread> threads = new ArrayList<J4ChanThread>();
}
