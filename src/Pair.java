public class Pair
{
	int n1;
	int n2;

	public Pair(int x, int y)
	{
		this.n1 = x;
		this.n2 = y;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Pair))
		{
			return false;
		}

		Pair other = (Pair) obj;
		return (other.n1 == n1 && other.n2 == n2) || (other.n1 == n2 && other.n2 == n1);
	}

	@Override
	public int hashCode()
	{
		if (n1 < n2)
		{
			return n1 * 10 + n2;
		}
		return n2 * 10 + n1;
	}
}
