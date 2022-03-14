package part02 ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import part01.AudioPlayer;
import part01.AudioTrack;
import part01.Genre;
import part01.JukeBox;



public class Tester 
{
	
	//Defining the Menu
	
	
	// Define a constant PROMPT
		static final String PROMPT = "---->";
		// Define a constant SPACES
		static final String SPACES = " ";
		// String array of menu options
		static final String options[] = {"Display All Tracks","Display Tracks by Artist"
		                                 ,"Add New Track","Play a Track","Create a Playlist",
		                                 "Shuffle Play","Display the Top ten","Exit"};
		// Define a constant QUIT
		static final int QUIT = options.length;
		
		// A menu title
		static String title = "Jukebox";
	
		// Define a menu using title & options
		static Menu myMenu = new Menu(title, options);
	
		// Define a Scanner
		static Scanner input = new Scanner(System.in);
		
		// Define a value 'count' to indicate number of objects in array
		static int count = 0; // 0 to start with
	   // static final int MAX = 100;
		// Create an array capable of managing up to MAX track instances
		static AudioTrack[] tracks = new AudioTrack[count];
		static AudioPlayer ap = new AudioPlayer(null);
		static JukeBox jkb;// = new JukeBox(ap);
		static MP3Player mp = new MP3Player(null);
		static WavPlayer wv = new WavPlayer(null);
		
		// play list variables 
		static String playlistNames;
		static ArrayList<AudioTrack> playListTrk1; 
	
		//Top ten count 
		static ArrayList<AudioTrack> topTenCount;
		private static String csvInPath = "C:\\Users\\corma\\OneDrive\\Documents\\All Tracks/AllTracks.txt";
		private static String csvOutPath = "C:\\Users\\corma\\OneDrive\\Documents\\All Tracks/AllTracks.txt";
		private static ArrayList<AudioTrack> allTracks  = new ArrayList<AudioTrack>();
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		

		System.out.println("Choose MP3 or Wav for the Jukebox Construction");
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
		String playType = input.nextLine();
		playType = playType.toUpperCase();
		boolean done = false;
		do
		{
		if (!playType.equals("WAV") && !playType.equals("MP3"))
		{
			System.out.println("Invalid Style - Type either Wav or MP3 in.");
			System.out.println("******************************************");
			System.out.print(PROMPT + "Enter Encodeing: ");
			playType = input.nextLine();
			playType = playType.toUpperCase();
		}
		else
		{
			done = true;
		}
		}while(done==false);
		if (playType.equals("WAV"))
		{
			title = "Wav Jukebox";
		    jkb = new JukeBox(wv);
	
		}
		else
		{
			title = "MP3 Jukebox";
			jkb = new JukeBox(mp);
		}
		
		
	    allTracks.addAll(importAllTracks(csvInPath, true));
		for (AudioTrack trk: allTracks) 
	   {
			jkb.addTrack(trk);
	   }

		
int choice;
		
