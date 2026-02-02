import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class wordguessgame {
    public static void main(String[] args) {
        Scanner ize = new Scanner(System.in);
        Random r = new Random();

        int maxLives = 6;
        String[] words = {
                "computer", "java", "money", "cat", "dog", "keyboard", "mouse", "study", "alien", "graphics card", "cat poster", "steak", "lava lamp", "mouse pad","work",
        };

        System.out.println("Welcome to the Word Guess Game, the system will pick a random word for you, which you have to guess letter by letter.\nThere is no number or special characters in the words, apart from the Space in some. The program will indicate it.\nYou have 6 lives, if you fail to guess the word in 6 tries, the game is over.");
        System.out.println();

        do {
            int lives = maxLives;
            String wordToGuess = words[new Random().nextInt(words.length)]; //Picks a random word
            int wordLength = wordToGuess.length();
            String[] wordHidden = new String[wordLength]; // the hidden version of the word ( _ _ _ _ _ or spaces)
            String guessedLetters = ""; //Keeps track of letters already guessed
            System.out.println("The word's length is:  " + wordLength + "  characters.\nYou have " + lives + " lives left.");


            for (int i = 0; i < wordLength; i++) {
                if (wordToGuess.charAt(i) == ' ') {
                    wordHidden[i] = " ";
                } else {
                    wordHidden[i] = "_";
                }
            }

            while (true) {
                //Print the current state of the word
                for (int i = 0; i < wordHidden.length; i++) {
                    System.out.print(wordHidden[i] + " ");
                }
                System.out.println();

                printHangman(maxLives - lives, maxLives); //Draws the hangman

                String guess = "";
                char c;
                while (true) {
                    System.out.print("Guess a letter: ");
                    guess = ize.nextLine().trim();

                    if (guess.length() != 1) {
                        System.out.println("The guess must be exactly 1 character!");
                        continue;
                    }

                    c = Character.toLowerCase(guess.charAt(0));

                    if (!((c >= 'a' && c <= 'z'))) {
                        System.out.println("Only A-Z characters are available.");
                        continue;
                    }

                    //Checks if this letter was already guessed
                    if (guessedLetters.indexOf(c) != -1) {
                        System.out.println("You have already guessed that letter.");
                        continue;
                    }

                    guessedLetters += c;
                    break;
                }

                int guessedInt = 0;
                //Updates the hidden word array if the guessed letter is in the word
                for (int i = 0; i < wordLength; i++) {
                    if (wordToGuess.toLowerCase().charAt(i) == c) {
                        wordHidden[i] = String.valueOf(c); //Replaces underscore with the guessed letter
                        guessedInt++;
                    }
                }

                if (guessedInt == 0) {
                    lives--;
                    System.out.println("You have guessed a wrong letter. -1 life.");
                }
                System.out.println("You have: " + lives + " lives");

                //Check if the whole word has been guessed
                if (String.join("", wordHidden).equalsIgnoreCase(wordToGuess)) {
                    for (int i = 0; i < wordHidden.length; i++) {
                        System.out.print(wordHidden[i] + " ");
                    }
                    System.out.println();
                    printHangman(maxLives - lives, maxLives);
                    System.out.println("\nCongratulations! You have guessed the word! Good job!\nThe word was: " + wordToGuess);
                    break;
                }

                //Check if player ran out of lives
                if (lives <= 0) {
                    for (int i = 0; i < wordHidden.length; i++) {
                        System.out.print(wordHidden[i] + " ");
                    }
                    System.out.println();
                    printHangman(maxLives - lives, maxLives);
                    System.out.println("\nGame over, you ran out of lives. The word was: " + wordToGuess);
                    break;
                }
            }

            String replay;
            System.out.println("\nDo you want to replay? Yes or No");
            replay = ize.nextLine();

            while (true) {
                if (replay.toLowerCase().equals("yes") || replay.toLowerCase().equals("no")) {
                    break;
                } else {
                    System.out.println("Please answer with either Yes or No.");
                    replay = ize.nextLine();
                }
            }
            if (replay.toLowerCase().equals("no")) {
                System.out.println("\nGame is over, you quit.");
                break;
            }
            System.out.println();
        } while (true);
    }

    //Draws the hangman based on number of wrong guesses
    public static void printHangman(int wrongGuesses, int maxLives) {
        String[] hangman = new String[7];
        hangman[0] = "  _______";
        hangman[1] = " |     |";
        hangman[2] = " |      ";
        hangman[3] = " |      ";
        hangman[4] = " |      ";
        hangman[5] = " |      ";
        hangman[6] = "_|_";

        if (wrongGuesses >= 1) hangman[2] = " |     O"; //Head
        if (wrongGuesses >= 2) hangman[3] = " |     |"; //Torso
        if (wrongGuesses >= 3) hangman[3] = " |    /|"; //Left arm
        if (wrongGuesses >= 4) hangman[3] = " |    /|\\"; //Both arms
        if (wrongGuesses >= 5) hangman[4] = " |    /"; //Left leg
        if (wrongGuesses >= 6) hangman[4] = " |    / \\"; //Both legs

        for (String s : hangman) {
            System.out.println(s);
        }
    }
}