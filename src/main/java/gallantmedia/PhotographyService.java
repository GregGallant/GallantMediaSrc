package gallantmedia;

import java.util.List;

public interface PhotographyService
{
    public List<Photography> findAll();

    public String findName();

    public void savePhotography(Photography photography);
}
