package com.fresco.arraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

class Passanger {

  int id;
  float fare;

  public Passanger(int id, float fare) {
    this.id = id;
    this.fare = fare;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public float getFare() {
    return fare;
  }

  public void setFare(float fare) {
    this.fare = fare;
  }

  @Override
  public String toString() {
    return "Passanger{" +
        "id=" + id +
        ", fare=" + fare +
        '}';
  }
}

public class BusProb {

  public static final String QUERY_OUTPUT_1 = "%d passengers got on the bus and %d passengers got out of the bus";
  public static final String QUERY_OUTPUT_2 = "%d passengers traveled with a fare of %.1f";
  int capacity;
  List<Passanger> passengers;
  int currentCount = 0;

  public String output(int capacity, int stops, List<String> listOfInputStrings, String query) {
    this.capacity = capacity;
    passengers = new ArrayList(capacity);

    listOfInputStrings.stream().forEach(x ->
    {
      Arrays.stream(x.split(" "))
          .map(Integer::parseInt).forEach(id -> {
        if (id >= 0) {
          currentCount++;
        } else {
          currentCount--;
        }
      });

      Arrays.stream(x.split(" "))
          .map(Integer::parseInt).map(id ->

          new Passanger(id, id >= 0 ? getFare() : 0.0F)
      )
          .forEach(passengers::add);

    });

    System.out.println(passengers);
    System.out.println(evaluateQuery(query));
    return evaluateQuery(query);
  }

  private float getFare() {
    float fare;
    if (currentCount <= Math.ceil(capacity * 0.25)) {
      fare = capacity + capacity * 0.6F;
    } else if (currentCount <= Math.ceil(capacity * 0.5)) {
      fare = capacity + capacity * 0.3F;
    } else {
      fare = capacity;
    }
    return fare;
  }

  private String evaluateQuery(String query) {
    String output = "";
    String[] parameters = query.split(", ");
    switch (parameters[0]) {
      case "1":
        long gotOn = passengers.stream().filter(passanger -> passanger.getId() >= 0)
            .count();
        long gotOff = passengers.stream().filter(passanger -> passanger.getId() <= 0)
            .count();
        output = String.format(QUERY_OUTPUT_1, gotOn, gotOff);
        break;
      case "2":
        Map<Float, Long> groupByFare = passengers.stream()
            .filter(passanger -> passanger.getId() > 0)
            .collect(Collectors.groupingBy(Passanger::getFare, Collectors.counting()));
        List<String> texts = groupByFare.entrySet().stream()
            .sorted(Entry.comparingByKey(Comparator.reverseOrder()))
            .map(entry -> String
                .format(QUERY_OUTPUT_2, entry.getValue(), entry.getKey()))
            .collect(Collectors.toList());
        for (int i = 0; i < texts.size(); i++) {
          output += texts.get(i);
          if (i == texts.size() - 2) {
            output += " and ";
          } else if (i < texts.size() - 2) {
            output += ", ";
          }
        }
        break;
      case "3":
        double totalFare = passengers.stream()
            .filter(passanger -> Math.abs(passanger.getId()) == Integer
                .parseInt(parameters[1])).mapToDouble(Passanger::getFare).sum();
        output = String.format("Passenger %s spent a total fare of %.1f", parameters[1], totalFare);
        break;
      case "4":
        long timesBoarded = passengers.stream()
            .filter(passanger -> passanger.getId() == Integer
                .parseInt(parameters[1])).count();
        output = String
            .format("Passenger %s has got on the bus for %d times", parameters[1], timesBoarded);
        break;
      case "5":
        boolean isOnBus = passengers.stream()
            .filter(passanger -> Math.abs(passanger.getId()) == Integer
                .parseInt(parameters[1])).mapToInt(Passanger::getId).sum() != 0;
        if (isOnBus) {
          output = String
              .format("Passenger %s was inside the bus at the end of the trip", parameters[1]);
        } else {
          output = String
              .format("Passenger %s was not inside the bus at the end of the trip", parameters[1]);
        }
        break;
    }
    return output;
  }
}
