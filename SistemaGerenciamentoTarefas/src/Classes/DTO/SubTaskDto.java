package Classes.DTO;

import java.util.ArrayList;

public class SubTaskDto extends TaskDto {
    private ArrayList<String> steps;
    private ArrayList<Boolean> stepsStatus;

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
    