package org.example.Function;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface StatisticsTime<T> {
    public Map<Integer, Long> calculateTimeDistribution(List<T> in);
}
