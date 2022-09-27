package ui;

import config.ConfigProperties;
import config.ConfigYaml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainTest {
    public static void main(String[] args) throws IOException {
        //para usuario
        Path name = Paths.get(ConfigYaml.getInstance().getProperty("user"));
        Path password = Paths.get(ConfigYaml.getInstance().getProperty("password"));
        System.out.println(name + " " + password);

        //para rutas
        File file2 = new File(ConfigProperties.getInstance().getProperty("pathNewspaper"));
        BufferedReader br = new BufferedReader(new FileReader(file2));
        String st;
//        esto puede servir
//        br.lines().collect(Collectors.joining());
        List<String> buff = new ArrayList<>();
        while ((st = br.readLine()) != null) {
            buff.add(st);
        }

        buff.forEach(System.out::println);
    }
}
