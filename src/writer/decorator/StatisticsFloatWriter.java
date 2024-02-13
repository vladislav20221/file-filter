package src.writer.decorator;

import src.writer.FilterWriter;

import java.io.IOException;

public class StatisticsFloatWriter extends WriterDecorator {
    private float max = Float.MIN_VALUE;
    private float min = Float.MAX_VALUE;
    private float sum = 0F;

    public StatisticsFloatWriter(final FilterWriter writer) {
        super(writer);
    }

    @Override
    public void write(String line) throws IOException {
        final float value = Float.parseFloat(line);
        sum += value;
        max = Float.max(max, value);
        min = Float.min(min, value);
        super.writer.write(line);
    }

    @Override
    public void close() throws IOException {
        System.out.println("min = " + getMin() + "\tmax = " + getMax() + "\tsum = " + getSum() + "\taverage = " + average());
        super.writer.close();
    }

    public float getMin() {
        return min;
    }

    public float getMax() {
        return max;
    }

    public float getSum() {
        return sum;
    }

    public float average(final int count) {
        return sum/(float) count;
    }

    public float average() {
        return (min+max)/2F;
    }

}
