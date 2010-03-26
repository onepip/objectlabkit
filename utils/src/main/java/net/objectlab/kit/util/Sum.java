/*
 * ObjectLab, http://www.objectlab.co.uk/open is sponsoring the ObjectLab Kit.
 * 
 * Based in London, we are world leaders in the design and development 
 * of bespoke applications for the securities financing markets.
 * 
 * <a href="http://www.objectlab.co.uk/open">Click here to learn more</a>
 *           ___  _     _           _   _          _
 *          / _ \| |__ (_) ___  ___| |_| |    __ _| |__
 *         | | | | '_ \| |/ _ \/ __| __| |   / _` | '_ \
 *         | |_| | |_) | |  __/ (__| |_| |__| (_| | |_) |
 *          \___/|_.__// |\___|\___|\__|_____\__,_|_.__/
 *                   |__/
 *
 *                     www.ObjectLab.co.uk
 *
 * $Id: AbstractDateCalculator.java 309 2010-03-23 21:01:49Z marchy $
 * 
 * Copyright 2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.objectlab.kit.util;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Mutable class representing a sum of BigDecimals.
 * @author Benoit Xhenseval
 *
 */
public class Sum implements Serializable {
    private static final long serialVersionUID = -8583271171731930344L;
    private BigDecimal value = BigDecimal.ZERO;
    private int count = 0;

    public Sum() {
        this(BigDecimal.ZERO, 2);
    }

    public Sum(final BigDecimal start) {
        if (start != null) {
            value = start;
        }
    }

    public Sum(final BigDecimal start, final int scale) {
        if (start != null) {
            value = start.setScale(scale, BigDecimal.ROUND_HALF_UP);
        }
    }

    public Sum(final Sum start) {
        if (start != null) {
            value = start.getTotal();
        }
    }

    public BigDecimal getTotal() {
        return value;
    }

    public void setTotal(final BigDecimal total) {
        if (total != null) {
            this.value = total;
        }
    }

    /**
     * @return the current Sum with new total.
     */
    public Sum add(final BigDecimal... value) {
        this.value = BigDecimalUtil.add(this.value, value);
        count += value.length;
        return this;
    }

    public Sum subtract(final BigDecimal... value) {
        this.value = BigDecimalUtil.subtract(this.value, value);
        count += value.length;
        return this;
    }

    public Sum add(final Integer value) {
        this.value = BigDecimalUtil.add(this.value, new BigDecimal(value));
        count++;
        return this;
    }

    /**
     * @return the current Sum with new total.
     */
    public Sum add(final Sum value) {
        if (value != null) {
            this.value = BigDecimalUtil.add(this.value, value.getTotal());
            count++;
        }
        return this;
    }

    /**
     * @return the current Sum with new total.
     */
    public Sum minus(final BigDecimal... value) {
        this.value = BigDecimalUtil.subtract(this.value, value);
        count += value.length;
        return this;
    }

    /**
     * @return the current Sum with new total.
     */
    public Sum minus(final Sum value) {
        if (value != null) {
            this.value = BigDecimalUtil.subtract(this.value, value.getTotal());
            count++;
        }
        return this;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public boolean isZero() {
        return BigDecimalUtil.isZero(value);
    }

    public boolean isNotZero() {
        return !isZero();
    }

    public boolean isNegative() {
        return BigDecimalUtil.isNegative(value);
    }

    public boolean isZeroOrLess() {
        return BigDecimalUtil.isZeroOrLess(value);
    }

    public int getCount() {
        return count;
    }
}
