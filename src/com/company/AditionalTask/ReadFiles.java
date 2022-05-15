package com.company.AditionalTask;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFiles {

    private String path;
    private List<String> fileNames = new ArrayList<>();     // список файлов
    private Map<String, Document> files = new HashMap<>();

    public void readFilesName(String path) throws FileDosentExistException {        // считываем имена из указанной папки

        this.path = path;
        File[] files = new File(path).listFiles();                  // массив файлов, находящиеся в папке
        Pattern pattern = Pattern.compile("\\w\\.txt$");            // читаем и записываем только txt-файлы
        Matcher matcher;

        if (files.length == 0)
            throw new FileDosentExistException("Папка пуста");
        else {
            for (File file : files) {
                if (file.isFile()) {
                    matcher = pattern.matcher(file.getName());          // проверяем название файла по шаблоку
                    if (matcher.find()) {
                        fileNames.add(file.getName());          // записываем вафйл в List
                    }
                }
            }
        }
    }

    public void loadFiles(int enterValue) throws FileNotFoundException {            // записываем данные в map


        String[] fileData = new String[3];

        if (enterValue > fileNames.size())
            for (String fileName : fileNames) {
                files.put(fileName, (this.readFromFile(fileName, fileData)));
            }
        else {
            int count = 0;
            while (count < enterValue) {
                for (String fileName : fileNames) {
                    files.put(fileName, (this.readFromFile(fileName, fileData)));
                    count++;
                    fileNames.remove(fileName);
                    break;
                }
            }
        }

        for (Map.Entry<String, Document> f : files.entrySet()) {            // выводим данные из map в консоль
            System.out.println(f.getKey() + "\t" + f.getValue());
        }
    }


    private Document readFromFile(String fileName, String[] fileData) throws FileNotFoundException {

        ArrayList<String> documentNumber = new ArrayList<>();
        String eMail = null;
        String phoneNumber = null;

        FileReader fileReader = new FileReader(path + "/" + fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader, 500);

        String line;

        try {
            while ((line = bufferedReader.readLine()) != null) {
                fileData = this.sptitLine(line);
                for (int i = 0; i < fileData.length; i++) {         // добавляем в map данные из файла
                    if (this.isDocumentNumber(fileData[i])) {
                        documentNumber.add(fileData[i]);
                    }
                    if (this.isEmail(fileData[i])) {
                        eMail = fileData[i];
                    }
                    if (this.isPhoneNumber(fileData[i])) {
                        phoneNumber = fileData[i];
                    }
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Document(documentNumber, eMail, phoneNumber);
    }


    private String[] sptitLine(String line) {

        return line.split(" ");
    }


    private boolean isDocumentNumber(String documentNumber) {

        Pattern pattern = Pattern.compile("^(([0-9]{5})-([A-Za-z]{3})-([0-9]{5})-([A-Za-z]{3})-([0-9][A-Za-z][0-9][A-Za-z]))$");
        Matcher matcher = pattern.matcher(documentNumber);

        return matcher.find();
    }

    private boolean isEmail(String documentNumber) {

        Pattern pattern = Pattern.compile("^((\\w+[\\.-]?\\w+)+\\@(\\w+[\\.-]?\\w+)+\\.([a-z]{2,4}))$");
        Matcher matcher = pattern.matcher(documentNumber);

        return matcher.find();
    }

    private boolean isPhoneNumber(String documentNumber) {

        Pattern pattern = Pattern.compile("^\\+375\\((29|25|33|44)\\)([0-9]{3})\\-([0-9]+)\\-([0-9]+)$");
        Matcher matcher = pattern.matcher(documentNumber);

        return matcher.find();
    }

}
