import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MatrixCreator
{
	public static void main(String[] args)
	{
		final long timeStart = System.nanoTime();

		MatrixCreator matrixCreator = new MatrixCreator();
		// matrixCreator.createMatrixes(false, 3_000_000);

		//matrixCreator.solve(3000_000, 50_000);
		
		List<Matrix> matrixesB = matrixCreator.createMatrixes(true, 3_000_000);

		final long timeEnd = System.nanoTime();

		System.out.println("gebrauchte Zeit: " + ((timeEnd - timeStart) / 1000000) + "ms");
	}

//
//	public static void main(String[] args) {
//		MatrixBlueprint mb = new MatrixBlueprint(true);
//		
//		System.out.println("--------------------------------------");
//		
//		MatrixBlueprint mb2 = new MatrixBlueprint(false);
//	}
	
	public List<Solution> solve(int matrixACount, int matrixBCount)
	{
		List<Solution> solutions = new ArrayList<>();

		List<Matrix> matrixesA = createMatrixes(true, matrixACount);
		System.out.println("matrixesA created.");
		List<Matrix> matrixesB = createMatrixes(false, matrixBCount);
		System.out.println("matrixesB created.");

		for (Matrix matrixA : matrixesA)
		{
			for (Matrix matrixB : matrixesB)
			{
				if (Matrix.areValid(matrixA, matrixB))
				{
					System.out.println("-------------------- SOLVED ----------------------");
					System.out.println(matrixA);
					System.out.println();
					System.out.println(matrixB);
				}
			}
		}

		return solutions;
	}

	public List<Matrix> createMatrixes(boolean a, int maxMatrixCount)
	{
		List<Matrix> possibleMatrixes = new ArrayList<>();

		Matrix matrix = a ? new MatrixA() : new MatrixB();
		HashMap<Position, Integer> chosenPossibleValueIndexPerPosition = new HashMap<>();

		rowLoop: for (int i = 0; i < Matrix.M; i++)
		{
			columnLoop: for (int j = 0; j < Matrix.N; j++)
			{
				List<Value> possibleValues = matrix.getBlueprint().getPossibleValues(matrix, i, j);

				Position position = new Position(i, j);
				int possibleValuesStartIndex;

				if (chosenPossibleValueIndexPerPosition.containsKey(position))
				{
					possibleValuesStartIndex = chosenPossibleValueIndexPerPosition.get(position) + 1;
					// wenn wir über max Index hinaus gegangen sind, dann wird die possibleTupleLoop einfach sofort beenden
				}
				else
				{
					possibleValuesStartIndex = 0;
				}

				for (int possibleTupleIndex = possibleValuesStartIndex; possibleTupleIndex < possibleValues.size(); possibleTupleIndex++)
				{
					Value chosenTuple = possibleValues.get(possibleTupleIndex);
					matrix.set(i, j, chosenTuple);
					if (matrix.isValid())
					{
						if (position.equals(matrix.getLastPositionToComplete()))
						{
							//System.out.println(matrix);
							if (possibleMatrixes.size() % 10_000 == 0 && possibleMatrixes.size() != 0)
							{
								System.out.println("Anzahl gefundene valide Matrizen: " + possibleMatrixes.size());
								// System.out.println(matrix);
							}
							possibleMatrixes.add(matrix);
							matrix = (Matrix) matrix.clone();
							if (possibleMatrixes.size() == maxMatrixCount)
							{
								break rowLoop;
							}
						}
						else
						{
							chosenPossibleValueIndexPerPosition.put(position, possibleTupleIndex);
							continue columnLoop;
						}
					}
				}
				// keines der möglichen Tuples in diesem Feld hat zu einer validen Matrix geführt => ein Feld zurück und dort nächst mögliches Tuple
				// setzen

				matrix.clear(i, j);
				chosenPossibleValueIndexPerPosition.remove(position);

				if (j > 0)
				{
					j -= 2;
				}
				else
				{
					if (i == 0)
					{
						// keine (weitere) Lösung gefunden
						break rowLoop;
					}
					j = Matrix.N - 2;
					i -= 1;
				}
				continue;
			}
		}

		System.out.println("Anzahl Matrizen: " + possibleMatrixes.size());

		if (!possibleMatrixes.isEmpty())
		{
			System.out.println(possibleMatrixes.get(possibleMatrixes.size() - 1));
		}

		return possibleMatrixes;
	}
}
