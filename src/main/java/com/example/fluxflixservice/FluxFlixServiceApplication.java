package com.example.fluxflixservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class FluxFlixServiceApplication {

    private List<Movie> movieList;

    public static void main(String[] args) {
        SpringApplication.run(FluxFlixServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner demo() {
        return args -> {
            movieList = Stream.of("Aeon Flux", "The Mono Void", "The Fluxinator", "Silence of the Lambdas", "Y tu Mono Tambian", "Attack of the Fluxes", "Back to the Future")
                    .map(name -> new Movie(UUID.randomUUID().toString(), name, randomGenre().toString()))
                    //.map(movieRepository::save)
                    .collect(Collectors.toList());
        };
    }

    private Object randomGenre() {
        String[] genres = "horror,ramcom,drama,action,doc".split(",");
        return genres[new Random().nextInt(genres.length)];
    }
}

@Service
class FluxService{


}

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
class MovieEvent {
    private Movie movie;
    private LocalDate date;
    private String user;
}

@Data
@ToString
class Movie {

    private String id;
    private String title;
    private String genre;

    Movie(String id,String title,String genre){
        this.genre = genre;
        this.id = id;
        this.title = title;
    }
}