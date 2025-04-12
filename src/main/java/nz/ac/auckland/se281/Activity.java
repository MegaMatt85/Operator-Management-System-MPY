package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.ActivityType;

public class Activity {

  private String name;
  private String operatorId;
  private String activityId;
  private ActivityType type;
  private Operator operator;

  public Activity(String name, String operatorId, int activityNum, ActivityType type, Operator operator) {
    this.name = name;
    this.operatorId = operatorId;
    this.activityId = createActivityId();
    this.type = type;
    this.operator = operator;
  }

  public String getName() {
    return this.name;
  }

  public String getActivityId() {
    return this.activityId;
  }

  public String getOperatorId() {
    return this.operatorId;
  }

  public ActivityType getActivityType() {
    return this.type;
  }

  public Operator getOperator() {
    return this.operator;
  }

  public String createActivityId() { // to be changed
    return this.operatorId + "-001";
  }

  public void printActivity() {
    System.out.println("* " + name + ": [" + activityId + "/" + type + "] offered by " + operator.getName());
  }
}