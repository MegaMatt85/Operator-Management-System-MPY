package nz.ac.auckland.se281;

public class PublicReview extends Review{
  Boolean anonymous = false;

  PublicReview(String name, String[] options) {
    super(name, options);
    this.anonymous = false;
  }
}