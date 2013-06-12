package com.zinglish.j4chan.objects;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class J4ChanBoardThread
{
	// Number of the post
	@SerializedName("no")
	public int number;
	
	// Time it was posted in Epoch
	@SerializedName("last_modified")
	public int time;
}
