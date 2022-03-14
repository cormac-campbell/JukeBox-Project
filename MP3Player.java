package part02;
import part01.AudioPlayer;
import part01.AudioTrack;

public class MP3Player extends AudioPlayer
{
	public MP3Player(AudioTrack currentTrack)
	{
		super(currentTrack);
	}
	
	@Override
	 public boolean play(AudioTrack currentTrack)
	    {
	     if (!currentTrack.getEncoding().equals("MP3") || currentTrack == null)
	    	{
	    		return false;
	    	}
	       return true;
	    }
}
