package net.mcarolan.dadjoke.ports.in;

import net.mcarolan.dadjoke.domain.Joke;

import java.util.List;

public interface JokeService {
    List<Joke> listJokes();
    void addJoke(String text);
    void deleteJoke(int id);
}
