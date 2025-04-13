package nz.ac.auckland.se281;

public class PublicReview extends Review {
  Boolean anonymous;

  PublicReview(String[] options, Activity activity) {
    super(activity);
    super.name = options[0];
    super.rating = Integer.parseInt(options[2]);
    super.comment = options[3];
    
    if (options[1].equals("y")) {
      this.anonymous = true;
    } else {
      this.anonymous = false;
    }

    if (super.rating < 0) {
      super.rating = 0;
    } else if (this.rating > 5) {
      super.rating = 5;
    }
  }

  @Override
  public void printSuccess() {
    MessageCli.REVIEW_ADDED.printMessage("Public", 
        Integer.toString(super.rating), super.activity.getName());
  }
}