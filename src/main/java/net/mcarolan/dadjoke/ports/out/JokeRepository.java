package net.mcarolan.dadjoke.ports.out;

import net.mcarolan.dadjoke.domain.Joke;

import java.util.List;

public interface JokeRepository {
    List<Joke> listJokes();
    void addJoke(String text);
    void deleteJoke(int id);
}
