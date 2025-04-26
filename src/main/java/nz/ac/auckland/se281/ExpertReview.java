package nz.ac.auckland.se281;

import java.util.ArrayList;

public class ExpertReview extends Review {
  private Boolean reccomend;
  private ArrayList<String> images = new ArrayList<>();

  ExpertReview(String[] options, Activity activity) {
    super(activity);
    super.name = options[0];
    super.rating = Integer.parseInt(options[1]);
    super.comment = options[2];

    if (super.rating < 1) {
      super.rating = 1;
    } else if (this.rating > 5) {
      super.rating = 5;
    }

    if (options[3].equals("y")) {
      this.reccomend = true;
    } else {
      this.reccomend = false;
    }
  }

  public void addImage(String imageName) {
    this.images.add(imageName);
  }

  @Override
  public void printSuccess() {
    MessageCli.REVIEW_ADDED.printMessage("Expert", 
        super.reviewId, super.activity.getName());
  }

  @Override
  public void printReview() {
    String rating = Integer.toString(super.rating);

    MessageCli.REVIEW_ENTRY_HEADER.printMessage(rating, "5", "Expert", super.reviewId, super.name);
    MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(super.comment);

    // Prints reccomendation if reccomended
    if (this.reccomend) {
      MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
    }

    // Prints the images added to this review if any added
    if (this.images.size() > 0) {
      // Make a single string of all images
      String imageList = this.images.get(0);
      for (int i = 1; i < this.images.size(); i++) {
        imageList += "," + this.images.get(i);
      }
      MessageCli.REVIEW_ENTRY_IMAGES.printMessage(imageList);
    }
  }
}