package nz.ac.auckland.se281;

public abstract class Review {
  protected String name;
  protected int rating;
  protected String comment;
  protected Activity activity;

  Review(Activity activity) {
    this.activity = activity;
  }

  public String getName() {
    return this.name;
  }

  public int getRating() {
    return this.rating;
  }

  public abstract void printSuccess();
}