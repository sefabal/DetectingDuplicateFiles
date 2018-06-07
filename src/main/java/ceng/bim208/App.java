package ceng.bim208;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    static Map<Long, List<Path>> map = new HashMap<>();


    public static void main(String[] args) throws IOException {


        if (args.length < 1) {
            System.err.println("Usage: java App startingDir...");
            return;
        }

        Path startingDir = Paths.get(args[0]);
        RegularFileVisitor visitor = new RegularFileVisitor();
        Files.walkFileTree(startingDir, visitor);

        map = sortByListSize(map);

        map.forEach((Long aLong, List<Path> paths) -> {
            paths.sort(new LastModifiedTimeComparator());
            if (paths.size() != 1) {
                for (Path path : paths
                        ) {
                    System.out.print(startingDir.relativize(path) + " ");
                    //System.out.print(" " + path.getName(path.getNameCount()-2)+"/"+ path.getFileName() + " " + Files.getLastModifiedTime(path, LinkOption.NOFOLLOW_LINKS) );

                }
                System.out.println();
            }
        });


    }

    private static <K extends Comparable<? super K>, V> Map<K, List<V>> sortByListSize(Map<K, List<V>> map) {
        return map.entrySet()
                .stream()
                .filter((c1) -> c1.getValue().size() > 1)
                .sorted((c1, c2) -> c2.getValue().size() - c1.getValue().size())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}

