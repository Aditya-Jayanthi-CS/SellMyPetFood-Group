/*
 * problem 2.3.1 sell my pet food
 */
public class TargetedAd {

  public static void main(String[] args)
  {
    // setting up the collector to read our files
    DataCollector collector = new DataCollector();
    collector.setData("socialMediaPosts.txt", "targetWords.txt");

    String targetedUsers = ""; // this will hold our long list of winners
    String post = collector.getNextPost();
    
    // logic 1: the big loop
    // we keep going until the collector says "none" (end of file)
    while (!post.equals("NONE")) 
    {
      // find where the username ends (the first space)
      int firstSpace = post.indexOf(" ");
      String username = post.substring(0, firstSpace);
      
      // make it lowercase so "Monkey" and "monkey" both count
      String lowerPost = post.toLowerCase();
      
      boolean found = false; // flag so we don't add the same person twice for one post
      String targetWord = collector.getNextTargetWord();

      // nested loop: check the post against every single target word
      while (!targetWord.equals("NONE")) 
      {
        String lowerTarget = targetWord.toLowerCase();

        // if the keyword is anywhere in the post, indexof will be 0 or higher
        if (lowerPost.indexOf(lowerTarget) >= 0) 
        {
          if (found == false) {
            targetedUsers += username + " "; // add 'em to the list
            found = true; 
          }
        }
        
        targetWord = collector.getNextTargetWord(); // check the next word
      }
      
      post = collector.getNextPost(); // move to the next post
    }

    // the ad we are sending out
    String adContent = "whatssup!!! check out our new premium monkey treats! hint: there are special discount inside. SHHH take it while you can!";
    
    // logic 2: generate the final file
    collector.prepareAdvertisement("prepareAdvertisement.txt", targetedUsers, adContent);

    // console stuff so we know it worked
    System.out.println("targeted advertisement file is created");
    System.out.println("------------------------------");
    System.out.println("targeted users: " + targetedUsers);
  }
}
