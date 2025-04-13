package nz.ac.auckland.se281;

public class PrivateReview extends Review {
  String email;
  Boolean response;

  PrivateReview(String[] options, Activity activity) {
    super(activity);
    super.name = options[0];
    this.email = options[1];
    super.rating = Integer.parseInt(options[2]);
    super.comment = options[3];

    if (super.rating < 0) {
      super.rating = 0;
    } else if (this.rating > 5) {
      super.rating = 5;
    }

    if (options[4].equals("y")) {
      this.response = true;
    } else {
      this.response = false;
    }
  }

  @Override
  public void printSuccess() {
    MessageCli.REVIEW_ADDED.printMessage("Private", 
    super.reviewId, super.activity.getName());
  }
}