package CodeGame;

import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Person{
    public String name;
    public int dice1;
    public int dice2;
    public int position = 0;
    public int jailCounter = 0;
    public int doubleDice = 0;

    public Person() {
    }
}

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int P = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }
        ArrayList<Person> arr = new ArrayList<Person>();
        for (int i = 0; i < P; i++) {
            Person newPlayer = new Person();
            String playerName = in.nextLine();
            String[] str = playerName.trim().split("\\s+");
            newPlayer.name = str[0];
            newPlayer.position = Integer.parseInt(str[1]);
            arr.add(newPlayer);
        }


        int D = in.nextInt();
        if (in.hasNextLine()) {
            in.nextLine();
        }

        //aux for iterating in player array
        int j = 0;
        Person player; // create current player object

        for (int i = 0; i < D; i++) {
            String dice = in.nextLine();
            String[] strs = dice.trim().split("\\s+");
            int dice1 = Integer.parseInt(strs[0]);
            int dice2 = Integer.parseInt(strs[1]);

            player = arr.get(j);    // initialize current player object

            player.dice1 = dice1;       //assign 1st dice
            player.dice2 = dice2;       //assign 2nd dice

            //doubles
            if (player.dice1 == player.dice2) {
                player.doubleDice += 1;
                if (player.jailCounter != 0) {
                    player.jailCounter = 0;
                    player.doubleDice = 0;   //useless double because used for early release
                }
            }

            //In-jail rules
            if (player.jailCounter != 0) {
                player.jailCounter--;
            }

            //goto new Position
            if (player.jailCounter == 0) player.position += player.dice1 + player.dice2;

            //three doubles
            if (player.doubleDice == 3) {
                player.position = 10;
                player.jailCounter = 3;
                player.doubleDice = 0; //useless double
            }

            //goto jail
            if (player.position == 30) {
                player.position = 10;
                player.jailCounter = 3;
                player.doubleDice = 0; //useless double
            }

            //round board
            if (player.position > 39 ) player.position -= 40;


            //pass to next player
            if ( (j < P) && (player.doubleDice == 0))
                j++;
            else
                player.doubleDice=0;
            if (j == P ) j=0;
        }

        for (int i = 0; i < 40; i++) {
            String boardline = in.nextLine();
        }

        for (Person plyr: arr
        ) {
            System.out.println(plyr.name + " " + plyr.position);
        }

    }
}
