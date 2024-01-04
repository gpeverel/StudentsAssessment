package leet.code.tasks;

import java.util.ArrayList;

public class Student {
	private final String name;
	private ArrayList<Integer> assessments;

	public Student(final String name, final ArrayList<Integer> assessments) {
		this.name = name;
		this.assessments = assessments;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Integer> getAssessments() {
		return assessments;
	}

	public void setAssessments(ArrayList<Integer> assessments) {
		this.assessments = assessments;
	}
}
