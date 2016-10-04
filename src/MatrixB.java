import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MatrixB extends Matrix {
	public static final MatrixBlueprint BLUEPRINT = new MatrixBlueprint(false);

	@Override
	public boolean isValid() {
		if (!super.isValid()) {
			return false;
		}

		if (isComplete()) {
			// In B, (5,1) must occur exactly two times.
			if (Collections.frequency(getAllValues(), new Value(5, 2)) != 2) {
				FailStatistics.requirementFailed(Requirement._1c_1);
				return false;
			}

			// 1. a) The values for x = 1, 3, 4 and 7 must occur in B exactly
			// two times, one time paired with y=0 and one time with y=1.

			Map<Integer, List<Value>> valuesPerX = getValuesPerX();

			for (Integer x : valuesPerX.keySet()) {
				if (Lists.LIST_1_3_4_7.contains(x)) {
					List<Value> values = valuesPerX.get(x);
					if (values.size() != 2) {
						FailStatistics.requirementFailed(Requirement._1a_1_0);
						return false;
					}
					if ((values.get(0).getY() + values.get(1).getY()) != 1) {
						FailStatistics.requirementFailed(Requirement._1a_1_1);
						return false;
					}
				}
			}
		}

		return true;
	}

	@Override
	public Object clone() {
		MatrixB clone = new MatrixB();
		clone.valuesPerX = valuesPerX;
		clone.rowXValues = rowXValues;
		clone.rowYValues = rowYValues;
		clone.contains_5_1 = contains_5_1;
		clone.count_5_0 = count_5_0;
		clone.values = new Value[M][N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				clone.values[i][j] = values[i][j];
			}
		}
		return clone;
	}

	@Override
	public MatrixBlueprint getBlueprint() {
		return MatrixB.BLUEPRINT;
	}

	@Override
	public Position getLastPositionToComplete() {
		return new Position(M - 1, N - 1); // zwei nicht zu füllende Felder am
											// Ende
	}
}
