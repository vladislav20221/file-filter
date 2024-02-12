package src.writer;

import src.Application;
import src.writer.decorator.StatisticsFloatWriter;
import src.writer.decorator.StatisticsIntegerWriter;
import src.writer.decorator.StatisticsStringWriter;
import src.writer.decorator.StatisticsTimeWorkerWriter;
import src.writer.decorator.StatisticsWriter;
import src.writer.impl.FloatWriter;
import src.writer.impl.IntegerWriter;
import src.writer.impl.StringWriter;

public class WriterFactory {

    public static Writer createIntegerWriter() {
        final boolean isAllStatistics = Boolean.parseBoolean(Application.properties.getProperty("all-statistics"));
        final IntegerWriter writer = new IntegerWriter();
        final StatisticsTimeWorkerWriter timeStatistics = new StatisticsTimeWorkerWriter(writer);
        final StatisticsWriter baseStatistics = new StatisticsWriter(timeStatistics);

        if (isAllStatistics) {
            return new StatisticsIntegerWriter(baseStatistics);
        }

        return baseStatistics;
    }

    public static Writer createStringWriter() {
        final boolean isAllStatistics = Boolean.parseBoolean(Application.properties.getProperty("all-statistics"));
        final StringWriter writer = new StringWriter();
        final StatisticsTimeWorkerWriter timeStatistics = new StatisticsTimeWorkerWriter(writer);
        final StatisticsWriter baseStatistics = new StatisticsWriter(timeStatistics);

        if (isAllStatistics) {
            return new StatisticsStringWriter(baseStatistics);
        }

        return baseStatistics;
    }

    public static Writer createFloatWriter() {
        final boolean isAllStatistics = Boolean.parseBoolean(Application.properties.getProperty("all-statistics"));
        final FloatWriter writer = new FloatWriter();
        final StatisticsTimeWorkerWriter timeStatistics = new StatisticsTimeWorkerWriter(writer);
        final StatisticsWriter baseStatistics = new StatisticsWriter(timeStatistics);

        if (isAllStatistics) {
            return new StatisticsFloatWriter(baseStatistics);
        }

        return baseStatistics;
    }

}
