package com.github.illyrius666.webstormvento

import com.intellij.psi.PsiElement
import com.intellij.psi.meta.PsiPresentableMetaData
import com.intellij.psi.xml.XmlTag
import com.intellij.util.ArrayUtil
import com.intellij.xml.impl.BasicXmlAttributeDescriptor

class VentoAttributeDescriptor(
    private val name: String,
    private val xmlTag: XmlTag
) :
    BasicXmlAttributeDescriptor(),
    PsiPresentableMetaData {

    private val info: AttributeInfo = AttributeInfo(name)

    override fun getIcon() = Vento.ICON

    override fun getTypeName(): String = info.typeText

    override fun init(psiElement: PsiElement) {}

    override fun isRequired(): Boolean = false

    override fun hasIdType(): Boolean = name == "id"

    override fun hasIdRefType(): Boolean = false

    override fun isEnumerated(): Boolean = !info.hasValue()

    override fun getDeclaration(): PsiElement? = xmlTag

    override fun getName(): String = name

    override fun getDependencies(): Array<Any> = ArrayUtil.EMPTY_OBJECT_ARRAY

    override fun isFixed(): Boolean = false

    override fun getDefaultValue(): String? = null

    override fun getEnumeratedValues(): Array<String>? = ArrayUtil.EMPTY_STRING_ARRAY
}
