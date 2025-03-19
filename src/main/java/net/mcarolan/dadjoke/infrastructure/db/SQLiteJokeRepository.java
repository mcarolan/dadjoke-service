package net.mcarolan.dadjoke.infrastructure.db;

import net.mcarolan.dadjoke.domain.Joke;
import net.mcarolan.dadjoke.ports.out.JokeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteJokeRepository implements JokeRepository {

    private final String databaseUrl;

    public SQLiteJokeRepository(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    private Connection connect() {
        try {
            return DriverManager.getConnection(this.databaseUrl);
        } catch (SQLException e) {
            throw new RuntimeException("Connection failed!", e);
        }
    }

    @Override
    public List<Joke> listJokes() {
        List<Joke> jokes = new ArrayList<>();

        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            String query = "SELECT * FROM dad_jokes";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Joke joke = new Joke(rs.getInt("id"), rs.getString("joke"));
                jokes.add(joke);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return jokes;
    }

    @Override
    public void addJoke(String text) {
        String query = "INSERT INTO dad_jokes (joke) VALUES (?)";

        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, text);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteJoke(int id) {
        String query = "DELETE FROM dad_jokes WHERE id = ?";

        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
