package applicationLogic;

import java.util.HashMap;

public class ViewBehaviour {
    private HashMap<Program, Double> programs;

    public ViewBehaviour() {
        this.programs = new HashMap<Program, Double>();
    }

    public HashMap<Program, Double> getPrograms() {
        return programs;
    }

    private void add(Program program, double progressPerct) {
            programs.put(program, progressPerct);
    }

    private void remove(Program program) {
        programs.remove(program);
    }
}
