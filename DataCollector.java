/*
 * problem 2.3.1 sell my pet food
 */
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.io.*;

public class DataCollector
{
  // setting up our lists to hold the posts and the keywords
  private ArrayList<String> socialMediaPosts;
  private ArrayList<String> targetWords;
  private Scanner sc;
  private int currentPost;
  private int currentTargetWord;

  public DataCollector()
  {
    // initializing everything so we don't get null pointer errors
    socialMediaPosts = new ArrayList<String>();
    targetWords = new ArrayList<String>();
    currentPost = 0;
    currentTargetWord = 0;
  }

  /**
   * this method grabs the data from the .txt files and shoves them into our arraylists.
   * we use a try/catch block here because java gets nervous about files not existing.
   */
  public void setData(String socialMediaPostsFilename, String targetWordsFilename) {
    try
    {
      // reading the social media posts first
      sc = new Scanner(new File(socialMediaPostsFilename));
      while (sc.hasNextLine())
      {
        // .trim() is nice because it gets rid of any annoying accidental spaces
        String line = sc.nextLine().trim();
        if (line.length() > 0)
          socialMediaPosts.add(line);
      }
      
      // now reading the keywords we want to hunt for
      sc = new Scanner(new File(targetWordsFilename));
      while (sc.hasNextLine())
      {
        String word = sc.nextLine().trim();
        if (word.length() > 0)
          targetWords.add(word);
      }
    }
    catch (Exception e)
    {
      // if the files are missing, this tells us what went wrong
      System.out.println("something went wrong with the files: " + e);
    }
  }

  /**
   * this pulls the next post from the list. 
   * we return "none" if we've reached the end so our while loop knows when to quit.
   */
  public String getNextPost()
  {
    if (currentPost < socialMediaPosts.size())
    {
      this.currentPost++;
      return socialMediaPosts.get(currentPost - 1);
    }
    else
    {
      return "NONE";
    }
  }

  /**
   * same thing as getnextpost, but for the keywords.
   * once it hits the end, it resets to 0 so we can check the next post against the same words.
   */
  public String getNextTargetWord()
  {
    if (currentTargetWord < targetWords.size())
    {
      this.currentTargetWord++;
      return targetWords.get(currentTargetWord - 1);
    }
    else
    {
      this.currentTargetWord = 0;
      return "NONE";
    }
  }

  /**
   * logic 2: file generation algorithm
   * this is the cool part where we actually make the prepareadvertisement.txt file.
   */
  public void prepareAdvertisement(String filename, String usernames, String advertisement)
  {
    try
    {
      // filewriter is what actually lets java create a new file on the computer
      FileWriter fw = new FileWriter(filename);
      
      // algorithm deep dive:
      // we take our massive string of usernames and use .split(" ") to turn it into an array.
      // the "for-each" loop lets us walk through that array easily.
      for (String un : usernames.split(" "))
      {
          // we add the @ symbol to tag them and \n to make sure every ad is on a new line
          fw.write("@" + un + " " + advertisement +"\n");
      }
      // closing the file is super important or it might not save properly
      fw.close();
    }
    catch (IOException e)
    {
        System.out.println("couldn't write the file. rip: " + e);
    }
  }

  /**
   * just a helper method if we ever need to dump all posts to the console
   */
  public void printAllPosts()
  {
    for (String post : this.socialMediaPosts)
      System.out.println(post);
  }

  /**
   * same helper but for the keywords
   */
  public void printAllTargetWords()
  {
    for (String word : this.targetWords)
      System.out.println(word);
  }
}
