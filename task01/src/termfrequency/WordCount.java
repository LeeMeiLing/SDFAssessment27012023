package termfrequency;

public class WordCount {
    
    private String word;
    private Integer count;

    public WordCount(String word) {
        this.word = word;
        this.count = 1;
    }

    public String getWord() {
        return word;
    }

    public Integer getCount() {
        return count;
    }

    public void addCount(){
        this.count++;
        //System.out.println(this.word + " count = " + this.count); //debug
    }

    @Override
    public String toString() {
        return "WordCount [word=" + word + ", count=" + count + "]";
    }

    
}
