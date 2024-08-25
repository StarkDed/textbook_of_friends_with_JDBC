package org.example.WorkWithFiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//class for read info connection to db
public class ReaderInfoForCon {

    //method which read 3 lines of file and then return String[]
    public static String[] readFileForConnection() throws IOException,IndexOutOfBoundsException{
        String[] arr=new String[3];
        String str;
        int i=0;
        try(BufferedReader reader =new BufferedReader(new FileReader("InfoConnection.txt"))) {
            while ((str = reader.readLine()) != null) {
                arr[i++] = str;
            }
        }

        return arr;

    }
}


/*}catch(IOException e){
        System.out.println("File not found");
        }catch(IndexOutOfBoundsException e){
        System.out.println("File don't include connection");
        }*/
