package GA;

import GA.Paper;
import org.junit.Test;

public class SelectedTest {

    @Test
    public void selectedChoice(){
        //System.out.println("1 means this question is selected,0 means this question is not selected!!!");
        int[] choiceChrom;
        Paper A = new Paper();
        choiceChrom = new int[10];
        A.selected(choiceChrom,4);
        System.out.println("Selected for choicequestion:");
        for(int a:choiceChrom)
            System.out.print(a);
            System.out.println();
    }

    @Test
    public void selectedBlank(){

        int[] blankChrom;
        Paper A = new Paper();
        blankChrom = new int[10];
        A.selected(blankChrom,2);
        System.out.println("Selected for blankquestion:");
        for(int a:blankChrom)
            System.out.print(a);
        System.out.println();
    }
    @Test
    public void selectedCalculate(){

        int[] calculateChrom;
        Paper A = new Paper();
        calculateChrom = new int[10];
        A.selected( calculateChrom,1);
        System.out.println("Selected for calculatequestion:");
        for(int a:calculateChrom)
            System.out.print(a);
        System.out.println();
    }
    @Test
    public void selectedShortAnswer(){

        int[] shortAnswerChrom;
        Paper A = new Paper();
        shortAnswerChrom = new int[10];
        A.selected(shortAnswerChrom,2);
        System.out.println("Selected for shortAnswerquestion:");
        for(int a:shortAnswerChrom)
            System.out.print(a);
        System.out.println();
    }
}
