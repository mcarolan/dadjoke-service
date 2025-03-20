package net.mcarolan.dadjoke;


import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactBroker;
import io.javalin.Javalin;
import net.mcarolan.dadjoke.domain.Joke;
import net.mcarolan.dadjoke.infrastructure.http.JavalinJokeController;
import net.mcarolan.dadjoke.services.StubJokeService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@Provider("dadjoke-service")
@PactBroker(url = "https://lasting-neatly-pig.ngrok-free.app")
public class PactTest {

    private static Javalin app;
    private static StubJokeService stubJokeService;

    @BeforeAll
    static void setUp() {
        app = Javalin.create().start();
        stubJokeService = new StubJokeService();
        new JavalinJokeController(stubJokeService).setupRoutes(app);
    }

    @State("there are 2 jokes available")
    void givenThereAre2JokesAvailable() {
        stubJokeService.setCurrentJokeListState(List.of(
                new Joke(4, "Foo"),
                new Joke(7, "Bar")
        ));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void verifyPact(PactVerificationContext context) throws MalformedURLException {
        context.setTarget(HttpTestTarget.fromUrl(URI.create(String.format("http://localhost:%d", PactTest.app.port())).toURL()));
        context.verifyInteraction();
    }

    @AfterAll
    static void tearDown() {
        app.stop();
    }
}
