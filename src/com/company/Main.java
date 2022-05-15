package com.company;

import com.company.AditionalTask.FileDosentExistException;
import com.company.AditionalTask.ReadFiles;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {

        CheckIP checkIP = new CheckIP();
        Scanner scanner = new Scanner(System.in);
        ReadFiles readFiles = new ReadFiles();


        System.out.println("Введи IP-дрес:");
        String enterIP = scanner.next();

        System.out.print("Введенный IP-дрес валиден? - ");
        System.out.print(checkIP.isValid(enterIP));

        System.out.println("\n\nУкажи путь к папке: ");
        String path = scanner.next();
        System.out.println("Сколько файлов нужно обработать?: ");


        int enterValue = scanner.nextInt();
        try {
            readFiles.readFilesName(path);
        }catch (FileDosentExistException e){
            e.printStackTrace();
        }

        try {
            readFiles.loadFiles(enterValue);
        }catch (FileNotFoundException e){
            e.printStackTrace();
         }

    }
}
