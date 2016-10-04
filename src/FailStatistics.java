import java.util.HashMap;
import java.util.Map;


public class FailStatistics {
	
	private static Map<Requirement, Integer> requirementToFailures = new HashMap<>();
	
	public static void requirementFailed(Requirement requirement) {
		Integer failures = requirementToFailures.get(requirement);
		if (failures == null) {
			requirementToFailures.put(requirement, 1);
		}
		else {
			requirementToFailures.put(requirement, failures + 1);
		}
	}
}
