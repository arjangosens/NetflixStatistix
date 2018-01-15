package applicationLogic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {
    @Test
    void testAddViewBehaviourWithNewViewBehaviour() {
        //Arange
        UserProfile userProfile = new UserProfile(1, 1, "Iemand", 18);
        ViewBehaviour viewBehaviour = new ViewBehaviour(1, 1, 1, 50);
        //Act
        userProfile.addViewBehaviour(viewBehaviour);
        int returnvalue = userProfile.getViewBehaviourArrayList().size();
        //Assert
        Assertions.assertEquals(1, returnvalue);
    }

    @Test
    void testAddViewBehaviourWithExistingViewBehaviour() {
        //Arange
        UserProfile userProfile = new UserProfile(1, 1, "Iemand", 18);
        ViewBehaviour viewBehaviour = new ViewBehaviour(1, 1, 1, 50);
        ViewBehaviour viewBehaviour1 = new ViewBehaviour(1, 1, 1, 50);
        userProfile.addViewBehaviour(viewBehaviour);
        //Act
        userProfile.addViewBehaviour(viewBehaviour1);
        int returnvalue = userProfile.getViewBehaviourArrayList().size();
        //Assert
        Assertions.assertEquals(1, returnvalue);
    }

    @Test
    void testAddViewBehaviourWithReaddingSameViewBehaviour() {
        //Arange
        UserProfile userProfile = new UserProfile(1, 1, "Iemand", 18);
        ViewBehaviour viewBehaviour = new ViewBehaviour(1, 1, 1, 50);
        userProfile.addViewBehaviour(viewBehaviour);
        //Act
        userProfile.addViewBehaviour(viewBehaviour);
        int returnvalue = userProfile.getViewBehaviourArrayList().size();
        //Assert
        Assertions.assertEquals(1, returnvalue);
    }

}