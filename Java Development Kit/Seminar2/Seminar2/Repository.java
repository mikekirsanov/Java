package Seminar2;

import java.util.List;

public interface Repository {
    void save(String text);
    List<String> read();
}
