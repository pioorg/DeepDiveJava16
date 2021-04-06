/*
 *  Copyright (C) 2020 Piotr Przybył
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

package org.przybyl.ddj16.smartCasting;

import java.io.*;
import java.time.*;

public class InstanceOfPatternMatching {

    private static Long aLong = 0L;

    public static void main(String[] args) {
        whoAreYou(Instant.now());
        whoAreYou("Hi");
        whoAreYou("Hello");
        whoAreYou(-2);
        whoAreYou(12);
        whoAreYou(777L);

        System.out.printf("Is 12 a non empty String? %b%n", nonEmptyString(12));
        System.out.printf("""
            Is "12" a non empty String? %b%n""", nonEmptyString("12"));
        System.out.printf("""
            Is "" a non empty String? %b%n""", nonEmptyString(""));

        for (int i = 0; i < 10; i++) {
            notFinal();
        }
    }

    public static void whoAreYou(Object obj) {
        if (obj instanceof Serializable serializable) {
            System.out.println("You're serializable " + serializable + "!");
        }
        if (obj instanceof String string && string.length() >= 3) {
            System.out.println("You're String, at least 3 characters long!");
        } else if (obj instanceof Integer integer && integer > 0) {
            System.out.println("You're a positive Integer");
        } else if (obj instanceof Long aLong && (aLong < -9 || aLong > 9)) {
            System.out.println("You're a Long with at least two digits");
        }
    }

    public static boolean nonEmptyString(Object obj) {
        return (obj instanceof String str) && !str.isEmpty();
    }

    public static void notFinal() {
        var number = getRandomNumber();
        if (number instanceof Long aLong) {
            aLong = 42L;
            System.out.println(aLong);
        } else if (number instanceof Float aFloat) {
            aFloat = 42f;
            System.out.println(aFloat);
        }
//        System.out.println(aLong);
    }

    private static Object getRandomNumber() {
        if (Math.random() < 0.5) {
            return 17L;
        } else {
            return 15f;
        }
    }

    public static void pointlessChecks() {
//		Long aLong = 42L;
//		if (aLong instanceof Number number) {
//			System.out.println("This should not work.");
//		}
//
//		if (aLong instanceof String str) {
//			System.out.println("This should not work.");
//		}
    }
}
