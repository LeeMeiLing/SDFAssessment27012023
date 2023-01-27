package termfrequency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {
    
    public static void main(String[] args) throws IOException{

        String fileName = null;
        String input;
        Map<String, WordCount> countWord = new HashMap<>();
        Map<String, Double> topTermFreq = new LinkedHashMap<>();
        List<WordCount> wordCountList = new LinkedList<>();
        int totalCount = 0;

        if (args.length == 0){
            System.out.println("Please rerun the program and enter a file name");
            System.exit(0);
        }else{
            fileName = args[0];
        }
       
        File file = new File(fileName);

        if (!file.exists()){

            System.out.println("file not found");

        }else{

            // do the following if file exist

            //using try-with-resource, BufferedReader auto close, no need to br.close()
            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                
                while ((input = br.readLine()) != null) { 

                    if(input.isBlank()){
                        continue; // skip line if blank
                    }

                    String[] inputArr = input.trim().split(" ");

                    for (int i = 0; i < inputArr.length; i++){

                        inputArr[i] = inputArr[i].toLowerCase().replaceAll("[\\.*,*:*!*\\-*\\(*\\)*{*}*\\'*\"*]", "");
                        if(inputArr[i].isBlank()){
                            continue;
                        }else{
                            totalCount++;
                            
                            if(countWord.containsKey(inputArr[i])){ // update map if exist in keySet

                                countWord.get(inputArr[i]).addCount();

                            }else{
                                // create new entry if not in keySet
                                countWord.put(inputArr[i], new WordCount(inputArr[i]));

                            }
                        }
                    }

                }

                System.out.println("total word count = " + totalCount);

                for (String key: countWord.keySet()){
                    wordCountList.add(countWord.get(key));
                }

                wordCountList.sort(Comparator.comparing(WordCount::getCount).reversed());

                for (int i =0; i < 10; i++){

                    Double termFreq = (double) wordCountList.get(i).getCount()/totalCount;
                    topTermFreq.put(wordCountList.get(i).getWord(), termFreq);
                    System.out.println("Top " + (i+1) + " word is [" + wordCountList.get(i).getWord() + "] , term frequency = " + termFreq );

                }

            }

        }
    }
}
