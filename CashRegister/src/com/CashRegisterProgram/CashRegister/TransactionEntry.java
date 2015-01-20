package com.CashRegisterProgram.CashRegister;

import java.math.BigDecimal;

/**
 * Created by Henry on 1/18/2015.
 */
public class TransactionEntry {

    private BigDecimal amount;
    private BigDecimal total;
    private Item item;

    public TransactionEntry(Item item, BigDecimal amount, BigDecimal total) {
        this.item = item;
        this.amount = amount;
        this.total = total;
    }

    public String getName() {
        return item.getName();
    }

    public Item getItem() {
        return item;
    }

    public BigDecimal getTotal() {
        return total.setScale(2,BigDecimal.ROUND_HALF_EVEN);
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getAmount() {
        return amount.setScale(2,BigDecimal.ROUND_HALF_EVEN);
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return item.getPricePerQuantifier().setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p/>
     * The {@code equals} method implements an equivalence relation
     * on non-null object references:
     * <ul>
     * <li>It is <i>reflexive</i>: for any non-null reference value
     * {@code x}, {@code x.equals(x)} should return
     * {@code true}.
     * <li>It is <i>symmetric</i>: for any non-null reference values
     * {@code x} and {@code y}, {@code x.equals(y)}
     * should return {@code true} if and only if
     * {@code y.equals(x)} returns {@code true}.
     * <li>It is <i>transitive</i>: for any non-null reference values
     * {@code x}, {@code y}, and {@code z}, if
     * {@code x.equals(y)} returns {@code true} and
     * {@code y.equals(z)} returns {@code true}, then
     * {@code x.equals(z)} should return {@code true}.
     * <li>It is <i>consistent</i>: for any non-null reference values
     * {@code x} and {@code y}, multiple invocations of
     * {@code x.equals(y)} consistently return {@code true}
     * or consistently return {@code false}, provided no
     * information used in {@code equals} comparisons on the
     * objects is modified.
     * <li>For any non-null reference value {@code x},
     * {@code x.equals(null)} should return {@code false}.
     * </ul>
     * <p/>
     * The {@code equals} method for class {@code Object} implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any non-null reference values {@code x} and
     * {@code y}, this method returns {@code true} if and only
     * if {@code x} and {@code y} refer to the same object
     * ({@code x == y} has the value {@code true}).
     * <p/>
     * Note that it is generally necessary to override the {@code hashCode}
     * method whenever this method is overridden, so as to maintain the
     * general contract for the {@code hashCode} method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     * argument; {@code false} otherwise.
     * @see #hashCode()
     * @see java.util.HashMap
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof TransactionEntry))
            return false;
        return (item.getNumber() == ((TransactionEntry) obj).getItem().getNumber()) && (item.getQuantifier() == ((TransactionEntry) obj).getItem().getQuantifier());
    }
}
