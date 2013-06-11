package dataobjects.threads;

import com.google.gson.annotations.SerializedName;

public class Threads
{
	// Number of the post
	@SerializedName("no")
	public int number;
	
	// Time it was posted in Epoch
	@SerializedName("last_modified")
	public int time;
}
