package com.ninja_squad.training.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Le TP Lambda
 * @author JB
 */
public class TP {

    public static void main(String[] args) {
        Arrays.asList(TP.class.getDeclaredMethods()).forEach(m -> System.out.println(m + " " + m.isBridge() + " " + m.isSynthetic()));
    }
    /**
     * Écrivez les dates des tweets sur la sortie standard
     */
    public static void step1() {
        Tweet.TWEETS.forEach(t -> System.out.println(t.date()));
    }

    /**
     * Faites la même chose, sans appeler date() ni System.out.println()
     */
    public static void step2() {
        Tweet.TWEETS
             .stream()
             .map(Tweet::date)
             .forEach(System.out::println);
    }

    /**
     * Extrayez une List<String> qui contient les senders des tweets
     */
    public static List<String> step3() {
        return Tweet.TWEETS
                    .stream()
                    .map(Tweet::sender)
                    .collect(Collectors.toList());
    }

    /**
     * Extrayez une List<String> qui contient les senders des tweets, sans duplicata
     */
    public static List<String> step4() {
        return Tweet.TWEETS
                    .stream()
                    .map(Tweet::sender)
                    .distinct()
                    .collect(Collectors.toList());
    }

    /**
     * Extrayez une List<String> qui contient les senders des tweets, sans duplicata, triés par ordre alphabétique
     */
    public static List<String> step5() {
        return Tweet.TWEETS
                     .stream()
                     .map(Tweet::sender)
                     .distinct()
                     .sorted()
                     .collect(Collectors.toList());
    }

    /**
     * Extrayez une List<Tweet> qui contient les tweets contenant le hashtag #lambda
     */
    public static List<Tweet> step6() {
        return Tweet.TWEETS
                    .stream()
                    .filter(t -> t.containsHashTag("#lambda"))
                    .collect(Collectors.toList());
    }

    /**
     * Extrayez une List<Tweet> qui contient les tweets contenant le hashtag #lambda, triés par sender puis par date
     */
    public static List<Tweet> step7() {
        return Tweet.TWEETS
                    .stream()
		              .filter(t -> t.containsHashTag("#lambda"))
			           .sorted(Comparator.comparing(Tweet::sender)
                    .thenComparing(Tweet::date))
                    .collect(Collectors.toList());
    }

    /**
     * Extrayez un Set<String> qui contient l'ensemble des hash tags des tweets
     */
    public static Set<String> step8() {
        return Tweet.TWEETS
                    .stream()
                    .flatMap(t -> t.getHashTags().stream())
                    .collect(Collectors.toSet());
    }

    /**
     * Créez une Map<String, List<Tweet>> qui contient, pour chaque sender, les tweets envoyés par ce sender
     */
    public static Map<String, List<Tweet>> step9() {
        return Tweet.TWEETS
                    .stream()
                    .collect(Collectors.groupingBy(Tweet::sender, Collectors.toList()));
    }

    /**
     * Extrayez deux listes: les tweets qui contiennent le hash tag #lambda, et ceux qui ne les contiennent pas.
     */
    public static Map<Boolean, List<Tweet>> step10() {


       return Tweet.TWEETS
                    .stream()
                    .collect(Collectors.partitioningBy(t -> t.containsHashTag("#lambda")));
    }

    public static Map<Boolean, List<Tweet>> step10x() {
        Stream<Tweet> stream = Tweet.TWEETS.stream();
        Predicate<Tweet> lambda = t -> t.containsHashTag("#lambda");
        Collector<Tweet, ?, Map<Boolean, List<Tweet>>> collector = Collectors.partitioningBy(lambda);
        return stream.collect(collector);
    }

   public record Stats(int tweetCount, int characterCount) {

      public Stats(Tweet tweet) {
         this(1, tweet.text().length());
      }

      public Stats withStats(Stats stats) {
         return new Stats(this.tweetCount + stats.tweetCount, this.characterCount + stats.characterCount);
      }

      public int average() {
         return tweetCount == 0 ? 0 : (characterCount / tweetCount);
      }

      public int total() {
         return characterCount;
      }
   }

    /**
     * Calculez le total et la moyenne du nombre de caractères des textes des tweets.
     * Hints:
     *     Créez une class Stats
     *     Utilisez stream.collect(..., ..., ...) ou stream.map(...).reduce(...)
     */
    public static Stats step11() {
       return Tweet.TWEETS
          .stream()
          .map(Stats::new)
          .reduce(new Stats(0, 0), Stats::withStats);
    }

   /**
    * Faites la même chose, mais de manière parallèle
    */
   public static Stats step12() {
      return Tweet.TWEETS
         .parallelStream()
         .map(Stats::new)
         .reduce(new Stats(0, 0), Stats::withStats);
   }
}
