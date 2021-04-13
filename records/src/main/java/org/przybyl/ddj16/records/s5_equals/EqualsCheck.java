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

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class EqualsCheck {

    public static void main(String[] args) throws IOException, InterruptedException {

        var box = new HashSet<Rekord>();

        var bar1 = new Rekord(Instant.ofEpochSecond(1618309360), new ArrayList<>(Collections.singletonList("factory")));
        var bar2 = new Rekord(Instant.ofEpochSecond(1618309370), new ArrayList<>(Collections.singletonList("factory")));

        box.add(bar1);
        box.add(bar2);

        bar1.locations().add("warehouseAlpha");
        bar2.locations().add("warehouseBeta");

        box.add(bar1);
        box.add(bar2);

        System.out.print("So, how many rekords we have in our box?");
        Thread.sleep(1000L);
        System.out.println(" [Press enter when ready...]");
        System.in.read();
        System.out.println(box.size());
        System.in.read();
        box.forEach(System.out::println);

    }
}

record Rekord(Instant created, List<String> locations) {
}
