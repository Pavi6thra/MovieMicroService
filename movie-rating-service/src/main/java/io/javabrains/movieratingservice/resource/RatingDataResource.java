package io.javabrains.movieratingservice.resource;

import io.javabrains.movieratingservice.model.Rating;
import io.javabrains.movieratingservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId,8);
    }

    @RequestMapping("users/{userId}")
    public UserRating getUserWatchedMovies(@PathVariable("userId") String movieId){
        UserRating userRating=new UserRating();
       // List<Rating> ratings=Arrays.asList(new Rating("1",7),new Rating("2",8));
        userRating.setUserRating(Arrays.asList(new Rating("1",7),new Rating("2",8)));
        return userRating;
    }
}
