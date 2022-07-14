package com.kenzie.app;

// import necessary libraries

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;


/////


public abstract class Main {

    /* Java Fundamentals Capstone project:
       - Define as many variables, properties, and methods as you decide are necessary to
       solve the program requirements.
       - You are not limited to only the class files included here
       - You must write the HTTP GET call inside the CustomHttpClient.sendGET(String URL) method
         definition provided
       - Your program execution must run from the main() method in Main.java
       - The rest is up to you. Good luck and happy coding!
     */

    static final String INPUT_FILE = "https://jservice.kenzie.academy/api/clues/";

    // If needed to get names of categories
    static final String INPUT_FILE2 = "https://jservice.kenzie.academy/api/categories/";

    public static String formatURL(String URLString, int clueNumber) {

        String Url = URLString + clueNumber;
        return Url;
    }

    //Request
//    public static String sendGET(String URLString) {
//////
//        HttpClient client = HttpClient.newHttpClient();
//        URI uri = URI.create(URLString);
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(uri)
//                .header("Accept", "application/json")
//                .GET()
//                .build();
//        try {
//            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
//            int statusCode = httpResponse.statusCode();
//            if (statusCode == 200) {
//                return httpResponse.body();
//            } else {
//                return String.format("GET request failed: %d status code received", statusCode);
//            }
//        } catch (IOException | InterruptedException e) {
//            return e.getMessage();
//        }
//    }

    public static String formatCluesOutput(String jsonString) throws ParseException, IOException{
        //TODO: Fill out method and update return value

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CluesDTO cluesDTO = objectMapper.readValue(jsonString, CluesDTO.class);
            return cluesDTO.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "No activity found";
        }
    }

    public static String findSpecificClue(int clueNumber) throws com.fasterxml.jackson.core.JsonProcessingException, IOException {

        String specificClue = CustomHttpClient.sendGET(formatURL(INPUT_FILE, clueNumber));

        return specificClue;
    }

