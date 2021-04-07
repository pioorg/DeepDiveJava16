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

public class SealedFunCheck {

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

//@FunctionalInterface
sealed interface FunSealInt
    // no need for permit if children are in the same source file
    //  permits FunSeal1, FunSeal2
{
    int aFunction(String arg);
}

final class FunSeal1 implements FunSealInt {
    // this won't work, as permitted classes need to extend/implement directly
// sealed class FunSeal1 implements FunSealInt permits FunSeal2 {
    @Override
    public int aFunction(String arg) {
        return arg.length();
    }
}

final class FunSeal2 implements FunSealInt {
    @Override
    public int aFunction(String arg) {
        return arg.hashCode();
    }
}
