package com.zinglish.j4chan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonInterface
{
	public String userAgent = "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.36 Safari/537.36";
	
	public String getJsonString(String url)
	{
		URL json;
		String jsonString = "";
		
		try
		{
			json = new URL(url);
		
			HttpURLConnection connection = (HttpURLConnection) json.openConnection();
			connection.addRequestProperty("User-Agent", this.userAgent);
			connection.connect();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	
	        String html;
	        while ((html = in.readLine()) != null)
	        {
	        	jsonString += html;
	        }
	        
	        in.close();
	        connection.disconnect();
		}
	    catch(MalformedURLException e)
	    {
	    	e.printStackTrace();
	    }
		catch (IOException e)
		{
			e.printStackTrace();
		}
	    
	    return jsonString;
	}
}
