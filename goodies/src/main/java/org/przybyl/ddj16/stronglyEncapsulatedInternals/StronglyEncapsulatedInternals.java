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

package org.przybyl.ddj16.stronglyEncapsulatedInternals;


import java.lang.reflect.Field;
import java.time.Instant;
import java.util.Arrays;

/**
 * You may wish to run this as
 * java --add-opens java.base/java.lang=ALL-UNNAMED --add-opens java.base/java.time=ALL-UNNAMED org/przybyl/ddj16/stronglyEncapsulatedInternals/StronglyEncapsulatedInternals.java
 */
public class StronglyEncapsulatedInternals {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        deepLookInto("Hello --illegal-access=deny!");
        secondsIn(Instant.now());
    }

    // copied from https://youtu.be/hAbvZs6bJP8?t=2207
    // and https://github.com/pioorg/trivialJ11Migration/blob/b3f61a9e2cda3a1b46614bce667baa1bd098be3c/src/main/java/simple/migration/App.java#L60
    private static void deepLookInto(String input) {
        try {
            Field valueField = String.class.getDeclaredField("value");
            valueField.setAccessible(true);
            byte[] actualValue = (byte[])valueField.get(input);
            System.out.println("Actual char array in string is: " + Arrays.toString(actualValue));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void secondsIn(Instant now) throws NoSuchFieldException, IllegalAccessException {
        var secondsField = now.getClass().getDeclaredField("seconds");
        secondsField.setAccessible(true);
        System.out.println(secondsField.get(now));
    }
}
