package gallantmedia;

import java.util.List;

public interface PhotographyService
{
    public List<Photography> findAll();

    public void savePhotography(Photography photography);
}
