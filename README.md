# 4mine (4chan Image Archiver/Scraper)
By Terence-Lee "Zinglish" Davis

## What is 4mine?

4mine is a Java CLI operated application that will automagically scrape the 4chan API looking for images within a search filter of your choice. The images are downloaded to a path which you specify. Downloads are threaded, so multiple image downloads can be made at once (You can specify how many simultaneous downloads you would like at one time).

## What are the requirements for 4mine?

Windows, Linux or Mac (Any OS really) and JRE (Preferably latest JRE from Oracle).

## How do I use 4mine?

Once you have JRE installed you can pass arguments to 4mine to tell it which board you want and where you want images downloaded to.

**Examples** 

This downloads from /w/ board to /tmp/4mine with 4 threads for downloading images. (Unix based OS only)
```
#!text
java -jar 4mine.jar -b w -f "/tmp/4mine/" -t 4
``` 
 

Downloads from the /a/ board to C:\4mine with 7 threads. (Windows only)
```
#!text
java -jar 4mine.jar -b a -f "C:\4mine\" -t 7
```

## Current arguments

```
#!text

-b No Default Board     Board in which to check (Example: w)
-i 5                    The amount of time the board is checked in minutes
-f /tmp                 File path to dump the images to (Can end with trailing slash)
-t 1                    The number of download threads that will run asynchronous downloads
```
