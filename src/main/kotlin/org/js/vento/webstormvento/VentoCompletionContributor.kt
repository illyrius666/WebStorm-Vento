package org.js.vento.webstormvento

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.XmlPatterns.xmlAttribute
import com.intellij.psi.xml.XmlTokenType

class VentoCompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC,
            psiElement(XmlTokenType.XML_NAME).withParent(xmlAttribute()),
            VentoAttributeCompletionProvider()
        )
    }
}
