package org.example.Model.Function;

import java.util.List;
import java.util.Map;

public interface StatisticsTime<T> {
    public Map<Integer, Long> calculateTimeDistribution(List<T> in);
}
