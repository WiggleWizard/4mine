package com.zinglish.j4chan.objects;

import com.google.gson.annotations.SerializedName;

public class J4ChanBoard
{
	// Page object that contains all the page's data including threads
	@SerializedName("pageObject")
	public J4ChanThreadPage page;
}
