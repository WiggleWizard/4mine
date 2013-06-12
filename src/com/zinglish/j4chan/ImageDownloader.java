package com.zinglish.j4chan;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import scraper.Main;



public class ImageDownloader implements Runnable
{
	public String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.36 Safari/537.36";
	public List<PseudoImage> imageURLStack = new ArrayList<PseudoImage>();
	
	public void run()
	{
		// Loop through the image URLs and download each one to the correct directory
		while(true)
		{
			if(imageURLStack.size() > 0)
			{				
				// Get an image URL 'object' off the stack
				PseudoImage image = imageURLStack.get(0);
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
				
				imageURLStack.remove(0); // Pop the index off the stack
			}
			else // If there is nothing in the DL stack, the thread waits longer for the stack to build
			{
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
