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
	
	public synchronized PseudoImage getUnlockedImage()
	{
		PseudoImage tmp = null;
		
		for(PseudoImage image : this.imageStack)
		{					
			if(image == null)
			{
				continue;
			}
			else if(!image.locked)
			{
				tmp = image;
				image.locked = true;
				
				break;
			}
		}
		
		return tmp;
	}
	
	public synchronized void add(PseudoImage image)
	{
		imageStack.add(image);
	}
	
	public synchronized void remove(PseudoImage image)
	{
		imageStack.remove(image);
	}
}
