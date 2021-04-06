/*
 *  Copyright (C) 2021 Piotr Przybył
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */


package org.przybyl.ddj16.valueBasedClasses;

/**
 You may want to run this with:
 -XX:+UnlockDiagnosticVMOptions -XX:DiagnoseSyncOnValueBasedClasses=2
 */
public class ValueBasedClasses {
    private final static Long aLong = new Long(42);

    public static void main(String[] args) {
        synchronized (aLong) {
            System.out.println("Calling you from withing the synchronized block");
        }
        System.out.println("Let's check if 42 == 42");
        if (aLong != Long.valueOf(42L)) {
            System.out.println("No, they're not ==");
        } else {
            System.out.println("Yes, they're ==");
        }
    }
}
