package filesort;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import static java.nio.file.StandardCopyOption.*;

public class main {

	public static void main(String[] args) throws Exception  {

		String BuferFolder = "\\Bufer";
		getListFiles(args[0] + BuferFolder, args[0]);
		
        System.out.println("��������� ���������.");
		
		
    }
        public static void getListFiles(String rootpath, String str) throws IOException {
            
        	File f = new File(str);

            if (!createFolder(rootpath)) {
            	return;
            } 
            
            for (File s : f.listFiles()) {
                if (s.isFile()) {

                	SimpleDateFormat yearformat = new SimpleDateFormat("yyyy");
                	SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
                    String yearfolder = yearformat.format(s.lastModified());                	
                    String datefolder = dateformat.format(s.lastModified());

                    if (createFolder(rootpath + "\\" + yearfolder)) {

                    	String FinishFolderName = rootpath + "\\" + yearfolder + "\\" + datefolder; 
                    	if (createFolder(FinishFolderName)) {
                            File d = new File(FinishFolderName + "\\" + s.getName());
                            System.out.println("���� " + s.getPath() + " ���������� � " + FinishFolderName + "\\" + s.getName());
                    		Files.copy(s.toPath(), d.toPath(), REPLACE_EXISTING);
                    		
                    	}                	
                    }
                } else if (s.isDirectory()) {
                    //getListFiles(rootpath, s.getAbsolutePath());      
                }
            }
        }
        
        public static boolean createFolder(String path) throws IOException {
        	
        	final File FinishFolder = new File(path);
            if(!FinishFolder.exists()) {
                if(FinishFolder.mkdir()) {
                    System.out.println("������� " + FinishFolder.getAbsolutePath() + " ������� ������.");
                } else {
                    System.out.println("������� " + FinishFolder.getAbsolutePath() + " ������� �� �������.");
                    return false;
                }
            } 
        
            return true;
        }            
	}
