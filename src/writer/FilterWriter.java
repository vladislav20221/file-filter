package src.writer;

import java.io.Closeable;
import java.io.IOException;

/**
 * Абстракция для записи отфильтрованных данных в файл назначения.
 */
public interface FilterWriter extends Closeable {

    /**
     * Записывает переданную строку в фаел. Каждая срока пишется с символом переноса каретки.
     *
     * @param line          строка для записи.
     * @throws IOException  ошибка записи данных в файл.
     */
    void write(String line) throws IOException;

}
