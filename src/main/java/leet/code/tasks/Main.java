package leet.code.tasks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static MenuCommands menuCommands;

	public static void main(String[] args) {
		ArrayList<Student> students;
		int result;
		result = getFileWorkOptionResult();
		students = MenuCommands.workWithFile(result);
		while(true) {
			result = getMainMenuWorkOptionResult();
			MenuCommands.makeCommand(students, result);
			if (result == 8) {
				return;
			}
		}
	}

	private static int getFileWorkOptionResult() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int result = 2;
		boolean untilIncorrectResult = true;
		while (untilIncorrectResult) {
			printFirstFilesMenu();
			try {
				result = Integer.parseInt(reader.readLine());
				System.out.println(result);
				if (result == 1 || result == 2) {
					untilIncorrectResult = false;
				} else {
					System.out.println("Либо 1, либо 2!");
				}
			} catch(Exception e) {
				System.out.println("Либо 1, либо 2!");
			}
		}
		return result;
	}

	private static int getMainMenuWorkOptionResult() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int result = 8;
		while (true) {
			try {
				printMainMenu();
				result = Integer.parseInt(reader.readLine());
				System.out.println(result);
				if (result > 8 || result < 1) {
					System.out.println("Введено некорректное значение!");
				} else {
					return result;
				}
			} catch (Exception e) {
				System.out.println("Введено некорректное значение!");
			}
		}
	}

	private static void printFirstFilesMenu() {
		System.out.print("Загрузить существующий файл данных или создать новый?\n" +
				"1: Загрузить существующий файл.\n" +
				"2. Создать новый.\n>> ");
	}


	private static void printMainMenu() {
		final String mainMenu = "Выберите действие:\n" +
				"1. Добавить нового ученика\n" +
				"2. Удалить ученика\n" +
				"3. Добавить оценку ученику\n" +
				"4. Обновить оценку ученику\n" +
				"5. Удалить оценку ученика\n" +
				"6. Просмотр оценок всех учащихся\n" +
				"7. Просмотр оценок конкретного учащегося\n" +
				"8. Завершить и выгрузить в файл\n>> ";
		System.out.print(mainMenu);
	}
}