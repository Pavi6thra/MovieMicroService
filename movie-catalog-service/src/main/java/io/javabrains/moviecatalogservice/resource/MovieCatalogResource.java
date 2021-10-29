package io.javabrains.moviecatalogservice.resource;


import com.netflix.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.services.MovieInfo;
import io.javabrains.moviecatalogservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    //private Movie movie;
   // private Rating rating;

    /*@Autowired
    private DiscoveryClient discoveryClient;
    */
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @RequestMapping("/{userId}")
   //  @HystrixCommand(fallbackMethod = "getFallBackResponse")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

      //  RestTemplate restTemplate=new RestTemplate();
      /* Movie movie= restTemplate.getForObject("http://localhost:8081/movies/1", Movie.class);*/
       // List<Rating> ratings= Arrays.asList(new Rating("1",5),new Rating("2",6));
        //get all rated movies id
        UserRating ratings=userRatingInfo.getUserRating(userId);
     return ratings.getUserRating().stream().
             map(rating -> movieInfo.getCatalogItem(rating)).collect(Collectors.toList());
        //for each movie id, call movie info service and get details
      //  return Collections.singletonList(new catalogItem("omg","test","8"));
    }
    /*@HystrixCommand(fallbackMethod = "getFallBackResponseForRating")
    private UserRating getUserRating(@PathVariable("userId") String userId){
        return restTemplate.getForObject("http://movie-rating-service/ratingsdata/users/" + userId, UserRating.class);
    }

    private UserRating getFallBackResponseForRating(@PathVariable("userId") String userId){
        UserRating userRating=new UserRating();
        userRating.setUserId(userId);
        userRating.setUserRating(Arrays.asList(
                new Rating("0",0)
        ));
        return userRating;
    }

    @HystrixCommand(fallbackMethod = "getFallBackResponseForMovieInfo")
    public catalogItem getCatalogItem(Rating rating){
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        //put them all together
        return  new catalogItem(movie.getName(),"test",rating.getRating());
    }

    private catalogItem getFallBackResponseForMovieInfo(Rating rating){
        return  new catalogItem("null","no response",rating.getRating());
    }
*/
    /*public List<catalogItem> getFallBackResponse(@PathVariable("userId") String userId){
        return  Arrays.asList(new catalogItem("No Movie", "null",0));
    }*/
}
