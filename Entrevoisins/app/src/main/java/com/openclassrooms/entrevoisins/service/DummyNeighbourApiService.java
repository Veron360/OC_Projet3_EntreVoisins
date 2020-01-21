package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {
    //variable
    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     * retourne neighbour
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     * supprime un neighbour de la liste
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * @return
     * verifie si l'utilisateur est déja favoris ou pas,
     * dans tout les cas retourne neighbour favoris
     */
    @Override
    public List<Neighbour> getFavoritesNeighbour() {
        List<Neighbour> favoritesNeighbours = new ArrayList<>();

        for (Neighbour neighbour1 : neighbours) {
            if (neighbour1.isFavorite()) {
                favoritesNeighbours.add(neighbour1);
            }
        }
        return favoritesNeighbours;
    }

    /**
     * @param neighbour
     * différent de neighbour favoris
     */
    @Override
    public void changeFavorite(Neighbour neighbour) {
        int neighbourIndex = neighbours.indexOf(neighbour);
        neighbours.get(neighbourIndex).setFavorite(!neighbour.isFavorite());
    }

}
