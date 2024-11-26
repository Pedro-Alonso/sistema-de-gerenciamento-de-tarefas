package Classes.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class SubTask extends Task {
    /*
    * @author Abigail
    * 
    */
    // This is the viable way of implementing subtasks that are different from their parent.
    private ArrayList<String> steps;
    private ArrayList<Boolean> stepsStatus;

    public SubTask (UserTask user, String name, String description, LocalDate deadline, int gravity, int urgency, int trend) {
        super(user, name, description, deadline, gravity, urgency, trend);
        steps = new ArrayList<String>();
        stepsStatus = new ArrayList<Boolean>();
    }

    // Methods

    public void changeStepStatus (int stepIndex) {
        if (stepsStatus.get(stepIndex) == true) {
            stepsStatus.set(stepIndex, false);
        } else {
            stepsStatus.set(stepIndex, true);
        }
    }


    // Getters and Setters
    public ArrayList<String> getSteps() {
        return steps;
    }
    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }
    public ArrayList<Boolean> getStepsStatus() {
        return stepsStatus;
    }
    public void setStepsStatus(ArrayList<Boolean> stepsStatus) {
        this.stepsStatus = stepsStatus;
    }

}
