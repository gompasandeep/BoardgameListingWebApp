package com.javaproject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.javaproject.beans.BoardGame;
import com.javaproject.beans.Review;
import com.javaproject.beans.Student;
import com.javaproject.database.DatabaseAccess;

@Controller
public class HomeController {

    // /**
    // * call rest method and send info back to the template
    // *
    // * @param model to add info to that'll get passed to the view
    // * @param restTemplate Helper class to easily invoke REST methods
    // * @return
    // */
    // @GetMapping("/")
    // public String goHome(Model model, RestTemplate restTemplate) {
    // // use ResponseEntity to make the RESTful call & specify that we want this as
    // an
    // // array of Student
    // ResponseEntity<Student[]> responseEntity =
    // restTemplate.getForEntity("http://localhost:8080/students",
    // Student[].class);
    // model.addAttribute("students", responseEntity.getBody());
    // return "view-student";
    // }

    @Autowired
    DatabaseAccess da;

    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;

    @GetMapping("/newUser")
    public String newUser(Model model) {

        List<String> authorities = da.getAuthorities();
        model.addAttribute("authorities", authorities);
        return "new-user";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String userName, @RequestParam String password,
            @RequestParam String[] authorities, Model model) {
        // if the username does not exist in database
        // if (da.userNameNotExist(userName)) {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        for (String authority : authorities) {
            authorityList.add(new SimpleGrantedAuthority(authority));
        }
        String encodedPassword = passwordEncoder.encode(password);

        // check existing user
        User user = new User(userName, encodedPassword, authorityList);

        jdbcUserDetailsManager.createUser(user);

        model.addAttribute("message", "User succesfully added");
        // return "/secured/gateway";
        // } else {
        // System.out.println("User already exists");
        // }
        return "redirect:/";
    }

    @GetMapping("/")
    public String goHome(Model model) {
        List<BoardGame> boardgames = da.getBoardGames();
        model.addAttribute("boardgames", boardgames);
        return "index";
    }

    @GetMapping("/reviews/{id}")
    public String getReviews(@PathVariable Long id, Model model) {
        model.addAttribute("boardgame", da.getBoardGame(id));
        model.addAttribute("reviews", da.getReviews(id));
        return "review";
    }

    @GetMapping("/secured/addReview/{id}")
    public String addReview(@PathVariable Long id, Model model) {
        model.addAttribute("boardgame", da.getBoardGame(id));
        model.addAttribute("review", new Review());

        return "/secured/addReview";
    }

    @GetMapping("/secured/addBoardGame")
    public String addBoardGame(Model model) {
        model.addAttribute("boardgame", new BoardGame());
        return "/secured/addBoardGame";
    }

    @PostMapping("/boardgameAdded")
    public String boardgameAdded(@ModelAttribute BoardGame boardgame) {
        int returnValue = da.addBoardGame(boardgame);
        System.out.println("return value is: " + returnValue);
        return "redirect:/";
    }

    @GetMapping("/editReview/{id}")
    public String editReview(@PathVariable Long id, Model model) {
        Review review = da.getReview(id);
        model.addAttribute("review", review);
        model.addAttribute("boardgame", da.getBoardGame(review.getGameId()));
        return "/secured/addReview";
    }

    @PostMapping("/reviewAdded")
    public String reviewAdded(@ModelAttribute Review review) {
        int returnValue;
        // if id exists, edit
        if (review.getId() != null) {
            returnValue = da.editReview(review);
        } else {
            // if id not exists, add
            returnValue = da.addReview(review);
        }
        System.out.println("return value is: " + returnValue);
        return "redirect:/reviews/" + review.getGameId();
    }

    @GetMapping("/deleteReview/{id}")
    public String deleteReview(@PathVariable Long id) {
        Long gameId = da.getReview(id).getGameId();
        int returnValue = da.deleteReview(id);
        System.out.println("return value is: " + returnValue);
        return "redirect:/reviews/" + gameId;
    }

    @GetMapping("/user")
    public String goToUserSecured() {
        return "/secured/user/index";
    }

    @GetMapping("/manager")
    public String goToManagerSecured() {
        return "/secured/manager/index";
    }

    @GetMapping("/secured")
    public String goToSecured() {
        return "/secured/gateway";
    }

    @GetMapping("/login")
    public String goToLogin() {
        return "login";
    }

    @GetMapping("/permission-denied")
    public String goToDenied() {
        return "/error/permission-denied";
    }
}
