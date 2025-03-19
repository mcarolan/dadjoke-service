package net.mcarolan.dadjoke.infrastructure.http;

import io.javalin.Javalin;
import net.mcarolan.dadjoke.domain.Joke;
import net.mcarolan.dadjoke.infrastructure.http.domain.AddJokeDTO;
import net.mcarolan.dadjoke.infrastructure.http.domain.AddJokeResponseDTO;
import net.mcarolan.dadjoke.infrastructure.http.domain.JokeDTO;
import net.mcarolan.dadjoke.infrastructure.http.domain.JokeListDTO;
import net.mcarolan.dadjoke.ports.in.JokeService;
import org.eclipse.jetty.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class JavalinJokeController {
    private final JokeService jokeService;

    public JavalinJokeController(JokeService jokeService) {
        this.jokeService = jokeService;
    }

    public void setupRoutes(Javalin app) {
        app.get("/jokes", ctx -> {
           final List<JokeDTO> jokes = new ArrayList<>();
           for (final Joke joke : jokeService.listJokes()) {
               jokes.add(new JokeDTO(joke.id(), joke.joke()));
           }
           ctx.json(new JokeListDTO(jokes), JokeListDTO.class);
        });

        app.post("/jokes", ctx -> {
            AddJokeDTO addJokeDTO = ctx.bodyAsClass(AddJokeDTO.class);
            ctx.status(HttpStatus.CREATED_201).json(new AddJokeResponseDTO(true));
        });
    }
}
