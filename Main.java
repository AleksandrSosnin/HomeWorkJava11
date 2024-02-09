
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол");
            String input = scanner.nextLine();

            String[] data = input.split(" ");
            if (data.length != 6) {
                System.err.println("Ошибка: неверное количество данных");
                return;
            }

            try {
                Person person = parsePersonData(data);
                FileManager.writeToFile(person);
                System.out.println("Данные успешно записаны в файл.");
            } catch (DataFormatException | DataLengthException | FileWriteException e) {
                System.err.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private static Person parsePersonData(String[] data) throws DataFormatException, DataLengthException {
        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        String dateOfBirth = data[3];
        long phoneNumber;
        char gender;

        try {
            phoneNumber = Long.parseLong(data[4]);
        } catch (NumberFormatException e) {
            throw new DataFormatException("Неверный формат номера телефона");
        }

        if (!data[5].matches("[mf]")) {
            throw new DataFormatException("Неверный формат пола");
        } else {
            gender = data[5].charAt(0);
        }

        return new Person(lastName, firstName, middleName, dateOfBirth, phoneNumber, gender);
    }
}
