package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    /**
     * appel de voisin avec succès
     */
    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedNeighbours.toArray())));
    }

    /**
     * Supprime voisin avec succès
     */
    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    /**
     * Change le statut du voisin favori/non-favori
     */
    @Test
    public void changeFavoriteStatusWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        Neighbour neighbour1 = neighbours.get(0);
        boolean neighbour1BeginFavStatus = neighbour1.isFavorite();
        service.changeFavorite(neighbour1);
        assertThat(neighbour1BeginFavStatus, is(not(service.getNeighbours().get(0).isFavorite())));
    }

    /**
     * Verifie statut du voisin favori avec succès
     */
    @Test
    public void getFavoriteNeighboursWithSuccess() {

        List<Neighbour> neighbours = service.getNeighbours();
        Neighbour neighbour1 = neighbours.get(0);
        service.changeFavorite(neighbour1);
        Neighbour neighbour2 = neighbours.get(1);
        service.changeFavorite(neighbour2);
        List<Neighbour> favoriteNeighbours = service.getFavoritesNeighbour();
        assertThat(favoriteNeighbours.size(), is(2));
        assertTrue(favoriteNeighbours.get(0).isFavorite());
        assertTrue(favoriteNeighbours.get(1).isFavorite());
    }

}