package dataobjects.threads;

public class JsonThreads
{
	public Pages pageObject;
	
	public String toString()
	{
		String tmp = "";
		
		tmp += "Page: " + pageObject.page;
		
		return tmp;
	}
}
