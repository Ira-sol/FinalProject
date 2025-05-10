import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    public static void saveArtifacts(ArrayList<Artifact> list, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Artifact a : list) {
                writer.write(a.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to " + filename + ": " + e.getMessage());
        }
    }

    public static void saveArray(Artifact[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Artifact a : array) {
                if (a != null) {
                    writer.write(a.toFileString());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving to " + filename + ": " + e.getMessage());
        }
    }

    public static ArrayList<Artifact> loadArtifacts(String filename) {
        ArrayList<Artifact> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(Artifact.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading " + filename + ": " + e.getMessage());
        }
        return list;
    }

    public static Artifact[] loadArray(String filename, int size) {
        Artifact[] array = new Artifact[size];
        int index = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null && index < size) {
                array[index++] = Artifact.fromFileString(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading " + filename + ": " + e.getMessage());
        }
        return array;
    }
}