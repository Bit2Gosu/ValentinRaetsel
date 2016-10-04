import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Matrix implements Cloneable {
	public static final int M = 5;
	public static final int N = 4;
	public static final List<Pair> ROW_COMBINATIONS = Util
			.getCombinations(Matrix.M);

	protected Value[][] values = new Value[M][N];
	protected Map<Integer, List<Value>> valuesPerX = new HashMap<>();
	protected List<List<Integer>> rowXValues = new ArrayList<>(Matrix.M);
	protected List<List<Integer>> rowYValues = new ArrayList<>(Matrix.M);

	protected boolean contains_5_1 = false;
	protected int count_5_0 = 0;

	/**
	 * Creates an emtpy Matrix. All of its values are null.
	 */
	public Matrix() {
		for (Integer x : PossibleValues.ALL_POSSIBLE_VALUES_X_A) {
			valuesPerX.put(x, new ArrayList<>());
		}
		for (int row = 0; row < Matrix.M; row++) {
			rowXValues.add(new ArrayList<>());
			rowYValues.add(new ArrayList<>());
		}
	}

	public void clear(int i, int j) {
		Value oldValue = get(i, j);
		values[i][j] = null;
		if (oldValue != null && !oldValue.isEmpty()) {
			valuesPerX.get(oldValue.getX()).remove(oldValue);
			rowXValues.get(i).remove((Object) oldValue.getX());
			rowYValues.get(i).remove((Object) oldValue.getY());

			if (oldValue.equals(new Value(5, 1))) {
				contains_5_1 = false;
			} else if (oldValue.equals(new Value(5, 0))) {
				count_5_0--;
			}
		}
	}

	public abstract Object clone();

	public Value get(int i, int j) {
		return values[i][j];
	}

	public List<Value> getAllValues() {
		List<Value> values = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			values.addAll(getRowValues(i));
		}

		return values;
	}

	public abstract MatrixBlueprint getBlueprint();

	public abstract Position getLastPositionToComplete();

	public Value[] getRow(int i) {
		return values[i];
	}

	public List<Value> getRowValues(int i) {
		return Arrays.asList(getRow(i));
	}

	public List<Integer> getRowValues(int row, boolean x) {
		if (x) {
			return rowXValues.get(row);
		}
		return rowYValues.get(row);
	}

	public Map<Integer, List<Value>> getValuesPerX() {
		return valuesPerX;
	}

	public boolean isComplete() {
		return !getAllValues().contains(null);
	}

	public boolean isRowComplete(int i) {
		Value[] row = getRow(i);
		for (Value value : row) {
			if (value == null) {
				return false;
			}
		}
		return true;
	}

	public boolean isValid() {
		// 1. b) x = 2, 6, 8, 9, 10, 11 must each occur at least one time in each
		// matrix and exactly three times in A and B together. If it occurs
		// exactly one time in a matrix, it must be paired with y=0. If it
		// occurs two times in one matrix, one must be paired with y = 0 and one
		// with y = 1.

		for (Integer x : Lists.LIST_2_6_8_9_10_11) {
			if (valuesPerX.get(x).isEmpty()) {
				FailStatistics.requirementFailed(Requirement._1b_0);
				return false;
			}
			else if (valuesPerX.get(x).size() == 1) {
				if (valuesPerX.get(x).get(0).getY() != 0) {
					FailStatistics.requirementFailed(Requirement._1b_2);
					return false;
				}
			} else if (valuesPerX.get(x).size() == 2) {
				if ((valuesPerX.get(x).get(0).getY() + valuesPerX.get(x).get(0)
						.getY()) != 1) {

					FailStatistics.requirementFailed(Requirement._1b_3);
					return false;
				}
			}
			else if (valuesPerX.get(x).size() > 2) {
				FailStatistics.requirementFailed(Requirement._1b_1);
				return false;
			}
		}

		// 4. If you pick two rows of one matrix, they must not share more than one value.

		for (Pair pair : Matrix.ROW_COMBINATIONS) {
			if (pair.n1 != pair.n2) {
				if (tooManyEqualXValues(getRowValues(pair.n1, true),
						getRowValues(pair.n2, true), 1)) {
					FailStatistics.requirementFailed(Requirement._4);
					return false;
				}
			}
		}
		
		// 6. If in a row values for y= 0 and 1 are permitted, 0 and 1
		// must occur minimum one time in that row.
		for (int i = 0; i < M; i++) {
			if (isRowComplete(i)) {
				Set<Integer> possibleRowYValues = getBlueprint()
						.getPossibleRowValues(i, false);

				if (possibleRowYValues.containsAll(Lists.LIST_0_1)) {
					List<Integer> setRowYValues = getRowValues(i, false);

					if (!setRowYValues.containsAll(Lists.LIST_0_1)) {
						FailStatistics.requirementFailed(Requirement._6);
						return false;
					}
				}
			}
		}

		return true;

	}

	public void set(int row, int col, Value value) {
		if (!isEmpty(row, col)) {
			Value oldValue = values[row][col];
			if (!oldValue.isEmpty()) {
				valuesPerX.get(oldValue.getX()).remove(oldValue);
				rowXValues.get(row).remove((Object) oldValue.getX());
				rowYValues.get(row).remove((Object) oldValue.getY());

				if (oldValue.equals(new Value(5, 1))) {
					contains_5_1 = false;
				} else if (oldValue.equals(new Value(5, 0))) {
					count_5_0--;
				}
			}
		}

		values[row][col] = value;
		if (value != null && !value.isEmpty()) {
			valuesPerX.get(value.getX()).add(value);
			rowXValues.get(row).add(value.getX());
			rowYValues.get(row).add(value.getY());

			if (value.equals(new Value(5, 1))) {
				contains_5_1 = true;
			} else if (value.equals(new Value(5, 0))) {
				count_5_0++;
			}
		}
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < Matrix.M; i++) {
			for (int j = 0; j < Matrix.N; j++) {
				if (values[i][j] == null) {
					result += "null";
				} else if (values[i][j].isEmpty()) {
					result += " --- ";
				} else {
					result += values[i][j];
				}
				result += "\t";
			}
			result += "\n";
		}

		return result;
	}

	boolean isEmpty(int row, int col) {
		return values[row][col] == null;
	}

	/**
	 * @pre both give matrixes are complete
	 * @param matrixA
	 * @param matrixB
	 * @return
	 */
	public static boolean areValid(Matrix matrixA, Matrix matrixB) {
		Map<Integer, List<Value>> valuesPerXInA = matrixA.getValuesPerX();
		Map<Integer, List<Value>> valuesPerXInB = matrixA.getValuesPerX();
		Map<Integer, List<Value>> valuesPerXInBoth = new HashMap<>();

		for (Integer x : PossibleValues.ALL_POSSIBLE_VALUES_X_A) {
			List<Value> joinedValues = new ArrayList<>(valuesPerXInA.get(x));
			joinedValues.addAll(valuesPerXInB.get(x));
			valuesPerXInBoth.put(x, joinedValues);
		}

		// 1 b) x = 2, 6, 8, 9, 10, 11 must each occur exactly three times in A and B together.

		for (Integer x : Lists.LIST_2_6_8_9_10_11) {
			if (valuesPerXInBoth.get(x).size() != 3) {
				FailStatistics.requirementFailed(Requirement._1b_1);
				return false;
			}
		}

		// 5. If you pick one row of a matrix and one row of the other matrix, these rows must not share more than two x-values.

		for (Pair pair : Matrix.ROW_COMBINATIONS) {
			if (tooManyEqualXValues(matrixA.getRowValues(pair.n1, true),
					matrixB.getRowValues(pair.n2, true), 2)) {
				FailStatistics.requirementFailed(Requirement._5);
				return false;
			}
		}

		return true;
	}

	public static boolean tooManyEqualXValues(List<Integer> row1XValues,
			List<Integer> row2XValues, int maxEqualCount) {
		int equalCount = 0;
		for (Integer row1X : row1XValues) {
			if (row2XValues.contains(row1X)) {
				equalCount++;
				if (equalCount > maxEqualCount) {
					return true;
				}
			}
		}

		return false;
	}
}
