package leet.code.tasks;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class MainMenuCommands {
	private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));




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
				updateAssessmentOfStudent(students);
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
		ArrayList<Integer> assessmentList = getAssessmentsFromConsole();
		final Student student = new Student(name, assessmentList);
		students.add(student);
		System.out.println("Ученик добавлен!");
	}



	private static void deleteStudent(final ArrayList<Student> students) throws IOException {
		System.out.print("Введите имя\n>> ");
		final String name = reader.readLine();
		System.out.println("Удалим первого студента с этим именем!...");
		boolean removed = students.removeIf(s -> s.getName().equals(name));
		if (removed) {
			System.out.println("Студент удален!");
		} else {
			System.out.println("Студент с таким именем не найден...");
		}
	}

	private static void addAssessmentToStudent(final ArrayList<Student> students) throws Exception {
		final Optional<Student> student = getStudentByNameFromConsole(students);
		if (student.isPresent()) {
			System.out.print("Введите одну или более оценки через запятую\n>> ");
			ArrayList<Integer> assessmentList = getAssessmentsFromConsole();
			student.get().getAssessments().addAll(assessmentList);
			System.out.println("Оценки добавлены!");
		} else {
			System.out.println("Студент с таким именем не найден...");
		}

	}

	private static void updateAssessmentOfStudent(final ArrayList<Student> students) throws Exception {
		final Optional<Student> student = getStudentByNameFromConsole(students);
		if (student.isPresent()) {
			showAssessmentsWithIndexes(student.get());
			System.out.print("Введите индекс оценки, которую хотите изменить\n>> ");
			final String indexLine = reader.readLine();
			final int index = Integer.parseInt(indexLine);
			System.out.print("Введите новую оценку\n>> ");
			final String newAssessmentLine = reader.readLine().trim();
			if (!AssessmentUtil.isValidAssessment(newAssessmentLine)) {
				throw new Exception();
			}
			final int newAssessment = Integer.parseInt(newAssessmentLine);
			student.get().getAssessments().set(index, newAssessment);
			System.out.println("Оценка изменена!");
		} else {
			System.out.println("Студент с таким именем не найден...");
		}
	}

	private static void deleteAssessmentOfStudent(final ArrayList<Student> students) throws Exception {
		final Optional<Student> student = getStudentByNameFromConsole(students);
		if (student.isPresent()) {
			showAssessmentsWithIndexes(student.get());
			System.out.print("Введите индекс оценки, которую хотите удалить\n>> ");
			final String indexLine = reader.readLine();
			final int index = Integer.parseInt(indexLine);
			student.get().getAssessments().remove(index);
			System.out.println("Оценка удалена!");
		} else {
			System.out.println("Студент с таким именем не найден...");
		}
	}

	private static void showAssessmentsWithIndexes(final Student student) {
		System.out.println("Оценки студента - " + student.getName());
		ArrayList<Integer> assessments = student.getAssessments();
		for(int i = 0; i < assessments.size(); i++) {
			System.out.println(i + ") " + assessments.get(i));
		}
	}

	private static void showAllAssessmentOfStudents(final ArrayList<Student> students) {
		System.out.println("Все ученики");
		for(Student student : students) {
			System.out.println("Имя: " + student.getName() + "\n" +
					"Оценки: " + student.getAssessments());
		}
	}

	private static void showAssessmentsOfStudent(final ArrayList<Student> students) throws Exception {
		final Optional<Student> student = getStudentByNameFromConsole(students);
		if (student.isPresent()) {
			System.out.println("Оценки: " + student.get().getAssessments());
		} else {
			System.out.println("Студент с таким именем не найден...");
		}
	}

	private static void downloadToFile(final ArrayList<Student> students) throws IOException {
		final File file = new File(FileWorkHandler.newFilePath);
		if (!file.exists())
			file.createNewFile();
		System.out.println("Файл будет перезаписан с новыми данными!");
		try(FileWriter writer = new FileWriter(file, false)) {
			for(Student student : students) {
				StringBuilder textLine = new StringBuilder();
				textLine.append(student.getName()).append(" : ");
				for(int i = 0; i < student.getAssessments().size(); i++) {
					if (i == student.getAssessments().size() - 1) {
						textLine.append(student.getAssessments().get(i).toString());
					} else {
						textLine.append(student.getAssessments().get(i).toString()).append(", ");
					}
				}
				textLine.append("\n");
				writer.write(textLine.toString());
			}
			writer.flush();
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static Optional<Student> getStudentByNameFromConsole(final ArrayList<Student> students) throws IOException {
		System.out.print("Введите имя\n>> ");
		final String name = reader.readLine();
		return students.stream()
				.filter(s -> s.getName().equals(name))
				.findFirst();
	}

	private static ArrayList<Integer> getAssessmentsFromConsole() throws Exception {
		final String assessmentsLine = reader.readLine();
		String[] assessments = assessmentsLine.replaceAll(" ", "")
				.split(",");
		return AssessmentUtil.getAssessmentList(assessments);
	}



}
