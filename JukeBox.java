package part01;


import java.util.*;

public class JukeBox implements IJukeBox
{
private ArrayList<AudioTrack> allTracks;
private AudioPlayer player;


public JukeBox(AudioPlayer player)
{
	allTracks = new ArrayList<AudioTrack>();
	this.player = player;
}

public String getTrackList()
{

	String str = "Track List: ";
	 for (AudioTrack obj : allTracks)
     { 		      
         str = str + obj.getTrackCode() + ". " + obj.getTitle() + " "; 		
     }
	
	 if (!str.equals("Track List: "))
	 {
     return " " + str;
	 }
	 else
	 {
		return "No Tracks in track list";
	 }
}

public boolean playTrack(AudioTrack trk)
{

  return  player.play(trk);
	
	
}

public String shuffle(ArrayList<AudioTrack> list)
{

	
	ArrayList<AudioTrack> shuffledTracks = new ArrayList<AudioTrack>();
	String str = "Shuffled Tracks\n***************";
	if (list.size() > 0)
	{
	int count = 0;
	int[] repeatedValues = new int[200];
	Random rand = new Random();
	AudioTrack result = null;
	do 
	{
		boolean add = true;
		int value = rand.nextInt(list.size());
		result = (AudioTrack)list.get(value);
	
       for (int index = 0; index<count; index++)
       {
    	   if (repeatedValues[index] == value)
    	   {
    		   add = false;
    	   }
       }
       if (add == true)
       {
              shuffledTracks.add(result);
       }
    		   repeatedValues[count] = value;
    			count++;
	} while (list.size() != shuffledTracks.size());

	for (AudioTrack trk : shuffledTracks)
	{
		
		str = str + "\n" + "*" + trk.getTitle();
		
	}
	}
	
	if (!str.equals("Shuffled Tracks\n***************"))
	{
	return str;
	}
	else
	{
	return "No Tracks to shuffle";
	}
}

public void addTrack(AudioTrack trk)
{
	//checking if song has already been added
	boolean trackAdd = true;
	
	if (!trk.getTitle().equals("Unknown title name!") || !trk.getArtist().equals("Unknown Artist Name!") 
   		 || !trk.getEncoding().equals("invalid encoding!") || trk.getDuration() != 0)
		{
	  trackAdd=true;
		} 
		else 
		{
			trackAdd =false;
		}
		
			if(allTracks != null)
			{
			for (AudioTrack obj : allTracks)
			{
					
			if (obj.getTitle().equalsIgnoreCase(trk.getTitle()) && obj.getArtist().equalsIgnoreCase(trk.getArtist()) )
				{
					trackAdd = false;
				}
			}
			}

	if (trackAdd == true)
	{
		allTracks.add(trk);
	}
	
}

public AudioTrack[] getTracks()
{
	if (allTracks.size() > 0  )
	{
		AudioTrack[] trk = new AudioTrack[allTracks.size()]; 
		for (int index = 0; index < allTracks.size(); index++)
		{
			 trk[index] = allTracks.get(index);
		}
  return trk;
    }
   return null;
}



}





