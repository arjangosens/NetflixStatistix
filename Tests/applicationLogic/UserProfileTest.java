package applicationLogic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserProfileTest {

    // Unit tests for addViewBehaviour()

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
        //Arrange
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

    // Unit tests for deleteViewBehaviour()

    @Test
    void testDeleteViewBehaviourWithSameVariableForViewBehaviour() {
        //Arrange
        UserProfile userProfile = new UserProfile(1, 1, "Iemand", 18);
        ViewBehaviour viewBehaviour = new ViewBehaviour(1, 1, 1, 50);
        userProfile.addViewBehaviour(viewBehaviour);
        //Act
        userProfile.deleteViewBehaviour(viewBehaviour);
        int returnvalue = userProfile.getViewBehaviourArrayList().size();
        //Assert
        Assertions.assertEquals(0, returnvalue);
    }

    @Test
    void testDeleteViewBehaviourWithOtherViewBehaviour() {
        //Arrange
        UserProfile userProfile = new UserProfile(1, 1, "Iemand", 18);
        ViewBehaviour viewBehaviour = new ViewBehaviour(1, 1, 1, 50);
        ViewBehaviour otherViewBehaviour = new ViewBehaviour(2, 1, 2, 75);
        userProfile.addViewBehaviour(viewBehaviour);
        //Act
        userProfile.deleteViewBehaviour(otherViewBehaviour);
        int returnvalue = userProfile.getViewBehaviourArrayList().size();
        //Assert
        Assertions.assertEquals(1, returnvalue);
    }

    @Test
    void testDeleteViewBehaviourWithOtherViewBehaviourWithSameValues() {
        //Arrange
        UserProfile userProfile = new UserProfile(1, 1, "Iemand", 18);
        ViewBehaviour viewBehaviour = new ViewBehaviour(1, 1, 1, 50);
        ViewBehaviour otherViewBehaviour = new ViewBehaviour(1, 1, 1, 50);
        userProfile.addViewBehaviour(viewBehaviour);
        //Act
        userProfile.deleteViewBehaviour(otherViewBehaviour);
        int returnvalue = userProfile.getViewBehaviourArrayList().size();
        //Assert
        Assertions.assertEquals(0, returnvalue);
    }
}