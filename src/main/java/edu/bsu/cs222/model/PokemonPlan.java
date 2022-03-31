package edu.bsu.cs222.model;

import java.util.List;
import java.util.Map;

public interface PokemonPlan {
    void setTypeList(List<Type> typeList);
    void setImmunities(List<String> immunities);

    void setWeaknesses(List<String> weaknesses);

    void setResistances(List<String> resistances);

    void setStatsMap(Map<String, Integer> statsMap);

    void setMoveList(List<Move> moveList);

    void setImageURL(String imageURL);
}
