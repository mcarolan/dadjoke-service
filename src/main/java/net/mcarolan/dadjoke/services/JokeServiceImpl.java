package net.mcarolan.dadjoke.services;

import net.mcarolan.dadjoke.domain.Joke;
import net.mcarolan.dadjoke.ports.in.JokeService;
import net.mcarolan.dadjoke.ports.out.JokeRepository;

import java.util.List;

public class JokeServiceImpl implements JokeService {

    private final JokeRepository jokeRepository;

    public JokeServiceImpl(JokeRepository jokeRepository) {
        this.jokeRepository = jokeRepository;
    }

    @Override
    public List<Joke> listJokes() {
        return this.jokeRepository.listJokes();
    }

    @Override
    public void addJoke(String text) {
        this.jokeRepository.addJoke(text);
    }

    @Override
    public void deleteJoke(int id) {
        this.jokeRepository.deleteJoke(id);
    }

}
