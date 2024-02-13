package src.writer.decorator;

import src.writer.FilterWriter;

import java.io.IOException;

public class StatisticsStringWriter extends WriterDecorator {
    private int max = Integer.MIN_VALUE;
    private int min = Integer.MAX_VALUE;

    public StatisticsStringWriter(final FilterWriter writer) {
        super(writer);
    }

    @Override
    public void write(final String line) throws IOException {
        max = Integer.max(max, line.length());
        min = Integer.min(min, line.length());
        this.writer.write(line);
    }

    @Override
    public void close() throws IOException {
        System.out.println("min length = " + getMaxLength() + "\tmax length = " + getMinLength() + "\taverage length = " + average());
        this.writer.close();
    }

    public int getMaxLength() {
        return max;
    }

    public int getMinLength() {
        return min;
    }

    public float average() {
        return (float)(min+max)/2F;
    }

}