		do
		{
			myMenu.display();
			choice = myMenu.getUserChoice();
			if (choice != QUIT)
			{
				processChoice(choice);
			}
		} while(choice != QUIT);
        input.close();
		setAllTracks(csvOutPath, allTracks);
		System.out.println("\n GoodBye!");
	}

	private static void processChoice(int choice)
	{
	 
		switch(choice) {
		case 1 : displayAllTracks();
		break;
		case 2 : displayArtist();
		break;
		case 3 : addAudioTrack();
		break;
		case 4 : playTrack();
		break;
		case 5 : addToPlaylist();
		break;
		case 6 :  if (playListTrk1 != null)
		{
		System.out.println(jkb.shuffle(playListTrk1));
		}
		else
		{
			System.out.println("Playlist not made!");
		}

		break;
		case 7 :  displayTopTen();
		break;
		case 8:  choice = QUIT;
		break;
		default:System.out.println("Option "+choice+" is invalid.");
		}
		System.out.println();
	}
	
	private static void addAudioTrack()
	{
	
		System.out.println("\nOk - Add new Track.");
		// get Track info
		
		AudioTrack tr = new AudioTrack();
		
		System.out.print(PROMPT + "Enter Title: ");
		String title = input.nextLine();
		do
		{
		if (title.equals(""))
		{
			System.out.println("Invalid Title - Type song name in");
			System.out.println("*********************************");
			System.out.print(PROMPT + "Enter Title: ");
			title = input.nextLine();
		}
		}while(title.equals(""));
		title = title.toLowerCase();
		tr.setTitle(title);
		
		System.out.print(PROMPT + "Enter Artist: ");
		String artist = input.nextLine();
		do
		{
		if (artist.equals(""))
		{
			System.out.println("Invalid Artist - Type Artists name in");
			System.out.println("*********************************");
			System.out.print(PROMPT + "Enter Artist: ");
			artist = input.nextLine();
		}
		}while(artist.equals(""));
		artist =artist.toLowerCase();
		tr.setArtist(artist);
		
		System.out.print(PROMPT + "Enter Duration(in minutes): ");
		String duration = input.nextLine();
		boolean done = false;
		do
		{
		try
		{
			 int d = Integer.parseInt(duration);
			 done = true;
		}
		catch (Exception ex)
		{
			done = false;
			System.out.println("Invalid Duration - Type number in.");
			System.out.println("**********************************");
			System.out.print(PROMPT + "Enter Duration(in minutes): ");
			duration = input.nextLine();
		}
		
		}while(done == false);
        
		tr.setDuration(Integer.parseInt(duration));
		
		
		System.out.print( "Types of Genre\n************** \n -P for Pop\n -R for Rock\n -D for Dance\n -J for Jazz\n -C for Classical\n -O for other \n*****************\n" + PROMPT + "Enter Genre:");
		String style = input.nextLine();
		style = style.toUpperCase();
		done = false;
		do 
		{
			if (!style.equals("P") && !style.equals("R") &&!style.equals("D") &&!style.equals("J") &&!style.equals("C") &&!style.equals("O"))
			{
				System.out.println("Invalid Style - Type genre code in.");
				System.out.println("***********************************");
				System.out.print(PROMPT + "Enter Genre: ");
				style = input.nextLine();
				style = style.toUpperCase();
			}
			else 
			{
				done = true;
			}
		}while(done == false);
		
		tr.setStyle(style.charAt(0));
	
		System.out.print(PROMPT + "Enter Encoding (MP3 or Wav): ");
		String encoding = input.nextLine();
		encoding = encoding.toUpperCase();
		do
		{
			done = false;
		if (!encoding.equals("WAV") && !encoding.equals("MP3"))
		{
			System.out.println("Invalid Style - Type either Wav or MP3 in.");
			System.out.println("******************************************");
			System.out.print(PROMPT + "Enter Encodeing: ");
			encoding = input.nextLine();
			encoding = encoding.toUpperCase();
		}
		else
		{
			done = true;
		}
		}while(done==false);
		tr.setEncoding(encoding);
		
		//checking if song has already been added
		boolean trackAdd = true;
		if(jkb.getTracks() != null)
		{
		for (AudioTrack obj : jkb.getTracks())
		{
				
		if (obj.getTitle().equalsIgnoreCase(tr.getTitle()) && obj.getArtist().equalsIgnoreCase(tr.getArtist()) )
			{
				System.out.println("Track has already been added to system.");
				trackAdd = false;
			}
		}
		}
		if(trackAdd == true)
		{
		// add object to array 
		//tracks[count] = tr; 
		//increment count 			
		count++;
		jkb.addTrack(tr);
		allTracks.add(tr);
		}
		
	}
	
	private static void displayAllTracks()
	{
		
		System.out.println("\nOk - List all Tracks.");
	if (jkb.getTracks() == null )
		{
			System.out.println(PROMPT  + "Sorry, there are no Tracks available. ");
		}
		else
	    {
			System.out.println("We have the following Tracks:");
			 sortAlphabetically(jkb.getTracks());
			int index = 1;
			for (AudioTrack trk : tracks)
		{
				System.out.println(SPACES+index+ ". " + trk.getTitle());
				index++;
		}

		}
	}
	
	private static void displayTopTen()
	{
		int Number = 1;
		sortNumerically(jkb.getTracks());
		System.out.println("Top Ten Played Tracks");
		System.out.println("*********************");
		for (AudioTrack trk : tracks)
		{
			if (jkb.getTracks() != null)
			{
				System.out.println(SPACES + Number + ". " + trk.getTitle());
				Number++;
			}
		}
    }
	
	private static void displayArtist()
	{
		AudioTrack previousArtist = new AudioTrack();
		System.out.println("\nOk - List all Artists.");
		if (jkb.getTracks() == null)
		{
			System.out.println(PROMPT  + "Sorry, there are no Artists available. ");
		}
		else
		{
			System.out.println("We have the following Artits:");
			sortAlphabetically(jkb.getTracks());
		    int index =0 ;
			for (AudioTrack trk : tracks)
			{
				if (index != 0 )
				{
					if (trk.getArtist().equals(previousArtist.getArtist()));
				else
					{
						System.out.println(SPACES+"-" + trk.getArtist());
					}
		        }
				else
				{
				System.out.println(SPACES+ "-" + trk.getArtist());
				}
			    index++;
			    previousArtist = trk;
			}
			
		}
		
		
		// Allow user enter which artist songs they want to view 
		
		System.out.print(PROMPT + "Enter Artist: ");
		
		String target = input.nextLine();
		
		displayArtistSongs(target);
		
	}
	
	private static void displayArtistSongs(String target)
	{
		int Number = 1;
		for (AudioTrack trk : jkb.getTracks())
		{
			if (trk.getArtist().equalsIgnoreCase(target))
			{
				System.out.println(SPACES + Number + ". " + trk.getTitle());
				Number++;
			}
		}
		
	}
	
	private static void playTrack()
	{
	//	AudioTrack[] playTracks = new AudioTrack[jkb.getTracks().length];
		String song = "";
		String target = "";
		System.out.println("Choose from available Playlists or All Tracks");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		// displaying PlayList Names
		displayPlaylistsNames();
	    System.out.println("* All Tracks");
		target = input.nextLine();
		boolean play = false;
	
		if (playListTrk1 != null && playlistNames.equals(target))
		{
	        if(playlistNames.equalsIgnoreCase(target))
			{
			displayPlaylist(target);
			}		
		}
	        else if (target.equalsIgnoreCase("All Tracks"))
			{
				displayAllTracks();
		
			}
			else
			{
				System.out.println("Invalid option!");
			
			}

	System.out.println("Choose a song from above to play.");
	System.out.println("+++++++++++++++++++++++++++++++++");
	song = input.nextLine();
	
	for (AudioTrack obj : jkb.getTracks())
	{
		if (obj.getTitle().equalsIgnoreCase(song))
		{
			if (jkb.playTrack(obj) == true)
	        {
			play = true;
			obj.setPlayCount(obj.getPlayCount()+1);
			}
		}
	}

		if (play == true)
        {
		System.out.println("Audio Track playing");
		}
	    else
	    {
	    	System.out.println("Audio Track cannot be played");
	    }

	
}
	
	private static void addToPlaylist()
	{
		System.out.println("Enter Playlist Name");
		System.out.println("+++++++++++++++++++");
		playlistNames = input.nextLine();
		System.out.println(playlistNames);
		String song = "";
		AudioTrack addedTrack = new AudioTrack();
	    boolean add = false;
		displayAllTracks();

		// adding the song
		
		System.out.println("Enter Song Name to add to playlist");
		System.out.println("++++++++++++++++++++++++++++++++++");
		System.out.println("Type Exit to finish creasting playlist");
		System.out.println("++++++++++++++++++++++++++++++++++++++");
	
		
		//adding play list one
		 playListTrk1 = new ArrayList<AudioTrack>();
		 while ( !song.equalsIgnoreCase("Exit"))
		{
		     song = input.nextLine();
		     
			for (AudioTrack obj : jkb.getTracks())
			{
					
			if (obj.getTitle().equalsIgnoreCase(song))
				{
					add = true;
					addedTrack = obj;
				}
			}
	if (playListTrk1 != null)
	{
			for (AudioTrack str : playListTrk1)
			{
				if (str.getTitle().equalsIgnoreCase(song))
				{
				add = false;
				}
			}
	}

	    if(song.equalsIgnoreCase("exit"))
			{
			System.out.println("Exiting playlist");
			break;
	     	}	
			
		if (add == true)
			{
			playListTrk1.add(addedTrack);
			System.out.println("Song added");
			}
		else
			{
			System.out.println("Song not added. Not included in all tracks.");
			}
			add = false;
		} 

	}
	
	private static void displayPlaylist(String target)
	{
	
	if(playlistNames.equalsIgnoreCase(target))
	{
	 	for (AudioTrack str : playListTrk1)
	      { 		      
	           System.out.println(str.getTitle()); 		
	      }
	}
	
	
    }
	
	private static void displayPlaylistsNames()
	{
		// displaying PlayList Names
					if ( playlistNames != null )
					{
						System.out.println( "* " + playlistNames);
					}
				
				
	}
	
	private static void sortAlphabetically(AudioTrack[] trk)
	{
		
		AudioTrack temp = null;
	    for (int pass = 0; pass < (count-1); pass++)
	    {
	        for (int c = 0; c < (count - 1); c++)
	        {
	            if  (trk[c].getArtist().compareTo(trk[c+1].getArtist()) > 0 )
	            {
	                temp = trk[c];
	                trk[c]= trk[c+1];
	                trk[c+1]=temp;
	            }
	        }
	    }
		tracks = trk;
		}
	
	private static void sortNumerically(AudioTrack[] trk)
	{
		AudioTrack temp = null;
	    for (int pass = 0; pass < (count - 1); pass++)
	    {
	        for (int c = 0; c < (count - 1); c++)
	        {
	            if  (trk[c].getPlayCount() < trk[c+1].getPlayCount())
	            {
	                temp = trk[c];
	                trk[c]= trk[c+1];
	                trk[c+1]=temp;
	            }
	        }
	    }
		tracks = trk;
	}

	private static ArrayList<AudioTrack> importAllTracks(String csvFilePath, boolean hasHeader)
	{
		allTracks = new ArrayList<AudioTrack>();
		
		 File myFile; 
        Scanner mySc;   
		try
		{
	    myFile = new File(csvFilePath);
		mySc = new Scanner(myFile);  
	  	 if(hasHeader)
	  	 {
		      mySc.nextLine();
		 }
	  	while(mySc.hasNextLine())
	  	{
		      String record = mySc.nextLine();
		      String[] parts = record.split(",");			

		      String title = parts[0].trim();
		      String artist = parts[1].trim();
		      int duration = Integer.parseInt(parts[2].trim());
		      String style = parts[3].trim();
		      String encoding = parts[4].trim();
		      int playCount = Integer.parseInt(parts[5].trim());
		      
		      
		      AudioTrack trk = new AudioTrack();
		      trk.setTitle(title);
		      trk.setArtist(artist);
		      trk.setDuration(duration);
		      trk.setEncoding(encoding);
		      trk.setStyle(style.charAt(0));
		      trk.setPlayCount(playCount);
		    //  jkb.addTrack(trk);
		      allTracks.add(trk);
		      jkb.addTrack(trk);
	  	}
		}
	  	catch(FileNotFoundException e)
	  	{
	 	   e.printStackTrace();
		   //A null object is returned in the event of any errors. 
		   return null;
		}		
		 mySc.close();
		return allTracks;
		
	}
	
	private static void setAllTracks(String csvOutPath, ArrayList<AudioTrack> allTracks)
	{
		try
		{
		PrintWriter myPw = new PrintWriter(csvOutPath);
		myPw.println("Title, Artist, Duration, Encoding, Style, playCount");
		
		for (AudioTrack trk : allTracks)
		{
		   // myPw = new PrintWriter(csvOutPath);
			myPw.print(trk.getTitle() + ",");
			myPw.print(trk.getArtist() + ",");
			myPw.print(trk.getDuration() + ",");
		
			char style = 'O';
		
				if(trk.getStyle().equals( "Rock and Roll") )
				{
					  style = 'R';
				}
				else if(trk.getStyle().equals( "Easy Listening Pop"))
				{
                     style = 'P';
				}
				else if(trk.getStyle().equals( "Techno Dance"))
				{
					style = 'D';
				}
				else if(trk.getStyle().equals( "Smooth Jazz"))
				{
					style = 'J';
				}
				else if(trk.getStyle().equals("Classical"))
				{
					style = 'C';
				}
				else if(trk.getStyle().equals("Unknown Genre"))
				{
					style = 'O';
				}	
			
			myPw.print(style + ",");
			myPw.print(trk.getEncoding() + ",");
			myPw.print(trk.getPlayCount());
			myPw.println();
		}
		System.out.println("all tracks Data Output Complete.");
		myPw.close();
		}
		catch(FileNotFoundException e) 
		{
			e.printStackTrace();
		}

	}
}
