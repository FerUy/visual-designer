/*
 * TeleStax, Open Source Cloud Communications
 * Copyright 2011-2014, Telestax Inc and individual contributors
 * by the @authors tag.
 *
 * This program is free software: you can redistribute it and/or modify
 * under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package org.restcomm.connect.rvd.http.resources;

import com.google.gson.Gson;
import org.restcomm.connect.rvd.RvdConfiguration;
import org.restcomm.connect.rvd.http.RestService;
import org.restcomm.connect.rvd.model.ClientConfiguration;
import org.restcomm.connect.rvd.utils.RvdUtils;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Client configuration rest service
 *
 * @author otsakir@gmail.com - Orestis Tsakiridis
 */

@Path("config")
public class ConfigurationRestService extends RestService {

    @PostConstruct
    public void init() {
        super.init();
    }

    @GET
    public Response getConfiguration() {
        RvdConfiguration config = this.applicationContext.getConfiguration();
        ClientConfiguration clientConfig = new ClientConfiguration();
        clientConfig.videoSupport = config.getVideoSupport();
        // return rvd.xml/restcommBaseUrl parameter if set
        if (config.getRawRvdConfig() != null && ! RvdUtils.isEmpty(config.getRawRvdConfig().getRestcommBaseUrl()) )
            clientConfig.restcommBaseUrl = config.getRawRvdConfig().getRestcommBaseUrl();
        clientConfig.ussdSupport = config.isUssdSupport();
        Gson gson = new Gson();
        return Response.ok(gson.toJson(clientConfig), MediaType.APPLICATION_JSON_TYPE).build();
    }
}
