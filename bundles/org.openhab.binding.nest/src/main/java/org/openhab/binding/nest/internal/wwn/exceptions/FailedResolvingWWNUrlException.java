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
package org.openhab.binding.nest.internal.wwn.exceptions;

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * Will be thrown when the bridge was unable to resolve the Nest redirect URL.
 *
 * @author Wouter Born - Initial contribution
 * @author Wouter Born - Improve exception handling while sending data
 */
@NonNullByDefault
@SuppressWarnings("serial")
public class FailedResolvingWWNUrlException extends Exception {
    public FailedResolvingWWNUrlException(String message) {
        super(message);
    }

    public FailedResolvingWWNUrlException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedResolvingWWNUrlException(Throwable cause) {
        super(cause);
    }
}
