package pl.zajavka.case2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static pl.zajavka.git.GitConfig.YOUR_LOCAL_PLAYGROUND_REPOSITORY_LOCATION;

class FileModifications {

    public static final String PERSON_FILE_LOCATION = "./src/main/java/pl/zajavka/case2/Person.java";
    public static final String PERSON_FILE_PATH = YOUR_LOCAL_PLAYGROUND_REPOSITORY_LOCATION + "/../" + PERSON_FILE_LOCATION;

    public static void modification1() {
        final String content = """
            package pl.zajavka.case2;
                        
            record Person(String name, Long age) {
                
                public String whatIsYourJob() {
                    return "Software developer!";
                }
                        
                public String whatDoYouLike() {
                    return "Strawberries";
                }
                        
                public Boolean doYouHaveABike() {
                    return true;
                }
            }
            """;
        save(Paths.get(PERSON_FILE_PATH), content);
    }

    public static void modification2() {
        final String content = """
            package pl.zajavka.case2;
                        
            import java.math.BigDecimal;
                        
            record Person(String name, Long age, BigDecimal salary) {
                
                public Boolean doYouHaveABike() {
                    return true;
                }
                        
                public Boolean doYouLikePizza() {
                    return true;
                }
            }
            """;
        save(Paths.get(PERSON_FILE_PATH), content);
    }

    public static void modification3() {
        final String content = """
            package pl.zajavka.case2;
                        
            import java.math.BigDecimal;
                        
            record Person(String name, BigDecimal salary) {
                
                public BigDecimal howMuchDoYouEarn() {
                    return salary;
                }
                        
                public Boolean doYouLikePizza() {
                    return true;
                }
            }
            """;
        save(Paths.get(PERSON_FILE_PATH), content);
    }

    private static void save(Path filePath, String content) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
