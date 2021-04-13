/*
 *
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
 *
 */
package org.przybyl.ddj16.records.s6_other;


sealed interface Bird {
    String color();
}

record Duck(String color, float swimmingSpeed, float flyingSpeed) implements Bird {
}

record Chicken(String color, float eggsPerWeek) implements Bird {
}


public class OtherFeatures {

    public static void main(String[] args) {
        describe(new Chicken("brownish gold", 7));
        describe(new Duck("black", 0.7F, 4));
    }

    private static void describe(Bird aBird) {
        System.out.println("Let's see what bird we have, shall we...?");
        if (aBird instanceof Duck duck) {
            System.out.println("We have a duck! " + duck);
        } else if (aBird instanceof Chicken chicken) {
            System.out.println("We have " + chicken.color() + " chicken laying " + chicken.eggsPerWeek() + " egg(s) per week!");
        }
    }
}

