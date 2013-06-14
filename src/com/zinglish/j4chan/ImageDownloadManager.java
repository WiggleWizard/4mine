package com.zinglish.j4chan;

import java.util.HashMap;
import java.util.Map;

public class ImageDownloadManager
{
	ImageDownloader[] imageDownloaderObjects;
	Thread[] imageDownloadThreads;
	
	public Map<String, PseudoImage> imageStack = new HashMap<String, PseudoImage>();
	
	int threadCount;
	
	public ImageDownloadManager(int threadCount)
	{
		this.threadCount = threadCount;
		this.imageDownloaderObjects = new ImageDownloader[threadCount];
		this.imageDownloadThreads = new Thread[threadCount];
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
			imageDownloaderObjects[i] = new ImageDownloader(this);
			
			imageDownloadThreads[i] = new Thread(imageDownloaderObjects[i]);
			imageDownloadThreads[i].start();
		}
	}
}
