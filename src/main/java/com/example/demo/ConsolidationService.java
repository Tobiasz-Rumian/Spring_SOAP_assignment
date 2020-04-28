package com.example.demo;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ConsolidationService {

    private final Random random = new Random();

    public List<Loan> getUserLoans(int userId) {
        return switch (random.nextInt(5)) {
            case 0 -> generateListOfRandomLoans(3);
            case 1 -> generateListOfRandomLoans(2);
            case 2 -> generateListOfRandomLoans(1);
            case 3 -> generateListOfRandomLoans(4);
            case 4 -> generateListOfRandomLoans(5);
            default -> List.of();
        };
    }

    public Offer calculateOffer(List<Loan> selectedLoans) {
        var offer = new Offer();
        offer.loanAmount = Precision.round(selectedLoans.stream().map(loan -> loan.remainingAmount).reduce(0.0, Double::sum), 2);
        offer.numberOfPayments = random.nextInt(39) + 1;
        offer.monthlyAmount = Precision.round(offer.loanAmount / offer.numberOfPayments, 2);
        return offer;
    }

    public boolean acceptOffer(Offer offer) {
        return true;
    }

    private List<Loan> generateListOfRandomLoans(int size) {
        var loans = new ArrayList<Loan>();
        for (var i = 0; i < size; i++) loans.add(generateRandomLoan());
        return loans;
    }


    private Loan generateRandomLoan() {
        var loan = new Loan();
        loan.accountNumber = getRandomAccountNumber();
        loan.currency = "PLN";
        loan.remainingAmount = Precision.round(1000 + (100000 - 1000) * random.nextDouble(), 2);
        return loan;
    }


    private String getRandomAccountNumber() {
        StringBuilder sb = new StringBuilder(26);
        for (int i = 0; i < 26; i++) sb.append("0123456789".charAt(random.nextInt(10)));
        return sb.toString();
    }
}
