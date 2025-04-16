package nz.ac.auckland.se281;

public class PublicReview extends Review {
  Boolean anonymous;
  Boolean endorsed = false;

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

  public void endorse() {
    endorsed = true;
  }

  @Override
  public void printSuccess() {
    MessageCli.REVIEW_ADDED.printMessage("Public", 
        super.reviewId, super.activity.getName());
  }

  @Override
  public void printReview() {
    String rating = Integer.toString(super.rating);
    String name = super.name;

    // Mark the displayed name as anonymous if anonymous
    if (this.anonymous) {
      name = "Anonymous";
    }

    MessageCli.REVIEW_ENTRY_HEADER.printMessage(rating, "5", "Public", super.reviewId, name);
    MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(super.comment);
    
    // Print endorsement id endorsed
    if (this.endorsed) {
      MessageCli.REVIEW_ENTRY_ENDORSED.printMessage();
    }
  }
}