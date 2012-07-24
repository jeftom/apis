/*
 * Copyright 2012 SURFnet bv, The Netherlands
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.surfnet.oaaas.resource;

import com.yammer.dropwizard.testing.ResourceTest;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.surfnet.oaaas.model.ResourceServer;
import org.surfnet.oaaas.repository.ResourceServerRepository;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ResourceServerResourceTest extends ResourceTest {

  @Mock
  private ResourceServerRepository repository;

  @InjectMocks
  private ResourceServerResource resourceServerResource;

  @Override
  protected void setUpResources() throws Exception {
    resourceServerResource = new ResourceServerResource();
    MockitoAnnotations.initMocks(this);
    addResource(resourceServerResource);
  }


  @Test
  public void getServer() {
    ResourceServer s = new ResourceServer();
    when(repository.findOne(1L)).thenReturn(s);
    assertThat("GET requests fetch the server by ID",
        client().resource("/resourceServer/1").get(ResourceServer.class),
        is(s));
    verify(repository).findOne(1L);
  }
}