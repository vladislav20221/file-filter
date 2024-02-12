package src.writer.decorator;

import src.writer.Writer;

import java.io.IOException;

public class StatisticsIntegerWriter extends WriterDecorator {
    private int max = Integer.MIN_VALUE;
    private int min = Integer.MAX_VALUE;
    private int sum = 0;

    public StatisticsIntegerWriter(final Writer writer) {
        super(writer);
    }

    @Override
    public void write(String line) throws IOException {
        final int value = Integer.parseInt(line);
        sum += value;
        max = Integer.max(max, value);
        min = Integer.min(min, value);
        super.writer.write(line);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Integer statistics: min = " + getMin() + "\tmax = " + getMax() + "\tsum = " + getSum() + "\taverage = " + average());
        super.writer.close();
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public int getSum() {
        return sum;
    }

    public float average(final int count) {
        return (float)sum/(float) count;
    }

    public float average() {
        return (float) (min+max)/2F;
    }

}