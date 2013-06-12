package com.zinglish.j4chan;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.zinglish.j4chan.objects.J4ChanBoard;
import com.zinglish.j4chan.objects.J4ChanThread;

public class J4Chan
{
	public ArrayList<J4ChanBoard> getBoardThreads(String board)
	{
		String url = "http://api.4chan.org/" + board + "/threads.json";

		JsonInterface jsonInterface = new JsonInterface();

		// Convert the json array into an array of objects
		Gson gson = new Gson();
		JsonParser parser = new JsonParser();
		JsonArray Jarray = parser.parse(jsonInterface.getJsonString(url)).getAsJsonArray();

		ArrayList<J4ChanBoard> chanThreads = new ArrayList<J4ChanBoard>();

		for(JsonElement obj : Jarray )
		{
			String tmp = "{ \"pageObject\": " + obj.toString() + "}"; // Create a fake object
			J4ChanBoard tmpThread = gson.fromJson(tmp , J4ChanBoard.class);

			chanThreads.add(tmpThread);
		}
		
		return chanThreads;
	}
	
	public J4ChanThread getThreadPosts(String board, int threadNumber)
	{
		String url = "http://api.4chan.org/" + board + "/res/" + threadNumber + ".json";

		// Get the json as a long string
		JsonInterface jsonInterface = new JsonInterface();
		String tmp = jsonInterface.getJsonString(url);

		// Convert the json array into an array of objects
		Gson gson = new Gson();
		J4ChanThread tmpThread = gson.fromJson(tmp , J4ChanThread.class);
		
		return tmpThread;
	}
}
