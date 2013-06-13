package com.zinglish.j4chan;

import java.util.HashMap;
import java.util.Map;

public class ImageDownloadManager
{
	ImageDownloader[] imageDownloaderObjects;
	Thread[] imageDownloadThreads;
	
	Map<String, PseudoImage> imageStack = new HashMap<String, PseudoImage>();
	
	private int threadCount;
	
	public ImageDownloadManager(int threadCount)
	{
		this.threadCount = threadCount;
		
		imageDownloaderObjects = new ImageDownloader[threadCount];
		imageDownloadThreads = new Thread[threadCount];
	}
	
	/**
	 * Initializes the thread objects, and starts them
	 * 
	 * @return
	 */
	public void initializeThreads()
	{
		for(int i=0;i<threadCount;i++)
		{
			imageDownloaderObjects[i] = new ImageDownloader(); // Create the object
			imageDownloadThreads[i] = new Thread(imageDownloaderObjects[i]); // Create the thread
		}
	}
}
