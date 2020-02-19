/**
 * CSCI251ProjectTwo: Use MyStack and MyQueue to write a project that check if a sentence is palindrom
 * 
 * @author Singhtaravirak Chea
 * @version 25-Feb-2019 
 */
import java.util.Scanner;

public class CSCI251ProjectTwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
        String sentence;
        String again;
        do{
            System.out.println("Enter a sentence, I will tell you if it is a palindrome: ");
            sentence = input.nextLine();
            if(isPalindrome(sentence))
                System.out.println("\"" + sentence + "\" is a palindrome!");
            else
                System.out.println("\"" + sentence + "\" is not a palindrome!");
            System.out.println("Do you want another test (\"YES\" or \"NO\"): ");
            again = input.nextLine();
        }while(again.equalsIgnoreCase("YES"));
        
	}
	/**
     * isPalindrom returns true if the given String is a palindrom
     * @
     */
    public static boolean isPalindrome(String sentence)
    {
        // declare a MyStack s
    	MyStack s = new MyStack(); 
        // declare a MyQueue q
    	MyQueue q = new MyQueue(); 
        for(int i = 0; i < sentence.length(); i++)
        {
            // if ith character in sentence is a letter
                // convert to upper case and push it into s and q
        	char c = sentence.charAt(i); 
        	if(Character.isLetter(c)) {
        		char upperCaseLetter = Character.toUpperCase(c);
        		s.push(upperCaseLetter);
        		q.push(upperCaseLetter);
        	}
        }
        //while(!s.isEmpty()){
            // if the front of the queue not match the top of stack
                // return false
            // pop out top of the stack and front of the queue
        //}
        while(!s.isEmpty()) {
        	if(q.peek() != s.peek()) {
        		return false; 
        	}
        	s.pop(); 
        	q.pop(); 
        }
        
        return true;
    }

}
