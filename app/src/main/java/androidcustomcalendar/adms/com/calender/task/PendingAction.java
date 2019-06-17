package androidcustomcalendar.adms.com.calender.task;

import java.util.ArrayList;
import java.util.List;


public class PendingAction {

    private List<Action> mActions = new ArrayList<>();

    public void addAction(Action action){
        mActions.add(action);
    }

    public synchronized void execute(){
        for (Action action : mActions) {
            action.execute();
        }
        mActions.clear();
    }

    public interface Action{
        void execute();
    }
}
