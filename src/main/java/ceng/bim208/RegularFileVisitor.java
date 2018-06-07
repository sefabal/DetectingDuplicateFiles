package ceng.bim208;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

class RegularFileVisitor extends SimpleFileVisitor<Path> {

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if (attrs.isRegularFile() && !Files.isHidden(file)) {

            long checkSum = Sum.sum(file);

            if (App.map.size() == 0) {
                App.map.put(checkSum, new ArrayList<>());
                App.map.get(checkSum).add(file);
            } else if (App.map.containsKey(checkSum)) {
                App.map.get(checkSum).add(file);
            } else {
                App.map.put(checkSum, new ArrayList<>());
                App.map.get(checkSum).add(file);
            }
        }
        return CONTINUE;
    }


    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {

        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        System.err.println(exc);
        return CONTINUE;
    }

}
