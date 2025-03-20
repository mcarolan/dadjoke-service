package net.mcarolan.dadjoke.services;

import net.mcarolan.dadjoke.domain.Joke;
import net.mcarolan.dadjoke.ports.in.JokeService;

import java.util.List;

public class StubJokeService implements JokeService {

    private List<Joke> currentJokeListState = List.of();

    @Override
    public List<Joke> listJokes() {
        return currentJokeListState;
    }

    @Override
    public void addJoke(String text) {

    }

    @Override
    public void deleteJoke(int id) {

    }

    public void setCurrentJokeListState(List<Joke> jokes) {
        this.currentJokeListState = jokes;
    }
}
