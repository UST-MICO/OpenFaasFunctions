/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.openfaas.function.cloudEvent;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import io.cloudevents.json.Json;


public class CloudEventDeserializer {

    public MicoCloudEventImpl<JsonNode> deserialize(String message) {
        if (message == null) {
            return null;
        }
        System.out.println("Trying to parse the message:" + message);
        MicoCloudEventImpl<JsonNode> micoCloudEvent = Json.decodeValue(message, new TypeReference<MicoCloudEventImpl<JsonNode>>() {
        });
        System.out.println("Deserialized micoCloudEvent '" + micoCloudEvent.toString() + "'");

        if (!micoCloudEvent.getData().isPresent()) {
            // data is entirely optional
            System.out.println("Received message does not include any data!");
        }
        return micoCloudEvent;
    }

}
