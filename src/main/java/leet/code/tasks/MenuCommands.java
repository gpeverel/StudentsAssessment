package leet.code.tasks;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MenuCommands {
	private static String newFilePath = null;


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
					String studentName = lineText.substring(0, divider);
					String[] assessments = lineText.substring(divider + 1)
							.replaceAll(" ", "")
							.split(",");

					ArrayList<Integer> assessmentList = Arrays.stream(assessments)
							.filter(MenuCommands::isDigit)
							.filter(MenuCommands::isValidAssessment)
							.map(Integer::parseInt)
							.collect(Collectors.toCollection(ArrayList::new));
					if (assessments.length == 0 || assessments.length != assessmentList.size()) {
						throw new Exception("В оценках ученика обнаружены проблемы!");
					}
					final Student student = new Student(studentName, assessmentList);
					students.add(student);
				}
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

	private static boolean isDigit(final String s) {
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

	private static boolean isValidAssessment(final String s) {
		if (s != null && !s.isEmpty()) {
			return s.equals("2") || s.equals("3") || s.equals("4") || s.equals("5");
		}
		return false;
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


	public static void makeCommand(final ArrayList<Student> students, final int result) {
		System.out.println("Делаем штуку!");
	}
}
