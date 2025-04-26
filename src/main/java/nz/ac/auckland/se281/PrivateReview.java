package nz.ac.auckland.se281;

public class PrivateReview extends Review {
  private String email;
  private Boolean resolved;
  private String resolutionMessage = "-";

  PrivateReview(String[] options, Activity activity) {
    super(activity);
    super.name = options[0];
    this.email = options[1];
    super.rating = Integer.parseInt(options[2]);
    super.comment = options[3];

    if (super.rating < 1) {
      super.rating = 1;
    } else if (this.rating > 5) {
      super.rating = 5;
    }

    if (options[4].equals("y")) {
      this.resolved = true;
    } else {
      this.resolved = false;
    }
  }

  public void resolve(String resolved) {
    this.resolutionMessage = resolved;
    this.resolved = false;
  }

  @Override
  public void printSuccess() {
    MessageCli.REVIEW_ADDED.printMessage(
      "Private", super.reviewId, super.activity.getName());
  }

  @Override
  public void printReview() {
    String rating = Integer.toString(super.rating);

    MessageCli.REVIEW_ENTRY_HEADER.printMessage(rating, "5", "Private", super.reviewId, super.name);
    MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(super.comment);
    // Changes the follow up message to resolved if no resolved requested or if resolved
    if (this.resolved) {
      MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(this.email);
    } else {
      MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(this.resolutionMessage);
    }
  }
}