import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
     Restaurant restaurant;
     //REFACTORED ALL THE REPEATED LINES OF CODE Below
    public void restaurantDetails() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",100);
        restaurant.addToMenu("Vegetable lasagne",269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        restaurantDetails();
        restaurant.setClosingTime(LocalTime.now().plusMinutes(10));
        assertTrue(restaurant.isRestaurantOpen());
        //LocalTime mockCurrentTime= LocalTime.parse("17:30:00");
        //Restaurant mockRestaurant=Mockito.spy(restaurant);
       // Mockito.when(mockRestaurant.getCurrentTime().plusMinutes(10));
        //assertEquals(true,mockRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        restaurantDetails();
        restaurant.setClosingTime(LocalTime.now().minusMinutes(10));
        assertFalse(restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<order total value >>>>>>>>>>>>

    @Test
    public void order_value_should_give_sum_total_of_items_selected(){
        restaurantDetails();
        List<Item> menu = restaurant.getMenu();
        assertEquals(529,restaurant.getOrderValue(menu));
    }

    @Test
    public void order_value_should_reduce_sum_total_of_item_when_an_item_removed(){
        restaurantDetails();
        List<Item> menu = restaurant.getMenu();
        int actualTotal = restaurant.getOrderValue(menu);
        int afterTotal = menu.get(1).getPrice();
        menu.remove(1);
        assertEquals(actualTotal-afterTotal,restaurant.getOrderValue(menu));
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        restaurantDetails();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }



    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurantDetails();
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        restaurantDetails();
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("chicken Burger"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}