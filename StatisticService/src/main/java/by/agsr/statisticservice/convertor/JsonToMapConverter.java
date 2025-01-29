package by.agsr.statisticservice.convertor;

import by.agsr.statisticservice.exception.JsonToMapConverterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class JsonToMapConverter implements AttributeConverter<Map<String, Object>, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            String errorMsg = String.format("An error occurred during parsing map: '%s' to String.", attribute);
            throw new JsonToMapConverterException(errorMsg, e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            String errorMsg = String.format("An error occurred during parsing json: '%s' to Map.", json);
            throw new JsonToMapConverterException(errorMsg, e);
        }
    }
}
