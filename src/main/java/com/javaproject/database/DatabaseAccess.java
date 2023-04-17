package com.javaproject.database;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import com.javaproject.beans.BoardGame;
import com.javaproject.beans.Review;

import lombok.AllArgsConstructor;
import lombok.Data;

@Repository
// @AllArgsConstructor
public class DatabaseAccess {

    // autowired using AllArgsConstructor
    @Autowired
    private NamedParameterJdbcTemplate jdbc;

    public List<String> getAuthorities() {

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "SELECT DISTINCT authority FROM authorities";

        List<String> authorities = jdbc.queryForList(query, namedParameters, String.class);

        return authorities;
    }

    public List<BoardGame> getBoardGames() {

        String query = "SELECT * FROM boardgames";

        BeanPropertyRowMapper boardgameMapper = new BeanPropertyRowMapper<>(BoardGame.class);

        List<BoardGame> boardgames = jdbc.query(query, boardgameMapper);
        return boardgames;
    }

    public BoardGame getBoardGame(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "SELECT * FROM boardgames WHERE id = :id";
        namedParameters.addValue("id", id);
        BeanPropertyRowMapper boardgameMapper = new BeanPropertyRowMapper<>(BoardGame.class);
        List<BoardGame> boardgames = jdbc.query(query, namedParameters, boardgameMapper);
        if (boardgames.isEmpty()) {
            return null;
        } else {
            return boardgames.get(0);
        }
    }

    public List<Review> getReviews(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "SELECT * FROM reviews WHERE gameId = :id";
        namedParameters.addValue("id", id);
        BeanPropertyRowMapper reviewMapper = new BeanPropertyRowMapper<>(Review.class);
        List<Review> reviews = jdbc.query(query, namedParameters, reviewMapper);
        if (reviews.isEmpty()) {
            return null;
        } else {
            return reviews;
        }
    }

    // public boolean userNameNotExist(String userName) {
    // MapSqlParameterSource namedParameters = new MapSqlParameterSource();

    // String query = "SELECT * FROM users WHERE username = :username";
    // namedParameters.addValue("username", userName);

    // BeanPropertyRowMapper userMapper = new BeanPropertyRowMapper<>(User.class);
    // List<User> users = jdbc.query(query, namedParameters, userMapper);
    // // List<User> users = jdbc.query(query, namedParameters, userMapper);
    // if (users.isEmpty()) {
    // return true;
    // } else {
    // return false;
    // }
    // }

    public int addBoardGame(BoardGame boardgame) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO boardgames (name, level, minPlayers, maxPlayers) VALUES (:name, :level, :minPlayers, :maxPlayers)";
        namedParameters
                .addValue("name", boardgame.getName())
                .addValue("level", boardgame.getLevel())
                .addValue("minPlayers", boardgame.getMinPlayers())
                .addValue("maxPlayers", boardgame.getMaxPlayers());

        return jdbc.update(query, namedParameters);
    }

    public int addReview(Review review) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "INSERT INTO reviews (gameId, text) VALUES (:gameId, :text)";
        namedParameters.addValue("gameId", review.getGameId())
                .addValue("text", review.getText());

        return jdbc.update(query, namedParameters);
    }

    public int deleteReview(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String query = "DELETE FROM reviews WHERE id = :id";
        namedParameters.addValue("id", id);
        return jdbc.update(query, namedParameters);
    }

    public Review getReview(Long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "SELECT * FROM reviews WHERE id = :id";
        namedParameters.addValue("id", id);
        BeanPropertyRowMapper reviewMapper = new BeanPropertyRowMapper<>(Review.class);
        List<Review> reviews = jdbc.query(query, namedParameters, reviewMapper);
        if (reviews.isEmpty()) {
            return null;
        } else {
            return reviews.get(0);
        }
    }

    // public boolean reviewExists(Long id) {
    // MapSqlParameterSource namedParameters = new MapSqlParameterSource();

    // String query = "SELECT * FROM reviews WHERE id = :id";
    // namedParameters.addValue("id", id);

    // }

    public int editReview(Review review) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String query = "UPDATE reviews SET text = :text "
                + "WHERE id = :id";

        namedParameters
                .addValue("text", review.getText())
                .addValue("id", review.getId());
        return jdbc.update(query, namedParameters);
    }

    // public int addStudent(Student student) {
    // MapSqlParameterSource namedParameters = new MapSqlParameterSource();

    // String query = "INSERT INTO students (username) VALUES (:username)";

    // namedParameters.addValue("username", student.getUserName());

    // return jdbc.update(query, namedParameters);
    // }
}
