package com.github.illyrius666.webstormvento

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.lang.html.HTMLLanguage
import com.intellij.openapi.util.text.StringUtil
import com.intellij.psi.html.HtmlTag
import com.intellij.psi.xml.XmlAttribute
import com.intellij.util.ProcessingContext

class VentoAttributeCompletionProvider() : CompletionProvider<CompletionParameters?>() {

    @Suppress("ReturnCount")
    public override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        result: CompletionResultSet
    ) {
        val position = parameters.position

        if (HTMLLanguage.INSTANCE !in position.containingFile.viewProvider.languages) {
            return
        }

        val attribute = position.parent as? XmlAttribute ?: return
        val xmlTag = attribute.parent as? HtmlTag ?: return

        val partialAttribute = StringUtil.trimEnd(attribute.name, CompletionUtilCore.DUMMY_IDENTIFIER_TRIMMED)

        if (partialAttribute.isEmpty()) {
            return
        }

        val suggestions = AutoCompleteSuggestions(xmlTag, partialAttribute)

        suggestions.descriptors.forEach {
            var text = it.attribute

            // If you go back and add a modifier, it ignores the prefix, so we'll
            // just kinda code around that for now
            if (text.contains(':') && text.contains('.')) {
                text = text.substringAfter(':')
            }

            var elementBuilder = LookupElementBuilder
                .create(text)
                .withCaseSensitivity(false)
                .withIcon(Alpine.ICON)
                .withTypeText(it.typeText)

            if (it.hasValue() && !it.canBePrefix()) {
                elementBuilder = elementBuilder.withInsertHandler(XmlAttributeInsertHandler.INSTANCE)
            }

            result.addElement(elementBuilder)
        }
    }
}
