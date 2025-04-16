package nz.ac.auckland.se281;

public abstract class Review {
  protected String name;
  protected int rating;
  protected String comment;
  protected Activity activity;
  protected int reviewNum;
  protected String reviewId;

  Review(Activity activity) {
    this.activity = activity;
    int previousNum = activity.getReviews().size();
    this.reviewNum = previousNum + 1;
    this.reviewId = activity.getActivityId() + "-R" + Integer.toString(this.reviewNum);
  }

  public String getName() {
    return this.name;
  }

  public int getRating() {
    return this.rating;
  }

  public String getReviewId() {
    	return this.reviewId;
  }

  public abstract void printSuccess();

  public abstract void printReview();
}