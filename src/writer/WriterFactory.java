package src.writer;

import src.Application;
import src.writer.decorator.StatisticsFloatWriter;
import src.writer.decorator.StatisticsIntegerWriter;
import src.writer.decorator.StatisticsStringWriter;
import src.writer.decorator.StatisticsWriter;
import src.writer.floater.FloatWriter;
import src.writer.integer.IntegerWriter;
import src.writer.string.StringWriter;

public class WriterFactory {

    public static Writer createIntegerWriter() {
        final boolean isAllStatistics = Boolean.parseBoolean(Application.properties.getProperty("all-statistics"));
        final IntegerWriter writer = new IntegerWriter();
        final StatisticsWriter baseStatistics = new StatisticsWriter(writer);

        if (isAllStatistics) {
            return new StatisticsIntegerWriter(baseStatistics);
        }

        return baseStatistics;
    }

    public static Writer createStringWriter() {
        final boolean isAllStatistics = Boolean.parseBoolean(Application.properties.getProperty("all-statistics"));
        final StringWriter writer = new StringWriter();
        final StatisticsWriter baseStatistics = new StatisticsWriter(writer);

        if (isAllStatistics) {
            return new StatisticsStringWriter(baseStatistics);
        }

        return baseStatistics;
    }

    public static Writer createFloatWriter() {
        final boolean isAllStatistics = Boolean.parseBoolean(Application.properties.getProperty("all-statistics"));
        final FloatWriter writer = new FloatWriter();
        final StatisticsWriter baseStatistics = new StatisticsWriter(writer);

        if (isAllStatistics) {
            return new StatisticsFloatWriter(baseStatistics);
        }

        return baseStatistics;
    }

}
