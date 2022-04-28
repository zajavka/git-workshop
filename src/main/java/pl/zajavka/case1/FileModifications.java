package pl.zajavka.case1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static pl.zajavka.git.GitConfig.YOUR_LOCAL_PLAYGROUND_REPOSITORY_LOCATION;

class FileModifications {

    public static final String EXERCISE_2_ANIMAL = "./src/main/java/pl/zajavka/case1/Animal.java";
    public static final String EXERCISE_2_CAT = "./src/main/java/pl/zajavka/case1/Cat.java";
    public static final String EXERCISE_2_DOG = "./src/main/java/pl/zajavka/case1/Dog.java";
    public static final String ANIMAL_FILE_PATH = YOUR_LOCAL_PLAYGROUND_REPOSITORY_LOCATION + "/../" + EXERCISE_2_ANIMAL;
    public static final String CAT_FILE_PATH = YOUR_LOCAL_PLAYGROUND_REPOSITORY_LOCATION + "/../" + EXERCISE_2_CAT;
    public static final String DOG_FILE_PATH = YOUR_LOCAL_PLAYGROUND_REPOSITORY_LOCATION + "/../" + EXERCISE_2_DOG;

    public static void modification1() {
        final String content1 = """
            package pl.zajavka.case1;
                        
            public abstract class Animal {
            
                protected String whatIsYourName() {
                    return "I don't have any";
                }
            }
            """;
        final String content2 = """
            package pl.zajavka.case1;
                        
            public class Dog extends Animal {
                private String name;
            }
            """;
        final String content3 = """
            package pl.zajavka.case1;
                        
            public class Cat extends Animal {
            
                @Override
                public String whatIsYourName() {
                    return "My name is Cat";
                }
            }
            """;
        save(Paths.get(ANIMAL_FILE_PATH), content1);
        save(Paths.get(DOG_FILE_PATH), content2);
        save(Paths.get(CAT_FILE_PATH), content3);
    }

    public static void modification2() {
        final String content1 = """
            package pl.zajavka.case1;
                        
            public abstract class Animal {
            
                private String name;
            
                protected String whatIsYourName() {
                    return "I don't have any";
                }
            }
            """;
        final String content2 = """
            package pl.zajavka.case1;
                        
            public class Dog extends Animal {
                
                @Override
                public String whatIsYourName() {
                    return "My name is Dog";
                }
            }
            """;
        save(Paths.get(ANIMAL_FILE_PATH), content1);
        save(Paths.get(DOG_FILE_PATH), content2);
    }

    public static void modification3() {
        final String content2 = """
            package pl.zajavka.case1;
                        
            public class Dog extends Animal {
                private String name;
                
                public void sniff() {
                    System.out.println("I'm soo sniffing!");
                }
            }
            """;
        final String content3 = """
            package pl.zajavka.case1;
                        
            public class Cat extends Animal {
            
                private String milk;
                
                public Cat(String milk) {
                    this.milk = milk;
                }
            
                @Override
                public String whatIsYourName() {
                    return "My name is Cat";
                }
            }
            """;
        save(Paths.get(DOG_FILE_PATH), content2);
        save(Paths.get(CAT_FILE_PATH), content3);
    }

    private static void save(Path filePath, String content) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
