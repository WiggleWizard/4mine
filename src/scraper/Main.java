package scraper;

import java.lang.Thread;
import java.util.List;

import com.zinglish.j4chan.J4Chan;
import com.zinglish.j4chan.objects.J4ChanBoard;
import com.zinglish.j4chan.objects.J4ChanThread;


public class Main
{
	static String board;
	static int boardCheckInterval = 5;
	static String imageSavePath = "/var/4chan";
	
	public static void main (String args[]) 
	{
		// Assign variables based on the args put in
		for(int i=0;i<args.length;i++)
		{
			// Board
			if(args[i].equals("-b"))
			{
				board = args[i + 1];
			}
			
			// Interval between checking for new board threads
			if(args[i].equals("-i"))
			{
				boardCheckInterval = Integer.valueOf(args[i + 1]);
			}
			
			// The file where all the images will be dumped
			if(args[i].equals("-f"))
			{
				imageSavePath = args[i + 1];
			}
		}
		
		System.out.println("Using board: " + board);
		System.out.println("Checking for new threads every " + boardCheckInterval + " minutes");
		
		// Initialize our static variables used for the loop
		J4Chan j4Chan = new J4Chan();
		
		// Each loop checks the specified board for updates
		while(true)
		{
			List<J4ChanBoard> boardThreads = j4Chan.getBoardThreads(board); // Get the threads that are currently on this board
			
			// For each page
			for(J4ChanBoard boardThread : boardThreads)
			{
				System.out.println("Entering page " + boardThread.page.number);
				// For each thread
				for(J4ChanThread threadPage : boardThread.page.threads)
				{
					System.out.println(threadPage.number);
				}
				
			}
			
			 // Wait until next check interval
			try
			{
				Thread.sleep(boardCheckInterval * 60 * 1000);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
