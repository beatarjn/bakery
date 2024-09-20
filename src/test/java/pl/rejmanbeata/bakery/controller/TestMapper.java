package pl.rejmanbeata.bakery.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestMapper {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
