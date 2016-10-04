import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PossibleValues
{
	public static final List<Integer> ALL_POSSIBLE_VALUES_X_A = Arrays.asList(11, 10, 9, 8, 6, 2, 7, 4, 3, 5, 1); // groups are: (8, 9), (6, 2, 7), (4, 3, 5)
	public static final List<Integer> ALL_POSSIBLE_VALUES_Y_A = Arrays.asList(1, 0);
	
	public static final List<Integer> ALL_POSSIBLE_VALUES_X_B = Arrays.asList(10, 8, 11, 6, 2, 4, 3, 5, 9, 7, 1); // groups are (2, 4, 3, 5), (9, 7, 1)
	public static final List<Integer> ALL_POSSIBLE_VALUES_Y_B = Arrays.asList(1, 0);

	private List<Integer> possibleXValues;
	private List<Integer> possibleYValues;
	private List<Value> impossibleValues;
	private List<Value> possibleValues;

	private PossibleValues()
	{

	}

	public static PossibleValues getForEmptyField()
	{
		PossibleValues result = new PossibleValues();
		result.possibleXValues = Collections.EMPTY_LIST;
		result.possibleYValues = Collections.EMPTY_LIST;
		result.possibleValues = Arrays.asList(Value.EMPTY);
		result.impossibleValues = Collections.EMPTY_LIST;
		return result;
	}

	public static PossibleValues getAllPossibleValues(boolean a)
	{
		PossibleValues result = new PossibleValues();
		if (a) {
			result.possibleXValues = new ArrayList<>(ALL_POSSIBLE_VALUES_X_A);
			result.possibleYValues = new ArrayList<>(ALL_POSSIBLE_VALUES_Y_A);
		}
		else {
			result.possibleXValues = new ArrayList<>(ALL_POSSIBLE_VALUES_X_B);
			result.possibleYValues = new ArrayList<>(ALL_POSSIBLE_VALUES_Y_B);
		}
		result.impossibleValues = Collections.EMPTY_LIST;
		result.calculatePossibleValues();

		return result;
	}

	public static PossibleValues getPossibleValue(int x, int y)
	{
		PossibleValues result = new PossibleValues();
		result.possibleXValues = Arrays.asList(x);
		result.possibleYValues = Arrays.asList(y);
		result.impossibleValues = Collections.EMPTY_LIST;
		result.calculatePossibleValues();
		return result;
	}

	public static PossibleValues getPossibleValues(List<Integer> possibleValuesX, List<Integer> possibleValuesY, boolean a)
	{
		PossibleValues result = new PossibleValues();
		result.possibleXValues = possibleValuesX;
		result.possibleYValues = possibleValuesY;
		result.impossibleValues = Collections.EMPTY_LIST;
		result.calculatePossibleValues(a);
		return result;
	}

	public static PossibleValues getForImpossibleValue(Value impossibleTuple, boolean a)
	{
		PossibleValues result = new PossibleValues();
		if (a) {
			result.possibleXValues = new ArrayList<>(ALL_POSSIBLE_VALUES_X_A);
			result.possibleYValues = new ArrayList<>(ALL_POSSIBLE_VALUES_Y_A);
		}
		else {
			result.possibleXValues = new ArrayList<>(ALL_POSSIBLE_VALUES_X_B);
			result.possibleYValues = new ArrayList<>(ALL_POSSIBLE_VALUES_Y_B);
		}
		result.impossibleValues = new ArrayList<>();
		result.impossibleValues.add(impossibleTuple);
		result.calculatePossibleValues();

		return result;
	}

	public List<Integer> getPossibleValues(boolean x)
	{
		return x ? possibleXValues : possibleYValues;
	}

	public List<Integer> getPossibleYValues()
	{
		return possibleYValues;
	}

	public List<Value> asList()
	{
		return possibleValues;
	}

	private void calculatePossibleValues()
	{
		possibleValues = new ArrayList<>();
		for (int i = 0; i < possibleXValues.size(); i++)
		{
			int possibleXValue = possibleXValues.get(i);

			for (int j = 0; j < possibleYValues.size(); j++)
			{
				int possibleYValue = possibleYValues.get(j);
				Value tuple = new Value(possibleXValue, possibleYValue);
				if (!impossibleValues.contains(tuple))
				{
					possibleValues.add(tuple);
				}
			}
		}
	}

	private void calculatePossibleValues(boolean a)
	{
		calculatePossibleValues();
	}

	// private void calculatePossibleValues(boolean a)
	// {
	// List<Integer> possibleXValuesMod;
	// if (a)
	// {
	// possibleXValuesMod = possibleXValues;
	// }
	// else
	// {
	// possibleXValuesMod = Arrays.asList(1, 3, 4, 7, 9, 11, 2, 5, 6, 8, 10);
	// }
	//
	// possibleValues = new ArrayList<>();
	// for (int possibleXValue : possibleXValuesMod)
	// {
	// for (int j = 0; j < possibleYValues.size(); j++)
	// {
	// int possibleYValue = possibleYValues.get(j);
	// Value tuple = new Value(possibleXValue, possibleYValue);
	// if (!impossibleValues.contains(tuple))
	// {
	// possibleValues.add(tuple);
	// }
	// }
	// }
	// }
}
