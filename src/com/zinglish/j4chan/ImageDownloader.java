package com.zinglish.j4chan;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import scraper.Main;

public class ImageDownloader implements Runnable
{
	public String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.36 Safari/537.36";
	
	private ImageDownloadManager downloadManager;
	
	public ImageDownloader(ImageDownloadManager imageDownloadManager)
	{
		downloadManager = imageDownloadManager;
	}
	
	public void run()
	{
		while(true)
		{
			if(downloadManager.imageStack.size() > 0)
			{
				PseudoImage image = downloadManager.getUnlockedImage();
			
				// Sometimes the stack can be empty and return no images
				if(image != null)
				{
					String imageFilename = image.name + image.extension;
					URL imageURL;
				
					// Read the stream from URL and put it into the file
					try
					{
						imageURL = new URL(image.url);
						HttpURLConnection connection = (HttpURLConnection) imageURL.openConnection();
						connection.addRequestProperty("User-Agent", this.userAgent);
						connection.connect();
						
						InputStream in = connection.getInputStream();
						OutputStream file = new FileOutputStream(Main.imageSavePath + imageFilename);
				
						byte[] buf=new byte[2048];
						int bytes_read;
						
				        while ((bytes_read = in.read(buf)) != -1)
				        {
				        	file.write(buf, 0, bytes_read);
				        }
				        
				        connection.disconnect();
				        file.close();
				        in.close();
				        
				        System.out.println("Downloaded image: " + image.name + image.extension);
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					
					downloadManager.remove(image); // Pop the index off the stack immediately after fetching it
				}
			}
			else // If there is nothing in the DL stack, the thread waits longer for the stack to build
			{
				try{ Thread.sleep(1000); } catch (InterruptedException e){ e.printStackTrace(); }
			}
		}
	}
}
