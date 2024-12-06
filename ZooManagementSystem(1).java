import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

abstract class Animal {
    protected String name;
    protected int age;
    private final String species;

    public Animal(String name, int age, String species) {
        this.name = name;
        this.age = age;
        this.species = species;
    }

    public abstract void makeSound();
    public abstract void eat();

    public final void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age + ", Species: " + species);
    }
}

class Lion extends Animal {
    public Lion(String name, int age) {
        super(name, age, "Lion");
    }

    @Override
    public void makeSound() {
        System.out.println("Lion " + name + " roars!");
    }

    @Override
    public void eat() {
        System.out.println("Lion " + name + " eats meat.");
    }
}

class Elephant extends Animal {
    public Elephant(String name, int age) {
        super(name, age, "Elephant");
    }

    @Override
    public void makeSound() {
        System.out.println("Elephant " + name + " trumpets!");
    }

    @Override
    public void eat() {
        System.out.println("Elephant " + name + " eats plants.");
    }
}

public class ZooManagementSystem {

    public static void makeAllAnimalsSound(ArrayList<Animal> zoo) {
        for (Animal animal : zoo) {
            animal.makeSound();
        }
    }

    public static ArrayList<Animal> loadAnimalsFromCSV(String fileName) {
        ArrayList<Animal> zoo = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String species = parts[0].trim();
                    String name = parts[1].trim();
                    int age = Integer.parseInt(parts[2].trim());

                    switch (species.toLowerCase()) {
                        case "lion":
                            zoo.add(new Lion(name, age));
                            break;
                        case "elephant":
                            zoo.add(new Elephant(name, age));
                            break;
                        default:
                            System.out.println("Unknown species: " + species);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        return zoo;
    }

    public static void main(String[] args) {
        ArrayList<Animal> zoo = loadAnimalsFromCSV("animals.csv");

        System.out.println("Zoo Information:");
        for (Animal animal : zoo) {
            animal.displayInfo();
        }

        System.out.println("\nAnimal Sounds:");
        makeAllAnimalsSound(zoo);
    }
}
