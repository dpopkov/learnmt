package learn.mt.pspmard.ajcp.conchashmap;

import learn.mt.pspmard.ajcp.conchashmap.model.Actor;
import learn.mt.pspmard.ajcp.conchashmap.model.Movie;
import learn.mt.pspmard.ajcp.conchashmap.model.MovieReader;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapParallelPatterns {
    public static void main(String[] args) {
        ConcurrentHashMap<Actor, Set<Movie>> map = new ConcurrentHashMap<>();
        MovieReader reader = new MovieReader();
        reader.addActorsToMap(map);
        System.out.println("# Actors = " + map.size());

        final int parallelismThreshold = 10;
        int maxMoviesForOneActor = map.reduce(parallelismThreshold,
                (actor, movies) -> movies.size(), Integer::max);
        System.out.println("Max movies for one actor = " + maxMoviesForOneActor);

        Actor mostSeenActor = map.search(parallelismThreshold,
                (actor, movies) -> movies.size() == maxMoviesForOneActor ? actor : null);
        System.out.println("Most seen actor = " + mostSeenActor);

        int numMovies = map.reduce(parallelismThreshold,
                (actor, movies) -> movies.size(), Integer::sum);
        System.out.printf("Average movies per actor = %.1f%n", (double) numMovies / map.size());
    }
}
