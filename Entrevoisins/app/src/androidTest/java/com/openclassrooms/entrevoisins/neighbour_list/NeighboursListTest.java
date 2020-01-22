
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;

import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;

import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;
    private static int ITEMS_COUNT2 = 0;

    private ListNeighbourActivity mActivity;
    private List<Neighbour> Neighbours;
    private NeighbourApiService ApiService;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        ApiService = DI.getNewInstanceApiService();
        Neighbours = ApiService.getNeighbours();

    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // TODO
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
    }

    /**
     * test vérifiant que lorsqu’on clique sur un élément de la liste, l’écran de
     * détails est bien lancé
     */
    @Test
    public void myNeighboursList_DetailView() {
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(ViewMatchers.withId(R.id.neighbours_image_profile)).check(matches(isDisplayed()));
    }

    /**
     * Test vérifiant qu’au démarrage de ce nouvel écran, le TextView indiquant
     * le nom de l’utilisateur en question est bien rempli
     */
    @Test
    public void myNeighbours_TextViewName(){
        String neighbours = Neighbours.get(0).getName();
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(ViewMatchers.withId(R.id.neighbours_name_Profile)).check(matches(withText(neighbours)));
    }

    /**
     * test vérifiant que l’onglet Favoris n’affiche que les voisins marqués comme favoris
     */
    @Test
    public void myNeighbours_GetFavorite(){
        String neighbours = Neighbours.get(0).getName();
        onView(withText(R.string.tab_favorites_title)).perform(click());
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT2));
        onView(withText(R.string.tab_neighbour_title)).perform(click());
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(ViewMatchers.withId(R.id.button_favorites)).perform(click());
        Espresso.pressBack();
        onView(withText(R.string.tab_favorites_title)).perform(click());
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT2+1));
        onView(allOf(ViewMatchers.withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(ViewMatchers.withId(R.id.neighbours_name_Profile)).check(matches(withText(neighbours)));
    }

}