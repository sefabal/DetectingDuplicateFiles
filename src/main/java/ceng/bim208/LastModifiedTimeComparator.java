package ceng.bim208;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.util.Comparator;

/**
 * Compare the last modified date/time of two paths
 */
class LastModifiedTimeComparator implements Comparator<Path> {


    @Override
    public int compare(Path p1, Path p2){

        FileTime t1 = null,t2 = null;

        try {
            t1 = Files.getLastModifiedTime(p1, LinkOption.NOFOLLOW_LINKS);
            t2 = Files.getLastModifiedTime(p2,LinkOption.NOFOLLOW_LINKS);
        } catch (IOException e) {
            e.printStackTrace();
        }



        return t2.compareTo(t1);


    }
}