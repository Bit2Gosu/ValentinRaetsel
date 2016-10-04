import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MatrixBlueprint {
	private PossibleValues[][] possibleBlueprintValues = new PossibleValues[Matrix.M][Matrix.N];
	private List<Set<Integer>> possibleXValuesPerRow = new ArrayList<>(Matrix.M);
	private List<Set<Integer>> possibleYValuesPerRow = new ArrayList<>(Matrix.M);
	private boolean a;

	public MatrixBlueprint(boolean a) {
		this.a = a;

		List<Integer> possibleValuesX;
		if (a) {
			possibleValuesX = Arrays.asList(6, 2, 4, 3, 5, 1);
			possibleBlueprintValues[0][0] = PossibleValues.getPossibleValue(1,
					0);
			possibleBlueprintValues[0][1] = PossibleValues.getPossibleValues(
					possibleValuesX, Arrays.asList(0), true);
			possibleBlueprintValues[0][2] = PossibleValues.getPossibleValues(
					possibleValuesX, Arrays.asList(0), true);
			possibleBlueprintValues[0][3] = PossibleValues.getPossibleValues(
					possibleValuesX, Arrays.asList(0), true);

			possibleValuesX = Arrays.asList(6, 2, 7, 4, 3, 5, 1);

			possibleBlueprintValues[1][0] = PossibleValues.getPossibleValues(
					possibleValuesX, Arrays.asList(0), true);
			possibleBlueprintValues[1][1] = PossibleValues.getPossibleValues(
					possibleValuesX, Arrays.asList(0), true);
			possibleBlueprintValues[1][2] = PossibleValues.getPossibleValues(
					possibleValuesX, Arrays.asList(0), true);
			possibleBlueprintValues[1][3] = PossibleValues.getPossibleValues(
					possibleValuesX, Arrays.asList(0), true);

			possibleBlueprintValues[2][0] = PossibleValues.getPossibleValue(1,
					0);
			possibleBlueprintValues[2][1] = PossibleValues.getPossibleValue(7,
					0);
			possibleBlueprintValues[2][2] = PossibleValues.getPossibleValue(8,
					0);
			possibleBlueprintValues[2][3] = PossibleValues.getPossibleValue(9,
					0);

			possibleValuesX = Arrays.asList(10, 8, 6, 2, 7, 4, 3, 5, 1);
			possibleBlueprintValues[3][0] = PossibleValues.getPossibleValues(
					possibleValuesX, Arrays.asList(0), true);

			possibleValuesX = Arrays.asList(9, 8, 7, 4, 3, 5, 1);
			possibleBlueprintValues[3][1] = PossibleValues.getPossibleValues(
					possibleValuesX, Arrays.asList(0), true);

			possibleValuesX = Arrays.asList(10, 9, 7, 4, 3, 5, 1);
			possibleBlueprintValues[3][2] = PossibleValues.getPossibleValues(
					possibleValuesX, Arrays.asList(0), true);

			possibleBlueprintValues[3][3] = PossibleValues
					.getForImpossibleValue(new Value(10, 1), true);

			possibleBlueprintValues[4][0] = PossibleValues
					.getAllPossibleValues(true);
			possibleBlueprintValues[4][1] = PossibleValues
					.getAllPossibleValues(true);
			possibleBlueprintValues[4][2] = PossibleValues
					.getAllPossibleValues(true);
			possibleBlueprintValues[4][3] = PossibleValues
					.getAllPossibleValues(true);
		} else {
			possibleValuesX = Arrays.asList(11, 6, 2, 4, 3, 5, 9, 7, 1);
			possibleBlueprintValues[0][0] = PossibleValues.getPossibleValues(
					possibleValuesX, PossibleValues.ALL_POSSIBLE_VALUES_Y_B,
					false);
			possibleBlueprintValues[0][1] = PossibleValues.getPossibleValues(
					possibleValuesX, PossibleValues.ALL_POSSIBLE_VALUES_Y_B,
					false);
			possibleBlueprintValues[0][2] = PossibleValues.getPossibleValues(
					possibleValuesX, PossibleValues.ALL_POSSIBLE_VALUES_Y_B,
					false);
			possibleBlueprintValues[0][3] = PossibleValues.getForEmptyField();

			possibleBlueprintValues[1][0] = PossibleValues.getPossibleValues(
					possibleValuesX, PossibleValues.ALL_POSSIBLE_VALUES_Y_B,
					false);
			possibleBlueprintValues[1][1] = PossibleValues.getPossibleValues(
					possibleValuesX, PossibleValues.ALL_POSSIBLE_VALUES_Y_B,
					false);
			possibleBlueprintValues[1][2] = PossibleValues.getPossibleValues(
					possibleValuesX, PossibleValues.ALL_POSSIBLE_VALUES_Y_B,
					false);
			possibleBlueprintValues[1][3] = PossibleValues.getPossibleValues(
					possibleValuesX, PossibleValues.ALL_POSSIBLE_VALUES_Y_B,
					false);

			possibleValuesX = Arrays.asList(8, 6, 2, 4, 3, 5, 9, 7, 1);
			possibleBlueprintValues[2][0] = PossibleValues.getPossibleValues(
					possibleValuesX, PossibleValues.ALL_POSSIBLE_VALUES_Y_B,
					false);
			possibleBlueprintValues[2][1] = PossibleValues.getPossibleValues(
					possibleValuesX, PossibleValues.ALL_POSSIBLE_VALUES_Y_B,
					false);
			possibleBlueprintValues[2][2] = PossibleValues.getPossibleValues(
					possibleValuesX, PossibleValues.ALL_POSSIBLE_VALUES_Y_B,
					false);
			possibleBlueprintValues[2][3] = PossibleValues.getPossibleValues(
					possibleValuesX, PossibleValues.ALL_POSSIBLE_VALUES_Y_B,
					false);

			possibleBlueprintValues[3][0] = PossibleValues
					.getPossibleValues(Arrays.asList(2, 4, 3, 5, 9, 7, 1),
							Arrays.asList(0), false);

			possibleBlueprintValues[3][1] = PossibleValues.getPossibleValues(
					Arrays.asList(9, 7, 1),
					PossibleValues.ALL_POSSIBLE_VALUES_Y_B, false);

			possibleValuesX = Arrays.asList(8, 11, 6, 2, 4, 3, 5, 9, 7, 1);
			possibleBlueprintValues[3][2] = PossibleValues.getPossibleValues(
					possibleValuesX, PossibleValues.ALL_POSSIBLE_VALUES_Y_B,
					false);
			possibleBlueprintValues[3][3] = PossibleValues.getPossibleValues(
					possibleValuesX, PossibleValues.ALL_POSSIBLE_VALUES_Y_B,
					false);

			possibleBlueprintValues[4][0] = PossibleValues.getPossibleValues(
					PossibleValues.ALL_POSSIBLE_VALUES_X_B, Arrays.asList(1),
					false);
			possibleBlueprintValues[4][1] = PossibleValues
					.getAllPossibleValues(false);
			possibleBlueprintValues[4][2] = PossibleValues.getForEmptyField();
			possibleBlueprintValues[4][3] = PossibleValues.getForEmptyField();
		}

		for (int row = 0; row < Matrix.M; row++) {
			HashSet<Integer> possibleXValues = new HashSet<>();
			HashSet<Integer> possibleYValues = new HashSet<>();

			for (int col = 0; col < Matrix.N; col++) {
				possibleXValues.addAll(getPossibleValues(row, col, true));
				possibleYValues.addAll(getPossibleValues(row, col, false));
			}

			possibleXValuesPerRow.add(row, possibleXValues);
			possibleYValuesPerRow.add(row, possibleYValues);
		}

	}

	public Set<Integer> getPossibleRowValues(int row, boolean x) {
		return x ? possibleXValuesPerRow.get(row) : possibleYValuesPerRow
				.get(row);
	}

	public List<Integer> getPossibleValues(int row, int col, boolean x) {
		return possibleBlueprintValues[row][col].getPossibleValues(x);
	}

	public List<Value> getPossibleValues(Matrix matrix, int row, int col) {
		List<Value> possibleValues = possibleBlueprintValues[row][col].asList();
		List<Integer> rowXValues = matrix.getRowValues(row, true);
		
		// 2. In each row, no value of x may be the same
		List<Value> impossibleValues = new ArrayList<>();
		for (Value possibleValue : possibleValues) {
			if (rowXValues.contains(possibleValue.getX())) {
				impossibleValues.add(possibleValue);
			}				
		}		
		possibleValues.removeAll(impossibleValues);

		// preparation for rule 3...
		int rule3Count = 0;
		for (int rowXValue : rowXValues) {
			if (Lists.LIST_7_8_9_10.contains(rowXValue)) {
				rule3Count++;
			}
		}
		boolean ourFieldIsLastPossibility = true;
		if (rule3Count == 0) {

			colLoop: for (int j = col + 1; j < Matrix.N; j++) {
				List<Value> possibleFieldValues = possibleBlueprintValues[row][j]
						.asList();
				for (Value possibleFieldValue : possibleFieldValues) {
					if (!possibleFieldValue.isEmpty()) {
						if (Lists.LIST_7_8_9_10.contains(possibleFieldValue
								.getX())) {
							ourFieldIsLastPossibility = false;
							break colLoop;
						}
					}
				}
			}
		}

		if (rule3Count == 0 && ourFieldIsLastPossibility) {
			boolean oneIsPossible = false;
			
			for (Value possibleValue : possibleValues) {
				if (Lists.LIST_7_8_9_10.contains(possibleValue.getX())) {
					oneIsPossible = true;
					break;
				}
			}
			
			if (!oneIsPossible) {
				return new ArrayList<>();
			}
		}

		List<Value> result = new ArrayList<>(possibleValues.size());
		for (Value possibleValue : possibleValues) {

			if (possibleValue.isEmpty()) {
				result.add(possibleValue);
			} else {
				// 3.Part 1) If in a row values for x= 7, 8, 9 or 10 are
				// permitted, it may contain maximum three of these numbers.
				if (rule3Count < 3
						|| !Lists.LIST_7_8_9_10.contains(possibleValue.getX())) {

					// 3.Part 2) If in a row values for x= 7, 8, 9 or 10 are
					// permitted, it must contain minimum on of these numbers.
					if (rule3Count > 0
							|| !ourFieldIsLastPossibility
							|| Lists.LIST_7_8_9_10.contains(possibleValue
									.getX())) {

						result.add(possibleValue);
					}
				}
			}
		}

		return result;
	}

	public PossibleValues[] getRow(int i) {
		return possibleBlueprintValues[i];
	}

	public boolean isA() {
		return a;
	}
}
