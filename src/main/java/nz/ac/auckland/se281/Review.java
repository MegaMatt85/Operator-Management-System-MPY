package nz.ac.auckland.se281;

public abstract class Review {
  protected String name;
  protected int rating;
  protected String comment;

  Review(String name, String[] options) {
    this.name = name;
    this.rating = 4;
    this.comment = "real";
  }
}