package nz.ac.auckland.se281;

public class ExpertReview extends Review {
  Boolean reccomend;

  ExpertReview(String[] options, Activity activity) {
    super(activity);
    super.name = options[0];
    super.rating = Integer.parseInt(options[1]);
    super.comment = options[2];

    if (super.rating < 0) {
      super.rating = 0;
    } else if (this.rating > 5) {
      super.rating = 5;
    }

    if (options[3].equals("y")) {
      this.reccomend = true;
    } else {
      this.reccomend = false;
    }
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

    if (this.reccomend) {
      MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
    }
  }
}