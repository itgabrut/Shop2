package utils;

import com.ilya.model.Item;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilya on 04.09.2016.
 */
public final class FotoSaver {

    private FotoSaver(){}

    /**
     * Copies image file to filesystem using parameters to create directories
     * @param in            Source
     * @param category      Item's category. Serves as part of file path
     * @param name          Items' name
     * @param num           Number of image.
     * @throws IOException
     */
    public static void saveFotoToFileSystem(InputStream in, String category, String name, String num) throws IOException{
        Path p = Paths.get("/foto/"+category+"/"+name);
        Files.createDirectories(p);
        Path res = p.resolve("file"+num+".jpg");
        if(in.available()>0)
        Files.copy(in,res, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     *
     * @param item Item entity
     * @return List of Path objects
     * @throws IOException
     */
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

    /**
     *  Tries to delete File
     * @param p String representation of Path to object
     * @throws IOException
     */
    public static void deleteFile(String p)throws IOException{

        Files.deleteIfExists(Paths.get(p));
    }

    /**
     *  Move all files from path, represented by [old] to new location
     * @param name Part of new location path
     * @param theme Part of new location path
     * @param oldName Part of old location path
     * @param oldTheme  Part of old location path
     * @throws IOException
     */
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
