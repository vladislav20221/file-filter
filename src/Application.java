package src;

import src.enums.DataType;
import src.filter.FileFilter;
import src.writer.StatisticsWriter;
import src.writer.floater.FloatWriter;
import src.writer.floater.StatisticsFloatWriter;
import src.writer.integer.IntegerWriter;
import src.writer.integer.StatisticsIntegerWriter;
import src.writer.string.StatisticsStringWriter;
import src.writer.string.StringWriter;
import src.writer.Writer;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class Application {
    public static final Properties properties = new Properties();

    public static void main(String ... args) {
        try (final FileReader in = new FileReader("config.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final Map<DataType, Writer> writerMap = Map.of(DataType.INTEGER, createIntegerWriter(),
                                                       DataType.FLOAT, createFloatWriter(),
                                                       DataType.STRING, createStringWriter());

        final FileFilter filter = new FileFilter(writerMap);
        filter.scanning();

    }

    private static Writer createIntegerWriter() {
        final IntegerWriter writer = new IntegerWriter();
        final StatisticsIntegerWriter statistics = new StatisticsIntegerWriter(writer);
        final StatisticsWriter baseStatistics = new StatisticsWriter(statistics);

        return baseStatistics;
    }

    private static Writer createStringWriter() {
        final StringWriter writer = new StringWriter();
        final StatisticsStringWriter statistics = new StatisticsStringWriter(writer);
        final StatisticsWriter baseStatistics = new StatisticsWriter(statistics);

        return baseStatistics;
    }

    private static Writer createFloatWriter() {
        final FloatWriter writer = new FloatWriter();
        final StatisticsFloatWriter statistics = new StatisticsFloatWriter(writer);
        final StatisticsWriter baseStatistics = new StatisticsWriter(statistics);

        return baseStatistics;
    }

}
