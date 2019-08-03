package gallantmedia.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
public class Music implements Serializable {

    private static final long serialVersionUID = 2L;

    private String artistName;

    public String getArtistName()
    {
            return artistName;
    }

    public void setArtistName(String artistName)
    {
            this.artistName = artistName;
    }
}
