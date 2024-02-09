import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public static void writeToFile(Person person) throws FileWriteException {
        String filename = person.getLastName() + ".txt";
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(person.getLastName() + " " + person.getFirstName() + " " + person.getMiddleName() + " " +
                    person.getDateOfBirth() + " " + person.getPhoneNumber() + " " + person.getGender() + "\n");
        } catch (IOException e) {
            throw new FileWriteException("Error writing to file: " + e.getMessage());
        }
    }
}
