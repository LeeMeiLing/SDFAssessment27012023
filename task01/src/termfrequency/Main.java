package termfrequency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import termfrequency.WordCount;

public class Main {
    
    public static void main(String[] args) throws IOException{

        String fileName = null;
        String input;
        Map<String, WordCount> countWord = new HashMap<>();
        Map<String, Double> topTermFreq = new LinkedHashMap<>();
        List<WordCount> wordCountList = new LinkedList<>();
        int totalCount = 0;
        // int debugCount = 0; // debug
        WordCount temp;

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

            System.out.println("reading file" + file.getName()); // debug

            // do the following if file exist

            //using try-with-resource
            try(BufferedReader br = new BufferedReader(new FileReader(file))){
                
                while ((input = br.readLine()) != null) {  // remove debug count later

                    if(input.isBlank()){
                        continue; // skip line if blank
                    }

                    String[] inputArr = input.trim().split(" ");

                    for (int i = 0; i < inputArr.length; i++){

                        inputArr[i] = inputArr[i].toLowerCase().replaceAll("[\\.*,*:*!*\\-*\\(*\\)*{*}*\\'*\"*]", "");
                       // System.out.println("after replaceAll: " + inputArr[i]); // debug
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

                    // debugCount++;

                }// end of while loop

                //System.out.println(countWord); // debug
                System.out.println("total count = " + totalCount); // debug
                //System.out.printf("%f" , (double) 12/100);// debug

                for (String key: countWord.keySet()){
                    wordCountList.add(countWord.get(key));
                }

                wordCountList.sort(Comparator.comparing(WordCount::getCount).reversed());
                //System.out.println(wordCountList); // debug

                for (int i =1; i <= 10; i++){

                    Double termFreq = (double) wordCountList.get(i).getCount()/totalCount;
                    topTermFreq.put(wordCountList.get(i).getWord(), termFreq);
                    System.out.println("Top " + i + " word is " + wordCountList.get(i).getWord() + ", term frequency = " + termFreq );

                }

            }//end of try-with-resource

        }
    }
}
