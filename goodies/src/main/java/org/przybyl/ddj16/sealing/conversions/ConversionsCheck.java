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

package org.przybyl.ddj16.sealing.conversions;


interface Interface {
}

sealed class Klass {
}

non-sealed class Child extends Klass {
}
//final class Child extends Klass {}


public class ConversionsCheck {

    public static void main(String[] args) {
        var object = getObject();
        Interface interf = (Interface) object;
        if (object instanceof Interface) {
            System.out.println("The " + object + " is an " + Interface.class.getSimpleName());
        }
    }

    private static Klass getObject() {
        return null;
    }
}
