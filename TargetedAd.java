/*
 * Problem 2.3.1 Sell My Pet Food
 */
public class TargetedAd {

  public static void main(String[] args)
  {
    DataCollector collector = new DataCollector();
    collector.setData("socialMediaPosts.txt", "targetWords.txt");

    String targetedUsers = "";
    String post = collector.getNextPost();
    
    while (!post.equals("NONE")) 
    {
      int firstSpace = post.indexOf(" ");
      String username = post.substring(0, firstSpace);
      String lowerPost = post.toLowerCase();
      
      boolean found = false;
      String targetWord = collector.getNextTargetWord();

      while (!targetWord.equals("NONE")) 
      {
        String lowerTarget = targetWord.toLowerCase();

        if (lowerPost.indexOf(lowerTarget) >= 0) 
        {
          if (found == false) {
            targetedUsers += username + " ";
            found = true; 
          }
        }
        
        targetWord = collector.getNextTargetWord();
      }
      
      post = collector.getNextPost();
    }

    String adContent = "whatssup!!! check out our new premium monkey treats! hint: there are special discount inside. SHHH take it while you can!";
    collector.prepareAdvertisement("prepareAdvertisement.txt", targetedUsers, adContent);

    //just for us to know that we actually created it
    System.out.println("targeted advertisement file is created");
    System.out.println("------------------------------");
    System.out.println("targeted users: " + targetedUsers);
    System.out.println("------------------------------");
    System.out.println("advertisement content: " + adContent);
    System.out.println("------------------------------");
  }
}
    /*  
     * TODO:
     * PREPARATION WORK
     * (1) Create a file called targetWords.txt. Populate this file with words on each line that
     *     you think would determine if a user is a dog or cat owner.
     * 
     * PROGRAMMING
     * (2) Create a new DataCollector object and set the data to "socialMediaPostsSmall.txt" and "targetWords.txt"
     *     Important: Use the socialMedialPostsSmall to create your algorithm. Using a small file will help you 
     *     generate your solution quicker and give you the ability to double check your work.
     * (3) Create a String variable to hold the names of all the user. (The first word of every post is 
     *     a person's username)
     * (4) Compare each user's post to each target word. If a user mentions a target word, add their username to 
     *     the String of users. Separate usernames with a space. 
     *         Hint: You can use loops to look through each word. 
     *         Hint2: You can use indexOf to check if a word is in a user post. 
     * (5) Once you have all the users, use your DataCollector's prepareAdvertisement method to prepare a file 
     *     with all users and the advertisement you will send them.
     *         Additional Info: The prepareAdvertisement creates a new file on your computer. Check the posts of
     *         some of the usernames to make sure your algorithm worked.
     * 
     * THE FINAL SOLUTION
     * (6) Your solution should work with the socialMedialPostsSmall.txt. Modify your DataCollector initialization
     *    so you use the socialMediaPosts.txt. You should now have a larger file of users to target.
     */


    /* your code here */
    
  
