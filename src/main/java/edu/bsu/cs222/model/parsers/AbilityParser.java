package edu.bsu.cs222.model.parsers;

import com.jayway.jsonpath.JsonPath;
import edu.bsu.cs222.model.Version;

import java.util.List;

public class AbilityParser {
    public String parseEffect(Object abilityJsonDocument) {
        List<String> effectDescription = JsonPath.read(abilityJsonDocument,
                "$.effect_entries[?(@.language.name == 'en')].effect");
        return effectDescription.get(0);
    }

    public boolean assertExistsInVersion(Object abilityJsonDocument, Version version) {
        String generationName = JsonPath.read(abilityJsonDocument, "$.generation.name");
        int abilityGenerationID = version.getGenerationMap().getIdOf(generationName);
        return abilityGenerationID <= version.getGeneration().getGenerationID();
    }
}