//    public static String readQuestion(){
//
//        FileInputStream inputStream = new FileInputStream(specificClue);
//        InputStreamReader inputReader = new InputStreamReader(inputStream);
//        BufferedReader bReader = new BufferedReader(inputReader);
//
//        String line;
//        while (( line = bReader.readLine()) != null){
//
//    }
//    return line;
//    }

    public static int randomNumber() {

        int min = 1;
        int max = 3000;

        Random random = new Random();
        int upperbound =3000;
        int int_random = random.nextInt(upperbound);

        //  double random = Math.floor(Math.random() * (max - min + 1) + min);
        // return (int) random;

        return int_random;
    }

    public static String randomIncorrectResponse() {

        int min = 1;
        int max = 5;

        double random = Math.floor(Math.random() * (max - min + 1) + min);

        String one = ". Better luck next time.";
        String two = ". Don't give up, You got this!";
        String three = ". Alex Trebek would be disappointed.";
        String four = ". You know the purpose of the game is to get the right answer... right?...";
        String five = ". That's a bummer, the next one is easier, promise.";
        String six = ". Nice try.";
        String seven = ". Close but no cigar.";

        if (random == 1) {
            return one;
        }
        if (random == 2) {
            return two;
        }
        if (random == 3) {
            return three;
        }
        if (random == 4) {
            return four;
        }
        if (random == 5) {
            return five;
        }
        if (random == 6) {
            return six;
        }
        if (random == 7) {
            return seven;
        }

        return "";
    }

    public static String numberToString(int number) {

        String zero = "zero";
        String one = "one";
        String two = "two";
        String three = "three";
        String four = "four";
        String five = "five";
        String six = "six";
        String seven = "seven";
        String eight = "eight";
        String nine = "nine";
        String ten = "ten";

        if (number == 0) {
            return zero;
        }
        if (number == 1) {
            return one;
        }
        if (number == 2) {
            return two;
        }
        if (number == 3) {
            return three;
        }
        if (number == 4) {
            return four;
        }
        if (number == 5) {
            return five;
        }
        if (number == 6) {
            return six;
        }
        if (number == 7) {
            return seven;
        }
        if (number == 8) {
            return eight;
        }
        if (number == 9) {
            return nine;
        }
        if (number == 10) {
            return ten;
        }
        return "";
    }

    @SuppressWarnings("unchecked")
    public static String pullTitle(int num) throws JsonProcessingException {

        String category = CustomHttpClient.sendGET(formatURL(INPUT_FILE2, num));
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> result = objectMapper.readValue(category, HashMap.class);
            String answer = (String) result.get("title");

            return answer.toLowerCase();
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public static void endGame(int number) {

        if (number == 10) {
            System.out.println("Wow, a perfect score " + numberToString(number) + " out of ten, amazingly done! ");
        }
        if (number >= 6) {
            System.out.println("Great work, you got " + numberToString(number) + " questions correct. Your mom would be really proud. ");
        }
        if (number >= 1 && number < 6) {
            System.out.println("Well that wasn't the best performance that I have seen, but you did get " + numberToString(number) + " right.");
        }
        if (number == 0) {
            System.out.println("Maybe you should try a different game. Seeing that you didn't get any questions correct");
        }
    }

    @SuppressWarnings("unchecked")
    public static HashMap triviaData(int counter, String question, Boolean correct) throws NullPointerException {

        ArrayList<Map<String, Object>> endFormat = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put("Question Number ", String.valueOf(counter));
        map.put("Question ", question);
        map.put("Was Right ", String.valueOf(correct));

        return (HashMap) map;
    }

    @SuppressWarnings("unchecked")
    public static String printTriviaData(HashMap list){

        Map<String, Object> map = new HashMap<>(list);
        Set<String> keys = map.keySet();
        ArrayList<String> keyList = new ArrayList<>(keys);

        Collection<Object> values = map.values();
        ArrayList<Object> valuesList = new ArrayList<Object>(values);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 1; j++) {
                System.out.print(keyList.get(i) + " : ");
                System.out.print(valuesList.get(i));
                System.out.println();
            }
        }
        Set<Map.Entry<String,Object>> entrySet = map.entrySet();
        ArrayList<Map.Entry<String, Object>> listOfEntry = new ArrayList<>(entrySet);

        return String.valueOf(map);
    }

    public static HashMap storeTriviaData(){

        //ArrayList<Map<String, Object>> endFormat = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();

        return map;
    }

    @SuppressWarnings("unchecked")
    public static void game(int numberOfQuestions){

        System.out.println("\n Welcome to Kenzie Trivia! " +
                "\n The most random facts known to man. " +
                "\n \n You have " + numberToString(numberOfQuestions) + " questions, and will receive one point for each correct answer." +
                "\n Good Luck! " +
                "\n");

        Scanner userInput = new Scanner(System.in);

        int counter = 1;
        int correctCounter = 0;
        Boolean isCorrect = true;

        while (counter != numberOfQuestions + 1) {
            String response;
            try {
                //Question counter
                System.out.println("Question " + counter);
                //Random number
                int random = randomNumber();
                // Finding a random clue using the URL
                String specificClue = (findSpecificClue(random));

                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> result = mapper.readValue(specificClue, HashMap.class);
                // Pulling question and answer saving as a String value
                String valueOfQuestion = (String)result.get("question");
                String valueOfAnswer = (String)result.get("answer");
                // Displaying the score after the first question
                if (!(counter == 1)) {
                    if (correctCounter == 0) {
//                        System.out.println("You have not gotten any questions right.");
                        System.out.println("Score: " + correctCounter + "/" + (counter -1));
                    } else {
//                        System.out.println("You have gotten " + numberToString(correctCounter) + " question right out of " + numberToString(counter - 1) + ".");
                        System.out.println("Score: " + correctCounter + "/" + (counter-1));
                    }
                }
                // Not giving a second chance for empty String
//                System.out.println("The category is " + pullTitle(random));
//
//                System.out.println(valueOfQuestion);
//                System.out.println("Whats Your Answer?");
//
//                String answer = userInput.nextLine();
//                String formatAnswer = answer.toLowerCase();
//
//
//                if (formatAnswer.equalsIgnoreCase(valueOfAnswer)){
//                    System.out.println("Correct! Nice work keep it up!" + "\n");
//                    correctCounter++;
//                    isCorrect = true;
//                } else {
//                    System.out.println("That is incorrect, the answer is, " + valueOfAnswer + randomIncorrectResponse() + "\n");
//                    isCorrect = false;
//                }

                // Giving a second chance for an empty String
                System.out.println("The category is " + pullTitle(random));

                System.out.println(valueOfQuestion);
                System.out.println("Whats Your Answer?");

                String answer = userInput.nextLine();
                String formatAnswer = answer.toLowerCase();

                if (!(formatAnswer.isEmpty())){
                    if (formatAnswer.equalsIgnoreCase(valueOfAnswer)) {
                        System.out.println("Correct! Nice work keep it up!" + "\n");
                        correctCounter++;
                        isCorrect = true;
                    } else {
                        System.out.println("That is incorrect, the answer is, " + valueOfAnswer + randomIncorrectResponse() + "\n");
                        isCorrect = false;
                    }
                } if (formatAnswer.isEmpty()){
                    do {
                        System.out.println("Not a valid response, You have one more chance to provide a valid answer.");
                        System.out.println("The category is " + pullTitle(random));
                        System.out.println(valueOfQuestion);
                        System.out.println("What's Your Answer?");

                        String answer2 = userInput.nextLine();
                        String formatSecondAnswer = answer2.toLowerCase();

                        if (formatSecondAnswer.equalsIgnoreCase(valueOfAnswer)) {
                            System.out.println("Correct! Nice work keep it up!" + "\n");
                            correctCounter++;
                            isCorrect = true;
                        } else {
                            System.out.println("That is incorrect, the answer is, " + valueOfAnswer + randomIncorrectResponse() + "\n");
                            isCorrect = false;
                        }
                    } while (!(formatAnswer.isEmpty()));
                }

                storeTriviaData().put("questionID : ", counter);
                storeTriviaData().put("question : ", valueOfQuestion);
                storeTriviaData().put("wasCorrect : ", isCorrect);

            } catch (Exception e){
                System.out.println(e.getMessage());
            }
            counter++;
        }
        System.out.println("Your final score is : " + correctCounter + "/" + (counter-1));
        endGame(correctCounter);
    }

    //V.S mode
    public static boolean checkingAnswer(String player, String formatAnswer, String valueOfAnswers){

    boolean isRight = true;

            if (formatAnswer.equalsIgnoreCase(valueOfAnswers)) {
                System.out.println(" Player " + player + " Your answer was Correct! Nice work keep it up!");
                isRight = true;
            } else {
                System.out.println("Player " + player + ", that was not the correct answer.");
                isRight = false;
            }
            return isRight;
    }

    @SuppressWarnings("unchecked")
    public static void gameVSMode(int numberOfQuestions, int numberOfPlayers){

    if (numberOfPlayers == 1){
        game(numberOfQuestions);
    }
        System.out.println("\n Welcome to Kenzie Trivia v.s Mode!!! " +
                "\n The most random facts known to man. " +
                "\n \n You have " + numberToString(numberOfQuestions) + " total questions, each player will receive one point for each correct answer." +
                "\n Who ever has the most points in the end will be Crowned the champion of Kenzie Trivia!! " +
                "\n Good Luck! " +
                "\n");

        Scanner userInput = new Scanner(System.in);
        Scanner userInput2 = new Scanner(System.in);
        Scanner userInput3 = new Scanner(System.in);
        Scanner userInput4 = new Scanner(System.in);

    int counter = 1;

    int player1CorrectCounter = 0;
    int player2CorrectCounter = 0;
    int player3CorrectCounter = 0;
    int player4CorrectCounter = 0;

    Boolean isCorrect = true;

        while (counter != numberOfQuestions + 1) {

            String response;
            try {
                //Question counter
                System.out.println("Question " + counter);
                //Random number
                int random = randomNumber();
                // Finding a random clue using the URL
                String specificClue = (findSpecificClue(random));

                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> result = mapper.readValue(specificClue, HashMap.class);

                // Pulling question and answer saving as a String value
                String valueOfQuestion = (String)result.get("question");
                String valueOfAnswer = (String)result.get("answer");

                // Displaying the score after the first question
                if (numberOfPlayers == 2){
                    if(!(counter == 1)) {
                        System.out.println("Score: " + player1CorrectCounter + "/" + (counter - 1));
                        System.out.println("Score: " + player2CorrectCounter + "/" + (counter - 1) + "\n");
                    }
                }
                if (numberOfPlayers == 3){
                    if(!(counter == 1)) {
                        System.out.println("Score: " + player1CorrectCounter + "/" + (counter - 1));
                        System.out.println("Score: " + player2CorrectCounter + "/" + (counter - 1));
                        System.out.println("Score: " + player3CorrectCounter + "/" + (counter - 1) + "\n");
                    }
                }
                if (numberOfPlayers == 4){
                    if(!(counter == 1)) {
                        System.out.println("Score: " + player1CorrectCounter + "/" + (counter - 1));
                        System.out.println("Score: " + player2CorrectCounter + "/" + (counter - 1));
                        System.out.println("Score: " + player3CorrectCounter + "/" + (counter - 1));
                        System.out.println("Score: " + player4CorrectCounter + "/" + (counter - 1) + "\n");
                    }
                }
                // Not giving a second chance for empty String
//                System.out.println("The category is " + pullTitle(random));
//
//                System.out.println(valueOfQuestion);
//                System.out.println("Whats Your Answer?");
//
//                String answer = userInput.nextLine();
//                String formatAnswer = answer.toLowerCase();
//
//
//                if (formatAnswer.equalsIgnoreCase(valueOfAnswer)){
//                    System.out.println("Correct! Nice work keep it up!" + "\n");
//                    correctCounter++;
//                    isCorrect = true;
//                } else {
//                    System.out.println("That is incorrect, the answer is, " + valueOfAnswer + randomIncorrectResponse() + "\n");
//                    isCorrect = false;
//                }

                // Giving a second chance for an empty String
                System.out.println("The category is " + pullTitle(random));
                System.out.println(valueOfQuestion);

                if (numberOfPlayers == 2){
                    System.out.println("Player one, what's your answer?");
                    String user1Answer = userInput.nextLine();
                    String format1Answer = user1Answer.toLowerCase();

                    System.out.println("Player two, what's your answer?");
                    String user2Answer = userInput2.nextLine();
                    String format2Answer = user2Answer.toLowerCase();

                   if (checkingAnswer("one",format1Answer,valueOfAnswer) == true) {
                       player1CorrectCounter ++;
                   }
                   if (checkingAnswer("two", format2Answer, valueOfAnswer) == true) {
                       System.out.println("\n \n");
                       player2CorrectCounter++;
                   }
                    System.out.println("The correct answer was " + valueOfAnswer + "\n \n");
                }

                if (numberOfPlayers == 3){
                    System.out.println("Player one, what's your answer?");
                    String user1Answer = userInput.nextLine();
                    String format1Answer = user1Answer.toLowerCase();

                    System.out.println("Player two, what's your answer?");
                    String user2Answer = userInput2.nextLine();
                    String format2Answer = user2Answer.toLowerCase();

                    System.out.println("Player three, what's your answer?");
                    String user3Answer = userInput3.nextLine();
                    String format3Answer = user3Answer.toLowerCase();

                    if (checkingAnswer("one",format1Answer,valueOfAnswer) == true) {
                        player1CorrectCounter ++;
                    }
                    if (checkingAnswer("two", format2Answer, valueOfAnswer) == true) {
                        player2CorrectCounter++;
                    }
                    if (checkingAnswer("three", format3Answer, valueOfAnswer) == true){
                        player3CorrectCounter ++;
                    }
                    System.out.println("\n ");
                    System.out.println("The correct answer was " + valueOfAnswer+ "\n \n");
                }

                if (numberOfPlayers == 4){
                    System.out.println("Player one, what's your answer?");
                    String user1Answer = userInput.nextLine();
                    String format1Answer = user1Answer.toLowerCase();

                    System.out.println("Player two, what's your answer?");
                    String user2Answer = userInput2.nextLine();
                    String format2Answer = user2Answer.toLowerCase();

                    System.out.println("Player three, what's your answer?");
                    String user3Answer = userInput3.nextLine();
                    String format3Answer = user3Answer.toLowerCase();

                    System.out.println("Player four, what's your answer?");
                    String user4Answer = userInput4.nextLine();
                    String format4Answer = user4Answer.toLowerCase();

                    if (checkingAnswer("one",format1Answer,valueOfAnswer) == true) {
                        player1CorrectCounter ++;
                    }
                    if (checkingAnswer("two", format2Answer, valueOfAnswer) == true) {
                        player2CorrectCounter++;
                    }
                    if (checkingAnswer("three", format3Answer, valueOfAnswer) == true){
                        player3CorrectCounter ++;
                    }
                    if (checkingAnswer("four", format4Answer, valueOfAnswer) == true){
                        player4CorrectCounter ++;
                    }

                    System.out.println("\n");
                    System.out.println("The correct answer was " + valueOfAnswer+ "\n \n");
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
           counter++;
        }

        if (numberOfPlayers == 2) {
            System.out.println("The Final Scores");
            System.out.println(player1CorrectCounter + "/" + (counter - 1));
            System.out.println(player2CorrectCounter + "/" + (counter - 1));

            if (player1CorrectCounter > player2CorrectCounter) {
                System.out.println("Player one wins with a score of " + numberToString(player1CorrectCounter) + " points!");
            }
            if (player2CorrectCounter > player1CorrectCounter) {
                System.out.println("Player two wins with a score of " + numberToString(player2CorrectCounter) + " points!");
            }
            if (player1CorrectCounter == player2CorrectCounter) {
                System.out.println("Great game everyone. Unfortunately there was no decisive winner today.");
            }
        }

            if (numberOfPlayers == 3) {
                System.out.println("The Final Scores");
                System.out.println(player1CorrectCounter + "/" + (counter - 1));
                System.out.println(player2CorrectCounter + "/" + (counter - 1));
                System.out.println(player3CorrectCounter + "/" + (counter - 1));

                if (player1CorrectCounter > player2CorrectCounter && player1CorrectCounter > player3CorrectCounter) {
                    System.out.println("Player one wins with a score of " + numberToString(player1CorrectCounter) + " points!");
                }
                if (player2CorrectCounter > player1CorrectCounter && player2CorrectCounter > player3CorrectCounter) {
                    System.out.println("Player two wins with a score of " + numberToString(player2CorrectCounter) + " points!");
                }
                if (player3CorrectCounter > player1CorrectCounter && player3CorrectCounter > player2CorrectCounter) {
                    System.out.println("Player three wins with a score of " + numberToString(player3CorrectCounter) + " points!");
                }
                if (player1CorrectCounter == player2CorrectCounter && player2CorrectCounter == player3CorrectCounter){
                    System.out.println("Great game everyone. Unfortunately there was no decisive winner today.");
                }
            }

            if (numberOfPlayers == 4) {
                System.out.println("The Final Scores");
                System.out.println(player1CorrectCounter + "/" + (counter - 1));
                System.out.println(player2CorrectCounter + "/" + (counter - 1));
                System.out.println(player3CorrectCounter + "/" + (counter - 1));
                System.out.println(player4CorrectCounter + "/" + (counter - 1));

                if (player1CorrectCounter > player2CorrectCounter && player1CorrectCounter > player3CorrectCounter && player1CorrectCounter > player4CorrectCounter) {
                    System.out.println("Player one wins with a score of " + numberToString(player1CorrectCounter)+ " points!");
                }
                if (player2CorrectCounter > player1CorrectCounter && player2CorrectCounter > player3CorrectCounter && player2CorrectCounter > player4CorrectCounter) {
                    System.out.println("Player two wins with a score of " + numberToString(player2CorrectCounter) + " points!");
                }
                if (player3CorrectCounter > player1CorrectCounter && player3CorrectCounter > player2CorrectCounter && player3CorrectCounter > player4CorrectCounter) {
                    System.out.println("Player three wins with a score of " + numberToString(player3CorrectCounter) + " points!");
                }
                if (player4CorrectCounter > player1CorrectCounter && player4CorrectCounter > player2CorrectCounter && player4CorrectCounter > player3CorrectCounter) {
                    System.out.println("Player four wins with a score of " + numberToString(player4CorrectCounter) + " points!");
                }
                if (player1CorrectCounter == player2CorrectCounter && player2CorrectCounter == player3CorrectCounter && player3CorrectCounter == player4CorrectCounter){
                    System.out.println("Great game everyone. Unfortunately there was no decisive winner today.");
                }
            }
        }

    public static void fullGame(){
        String multiplayer = "multiplayer";
        String singlePlayer= "single player";

        try {
            Scanner gameplay = new Scanner(System.in);

            System.out.println("Welcome to Kenzie Trivia");
            System.out.println("Enter mode: multiplayer or single player");

            String input1 = gameplay.nextLine();
            String formatInput1 = input1.toLowerCase();

            if (input1.equalsIgnoreCase(multiplayer)){
                System.out.println( "How many players are you playing with? Your can play with up to four.");
                int players = gameplay.nextInt();

                if (players < 4 && players > 0){
                    System.out.println("How many questions would you and your friends like");
                    int questions = gameplay.nextInt();
                    gameVSMode(questions,players);
                }
                if (!(players < 4 && players > 0)){
                    System.out.println("Not a valid response");
                    System.out.println( "How many players are you playing with? You can play with up to four friends.");
                    int players1 = gameplay.nextInt();

                    System.out.println("How many questions would you and your friends like");
                    int questions = gameplay.nextInt();
                    gameVSMode(questions,players1);
                }
            }
            if (input1.equalsIgnoreCase(singlePlayer)){
                System.out.println( "How many questions would you like?");
                int questions = gameplay.nextInt();

                game(questions);
            }

            while(!(input1.equalsIgnoreCase( multiplayer) || input1.equalsIgnoreCase(singlePlayer))){

                System.out.println("Not a valid response");
                System.out.println("Please enter the mode in which you would like to play.");
                input1 = gameplay.nextLine();

                //Multiplayer option
                if (input1.equalsIgnoreCase(multiplayer)){

                    System.out.println( "How many players are you playing with? Your can play with up to four.");
                    int players = gameplay.nextInt();

                    if (players < 4 && players > 0){
                        System.out.println("How many questions would you and your friends like");
                        int questions = gameplay.nextInt();
                        gameVSMode(questions,players);
                    }

                    if (!(players < 4 && players > 0)){
                        System.out.println("Not a valid response");
                        System.out.println( "How many players are you playing with? You can play with up to four friends.");
                        int players1 = gameplay.nextInt();

                        System.out.println("How many questions would you and your friends like");
                        int questions = gameplay.nextInt();
                        gameVSMode(questions,players1);
                    }
                }

                //Single player option
                if (input1.equalsIgnoreCase(singlePlayer)){
                    System.out.println( "How many questions would you like?");
                    int questions = gameplay.nextInt();

                    game(questions);
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Not following directions looses your privilege to choose what you get to play.");
            game(10);
        }
    }

    public static String noPunctuation(String str){
       // String returnString = str.replaceAll("[^a-zA-Z0-9]", "");
        String returnString = str.replaceAll("\\p{Punct}", "");
        return returnString;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args){
        //Write main execution code here

        System.out.println("\n Welcome to Kenzie Trivia! " +
                "\n The most random facts known to man. " +
                "\n \n You have ten questions, and will receive one point for each correct answer." +
                "\n Good Luck! " +
                "\n");

    Scanner userInput = new Scanner(System.in);

    int counter = 1;
    int correctCounter = 0;
    Boolean isCorrect = true;

        while (counter != 11) {
            String response;
            try {
                //Question counter
                System.out.println("Question " + counter);

                //Random number
                int random = randomNumber();

                // Finding a random clue using the URL
                String specificClue = (findSpecificClue(random));

                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> result = mapper.readValue(specificClue, HashMap.class);

                // Pulling question and answer saving as a String value
                String valueOfQuestion = (String)result.get("question");
                String valueOfAnswer = (String)result.get("answer");
                String valueOfAnswerNoPunc = noPunctuation(valueOfAnswer);

                // Displaying the score after the first question
                if (!(counter == 1)) {
                    if (correctCounter == 0) {
//                        System.out.println("You have not gotten any questions right.");
                        System.out.println("Score: " + correctCounter + "/" + (counter -1));
                    } else {
//                        System.out.println("You have gotten " + numberToString(correctCounter) + " question right out of " + numberToString(counter - 1) + ".");
                        System.out.println("Score: " + correctCounter + "/" + (counter-1));
                    }
                }
                // Not giving a second chance for empty String
//                System.out.println("The category is " + pullTitle(random));
//
//                System.out.println(valueOfQuestion);
//                System.out.println("Whats Your Answer?");
//
//                String answer = userInput.nextLine();
//                String formatAnswer = answer.toLowerCase();
//
//
//                if (formatAnswer.equalsIgnoreCase(valueOfAnswer)){
//                    System.out.println("Correct! Nice work keep it up!" + "\n");
//                    correctCounter++;
//                    isCorrect = true;
//                } else {
//                    System.out.println("That is incorrect, the answer is, " + valueOfAnswer + randomIncorrectResponse() + "\n");
//                    isCorrect = false;
//                }

                // Giving a second chance for an empty String
                System.out.println("The category is " + pullTitle(random));

                System.out.println(valueOfQuestion);
                System.out.println("Whats Your Answer?");

                String answer = userInput.nextLine();
                String formatAnswer = answer.toLowerCase();
                String formatAnswerNoPunc = noPunctuation(formatAnswer);

                if (!(formatAnswer.isEmpty())){

                    if (formatAnswerNoPunc.equalsIgnoreCase(valueOfAnswer) || formatAnswerNoPunc.equalsIgnoreCase(valueOfAnswerNoPunc)) {
                        System.out.println("Correct! Nice work keep it up!" + "\n");
                        correctCounter++;
                        isCorrect = true;
                    } else {
                        System.out.println("That is incorrect, the answer is, " + valueOfAnswer + randomIncorrectResponse() + "\n");
                        isCorrect = false;
                    }
                } if (formatAnswer.isEmpty()){
                    do {
                        System.out.println("Not a valid response, You have one more chance to provide a valid answer.");
                        System.out.println("The category is " + pullTitle(random));
                        System.out.println(valueOfQuestion);
                        System.out.println("What's Your Answer?");

                        String answer2 = userInput.nextLine();
                        String formatSecondAnswer = answer2.toLowerCase();
                        String formatSecondAnswerNoPunc = noPunctuation(formatSecondAnswer);

                        if (formatSecondAnswerNoPunc.equalsIgnoreCase(valueOfAnswer) || formatSecondAnswerNoPunc.equalsIgnoreCase(valueOfAnswerNoPunc)) {
                            System.out.println("Correct! Nice work keep it up!" + "\n");
                            correctCounter++;
                            isCorrect = true;
                        } else {
                            System.out.println("That is incorrect, the answer is, " + valueOfAnswer + randomIncorrectResponse() + "\n");
                            isCorrect = false;
                        }
                    } while (!(formatAnswer.isEmpty()));
                }
                storeTriviaData().put("questionID : ", counter);
                storeTriviaData().put("question : ", valueOfQuestion);
                storeTriviaData().put("wasCorrect : ", isCorrect);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
           counter++;
        }

        System.out.println("Your final score is : " + correctCounter + "/" + (counter-1));
        endGame(correctCounter);

                                    //TODO EXTRA OPTIONAL FINISHING TOUCHES BELOW

        fullGame();

    }
    }
















































































//              Failed Attempts




// Running Full Game

//        String multiplayer = "multiplayer";
//        String singlePlayer= "single player";
//
//        try {
//
//            Scanner gameplay = new Scanner(System.in);
//
//            System.out.println("Welcome to Kenzie Trivia");
//            System.out.println("Enter mode: multiplayer or single player");
//
//            String input1 = gameplay.nextLine();
//            String formatInput1 = input1.toLowerCase();
//
//
//            if (input1.equalsIgnoreCase(multiplayer)){
//                System.out.println( "How many players are you playing with? Your can play with up to four.");
//                int players = gameplay.nextInt();
//
//                if (players < 4 && players > 0){
//                    System.out.println("How many questions would you and your friends like");
//                    int questions = gameplay.nextInt();
//                    gameVSMode(questions,players);
//                }
//                if (!(players < 4 && players > 0)){
//                    System.out.println("Not a valid response");
//                    System.out.println( "How many players are you playing with? You can play with up to four friends.");
//                    int players1 = gameplay.nextInt();
//
//                    System.out.println("How many questions would you and your friends like");
//                    int questions = gameplay.nextInt();
//                    gameVSMode(questions,players1);
//                }
//
//            }
//
//            if (input1.equalsIgnoreCase(singlePlayer)){
//                System.out.println( "How many questions would you like?");
//                int questions = gameplay.nextInt();
//
//                game(questions);
//            }
//
//            while(!(input1.equalsIgnoreCase( multiplayer) || input1.equalsIgnoreCase(singlePlayer))){
//
//                System.out.println("Not a valid response");
//                System.out.println("Please enter the mode in which you would like to play.");
//                input1 = gameplay.nextLine();
//
//                //Multiplayer option
//                if (input1.equalsIgnoreCase(multiplayer)){
//
//                    System.out.println( "How many players are you playing with? Your can play with up to four.");
//                    int players = gameplay.nextInt();
//
//                    if (players < 4 && players > 0){
//                        System.out.println("How many questions would you and your friends like");
//                        int questions = gameplay.nextInt();
//                        gameVSMode(questions,players);
//                    }
//
//                    if (!(players < 4 && players > 0)){
//                        System.out.println("Not a valid response");
//                        System.out.println( "How many players are you playing with? You can play with up to four friends.");
//                        int players1 = gameplay.nextInt();
//
//                        System.out.println("How many questions would you and your friends like");
//                        int questions = gameplay.nextInt();
//                        gameVSMode(questions,players1);
//                    }
//
//                }
//                //Single player option
//                if (input1.equalsIgnoreCase(singlePlayer)){
//                    System.out.println( "How many questions would you like?");
//                    int questions = gameplay.nextInt();
//
//                    game(questions);
//                }
//            }
//
//        } catch (Exception e){
//            System.out.println(e.getMessage());
//            System.out.println("Not following directions looses your privilege to choose what you get to play.");
//            game(10);
//        }
//





//    ObjectMapper mapper = new ObjectMapper();
//
//    Map<String,CluesDTO> map = mapper.readValue(Paths.get(String.valueOf(filePath)).toFile(), Map.class);
//
//                for (Map.Entry<String,CluesDTO>entry : map.entrySet()){
//        System.out.println(entry.getKey() + "=" + entry .getValue());
 //       }


//    FileInputStream fileInputStream = new FileInputStream(specificClue);
//    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
//    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


//    String[][] cluesList = new String[1][10];
//    String[] hope;
//    int row = 0;
//
//    String line = bufferedReader.readLine();
//                hope = line.split(",");
//
//                        while ((line != null)){
//                        line = bufferedReader.readLine();
//                        System.out.println(line);
//
//                        String[] data = line.split(",");
//                        for (int i = 0; i < data.length; i++) {
//        cluesList[row][i] = data[i];
//        }
//        row ++;
//        }
//
//        for (int i = 0; i < cluesList.length; i++) {
//        String[] list = cluesList[i];
//
//        for (int j = 0; j < list.length; j++) {
//        System.out.println(list[j]);
//        }
//        }


//    public static String formatActivityOutput(int clueNumber) throws Exception {
//
//
//        File jsonFile = new File(formatURL(INPUT_FILE, clueNumber));
//        ObjectMapper objectMapper = new ObjectMapper();
//        // convert json string to object
//        CluesDTO cluesDTO = objectMapper.readValue(jsonFile, CluesDTO.class);
//
//        return String.valueOf(cluesDTO);
//    }



//    //TODO WHERE OH WHERE IS THE ERROR!!!!
//    public static String formatCluesOutput(String jsonString) throws JsonProcessingException {

//Attempt 1   List<CluesDTO>
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            TypeReference<List<CluesDTO>> typeReferenceAllClueDTO =  new TypeReference<>(){};
//            List<CluesDTO> allCluesList = objectMapper.readValue(jsonString, typeReferenceAllClueDTO);
//
//            CluesDTO clues = objectMapper.readValue(jsonString, CluesDTO.class);
//            return allCluesList;
//        } catch (JsonParseException e) {
//            System.out.println(e.getMessage());
//        }
//        return null;
//    }

//Attempt 2
//        ObjectMapper objectMapper = new ObjectMapper();
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        map.put(objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {}));
//        return String.valueOf(map);
//    }

//Attempt 3
//    ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//        try {
//        CluesDTO clues = objectMapper.readValue(jsonString, CluesDTO.class);
//
//        CluesDTO question = new CluesDTO(clues.getId());
//
//        System.out.println("id" + question.getId());
//        System.out.println("question" + question.getQuestion());
//        System.out.println("answer" + question.getAnswer());
//        return question.toString();
//
//    } catch (IOException e) {
//        return "No activity found";
//    }
//}

//        ObjectMapper objectMapper = new ObjectMapper();
//        CollectionType listType =
//                objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, CluesDTO.class);
//        return objectMapper.readValue(jsonString, listType);
//
//    }


//    // TODO NOT A CSV... WILL NOT WORK
//    public static void readEachLine(String csvContent){
//
//        String bananas  = csvContent.replaceAll("\\{", "");
//        String[] csvLines = bananas.split(",");
//
//        String[] headers = csvLines[0].split(",");
//
//        for (int i = 0; i < csvLines.length; i++) {
//            String dataItem = csvLines[i];
//            String[] dataArray = dataItem.split(",");
//
//            for (int j = 0; j < headers.length; j++) {
//                System.out.println(headers[j] + ": " + dataArray[j]);
//            }
//            System.out.println("");
//        }
//
//    }


//    public static String read(String category) throws IOException{
//
//        FileInputStream inputStream = new FileInputStream(CustomHttpClient.GET_URL);
//        InputStreamReader inputReader = new InputStreamReader(inputStream);
//        BufferedReader bReader = new BufferedReader(inputReader);
//
//        String line;
//        while ((line = bReader.readLine()) != null) {
//            System.out.println(line);
//        }
//
//        return null;
//    }



//public static List<CluesDTO> getCluesList(String jsonString) throws JsonProcessingException {
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    TypeReference<List<CluesDTO>> typeReferenceCluesDTO = new TypeReference<>(){};
//    List<CluesDTO> allClues = objectMapper.readValue(jsonString, typeReferenceCluesDTO);
//
//    return allClues;
//}


//        File jsonFile = new File(GET_URL);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        CluesDTO clues = objectMapper.readValue(jsonFile, CluesDTO.class);
//        System.out.println(clues);


//            Path filepath = Path.of(GET_URL);
//            String x = Files.readString(filepath);

//            FileInputStream inputStream = new FileInputStream(x);
//            InputStreamReader inputReader = new InputStreamReader(inputStream);
//            BufferedReader bReader = new BufferedReader(inputReader);

//            String line;
//            while ((line = bReader.readLine()) != null) {
//                System.out.println(line);
//            }
//            inputStream.close();



//           System.out.println(CustomHttpClient.getCluesList(CustomHttpClient.GET_URL));
//           System.out.println(CustomHttpClient.formatCluesOutput(CustomHttpClient.getClueType(CustomHttpClient.GET_URL,)));



//////////// BackUP
//
//        System.out.println("\n Welcome to Kenzie Trivia! " +
//                "\n The most random facts known to man. " +
//                "\n \n You have ten questions, and will receive one point for each correct answer." +
//                "\n Good Luck! " +
//                "\n");
//        Scanner userInput = new Scanner(System.in);
//
//
//    int counter = 1;
//    int correctCounter = 0;
//    Boolean isCorrect = true;
//
//
//        while (counter != 11) {
//
//            String response;
//            try {
//
//                //Question counter
//                System.out.println("Question " + counter);
//
//                //Random number
//                int random = randomNumber();
//
//                // Finding a random clue using the URL
//                String specificClue = (findSpecificClue(random));
//
//
//                ObjectMapper mapper = new ObjectMapper();
//                Map<String, String> result = mapper.readValue(specificClue, HashMap.class);
//
//                // Pulling question and answer saving as a String value
//                String valueOfQuestion = (String)result.get("question");
//                String valueOfAnswer = (String)result.get("answer");
//
//                // Displaying the score after the first question
//                if (!(counter == 1)) {
//                    if (correctCounter == 0) {
////                        System.out.println("You have not gotten any questions right.");
//                        System.out.println("Score: " + correctCounter + "/" + (counter -1));
//                    } else {
////                        System.out.println("You have gotten " + numberToString(correctCounter) + " question right out of " + numberToString(counter - 1) + ".");
//                        System.out.println("Score: " + correctCounter + "/" + (counter-1));
//                    }
//                }
//
//                // Not giving a second chance for empty String
////                System.out.println("The category is " + pullTitle(random));
////
////                System.out.println(valueOfQuestion);
////                System.out.println("Whats Your Answer?");
////
////                String answer = userInput.nextLine();
////                String formatAnswer = answer.toLowerCase();
////
////
////                if (formatAnswer.equalsIgnoreCase(valueOfAnswer)){
////                    System.out.println("Correct! Nice work keep it up!" + "\n");
////                    correctCounter++;
////                    isCorrect = true;
////                } else {
////                    System.out.println("That is incorrect, the answer is, " + valueOfAnswer + randomIncorrectResponse() + "\n");
////                    isCorrect = false;
////                }
//
//
//                // Giving a second chance for an empty String
//                System.out.println("The category is " + pullTitle(random));
//
//                System.out.println(valueOfQuestion);
//                System.out.println("Whats Your Answer?");
//
//                String answer = userInput.nextLine();
//                String formatAnswer = answer.toLowerCase();
//
//
//                if (!(formatAnswer.isEmpty())){
//
//                    if (formatAnswer.equalsIgnoreCase(valueOfAnswer)) {
//                        System.out.println("Correct! Nice work keep it up!" + "\n");
//                        correctCounter++;
//                        isCorrect = true;
//                    } else {
//                        System.out.println("That is incorrect, the answer is, " + valueOfAnswer + randomIncorrectResponse() + "\n");
//                        isCorrect = false;
//                    }
//
//                } if (formatAnswer.isEmpty()){
//
//                    do {
//                        System.out.println("Not a valid response, You have one more chance to provide a valid answer.");
//                        System.out.println("The category is " + pullTitle(random));
//                        System.out.println(valueOfQuestion);
//                        System.out.println("What's Your Answer?");
//
//                        String answer2 = userInput.nextLine();
//                        String formatSecondAnswer = answer2.toLowerCase();
//
//                        if (formatSecondAnswer.equalsIgnoreCase(valueOfAnswer)) {
//                            System.out.println("Correct! Nice work keep it up!" + "\n");
//                            correctCounter++;
//                            isCorrect = true;
//                        } else {
//                            System.out.println("That is incorrect, the answer is, " + valueOfAnswer + randomIncorrectResponse() + "\n");
//                            isCorrect = false;
//                        }
//
//                    } while (!(formatAnswer.isEmpty()));
//
//                }
//
//                storeTriviaData().put("questionID : ", counter);
//                storeTriviaData().put("question : ", valueOfQuestion);
//                storeTriviaData().put("wasCorrect : ", isCorrect);
//
//
//            } catch (Exception e){
//                System.out.println(e.getMessage());
//            }
//
//           counter++;
//
//
//        }
//
//        System.out.println("Your final score is : " + correctCounter + "/" + (counter-1));
//
//        endGame(correctCounter);
//
//    }
