package com.java_inaction.transaction_quiz;


import java.util.*;
import java.util.stream.Collectors;

public class TransactionService {

    static Trader raoul = new Trader("Raoul", "Cambridge");
    static Trader mario = new Trader("Mario", "Milan");
    static Trader alan = new Trader("Alan", "Cambridge");
    static Trader brian = new Trader("Brian", "Cambridge");
    static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300, "231231356"),
            new Transaction(raoul, 2012, 1000, "231231356"),
            new Transaction(raoul, 2011, 400, "231231356"),
            new Transaction(mario, 2012, 710, "231231356"),
            new Transaction(mario, 2012, 700, "231231356"),
            new Transaction(alan, 2012, 95, "2312313560")
    );

    public List<Transaction> getTransactionsIn2011() {
        return transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .toList();
    }

    public List<String> uniqueCitiesAmongTraders() {
        var traders = Arrays.asList(raoul, mario, alan, brian);
        return traders.stream()
                .map(Trader::getCity)
                .distinct()
                .toList();
    }

    public List<Trader> getTradersFromCambridgeAndSortedByName() {
        var traders = Arrays.asList(raoul, mario, alan, brian);
        return traders.stream()
                .filter(t -> t.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .toList();
    }

    public String sortedTraderNames() {
        var traders = Arrays.asList(raoul, mario, alan, brian);
        String concatStringTraders = traders.stream()
                .map(Trader::getName)
                .sorted()
                .reduce("", String::concat);
        // using joining to concatenate string is more efficient since it internally use StringBuilder
        // no need to copy a String object everytime concatenate a new String like above did
        String joiningTraders = traders.stream().map(Trader::getName).collect(Collectors.joining());
        return joiningTraders;
    }

    public boolean checkTraderInMilan() {
        var traders = Arrays.asList(raoul, mario, alan, brian);
        return traders.stream().anyMatch(t -> t.getCity().equals("Milan"));
    }

    public void printTransactionsInCambridge() {
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .forEach(System.out::println);
    }

    public Transaction maxValueAmongTransaction() {
        Optional<Long> maxVal = transactions.stream().map(Transaction::getValue).reduce(Long::max);
        Optional<Transaction> maxComparator = transactions.stream().max(Comparator.comparing(Transaction::getValue));
        return maxComparator.orElseGet(Transaction::new);
    }

    public Transaction minValueAmongTransaction() {
        Optional<Long> minVal = transactions.stream().map(Transaction::getValue).reduce(Long::min);
        Optional<Transaction> minComparator = transactions.stream().min(Comparator.comparing(Transaction::getValue));
        return minComparator.orElseGet(Transaction::new);
    }

    public void filterFirstDigitOfTransaction() {
        // error-prone -> dealing with two objects, 1. iterate using iterator, but remove using transaction
        for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext(); ) {
            Transaction transaction = iterator.next();
            if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
                transactions.remove(transaction);
            }
        }
        for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext(); ) {
            Transaction transaction = iterator.next();
            if (Character.isDigit(transaction.getReferenceCode().charAt(0))) {
                iterator.remove();
            }
        }

        transactions.removeIf(t -> Character.isDigit(t.getReferenceCode().charAt(0)));
    }
}
