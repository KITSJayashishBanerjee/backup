package com.fresco.treemap;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class TreemapHandson {

    public TreeMap<Integer, String> createPlayerPositionMap(String cricketDataset) {
        return Arrays.stream(cricketDataset.split("\\|"))
                .map(data -> data.split(","))
                .collect(Collectors
                        .toMap(str -> Integer.parseInt(str[0]), str -> str[1], (o1, o2) -> o1,
                                TreeMap::new));

    }

    public TreeMap<String, Integer> createPlayerScoreMap(String cricketDataset) {
        return Arrays.stream(cricketDataset.split("\\|"))
                .map(data -> data.split(","))
                .collect(Collectors
                        .toMap(str -> str[1], str -> Integer.parseInt(str[2]), (o1, o2) -> o1,
                                TreeMap::new));
    }

    public TreeMap<Double, String> createMatchesMap(String cricketDataset) {
        return Arrays.stream(cricketDataset.split("\n")).map(this::createPlayerScoreMap)
                .flatMap(m -> m.entrySet().stream())
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> Arrays.asList(entry.getValue()),
                                (value1, value2) -> {
                                    ArrayList<Integer> combined = new ArrayList<>(value1);
                                    combined.addAll(value2);
                                    return combined;
                                }))
                .entrySet().stream().collect(Collectors.toMap(
                        entry -> entry.getValue().stream()
                                .mapToInt(i -> i).average().getAsDouble(),
                        entry -> entry.getKey(),
                        (v1, v2) -> v1,
                        TreeMap::new
                        )
                );
    }

    public TreeMap<Integer, String> createMatchesMap2(String cricketDataset) {
        return Arrays.stream(cricketDataset.split("\n"))
                .map(data -> data.split("\\|"))
                .flatMap(Arrays::stream)
                .map(data -> data.split(","))
                .filter(arr -> arr[0].equals("1"))
                .collect(
                        Collectors.toMap(arr -> arr[1],
                                arr -> Integer.parseInt(arr[2]),
                                (value1, value2) -> value1 + value2
                        )
                ).entrySet().stream().collect(Collectors.toMap(
                entry -> entry.getValue(),
                entry -> entry.getKey(),
                (v1, v2) -> v1,
                TreeMap::new
                )
        );
    }

    public String getQuery(String cricketDataset, String query) {
        String[] querySplit = query.split(" ");
        TreeMap<Integer, String> playerPositionMap;
        TreeMap<String, Integer> playerScoreMap;


        String result = null;
                switch (querySplit[0]){
            case "1":
               playerPositionMap = createPlayerPositionMap(cricketDataset);
                result = playerPositionMap.subMap(Integer.parseInt(querySplit[1]), true,
                        Integer.parseInt(querySplit[2]), true)
                        .entrySet().stream().map(entry -> entry.getKey() + " " + entry.getValue())
                        .collect(Collectors.joining("\n")) + "\n";
                break;
            case "2":
                playerPositionMap = createPlayerPositionMap(cricketDataset);
                playerScoreMap = createPlayerScoreMap(cricketDataset);
                List<String> playerScoredToThreshold = playerScoreMap.entrySet().stream()
                        .filter(entry -> entry.getValue() > Integer.parseInt(querySplit[1]))
                        .map(Entry::getKey).collect(Collectors.toList());
                result = playerPositionMap
                        .entrySet().stream().
                                filter(entry -> playerScoredToThreshold.contains(entry.getValue()))
                                .map(entry -> entry.getKey() + " " + entry.getValue())
                        .collect(Collectors.joining("\n")) + "\n" ;
                break;
            case "3":
                result = "The Efficient Opener is " + createMatchesMap2(cricketDataset).descendingMap().firstEntry().getValue();
                break;
        }


        return result;
    }


}
            

        
        
        
        
        

