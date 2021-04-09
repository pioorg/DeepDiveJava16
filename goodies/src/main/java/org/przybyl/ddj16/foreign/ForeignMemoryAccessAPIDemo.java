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


import java.lang.invoke.VarHandle;
import java.nio.ByteOrder;

import jdk.incubator.foreign.MemoryAccess;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.MemoryHandles;
import jdk.incubator.foreign.MemoryLayout;
import jdk.incubator.foreign.MemorySegment;
import jdk.incubator.foreign.SequenceLayout;

/**
 * Demo of Foreign Memory Access API incubator feature of Java 16, 3rd preview,
 * based on https://openjdk.java.net/jeps/392
 * <p>
 * Please don't forget to --add-modules jdk.incubator.foreign
 */
public class ForeignMemoryAccessAPIDemo {

    private static MemorySegment readerSegment;

    public static void main(String[] args) throws InterruptedException {
        sameThreadDemo();
        twoThreadsDemo();
    }

    private static void sameThreadDemo() {
        SequenceLayout intArrayLayout = MemoryLayout.ofSequence(10, MemoryLayout.ofValueBits(32, ByteOrder.nativeOrder()));
        try (MemorySegment segment = MemorySegment.allocateNative(intArrayLayout)) {
            populateNativeMem(intArrayLayout, segment);
            examineNativeMem(intArrayLayout, segment);
        }
    }

    private static void twoThreadsDemo() throws InterruptedException {
        SequenceLayout intArrayLayout = MemoryLayout.ofSequence(10, MemoryLayout.ofValueBits(32, ByteOrder.nativeOrder()));
        MemorySegment segment = MemorySegment.allocateNative(intArrayLayout);
        populateNativeMem(intArrayLayout, segment);
        examineNativeMem(intArrayLayout, segment);
        var readerThread = new Thread(() -> examineAndClose(intArrayLayout, readerSegment), "readerThread");
        readerSegment = segment.handoff(readerThread);
        readerThread.start();
//        examineNativeMem(intArrayLayout, segment);
    }

    private static void populateNativeMem(SequenceLayout intArrayLayout, MemorySegment segment) {
        VarHandle intHandle = MemoryHandles.varHandle(int.class, ByteOrder.nativeOrder());
        VarHandle intElemHandle = intArrayLayout.varHandle(int.class, MemoryLayout.PathElement.sequenceElement());
        for (int i = 0; i < intArrayLayout.elementCount().getAsLong(); i++) {
            MemoryAccess.setIntAtOffset(segment, i * 4L, i);
//                intHandle.set(segment, i * 4L, i);
//                intElemHandle.set(segment, (long) i, i);
        }
    }

    private static void examineNativeMem(SequenceLayout intArrayLayout, MemorySegment segment) {
        MemoryAddress segmentBaseAddress = segment.address();
        System.out.println("Examining memory at: " + segmentBaseAddress.toRawLongValue());
        System.out.println("Segment owned by: " + segment.ownerThread().getName());
        VarHandle intElemHandle = intArrayLayout.varHandle(int.class, MemoryLayout.PathElement.sequenceElement());
        for (int i = 0; i < intArrayLayout.elementCount().getAsLong(); i++) {
            System.out.print(MemoryAccess.getIntAtOffset(segment, i * 4L));
            System.out.print(" ");
            System.out.println(intElemHandle.get(segment, (long) i));
        }
    }

    private static void examineAndClose(SequenceLayout intArrayLayout, MemorySegment readerSegment) {
        examineNativeMem(intArrayLayout, readerSegment);
        readerSegment.close();
//        examineNativeMem(intArrayLayout, readerSegment);
    }
}
