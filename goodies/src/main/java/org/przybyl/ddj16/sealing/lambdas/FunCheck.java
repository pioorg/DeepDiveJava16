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

package org.przybyl.ddj16.sealing.lambdas;

public class FunCheck {

    static int funCall(FunSealInt fun) {
        int i1 = fun.aFunction("lambda check");
        if (fun instanceof FunSeal1) {
            return 1;
        } else if (fun instanceof FunSeal2) {
            return 2;
            // else still needed ?
        } else {
            return 0;
        }
    }

    static void lambdaCheck() {
        funCall(new FunSeal1());
        funCall(new FunSeal2());
        // lambdas don't work here
        // funCall((String arg) -> arg.getBytes().length);
    }
}

@FunctionalInterface
interface FunInt {
    static void funCall(FunInt fun) {
        var aString = "lambda check";
        int i1 = fun.aFunction(aString);
    }

    int aFunction(String arg);
}


final class Fun1 implements FunInt {
    @Override
    public int aFunction(String arg) {
        return arg.length();
    }
}

final class Fun2 implements FunInt {
    @Override
    public int aFunction(String arg) {
        return arg.hashCode();
    }
}