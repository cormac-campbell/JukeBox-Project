package part02;
import part01.AudioPlayer;
import part01.AudioTrack;
public class WavPlayer extends AudioPlayer
{
	public WavPlayer(AudioTrack currentTrack)
	{
		super(currentTrack);
	}
	
	@Override
	 public boolean play(AudioTrack currentTrack)
	    {
	     if (!currentTrack.getEncoding().equals("WAV") || currentTrack == null)
	    	{
	    		return false;
	    	}
	       return true;
	    }
	 
}
