package leet.code.tasks;

import java.io.*;
import java.util.ArrayList;

public class FileWorkHandler {
	public static String newFilePath = null;

	/**
	 * Работаем с файлами. Создаем новый или загружаем данные из старого
	 * @param result выбор пользователя
	 * @return список данных о студентах
	 */
	public static ArrayList<Student> workWithFile(final int result) {
		if (result == 1) {
			return getAlreadyExistsDataFromFile();
		} else if (result == 2) {
			setNewFilePath();
		}
		return new ArrayList<>();
	}

	private static ArrayList<Student> getAlreadyExistsDataFromFile() {
		System.out.print("Введите путь до файла, из которого будем извлекать данные\n>> ");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			final String path = reader.readLine();
			File file = new File(path);
			String fileName = file.getName();
			if (file.isDirectory()) {
				System.out.println("Это директория! Создадим там файл db.txt");
				newFilePath = path + "//db.txt";
			} else if (!fileName.isEmpty()) {
				return getDataFromFile(file);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Не понятно что! Создадим файл db.txt в корневой папке!");
			newFilePath = "db.txt";
		}
		return new ArrayList<>();
	}

	private static ArrayList<Student> getDataFromFile(final File file) throws Exception {
		if(file.exists()) {
			final ArrayList<Student> students = new ArrayList<>();
			try (BufferedReader in = new BufferedReader(new FileReader(file))) {
				String lineText;
				while ((lineText = in.readLine()) != null) {
					if (lineText.equals("\n") || lineText.isBlank()) {
						continue;
					}
					int divider = lineText.indexOf(":");
					String studentName = lineText.substring(0, divider).trim();
					String[] assessments = lineText.substring(divider + 1)
							.replaceAll(" ", "")
							.split(",");

					ArrayList<Integer> assessmentList = AssessmentUtil.getAssessmentList(assessments);
					final Student student = new Student(studentName, assessmentList);
					students.add(student);
				}
				newFilePath = file.getPath();
				return students;
			} catch(IndexOutOfBoundsException e) {
				System.out.println("Проблемы с данными в файле!");
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
				throw new Exception();
			}

		} else {
			throw new Exception();
		}
		return new ArrayList<>();
	}

	private static void setNewFilePath() {
		System.out.print("Введите путь до файла, в который будем записывать новые данные\n>> ");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			final String path = reader.readLine();
			File file = new File(path);
			String fileName = file.getName();
			if (file.isDirectory()) {
				System.out.println("Это директория! Создадим там файл db.txt");
				newFilePath = path + "//db.txt";
			} else if (!fileName.isEmpty()) {
				setPathWithFileName(file);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("Не понятно что! Создадим файл db.txt в корневой папке!");
			newFilePath = "db.txt";
		}
	}

	private static void setPathWithFileName(final File file) {
		int lastSlash = file.getPath().lastIndexOf("/");
		if (lastSlash == -1) {
			System.out.println("Создадим файл в корне с именем: " + file.getName());
			newFilePath = file.getName();
		} else {
			System.out.println("Создадим файл с именем: " + file.getName() + " по адресу: " +
					(file.getPath().substring(0, lastSlash)));
			newFilePath = file.getPath();
		}
	}

}
