package net.mcarolan.dadjoke;

import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;
import net.mcarolan.dadjoke.infrastructure.db.SQLiteJokeRepository;
import net.mcarolan.dadjoke.infrastructure.http.JavalinJokeController;
import net.mcarolan.dadjoke.ports.in.JokeService;
import net.mcarolan.dadjoke.ports.out.JokeRepository;
import net.mcarolan.dadjoke.services.JokeServiceImpl;

/**
 * Hello world!
 */
public class App {
    private static final int PORT = 7000;

    public static void main(String[] args) {
        final String databaseUrl = "jdbc:sqlite:dad_jokes.db";

        final JokeRepository jokeRepository = new SQLiteJokeRepository(databaseUrl);
        final JokeService jokeService = new JokeServiceImpl(jokeRepository);

        final Javalin app = Javalin.create(config -> {
            config.plugins.enableCors(cors -> { cors.add(CorsPluginConfig::anyHost); });
        }).start(PORT);
        new JavalinJokeController(jokeService).setupRoutes(app);

        System.out.println("Up and running on http://localhost:" + PORT);
    }
}
