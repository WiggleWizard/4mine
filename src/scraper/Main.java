package scraper;

import java.io.File;
import java.lang.Thread;
import java.util.List;

import com.zinglish.j4chan.ImageDownloader;
import com.zinglish.j4chan.J4Chan;
import com.zinglish.j4chan.PseudoImage;
import com.zinglish.j4chan.objects.J4ChanBoard;
import com.zinglish.j4chan.objects.J4ChanBoardThread;
import com.zinglish.j4chan.objects.J4ChanPost;

public class Main
{
	static String board;
	static int boardCheckInterval = 5;
	static public String imageSavePath = "/tmp/";
	
	static public int searchMinHeight = 0;
	static public int searchMinWidth = 0;
	static public String searchContent;
	
	static int downloadThreads = 1;
	static ImageDownloader imageDownloader;
	static Thread imageDLThread;
	
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
				// Clean up the path to append or keep the trailing slash
				imageSavePath = args[i + 1];
				
				char tmp = imageSavePath.charAt(imageSavePath.length() - 1);
				if(tmp != '/')
				{
					imageSavePath += "/";
				}
				
				// Check if the path exists and is a legit directory
				File pathCheck = new File(imageSavePath);
				if(!pathCheck.exists() || !pathCheck.isDirectory())
				{
					System.out.println("Path does not exist: " + imageSavePath);
					System.exit(0);
				}
			}
			
			// Total number of download threads this program will create
			if(args[i].equals("-t"))
			{
				
			}
		}
		
		System.out.println("4Chan Abuser (c) Terence-Lee 'Zinglish' Davis");
		System.out.println("Abusing board: /" + board + "/");
		System.out.println("Dumping all images in: " + imageSavePath);
		System.out.println("Checking for new board threads every " + boardCheckInterval + " minutes");
		System.out.println("Download threads: " + downloadThreads);
		
		// Thread manager and thread initialisation
		imageDownloader = new ImageDownloader();
		imageDLThread = new Thread(imageDownloader);
		imageDLThread.start();
		
		// Initialise our static variables used for the loop
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
				for(J4ChanBoardThread threadPage : boardThread.page.threads)
				{
					System.out.println("Getting posts and images from thread ID: " + threadPage.number);
					
					// Get all the posts for each thread
					List<J4ChanPost> j4ChanPosts = j4Chan.getThreadPosts(board, threadPage.number).posts;
					for(J4ChanPost j4ChanPost : j4ChanPosts)
					{
						if(j4ChanPost.imageID > 0)
						{
							System.out.println("Image found: http://images.4chan.org/" + board + "/src/" + j4ChanPost.imageID + j4ChanPost.imageExtension);
							imageDownloader.imageURLStack.add(new PseudoImage(
									j4ChanPost.imageName, 
									j4ChanPost.imageExtension,
									"http://images.4chan.org/" + board + "/src/" + j4ChanPost.imageID + j4ChanPost.imageExtension,
									j4ChanPost.imageSize
								)
							);
						}
					}
					
					// Sleep for 1 second, giving the 4chan API a breather
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
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
