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

package org.przybyl.ddj16.foreign;


import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.nio.file.Path;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.LibraryLookup;
import jdk.incubator.foreign.MemoryAddress;

/**
 * Please don't forget to --add-modules jdk.incubator.foreign
 * and -Dforeign.restricted=permit
 * Most probably you'll need to tune the way of obtaining the clang.
 */
public class ForeignLinkerAPIDemo {


    public static void main(String[] args) throws Throwable {

//        LibraryLookup libclang = LibraryLookup.ofDefault()
        LibraryLookup libclang = LibraryLookup.ofPath(Path.of("/usr/lib/x86_64-linux-gnu/libclang-10.so.1"));

        MethodHandle methodHandle = CLinker.getInstance().downcallHandle(
            libclang.lookup("clang_getClangVersion").get(),
            MethodType.methodType(MemoryAddress.class),
            FunctionDescriptor.of(CLinker.C_POINTER));
        MemoryAddress res = (MemoryAddress) methodHandle.invoke();
        var version = CLinker.toJavaStringRestricted(res);
        System.out.println(version);
    }


}
