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
package org.openhab.binding.robonect.internal;

import static org.openhab.binding.robonect.internal.RobonectBindingConstants.THING_TYPE_AUTOMOWER;

import java.util.Collections;
import java.util.Set;

import org.eclipse.jetty.client.HttpClient;
import org.openhab.binding.robonect.internal.handler.RobonectHandler;
import org.openhab.core.i18n.TimeZoneProvider;
import org.openhab.core.io.net.http.HttpClientFactory;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingTypeUID;
import org.openhab.core.thing.binding.BaseThingHandlerFactory;
import org.openhab.core.thing.binding.ThingHandler;
import org.openhab.core.thing.binding.ThingHandlerFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The {@link RobonectHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Marco Meyer - Initial contribution
 */
@Component(service = ThingHandlerFactory.class, configurationPid = "binding.robonect")
public class RobonectHandlerFactory extends BaseThingHandlerFactory {

    private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Collections.singleton(THING_TYPE_AUTOMOWER);

    private HttpClient httpClient;
    private TimeZoneProvider timeZoneProvider;

    @Activate
    public RobonectHandlerFactory(@Reference HttpClientFactory httpClientFactory,
            @Reference TimeZoneProvider timeZoneProvider) {
        this.httpClient = httpClientFactory.getCommonHttpClient();
        this.timeZoneProvider = timeZoneProvider;
    }

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(THING_TYPE_AUTOMOWER)) {
            return new RobonectHandler(thing, httpClient, timeZoneProvider);
        }

        return null;
    }
}
