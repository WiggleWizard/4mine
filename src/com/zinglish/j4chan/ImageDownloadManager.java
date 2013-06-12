package com.zinglish.j4chan;

import java.util.ArrayList;
import java.util.List;

public class ImageDownloadManager
{
	List<ImageDownloader> imageDownloaderObjects = new ArrayList<ImageDownloader>();
	List<Thread> imageDownloadThreads = new ArrayList<Thread>();
	
	private int threadCount;
	private int threadLoadID = 0;
	
	public ImageDownloadManager(int threadCount)
	{
		this.threadCount = threadCount;
		
		
	}
	
	/**
	 * Initializes the thread objects, and starts them
	 * 
	 * @return
	 */
	public void initializeThreads()
	{
		
	}
	
	public void addImage(PseudoImage image)
	{
		
	}
}
