package com.zinglish.j4chan;

import java.util.ArrayList;
import java.util.List;

public class ImageDownloadManager
{
	ImageDownloader[] imageDownloaderObjects;
	Thread[] imageDownloadThreads;
	
	public volatile List<PseudoImage> imageStack = new ArrayList<PseudoImage>();
	
	int threadCount;
	
	public ImageDownloadManager(int threadCount)
	{
		this.threadCount = threadCount;

		this.imageDownloaderObjects = new ImageDownloader[threadCount];
		this.imageDownloadThreads = new Thread[threadCount];
	}

	public void initializeThreads()
	{
		for(int i=0;i<threadCount;i++)
		{
			imageDownloaderObjects[i] = new ImageDownloader(this);
			
			imageDownloadThreads[i] = new Thread(imageDownloaderObjects[i]);
			imageDownloadThreads[i].start();
		}		
	}
	
	public void imageStackManipulation(PseudoImage image, boolean add)
	{
		synchronized(this){
			if(add)
			{
				this.imageStack.add(image);
			}
			else
			{
				this.imageStack.remove(image);
			}
		}
		
	}
}
