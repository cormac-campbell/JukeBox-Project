package part01;

public class AudioPlayer implements IPlayer
{
    private AudioTrack currentTrack;
	
    public AudioPlayer(AudioTrack currentTrack)
    {
    	this.currentTrack = currentTrack;
    }
    
    public boolean play(AudioTrack currentTrack)
    {
     if (currentTrack.getTitle().equals("Unknown title name!") && currentTrack.getArtist().equals("Unknown Artist Name!") 
    		 && currentTrack.getEncoding().equals("invalid encoding!") && currentTrack.getDuration() == 0 ) 
    	{
    		return false;
    	}
       return true;
    }
}
