package gallantmedia;

import com.google.gson.JsonElement;
import gallantmedia.models.Music;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import gallantmedia.services.music.Goodmusic;


@CrossOrigin(origins = "http://staging.gallantone.com")
@RestController
public class MusicController
{
    Logger logger = LoggerFactory.getLogger(MusicController.class);

    /**
     * Get request for testing json calls
     * @return
     */
    @RequestMapping(value="/music", method=RequestMethod.GET)
    public String showMusic()
    {
        Goodmusic goodmusic = new Goodmusic();

        String final_artist_string = this.tokenizeArtistString("vanessa_hudgens");

        return goodmusic.renderArtist(final_artist_string);
    }

    @RequestMapping(value="/music", method=RequestMethod.POST, headers="Accept=application/json")
    public String requestMusic( @RequestBody Music music )
    {
        logger.info("User enters artist: " + music.getArtistName());

        Goodmusic goodmusic = new Goodmusic();

        String final_artist_string = this.tokenizeArtistString(music.getArtistName());

        return goodmusic.renderArtist(final_artist_string);
    }

    /**
     * Turn string into Deezer api usable form
     * @param artist_string
     * @return
     */
    private String tokenizeArtistString(String artist_string)
    {
        String fixed_string;
        fixed_string = artist_string.replace(" ", "_");
        fixed_string = fixed_string.replace("the_", "");
        return fixed_string;
    }

}
