package src.writer.decorator;

import src.writer.Writer;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsFloatWriter extends WriterDecorator {
    private final AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
    private final AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
    private final AtomicInteger sum = new AtomicInteger(0);

    public StatisticsFloatWriter(final Writer writer) {
        super(writer);
    }

    @Override
    public void write(String line) throws IOException {
        final int value = Float.floatToIntBits(Float.parseFloat(line));
        sum.addAndGet(value);
        max.set(Integer.max(max.get(), value));
        min.set(Integer.min(min.get(), value));
        super.writer.write(line);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Float statistics min: " + getMin() + "\tmax: " + getMax() + "\tsum: " + getSum() + "\taverage: " + average(getMin(), getMax()));
        super.writer.close();
    }

    public float getMin() {
        return Float.intBitsToFloat(min.get());
    }

    public float getMax() {
        return Float.intBitsToFloat(max.get());
    }

    public float getSum() {
        return Float.intBitsToFloat(sum.get());
    }

    public float average(final int count) {
        return Float.intBitsToFloat(sum.get())/(float) count;
    }

    public float average(final float min, final float max) {
        return (min+max)/2F;
    }

}
