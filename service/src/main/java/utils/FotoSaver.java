package utils;

import com.ilya.model.Item;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ilya on 04.09.2016.
 */
public class FotoSaver {


    public static void saveFotoToFileSystem(InputStream in, String cathegory, String name, String num) throws IOException{
        Path p = Paths.get("/foto/"+cathegory+"/"+name);
        Files.createDirectories(p);
        Path res = p.resolve("file"+num+".jpg");
        if(in.available()>0)
        Files.copy(in,res, StandardCopyOption.REPLACE_EXISTING);
    }
    public static List<Path> getPathsOfFotos(Item item)throws IOException{
        Path p = Paths.get("/foto/"+item.getTheme()+"/"+item.getName().trim());
        List<Path> list = new ArrayList<>();
        Files.createDirectories(p);
        try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(p)){
            for(Path path : directoryStream){
                list.add(path);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }

    public static void deleteFile(String p)throws IOException{

        Files.deleteIfExists(Paths.get(p));
    }


    public static void renameFotoDirectory(String name,String theme,String oldName,String oldTheme)throws IOException{
        Path old = Paths.get("/foto/"+oldTheme+"/"+oldName.trim());
        Path hadash = Paths.get("/foto/"+theme+"/"+name.trim());
        Files.createDirectories(old);
        Files.createDirectories(hadash);
        try(DirectoryStream<Path> directoryStream = Files.newDirectoryStream(old)){
            for (Path path : directoryStream){
                Path toSave =  hadash.resolve(path.getFileName());
                Files.move(path,toSave);
                Files.deleteIfExists(path);
            }
        }
    }

}
