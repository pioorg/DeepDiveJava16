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
package org.przybyl.ddj16.records.s4_nesting;

public class LocalEnumsAndInterfaces {

	public static void main(String[] args) {

// 	please don't forget we're inside a method!

		record LocalRecord() {
			public String introduce() {
				return "Local records work :-)";
			}
		}

		enum LocalEnum {
			SINGLE;

			public String introduce() {
				return "In Java 16 you can have local enums :-D";
			}
		}

		interface LocalInterface {
			static String introduce() {
				return "In Java 16 you can have local interfaces :-D";
			}
		}


		class LocalClass {
			String introduce() {
				return "Local classes too, of course.";
			}
		}


		System.out.println(new LocalRecord().introduce());
		System.out.println(LocalEnum.SINGLE.introduce());
		System.out.println(LocalInterface.introduce());
		System.out.println(new LocalClass().introduce());
	}
}
