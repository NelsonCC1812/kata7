package dto;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import model.db.schema.FlightField;

public class HistogramDTO {
    public String dimension;
    public Map<String, Integer> filters;
    public Set<Integer> x;
    public Collection<Integer> y;

    public HistogramDTO(String dimension, Map<FlightField, Integer> filters, Set<Integer> x, Collection<Integer> y) {
        this.dimension = dimension;

        Map<String, Integer> map = new HashMap<>();
        for (FlightField ff : filters.keySet())
            map.put(ff.name(), filters.get(ff));
        this.filters = map;

        this.x = x;
        this.y = y;
    }
}
