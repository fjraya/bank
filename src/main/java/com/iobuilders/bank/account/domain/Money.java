package com.iobuilders.bank.account.domain;

import com.iobuilders.bank.user.domain.ValueObject;

public class Money implements ValueObject<Double> {
    private Double amount;

    public Money(Double amount) {
        this.amount = amount;
    }

    public Money() {
        this(0.0);
    }


    public Money add(Money money) {
        return new Money(this.amount + money.value());
    }


    @Override
    public Double value() {
        return this.amount;
    }

    public Money minus() {
        return new Money(-this.amount);
    }

    public boolean greaterOrEqualsThan(Money money) {
        return this.add(money.minus()).amount >= 0;
    }
}
