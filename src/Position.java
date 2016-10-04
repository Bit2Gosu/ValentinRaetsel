public final class Position
{
	private final int i;
	private final int j;

	public Position(int i, int j)
	{
		this.i = i;
		this.j = j;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Position))
		{
			return false;
		}

		Position other = (Position) obj;
		return other.i == i && other.j == j;
	}

	@Override
	public int hashCode()
	{
		return i + 10 * j;
	}

	@Override
	public String toString()
	{
		return "i: " + i + "; j: " + j;
	}

	public int getI()
	{
		return i;
	}

	public int getJ()
	{
		return j;
	}

}
