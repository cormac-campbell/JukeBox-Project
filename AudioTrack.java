package part01;

public class AudioTrack implements ITrack
{
private int trackCode;
private static int nextCode = 0;
private String title;
private String artist;
private int duration;
private Genre style;
private String encoding;
private int playCount;

public AudioTrack(String title, String artist, int duration, Genre style, String encoding, int playCount)
{
    setTitle(title);
    setArtist(artist);
    setDuration(duration);
    setStyle(style);
    setEncoding(encoding);
	this.trackCode = trackCode;
    trackCode = nextCode;
	useNextCode();
	setPlayCount(playCount);
}

public AudioTrack()
{
  this(null,null,0,Genre.OTHER,null,0);
}

public String getDetails()
{
	String result = "Track Code: " + trackCode + "\nTitle: " + getTitle() +  "\nArtist: " + getArtist() +  "\nDuration: " + getDuration() +  "\nStyle: " + getStyle() +  "\nencoding: " + getEncoding();
	return result;
}
public int getTrackCode()
{
	return this.trackCode;
}
private int useNextCode()
{
	nextCode = trackCode;
	return this.nextCode++ ;
}

public String getTitle()
{
	return this.title;
}

public String getArtist()
{
	return this.artist;
}

public int getDuration()
{
	return this.duration;
}

public String getStyle()
{
	return this.style.toString();
}

public String getEncoding()
{
	return this.encoding;
}

public void setTitle(String title)
{
if (title == null)
{
	this.title = "Unknown title name!";
}
else
{
	this.title = title;
}

}

public void setArtist(String artist)
{
	if (artist == null)
	{
		this.artist = "Unknown Artist Name!";
	}
	else
	{
        this.artist = artist;
	}

}

public void setDuration(int duration)
{
	if (duration < 0)
	{
		this.duration = 0; //Default value 
	}
	else
	{
	this.duration = duration;
	}
}

public void setStyle(Genre style)
	{
		this.style = style;
	}

public void setStyle(char styl)
{
	if (styl == 0)
	{
		styl = 'O';
	}
 String styleChar = String.valueOf(styl);
	switch(styleChar.toUpperCase())
	{
	case "R": setStyle(Genre.ROCK);
	break;
	case "P" : setStyle(Genre.POP);
	break;
	case "D" : setStyle(Genre.DANCE);
	break;
	case "J" : setStyle(Genre.JAZZ);
	break;
	case "C" : setStyle(Genre.CLASSICAL);
	break;
	default : setStyle(Genre.OTHER);
	}
}

public void setEncoding(String encoding)
{

		this.encoding = encoding;

}

//How to find top ten
public int getPlayCount()
{
	return this.playCount;
}

public void setPlayCount(int playCount)
{
	if (playCount<0)
	{
		this.playCount = 0;
	}
	else
	{
	this.playCount = playCount;
	}
}


}
