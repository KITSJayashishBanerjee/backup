package com.fresco.hashset;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Hashset
{

    public static String getOut(int numberOfMatches, String squads, int squad1, int squad2)
    {
        List<Set<String>> squadPlayers =
        Arrays.stream(squads.split("\\#")).map(squad ->
            Arrays.stream(squad.split(" ")).collect(Collectors.toSet())).collect(Collectors.toList());

        Set<String> commonPlayers = new HashSet<>(squadPlayers.get(0));
        for(int i = 1; i < squadPlayers.size(); i++) {
            commonPlayers.retainAll(squadPlayers.get(i));
        }
        Set<String> specificPlayers = new HashSet<>(squadPlayers.get(squad2 - 1));

        specificPlayers.removeAll(squadPlayers.get(squad1 - 1));


        return String.join(" ", commonPlayers) + ", "+ String.join(" ", specificPlayers);
    }
}
