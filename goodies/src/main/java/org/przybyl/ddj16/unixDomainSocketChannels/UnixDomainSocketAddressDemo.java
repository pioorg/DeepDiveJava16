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

package org.przybyl.ddj16.unixDomainSocketChannels;

import java.io.IOException;
import java.net.Socket;
import java.net.UnixDomainSocketAddress;

/**
 * For this demo you may need to change the local auth method in PostgreSQL not to be peer, e.g. md5
 */
public class UnixDomainSocketAddressDemo {
    public static void main(String[] args) throws IOException {
        var linuxPostgesqlUnixSocketAddress = "/var/run/postgresql/.s.PGSQL.5432";
        var unixSocketAddress = UnixDomainSocketAddress.of(linuxPostgesqlUnixSocketAddress);
        var unixSocket = new Socket();
        unixSocket.connect(unixSocketAddress);
    }
}
