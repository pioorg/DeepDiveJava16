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

package org.przybyl.ddj16.records.s5_equals;

import java.util.ArrayList;
import java.util.List;

public class CashedHashCode {

    public static void main(String[] args) {
        record IsHashCodeCached(List<?> list) {}
        var list = new ArrayList<String>();
        var record = new IsHashCodeCached(list);
        var words = List.of("ene", "due", "like", "fake");
        printHashCodes(record, list);
        for (String word : words) {
            list.add(word);
            printHashCodes(record, list);
        }
    }

    static void printHashCodes(Record record, List<?> list) {
        System.out.printf("%d, %d, %s%n", record.hashCode(), list.hashCode(), list);
    }


}
