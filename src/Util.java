import java.util.ArrayList;
import java.util.List;

public class Util
{
	public static List<Pair> getCombinations(int count)
	{
		List<Pair> combinations = new ArrayList<>();

		for (int i = 0; i < count; i++)
		{
			for (int i2 = 0; i2 < count; i2++)
			{
				Pair pair = new Pair(i, i2);
				if (!combinations.contains(pair))
				{
					combinations.add(pair);
				}
			}
		}

		return combinations;
	}
}
