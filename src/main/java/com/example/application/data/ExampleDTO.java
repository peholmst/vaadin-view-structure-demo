package com.example.application.data;

import java.util.ArrayList;
import java.util.List;

public record ExampleDTO(
        String first,
        String second,
        int third,
        boolean fourth,
        String fifth,
        String sixth
) {

    public static List<ExampleDTO> generate(int count) {
        var list = new ArrayList<ExampleDTO>(count);
        for (int i = 0; i < count; ++i) {
            list.add(new ExampleDTO("First " + i,
                    "Second " + i,
                    i,
                    i % 2 == 0,
                    "Fifth " + i,
                    "Sixth " + i));
        }
        return list;
    }
}