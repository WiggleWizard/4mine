package com.zinglish.j4chan;

public class PseudoImage
{
	public String name;
	public String extension;
	public String url;
	public int expectedSize;
	
	public PseudoImage(String name, String extension, String url, int expectedSize)
	{
		this.name = name;
		this.extension = extension;
		this.expectedSize = expectedSize;
		this.url = url;
	}
}
