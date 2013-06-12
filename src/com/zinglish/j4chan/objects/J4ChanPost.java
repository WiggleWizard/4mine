package com.zinglish.j4chan.objects;

import com.google.gson.annotations.SerializedName;

public class J4ChanPost
{
	// Post number
	@SerializedName("no")
	public int number;
	
	// Post content
	@SerializedName("com")
	public String content;
	
	// Image variables
	@SerializedName("tim")
	public long imageID;
	@SerializedName("ext")
	public String imageExtension;
	@SerializedName("filename")
	public String imageName;
	@SerializedName("fsize")
	public int imageSize; // Image file size on disk
	@SerializedName("h")
	public int imageH; // Image height
	@SerializedName("w")
	public int imageW; // Image width
}
