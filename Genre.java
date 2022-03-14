package part01;

public enum Genre 
{
ROCK(1), POP(2), DANCE(3), JAZZ(4), CLASSICAL(5), OTHER(6);
	
	private int genreValue;
	private String genreNames[] = {"Rock and Roll", "Easy Listening Pop", "Techno Dance", "Smooth Jazz", "Classical", "Unknown Genre"};
	
	private Genre(int i)
	{
		genreValue = i;
	}
	
	public int getGenre()
	{
		return genreValue;
	}
	
	public String toString()
	{
		return genreNames[genreValue - 1];
	}
	

}
