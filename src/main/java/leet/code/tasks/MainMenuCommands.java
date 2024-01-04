package leet.code.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainMenuCommands {
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));




	/**
	 * Выполняем команды пользователя по работе с оценками студента
	 * @param students список данных о студентах, который будем изменять
	 * @param result выбор пользователя
	 */
	public static void makeCommand(final ArrayList<Student> students, final int result) {
		try {
			if (result == 1) {
				addNewStudent(students);
			} else if (result == 2) {
				deleteStudent(students);
			} else if (result == 3) {
				addAssessmentToStudent(students);
			} else if (result == 4) {
				updateAssessmentToStudent(students);
			} else if (result == 5) {
				deleteAssessmentOfStudent(students);
			} else if (result == 6) {
				showAllAssessmentOfStudents(students);
			} else if (result == 7) {
				showAssessmentsOfStudent(students);
			} else if (result == 8) {
				downloadToFile(students);
			}
		} catch(Exception e) {
			System.out.println("Ошибка при вводе данных!");
		}
	}

	private static void addNewStudent(final ArrayList<Student> students) throws Exception {
		System.out.println("Введите имя и оценки студента");
		System.out.print("Введите имя\n>> ");
		final String name = reader.readLine();
		System.out.print("Введите оценки через запятую\n>> ");
		final String assessmentsLine = reader.readLine();
		String[] assessments = assessmentsLine.replaceAll(" ", "")
				.split(",");
		ArrayList<Integer> assessmentList = AssessmentUtil.getAssessmentList(assessments);
		final Student student = new Student(name, assessmentList);
		students.add(student);
		System.out.println("Ученик добавлен!");
	}



	private static void deleteStudent(final ArrayList<Student> students) {


	}

	private static void addAssessmentToStudent(final ArrayList<Student> students) {

	}

	private static void updateAssessmentToStudent(final ArrayList<Student> students) {

	}

	private static void deleteAssessmentOfStudent(final ArrayList<Student> students) {

	}

	private static void showAllAssessmentOfStudents(final ArrayList<Student> students) {
		System.out.println("Все ученики");
		for(Student student : students) {
			System.out.println("Имя: " + student.getName() + "\n" +
					"Оценки: " + student.getAssessments());
		}
	}

	private static void showAssessmentsOfStudent(final ArrayList<Student> students) {

	}

	private static void downloadToFile(final ArrayList<Student> students) {

	}



}
