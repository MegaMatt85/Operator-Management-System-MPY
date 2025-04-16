package nz.ac.auckland.se281;

public class PrivateReview extends Review {
  String email;
  Boolean response;
  String resolutionMessage = "-";

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

  public void resolve(String response) {
    this.resolutionMessage = response;
    this.response = false;
  }

  @Override
  public void printSuccess() {
    MessageCli.REVIEW_ADDED.printMessage("Private", 
    super.reviewId, super.activity.getName());
  }

  @Override
  public void printReview() {
    String rating = Integer.toString(super.rating);
    //String followUp = "Need to email '" + this.email + "' for follow-up.";

    // Changes the follow up message to resolved if no response requested
    // if (!this.response) {
    //   followUp = "Resolved: \"-\""; // to be changed?
    // }
    MessageCli.REVIEW_ENTRY_HEADER.printMessage(rating, "5", "Private", super.reviewId, super.name);
    MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(super.comment);
    if (this.response) {
      MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(this.email);
    } else {
      MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(this.resolutionMessage);
    }
    
    // System.out.println("* [" + rating + "/5] Private review (" + super.reviewId 
    //   + ") by '" + super.name + "'");
    // System.out.println("\"" + super.comment + "\"");
    // System.out.println(followUp);

  }
}