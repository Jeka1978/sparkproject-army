package com.army;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import lombok.experimental.Wither;

/**
 * @author Evgeny Borisov
 */
@Wither
@Data
@AllArgsConstructor
public class Trip {
    private final String id;
    private final String city;
    private final int km;

    public Trip(String line) {
        String[] split = line.split(" ");
        id = split[0];
        city = split[1].toLowerCase();
        km = Integer.parseInt(split[2]);
    }
}
