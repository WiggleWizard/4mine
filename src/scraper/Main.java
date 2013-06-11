package scraper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import dataobjects.threads.JsonThreads;

public class Main
{
	public static void main (String args[]) 
	{
		String url = "http://api.4chan.org/w/threads.json";
	    
		try 
		{
			URL json = new URL(url);
			
			HttpURLConnection connection = (HttpURLConnection) json.openConnection();
			connection.addRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.36 Safari/537.36");
			connection.connect();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			String jsonString = "";
	        String html;
	        while ((html = in.readLine()) != null)
	        {
	        	jsonString += html;
	        	//System.out.println(html);
	        }
	        
	        in.close();
	        connection.disconnect();
	        
	        // Deserialize JSON
	        Gson gson = new Gson();
	        JsonParser parser = new JsonParser();
	        JsonArray Jarray = parser.parse(jsonString).getAsJsonArray();

	        ArrayList<JsonThreads> lcs = new ArrayList<JsonThreads>();

	        for(JsonElement obj : Jarray )
	        {
	        	String tmp = "{ \"pageObject\": " + obj.toString() + "}";
	        	JsonThreads cse = gson.fromJson( tmp , JsonThreads.class);
	        	System.out.println(cse.pageObject.page);
	            lcs.add(cse);
	        }
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
