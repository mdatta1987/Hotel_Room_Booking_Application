import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void beforeEach() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = Mockito.spy(new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime));
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToSelectedItemList("Sweet corn soup");
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime openTime = LocalTime.parse("10:30:00").plusHours(5);
        Mockito.when(restaurant.getCurrentTime()).thenReturn(openTime);
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime closedTime = LocalTime.parse("22:00:00").plusHours(5);
        Mockito.when(restaurant.getCurrentTime()).thenReturn(closedTime);
        assertFalse(restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<TDD>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void one_item_is_added_to_the_list_of_selected_items() {
        assertFalse(restaurant.getSelectedItemList().isEmpty());
    }

    @Test
    public void size_of_the_list_of_selected_items_should_be_equal_to_the_number_of_items_added() {
        assertEquals(1, restaurant.getSelectedItemList().size());
    }

    @Test
    public void total_cost_should_be_displayed() {
        restaurant.addToMenu("Sizzling brownie",319);
        restaurant.addToSelectedItemList("Vegetable lasagne");
        restaurant.addToSelectedItemList("Sizzling brownie");
        assertEquals(707, restaurant.getTotal(restaurant.getSelectedItemList()));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<TDD>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}

