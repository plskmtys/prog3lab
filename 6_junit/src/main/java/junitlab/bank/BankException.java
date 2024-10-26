package junitlab.bank;

/**
 * A bankkal kapcsolatos kiv�telek �soszt�lya.
 */
public abstract class BankException extends Exception {

    /**
     * A sz�mlasz�m, amelyn�l a hiba fell�pett.
     */
    private String accountNumber;

    /**
     * A kiv�tel l�trehoz�sa.
     * @param accountNumber A sz�mlasz�m, amelyn�l a hiba fell�pett.
     */
    public BankException(String accountNumber) {
        super();
        this.accountNumber = accountNumber;
    }

    /**
     * A kiv�tel l�trehoz�sa az adott hiba�zenettel.
     * @param accountNumber A sz�mlasz�m, amelyn�l a hiba fell�pett.
     * @param message A hiba�zenet sz�vege.
     */
    public BankException(String accountNumber, String message) {
        super(message);
        this.accountNumber = accountNumber;
    }

    /**
     * A hib�t okoz� sz�mlasz�m lek�rdez�se.
     * @return A sz�mlasz�m, amelyn�l a kiv�tel fell�pett.
     */
    public String getAccountNumber() {
        return accountNumber;
    }
}
