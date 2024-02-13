package src.writer;

import src.writer.impl.IoWriter;

import java.io.IOException;
import java.util.Objects;

/**
 * Абстрактный класс для объектов реализующих - FilterWriter
 */
public abstract class AbstractWriter implements FilterWriter {
    protected final FilterWriter baseWriter;

    /**
     * Путь для файла задатся в конфигурации.
     * <file-path-out> - дириктория в которую сохряняются все отфильрованные данные.
     *
     * @param fileName  имя файла назначения.
     */
    public AbstractWriter(final String fileName) {
        Objects.requireNonNull(fileName, "Target write file name is null !");
        this.baseWriter = new IoWriter(fileName);
    }

    @Override
    public void write(final String line) throws IOException {
        this.baseWriter.write(line);
    }

    @Override
    public void close() throws IOException {
        this.baseWriter.close();
    }

}
