/*
 * Copyright 2016-2017 the original author or authors.
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

package org.joinfaces.autoconfigure.mojarra;

import java.util.Set;

import javax.faces.component.html.HtmlPanelGroup;
import javax.servlet.annotation.HandlesTypes;

import com.sun.faces.config.FacesInitializer;
import com.sun.faces.facelets.compiler.UIText;
import org.joinfaces.autoconfigure.servlet.initializer.JsfClassFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.core.annotation.AnnotationUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class JsfClassFactoryTest {

	private JsfClassFactory.Configuration configuration;

	@BeforeEach
	public void setUp() {
		this.configuration = JsfClassFactory.Configuration.builder()
				.excludeScopedAnnotations(false)
				.anotherConfig(MojarraInitializerRegistrationBean.ANOTHER_CONFIG)
				.handlesTypes(AnnotationUtils.findAnnotation(FacesInitializer.class, HandlesTypes.class))
				.build();
	}

	@Test
	public void testJavaxFacesHtmlPanelGroupWithMojarra() {
		Set<Class<?>> classes = new JsfClassFactory(this.configuration).getOtherClasses();
		assertThat(classes).contains(HtmlPanelGroup.class);
	}

	@Test
	public void testMojarraUITextWithMojarra() {
		Set<Class<?>> classes = new JsfClassFactory(this.configuration).getOtherClasses();
		assertThat(classes).contains(UIText.class);
	}

}
