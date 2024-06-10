package com.kubixdev.bingr.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String subscriptionPlan;

    @Column(nullable = true)
    private LocalDate subscriptionDate;

    /////////////////////
    // SQL
    /////////////////////
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_movies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> favoriteMovies = new ArrayList<>();


    /////////////////////
    // SUBSCRIPTION
    /////////////////////
    public boolean isSubscriptionActive() {
        if ("none".equals(subscriptionPlan) || subscriptionDate == null) {
            return false;
        }

        LocalDate currentDate = LocalDate.now();
        LocalDate subscriptionEndDate = subscriptionDate.plusMonths(1);
        return !currentDate.isAfter(subscriptionEndDate);
    }

    public long getDaysLeftInSubscription() {
        if (!isSubscriptionActive()) {
            return 0;
        }

        LocalDate subscriptionEndDate = subscriptionDate.plusMonths(1);
        return ChronoUnit.DAYS.between(LocalDate.now(), subscriptionEndDate);
    }
}