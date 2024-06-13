package cd_programs;

import java.util.Scanner;

public class OperatorPrecedenceParser 
{
    public static void main(String[] args) 
    {
    	Scanner sc = new Scanner(System.in);
    	
        char[] stack = new char[20];
        char[] ip = new char[20];
        char[] ter = new char[10];
        char[][][] opt = new char[10][10][1];
        int i, j, k, n, top = 0, col = 0, row = 0;

        for (i = 0; i < 10; i++)
        {
            stack[i] = 0;
            ip[i] = 0;
            for (j = 0; j < 10; j++) 
                opt[i][j][0] = 0;
        }

        System.out.print("Enter the no. of terminals:");
        n = sc.nextInt();
        System.out.print("\nEnter the terminals:");
        ter = sc.next().toCharArray();

        System.out.println("\nEnter the table values:");
        for (i = 0; i < n; i++) 
        {
            for (j = 0; j < n; j++) 
            {
                System.out.print("\nEnter the value for "+ter[i]+" "+ter[j]+":");
                opt[i][j] = sc.next().toCharArray();
            }
        }

        System.out.println("\nOPERATOR PRECEDENCE TABLE:");
        for (i = 0; i < n; i++) 
            System.out.print("\t" + ter[i]);
        System.out.println();

        for (i = 0; i < n; i++) 
        {
            System.out.println();
            System.out.print(ter[i]);
            for (j = 0; j < n; j++)
                System.out.print("\t" + opt[i][j][0]);
        }

        
        stack[top] = '$';
        System.out.print("\nEnter the input string:");
        String input = sc.next();
        ip = input.toCharArray();
        i = 0;
        System.out.println("\nSTACK\t\t\tINPUT STRING\t\t\tACTION");
        System.out.print("\n" + String.valueOf(stack) + "\t\t\t" + input + "\t\t\t");

        while (i <= input.length())
        {
            for (k = 0; k < n; k++)
            {
                if (stack[top] == ter[k])
                    col = k;
                if (ip[i] == ter[k])
                    row = k;
            }

            if ((stack[top] == '$') && (ip[i] == '$')) 
            {
                System.out.println("ACCEPT");
                break;
            }
            else if ((opt[col][row][0] == '<') || (opt[col][row][0] == '=')) 
            {
                stack[++top] = opt[col][row][0];
                stack[++top] = ip[i];
                System.out.println("Shift " + ip[i]);
                i++;
            }
            else 
            {
                if (opt[col][row][0] == '>')
                {
                    while (stack[top] != '<') 
                        --top;
                    top = top - 1;
                    System.out.println("Reduce");
                } 
                else 
                {
                    System.out.println("\nERROR");
                    break;
                }
            }
            
            System.out.println();
            
            for (k = 0; k <= top; k++) 
                System.out.print(stack[k]);
            
            System.out.print("\t\t\t");
            
            for (k = i; k < input.length(); k++) 
                System.out.print(ip[k]);
            
            System.out.print("\t\t\t");
        }
    }
}
