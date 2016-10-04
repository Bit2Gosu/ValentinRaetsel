import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MatrixA extends Matrix
{
	public static final MatrixBlueprint BLUEPRINT = new MatrixBlueprint(true);

	@Override
	public boolean isValid()
	{
		if (!super.isValid())
		{
			return false;
		}

		if (isComplete())
		{
			// 1. c) In A, (5,0) must occur exactly two times.
			if (Collections.frequency(getAllValues(), new Value(5, 0)) != 2) {
				FailStatistics.requirementFailed(Requirement._1c_0);
				return false;
			}
			
			// 1. a) The values for x = 1, 3, 4 and 7 must occur in A exactly two times. y must be 0 in each case.

			Map<Integer, List<Value>> valuesPerX = getValuesPerX();

			for (Integer x : valuesPerX.keySet())
			{
				if (Lists.LIST_1_3_4_7.contains(x))
				{
					List<Value> values = valuesPerX.get(x);
					if (values.size() != 2)
					{
						FailStatistics.requirementFailed(Requirement._1a_0_0);
						return false;
					}
					if ((values.get(0).getY() + values.get(1).getY()) != 0)
					{
						FailStatistics.requirementFailed(Requirement._1a_0_1);
						return false;
					}
				}
			}
		}

		return true;
	}

	@Override
	public Object clone()
	{
		MatrixA clone = new MatrixA();
		clone.valuesPerX = valuesPerX;
		clone.rowXValues = rowXValues;
		clone.rowYValues = rowYValues;
		clone.contains_5_1 = contains_5_1;
		clone.count_5_0 = count_5_0;
		clone.values = new Value[M][N];
		for (int i = 0; i < M; i++)
		{
			for (int j = 0; j < N; j++)
			{
				clone.values[i][j] = values[i][j];
			}
		}
		return clone;
	}

	@Override
	public MatrixBlueprint getBlueprint()
	{
		return MatrixA.BLUEPRINT;
	}

	@Override
	public Position getLastPositionToComplete()
	{
		return new Position(M - 1, N - 1);
	}

}
