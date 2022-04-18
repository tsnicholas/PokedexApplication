package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.Version;
import net.minidev.json.JSONArray;

public class AbilityParser {
    public String parseEffect(Object abilityJsonDocument) {
        JSONArray jsonArray = JsonPath.read(abilityJsonDocument, "$.effect_entries[?(@.language.name == 'en')].effect");
        return jsonArray.get(0).toString();
    }

    public boolean assertExistsInVersion(Object abilityJsonDocument, Version version) {
        String generationName = JsonPath.read(abilityJsonDocument, "$.generation.name");
        int abilityGenerationID = version.getGenerationMap().getIdOf(generationName);
        return abilityGenerationID <= version.getGeneration().getGenerationID();
    }
}
