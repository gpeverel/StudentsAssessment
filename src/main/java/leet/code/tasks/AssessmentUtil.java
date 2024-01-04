package leet.code.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public final class AssessmentUtil {

	public static boolean isDigit(final String s) {
		if (s != null && !s.isEmpty()) {
			for (int i = 0; i < s.length(); i++) {
				if (!Character.isDigit(s.charAt(i))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public static boolean isValidAssessment(final String s) {
		if (s != null && !s.isEmpty()) {
			return s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5");
		}
		return false;
	}

	public static ArrayList<Integer> getAssessmentList(String[] assessments) throws Exception {
		ArrayList<Integer> assessmentList = Arrays.stream(assessments)
				.filter(AssessmentUtil::isDigit)
				.filter(AssessmentUtil::isValidAssessment)
				.map(Integer::parseInt)
				.collect(Collectors.toCollection(ArrayList::new));
		if (assessments.length == 0 || assessments.length != assessmentList.size()) {
			throw new Exception("В оценках ученика обнаружены проблемы!");
		}
		return assessmentList;
	}
}
