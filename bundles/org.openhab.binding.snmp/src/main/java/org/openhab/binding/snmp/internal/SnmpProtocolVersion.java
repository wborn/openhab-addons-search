/**
 * Copyright (c) 2010-2023 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.snmp.internal;

/**
 * The {@link SnmpProtocolVersion} enum defines the datatype of SNMP channels
 *
 * @author Jan N. Klug - Initial contribution
 */

public enum SnmpProtocolVersion {
    v1(0),
    V1(0),
    v2c(1),
    V2C(1);

    private final int value;

    private SnmpProtocolVersion(int value) {
        this.value = value;
    }

    public int toInteger() {
        return value;
    }
}
