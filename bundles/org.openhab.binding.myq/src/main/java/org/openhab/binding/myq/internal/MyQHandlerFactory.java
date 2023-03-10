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
package org.openhab.binding.myq.internal;

import static org.openhab.binding.myq.internal.MyQBindingConstants.*;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jetty.client.HttpClient;
import org.openhab.binding.myq.internal.handler.MyQAccountHandler;
import org.openhab.binding.myq.internal.handler.MyQGarageDoorHandler;
import org.openhab.binding.myq.internal.handler.MyQLampHandler;
import org.openhab.core.auth.client.oauth2.OAuthFactory;
import org.openhab.core.io.net.http.HttpClientFactory;
import org.openhab.core.thing.Bridge;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingTypeUID;
import org.openhab.core.thing.binding.BaseThingHandlerFactory;
import org.openhab.core.thing.binding.ThingHandler;
import org.openhab.core.thing.binding.ThingHandlerFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The {@link MyQHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Dan Cunningham - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.myq", service = ThingHandlerFactory.class)
public class MyQHandlerFactory extends BaseThingHandlerFactory {
    private final HttpClient httpClient;
    private OAuthFactory oAuthFactory;

    @Activate
    public MyQHandlerFactory(final @Reference HttpClientFactory httpClientFactory,
            final @Reference OAuthFactory oAuthFactory) {
        this.httpClient = httpClientFactory.getCommonHttpClient();
        this.oAuthFactory = oAuthFactory;
    }

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (THING_TYPE_ACCOUNT.equals(thingTypeUID)) {
            return new MyQAccountHandler((Bridge) thing, httpClient, oAuthFactory);
        }

        if (THING_TYPE_GARAGEDOOR.equals(thingTypeUID)) {
            return new MyQGarageDoorHandler(thing);
        }

        if (THING_TYPE_LAMP.equals(thingTypeUID)) {
            return new MyQLampHandler(thing);
        }

        return null;
    }
}
