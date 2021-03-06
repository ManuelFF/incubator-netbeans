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

package org.netbeans.modules.hibernate.completion;

import java.net.URL;
import javax.lang.model.element.Element;
import javax.swing.Action;
import org.netbeans.api.java.source.CompilationController;
import org.netbeans.api.java.source.ui.ElementJavadoc;
import org.netbeans.spi.editor.completion.CompletionDocumentation;

/**
 *
 * 
 */
public abstract class HibernateCompletionDocumentation implements CompletionDocumentation {

    public static HibernateCompletionDocumentation getAttribValueDoc(String text) {
        return new AttribValueDoc(text);
    }
    
     public static HibernateCompletionDocumentation createJavaDoc(CompilationController cc, Element element) {
        return new JavaElementDoc(ElementJavadoc.create(cc, element));
    }
    
    public URL getURL() {
        return null;
    }

    public CompletionDocumentation resolveLink(String link) {
        return null;
    }

    public Action getGotoSourceAction() {
        return null;
    }
    
    
    
    private static class AttribValueDoc extends HibernateCompletionDocumentation {

        private String text;

        public AttribValueDoc(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }
    
    private static class JavaElementDoc extends HibernateCompletionDocumentation {

        private ElementJavadoc elementJavadoc;

        public JavaElementDoc(ElementJavadoc elementJavadoc) {
            this.elementJavadoc = elementJavadoc;
        }

        @Override
        public JavaElementDoc resolveLink(String link) {
            ElementJavadoc doc = elementJavadoc.resolveLink(link);
            return doc != null ? new JavaElementDoc(doc) : null;
        }

        @Override
        public URL getURL() {
            return elementJavadoc.getURL();
        }

        public String getText() {
            return elementJavadoc.getText();
        }

        @Override
        public Action getGotoSourceAction() {
            return elementJavadoc.getGotoSourceAction();
        }
    }
}
