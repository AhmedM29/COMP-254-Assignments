package Exercise2;

import java.util.Stack;

public class StackTransfer {

    // this method transfers all elements from stack S to stack T
    public static void transfer(Stack<Integer> S, Stack<Integer> T) {
        // check if either of the stacks are null to prevent any exceptions
        if (S == null || T == null) {
            throw new IllegalArgumentException("Stacks cannot be null");
        }

        // a temporary stack to hold the elements in reverse order
        Stack<Integer> tempStack = new Stack<>();
        // pop elements from S and push to tempStack, reversing them
        while (!S.isEmpty()) {
            tempStack.push(S.pop());
        }

        // now transfer the elements from tempStack to T, preserving the order
        while (!tempStack.isEmpty()) {
            T.push(tempStack.pop());
        }
    }

    //main method to actually run
    public static void main(String[] args) {
        // creating two stacks, S and T
        Stack<Integer> S = new Stack<>();
        Stack<Integer> T = new Stack<>();

        // pushing rand numbers
        S.push(1);
        S.push(2);
        S.push(3);

        // print the contents of S and T before the transfer
        System.out.println("Stack S before transfer: " + S);
        System.out.println("Stack T before transfer: " + T);

        // do the actual transfer
        transfer(S, T);

        // print final
        System.out.println("Stack S after transfer: " + S);
        System.out.println("Stack T after transfer: " + T);

    }
}
